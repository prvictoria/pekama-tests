package draft;

import org.junit.Assert;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import static Utils.Utils.formatDateToString;
import static javax.mail.Message.RecipientType.*;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class EmailSearcher {
    static String login = "testqweeco005@gmail.com";
    static String password = "123456789qasw11";
    static String subject = "Pekama Report \"Last week's Events\"";
    static String emailFrom = "noreply@emstaging.pekama.com";
    public static final String IMAP_HOST = "imap.gmail.com";
    public static final String IMAP_PORT = "993";

    /**
     * Searches for e-mail messages containing the specified keyword in
     * Subject field.
     *
     * @param userName
     * @param password
     * @param keyword
     */

    public void searchEmail(

            String userName, String password,
            final String keyword, String subjectToDelete) {
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);


            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
            SearchTerm searchCondition = searchTermFromAddress(keyword);

            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);

            for (int i = 0; i < foundMessages.length; i++) {
                Message message = emailDetails (foundMessages, i);
               // emailHtmlPart(message);
               // emailPlainTextPart(message);
                deleteDetectedEmailBySenderEmail(keyword, message);

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
    private static SearchTerm searchTerm(final String keyword) {
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
        return searchCondition;
    }
    private static SearchTerm searchTermFromAddress(final String keyword) {
        // creates a search criterion
        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    Address[] froms = message.getFrom();
                    String email = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
                    System.out.println(email);
                    if (email.equals(keyword)) {
                        return true;
                    }
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
        return searchCondition;
    }
    private static void deleteDetectedEmailBySubject(String subjectToDelete, Message message) throws MessagingException {
        String subject = message.getSubject();
        if (subjectToDelete!=null) {
            if (subject.equals(subjectToDelete)) {
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + subject);
            }
        }
    }
    public static void deleteDetectedEmailBySenderEmail(String keyword, Message message) throws MessagingException {
        if (keyword != null) {
            Address[] froms = message.getFrom();
            String email = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
            System.out.println(email);
            if (email.equals(keyword)) {
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + message.getSubject());
            }
        }
    }

    private static Object emailHtmlPart(Message message) throws IOException, MessagingException {
        Object content = message.getContent();
        if (content instanceof Multipart) {
            Multipart mp = (Multipart) content;
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart bp = mp.getBodyPart(i);
                if (Pattern
                        .compile(Pattern.quote("text/html"),
                                Pattern.CASE_INSENSITIVE)
                        .matcher(bp.getContentType()).find()) {
                    // found html part
                    System.out.println((String) bp.getContent());
                } else {
                    // some other bodypart...
                }
            }
        }
        return content;
    }
    private static Object emailPlainTextPart(Message message) throws IOException, MessagingException {
        Object content = message.getContent();
        if (content instanceof Multipart) {
            Multipart mp = (Multipart) content;
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart bp = mp.getBodyPart(i);
                if (Pattern
                        .compile(Pattern.quote("text/plain"),
                                Pattern.CASE_INSENSITIVE)
                        .matcher(bp.getContentType()).find()) {
                    // found text part
                    System.out.println((String) bp.getContent());
                    System.out.println("--------------------------------");
                } else {
                    // some other bodypart...
                }
            }
        }
        return content;
    }
    private static Message emailDetails (Message[] foundMessages, Integer i) throws MessagingException, IOException {
        Message message = foundMessages[i];
        System.out.println("---------------------------------");
        System.out.println("Email Number " + (i + 1));
        System.out.println("Found message #" + i + ": " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("TO: " + message.getRecipients(TO)[0]);
        try{
            System.out.println("CC: " + message.getRecipients(CC)[0]);
        }
        catch (java.lang.NullPointerException e){
            System.out.println("CC: " + "No CC in email");
        }
        System.out.println("Send date message #" + i + ": " + formatDateToString(message.getSentDate()));
        System.out.println("Text: " + message.getContent().toString());
        return message;
    }
    private Properties setProperties (String host, String port){
        Properties properties = new Properties();

        // server setting
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", port);

        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));
        // Timeout
        properties.setProperty("mail.imap.connectiontimeout", String.valueOf(10000));
        return properties;
    }
    //TODO DELETE TEST
    public static void main(String[] args) {
        //String subjectToDelete = "Confirm Registration [Pekama]";
        //String subjectToDelete = "no-reply@accounts.google.com";
        String subjectToDelete = "dan@pekama.com";
        //String subjectToDelete = "noreply@emstaging.pekama.com";
        //String subjectToDelete = "We are waiting for you!";
        //String subject = "Pekama Report \"Last week's Events\"";
        EmailSearcher searcher = new EmailSearcher();
        String keyword = subjectToDelete;
        searcher.searchEmail(login, password, keyword, subjectToDelete);
    }
    public static void deleteAllPekamaEmails(){
        
    }
    @Test
    public void deleteEmailByAddressFrom(){

    }
}


