package Steps.Objects.Emails;

import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.select.Elements;
import org.junit.Assert;

import javax.mail.MessagingException;

import java.io.IOException;

import static Steps.MessagesIMAP.getLink;
import static Steps.MessagesIMAP.parseHtmlHrefArray;
import static Steps.MessagesIMAP.parseHtmlLinkText;
import static Steps.Objects.Emails.EmailTypes.SIGN_UP;

final public class ValidatorEmailSignUp {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private EmailValidator emailValidator;
    private String html;
    private String signUpLink;
    private boolean isValidationPassed = false;
    private ObjectUser user;
    private ReferenceEmail referenceEmail;
    private ImapService actualEmail;

    public String getSignUpLink() {
        rootLogger.info(this.signUpLink);
        return signUpLink;
    }
    public ValidatorEmailSignUp buildReferenceEmail(ObjectUser user){
        this.user = user;
        this.referenceEmail = new ReferenceEmail().buildEmail(SIGN_UP, this.user);
        return this;
    }

    public ValidatorEmailSignUp getEmailFormInbox() throws MessagingException, InterruptedException, IOException {
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

    public ValidatorEmailSignUp buildValidator(){
        new ValidatorEmailSignUp();
        this.emailValidator = EmailValidator.builder()
                .html(this.actualEmail.getMessageHtmlPart())
                .actualEmail(this.actualEmail)
                .referenceEmail(this.referenceEmail)
                .build();
        return this;
    }


    //@Override
    public ValidatorEmailSignUp checkEmailBody(){
        this.html = this.emailValidator.actualEmail().getMessageHtmlPart();

        if(emailValidator!=null){
            Assert.assertTrue(parseHtmlLinkText(this.html)
                    .equals(this.emailValidator.referenceEmail()
                    .getAbstractEmail().emailButtonLinkText()));
            Assert.assertTrue(parseHtmlHrefArray(this.html).size() == 3);
            Elements links = parseHtmlHrefArray(this.html);
            Assert.assertTrue(getLink(links, 0)
                            .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail()
                            .emailLinkConfirmRegistrationInButton()));
            Assert.assertTrue(getLink(links, 1)
                    .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail().emailLinkConfirmRegistration()));
            Assert.assertTrue(getLink(links, 2)
                    .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail().emailLinkMailTo()));

            //Text asserts
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailTitle()));
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailButtonText()));
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailText()));
            rootLogger.info("Email validation passed");
            this.signUpLink = getLink(links, 0);
            this.isValidationPassed = true;
            return this;
        }
        return this;
    }
    public ValidatorEmailSignUp assertValidationResult(){
        if(isValidationPassed==false){
            Assert.fail("Validation failed");
        }
        return this;
    }
}
