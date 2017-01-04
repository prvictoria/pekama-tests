package com.pekama.app;

import com.codeborne.selenide.Condition;
import com.pekama.app.draft.LoginGmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

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
    @Test //valid
    public void validEmailResetPassword() {
        open(urlResetPassword);
        sleep(1000);
        $(By.xpath(RESET_PAGE_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
        $(By.xpath(RESET_PAGE_EMAIL)).sendKeys("123@123.com");
        $(By.xpath(RESET_PAGE_RESET_BTN)).click();
        sleep(1000);
        $(By.xpath(RESET_PAGE_SUCCESS)).shouldBe(Condition.visible).shouldHave(Condition.text(RESET_PAGE_SUCCESS_MSG));

        //Check gmail

        //follow link
        //Set up new password
        //login with new password

    }

}
