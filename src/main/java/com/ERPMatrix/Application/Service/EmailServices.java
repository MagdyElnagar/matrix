package com.ERPMatrix.Application.Service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

import static com.ERPMatrix.Application.Constant.EmailConstant.*;

@Service
public class EmailServices {
	public void sendNewPasswordEmail(String firsName, String username, String password, String email)
			throws MessagingException {
		Message message = createEmail(firsName, username, password, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_EMAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWEORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();

	}

	public void sendEmail(String email, String body,String Subject)
			throws MessagingException {
		Message message = createEmailSender( email,  body,Subject);


		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_EMAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWEORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();

	}
	private Message MessageUpdatePasswor(String firsName, String username, String email, String password)
			throws AddressException, MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
		message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(EMAIL_SUBJECT);
		message.setText("Hello " + firsName
				+ ", \n \nYour account password has been changed successfuly."
				+ "\n\nYour new password is : " + password
				+ "\n\nThanks" + " \nMedicalMatrix Support Team");
		/*
		 *
		 * ("Hello " + firsName +
		 * ",\n \nYour account password has been changed successfuly." + "\n\nThanks" +
		 * " \nYour new password is : " + password +"\n\nThanks" +
		 * " \nMedicalMatrix Support Team");
		 */
		message.setSentDate(new Date());
		message.saveChanges();
		return message;

	}
	public void sendPasswordChange(String firsName, String username, String email, String password)
			throws MessagingException {
		Message message = MessageUpdatePasswor(firsName, username, email, password);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_EMAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWEORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();

	}


	private Message createEmailSender(String email, String body,String Subject)
			throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
		message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(Subject);
		message.setText(body);
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	private Message createEmail(String firsName, String username, String password, String email)
			throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
		message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(EMAIL_SUBJECT);
		message.setText(
				"Hello " + firsName + ", \n \nWelcome to School Application " + " \nYour username is:"
						+ username + " \nAnd your account password is:" + password +"\nPlease keep it secret and try to change it." + "\n\nThanks"+ " \nThe Support Team");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	private Session getEmailSession() {
		Properties properties = System.getProperties();
		properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
		properties.put(SMTP_AUTH, true);
		properties.put(SMTP_PORT, DEFAULT_PORT);
		properties.put(SMTP_STARTTLS_ENABLE, true);
		properties.put(SMTP_STARTTLS_REQUIRED, true);
		return Session.getInstance(properties, null);

	}

}
