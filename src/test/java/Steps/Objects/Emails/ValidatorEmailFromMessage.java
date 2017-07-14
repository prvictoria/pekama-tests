package Steps.Objects.Emails;

import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;

import javax.mail.MessagingException;
import java.io.IOException;

import static Steps.MessagesIMAP.*;
import static Steps.Objects.Emails.EmailTypes.MESSAGE_EMAIL;
import static Steps.Objects.Emails.ReferenceEmail.*;

final public class ValidatorEmailFromMessage {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private EmailValidator emailValidator;
    private String html;
    private boolean isValidationPassed = false;
    private ObjectUser user;
    private ReferenceEmail referenceEmail;
    private ImapService actualEmail;
    private String replyInPekamaBackLink;

    public ValidatorEmailFromMessage buildReferenceEmail(String emailActualSubject, ObjectUser follower, ObjectUser posterMessage){
        this.user = follower;
        inviterUser = posterMessage;
        thisEmailSubject = emailActualSubject;
        this.referenceEmail = new ReferenceEmail().buildEmail(MESSAGE_EMAIL, this.user);
        return this;
    }

    public ValidatorEmailFromMessage getEmailFormInbox() throws MessagingException, InterruptedException, IOException {
        this.actualEmail = new ImapService()
                .setProperties()
                .connectStore(this.user)
                .openFolder()
                .imapDetectEmail(this.referenceEmail)
                .getFirstMessage()
                .setHtmlPart()
                //.markEmailsForDeletion()
                .clearFolder()
                .closeStore();
        return this;
    }

    public ValidatorEmailFromMessage buildValidator(){
        new ValidatorEmailFromMessage();
        this.emailValidator = EmailValidator.builder()
                .html(this.actualEmail.getMessageHtmlPart())
                .actualEmail(this.actualEmail)
                .referenceEmail(this.referenceEmail)
                .build();
        return this;
    }

    public ValidatorEmailFromMessage checkEmailBody() throws IOException {
        this.html = this.emailValidator.actualEmail().getMessageHtmlPart();

        if(emailValidator!=null){
            Assert.assertTrue(parseHtmlHrefArray(this.html).size() == 1);
            Elements links = parseHtmlHrefArray(this.html);
            Assert.assertTrue(getLink(links, 0)
                    .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail()
                            .emailLinkBackToPekama()));

            Assert.assertTrue("Button text check failed", parseHtmlLinkText(this.html)
                    .equals(this.emailValidator.referenceEmail()
                    .getAbstractEmail().emailButtonLinkText()));

            Document document = document(this.html);
            parseCleanHtml(document); //parse email text
            Assert.assertEquals(getHtmlElementByTag(document, "p", 0),
                    this.emailValidator
                    .referenceEmail().getAbstractEmail()
                    .emailTitle());
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailText()));
            rootLogger.info("Email validation passed");
            this.replyInPekamaBackLink = getLink(links, 0);
            this.isValidationPassed = true;
            return this;
        }
        return this;
    }
    public ValidatorEmailFromMessage assertValidationResult(){
        if(isValidationPassed==false){
            Assert.fail("Validation failed");
        }
        return this;
    }
}
