package com.pekama.app;
import Steps.ExternalSteps;
import Steps.PekamaSteps;
import Utils.*;
import com.codeborne.selenide.Condition;
import com.pekama.app.draft.LoginGmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;


import static Page.TestsUrl.*;
import static Page.Emails.*;
import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrlConfiguration.*;
import static Steps.ExternalSteps.REDIRECT_LINK;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaResetPassword {
    private String RANDON_8_LETTER = "";
    public static String SELECT_HOST = PEKAMA;
    static final Logger rootLogger = LogManager.getLogger(LoginGmail.class);
    public static String NEW_PASSWORD = null;
    String GMAIL_LOGIN = User4.GMAIL_EMAIL.getValue();
    String GMAIL_PASSWORD = User4.GMAIL_PASSWORD.getValue();
    String EMAIL_SUBJECT = EMAIL_RESET_PASSWORD_SUBJECT;
    String EMAIL_TITLE = EMAIL_RESET_PASSWORD_TITLE;
    String EMAIL_TEXT = EMAIL_RESET_PASSWORD_TEXT;
    String EMAIL_BTN = EMAIL_RESET_PASSWORD_BTN;
    String EMAIL_REDIRECT_LINK = EMAIL_RESET_PASSWORD_BACKLINK;
    String PEKAMA_USER_EMAIL = User4.GMAIL_EMAIL.getValue();

    @Ignore
    @Test
    public void openResetPassword() {
        rootLogger.info("Open URL - " +urlLogIn);
        HttpAuth openHost = new HttpAuth();
        String AUTH_URL = urlLogIn;
        openHost.httpAuthWhithCustomLink(AUTH_URL);
        sleep(1000);
        $(byXpath(lOGIN_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
        $(byXpath(LINK_FORGOT_PASSWORD)).click();
        sleep(1000);
    }
    @Ignore
    @Test
    public void invalidEmailResetPassword() {
        rootLogger.info("Open URL - " +urlResetPassword);
        HttpAuth openHost = new HttpAuth();
        String AUTH_URL = urlResetPassword;
        openHost.httpAuthWhithCustomLink(AUTH_URL);
        sleep(1000);
        $(byXpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        $(byXpath(RESET_PAGE_EMAIL)).sendKeys("123@123");
        $(byXpath(RESET_PAGE_RESET_BTN)).click();
        sleep(1000);
        $(byXpath(RESET_PAGE_ERROR)).shouldBe(Condition.visible).shouldHave(Condition.text(ERROR_MSG_INVALID_EMAIL));
    }
    @Test
    public void resetPassword_A() {
        REDIRECT_LINK = null;
        rootLogger.info("Open URL - " + urlResetPassword);
        HttpAuth openHost = new HttpAuth();
        String AUTH_URL = urlResetPassword;
        openHost.httpAuthWhithCustomLink(AUTH_URL);
        sleep(1000);
        $(byXpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        $(byXpath(RESET_PAGE_EMAIL)).sendKeys(User4.GMAIL_EMAIL.getValue());
        $(byXpath(RESET_PAGE_RESET_BTN)).click();
        sleep(1000);
        $(byXpath(RESET_PAGE_SUCCESS)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
        String testSuccessMsg = $(byXpath(RESET_PAGE_SUCCESS)).getText();
        rootLogger.info(testSuccessMsg + " displayed, valid email submitted");
        ExternalSteps loginGmailInboxApp = new ExternalSteps();
        loginGmailInboxApp.signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        rootLogger.info("Inbox Email opened");
        loginGmailInboxApp.checkInboxEmail(EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
        rootLogger.info("Email and links correspond requirements");
    }
    @Test
    public void resetPassword_B() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);
            rootLogger.info("Set up new password page opened");
            $(byXpath(NEWPASSWORD_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(NEWPASSWORD_TITLE_TEXT));
            $$(byText(NEWPASSWORD_TEXT)).filter(visible).shouldHave(size(1));
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).shouldBe(Condition.visible).shouldHave(value(""));
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).shouldHave(value(""));
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).shouldBe(Condition.visible);
            rootLogger.info("Set up new password page - contain all elements");
            }
    }
    @Test
    public void resetPassword_C() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test");
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).waitUntil(visible, 10000).click();
            $$(byXpath(RESET_PAGE_ERROR)).filter(visible).shouldHave(size(2));
            $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
            rootLogger.info("User submitted blank form - "+ERROR_MSG_REQUIRED_FIELD);
        }
    }
    @Test
    public void resetPassword_D() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test");
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).waitUntil(visible, 10000).sendKeys("12345");
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).sendKeys("67890");
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
            $$(byXpath(RESET_PAGE_ERROR)).filter(visible).shouldHave(size(1));
            $(byText(ERROR_MSG_NOT_MATCHED_PASSWORD)).shouldBe(visible);
            rootLogger.info("User submitted different passwords - "+ERROR_MSG_NOT_MATCHED_PASSWORD);
        }
    }
    @Test
    public void resetPassword_E() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test");
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).waitUntil(visible, 10000).sendKeys(User1.GMAIL_EMAIL.getValue());
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).sendKeys(User1.GMAIL_EMAIL.getValue());
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
            $$(byXpath(RESET_PAGE_ERROR)).filter(visible).shouldHave(size(2));
            $(byText(ERROR_MSG_FAMILIAR_TO_EMAIL_PASSWORD)).shouldBe(visible);
            $(byText(ERROR_MSG_WEAK_PASSWORD)).shouldBe(visible);
            rootLogger.info("User submitted familiar to email passwords - "+ERROR_MSG_FAMILIAR_TO_EMAIL_PASSWORD);
        }
    }
    @Ignore
    @Test
    public void resetPassword_F() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Start test - "+"User submitted invalid password");
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);

            rootLogger.info("Validation test");
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).waitUntil(visible, 10000).sendKeys(User1.GMAIL_EMAIL.getValue());
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).sendKeys(User1.GMAIL_EMAIL.getValue());
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
            $$(byXpath(RESET_PAGE_ERROR)).filter(visible).shouldHave(size(2));
            $(byText(ERROR_MSG_FAMILIAR_TO_EMAIL_PASSWORD)).shouldBe(visible);
            $(byText(ERROR_MSG_WEAK_PASSWORD)).shouldBe(visible);
            rootLogger.info("User submitted invalid password");
        }
    }
    @Test
    public void resetPassword_P() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);
            rootLogger.info("Positive test");
            //need random passwrord +constant
            String RANDOM_8_LETTER = Utils.getRandomString(8);
            NEW_PASSWORD = VALID_PASSWORD+RANDOM_8_LETTER;
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).shouldBe(Condition.visible).sendKeys(NEW_PASSWORD);
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).sendKeys(NEW_PASSWORD);
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();

            $(byText(RESET_PAGE_FINISHED_TITLE)).waitUntil(visible, 10000);
            $(byText(RESET_PAGE_FINISHED_TEXT)).waitUntil(visible, 10000);
            $(byXpath(RESET_PAGE_FINISHED_BTN_LOGIN)).shouldBe(visible);
            String thisUrl = url();
            assertEquals(thisUrl, urlResetPasswordComplete);
            rootLogger.info("User submitted valid credentials");
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);
    }

    @Test
    public void resetPassword_Q() {
        if (NEW_PASSWORD != null) {
            rootLogger.info("Open URL - " +urlDashboard);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = urlDashboard;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);

            PekamaSteps loginWithNewPassword = new PekamaSteps();
            String USER_PEKAMA_PASSWORD = NEW_PASSWORD;
            loginWithNewPassword.submitLoginCredentials(PEKAMA_USER_EMAIL,USER_PEKAMA_PASSWORD);
                sleep(1000);
                String thisUrl = url();
                assertEquals(thisUrl, urlDashboard);
        rootLogger.info("Login into Pekama with NEW valid credentials");
        }
        else Assert.fail("password - "+NEW_PASSWORD);
    }

    @Test
    public void resetPassword_S() {
        if (NEW_PASSWORD != null) {
            rootLogger.info("User password - " +NEW_PASSWORD);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = urlResetPassword;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);
            $(byXpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
            $(byXpath(RESET_PAGE_EMAIL)).sendKeys(User4.GMAIL_EMAIL.getValue());
            $(byXpath(RESET_PAGE_RESET_BTN)).click();
            sleep(1000);
            $(byXpath(RESET_PAGE_SUCCESS)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
            String testSuccessMsg = $(byXpath(RESET_PAGE_SUCCESS)).getText();
            rootLogger.info(testSuccessMsg + " displayed, valid email submitted");
            ExternalSteps loginGmailInboxApp = new ExternalSteps();
            loginGmailInboxApp.signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
            rootLogger.info("Inbox Email opened");
            loginGmailInboxApp.checkInboxEmail(EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
            rootLogger.info("Email and links correspond requirements");

            open(REDIRECT_LINK);
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).waitUntil(visible, 10000).sendKeys(NEW_PASSWORD);
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).sendKeys(NEW_PASSWORD);
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
            $(byText("")).waitUntil(visible, 10000); //todo
            rootLogger.info("Validation old password present");
        }
        else Assert.fail("password - "+NEW_PASSWORD);
    }
    @Ignore("stub")
    @Test
    public void resetPassword_Z() {
        if (REDIRECT_LINK != null) {
            rootLogger.info("Open URL - " +REDIRECT_LINK);
            HttpAuth openHost = new HttpAuth();
            String AUTH_URL = REDIRECT_LINK;
            openHost.httpAuthWhithCustomLink(AUTH_URL);
            sleep(1000);
            rootLogger.info("Validation test - link was used");
            $(byText(FAILED_RESET_TITLE_TEXT)).waitUntil(visible, 10000);
            rootLogger.info("Page Title - "+FAILED_RESET_TITLE_TEXT);
            rootLogger.info(REDIRECT_LINK);
        }
        else Assert.fail("Redirect Link is - "+REDIRECT_LINK);

    }



}
