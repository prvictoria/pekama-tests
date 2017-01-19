package com.pekama.app.draft;

import Steps.ExternalSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.Emails.*;

import static Page.TestsCredentials.*;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by VatslauX on 25-Dec-16.
 */
public class LoginGmail {
    static final Logger logging = LogManager.getLogger(LoginGmail.class);
    String GMAIL_LOGIN = User1.GMAIL_EMAIL.getValue();
    String GMAIL_PASSWORD = User1.GMAIL_PASSWORD.getValue();
    SelenideElement EMAIL_SUBJECT = EMAIL_RESET_PASSWORD_SUBJECT;
    String EMAIL_TITLE = EMAIL_RESET_PASSWORD_TITLE;
    String EMAIL_TEXT = EMAIL_RESET_PASSWORD_TEXT;
    String EMAIL_BTN = EMAIL_RESET_PASSWORD_BTN;
    SelenideElement EMAIL_REDIRECT_LINK = EMAIL_RESET_PASSWORD_BACKLINK;
    @Ignore
    @Test
    public void canOpenChromeApps() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
//        Configuration.baseUrl = "";
        Configuration.browser = "chrome";
        open(GMAIL_URL);
        sleep(10000);
    }
    @Ignore
    @Test
    public void open_page() {
        logging.info("Start browser");
        open(GMAIL_URL);
        logging.info("Type email");
        GMAIL_LOGIN_FIELD.sendKeys(GMAIL_LOGIN);
        logging.info("Submit email");
        $(GMAIL_NEXT_BTN).click();

        logging.info("Type password");
        $(GMAIL_PASSWORD_FIELD).shouldBe(visible).sendKeys(GMAIL_PASSWORD);
        logging.info("Submit password");
        $(GMAIL_SIGNIN_BTN).shouldBe(visible).click();
//        logging.info($(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title"));

//        $(By.xpath(emailFirst));
//        $(By.xpath(emailFirst+emailFirstSubject)).shouldHave(Condition.text(emailSubject));
        //assertEquals("Inbox -ххххххххххххх@gmail.com - Gmail", title());
//        $(By.xpath(GMAIL_INBOX_BTN)).find(withText("Inbox")).shouldBe(visible);
//        $$(byText("Gmail Team")).filter(visible).shouldHave(size(1));
//        $$(byText("LastPass")).filter(visible).shouldHave(size(1));
//        $$(byText("Pivotal Tracker")).filter(visible).shouldHave(size(1));
//        GMAIL_SELECT_MENU)).click();

//        $(byAttribute("selector", "none")).shouldBe(visible);
//        $(byAttribute("selector", "read")).shouldBe(visible);
//        $(byAttribute("selector", "unread")).shouldBe(visible).click();
        sleep(250);
        String actualEmailSubject = EMAIL_RESET_PASSWORD_SUBJECT.getText();
        logging.info("Target full email subject - " +actualEmailSubject);
        EMAIL_RESET_PASSWORD_SUBJECT.click();
        $$(byText(EMAIL_RESET_PASSWORD_TITLE)).filter(visible).shouldHave(size(1));
        $$(byText(EMAIL_RESET_PASSWORD_TEXT)).filter(visible).shouldHave(size(1));
        $$(byText(EMAIL_RESET_PASSWORD_BTN)).filter(visible).shouldHave(size(1));
        String actualBackLink = EMAIL_RESET_PASSWORD_BACKLINK.getAttribute("href");
        logging.info("Back link to Pekama - " +actualBackLink);

        GMAIL_ARCHIVE_BTN.click();
//        open(actualBackLink);

//        $(byAttribute("selector", "starred")).shouldBe(visible);
//        $(byAttribute("selector", "unstarred")).shouldBe(visible);


    }
    @Test
    public void openInbox() {
        logging.info("Start browser");
        open(INBOX_URL);
        INBOX_SIGNIN.waitUntil(visible, 10000).click();
        logging.info("Type email");
        GMAIL_LOGIN_FIELD.sendKeys(GMAIL_LOGIN);
        logging.info("Submit email");
        $(GMAIL_NEXT_BTN).click();
        logging.info("Type password");
        $(GMAIL_PASSWORD_FIELD).shouldBe(visible).sendKeys(GMAIL_PASSWORD);
        logging.info("Submit password");
        $(GMAIL_SIGNIN_BTN).shouldBe(visible).click();

        EMAIL_RESET_PASSWORD_SUBJECT.waitUntil(visible, 10000).click();
        INBOX_BTN_DONE.waitUntil(visible, 10000);
        $$(byText(EMAIL_RESET_PASSWORD_TITLE)).filter(visible).shouldHave(size(1));
        $$(byText(EMAIL_RESET_PASSWORD_TEXT)).filter(visible).shouldHave(size(1));
        $$(byText(EMAIL_RESET_PASSWORD_BTN)).filter(visible).shouldHave(size(1));
        String actualBackLink = EMAIL_RESET_PASSWORD_BACKLINK.getAttribute("href");
        logging.info("Back link to Pekama - " +actualBackLink);
        INBOX_BTN_DONE.click();
        open(actualBackLink);
    }

    @Test
    public void loginGmailTest(){
//        ExternalSteps loginGmailInboxApp = new ExternalSteps();
//        loginGmailInboxApp.signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
//        loginGmailInboxApp.checkInboxEmail(EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK, EMAIL_SUBJECT);

    }
}