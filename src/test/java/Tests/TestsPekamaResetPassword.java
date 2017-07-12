package Tests;
import Pages.PekamaLogin;
import Steps.*;
import Steps.Intrefaces.IMessagesValidator;
import Steps.Objects.Emails.*;
import Utils.Utils;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;

import static Pages.PekamaResetPassword.*;
import static Pages.PekamaSignUp.*;
import static Pages.UrlConfiguration.*;
import static Pages.DataCredentials.*;
import static Pages.DataStrings.*;
import static Pages.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.MessagesIMAP.*;
import static Steps.ObjectUser.Users.USER_04;
import static Steps.ObjectUser.newBuilder;
import static Steps.Objects.Emails.EmailTypes.RESET_PASSWORD;
import static Steps.Steps.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekama.checkText;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaResetPassword {
    static String REDIRECT_LINK;
    static final Logger rootLogger = LogManager.getLogger(TestsPekamaResetPassword.class);
    private static String NEW_PASSWORD_PEKAMA = null;
    private static final ObjectUser user = new ObjectUser(newBuilder()).buildUser(USER_04);
    private static boolean skipBefore = false;
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        MessagesIMAP emailTask = new MessagesIMAP();
        emailTask.imapSearchEmailDeleteAll(
                user.email,
                user.passwordEmail);
    }

    @Test
    public void openResetPassword() {
        rootLogger.info("Open URL - " + URL_LOGIN);
        openUrlWithBaseAuth(URL_LOGIN);
        sleep(1000);
        PekamaLogin.lOGIN_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(PekamaLogin.lOGIN_TITLE_TEXT));
        PekamaLogin.LINK_FORGOT_PASSWORD.click();
        RESET_PAGE_TITLE.waitUntil(visible, 20000).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        RESET_PAGE_EMAIL.shouldHave(value(""));
        RESET_PAGE_RESET_BTN.shouldBe(visible).shouldBe(enabled);
    }
    @Test
    public void invalidEmailResetPassword() {
       rootLogger.info("Open URL - " +URL_PEKAMA_RESET_PASSWORD);
       openUrlWithBaseAuth(URL_PEKAMA_RESET_PASSWORD);
       user.submitReset(null);
       RESET_PAGE_ERROR.shouldHaveSize(1);
       $(byText(ERROR_MSG_INVALID_EMAIL)).shouldBe(visible);
    }
    @Test
    public void resetPassword_A_get_link() throws MessagingException, InterruptedException, IOException {
        REDIRECT_LINK = null;
        openUrlWithBaseAuth(URL_PEKAMA_RESET_PASSWORD);
        user.submitReset(user.email);
        Steps.checkTextInSelector(RESET_PAGE_SUCCESS, RESET_PAGE_SUCCESS_MSG);

        rootLogger.info("Check reset password email");
        Email referenceEmail = new Email().buildEmail(RESET_PASSWORD, user);
        rootLogger.info(referenceEmail.getAbstractEmail().emailSubject());
        ImapService actualEmail = new ImapService()
                .setProperties()
                .connectStore(user)
                .openFolder()
                .imapDetectEmail(referenceEmail)
                .getFirstMessage()
                .setHtmlPart()
                .markEmailsForDeletion()
                .clearFolder()
                .closeStore();
        REDIRECT_LINK = new ValidatorEmailResetPassword()
                .buildValidator(actualEmail, referenceEmail)
                .checkEmailBody()
                .assertValidationResult()
                .getResetPasswordLink();

        Assert.assertTrue(REDIRECT_LINK!=null);
        rootLogger.info("Test passed");
    }
    @Test
    public void resetPassword_B_reset_page_gui() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Set up new password page opened");
           NEWPASSWORD_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(NEWPASSWORD_TITLE_TEXT));
            $$(byText(NEWPASSWORD_TEXT)).filter(visible).shouldHave(size(1));
           NEWPASSWORD_PAGE_NEW_PASSWORD.shouldBe(Condition.visible).shouldHave(value(""));
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).shouldHave(value(""));
           NEWPASSWORD_PAGE_RESTORE_BTN.shouldBe(Condition.visible);
            rootLogger.info("Set up new password page - contain all elements");
            }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Test
    public void resetPassword_C_submit_blank_password() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test");
            NEWPASSWORD_PAGE_RESTORE_BTN.waitUntil(visible, 10000).click();
            checkText(ERROR_MSG_REQUIRED_FIELD, 2);
            rootLogger.info("ObjectUser submitted blank form - "+ERROR_MSG_REQUIRED_FIELD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_D_submit_different_passwords() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test");
            user.submitResetPassword(
                    VALID_PASSWORD+"1",
                    VALID_PASSWORD+"2");
           checkText(ERROR_MSG_NOT_MATCHED_PASSWORD);
           rootLogger.info("ObjectUser submitted different passwords - "+ERROR_MSG_NOT_MATCHED_PASSWORD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Deprecated //not actual now
    @Ignore
    @Test
    public void resetPassword_E_familiar_to_email_password() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test");
            user.submitResetPassword(
                    user.email,
                    user.email);
            checkText(ERROR_MSG_PASSWORD_FAMILIAR_TO_EMAIL);
            //checkText(ERROR_MSG_WEAK_PASSWORD);
            rootLogger.info("ObjectUser submitted familiar to email passwords - "+ ERROR_MSG_PASSWORD_FAMILIAR_TO_EMAIL);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Deprecated //not actual now
    @Ignore
    @Test
    public void resetPassword_F_similar_to_name() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test - name");
            user.submitResetPassword(
                    user.name,
                    user.name);
            checkText("This password is too short. It must contain at least 8 characters.");
            checkText(ERROR_MSG_PASSWORD_FAMILIAR_TO_NAME);
            checkText("Your password must contain at least one lowercase character, one uppercase character and one digit.");
            RESET_PAGE_ERROR.filter(visible).shouldHave(size(3));
            rootLogger.info("ObjectUser submitted own Username to email passwords - "+ERROR_MSG_SHORT_PASSWORD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

     @Deprecated //not actual now
    @Ignore
    @Test
    public void resetPassword_G_similar_to_surname() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test - surname");
            user.submitResetPassword(
                    user.surname,
                    user.surname);
            checkText("This password is too short. It must contain at least 8 characters.");
            checkText(ERROR_MSG_PASSWORD_FAMILIAR_TO_SURNAME);
            checkText("Your password must contain at least one lowercase character, one uppercase character and one digit.");
            RESET_PAGE_ERROR.filter(visible).shouldHave(size(3));
            rootLogger.info("ObjectUser submitted own Surname to email passwords - "+ ERROR_MSG_PASSWORD_FAMILIAR_TO_SURNAME);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Test
    public void resetPassword_I_max_length_validation() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test");
            String RANDOM_129_LETTER = Utils.randomString(129);
            user.submitResetPassword(
                    RANDOM_129_LETTER,
                    RANDOM_129_LETTER);
            checkText("Ensure this value has at most 128 characters (it has 129).");
            rootLogger.info("Max length validation present - "+ ERROR_MSG_PASSWORD_FAMILIAR_TO_EMAIL);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Ignore //TODO bug ?
    @Test
    public void resetPassword_F_weak_password_validation_loop() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Start test - "+"ObjectUser submitted invalid password");
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);

            rootLogger.info("Validation Loop");
            for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
                user.submitResetPassword(
                        arrayInvalidPasswords[arrayLength],
                        arrayInvalidPasswords[arrayLength]);
                RESET_PAGE_ERROR.filter(visible);
                refresh();
                sleep(500);
            }
            rootLogger.info("Validation Loop - passed");
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_P_valid_new_password() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Positive test");
            user.submitResetPassword(null);
            NEW_PASSWORD_PEKAMA = user.passwordPekama;
            $(byText(RESET_PAGE_FINISHED_TITLE)).waitUntil(visible, 15000);
            RESET_PAGE_FINISHED_BTN_LOGIN.shouldBe(visible);
            String thisUrl = url();
            assertEquals(thisUrl, URL_RESET_PASSWORD_COMPLETE);
            rootLogger.info("ObjectUser submitted valid credentials");
            return;
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_Q_login_with_new_password() {
        if (NEW_PASSWORD_PEKAMA != null) {
            rootLogger.info("Login into Pekama with NEW valid credentials");
            user.login();
            Assert.assertTrue(user.isLoggedIn);
            openUrlWithBaseAuth(URL_LOGOUT);
            return;
        }
        else Assert.fail("password - "+ NEW_PASSWORD_PEKAMA);
    }

    @Test
    public void resetPassword_S_used_link() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);

            rootLogger.info("Validation test - link was used");
            $(byText(FAILED_RESET_TITLE_TEXT)).waitUntil(visible, 10000);
            rootLogger.info("Page Title - "+FAILED_RESET_TITLE_TEXT);
            rootLogger.info(REDIRECT_LINK);
            return;
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);

    }

    @Deprecated //not actual now //not actual now //not actual now
    @Ignore
    @Test
    public void resetPassword_Z_validation_set_old_password_again() {
        if (NEW_PASSWORD_PEKAMA != null) {
            REDIRECT_LINK = null;
            rootLogger.info("ObjectUser password - " + NEW_PASSWORD_PEKAMA);
            openUrlWithBaseAuth(URL_PEKAMA_RESET_PASSWORD);
            sleep(1000);
            user.submitReset(user.email);
           RESET_PAGE_SUCCESS.shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
            String testSuccessMsg = RESET_PAGE_SUCCESS.getText();
            rootLogger.info(testSuccessMsg + " displayed, valid email submitted");

            rootLogger.info("Check reset password email");
            detectEmailIMAP(
                    user.email,
                    user.passwordEmail,
                    "Password Restoration [Pekama]");
            MessagesIMAP searcher = new MessagesIMAP();
            REDIRECT_LINK = searcher.searchEmailBySubjectAndValidate(
                    user.email,
                    user.passwordEmail,
                    "Password Restoration [Pekama]",
                    new IMessagesValidator.ValidationResetPassword(), 0);
            rootLogger.info("Email and links correspond requirements");

            openUrlWithBaseAuth(REDIRECT_LINK);
            user.submitResetPassword(
                    NEW_PASSWORD_PEKAMA,
                    NEW_PASSWORD_PEKAMA);
            checkText(ERROR_MSG_NEW_PASSOWRD_EQUALS_TO_OLD);
            rootLogger.info("Validation old password present");
            return;
        }
        else Assert.fail("password - "+ NEW_PASSWORD_PEKAMA);
    }

}
