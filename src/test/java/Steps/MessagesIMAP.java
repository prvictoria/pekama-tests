package Steps;
import Steps.Intrefaces.IMessagesValidator;
import Steps.Intrefaces.IMessagesValidator.ValidationNotificationCaseConfirmed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
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

import static Pages.DataCredentials.*;
import static Steps.Messages.*;
import static Steps.Messages.EMAIL_SUBJECT_YOU_INVITED_IN_COMMUNITY;
import static Steps.Intrefaces.IMessagesValidator.*;
import static Steps.Intrefaces.IMessagesValidator.ValidationEmailMessage.replyLink;
import static Utils.Utils.formatDateToString;
import static com.codeborne.selenide.Selenide.getElement;
import static com.codeborne.selenide.Selenide.sleep;
import static javax.mail.Flags.*;
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
    static final Logger rootLogger = LogManager.getRootLogger();
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
     /**
     * @param host
     * @param port
     */

    //IMAP STEPS
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
    private Store store (Properties properties, final String userName, final String password){
        System.out.println("---------------------------------");
        System.out.println("Checked email is: " +userName);
        Session session = Session.getDefaultInstance(properties);
        Store store = null;
        Integer loop = 0;
        Boolean connect = false;
        while (connect==false && loop<5) {
            try {
                loop++;
                rootLogger.info("Connects to the message store. Loop #: "+loop);
                store = session.getStore("imap");
                store.connect(userName, password);
                connect = store.isConnected();
                if (connect==true){
                    rootLogger.info("Connect store present");
                    return store;
                }
                Thread.sleep(4000);
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return store;
    }
    private Folder folder (Store store) throws MessagingException {
        rootLogger.info("Opens the inbox folder");
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }
    private void clearFolder(Folder folder) throws MessagingException {
        rootLogger.info("Remove emails marked for deletion");
        folder.close(true);
    }
    private void closeStore(Store store) throws MessagingException {
        rootLogger.info("Disconnect");
        store.close();
    }

    //ACTION IN EMAIL STEPS
    private void markEmailsForDeletion(Boolean messageIsRead, Folder folder) throws MessagingException {
        rootLogger.info("performs search through the folder"+folder.toString());
        Message foundMessagesUnread[] = folder.search(new FlagTerm(new Flags(
                Flag.SEEN), messageIsRead));
        System.out.println("No. of Messages: " + foundMessagesUnread.length);
        for (int i = 0; i < foundMessagesUnread.length; i++) {
            Message message = foundMessagesUnread[i];
            message.setFlag(Flag.DELETED, true);
            System.out.println("Marked DELETE for message: " + message.getSubject());
        }
    }
    private Boolean imapDetectEmail(Folder folder, SearchTerm searchCondition) throws MessagingException, IOException {
        Boolean result = false;
        int loop = 0;
        while (result==false && loop<10) {
            Message[] foundMessages = folder.search(searchCondition);
            if (foundMessages.length > 0) {
                for (int i = 0; i < foundMessages.length; i++) {
                    emailDetails(foundMessages, i);
                }
                rootLogger.info("Email detected");
                return true;
            }
            else {
                result = false;
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loop++;
                rootLogger.info("Loop # "+loop);
            }
        }
        return false;
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
            rootLogger.info("Email not detected in Inbox");
            return false;
        }
        return false;
    }
    private static void deleteDetectedEmailBySubject(String subjectToDelete, Message message) throws MessagingException {
        String subject = message.getSubject();
        if (subjectToDelete!=null) {
            if (subject.contains(subjectToDelete)) {
                message.setFlag(Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + subject);
                return;
            }
            else {rootLogger.info("Email with defined subject not detected");}
        }
    }
    private static Object emailHtmlPart(Message message) throws IOException, MessagingException {
        String html = null;
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
                    System.out.println(bp.getContentType());
                    //System.out.println((String) bp.getContent());
                    System.out.println("--------------------------------");
                    html = (String) bp.getContent();
                } else {
                    // some other bodypart...
                }
            }
        }
        return html;
    }
    private static Object emailMixedCSVPart(Message message) throws IOException, MessagingException {
        Object content = message.getContent();
        BodyPart bp = null;
        if (content instanceof Multipart) {
            Multipart mp = (Multipart) content;
            for (int i = 0; i < mp.getCount(); i++) {
                bp = mp.getBodyPart(i);
                if (Pattern
                        .compile(Pattern.quote("text/csv"),
                                Pattern.CASE_INSENSITIVE)
                        .matcher(bp.getContentType()).find()) {
                    //found html part
                    System.out.println(bp.getContent());
                    System.out.println("--------------------------------");
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
    private static boolean setReadFlag(Message message) throws MessagingException {
        message.setFlag(Flag.SEEN, true);
        return true;
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
        System.out.println(searchCondition);
        return searchCondition;
    }
    private Message[] messages(Folder folder, SearchTerm searchCondition) throws MessagingException {
        Message[] foundMessages = folder.search(searchCondition);
        if (foundMessages.length < 1) {
            Assert.fail("No Mails in inbox");
        }
        if (foundMessages.length > 1) {
            rootLogger.info("More that 1 Mails detected. Qty = "+foundMessages.length);
        }
        return foundMessages;
    }
    private String parseHtml(Message[] messages) throws IOException, MessagingException {
        String html = emailHtmlPart(messages[0]).toString();
        //System.out.println(html);
        return html;
    }
    private String parseHtml(Message message) throws IOException, MessagingException {
        String html = emailHtmlPart(message).toString();
        //System.out.println(html);
        return html;
    }
    private void imapCloseConnectAndClearFolder(){

    }


    //EMAIL PARSE STEPS
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
        //printAllLInks(link);
        return link;
    }
    private static void printAllLInks(Elements link){
        Integer size = link.size();
        Integer i = 0;
        while (size>0) {
            System.out.println("Link attribute " + link.get(i).attr("href"));
            System.out.println("--------------------------------");
            size--;
            i++;
        }
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
        rootLogger.info(linkText);
        rootLogger.debug("--------------------------------");
        return linkText;
    }
    public static Document document(String html){
        Document doc = Jsoup.parse(html);
        return doc;
    }
    public static Document parseCleanHtml(Document doc) throws IOException {
        // Load and parse the document fragment.
//        File f = new File("myfile.html"); // See also Jsoup#parseBodyFragment(s)
//        Document doc = Jsoup.parse(f, "UTF-8", "http://example.com");

        // Remove all script and style elements and those of class "hidden".
        doc.select("script, style, .hidden").remove();
        rootLogger.info(doc);
        // Remove all style and event-handler attributes from all elements.
        Elements all = doc.select("*");
        for (Element el : all) {
            for (Attribute attr : el.attributes()) {
                String attrKey = attr.getKey();
                if (attrKey.equals("style") || attrKey.startsWith("on")) {
                    el.removeAttr(attrKey);
                }
            }
        }
        rootLogger.info(doc);
        // See also - doc.select("*").removeAttr("style");
        return doc;
    }
    public static String parsedEmailToText(String html){
        Document doc = Jsoup.parse(html);
        rootLogger.debug(doc);
        String text = doc.text();
        rootLogger.info(text);
        return text;
    }
    public static String getHtmlElementByTag(Document document, String tagName, int index){
        String element = document.getElementsByTag(tagName).get(index).html();
        return element;
    }
    //END TO END IMAP FLOW - need refactor

    public Boolean searchEmailBySubject(String userName, String password, final String keyword) {
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Session session = Session.getDefaultInstance(properties);
        try {
            // connects to the message store
            Store store = store(properties, userName, password);

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
    public boolean searchEmailBySubjectAndValidate(String userName, String password, final String keyword, IMessagesValidator validator) {
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Session session = Session.getDefaultInstance(properties);
        try {
            // connects to the message store
            Store store = store(properties, userName, password);

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
                //String html = emailMixedPart(foundMessages[i]).toString();
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


    //NEW Logic
    public Message getEmail(String userName, String password, final String keyword) throws MessagingException, IOException {
        String html = null;
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Store store = store(properties, userName, password);
        Folder folderInbox = folder (store);
        SearchTerm searchCondition = searchTermSubject(keyword);
        Boolean result = imapDetectEmail(folderInbox, searchCondition);
        if(result==true){
            Message[] messages = messages(folderInbox, searchCondition);
            Message message = messages[0];
            return message;
        }
        if (result==false) {
            clearFolder(folderInbox);
            closeStore(store);
        }
        return null;
    }
    public void imapSearchEmailDeleteAll(String userName, String password) throws MessagingException {
        Properties properties = setProperties (IMAP_HOST, IMAP_PORT);
        Store store = store(properties, userName, password);
        Folder folderInbox = folder (store);
        markEmailsForDeletion(true, folderInbox);
        markEmailsForDeletion(false, folderInbox);
        clearFolder(folderInbox);
        closeStore(store);
    }

    //FINAL VALIDATOR EMAILS
    public boolean validateEmailInviteInTeamUnregistered(String login, String password, String inviterNameSurname, String inviterName, String inviterFullTeamName){
        ValidationInviteInTeamUnregistered.userEmail = login;
        ValidationInviteInTeamUnregistered.inviterNameSurname = inviterNameSurname;
        ValidationInviteInTeamUnregistered.inviterName = inviterName;
        ValidationInviteInTeamUnregistered.inviterFullTeamName = inviterFullTeamName;
        rootLogger.info("Search this subject: "+EMAIL_SUBJECT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName));
        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName));
        Assert.assertTrue(detectResult);

        MessagesIMAP searcher = new MessagesIMAP();
        Boolean validationResult = searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName),
                new ValidationInviteInTeamUnregistered());
        return validationResult;
    }

    public boolean validateEmailInviteInTeamRegistered(String login, String password, String inviterNameSurname, String inviterName, String inviterFullTeamName){
        ValidationInviteInTeamRegistered.userEmail = login;
        ValidationInviteInTeamRegistered.inviterNameSurname = inviterNameSurname;
        ValidationInviteInTeamRegistered.inviterName = inviterName;
        ValidationInviteInTeamRegistered.inviterFullTeamName = inviterFullTeamName;

        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName));
        Assert.assertTrue(detectResult);
        MessagesIMAP searcher = new MessagesIMAP();
        Boolean validationResult = searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName),
                new ValidationInviteInTeamRegistered());
        return validationResult;
    }

   public boolean validateEmailMessage(String email, String password, String keyword, String text, String userNameSurname, String followerEmailOrTeamNameSurname, IMessagesValidator validator) throws IOException, MessagingException {
        ValidationEmailMessage.userNameSurname = userNameSurname;
        ValidationEmailMessage.followerEmailOrTeamNameSurname = followerEmailOrTeamNameSurname;
        String html;
        Boolean result;
        MessagesIMAP emailTask = new MessagesIMAP();
        Message message = emailTask.getEmail(
                email,
                password,
                keyword);
        Assert.assertNotNull(message);
        html = parseHtml(message);
        result = validator.validationEmail(html, text);
        replyLink = validator.validateLink(html, 0);
        emailTask.imapSearchEmailDeleteAll(email, password);
        if (result==true){
        return true;
        }
        else return false;
    }

}


