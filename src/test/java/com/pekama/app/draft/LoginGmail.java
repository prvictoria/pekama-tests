package com.pekama.app.draft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.PekamaEmails.*;
import static Page.TestsCredentials.USER_EMAIL_01;
import static Page.TestsCredentials.USER_GMAIL_PASSWORD;
import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by VatslauX on 25-Dec-16.
 */
public class LoginGmail {
    static final Logger logging = LogManager.getLogger(LoginGmail.class);
    String GMAIL_LOGIN = USER_EMAIL_01;
    String GMAIL_PASSWORD = USER_GMAIL_PASSWORD;
    String testEmailSubject = EMAIL_SUBJECT_RESET_PASSWORD;
    final static String emailFirst = "//tbody/tr[@class='zA yO'][2]";
    final static String emailFirstSubject = "//span[@id=':d1']";
    String actualEmailSubject = "";
    final static public String GMAIL_INBOX_BTN = "//*[@data-tooltip='Select']/div[1]/div";


    @Test
    public void open_page() {
        logging.info("Start browser");
        open(GMAIL_URL);

        logging.info("Type email");
        $(By.name(GMAIL_LOGIN_FIELD)).sendKeys(GMAIL_LOGIN);
        logging.info("Submit email");
        $(GMAIL_NEXT_BTN).click();

        logging.info("Type password");
        $(GMAIL_PASSWORD_FIELD).shouldBe(visible).sendKeys(GMAIL_PASSWORD);
        logging.info("Submit password");
        $(GMAIL_SIGNIN_BTN).shouldBe(visible).click();
        logging.info($(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title"));

//        $(By.xpath(emailFirst));
//        $(By.xpath(emailFirst+emailFirstSubject)).shouldHave(Condition.text(emailSubject));
        //assertEquals("Inbox -ххххххххххххх@gmail.com - Gmail", title());
        $(By.xpath(GMAIL_INBOX_BTN)).find(withText("Inbox")).shouldBe(visible);
//        $$(byText("Gmail Team")).filter(visible).shouldHave(size(1));
//        $$(byText("LastPass")).filter(visible).shouldHave(size(1));
//        $$(byText("Pivotal Tracker")).filter(visible).shouldHave(size(1));
        $(byXpath("//*[@data-tooltip='Select']/div[1]/div")).click();

        $(byAttribute("selector", "none")).shouldBe(visible);
        $(byAttribute("selector", "read")).shouldBe(visible);
        $(byAttribute("selector", "unread")).shouldBe(visible).click();
        sleep(2000);


        String actualEmailSubject = $(byXpath(testEmailSubject)).getText();
        logging.info("Target full email subject -" +actualEmailSubject);
        $(byXpath(testEmailSubject)).click();
//        $(byAttribute("selector", "starred")).shouldBe(visible);
//        $(byAttribute("selector", "unstarred")).shouldBe(visible);

    };
}