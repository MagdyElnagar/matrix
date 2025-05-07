package com.ERPMatrix.Application.filter;

import static com.ERPMatrix.Application.Constant.SecurityConfig.OPTIONS_HTTP_METHOD;
import static com.ERPMatrix.Application.Constant.SecurityConfig.TOKEN_PREFIX;
import static com.ERPMatrix.Application.Constant.emplyee.EMPLOYEE_NAME;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ERPMatrix.Application.utilty.JWTTokenProvider;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	String ip;
	private JWTTokenProvider jwtTokenProvider;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	String macAddr;
	@Value("${user.ip}")
	private String userip;

	private com.ERPMatrix.Application.Service.UserService userService;

	public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider,
			com.ERPMatrix.Application.Service.UserService userService) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		this.ip = request.getRemoteAddr();
		this.macAddr = "";
	
		LOGGER.info(String.valueOf("request URL: " + request.getRequestURI()));
		LOGGER.info(String.valueOf("request IP Adress : " + request.getRemoteAddr()));
		InetAddress inetAddress = InetAddress.getByName(request.getRemoteAddr());
		userip = request.getRemoteAddr();

		// LOGGER.info(String.valueOf("request Mac Adress : " + this.macAddr));
		try {
			// LOGGER.info(String.valueOf("request getMacAddress : " + getMacAddress()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
			response.setStatus(HttpStatus.OK.value());
		} else {
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			String token = authorizationHeader.substring(TOKEN_PREFIX.length());
			StringBuilder sb = new StringBuilder(token);
			int i = token.getBytes().length - 1;
			sb.deleteCharAt(i);
			sb.deleteCharAt(0);
			String username = jwtTokenProvider.getSubject(String.valueOf(sb));
			LOGGER.info(String.valueOf("Username From Token : " + username));
			EMPLOYEE_NAME = username;
			userService.ImOnline(username);
			if (jwtTokenProvider.isTokenValid(username, String.valueOf(sb))
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(String.valueOf(sb));
				Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				SecurityContextHolder.clearContext();
			}
		}
		filterChain.doFilter(request, response);

	}

	public void getMac() {

		try {
			// InetAddress address = InetAddress.getLocalHost();
			InetAddress address = InetAddress.getByName(this.ip);

			/*
			 * Get NetworkInterface for the current host and then read the hardware address.
			 */
			NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			if (ni != null) {
				byte[] mac = ni.getHardwareAddress();

				if (mac != null) {
					/*
					 * Extract each array of mac address and convert it to hexadecimal with the
					 * following format 08-00-27-DC-4A-9E.
					 */
					for (int i = 0; i < mac.length; i++) {
						// System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");

						this.macAddr = this.macAddr + String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");

					}
				} else {
					System.out.println("Address doesn't exist or is not accessible.");
				}
			} else {
				System.out.println("Network Interface for the specified address is not found.");

			}
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
	}

	public void getMac2() {

		try {

			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface network = networkInterfaces.nextElement();
				System.out.println("network : " + network);
				byte[] mac = network.getHardwareAddress();
				if (mac == null) {
					System.out.println("null mac");
				} else {
					System.out.print("MAC address : ");

					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
					}
					System.out.println(sb.toString());
					break;
				}
			}
		} catch (SocketException e) {

			e.printStackTrace();

		}
	}

	public String getMacAddress() throws Exception {
		String macAddress = null;
		String command = "ifconfig";

		String osName = System.getProperty("os.name");
		System.out.println("Operating System is " + osName);

		if (osName.startsWith("Windows")) {
			command = "ipconfig /all";
		} else if (osName.startsWith("Linux") || osName.startsWith("Mac") || osName.startsWith("HP-UX")
				|| osName.startsWith("NeXTStep") || osName.startsWith("Solaris") || osName.startsWith("SunOS")
				|| osName.startsWith("FreeBSD") || osName.startsWith("NetBSD")) {
			command = "ifconfig -a";
		} else if (osName.startsWith("OpenBSD")) {
			command = "netstat -in";
		} else if (osName.startsWith("IRIX") || osName.startsWith("AIX") || osName.startsWith("Tru64")) {
			command = "netstat -ia";
		} else if (osName.startsWith("Caldera") || osName.startsWith("UnixWare") || osName.startsWith("OpenUNIX")) {
			command = "ndstat";
		} else {// Note: Unsupported system.
			throw new Exception("The current operating system '" + osName + "' is not supported.");
		}

		Process pid = Runtime.getRuntime().exec(command);
		BufferedReader in = new BufferedReader(new InputStreamReader(pid.getInputStream()));
		Pattern p = Pattern.compile("([\\w]{1,2}(-|:)){5}[\\w]{1,2}");
		while (true) {
			String line = in.readLine();
			System.out.println("line " + line);
			if (line == null)
				break;

			Matcher m = p.matcher(line);
			if (m.find()) {
				macAddress = m.group();
				break;
			}
		}
		in.close();
		return macAddress;
	}
}
