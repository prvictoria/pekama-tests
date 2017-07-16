package Steps.Objects.Emails;

import Steps.ObjectProject;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.select.Elements;
import org.junit.Assert;

import javax.mail.MessagingException;
import java.io.IOException;

import static Steps.Objects.Emails.EmailTypes.INVITE_IN_PROJECT;
import static Steps.Objects.Emails.ReferenceEmail.*;
import static Steps.Objects.Emails.ReferenceEmail.projectInEmail;

final public class ValidatorEmailInviteInProject {
    private static final Logger rootLogger = LogManager.getRootLogger();
    private EmailValidator emailValidator;
    private String html;
    private String inviteLink;
    private boolean isValidationPassed = false;
    private ObjectUser user;
    private ReferenceEmail referenceEmail;
    private ImapService actualEmail;

    public String getInviteLink() {
        rootLogger.info(this.inviteLink);
        return inviteLink;
    }
    public ValidatorEmailInviteInProject buildReferenceEmail(ObjectUser invited, ObjectUser inviter, ObjectProject project){
        this.user = invited;
        invitedUser = invited;
        inviterUser = inviter;
        projectInEmail = project;
        this.referenceEmail = new ReferenceEmail().buildEmail(INVITE_IN_PROJECT, invitedUser);

        rootLogger.debug(this.referenceEmail.getAbstractEmail().emailSubject());
        rootLogger.debug(this.referenceEmail.getAbstractEmail().emailTitle());
        rootLogger.debug(this.referenceEmail.getAbstractEmail().emailText());
        rootLogger.debug(this.referenceEmail.getAbstractEmail().emailButtonLinkText());
        return this;
    }

    public ValidatorEmailInviteInProject getEmailFormInbox() throws MessagingException, InterruptedException, IOException {
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

    public ValidatorEmailInviteInProject buildValidator(){
        new ValidatorEmailInviteInProject();
        this.emailValidator = EmailValidator.builder()
                .html(this.actualEmail.getMessageHtmlPart())
                .actualEmail(this.actualEmail)
                .referenceEmail(this.referenceEmail)
                .build();
        return this;
    }


    //@Override
    public ValidatorEmailInviteInProject checkEmailBody(){
        this.html = this.emailValidator.actualEmail().getMessageHtmlPart();

        if(emailValidator!=null){
            //Links validation
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(this.html).size() == 3);
            Elements links = EmailUtils.parseHtmlHrefArray(this.html);
            Assert.assertTrue(EmailUtils.getLink(links, 0)
                            .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail()
                            .emailLinkPathPart1()));
            Assert.assertTrue(EmailUtils.getLink(links, 0)
                    .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail().emailLinkPathPart2()));
            Assert.assertTrue(EmailUtils.getLink(links, 1)
                    .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail()
                            .emailLinkPathPart1()));
            Assert.assertTrue(EmailUtils.getLink(links, 1)
                    .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail().emailLinkPathPart2()));
            Assert.assertTrue(EmailUtils.getLink(links, 2)
                    .contains(this.emailValidator
                            .referenceEmail().getAbstractEmail().emailLinkMailTo()));

            //Text asserts
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailTitle()));
            Assert.assertTrue(this.html.contains(this.emailValidator
                    .referenceEmail().getAbstractEmail().emailText()));
            Assert.assertTrue(EmailUtils.parseHtmlLinkText(this.html)
                    .equals(this.emailValidator.referenceEmail()
                            .getAbstractEmail().emailButtonLinkText()));

            rootLogger.info("Email validation passed");
            this.inviteLink = EmailUtils.getLink(links, 0);
            this.isValidationPassed = true;
            return this;
        }
        return this;
    }
    public ValidatorEmailInviteInProject assertValidationResult(){
        if(isValidationPassed==false){
            Assert.fail("Validation failed");
        }
        return this;
    }
}
