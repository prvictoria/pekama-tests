package com.pekama.app.draft;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.PekamaEmails.*;
import static Page.TestsCredentials.USER_EMAIL_01;
import static Page.TestsCredentials.USER_GMAIL_PASSWORD;
import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by VatslauX on 25-Dec-16.
 */
public class LoginGmail {
    static final Logger logging = LogManager.getLogger(LoginGmail.class);
    String GMAIL_LOGIN = USER_EMAIL_01;
    String GMAIL_PASSWORD = USER_GMAIL_PASSWORD;
    final static String emailSubject = "";
    final static String emailFirst = "//tbody/tr[@class='zA yO'][2]";
    final static String emailFirstSubject = "//span[@id=':d1']";

    @Test
    public void open_page() {
        logging.info("Start browser");
        open(GMAIL_URL);

        logging.info("Type email");
        $(By.name(GMAIL_LOGIN_FIELD)).sendKeys(GMAIL_LOGIN);
        logging.info("Submit email");
        $(GMAIL_NEXT_BTN).click();

        logging.info("Type password");
        $(GMAIL_PASSWORD_FIELD).shouldBe(Condition.visible).sendKeys(GMAIL_PASSWORD);
        logging.info("Submit password");
        $(GMAIL_SIGNIN_BTN).shouldBe(Condition.visible).click();
        logging.info($(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title"));

        $(By.xpath(emailFirst));
        $(By.xpath(emailFirst+emailFirstSubject)).shouldHave(Condition.text(emailSubject));
        //assertEquals("Inbox -ххххххххххххх@gmail.com - Gmail", title());

    };
}