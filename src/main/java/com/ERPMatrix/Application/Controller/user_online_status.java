package com.ERPMatrix.Application.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ERPMatrix.Application.Model.User.User;
import com.ERPMatrix.Application.Repository.userRepository;

@Configuration
@EnableScheduling
public class user_online_status {

	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private userRepository userRepo;

	private boolean cheak_Date(Date lastDisplay) {
		Date date_Now = new Date();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh mm");
		SimpleDateFormat dd = new SimpleDateFormat("dd");
		SimpleDateFormat mm = new SimpleDateFormat("MM");
		SimpleDateFormat yy = new SimpleDateFormat("yyyy");
		SimpleDateFormat hh = new SimpleDateFormat("hh");
		SimpleDateFormat m = new SimpleDateFormat("mm");

		if (Integer.parseInt(yy.format(lastDisplay)) < Integer.parseInt(yy.format(date_Now))) {

			return false;
		} else {

			if (Integer.parseInt(mm.format(lastDisplay)) < Integer.parseInt(mm.format(date_Now))) {

				return false;

			} else {

				if (Integer.parseInt(dd.format(lastDisplay)) < Integer.parseInt(dd.format(date_Now))) {

					return false;
				} else {

					if (Integer.parseInt(hh.format(lastDisplay)) < Integer.parseInt(hh.format(date_Now))) {
						return false;

					} else {
						if (Integer.parseInt(m.format(lastDisplay)) < Integer.parseInt(m.format(date_Now))) {

							int min = Integer.parseInt(m.format(lastDisplay)) - Integer.parseInt(m.format(date_Now));
							if (min < 20) {

								return true;
							} else {

								return false;

							}

						} else {

							return true;

						}

					}

				}

			}

		}

	}

	@Scheduled(cron = "0 */15 * * * ?")
	private void cheakUser() {

		List<User> User = userRepo.findAll();

		for (int x = 0; x < User.size(); x++) {

			boolean cheak = cheak_Date(User.get(x).getLastLoginDateDispaly());
			if (!cheak) {
				LOGGER.info("This user is offline : " + User.get(x).getUsername());
				User.get(x).setOnline(false);
				userRepo.save(User.get(x));
			} else {
				LOGGER.info("This user is online : " + User.get(x).getUsername());

				User.get(x).setOnline(true);
				userRepo.save(User.get(x));

			}

		}

	}

}
