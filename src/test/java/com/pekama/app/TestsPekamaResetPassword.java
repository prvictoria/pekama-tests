package com.pekama.app;
import Steps.StepsExternal;
import Steps.StepsHttpAuth;
import Steps.StepsPekama;
import Utils.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.pekama.app.draft.LoginGmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;


import static Page.PekamaSignUp.*;
import static Page.TestsUrl.*;
import static Page.Emails.*;
import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrlConfiguration.*;
import static Steps.StepsExternal.REDIRECT_LINK;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaResetPassword {
    static final Logger rootLogger = LogManager.getLogger(LoginGmail.class);
    public static String SELECT_HOST = PEKAMA;
    public static String NEW_PASSWORD = null;
    String GMAIL_LOGIN = User4.GMAIL_EMAIL.getValue();
    String GMAIL_PASSWORD = User4.GMAIL_PASSWORD.getValue();
    SelenideElement EMAIL_SUBJECT = EMAIL_RESET_PASSWORD_SUBJECT;
    String EMAIL_TITLE = EMAIL_RESET_PASSWORD_TITLE;
    String EMAIL_TEXT = EMAIL_RESET_PASSWORD_TEXT;
    String EMAIL_BTN = EMAIL_RESET_PASSWORD_BTN;
    SelenideElement EMAIL_REDIRECT_LINK = EMAIL_RESET_PASSWORD_BACKLINK;
    String PEKAMA_USER_EMAIL = User4.GMAIL_EMAIL.getValue();


    @Test
    public void openResetPassword() {
        rootLogger.info("Open URL - " +URL_LogIn);
        StepsHttpAuth openHost = new StepsHttpAuth();
        String AUTH_URL = URL_LogIn;
        openHost.httpAuthUrl(AUTH_URL);
        sleep(1000);
       lOGIN_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
       LINK_FORGOT_PASSWORD.click();
        sleep(1000);
    }
    @Test
    public void invalidEmailResetPassword() {
        rootLogger.info("Open URL - " +URL_ResetPassword);
        StepsHttpAuth openHost = new StepsHttpAuth();
        String AUTH_URL = URL_ResetPassword;
        openHost.httpAuthUrl(AUTH_URL);
        sleep(1000);
       RESET_PAGE_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
       RESET_PAGE_EMAIL.sendKeys("123@123");
       RESET_PAGE_RESET_BTN.click();
        sleep(1000);
       RESET_PAGE_ERROR.shouldHaveSize(1);
       $(byText(ERROR_MSG_INVALID_EMAIL)).shouldBe(visible);
    }
    @Test
    public void resetPassword_A() {
        REDIRECT_LINK = null;
        rootLogger.info("Open URL - " + URL_ResetPassword);
        StepsHttpAuth openHost = new StepsHttpAuth();
        String AUTH_URL = URL_ResetPassword;
        openHost.httpAuthUrl(AUTH_URL);
        sleep(1000);
       RESET_PAGE_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
       RESET_PAGE_EMAIL.sendKeys(User4.GMAIL_EMAIL.getValue());
       RESET_PAGE_RESET_BTN.click();
        sleep(1000);
       RESET_PAGE_SUCCESS.shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
        String testSuccessMsg =RESET_PAGE_SUCCESS.getText();
        rootLogger.info(testSuccessMsg + " displayed, valid email submitted");
        StepsExternal loginGmailInboxApp = new StepsExternal();
        loginGmailInboxApp.checkInboxEmail(GMAIL_LOGIN, GMAIL_PASSWORD,EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
        rootLogger.info("Email and links correspond requirements");
    }
    @Test
    public void resetPassword_B() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
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
    public void resetPassword_C() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test");
           NEWPASSWORD_PAGE_RESTORE_BTN.waitUntil(visible, 10000).click();
            $$(RESET_PAGE_ERROR).filter(visible).shouldHave(size(2));
            $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
            rootLogger.info("User submitted blank form - "+ERROR_MSG_REQUIRED_FIELD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Test
    public void resetPassword_D() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test");
           NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys("12345");
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys("67890");
           NEWPASSWORD_PAGE_RESTORE_BTN.click();
            $$(RESET_PAGE_ERROR).filter(visible).shouldHave(size(1));
            $(byText(ERROR_MSG_NOT_MATCHED_PASSWORD)).shouldBe(visible);
            rootLogger.info("User submitted different passwords - "+ERROR_MSG_NOT_MATCHED_PASSWORD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Test
    public void resetPassword_E() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test");
           NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(User1.GMAIL_EMAIL.getValue());
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(User1.GMAIL_EMAIL.getValue());
           NEWPASSWORD_PAGE_RESTORE_BTN.click();
            $$(RESET_PAGE_ERROR).filter(visible).shouldHave(size(2));
            $(byText(ERROR_MSG_FAMILIAR_TO_EMAIL_PASSWORD)).shouldBe(visible);
            $(byText(ERROR_MSG_WEAK_PASSWORD)).shouldBe(visible);
            rootLogger.info("User submitted familiar to email passwords - "+ERROR_MSG_FAMILIAR_TO_EMAIL_PASSWORD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_F() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Start test - "+"User submitted invalid password");
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);

            rootLogger.info("Validation Loop");
            for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
               NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(arrayInvalidPasswords[arrayLength]);
               NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(arrayInvalidPasswords[arrayLength]);
               NEWPASSWORD_PAGE_RESTORE_BTN.click();
                $$(RESET_PAGE_ERROR).filter(visible);
                refresh();
                sleep(500);
            }
            rootLogger.info("Validation Loop - passed");
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Test
    public void resetPassword_G() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test - name");
           NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(User4.NAME.getValue());
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(User4.NAME.getValue());
           NEWPASSWORD_PAGE_RESTORE_BTN.click();
            $$(RESET_PAGE_ERROR).filter(visible).shouldHave(size(3));
            $(byText("The password is too similar to the first name.")).shouldBe(visible);
            rootLogger.info("User submitted own Username to email passwords - "+"The password is too similar to the first name.");
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Test
    public void resetPassword_H() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test");
           NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(User4.SURNAME.getValue());
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(User4.SURNAME.getValue());
           NEWPASSWORD_PAGE_RESTORE_BTN.click();
            $$(RESET_PAGE_ERROR).filter(visible).shouldHave(size(2));
            $(byText("The password is too similar to the last name.")).shouldBe(visible);
            rootLogger.info("User submitted own Surname to email passwords - "+"The password is too similar to the last name.");
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Test
    public void resetPassword_I() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test");
            String RANDOM_129_LETTER = Utils.getRandomString(129);
           NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(RANDOM_129_LETTER);
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(RANDOM_129_LETTER);
           NEWPASSWORD_PAGE_RESTORE_BTN.click();
            $$(RESET_PAGE_ERROR).filter(visible).shouldHave(size(1));
            $(byText("Ensure this value has at most 128 characters (it has 129).")).shouldBe(visible);
            rootLogger.info("Max length validation present - "+ERROR_MSG_FAMILIAR_TO_EMAIL_PASSWORD);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }
    @Test
    public void resetPassword_P() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);
            rootLogger.info("Positive test");
            //need random passwrord +constant
            String RANDOM_8_LETTER = Utils.getRandomString(8);
            NEW_PASSWORD = VALID_PASSWORD+RANDOM_8_LETTER;
           NEWPASSWORD_PAGE_NEW_PASSWORD.shouldBe(Condition.visible).sendKeys(NEW_PASSWORD);
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(NEW_PASSWORD);
           NEWPASSWORD_PAGE_RESTORE_BTN.click();

            $(byText(RESET_PAGE_FINISHED_TITLE)).waitUntil(visible, 10000);
           RESET_PAGE_FINISHED_BTN_LOGIN.shouldBe(visible);
            String thisUrl = url();
            assertEquals(thisUrl, URL_ResetPasswordComplete);
            rootLogger.info("User submitted valid credentials");
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_Q() {
        if (NEW_PASSWORD != null) {
            rootLogger.info("Open URL - " +URL_Dashboard);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = URL_Dashboard;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);

            StepsPekama loginWithNewPassword = new StepsPekama();
            String USER_PEKAMA_PASSWORD = NEW_PASSWORD;
            loginWithNewPassword.submitLoginCredentials(PEKAMA_USER_EMAIL,USER_PEKAMA_PASSWORD);
                sleep(3000);
                String thisUrl = url();
                assertEquals(thisUrl, URL_Dashboard);
        rootLogger.info("Login into Pekama with NEW valid credentials");
        }
        else Assert.fail("password - "+NEW_PASSWORD);
    }

    @Test
    public void resetPassword_S() {
        if (NEW_PASSWORD != null) {
            REDIRECT_LINK = null;
            rootLogger.info("User password - " +NEW_PASSWORD);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = URL_ResetPassword;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);
           RESET_PAGE_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
           RESET_PAGE_EMAIL.sendKeys(User4.GMAIL_EMAIL.getValue());
           RESET_PAGE_RESET_BTN.click();
            sleep(1000);
           RESET_PAGE_SUCCESS.shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
            String testSuccessMsg = RESET_PAGE_SUCCESS.getText();
            rootLogger.info(testSuccessMsg + " displayed, valid email submitted");
            StepsExternal loginGmailInboxApp = new StepsExternal();

            loginGmailInboxApp.checkInboxEmail(GMAIL_LOGIN, GMAIL_PASSWORD,EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
            rootLogger.info("Email and links correspond requirements");

            open(REDIRECT_LINK);
           NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(NEW_PASSWORD);
           NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(NEW_PASSWORD);
           NEWPASSWORD_PAGE_RESTORE_BTN.click();
            $$(byText(ERROR_MSG_NEW_PASSOWRD_EQUALS_TO_OLD)).shouldHaveSize(1);
            rootLogger.info("Validation old password present");
        }
        else Assert.fail("password - "+NEW_PASSWORD);
    }
    @Ignore("stub")
    @Test
    public void resetPassword_Z() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            StepsHttpAuth openHost = new StepsHttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthUrl(AUTH_URL);
            sleep(1000);

            rootLogger.info("Validation test - link was used");
            $(byText(FAILED_RESET_TITLE_TEXT)).waitUntil(visible, 10000);
            rootLogger.info("Page Title - "+FAILED_RESET_TITLE_TEXT);
            rootLogger.info(REDIRECT_LINK);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);

    }



}
