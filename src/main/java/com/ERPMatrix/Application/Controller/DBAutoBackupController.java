package com.ERPMatrix.Application.Controller;

import static com.ERPMatrix.Application.Constant.Account.DATABASE_NAME;
import static com.ERPMatrix.Application.Constant.Account.DATABASE_PASSWORD;
import static com.ERPMatrix.Application.Constant.Account.DATABASE_USERNAME;
import static com.ERPMatrix.Application.Constant.Account.MYSQL;
import static com.ERPMatrix.Application.Constant.FileConstant.DATABASE_PATH;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ERPMatrix.Application.Model.admin.Data_Base_BackUp;
import com.ERPMatrix.Application.Repository.Admin.database_backup;;

@Configuration
@EnableScheduling
public class DBAutoBackupController {
	@Autowired
	private database_backup database_backup;
//	@Scheduled(cron = "*/2 * * * * ?") every 2 sec
//	@Scheduled(cron = "* */30 * * * ?") every 30 min

	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

	//@Scheduled(cron = "* */2 * * * ?")
	public void schedule() throws ClassNotFoundException, IOException, SQLException {
		Data_Base_BackUp PK_Information = new Data_Base_BackUp();
		PK_Information.setMessiondate(new Date());
		PK_Information.setStatus(true);
		PK_Information.setUsername("backup taken by System Automaticly");
		LOGGER.info("Backup Started at " + new Date());
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy_MM_dd_(hh_mm)");
		SimpleDateFormat FolderFormat = new SimpleDateFormat("yyyy_MM_dd");

		Date backupDate = new Date();
		String dbNameList = DATABASE_NAME;

		String fileName = "Daily_DB_Backup"; // default file name
		String folderPath = DATABASE_PATH + "\\" + FolderFormat.format(backupDate);
		File f1 = new File(folderPath);
		f1.mkdir(); // create folder if not exist

		String saveFileName = fileName + "_" + String.valueOf(SDF.format(backupDate)) + ".sql";
		String savePath = DATABASE_PATH + "\\" + FolderFormat.format(backupDate) + File.separator + saveFileName;
		PK_Information.setFilename(savePath);

		//////////////

		File f = new File(saveFileName);
		String path = f.getPath();

		String executeCmd = MYSQL + "mysqldump  -u" + DATABASE_USERNAME + " -p" + DATABASE_PASSWORD
				+ " --add-drop-database -B " + DATABASE_NAME + " --port 3306 -r " + savePath;
		Process runtimeProcess;
		try {
        //   System.out.println(executeCmd);//this out put works in mysql shell
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);

//            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			LOGGER.info(executeCmd);

			LOGGER.info("processComplete");

			if (processComplete == 0) {
				LOGGER.info("Backup created successfully");

			} else {
				PK_Information.setStatus(false);
				LOGGER.info("Could not create the backup");

			}
			database_backup.save(PK_Information);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
