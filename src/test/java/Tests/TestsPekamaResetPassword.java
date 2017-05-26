package Tests;
import Steps.*;
import Utils.Utils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;

import static Page.Emails.*;
import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.PekamaSignUp.*;
import static Page.UrlConfig.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.MessagesIMAP.*;
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
    public static String SELECT_HOST = ENVIRONMENT_PEKAMA;
    private static String NEW_PASSWORD_PEKAMA = null;
    private static final String GMAIL_LOGIN = User4.GMAIL_EMAIL.getValue();
    private static final String GMAIL_PASSWORD = User4.GMAIL_PASSWORD.getValue();
    private SelenideElement EMAIL_SUBJECT = EMAIL_RESET_PASSWORD_SUBJECT;
    private String EMAIL_TITLE = EMAIL_RESET_PASSWORD_TITLE;
    private String EMAIL_TEXT = EMAIL_RESET_PASSWORD_TEXT;
    private String EMAIL_BTN = EMAIL_RESET_PASSWORD_BTN;
    private SelenideElement EMAIL_REDIRECT_LINK = EMAIL_RESET_PASSWORD_BACKLINK;
    private String email = User4.GMAIL_EMAIL.getValue();
    private static final String USERNAME = User4.NAME.getValue();
    private static final String USERSURNAME = User4.SURNAME.getValue();
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
                GMAIL_LOGIN,
                GMAIL_PASSWORD);
    }
