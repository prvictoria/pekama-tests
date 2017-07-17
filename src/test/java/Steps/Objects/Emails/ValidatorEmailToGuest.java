package Steps.Objects.Emails;

import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import javax.mail.MessagingException;
import java.io.IOException;

import static Steps.Objects.Emails.EmailTypes.MESSAGE_TO_REGISTERED;
import static Steps.Objects.Emails.ReferenceEmail.*;

final public class ValidatorEmailToGuest {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private EmailValidator emailValidator;
    private String html;
    private boolean isValidationPassed = false;
    private ObjectUser user;
    private ReferenceEmail referenceEmail;
    private ImapService actualEmail;

    public ValidatorEmailToGuest buildReferenceEmailCheckFollower(String emailActualSubject, ObjectUser follower, ObjectUser posterMessage){
        //For check email as follower
        this.user = follower;
        thisEmailSubject = emailActualSubject;
        inviterUser = posterMessage;
        followerEmailOrTeamNameSurname = follower.email;
        this.referenceEmail = new ReferenceEmail().buildEmail(MESSAGE_TO_REGISTERED, this.user);
        return this;
    }
    public ValidatorEmailToGuest buildReferenceEmailCheckSender(String emailActualSubject, ObjectUser follower, ObjectUser posterMessage){
        //For check email as sender
        this.user = posterMessage;
        thisEmailSubject = emailActualSubject;
        inviterUser = posterMessage;
        followerEmailOrTeamNameSurname = follower.email;
        this.referenceEmail = new ReferenceEmail().buildEmail(MESSAGE_TO_REGISTERED, this.user);
        return this;
    }

    public ValidatorEmailToGuest getEmailFormInbox() throws MessagingException, InterruptedException, IOException {
        this.actualEmail = new ImapService()
                .setProperties()
                .connectStore(this.user)
                .openFolder()
                .imapDetectEmail(this.referenceEmail)
                .getFirstMessage()
                .setHtmlPart()
                .markEmailsForDeletion()
                .clearFolder()
                .closeStore();
        return this;
    }

    public ValidatorEmailToGuest buildValidator(){
        new ValidatorEmailToGuest();
        this.emailValidator = EmailValidator.builder()
                .html(this.actualEmail.getMessageHtmlPart())
                .actualEmail(this.actualEmail)
                .referenceEmail(this.referenceEmail)
                .build();
        return this;
    }

    public ValidatorEmailToGuest checkEmailBody() throws IOException {
        this.html = this.emailValidator.actualEmail().getMessageHtmlPart();

        if(emailValidator!=null){
            Assert.assertTrue(EmailUtils.parsedEmailToText(this.html)
                    .contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailTitle()));

            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailText()));
            rootLogger.info("Email validation passed");
            this.isValidationPassed = true;
            return this;
        }
        return this;
    }
    public ValidatorEmailToGuest assertValidationResult(){
        if(isValidationPassed==false){
            Assert.fail("Validation failed");
        }
        return this;
    }
}
