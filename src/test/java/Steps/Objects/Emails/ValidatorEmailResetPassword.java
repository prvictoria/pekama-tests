package Steps.Objects.Emails;

import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.select.Elements;
import org.junit.Assert;

import static Steps.MessagesIMAP.*;
import static Steps.Objects.Emails.EmailTypes.RESET_PASSWORD;

final public class ValidatorEmailResetPassword {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private EmailValidator emailValidator;
    private Email referenceEmail;
    private String html;
    private String resetPasswordLink;
    private boolean isValidationPassed = false;

    public String getResetPasswordLink() {
        return resetPasswordLink;
    }
    public ValidatorEmailResetPassword buildReferenceEmail(EmailTypes emailType, ObjectUser user){
        this.referenceEmail = new Email().buildEmail(emailType, user);
        return this;
    }
    public ValidatorEmailResetPassword buildValidator(ImapService actualEmail, Email referenceEmail){
        new ValidatorEmailResetPassword();
        this.emailValidator = EmailValidator.builder()
                .html(actualEmail.getMessageHtmlPart())
                .actualEmail(actualEmail)
                .referenceEmail(referenceEmail)
                .build();
        return this;
    }


    //@Override
    public ValidatorEmailResetPassword checkEmailBody(){
        //this.recipient = this.emailValidator.users().get(0);
        this.html = this.emailValidator.actualEmail().getMessageHtmlPart();

        if(emailValidator!=null){
            Assert.assertTrue(parseHtmlLinkText(this.html)
                    .equals(this.emailValidator.referenceEmail()
                            .getAbstractEmail().emailButtonLinkText()));
            Assert.assertTrue(parseHtmlHrefArray(this.html).size() == 2);
            Elements links = parseHtmlHrefArray(this.html);
            Assert.assertTrue(getLink(links, 0)
                    .contains(this.emailValidator
                    .referenceEmail().getAbstractEmail()
                    .emailLinkResetPasswordInButton()));
            Assert.assertTrue(getLink(links, 1)
                    .contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailLinkResetPassword()));

            //Text asserts
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailTitle()));
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailButtonText()));
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailText()));
            rootLogger.info("Email validation passed");
            this.resetPasswordLink = getLink(links, 0);
            this.isValidationPassed = true;
            return this;
        }
        return this;
    }

    public ValidatorEmailResetPassword assertValidationResult(){
        if(isValidationPassed==false){
            Assert.fail("Validation failed");
        }
        return this;
    }
}
