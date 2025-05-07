package com.ERPMatrix.Application.Controller.Users;

import static com.ERPMatrix.Application.Constant.EmailConstant.EMAIL_SENT;
import static com.ERPMatrix.Application.Constant.FileConstant.TEMP_PROFILE_IMAGE_BASE_URL;
import static com.ERPMatrix.Application.Constant.SecurityConfig.JWT_TOKEN_HEADER;
import static com.ERPMatrix.Application.Constant.UserImplConstant.USER_DELETE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountExpiredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ERPMatrix.Application.Constant.HttpResponse;
import com.ERPMatrix.Application.Controller.DBAutoBackupController;
import com.ERPMatrix.Application.Model.User.EmailModel;
import com.ERPMatrix.Application.Model.User.User;
import com.ERPMatrix.Application.Model.User.UserPcInformation;
import com.ERPMatrix.Application.Model.User.UserPrincipal;
import com.ERPMatrix.Application.Service.UserService;
import com.ERPMatrix.Application.Service.admin.pc_info_Serv;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.EmailExistException;
import com.ERPMatrix.Application.exception.domain.EmailNotFoundException;
import com.ERPMatrix.Application.exception.domain.HandlerException;
import com.ERPMatrix.Application.exception.domain.PasswordExistException;
import com.ERPMatrix.Application.exception.domain.PasswordNotFoundException;
import com.ERPMatrix.Application.exception.domain.UsernameExistException;
import com.ERPMatrix.Application.utilty.JWTTokenProvider;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping(path = { "/", "/user" })
@CrossOrigin(origins = "*")
public class UserController extends ExceptionHandling {

	private AuthenticationManager authenticationManager;
	private DBAutoBackupController DBAutoBackupController;
	private JWTTokenProvider jwtTokenProvider;
	private pc_info_Serv pc_info_Serv;
	@Value("${user.ip}")
	private String userip;
	private UserService userService;

