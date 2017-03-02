package com.pekama.app.draft;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.slf4j.Logger;

public class ReadingEmails {

	private static final String SMTP_PROPERTIES = "src/test/resources/smtp.properties";
	private Logger logger;

	private String getText(Part p) throws MessagingException, IOException {
		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			p.isMimeType("text/html");
			return s.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "\n");
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null)
						return s.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "\n");
				} else {
					return getText(bp);
				}
			}
			return text.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "\n");
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null)
					return s.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "\n");
			}
		}

		return null;
	}

	public List<String> readEmail() {
		Properties props = new Properties();
		List<String> receivedEmailData = null;
		try {
			props.load(new FileInputStream(new File(SMTP_PROPERTIES)));
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("smtp.gmail.com", "some_email_address", "some_password");
			Folder inbox = store.getFolder("inbox");
			inbox.open(Folder.READ_WRITE);
			int messagesAmount = inbox.getNewMessageCount();
			System.out.println("Messages: " + messagesAmount + "\n" + "--------------------------------");
				Message[] message = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
			for (int i = message.length - 1; i < message.length; i++) {
				Message msg = message[i];
				String date = msg.getReceivedDate().toString();
				String subject = msg.getSubject();
				String messageBodyContent = getText(msg);
				receivedEmailData = new ArrayList<String>();
				receivedEmailData.add(0, date);
				receivedEmailData.add(1, subject);
				receivedEmailData.add(2, messageBodyContent);

			}
			inbox.close(true);
			store.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return receivedEmailData;
	}

}
