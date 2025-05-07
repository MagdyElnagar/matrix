package com.ERPMatrix.Application.Constant;

import java.util.Map;

public class FileConstant {
	public static final String DATABASE_PATH = "D:\\erp_matrix back_up";

	public static final String DEFAULT_USER_IMAGE_PATH = "/user/image/profile/";
	public static final String DIRECTORY_CREATED = "Created directory for: ";

	public static final String DOT = ".";

	static Map<String, String> env = System.getenv();
	public static final String FILE_SAVED_IN_FILE_SYSTEM = "Saved file in file system by name:";
	public static final String FORWARD_SLASH = "/";
	public static final String JPG_EXTENSION = "jpg";
	public static final String MYSQL_PATH = env.get("MYSQLDUMP");

	public static final String TEMP_PROFILE_IMAGE_BASE_URL = "https://photos.google.com/search/_tra_/photo/AF1QipOMWOHZYtN5QqhvdCQfVXTT-gEarUayxqQryzhd";
	public static final String USER_FOLDER = System.getProperty("user.home" + "/ERBmatrix/user/");
	public static final String USER_IMAGE_PATH = "/user/image/";

}
