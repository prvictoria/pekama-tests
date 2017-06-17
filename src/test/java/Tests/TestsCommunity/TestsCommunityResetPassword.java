package Tests.TestsCommunity;

import Page.NewCommunity.PageJoin;
import Steps.IMessagesValidator;
import Steps.MessagesIMAP;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Page.NewCommunity.PageJoin.JOIN_URL;
import static Page.PekamaResetPassword.*;
import static Page.PekamaResetPassword.RESET_PAGE_FINISHED_BTN_LOGIN;
import static Page.PekamaSignUp.arrayInvalidPasswords;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.Messages.EMAIL_SUBJECT_PASSWORD_REGISTRATION;
import static Steps.MessagesIMAP.detectEmailIMAP;
import static Steps.ObjectUser.Users.USER_04;
import static Steps.ObjectUser.newBuilder;
import static Steps.Steps.clickSelector;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

/**
 * Created by VatslauX on 14-Jun-17.
 */
public class TestsCommunityResetPassword {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final ObjectUser forgottenPasswordUser = new ObjectUser(newBuilder()).buildUser(USER_04);
    private PageJoin pageJoin;
    private static String resetPasswordLink = null;
    private static String usedPasswordLink = null;
    private static String newPasswordPekama = null;
    private static String usedPasswordPekama = null;
    

    @BeforeClass
    public void setUp() throws IOException {
        setEnvironment(2);
        setBrowser();
        holdBrowserAfterTest();
        openUrlIfActualNotEquals(JOIN_URL);
        hideZopim();
        submitCookie(10);
    }
    @BeforeMethod
    public void openTarget() {
        pageJoin = new PageJoin();
        refresh();
    }
    @Test (priority = 100)
    public void resetPassword_A_get_link() {
        resetPasswordLink = null;
        pageJoin.submitResetPassword(forgottenPasswordUser);
        pageJoin.validateSubmitResetPassword(true, null);

        rootLogger.info("Check reset password email");
        Boolean detectResult = detectEmailIMAP(
                forgottenPasswordUser.email,
                forgottenPasswordUser.passwordEmail,
                EMAIL_SUBJECT_PASSWORD_REGISTRATION);
        MessagesIMAP searcher = new MessagesIMAP();
        Assert.assertTrue(detectResult);
        resetPasswordLink = searcher.searchEmailBySubjectAndValidate(
                forgottenPasswordUser.email,
                forgottenPasswordUser.passwordEmail,
                EMAIL_SUBJECT_PASSWORD_REGISTRATION,
                new IMessagesValidator.ValidationResetPassword(), 0);
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
            $(byText(RESET_PAGE_FINISHED_TITLE)).waitUntil(visible, 15000);
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
            $(byText(FAILED_RESET_TITLE_TEXT)).waitUntil(visible, 10000);
            rootLogger.info("Page Title - "+FAILED_RESET_TITLE_TEXT);
            return;
    }
}
