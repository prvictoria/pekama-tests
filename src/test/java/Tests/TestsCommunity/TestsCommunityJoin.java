package Tests.TestsCommunity;

import Pages.NewCommunity.PageJoin;
import Steps.MessagesIMAP;
import Steps.ObjectUser;
import Steps.Objects.Emails.Email;
import Steps.Objects.Emails.EmailTypes;
import Steps.Objects.Emails.ImapService;
import Steps.Objects.Emails.ValidateEmailSignUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Assert;
import org.testng.annotations.*;

import javax.mail.MessagingException;

import java.io.IOException;

import static Pages.NewCommunity.PageJoin.*;
import static Steps.ObjectUser.Users.USER_05;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class TestsCommunityJoin extends Configuration{
    static final Logger rootLogger = LogManager.getRootLogger();
    ObjectUser invited;
    private PageJoin pageJoin;
    static boolean skipBefore;

    @BeforeMethod
    public void openTarget() {
        if (skipBefore==false) {
            pageJoin = new PageJoin();
            openUrlIfActualNotEquals(JOIN_URL);
            hideZopim();
            submitCookie(10);
            refresh();
            invited = new ObjectUser(newBuilder()).buildUser(USER_05);
        }
    }
    @Test (priority = 100)
    public void joinValidate(){
        ObjectUser user = newBuilder().build();
        refresh();
        pageJoin.submitSignUp(user);
        Assert.assertFalse(user.isSignUpSucceed);
        checkText("This field may not be blank.", 6);
        checkText("This field is required.", 3);
    }
    @Test (priority = 200)
    public void joinSuccess() throws MessagingException, InterruptedException {
        skipBefore = true;


        new ImapService()
                .setProperties()
                .connectStore(invited)
                .openFolder()
                .markEmailsForDeletion()
                .clearFolder()
                .closeStore();

        pageJoin.submitSignUp(invited);
        Assert.assertTrue(invited.isSignUpSucceed);
    }
    @Test (priority = 202, dependsOnMethods = { "joinSuccess" })
    public void joinGetEmail() throws MessagingException, InterruptedException, IOException {
        skipBefore = false;

        Email referenceEmail = new Email().buildEmail(EmailTypes.SIGN_UP, invited);
        ImapService actualEmail = new ImapService()
                .setProperties()
                .connectStore(invited)
                .openFolder()
                .imapDetectEmail(referenceEmail)
                .getFirstMessage()
                .setHtmlPart()
                .closeStore();
        new ValidateEmailSignUp()
                .buildValidator(actualEmail, referenceEmail)
                .checkEmailBody()
                .assertValidationResult();

        rootLogger.info("Test passed");
    }

    @AfterClass
    public void clear() throws MessagingException, InterruptedException {
        new ImapService()
                .setProperties()
                .connectStore(invited)
                .openFolder()
                .markEmailsForDeletion()
                .clearFolder()
                .closeStore();
    }
}


