package Steps.Objects.Emails;


import Steps.ObjectUser;
import com.sun.xml.xsom.impl.scd.Iterators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

import static Steps.ObjectUser.Users.*;
import static Steps.ObjectUser.newBuilder;
import static Utils.Utils.formatDateToString;
import static javax.mail.Flags.*;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

public class ImapService {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private static final String IMAP_HOST = "imap.gmail.com";
    private static final String IMAP_PORT = "993";
    private Properties properties;
    private Store store;
    private Folder folder;
    private SearchTerm searchCondition;
    private boolean isEmailDetected = false;
    private Message message;
    private Message messages[];
    private String messageHtmlPart;

    public String getMessageHtmlPart() {
        return messageHtmlPart;
    }
    public Message getMessage() {
        return message;
    }
    public Message[] getMessages() {
        return messages;
    }

    public ImapService setProperties (){
        this.properties = new Properties();
        // server setting
        properties.put("mail.imap.host", IMAP_HOST);
        properties.put("mail.imap.port", IMAP_PORT);
        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port", String.valueOf(IMAP_PORT));
        // Timeout
        properties.setProperty("mail.imap.connectiontimeout", String.valueOf(30000));
        return this;
    }

    public ImapService connectStore (ObjectUser user) throws MessagingException, InterruptedException {
        rootLogger.info("---------------------------------");
        rootLogger.info("Checked email is: " +user.email);
        Session session = Session.getDefaultInstance(this.properties);

        Integer loop = 0;
        Boolean connect = false;
        while (connect==false && loop<5) {
                loop++;
                rootLogger.info("Connects to the message store. Loop #: "+loop);
                this.store = session.getStore("imap");
                this.store.connect(user.email, user.passwordEmail);
                connect = store.isConnected();
                if (connect==true){
                    rootLogger.info("Connect store present");
                    return this;
                }
                Thread.sleep(4000);
        }
        return this;
    }

    public ImapService openFolder () throws MessagingException {
        rootLogger.info("Opens the inbox folder");
        this.folder = this.store.getFolder("INBOX");
        this.folder.open(Folder.READ_WRITE);
        return this;
    }

    public ImapService clearFolder() throws MessagingException {
        rootLogger.info("Remove emails marked for deletion");
        this.folder.close(true);
        return this;
    }

    public ImapService closeStore() throws MessagingException {
        rootLogger.info("Disconnect");
        rootLogger.info(" ---------------------------------------");
        this.store.close();
        return this;
    }

    public ImapService markEmailsForDeletion() throws MessagingException {
        rootLogger.info("Perform search through the folder"+this.folder.toString());
        this.messages = this.folder.search(new FlagTerm(new Flags(
                Flag.SEEN), true));
        System.out.println("No. of Messages: " + this.messages.length);
        for (int i = 0; i < this.messages.length; i++) {
            Message message = this.messages[i];
            message.setFlag(Flag.DELETED, true);
            System.out.println("Marked DELETE for message: " + message.getSubject());
        }
        this.messages = this.folder.search(new FlagTerm(new Flags(
                Flag.SEEN), false));
        System.out.println("No. of Messages: " + this.messages.length);
        for (int i = 0; i < this.messages.length; i++) {
            Message message = this.messages[i];
            message.setFlag(Flag.DELETED, true);
            System.out.println("Marked DELETE for message: " + message.getSubject());
        }
        return this;
    }
    //SEARCH Email logic
    private ImapService searchConditionEmailBySubject(final ReferenceEmail email) {
        // creates a search criterion
        this.searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject()
                            .contains(email.getAbstractEmail().emailSubject())) {
                        return true;
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return false;
            }
        };
        rootLogger.info(this.searchCondition);
        return this;
    }

    public ImapService imapDetectEmail(final ReferenceEmail email) throws MessagingException, IOException, InterruptedException {
        searchConditionEmailBySubject(email);

        int loop = 0;
        while (this.isEmailDetected==false && loop<10) {
            Message[] foundMessages = this.folder.search(this.searchCondition);
            if (foundMessages.length > 0) {
                for (int i = 0; i < foundMessages.length; i++) {
                    emailDetails(foundMessages, i);
                }
                rootLogger.info("Email detected");
                this.isEmailDetected = true;
                return this;
            }
            else {
                    this.isEmailDetected = false;
                    Thread.sleep(6000);
                    loop++;
                rootLogger.info("Loop # "+loop);
            }
        }
        return this;
    }
    private Message emailDetails (Message[] foundMessages, Integer i) throws MessagingException, IOException {
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

    public ImapService getFirstMessage() throws MessagingException {
       if(isEmailDetected==true) {
           this.messages  = this.folder.search(searchCondition);
           if (this.messages .length < 1) {
               Assert.fail
                       ("No Mails in inbox");
           }
           if (this.messages .length > 1) {
               rootLogger.info
                       ("More that 1 Mails detected. Qty = " + this.messages .length);
           }
           this.message = this.messages[0];
       }
       return this;
    }

    public ImapService setHtmlPart() throws IOException, MessagingException {
        this.messageHtmlPart = null;
        Object content = this.message.getContent();
        BodyPart bp;
        if (content instanceof Multipart) {
            Multipart mp = (Multipart) content;
            for (int i = 0; i < mp.getCount(); i++) {
                bp = mp.getBodyPart(i);
                if (Pattern
                        .compile(Pattern.quote("text/html"),
                                Pattern.CASE_INSENSITIVE)
                        .matcher(bp.getContentType()).find()) {
                    rootLogger.debug("found html part");
                    rootLogger.debug((String) bp.getContent());
                    rootLogger.debug("--------------------------------");

                    rootLogger.info(bp.getContentType());
                    rootLogger.info("--------------------------------");
                    this.messageHtmlPart = (String) bp.getContent();
                    return this;
                } else {
                    rootLogger.debug("some other body part...");
                }
            }
        }
        return this;
    }

    public ImapService emailAllEmailsCleaner() throws MessagingException, InterruptedException {
        ObjectUser user1 = new ObjectUser(newBuilder()).buildUser(USER_01);
        ObjectUser user2 = new ObjectUser(newBuilder()).buildUser(USER_02);
        ObjectUser user3 = new ObjectUser(newBuilder()).buildUser(USER_03);
        ObjectUser user4 = new ObjectUser(newBuilder()).buildUser(USER_04);
        ObjectUser user5 = new ObjectUser(newBuilder()).buildUser(USER_05);
        ObjectUser user6 = new ObjectUser(newBuilder()).buildUser(USER_06);
        ObjectUser user8 = new ObjectUser(newBuilder()).buildUser(USER_08);
        ObjectUser[] users = {user1, user2, user3, user4, user5, user6, user8};
        int i = users.length;
        do {
            new ImapService()
                    .setProperties()
                    .connectStore(users[i-1])
                    .openFolder()
                    .markEmailsForDeletion()
                    .clearFolder()
                    .closeStore();
            i--;
        }
        while (i>0);
        return this;
    }
    public ImapService emailEmailCleaner(ObjectUser user) throws MessagingException, InterruptedException {
        ObjectUser[] users = {user};
        int i = users.length;
        do {
            new ImapService()
                    .setProperties()
                    .connectStore(users[i-1])
                    .openFolder()
                    .markEmailsForDeletion()
                    .clearFolder()
                    .closeStore();
            i--;
        }
        while (i>0);
        return this;
    }
}
