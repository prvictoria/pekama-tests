package Steps.Objects.Emails;

import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.select.Elements;
import org.junit.Assert;

import javax.mail.MessagingException;
import java.io.IOException;

import static Steps.Objects.Emails.EmailTypes.REPORT;
import static Steps.Objects.Emails.ReferenceEmail.reportName;
import static Steps.Objects.Emails.ReferenceEmail.reportSchedule;

final public class ValidatorEmailReport {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private EmailValidator emailValidator;
    private String html;
    private boolean isValidationPassed = false;
    private ObjectUser user;
    private ReferenceEmail referenceEmail;
    private ImapService actualEmail;
    private String unSubscribeLink;

    public String getUnSubscribeLink() {
        rootLogger.info(this.unSubscribeLink);
        return unSubscribeLink;
    }
    public ValidatorEmailReport buildReferenceEmail(ObjectUser user, String reportsInterval, String reportTitle){
        this.user = user;
        reportSchedule = reportsInterval;
        reportName = reportTitle;
        this.referenceEmail = new ReferenceEmail().buildEmail(REPORT, this.user);
        return this;
    }

    public ValidatorEmailReport getEmailFormInbox() throws MessagingException, InterruptedException, IOException {
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

    public ValidatorEmailReport buildValidator(){
        new ValidatorEmailReport();
        this.emailValidator = EmailValidator.builder()
                .html(this.actualEmail.getMessageHtmlPart())
                .actualEmail(this.actualEmail)
                .referenceEmail(this.referenceEmail)
                .build();
        return this;
    }


    //@Override
    public ValidatorEmailReport checkEmailBody(){
        this.html = this.emailValidator.actualEmail().getMessageHtmlPart();

        if(emailValidator!=null){
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(this.html).size() == 2);
            Elements links = EmailUtils.parseHtmlHrefArray(this.html);
            Assert.assertTrue(EmailUtils.getLink(links, 0)
                            .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail()
                            .emailLinkBackToPekama()));
            Assert.assertTrue(EmailUtils.getLink(links, 1)
                    .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail().emailLinkUnSubscribe()));


            //Text asserts
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailText()));
            rootLogger.info("Email validation passed");
            this.unSubscribeLink = EmailUtils.getLink(links, 1);
            this.isValidationPassed = true;
            return this;
        }
        return this;
    }
    public ValidatorEmailReport assertValidationResult(){
        if(isValidationPassed==false){
            Assert.fail("Validation failed");
        }
        return this;
    }
}
