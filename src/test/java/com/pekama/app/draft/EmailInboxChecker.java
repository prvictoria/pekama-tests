package com.pekama.app.draft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class EmailInboxChecker {

	private static final String SMTP_PROPERTIES_MAC = "src/lib/smtp.properties";
	private static final String SMTP_PROPERTIES_WIN = "src\\lib\\smtp.properties";

	public static int checkEmailsAmount(String username, String password) {
		Properties props = new Properties();
		String pathToProperties = null;
		Folder inbox = null;
		Integer messagesAmount = null;
		String operationSystem = System.getProperty("os.name");
		if (operationSystem == "windows") {
			pathToProperties = SMTP_PROPERTIES_WIN;
		} else {
			pathToProperties = SMTP_PROPERTIES_MAC;
		}
		try {
			props.load(new FileInputStream(new File(pathToProperties)));
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.yandex.com", username, password);
			inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);
			messagesAmount = inbox.getMessageCount();
			System.out.println("Incoming messages: " + messagesAmount + "\n" + "--------------------------------");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return messagesAmount;
	}

}
