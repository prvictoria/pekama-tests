package Tests.TestsCommunity;

import Pages.NewCommunity.PageJoin;
import Steps.ObjectUser;
import Steps.Objects.Emails.ValidatorEmailResetPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;

import java.io.IOException;

import static Pages.NewCommunity.PageJoin.JOIN_URL;
import static Pages.PekamaResetPassword.*;
import static Pages.PekamaResetPassword.RESET_PAGE_FINISHED_BTN_LOGIN;
import static Pages.PekamaSignUp.arrayInvalidPasswords;
import static Pages.UrlStrings.*;
import static Steps.ObjectUser.Users.USER_04;
import static Steps.ObjectUser.newBuilder;
import static Steps.Steps.clickSelector;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

public class TestsCommunityResetPassword extends Configuration{
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final ObjectUser forgottenPasswordUser = new ObjectUser(newBuilder()).buildUser(USER_04);
    private PageJoin pageJoin;
    private static String resetPasswordLink = null;
    private static String usedPasswordLink = null;
    private static String newPasswordPekama = null;
    private static String usedPasswordPekama = null;
    
    @BeforeMethod
    public void openTarget() {
        pageJoin = new PageJoin();
        openUrlIfActualNotEquals(JOIN_URL);
        hideZopim();
        submitCookie(10);
        refresh();
    }
    @Test (priority = 100)
    public void resetPassword_A_get_link() throws MessagingException, InterruptedException, IOException {
        resetPasswordLink = null;
        pageJoin.submitResetPassword(forgottenPasswordUser);
        pageJoin.validateSubmitResetPassword(true, null);

        rootLogger.info("Check reset password email");
        resetPasswordLink = new ValidatorEmailResetPassword()
                .buildReferenceEmail(forgottenPasswordUser)
                .getEmailFormInbox()
                .buildValidator()
                .checkEmailBody()
                .assertValidationResult()
                .getResetPasswordLink();
        Assert.assertNotNull(resetPasswordLink);

        rootLogger.info("Test passed");
    }
    @Test (priority = 101)
    public void resetPassword_F_weak_password_validation_loop() {
        Assert.assertNotNull(resetPasswordLink);
            rootLogger.info("Start Validation Loop - "+"ObjectUser submitted invalid password");
            openUrlIfActualNotEquals(resetPasswordLink);
            for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
                forgottenPasswordUser.submitResetPassword(
                        arrayInvalidPasswords[arrayLength],
                        arrayInvalidPasswords[arrayLength]);
                RESET_PAGE_ERROR.filter(visible);
                refresh();
                sleep(500);
            }
            rootLogger.info("Validation Loop - passed");
    }
    @Test (priority = 102)
    public void resetPassword_P_valid_new_password() {
        Assert.assertNotNull(resetPasswordLink);
            rootLogger.info("Open URL - " +resetPasswordLink);
            openUrlWithBaseAuth(resetPasswordLink);
            sleep(1000);
            rootLogger.info("Positive test");
            forgottenPasswordUser.submitResetPassword(null);
            newPasswordPekama = forgottenPasswordUser.passwordPekama;
            checkText(RESET_PAGE_FINISHED_TITLE);
            RESET_PAGE_FINISHED_BTN_LOGIN.shouldBe(visible);
            assertEquals(getActualUrl(), URL_RESET_PASSWORD_COMPLETE);

            clickSelector(RESET_PAGE_FINISHED_BTN_LOGIN);
            forgottenPasswordUser.submitLoginCredentials(
                    forgottenPasswordUser.email,
                    forgottenPasswordUser.passwordPekama);
            sleep(3000);
            assertEquals(getActualUrl(), "https://communitystaging.pekama.com/");
            rootLogger.info("ObjectUser submitted valid credentials");
    }
    @Test (priority = 103)
    public void resetPassword_S_used_link() {
        Assert.assertNotNull(resetPasswordLink);
        usedPasswordLink = resetPasswordLink;
            openUrlWithBaseAuth(usedPasswordLink);
            sleep(1000);

            rootLogger.info("Validation test - link was used");
            checkText(FAILED_RESET_TITLE_TEXT);
            rootLogger.info("Page Title - "+FAILED_RESET_TITLE_TEXT);
            return;
    }
}
