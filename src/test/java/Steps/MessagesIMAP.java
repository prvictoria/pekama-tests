package Steps;

import Page.TestsCredentials;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import static Page.UrlConfig.setEnvironment;
import static Steps.Messages.EMAIL_SUBJECT_CONFIRM_REGISTRATION;
import static Steps.Messages.EMAIL_SUBJECT_CONGRATULATION_CASE_CREATED;
import static Steps.Messages.rootLogger;
import static Steps.MessagesValidator.ValidationSignUp.userEmail;
import static Utils.Utils.formatDateToString;
import static com.codeborne.selenide.Selenide.sleep;
import static javax.mail.Message.RecipientType.*;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class MessagesIMAP {
    static String login = null;
    static String password = null;
    static String subject = null;
    static String emailFrom = null;
    static String teamName = null;
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
    public void searchEmailByAddress(String userName, String password, final String keyword) {
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
            SearchTerm searchCondition = searchTermAddress(keyword);

            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);

            for (int i = 0; i < foundMessages.length; i++) {
                Message message = emailDetails (foundMessages, i);
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
    public Boolean searchEmailBySubject(String userName, String password, final String keyword) {
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
            SearchTerm searchCondition = searchTermSubject(keyword);

            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);
            if(foundMessages.length>0) {
                for (int i = 0; i < foundMessages.length; i++) {
                    Message message = emailDetails(foundMessages, i);
                }
                return true;
            }
            // disconnect
            folderInbox.close(false);
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
        return false;
    }

    public boolean searchEmailBySubjectAndValidate(String userName, String password, final String keyword, MessagesValidator validator) {
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Session session = Session.getDefaultInstance(properties);
        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
            SearchTerm searchCondition = searchTermSubject(keyword);

            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);
            if (foundMessages.length<1){Assert.fail("No Mails in inbox");}
            for (int i = 0; i < foundMessages.length; i++) {
                //Message message = emailDetails (foundMessages, i);
                String html = emailHtmlPart(foundMessages[i]).toString();
                //System.out.println(html);
                if(validator.validationEmail(html)==true) {
                    deleteDetectedEmailBySubject(keyword, foundMessages[i]);
                }
                else {Assert.fail("Mail validation failed");}
            }
            // disconnect
            folderInbox.close(true);
            store.close();
            return true;
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String searchEmailBySubjectAndValidate(String userName, String password, final String keyword, MessagesValidator validator, Integer index) {
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Session session = Session.getDefaultInstance(properties);
        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
            SearchTerm searchCondition = searchTermSubject(keyword);

            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);
            if (foundMessages.length < 1) {
                Assert.fail("No Mails in inbox");
            }
            String link = null;
            for (int i = 0; i < foundMessages.length; i++) {
                //Message message = emailDetails (foundMessages, i);
                String html = emailHtmlPart(foundMessages[i]).toString();
                //System.out.println(html);
                if (validator.validationEmail(html) == true) {
                    link = validator.validateLink(html, index);
                    deleteDetectedEmailBySubject(keyword, foundMessages[i]);
                } else {
                    Assert.fail("Mail validation failed");
                }
            }
            // disconnect
            folderInbox.close(true);
            store.close();
            return link;
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void searchEmailDeleteAll(String userName, String password) {
        System.out.println("---------------------------------");
        System.out.println("Checked email " +userName);
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);

            // performs search through the folder
            Message foundMessagesUnread[] = folderInbox.search(new FlagTerm(new Flags(
                    Flags.Flag.SEEN), false));
            System.out.println("No. of Unread Messages : " + foundMessagesUnread.length);
            for (int i = 0; i < foundMessagesUnread.length; i++) {
                Message message = foundMessagesUnread[i];
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + message.getSubject());
            }
            Message foundMessagesRead[] = folderInbox.search(new FlagTerm(new Flags(
                    Flags.Flag.SEEN), true));
            System.out.println("No. of already read Messages : " + foundMessagesRead.length);
            for (int i = 0; i < foundMessagesRead.length; i++) {
                Message message = foundMessagesRead[i];
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + message.getSubject());
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
        }
    }
    private static SearchTerm searchTermSubject(final String keyword) {
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
    private static SearchTerm searchTermAddress(final String keyword) {
        // creates a search criterion
        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    Address[] froms = message.getFrom();
                    String email = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
                    //System.out.println(email);
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
    private static void deleteDetectedEmailBySenderEmail(String keyword, Message message) throws MessagingException {
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
        BodyPart bp = null;
        if (content instanceof Multipart) {
            Multipart mp = (Multipart) content;
            for (int i = 0; i < mp.getCount(); i++) {
                bp = mp.getBodyPart(i);
                if (Pattern
                        .compile(Pattern.quote("text/html"),
                                Pattern.CASE_INSENSITIVE)
                        .matcher(bp.getContentType()).find()) {
                    // found html part
                    //System.out.println((String) bp.getContent());
                    //System.out.println("--------------------------------");
                } else {
                    // some other bodypart...
                }
            }
        }
        return (String) bp.getContent();
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
        properties.setProperty("mail.imap.connectiontimeout", String.valueOf(30000));
        return properties;
    }
    public static boolean setReadFlag(Message message) throws MessagingException {
        message.setFlag(Flags.Flag.SEEN, true);
        return true;
    }
    public static String parseHtml(String html){
        Document doc = Jsoup.parse(html);
        Element link = doc.select("a[href]").first();
        System.out.println(link);
        System.out.println("--------------------------------");

        String text = doc.body().text(); // "An example link"
        String linkHref = link.attr("href"); // "http://example.com/"
        System.out.println("Link attribute "+linkHref);
        System.out.println("--------------------------------");
        String linkText = link.text(); // "example""
        System.out.println(linkText);
        System.out.println("--------------------------------");

        String linkOuterH = link.outerHtml();
        System.out.println(linkOuterH);
        System.out.println("--------------------------------");
        // "<a href="http://example.com"><b>example</b></a>"
        String linkInnerH = link.html(); // "<b>example</b>"
        System.out.println(linkInnerH);
        System.out.println("--------------------------------");
        return linkHref;
    }
    public static String parseHtmlHref(String html){
        Document doc = Jsoup.parse(html);
        Element link = doc.select("a[href]").first();
        String linkHref = link.attr("href"); // "http://example.com/"
        System.out.println("Link attribute "+linkHref);
        System.out.println("--------------------------------");
        return linkHref;
    }
    public static Elements parseHtmlHrefArray(String html){
        Document doc = Jsoup.parse(html);
        Elements link = doc.getElementsByAttribute("href");
        Integer size = link.size();
        Integer i = 0;
//        while (size>0) {
//            System.out.println("Link attribute " + link.get(i).attr("href"));
//            System.out.println("--------------------------------");
//        size--;
//        i++;
//        }
        return link;
    }
    public static String getLink (Elements links, Integer index){
        String link = links.get(index).attr("href");
        rootLogger.info("Link #"+index+": "+link);
        rootLogger.info("-------------------------------------");
        return link;
    }
    public static String parseHtmlLinkText(String html){
        Document doc = Jsoup.parse(html);
        Element link = doc.select("a[href]").first();
        String linkText = link.text(); // "example""
        System.out.println(linkText);
        System.out.println("--------------------------------");
        return linkText;
    }
    public static boolean detectEmailIMAP(String login, String password, String keyword){
        Boolean searchResult = false;
        Integer i = 0;
        MessagesIMAP searcher = new MessagesIMAP();
        while (searchResult!=true && i<10) {
            searchResult = searcher.searchEmailBySubject(login, password, keyword);
            if(searchResult==true){
                rootLogger.info("Email with subject '"+keyword+"' detected");
                return true;}
            sleep(5000);
            i++;
            rootLogger.info("Loop # "+i);
        }
        if(searchResult!=true){
            Assert.fail("Email not detected in Inbox");
            return false;
        }
        return false;
    }
//test app
    public static void main(String[] args) {
        login = "";
        password = "";
        setEnvironment();
        //String subjectToDelete = "Confirm Registration [Pekama]";
        //String subjectToDelete = "no-reply@accounts.google.com";
        String subjectToDelete = "dan@pekama.com";
        //String subjectToDelete = "noreply@emstaging.pekama.com";
        //String subjectToDelete = "We are waiting for you!";
        //String subject = "Pekama Report \"Last week's Events\"";
        String keyword = EMAIL_SUBJECT_CONGRATULATION_CASE_CREATED;


        detectEmailIMAP(login, password, keyword);
        MessagesIMAP searcher = new MessagesIMAP();
        searcher.searchEmailBySubjectAndValidate(login, password, keyword, new MessagesValidator.ValidationCongratulationCaseCreated());
    }
    public boolean validateEmailSignUp(String login, String password){
        MessagesValidator.ValidationSignUp.userEmail = login;
        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_CONFIRM_REGISTRATION);
        Assert.assertTrue(detectResult);
        MessagesIMAP searcher = new MessagesIMAP();
        Boolean validationResult = searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_CONFIRM_REGISTRATION,
                new MessagesValidator.ValidationSignUp());
        Assert.assertTrue(validationResult==true);
        return true;
    }
    public boolean validateEmailResetPassword(){

        return true;
    }
    public boolean validateEmailCongratulation(String login, String password, String teamName){
        MessagesValidator.ValidationCongratulationCaseCreated.userEmail = login;
        MessagesValidator.ValidationCongratulationCaseCreated.teamName = teamName;
        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_CONGRATULATION_CASE_CREATED);
        MessagesIMAP searcher = new MessagesIMAP();
        Assert.assertTrue(detectResult);
        searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_CONGRATULATION_CASE_CREATED,
                new MessagesValidator.ValidationCongratulationCaseCreated());
        return true;
    }
    public boolean validateEmailInviteInTeam(){

        return true;
    }
    public boolean validateEmailInviteInProject(){

        return true;
    }
    public boolean validateEmailInviteInCommunity(){

        return true;
    }
    public boolean validateEmailInviteInPekama(){

        return true;
    }
    public boolean validateEmailReport(){

        return true;
    }
    @Test
    public void clearAllEmails(){
        MessagesIMAP emailTask = new MessagesIMAP();
        emailTask.searchEmailDeleteAll(
                TestsCredentials.User1.GMAIL_EMAIL.getValue(),
                TestsCredentials.User1.GMAIL_PASSWORD.getValue());
        emailTask.searchEmailDeleteAll(
                TestsCredentials.User2.GMAIL_EMAIL.getValue(),
                TestsCredentials.User2.GMAIL_PASSWORD.getValue());
        emailTask.searchEmailDeleteAll(
                TestsCredentials.User3.GMAIL_EMAIL.getValue(),
                TestsCredentials.User3.GMAIL_PASSWORD.getValue());
        emailTask.searchEmailDeleteAll(
                TestsCredentials.User4.GMAIL_EMAIL.getValue(),
                TestsCredentials.User4.GMAIL_PASSWORD.getValue());
        emailTask.searchEmailDeleteAll(
                TestsCredentials.User5.GMAIL_EMAIL.getValue(),
                TestsCredentials.User5.GMAIL_PASSWORD.getValue());
    }
}


