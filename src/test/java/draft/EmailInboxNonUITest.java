package draft;

import Page.TestsCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

//import utils.EmailInboxChecker;

public class EmailInboxNonUITest {

	private static final String USERNAME = "hist45@tut.by";
	private static final String PASSWORD = "2271941";
    static final Logger rootLogger = LogManager.getRootLogger();

	@Test
	public void checkIncomingMessages() {
		rootLogger.info("Test " + EmailInboxNonUITest.class + " started");
		int incomingEmailsAmount = draft.EmailInboxChecker.checkEmailsAmount(USERNAME, PASSWORD);
		Assert.assertNotNull(incomingEmailsAmount);
		rootLogger.info("Test " + EmailInboxNonUITest.class + " ended \n" + "------------------------------------");

	}

    public static void check(String host, String storeType, String user,
                             String password)
    {
        try {

            //create properties field
            Properties properties = new Properties();
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());

            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = TestsCredentials.User5.GMAIL_EMAIL.getValue();// change accordingly
        String password = TestsCredentials.User5.GMAIL_PASSWORD.getValue();// change accordingly

        check(host, mailStoreType, username, password);

    }
    @Test
    public void test1() {
        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = TestsCredentials.User5.GMAIL_EMAIL.getValue();// change accordingly
        String password = TestsCredentials.User5.GMAIL_PASSWORD.getValue();// change accordingly

        check(host, mailStoreType, username, password);
}

}
