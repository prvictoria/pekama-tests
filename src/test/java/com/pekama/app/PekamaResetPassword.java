package com.pekama.app;

import Steps.ExternalSteps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.pekama.app.draft.LoginGmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;


import static Page.DirectLinks.urlLogIn;
import static Page.DirectLinks.urlResetPassword;
import static Page.PekamaEmails.*;
import static Page.PekamaEmails.EMAIL_RESET_PASSWORD_BACKLINK;
import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.TestsCredentials.USER_EMAIL_01;
import static Page.TestsCredentials.USER_GMAIL_PASSWORD;
import static Page.TestsUrlConfiguration.PEKAMA;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Viachaslau_Balashevi on 1/3/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaResetPassword {
    public static String SELECT_HOST = PEKAMA;
    static final Logger logging = LogManager.getLogger(LoginGmail.class);


    @Test
    public void openResetPassword() {
        open(urlLogIn);
        sleep(1000);
        $(By.xpath(lOGIN_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
        $(By.xpath(LINK_FORGOT_PASSWORD)).click();
        sleep(1000);
    }
    @Test //invalid email
    public void invalidEmailResetPassword() {
        open(urlResetPassword);
        sleep(1000);
        $(By.xpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        $(By.xpath(RESET_PAGE_EMAIL)).sendKeys("123@123");
        $(By.xpath(RESET_PAGE_RESET_BTN)).click();
        sleep(1000);
        $(By.xpath(RESET_PAGE_ERROR)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_ERROR_MSG));
    }
    @Test
    public void validEmailResetPassword() {
        String GMAIL_LOGIN = USER_EMAIL_01;
        String GMAIL_PASSWORD = USER_GMAIL_PASSWORD;
        String EMAIL_SUBJECT = EMAIL_RESET_PASSWORD_SUBJECT;
        String EMAIL_TITLE = EMAIL_RESET_PASSWORD_TITLE;
        String EMAIL_TEXT = EMAIL_RESET_PASSWORD_TEXT;
        String EMAIL_BTN = EMAIL_RESET_PASSWORD_BTN;
        String EMAIL_REDIRECT_LINK = EMAIL_RESET_PASSWORD_BACKLINK;
//        open(urlResetPassword);
//        sleep(1000);
//        $(By.xpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
//        $(By.xpath(RESET_PAGE_EMAIL)).sendKeys(USER_EMAIL_01);
//        $(By.xpath(RESET_PAGE_RESET_BTN)).click();
//        sleep(1000);
//        $(By.xpath(RESET_PAGE_SUCCESS)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
//        String testSuccessMsg = $(By.xpath(RESET_PAGE_SUCCESS)).getText();
//        logging.info(testSuccessMsg+ " displayed, valid email submitted");

        ExternalSteps loginGmailInboxApp = new ExternalSteps();
        loginGmailInboxApp.signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        logging.info("Inbox Email opened");
        loginGmailInboxApp.checkInboxEmail(EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK, EMAIL_SUBJECT);
        logging.info("Reset Mail detected");
        logging.info("Email and links correspond requirements");
        logging.info("User followed reset link");

        logging.info("Set up new password page opened");
        $(By.xpath(NEWPASSWORD_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(NEWPASSWORD_TITLE_TEXT));
        $$(byText(NEWPASSWORD_TEXT)).filter(visible).shouldHave(size(1));
        $(By.xpath(NEWPASSWORD_PAGE_NEW_PASSWORD)).shouldBe(Condition.visible).shouldHave(value(""));
        $(By.xpath(NEWPASSWORD_PAGE_CONFIRM_PASSWORD)).shouldBe(Condition.visible).shouldHave(value(""));
        $(By.xpath(NEWPASSWORD_PAGE_RESTORE_BTN)).shouldBe(Condition.visible);
        logging.info("Set up new password page - contain all elements");
        $(By.xpath(NEWPASSWORD_PAGE_RESTORE_BTN)).click();

        logging.info("User submitted blank form");
//
//        logging.info("User submitted invalid credentials");
//
//        logging.info("User submitted valid credentials");
//
//        logging.info("User logged with new credentials");

    }
    @Ignore
    @Test
    public void testFailed() {


        logging.info("Test obove failed");
    }
    @Ignore
    @Test
    public void testPassed() {

        logging.info("Test obove passed");
    }

}
