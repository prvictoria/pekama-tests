package draft;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import java.util.Properties;

import static draft.MailingClass.user;

public class testemail {
    java.lang.Comparable c[];
    Properties properties = null;
    private Session session = null;
    private Store store = null;
    private Folder inbox = null;
    private String userName = user;  //
    private String password = MailingClass.password;


    public testemail() {

    }

    public void readMails() throws Exception {
        properties = new Properties();
        properties.setProperty("mail.host", "imap.gmail.com");
        properties.setProperty("mail.port", "995");
        properties.setProperty("mail.transport.protocol", "imaps");
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });


        store = session.getStore("imaps");
        store.connect();
        inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        //Message messages[] = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        Message messages[] = inbox.getMessages();
        System.out.println("Number of mails = " + messages.length);


        for (Message message : messages) {
            System.out.println("Subject: " + message.getSubject());
            if (message.getSubject().toString() == "Suppliers that match your search: suto in future mode") {
                Address[] from = message.getFrom();
                System.out.println("-------------------------------");
                System.out.println("Date : " + message.getSentDate());
                System.out.println("From : " + from[0]);
                System.out.println("Subject: " + message.getSubject());


                Object content = message.getContent();
                System.out.println("Content :" +content);
                System.out.println("--------------------------------");
                Multipart multiPart = (Multipart) content;
                //procesMultiPart(multiPart);
                System.out.println("Multipart :" +multiPart);
                System.out.println("--------------------------------");
            } else {
                System.out.println("not found");
            }
        }
        inbox.close(true);
        store.close();

    }
    @Test
    public void test() throws Exception {
        readMails();
    }
}