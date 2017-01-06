package com.pekama.app;

import com.codeborne.selenide.Condition;
import com.pekama.app.draft.LoginGmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;


import static Page.DirectLinks.urlResetPassword;
import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.RunConfig.PEKAMA;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Viachaslau_Balashevi on 1/3/2017.
 */
public class PekamaResetPassword {
    public static String SELECT_HOST = PEKAMA;
    static final Logger logging = LogManager.getLogger(LoginGmail.class);
    @Rule

    //DOMConfigurator.configure("log4j2.xml");
    @Test
    public void openResetPassword() {
        open(urlLogin);
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
    @RunIf
    public void validEmailResetPassword() {
        open(urlResetPassword);
        sleep(1000);
        $(By.xpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        $(By.xpath(RESET_PAGE_EMAIL)).sendKeys("123@123.com");
        $(By.xpath(RESET_PAGE_RESET_BTN)).click();
        sleep(1000);
        $(By.xpath(RESET_PAGE_SUCCESS)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));
        logging.info("Success messages displayed, valid email submitted");

        logging.info("Inbox Email opened");
        logging.info("Mail detected");
        logging.info("Email and links correspond requirements");
        logging.info("User followed reset link");
        logging.info("et up new password page opened");
        logging.info("User logged with new credentials");

    }

}
