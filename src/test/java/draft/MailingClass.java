package draft;

/**
 * Created by Viachaslau_Balashevi on 3/8/2017.
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailingClass {
    public static String user = "testqweeco005@gmail.com";
    public static String password = "123456789qasw11";
    private final Properties mailProperties = new Properties();
    private final String imapHost;
    private final int imapPort;

    public MailingClass(String smtpHost, int smtpPort,
                        String imapHost, int imapPort,
                        String user, String password) {
        this.imapHost = imapHost;
        this.imapPort = imapPort;
        mailProperties.setProperty("mail.smtp.host", smtpHost);
        mailProperties.setProperty("mail.smtp.port", Integer.toString(smtpPort));
        mailProperties.setProperty("mail.user", user);
        mailProperties.setProperty("mail.password", password);
        mailProperties.setProperty("mail.store.protocol", "imap");
    }

    public void sendMail(String to, String from, String subject,
                         String content) throws MessagingException {
        Session session = Session.getDefaultInstance(mailProperties);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }

    public Message[] receiveMail(String user, String password)
            throws MessagingException {
        Session session = Session.getDefaultInstance(mailProperties);

        Store store = session.getStore("imap");
        store.connect(imapHost, imapPort, user, password);
        Folder inbox = store.getFolder("INBOX");

        inbox.open(Folder.READ_ONLY);

        return inbox.getMessages();
    }

    @Test
    public void test() throws MessagingException {
        receiveMail(user, password);
    }
}