	@Autowired
	public UserController(UserService userService, AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider, DBAutoBackupController DBAutoBackupController,
			pc_info_Serv pc_info_Serv) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.DBAutoBackupController = DBAutoBackupController;
		this.pc_info_Serv = pc_info_Serv;

	}

	@PostMapping("/addNewUser")
	public ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("username") String username,
			@RequestParam("email") String email, @RequestParam("role") String role,
			@RequestParam("isActive") Boolean isActive, @RequestParam("isNonLock") Boolean isNonLock,
			@RequestParam(value = "profileImage", required = true) MultipartFile profileImage)
			throws UsernameNotFoundException, UsernameExistException, EmailNotFoundException, EmailExistException,
			MessagingException, IOException {

		User newUser = userService.addNewUser(firstName, lastName, username, email, role, isNonLock, isActive,
				profileImage);
		return new ResponseEntity<>(newUser, OK);
	}

	private void authentication(String username, String password) throws AccountExpiredException {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	@PostMapping("Change_AUTHORITIES")
	public ResponseEntity<User> Change_AUTHORITIES(@RequestParam("username") String username,
			@RequestParam("role") String role) {
		User newUser = userService.Change_AUTHORITIES(username, role);
		return new ResponseEntity<>(newUser, OK);
	}

	@DeleteMapping("/deleteUser/{id}")
	@PreAuthorize("hasAnyAuthority('user:delete')")
	public ResponseEntity<HttpResponse> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);

		return response(OK, USER_DELETE);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<User>> findAll() {

		List<User> user = userService.getUsers();

		return new ResponseEntity<>(user, OK);

	}

	@PostMapping("/find")
	public ResponseEntity<User> findByName(@RequestParam() String username) {

		User user = userService.findByUsername(username);

		return new ResponseEntity<>(user, OK);

	}

	@GetMapping("/findNames")
	public ResponseEntity<List<User>> findNames() {

		List<User> user = userService.getUsers();

		for (int x = 0; x < user.size(); x++) {
			user.get(x).setPassword("");
			user.get(x).setAuthorities(null);
		}

		return new ResponseEntity<>(user, OK);

	}

	private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
		System.out.println("getJwtHeader");
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
		System.out.println("JWT_TOKEN_HEADER : " + headers);
		return headers;
	}

	@GetMapping(path = "/image/profile/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
	public byte[] getProgileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName)
			throws IOException {
		URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try (InputStream inputStream = url.openStream()) {
			int bytesRead;
			byte[] chunk = new byte[1024];
			while ((bytesRead = inputStream.read(chunk)) > 0) {
				byteArrayOutputStream.write(chunk, 0, bytesRead);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return byteArrayOutputStream.toByteArray();
	}

	@PostMapping("/goOffline")
	public ResponseEntity<User> goOffline(@RequestParam("username") String username) {
		User findUser = userService.findByUsername(username);

		findUser.setOnline(false);
		userService.save(findUser);
		return null;
	}



	@PostMapping("/login")
	public ResponseEntity<User> loging(@RequestBody User user) throws AccountExpiredException {
		System.out.println("loging class");
		authentication(user.getUsername(), user.getPassword());
		System.out.println("After authentication");

		User loginUser = userService.findByUsername(user.getUsername());

		UserPrincipal userPrincipal = new UserPrincipal(loginUser);

		HttpHeaders httpheader = getJwtHeader(userPrincipal);

		return new ResponseEntity<>(loginUser, httpheader, OK);
	}
	
	


	@GetMapping("onlineStatus")
	public ResponseEntity<List<User>> onlineStatus() {

		List<User> findAll = userService.getUsers();

		ArrayList<User> users = new ArrayList<>();
		for (int x = 0; x < findAll.size(); x++) {

			users.add(new User(null, null, null, x, false, false, false, null, findAll.get(x).getLastLoginDate(),
					findAll.get(x).getLastLoginDateDispaly(), null, null, findAll.get(x).getOnline(), null, null, null,
					null, x, null, findAll.get(x).getUsername()));
//

		}

		return new ResponseEntity<>(users, OK);

	}

	@GetMapping("/PC_INFO/{username}/{MAC_Adress}/{pcname}/{ip}")
	public ResponseEntity<Boolean> PC_INFO(@PathVariable String username, @PathVariable String MAC_Adress,
			@PathVariable String pcname, @PathVariable String ip) throws AccountExpiredException {
		UserPcInformation pc = new UserPcInformation();
		pc.setIp(ip);
		pc.setNewtworkid(MAC_Adress);
		pc.setUsername(username);
		pc.setPcname(pcname);

		UserPcInformation pcCheak = pc_info_Serv.save(pc);
		if (pcCheak == null || pcCheak.equals(null)) {
			System.out.println(pcCheak.getUsername());
			return new ResponseEntity<>(false, OK);

		} else {
			return new ResponseEntity<>(true, OK);

		}

	}

	@GetMapping("/PC_INFO/findAll")
	public ResponseEntity<List<UserPcInformation>> PC_INFO_findAll() throws AccountExpiredException {

		List<UserPcInformation> pcCheak = pc_info_Serv.findAll();

		return new ResponseEntity<>(pcCheak, OK);

	}

	@GetMapping("/PC_INFO/findByUsername/{username}")
	public ResponseEntity<UserPcInformation> PC_INFO_findByUsername(@PathVariable String username)
			throws AccountExpiredException {

		UserPcInformation pcCheak = pc_info_Serv.findByUsername(username);

		return new ResponseEntity<>(pcCheak, OK);

	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) throws UsernameNotFoundException,
			UsernameExistException, EmailNotFoundException, EmailExistException, MessagingException {
		User newUser = userService.register(user);

		return new ResponseEntity<>(newUser, OK);
	}

	@GetMapping("/resetpassword/{email}")
	public ResponseEntity<HttpResponse> resetpassword(@PathVariable String email)
			throws EmailNotFoundException, MessagingException {

		userService.resetPasswprd(email);

		return response(OK, EMAIL_SENT + email);
	}

	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
	}

	@PostMapping("SendEmail/{email}")
	public ResponseEntity<HttpResponse> sendEmail(@PathVariable("email") String email,
			@RequestBody EmailModel EmailModel) throws MessagingException {

		userService.sendEmail(EmailModel.getEmail(), EmailModel.getBody(), EmailModel.getSubject());

		return response(OK, EMAIL_SENT + email);

	}

	@GetMapping("/token/{username}/{password}")
	public ResponseEntity<Boolean> token(@PathVariable String username, @PathVariable String password)
			throws AccountExpiredException {

		authentication(username, password);
		User loginUser = userService.findByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders httpheader = getJwtHeader(userPrincipal);

		return new ResponseEntity<>(true, OK);
	}

	@PostMapping("/changePassword/{newpassword}")
	public ResponseEntity<User> updatePassword(@PathVariable("newpassword") String newpassword, @RequestBody User user)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, MessagingException,
			HandlerException, PasswordNotFoundException, PasswordExistException {

		User updatePassword = userService.updatePassword(user.getUsername(), user.getPassword(), newpassword);
		UserPrincipal userPrincipal = new UserPrincipal(updatePassword);
		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<>(updatePassword, headers, OK);
	}

	@PostMapping("/updateProfileImage")
	public ResponseEntity<User> updateProfileImage(@RequestParam("username") String username,
			@RequestParam(value = "profileImage", required = true) MultipartFile profileImage)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, IOException {
		User updateUserImage = userService.updateProfileImage(username, profileImage);
		return new ResponseEntity<>(updateUserImage, OK);
	}

	@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UsernameNotFoundException,
			UsernameExistException, EmailNotFoundException, EmailExistException, MessagingException, IOException {

		User updateUser = userService.updateUser(user);
		return new ResponseEntity<>(updateUser, OK);
	}

	@PostMapping("/updateUserAuth")
	public ResponseEntity<User> updateUserAuth(@RequestBody User user) throws UsernameNotFoundException,
			UsernameExistException, EmailNotFoundException, EmailExistException, MessagingException {

		User newUser = userService.register(user);

		return new ResponseEntity<>(newUser, OK);
	}

}