//    @Ignore
//    @Before
//    public void before(){
//    }
//    @AfterClass
//    public static void afterClass() {
//        clearBrowserCache();
//    }
    @Test
    public void openResetPassword() {
        rootLogger.info("Open URL - " +URL_LogIn);
        openUrlWithBaseAuth(URL_LogIn);
        sleep(1000);
        lOGIN_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
        LINK_FORGOT_PASSWORD.click();
        RESET_PAGE_TITLE.waitUntil(visible, 20000).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        RESET_PAGE_EMAIL.shouldHave(value(""));
        RESET_PAGE_RESET_BTN.shouldBe(visible).shouldBe(enabled);
    }
    @Test
    public void invalidEmailResetPassword() {
       rootLogger.info("Open URL - " +URL_PEKAMA_RESET_PASSWORD);
       openUrlWithBaseAuth(URL_PEKAMA_RESET_PASSWORD);
       ObjectUser userInvalidPassword = ObjectUser.newBuilder().build();
       userInvalidPassword.submitReset(null);
       RESET_PAGE_ERROR.shouldHaveSize(1);
       $(byText(ERROR_MSG_INVALID_EMAIL)).shouldBe(visible);
    }
    @Test
    public void resetPassword_A_get_link() {
        REDIRECT_LINK = null;
        rootLogger.info("Open URL - " + URL_PEKAMA_RESET_PASSWORD);
        openUrlWithBaseAuth(URL_PEKAMA_RESET_PASSWORD);

        ObjectUser userNewPassword = ObjectUser.newBuilder().build();
        userNewPassword.submitReset(email);

       RESET_PAGE_SUCCESS.shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
        String testSuccessMsg = RESET_PAGE_SUCCESS.getText();
        rootLogger.info(testSuccessMsg + " displayed, valid email submitted");

        rootLogger.info("Check reset password email");
        Boolean detectResult = detectEmailIMAP(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                EMAIL_SUBJECT_PASSWORD_REGISTRATION);
        MessagesIMAP searcher = new MessagesIMAP();
        Assert.assertTrue(detectResult);
        REDIRECT_LINK = searcher.searchEmailBySubjectAndValidate(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                EMAIL_SUBJECT_PASSWORD_REGISTRATION,
                new MessagesValidator.ValidationResetPassword(), 0);
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
           NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys("12345");
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys("67890");
           NEWPASSWORD_PAGE_RESTORE_BTN.click();
           checkText(ERROR_MSG_NOT_MATCHED_PASSWORD);
            rootLogger.info("ObjectUser submitted different passwords - "+ERROR_MSG_NOT_MATCHED_PASSWORD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_E_familiar_to_email_password() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test");
            ObjectUser newPassword = ObjectUser.newBuilder().build();
            newPassword.submitResetPassword(
                    User1.GMAIL_EMAIL.getValue(),
                    User1.GMAIL_EMAIL.getValue());
            checkText(ERROR_MSG_PASSWORD_FAMILIAR_TO_EMAIL);
            //checkText(ERROR_MSG_WEAK_PASSWORD);
            rootLogger.info("ObjectUser submitted familiar to email passwords - "+ ERROR_MSG_PASSWORD_FAMILIAR_TO_EMAIL);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_F_weak_password_validation_loop() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Start test - "+"ObjectUser submitted invalid password");
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);

            rootLogger.info("Validation Loop");
            for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
                ObjectUser newPassword = ObjectUser.newBuilder().build();
                newPassword.submitResetPassword(
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
    public void resetPassword_G_similar_to_name() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test - name");
            ObjectUser newPassword = ObjectUser.newBuilder().build();
            newPassword.submitResetPassword(
                    USERNAME,
                    USERNAME);
           checkText(ERROR_MSG_PASSWORD_FAMILIAR_TO_NAME);
           checkText(ERROR_MSG_SHORT_PASSWORD);
            RESET_PAGE_ERROR.filter(visible).shouldHave(size(2));
            rootLogger.info("ObjectUser submitted own Username to email passwords - "+ERROR_MSG_SHORT_PASSWORD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_H_similar_to_surname() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            openUrlWithBaseAuth(REDIRECT_LINK);
            sleep(1000);
            rootLogger.info("Validation test");
            ObjectUser newPassword = ObjectUser.newBuilder().build();
            newPassword.submitResetPassword(
                    USERSURNAME,
                    USERSURNAME);
            checkText(ERROR_MSG_PASSWORD_FAMILIAR_TO_SURNAME);
            RESET_PAGE_ERROR.filter(visible).shouldHave(size(1));
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
            ObjectUser newPassword = ObjectUser.newBuilder().build();
            newPassword.submitResetPassword(
                    RANDOM_129_LETTER,
                    RANDOM_129_LETTER);
            checkText("Ensure this value has at most 128 characters (it has 129).");
            rootLogger.info("Max length validation present - "+ ERROR_MSG_PASSWORD_FAMILIAR_TO_EMAIL);
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
            ObjectUser userResetPassword = ObjectUser.newBuilder().build();
            userResetPassword.submitResetPassword(null);
            NEW_PASSWORD_PEKAMA = userResetPassword.passwordPekama;
            $(byText(RESET_PAGE_FINISHED_TITLE)).waitUntil(visible, 15000);
            RESET_PAGE_FINISHED_BTN_LOGIN.shouldBe(visible);
            String thisUrl = url();
            assertEquals(thisUrl, URL_PEKAMA_RESET_PASSWORD_COMPLETE);
            rootLogger.info("ObjectUser submitted valid credentials");
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_Q_login_with_new_password() {
        if (NEW_PASSWORD_PEKAMA != null) {
            rootLogger.info("Open URL - " +URL_Dashboard);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = URL_Dashboard;
            openUrlWithBaseAuth(AUTH_URL);
            sleep(1000);
            ObjectUser owner = ObjectUser.newBuilder().email(email).passwordPekama(NEW_PASSWORD_PEKAMA).build();
            owner.loginByURL(owner.email, owner.passwordPekama, URL_PEKAMA_LOGIN);
            Assert.assertTrue(owner.isLoginSucceed);
        rootLogger.info("Login into Pekama with NEW valid credentials");
            openUrlWithBaseAuth(URL_Logout);
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
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);

    }

    @Test
    public void resetPassword_Z_validation_set_old_password_again() {
        if (NEW_PASSWORD_PEKAMA != null) {
            REDIRECT_LINK = null;
            rootLogger.info("ObjectUser password - " + NEW_PASSWORD_PEKAMA);
            openUrlWithBaseAuth(URL_PEKAMA_RESET_PASSWORD);
            sleep(1000);
            ObjectUser userOldPassword = ObjectUser.newBuilder().build();
            userOldPassword.submitReset(email);
           RESET_PAGE_SUCCESS.shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
            String testSuccessMsg = RESET_PAGE_SUCCESS.getText();
            rootLogger.info(testSuccessMsg + " displayed, valid email submitted");

            rootLogger.info("Check reset password email");
            detectEmailIMAP(
                    GMAIL_LOGIN,
                    GMAIL_PASSWORD,
                    "Password Restoration [Pekama]");
            MessagesIMAP searcher = new MessagesIMAP();
            REDIRECT_LINK = searcher.searchEmailBySubjectAndValidate(
                    GMAIL_LOGIN,
                    GMAIL_PASSWORD,
                    "Password Restoration [Pekama]",
                    new MessagesValidator.ValidationResetPassword(), 0);
            rootLogger.info("Email and links correspond requirements");

            openUrlWithBaseAuth(REDIRECT_LINK);
            userOldPassword.submitResetPassword(
                    NEW_PASSWORD_PEKAMA,
                    NEW_PASSWORD_PEKAMA);
            checkText(ERROR_MSG_NEW_PASSOWRD_EQUALS_TO_OLD);
            rootLogger.info("Validation old password present");
        }
        else Assert.fail("password - "+ NEW_PASSWORD_PEKAMA);
    }

}
