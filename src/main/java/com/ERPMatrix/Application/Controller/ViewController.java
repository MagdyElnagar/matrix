package com.ERPMatrix.Application.Controller;

import static com.ERPMatrix.Application.Constant.SecurityConfig.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

import java.security.Key;

import javax.security.auth.login.AccountExpiredException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ERPMatrix.Application.Model.User.User;
import com.ERPMatrix.Application.Model.User.UserPrincipal;
import com.ERPMatrix.Application.Service.UserService;
import com.ERPMatrix.Application.Service.admin.pc_info_Serv;
import com.ERPMatrix.Application.utilty.JWTTokenProvider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Controller
public class ViewController {
	private AuthenticationManager authenticationManager;
	private DBAutoBackupController DBAutoBackupController;
	private JWTTokenProvider jwtTokenProvider;
	private pc_info_Serv pc_info_Serv;
	@Value("${user.ip}")
	private String userip;
	private UserService userService;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	public ViewController(UserService userService, AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider, DBAutoBackupController DBAutoBackupController,
			pc_info_Serv pc_info_Serv) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.DBAutoBackupController = DBAutoBackupController;
		this.pc_info_Serv = pc_info_Serv;

	}



	
    // التحويل إلى الصفحة الرئيسية
    @GetMapping("/")
    public String home() {
    	
        return "home";
    }
    @GetMapping("/product")
    public String showAddProductForm() {
        return "redirect:templates/product/product.html";
    }

    // عرض صفحة تسجيل الدخول
    @GetMapping("/login")
    public String showProductsPage() {
        return "login"; // اسم الملف بدون .html
    }


	
	@PostMapping("/logining")
	public String logining(@RequestParam(name = "username") String  username , @RequestParam(name = "password") String  password) throws AccountExpiredException {
		System.out.println("loging class");
		authentication(username, password);

		User loginUser = userService.findByUsername(username);
		Model model = null;
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);

		HttpHeaders httpheader = getJwtHeader(userPrincipal);
		
		
		LOGGER.info("Token: " + httpheader);
		
		 return "home";
	}
	
	private void authentication(String username, String password) throws AccountExpiredException {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}



	private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
		System.out.println("getJwtHeader");
		HttpHeaders headers = new HttpHeaders();
		
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
		
		System.out.println("JWT_TOKEN_HEADER : " + headers);
		
		return headers;
	}

	
    @GetMapping("/perform_login")
    public String perform_login() {
    	


        return "redirect:/home";
    }
    
}