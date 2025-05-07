package com.ERPMatrix.Application.Service_implement;

import static com.ERPMatrix.Application.Constant.FileConstant.DEFAULT_USER_IMAGE_PATH;
import static com.ERPMatrix.Application.Constant.FileConstant.DOT;
import static com.ERPMatrix.Application.Constant.FileConstant.JPG_EXTENSION;
import static com.ERPMatrix.Application.Constant.FileConstant.USER_FOLDER;
import static com.ERPMatrix.Application.Constant.UserImplConstant.EMAIL_ALREDY_EXISTS;
import static com.ERPMatrix.Application.Constant.UserImplConstant.EMPTY;
import static com.ERPMatrix.Application.Constant.UserImplConstant.EXIST_PASSWORD;
import static com.ERPMatrix.Application.Constant.UserImplConstant.NO_USER_FOUND_BY_EMAIL;
import static com.ERPMatrix.Application.Constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;
import static com.ERPMatrix.Application.Constant.UserImplConstant.USERNAME_ALREDY_EXISTS;
import static com.ERPMatrix.Application.Constant.UserImplConstant.WRONG_PASSWORD;
import static com.ERPMatrix.Application.Constant.UserImplConstant.WRONG_USER_OR_PASSWORD;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ERPMatrix.Application.Model.User.User;
import com.ERPMatrix.Application.Model.User.UserPrincipal;
import com.ERPMatrix.Application.Repository.userRepository;
import com.ERPMatrix.Application.Service.EmailServices;
import com.ERPMatrix.Application.Service.UserService;
import com.ERPMatrix.Application.Service.loginAtempServices;
import com.ERPMatrix.Application.enumeration.Role;
import com.ERPMatrix.Application.exception.domain.EmailExistException;
import com.ERPMatrix.Application.exception.domain.EmailNotFoundException;
import com.ERPMatrix.Application.exception.domain.HandlerException;
import com.ERPMatrix.Application.exception.domain.UsernameExistException;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
	private EmailServices emailServices;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	private loginAtempServices loginAtempServices;
	private Date old_date;
	private BCryptPasswordEncoder passwordEncoder;
	private userRepository userRepo;

	@Autowired
	public UserServiceImpl(userRepository userRepo, BCryptPasswordEncoder passwordEncoder,
			loginAtempServices loginAtempServices, EmailServices emailServices) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.loginAtempServices = loginAtempServices;
		this.emailServices = emailServices;

	}

	@Override
	public User addNewUser(String firstName, String lastName, String username, String email, String role,
			boolean isNotLock, boolean isActive, MultipartFile prifileImage)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, IOException {
		validateNewUsernameAndEmail(EMPTY, username, email);
		User user = new User();
		String password = generatePassword();
		String encodedPassword = encodedPassword(password);
		user.setUserId(generateUserId());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setEmail(email);
		user.setJoinDate(new Date());
		user.setPassword(encodedPassword);
		user.setActice(isActive);
		user.setNotLocked(isNotLock);
		user.setRole(getRoleEnumname(role).name());

		user.setAuthorities(getRoleEnumname(role).getAuthorities());

		user.setProfileImageUrl(getTemporaryProfileImageUrl(username));
		userRepo.save(user);
		saveProfileImage(user, prifileImage);
		return user;
	}

	@Override
	public User Change_AUTHORITIES(String username, String role) {

		User user = findByUsername(username);
		user.setRole(getRoleEnumname(role).name());
		user.setAuthorities(getRoleEnumname(role).getAuthorities());

		return user;
	}

	@Override
	public void deleteUser(long id) {
		userRepo.deleteById(id);

	}

	private String encodedPassword(String password) {

		return passwordEncoder.encode(password);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	@Override
	public User findById(Long userid) {

		return userRepo.findByIdIsContaining(userid);
	}

	@Override
	public User findByUsername(String username) {
		User user = userRepo.findByUsername(username);
		return user;
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

	private Role getRoleEnumname(String role) {
		return Role.valueOf(role.toUpperCase());
	}

	private String getTemporaryProfileImageUrl(String username) {

		return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username)
				.toUriString();
	}

	@Override
	public List<User> getUsers() {

		return userRepo.findAll();
	}

	@Override
	public Date imHere() {

		Date date_now = new Date();

		this.old_date = date_now;

		return null;
	}

	@Override
	public User ImOnline(String username) {
		User getUser = userRepo.findByUsername(username);
		getUser.setLastLoginDateDispaly(new Date());
		getUser.setOnline(true);
		userRepo.save(getUser);

		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
		} else {
			valdateLoginAttemp(user);
			user.setLastLoginDateDispaly(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			user.setOnline(true);
			userRepo.save(user);
			UserPrincipal userprinc = new UserPrincipal(user);
			return userprinc;

		}
	}

	@Override
	public void logMeOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public User register(User user)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, MessagingException {

		validateNewUsernameAndEmail(EMPTY, user.getUsername(), user.getEmail());
		String password = user.getPassword();
		String encodedPassword = encodedPassword(user.getPassword());
		user.setUserId(generateUserId());
		user.setJoinDate(new Date());
		user.setPassword(encodedPassword);
		user.setNotLocked(true);
		user.setRole(Role.ROLE_USER.name());
		user.setAuthorities(Role.ROLE_USER.getAuthorities());
		user.setNotExpired(true);
		user.setActice(true);
		user.setProfileImageUrl(getTemporaryProfileImageUrl(user.getUsername()));
		userRepo.save(user);
		emailServices.sendNewPasswordEmail(user.getFirstName(), user.getUsername(), password, user.getEmail());

		// send Email with password
		return user;
	}

	@Override
	public void resetPasswprd(String email) throws EmailNotFoundException, MessagingException {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + " :" + email);
		} else {
			String password = generatePassword();
			LOGGER.info("New Password : " + password);
			user.setPassword(encodedPassword(password));
			emailServices.sendNewPasswordEmail(user.getFirstName(), user.getUsername(), password, email);

		}

	}

	@Override
	public User save(User User) {

		return userRepo.save(User);
	}

	private void saveProfileImage(User user, MultipartFile profileImage) throws IOException {

		if (profileImage != null) {

			Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
			if (!Files.exists(userFolder)) {
				Files.createDirectories(userFolder, null);
			}
			Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
			Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION),
					REPLACE_EXISTING);
			user.setProfileImageUrl(setProfileImageUrl(user.getUsername()));
			userRepo.save(user);
		} else {

		}

	}

	@Override
	public void sendEmail(String email, String body, String subject) throws MessagingException {

		emailServices.sendEmail(email, body, subject);
	}

	private String setProfileImageUrl(String username) {

		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(DEFAULT_USER_IMAGE_PATH + username + DOT + JPG_EXTENSION).toUriString();
	}

	@Override
	public User updatePassword(String username, String password, String newPassword) throws UsernameNotFoundException,
			EmailExistException, UsernameExistException, MessagingException, HandlerException {
		User currentUser = userRepo.findByUsername(username);
		validateUser(currentUser);

		System.out.println("Old password : " + password + " New password : " + newPassword + " Current user : "
				+ currentUser.getPassword());

		if (!passwordEncoder.matches(password, currentUser.getPassword())) {

			throw new HandlerException(WRONG_PASSWORD, "UserService updatePassword");

		} else {

			if (passwordEncoder.matches(newPassword, currentUser.getPassword())) {
				throw new HandlerException(EXIST_PASSWORD, "UserService updatePassword");
			} else {
				currentUser.setPassword(encodedPassword(newPassword));

				userRepo.save(currentUser);
				emailServices.sendPasswordChange(currentUser.getFirstName(), currentUser.getUsername(),
						currentUser.getEmail(), newPassword);
				return currentUser;

			}

		}

	}

	@Override
	public User updateProfileImage(String username, MultipartFile profileImage)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, IOException {
		User user = validateNewUsernameAndEmail(username, null, null);
		saveProfileImage(user, profileImage);
		return user;
	}

	@Override
	public User updateUser(User user)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, IOException {

		User currentUser = userRepo.findByUsername(user.getUsername());

		currentUser.setAuthorities(user.getAuthorities());
		userRepo.save(currentUser);
		return currentUser;
	}

	public void valdateLoginAttemp(User user) {
		if (user.isNotLocked()) {
			if (loginAtempServices.hasExceededMaxAttempts(user.getUsername())) {
				user.setNotLocked(false);
			} else {
				user.setNotLocked(true);
			}
		} else {
			loginAtempServices.evictUserFromLoginAttemptCache(user.getUsername());
		}

	}

	private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException {
		User currentUser = findByUsername(newUsername);
		User userByUsername = findByUsername(newUsername);
		User userByEmail = findByEmail(newEmail);
		if (StringUtils.isNotBlank(currentUsername)) {

			if (currentUser == null) {
				throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			}

			if (userByUsername != null && currentUser.getId() != userByUsername.getId()) {

				throw new UsernameExistException(USERNAME_ALREDY_EXISTS + ": " + newUsername);

			}

			if (userByEmail != null && currentUser.getId() != userByEmail.getId()) {

				throw new EmailExistException(EMAIL_ALREDY_EXISTS);

			}

			return currentUser;

		} else {

			if (userByUsername != null) {
				throw new UsernameExistException(USERNAME_ALREDY_EXISTS);
			}
			if (userByEmail != null) {
				throw new EmailExistException(EMAIL_ALREDY_EXISTS);
			}
			return null;
		}
	}

	private User validateUser(User user) throws HandlerException {

		User getUser = userRepo.findByUsername(user.getUsername());

		if (getUser == null) {
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + user.getUsername());
		} else if (getUser.getPassword() != user.getPassword() || getUser.getUsername() != user.getUsername()) {

			throw new HandlerException(WRONG_USER_OR_PASSWORD, "UserService validateUser");

		}
		return getUser;

	}
}
