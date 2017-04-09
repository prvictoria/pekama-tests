package draft;

import org.junit.Test;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import static Utils.Utils.formatDateToString;
import static Utils.Utils.getCurrentDate;


public class EmailSearcher {
    static String login = "testqweeco005@gmail.com";
    static String password = "123456789qasw11";
    static String subject = "Pekama Report \"Last week's Events\"";
    static String emailFrom = "noreply@emstaging.pekama.com";
    static String host = "imap.gmail.com";
    static String port = "993";

    /**
     * Searches for e-mail messages containing the specified keyword in
     * Subject field.
     *
     * @param host
     * @param port
     * @param userName
     * @param password
     * @param keyword
     */

    public void searchEmail(String host, String port, String userName,
                            String password, final String keyword, String subjectToDelete) {
        Properties properties = new Properties();

        // server setting
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", port);

        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port",
                String.valueOf(port));

        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);

            // creates a search criterion
            SearchTerm searchCondition = new SearchTerm() {
                @Override
                public boolean match(Message message) {
                    try {
                        if (message.getSubject().contains(keyword)) {
                            return true;
                        }
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
            };

            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);

            for (int i = 0; i < foundMessages.length; i++) {
                Message message = foundMessages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Found message #" + i + ": " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Send date message #" + i + ": " + formatDateToString(message.getSentDate()));
                System.out.println("Text: " + message.getContent().toString());

                //deleteDetectedEmailBySubject(subjectToDelete, message);
            }

            // disconnect
            folderInbox.close(true);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test this program with a Gmail's account
     */
    public static void searchTerm(final String keyword) {
        // creates a search criterion
        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject().contains(keyword)) {
                        return true;
                    }
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
    }
    public static void deleteDetectedEmailBySubject(String subjectToDelete, Message message) throws MessagingException {
        if (subjectToDelete!=null) {
            if (subject.contains(subjectToDelete)) {
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + subject);
            }
        }
    }
    public static void deleteDetectedEmailBySenderEmail(String fromAddress, Message message) throws MessagingException {
        if (fromAddress !=null) {
            Address messageFrom = message.getFrom()[0];

            if (messageFrom.equals(fromAddress)) {
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + subject);
            }
        }
    }
    public static void main(String[] args) {
        //String subjectToDelete = "Confirm Registration [Pekama]";
        String subjectToDelete = subject;
        String host = "imap.gmail.com";
        String port = "993";
        EmailSearcher searcher = new EmailSearcher();
        String keyword = subject;
        searcher.searchEmail(host, port, login, password, keyword, subjectToDelete);
        }

    //TODO DELETE TEST
    @Test
    public void deleteEmailByAddressFrom(){

    }
}


