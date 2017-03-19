package draft;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.*;
import javax.mail.search.SearchTerm;

public class GmailInbox {
    static String login = "testqweeco005@gmail.com";
    static String password = "123456789qasw11";
    static String subject = "Confirm Registration [Pekama]";

    public static void main(String[] args) {
        GmailInbox gmail = new GmailInbox();
        gmail.read(login, password);

    }

    public void read(String login, String password) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("D:\\PRJ\\pekama-tests\\src\\lib\\smtpGmail.properties")));
            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", login, password);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();

            System.out.println("Total Messages:- " + messageCount);

            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");
            for (int i = 0; i < 20; i++) {
                System.out.println("Mail Subject:- " + messages[i].getSubject());
                checkSubject(subject);

            }

            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkSubject(final String subject) {

    SearchTerm term = new SearchTerm() {
        public boolean match(Message message) {
            try {
                if (message.getSubject().contains(subject)) {
                    System.out.println("Mail Subject:- " +subject+" present");
                    return true;
                }
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
            System.out.println("Mail Subject:- " +subject+" NOT present");
            return false;
        }
    };
}

}
