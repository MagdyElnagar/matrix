package com.ERPMatrix.Application.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.ERPMatrix.Application.Model.User.User;
import com.ERPMatrix.Application.exception.domain.EmailExistException;
import com.ERPMatrix.Application.exception.domain.EmailNotFoundException;
import com.ERPMatrix.Application.exception.domain.HandlerException;
import com.ERPMatrix.Application.exception.domain.PasswordExistException;
import com.ERPMatrix.Application.exception.domain.PasswordNotFoundException;
import com.ERPMatrix.Application.exception.domain.UsernameExistException;

public interface UserService {

	User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNotLock,
			boolean isActive, MultipartFile prifileImage)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, IOException;

	User Change_AUTHORITIES(String username, String role);

	void deleteUser(long id);

	User findByEmail(String email);

	User findById(Long userid);

	User findByUsername(String username);

	List<User> getUsers();

	Date imHere();

	User ImOnline(String username);

	void logMeOut();

	User register(User user) throws UsernameNotFoundException, UsernameExistException, EmailNotFoundException,
			EmailExistException, MessagingException;

	void resetPasswprd(String email) throws EmailNotFoundException, MessagingException;

	User save(User User);

	void sendEmail(String email, String body, String subject) throws MessagingException;

	User updatePassword(String username, String password, String newpassword)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, MessagingException,
			HandlerException, PasswordNotFoundException, PasswordExistException;

	User updateProfileImage(String username, MultipartFile profileImage)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, IOException;

	User updateUser(User user)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, IOException;
}
