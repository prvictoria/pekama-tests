package com.pekama.app;
import Steps.ExternalSteps;
import Utils.Utils;
import com.codeborne.selenide.Condition;
import com.pekama.app.draft.LoginGmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;


import java.util.Random;

import static Page.DirectLinks.*;
import static Page.PekamaEmails.*;
import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrlConfiguration.*;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Viachaslau_Balashevi on 1/3/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaResetPassword {
    private String RANDON_8_LETTER = "";
    public static String SELECT_HOST = PEKAMA;
    static final Logger logging = LogManager.getLogger(LoginGmail.class);
    String actualBackLink;


    @Test
    public void openResetPassword() {
        open(urlLogIn);
        sleep(1000);
        $(byXpath(lOGIN_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
        $(byXpath(LINK_FORGOT_PASSWORD)).click();
        sleep(1000);
    }
    @Test //invalid email
    public void invalidEmailResetPassword() {
        open(urlResetPassword);
        sleep(1000);
        $(byXpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        $(byXpath(RESET_PAGE_EMAIL)).sendKeys("123@123");
        $(byXpath(RESET_PAGE_RESET_BTN)).click();
        sleep(1000);
        $(byXpath(RESET_PAGE_ERROR)).shouldBe(Condition.visible).shouldHave(Condition.text(ERROR_MSG_INVALID_EMAIL));
    }
    @Test
    public void validEmailResetPassword_A() {
        String GMAIL_LOGIN = USER_01_EMAIL;
        String GMAIL_PASSWORD = USER_GMAIL_PASSWORD;
        String EMAIL_SUBJECT = EMAIL_RESET_PASSWORD_SUBJECT;
        String EMAIL_TITLE = EMAIL_RESET_PASSWORD_TITLE;
        String EMAIL_TEXT = EMAIL_RESET_PASSWORD_TEXT;
        String EMAIL_BTN = EMAIL_RESET_PASSWORD_BTN;
        String EMAIL_REDIRECT_LINK = EMAIL_RESET_PASSWORD_BACKLINK;
        logging.info("Open URL - "+urlResetPassword);
        open(urlResetPassword);
        sleep(1000);
        $(byXpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        $(byXpath(RESET_PAGE_EMAIL)).sendKeys(USER_01_EMAIL);
        $(byXpath(RESET_PAGE_RESET_BTN)).click();
        sleep(1000);
        $(byXpath(RESET_PAGE_SUCCESS)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
        String testSuccessMsg = $(byXpath(RESET_PAGE_SUCCESS)).getText();
        logging.info(testSuccessMsg+ " displayed, valid email submitted");

        ExternalSteps loginGmailInboxApp = new ExternalSteps();
        loginGmailInboxApp.signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        logging.info("Inbox Email opened");
        actualBackLink = loginGmailInboxApp.checkInboxEmail(EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK, EMAIL_SUBJECT);
        logging.info("Reset Mail detected");
        logging.info("Email and links correspond requirements");
//        actualBackLink = null; //Test assert fail link
        if (actualBackLink != null) {
            logging.info("User followed reset link");
            open(actualBackLink);

            logging.info("Set up new password page opened");
            $(byXpath(NEWPASSWORD_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(NEWPASSWORD_TITLE_TEXT));
            $$(byText(NEWPASSWORD_TEXT)).filter(visible).shouldHave(size(1));
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).shouldBe(Condition.visible).shouldHave(value(""));
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).shouldHave(value(""));
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).shouldBe(Condition.visible);
            logging.info("Set up new password page - contain all elements");

            logging.info("Validation test");
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
            $$(byXpath(RESET_PAGE_ERROR)).filter(visible).shouldHave(size(2));
            $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
            logging.info("User submitted blank form - "+ERROR_MSG_REQUIRED_FIELD);

            logging.info("Validation test");
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).shouldBe(Condition.visible).sendKeys("12345");
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).sendKeys("67890");
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
            $$(byXpath(RESET_PAGE_ERROR)).filter(visible).shouldHave(size(1));
            $(byText(ERROR_MSG_NOT_MATCHED_PASSWORD)).shouldBe(visible);
            logging.info("User submitted different passwords - "+ERROR_MSG_NOT_MATCHED_PASSWORD);

            logging.info("Validation test");
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).shouldBe(Condition.visible).sendKeys(User1.GMAIL_EMAIL.getValue());
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).sendKeys(User1.GMAIL_EMAIL.getValue());
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
            $$(byXpath(RESET_PAGE_ERROR)).filter(visible).shouldHave(size(2));
            $(byText(ERROR_MSG_FAMILIAR_TO_EMAIL_PASSWORD)).shouldBe(visible);
            $(byText(ERROR_MSG_WEAK_PASSWORD)).shouldBe(visible);
            logging.info("User submitted familiar to email passwords - "+ERROR_MSG_FAMILIAR_TO_EMAIL_PASSWORD);

            logging.info("Validation test");
            logging.info("User submitted invalid credentials");

            logging.info("Positive test");
            //need random passwrord +constant
            String RANDOM_8_LETTER = Utils.getRandomString(8);
            $(byXpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).shouldBe(Condition.visible).sendKeys(VALID_PASSWORD+RANDOM_8_LETTER);
            $(byXpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).sendKeys(VALID_PASSWORD+RANDOM_8_LETTER);
            $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
            logging.info("User submitted valid credentials");
            logging.info("User logged with new credentials");

//            logging.info("Validation test - link was used");
//            open(actualBackLink);
//            $(byText(FAILED_RESET_TITLE_TEXT)).shouldBe(visible);
//            $(byText(FAILED_RESET_TEXT)).shouldBe(visible);
//            logging.info("Restoration password link expired - Title "+FAILED_RESET_TITLE_TEXT);
        }
        else Assert.fail("Redirect Link not found");
    }
    @Ignore
    @Test
    public void validEmailResetPassword_B() {
        if (actualBackLink != null) {
            open(actualBackLink);
        }
        logging.info("Validation test");
        $(byXpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();
        $$(byXpath(RESET_PAGE_ERROR)).filter(visible).shouldHave(size(2));
        $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
        logging.info("User submitted blank form - "+ERROR_MSG_REQUIRED_FIELD);

        logging.info("Test above failed");
    }
    @Ignore
    @Test
    public void testPassed() {
        String RANDOM_8_LETTER = Utils.getRandomString(8);
        System.out.println(RANDOM_8_LETTER);
        logging.info("Test obove passed - "+RANDOM_8_LETTER);
    }

}
