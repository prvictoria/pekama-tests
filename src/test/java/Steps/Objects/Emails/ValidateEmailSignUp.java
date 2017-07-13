package Steps.Objects.Emails;

import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.select.Elements;
import org.junit.Assert;

import static Steps.MessagesIMAP.getLink;
import static Steps.MessagesIMAP.parseHtmlHrefArray;
import static Steps.MessagesIMAP.parseHtmlLinkText;

final public class ValidateEmailSignUp {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private EmailValidator emailValidator;
    private String html;
    private String signUpLink;
    private ObjectUser recipient;
    private ObjectUser sender;
    private ObjectUser other;
    private boolean isValidationPassed = false;


    public String getSignUpLink() {
        return signUpLink;
    }

    public ValidateEmailSignUp buildValidator(ImapService actualEmail, Email referenceEmail){
        new ValidateEmailSignUp();
        this.emailValidator = EmailValidator.builder()
                .html(actualEmail.getMessageHtmlPart())
                .actualEmail(actualEmail)
                .referenceEmail(referenceEmail)
                .build();
        return this;
    }


    //@Override
    public ValidateEmailSignUp checkEmailBody(){
        //this.recipient = this.emailValidator.users().get(0);
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
    public ValidateEmailSignUp assertValidationResult(){
        if(isValidationPassed==false){
            Assert.fail("Validation failed");
        }
        return this;
    }
}
