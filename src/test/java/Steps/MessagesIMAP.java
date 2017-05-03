package Steps;
import Steps.MessagesValidator.ValidationNotificationCaseConfirmed;
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
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import static Page.TestsCredentials.*;
import static Steps.Messages.*;
import static Steps.Messages.EMAIL_SUBJECT_YOU_INVITED_IN_COMMUNITY;
import static Steps.MessagesValidator.*;
import static Steps.MessagesValidator.ValidationEmailMessage.followerEmailOrTeamNameSurname;
import static Steps.MessagesValidator.ValidationEmailMessage.replyLink;
import static Steps.MessagesValidator.ValidationInviteInProject.projectBackLink;
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
    private static boolean searchTermSubjectLoop(final String keyword) {
        SearchTerm searchResult = null;
        Integer i = 0;
        while (searchResult==null && i<10) {
            searchResult = searchTermSubject(keyword);
            if(searchResult!=null){
                rootLogger.info("Email with subject '"+keyword+"' detected");
                return true;}
            sleep(5000);
            i++;
            rootLogger.info("Loop # "+i);
        }
        if(searchResult==null){
            Assert.fail("Email not detected in Inbox");
            return false;
        }
        return false;
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
                    if (email.contains(keyword)) {
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
            if (subject.contains(subjectToDelete)) {
                message.setFlag(Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + subject);
                return;
            }
            else {rootLogger.info("Email with defined subject not detected");}
        }
    }
    private static void deleteDetectedEmailBySenderEmail(String keyword, Message message) throws MessagingException {
        if (keyword != null) {
            Address[] froms = message.getFrom();
            String email = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
            System.out.println(email);
            if (email.equals(keyword)) {
                message.setFlag(Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + message.getSubject());
            }
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
        System.out.println(linkText);
        System.out.println("--------------------------------");
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
        //rootLogger.info(doc);
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
        //rootLogger.info(doc);
        // See also - doc.select("*").removeAttr("style");
        return doc;
    }
    public static String getHtmlElementByTag(Document document, String tagName, int index){
        String element = document.getElementsByTag(tagName).get(index).html();
        return element;
    }
    //END TO END IMAP FLOW - need refactor
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
            if (foundMessages.length > 1) {
                rootLogger.info("More that 1 Mails detected");
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

//            deleteDetectedEmailBySubject(keyword, messages[0]);
//            clearFolder(folderInbox);
//            closeStore(store);
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
    public boolean validateEmailNotificationCaseConfirmed(String login, String password, String expertTeam, String caseName){
        ValidationNotificationCaseConfirmed.userEmail = login;
        ValidationNotificationCaseConfirmed.expertTeam = expertTeam;
        ValidationNotificationCaseConfirmed.caseName = caseName;
        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_NOTIFICATION_INSTRUCTION_CONFIRMED(expertTeam) );
        Assert.assertTrue(detectResult);
        MessagesIMAP searcher = new MessagesIMAP();
        Boolean validationResult = searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_NOTIFICATION_INSTRUCTION_CONFIRMED(expertTeam),
                new ValidationNotificationCaseConfirmed());
        Assert.assertTrue(validationResult==true);
        return true;
    }
    public boolean validateEmailSignUp(String login, String password){
        ValidationSignUp.userEmail = login;
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
                new ValidationSignUp());
        Assert.assertTrue(validationResult==true);
        return true;
    }
    public boolean validateEmailResetPassword(){

        return true;
    }
    public boolean validateEmailCongratulation(String login, String password, String teamName){
        ValidationCongratulationCaseCreated.userEmail = login;
        ValidationCongratulationCaseCreated.teamName = teamName;
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
                new ValidationCongratulationCaseCreated());
        return true;
    }
    public boolean validateEmailCongratulationForInvite(String login, String password, String invitedEmail){
        ValidationCongratulationForInvite.userEmail = login;
        ValidationCongratulationForInvite.invitedEmail = invitedEmail;
        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_CONGRATULATION_FOR_INVITE(invitedEmail));
        MessagesIMAP searcher = new MessagesIMAP();
        Assert.assertTrue(detectResult);
        searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_CONGRATULATION_FOR_INVITE(invitedEmail),
                new ValidationCongratulationForInvite());
        return true;
    }
    public boolean validateEmailInviteInTeamUnregistered(String login, String password, String inviterNameSurname, String inviterName, String inviterFullTeamName){
        ValidationInviteInTeamUnregistered.userEmail = login;
        ValidationInviteInTeamUnregistered.inviterNameSurname = inviterNameSurname;
        ValidationInviteInTeamUnregistered.inviterName = inviterName;
        ValidationInviteInTeamUnregistered.inviterFullTeamName = inviterFullTeamName;

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
    public boolean validateEmailInviteInProject(String login, String password, String inviterNameSurname, String projectName){
        ValidationInviteInProject.inviterNameSurname = inviterNameSurname;
        ValidationInviteInProject.projectName = projectName;
        ValidationInviteInProject.userEmail = login;
        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_YOU_INVITED_IN_PROJECT(inviterNameSurname));
        Assert.assertTrue(detectResult);
        MessagesIMAP searcher = new MessagesIMAP();
        Boolean validationResult = searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_YOU_INVITED_IN_PROJECT(inviterNameSurname),
                new ValidationInviteInProject());
        return validationResult;
    }
    public boolean validateEmailInviteInCommunity(String login, String password, String name_surname, String customText){
        ValidationInviteCommunity.userEmail = login;
        ValidationInviteCommunity.inviterNameSurname = name_surname;
        ValidationInviteCommunity.customText = customText;
        System.out.println(EMAIL_SUBJECT_YOU_INVITED_IN_COMMUNITY(name_surname));
        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_YOU_INVITED_IN_COMMUNITY(name_surname));
        Assert.assertTrue(detectResult);
        MessagesIMAP searcher = new MessagesIMAP();
        Boolean validationResult = searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_YOU_INVITED_IN_COMMUNITY(name_surname),
                new ValidationInviteCommunity());
        Assert.assertTrue(validationResult==true);
        return true;
    }
    public boolean validateEmailInviteInPekama(){

        return true;
    }
    public boolean validateEmailReport(String login, String password, String reportSchedule, String reportName){
        ValidationReport.reportSchedule = reportSchedule;
        System.out.println(EMAIL_SUBJECT_REPORT(reportName));
        Boolean detectResult = detectEmailIMAP(
                login,
                password,
                EMAIL_SUBJECT_REPORT(reportName));
        Assert.assertTrue(detectResult);
        MessagesIMAP searcher = new MessagesIMAP();
        Boolean validationResult = searcher.searchEmailBySubjectAndValidate(
                login,
                password,
                EMAIL_SUBJECT_REPORT(reportName),
                new ValidationReport());
        Assert.assertTrue(validationResult==true);
        return true;
    }
    public boolean validateEmailMessage(String email, String password, String keyword, String text, String userNameSurname, String followerEmailOrTeamNameSurname, MessagesValidator validator) throws IOException, MessagingException {
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
    //IMAP APP
//    String keyword = "Confirm Registration [Pekama]";
//    String keyword = "no-reply@accounts.google.com";
//    String keyword = "dan@pekama.com";
//    String keyword = "noreply@emstaging.pekama.com";
//    String keyword = "We are waiting for you!";
//    String keyword = "Pekama Report \"Last week's Events\"";
//    String keyword = "Ready to confirm completion in Pekama?";
    String keyword = "New sign-in from Firefox on Windows";
    String text = "Why are we sending this?";
    public static void main(String[] args) {
//        login = User5.GMAIL_EMAIL.getValue();
//        password = User5.GMAIL_PASSWORD.getValue();
//        setEnvironment();
//        String name_surname = TestsCredentials.User3.NAME_SURNAME.getValue();
//        String projectName = "new test project - 83B25I";
//        String keyword = EMAIL_SUBJECT_YOU_INVITED_IN_PROJECT(name_surname);
//        rootLogger.info(keyword);
//        detectEmailIMAP(login, password, keyword);
       // MessagesIMAP searcher = new MessagesIMAP();
        //searcher.searchEmailBySubject(login, password, keyword);
        //searcher.searchEmailBySubjectAndValidate(login, password, keyword, new ValidationCongratulationCaseCreated());
        rootLogger.info("Check report email");
        String login = User5.GMAIL_EMAIL.getValue();
        String password = User5.GMAIL_PASSWORD.getValue();
        String inviterNameSurname = User3.NAME_SURNAME.getValue();
        String projectName = "new test project - 83B25I";
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInProject(login, password, inviterNameSurname, projectName);
        Assert.assertTrue(validationResult);
        Assert.assertNotNull(projectBackLink);
        rootLogger.info("Link invite to project is: "+projectBackLink);
        rootLogger.info("Test passed");
    }
   @Test
    public void clearAllEmails() throws MessagingException, IOException {
        MessagesIMAP emailTask = new MessagesIMAP();
//        Assert.assertTrue(
//                emailTask.validateEmailMessage(
//                        User2.GMAIL_EMAIL.getValue(),
//                        User2.GMAIL_PASSWORD.getValue(),
//                        keyword,
//                        text,
//                        new MessagesValidator.ValidationEmailMessage()
//                )
//
//        );
//        emailTask.getEmail(
//               User2.GMAIL_EMAIL.getValue(),
//               User2.GMAIL_PASSWORD.getValue(),
//                keyword);
        emailTask.imapSearchEmailDeleteAll(
                User1.GMAIL_EMAIL.getValue(),
                User1.GMAIL_PASSWORD.getValue());
        emailTask.imapSearchEmailDeleteAll(
                User2.GMAIL_EMAIL.getValue(),
                User2.GMAIL_PASSWORD.getValue());
        emailTask.imapSearchEmailDeleteAll(
                User3.GMAIL_EMAIL.getValue(),
                User3.GMAIL_PASSWORD.getValue());
        emailTask.imapSearchEmailDeleteAll(
                User4.GMAIL_EMAIL.getValue(),
                User4.GMAIL_PASSWORD.getValue());
        emailTask.imapSearchEmailDeleteAll(
                User5.GMAIL_EMAIL.getValue(),
                User5.GMAIL_PASSWORD.getValue());
        emailTask.imapSearchEmailDeleteAll(
                User6.GMAIL_EMAIL.getValue(),
                User6.GMAIL_PASSWORD.getValue());
    }
}


