package com.pekama.app;
import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.PekamaLanding.*;
import static Page.PekamaLogin.*;
import static Page.PekamaSignUp.*;
import static Page.RunConfig.PEKAMA;
import static Page.TestData.USER_EMAIL_01;
import static Page.TestData.USER_PEKAMA_PASSWORD;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
/**
 * Created by VatslauX on 27-Dec-16.
 */
public class PekamaLanding {
    @Before
    public void openUrlLogin() {
        open(PEKAMA);
    }
    @Test //GUI
    public void openLandingGui() {
        $(By.xpath(BTN_LOGIN)).shouldBe(Condition.visible);
        $(By.xpath(BTN_SIGN_UP)).shouldBe(Condition.visible);
        $(By.xpath(BTN_ABOUT)).shouldBe(Condition.visible);
        $(By.xpath(BTN_BENEFITS)).shouldBe(Condition.visible);
        $(By.xpath(BTN_VIDEO)).shouldBe(Condition.visible);
        $(By.xpath(BTN_TEAM)).shouldBe(Condition.visible);
        $(FIELD_EMAIL).shouldBe(Condition.visible).shouldHave(Condition.value(""));
        $(BTN_TRY_IT).shouldBe(Condition.visible);
  }
    @Test //Goto login page
    public void openLoginPage() {
        $(By.xpath(BTN_LOGIN)).shouldBe(Condition.visible).click();
        sleep(1000);
        $(By.xpath(lOGIN_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
    }
    @Test //Goto signup page
    public void openSignupPage() {
        $(By.xpath(BTN_SIGN_UP)).shouldBe(Condition.visible).click();
        sleep(1000);
        $(By.xpath(SIGN_UP_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(SIGN_UP_TITLE_TEXT));
    }
    @Test //Goto Tty it - email is prepopulated
    public void openSignupPageWithEmail() {
        $((FIELD_EMAIL)).shouldBe(Condition.visible).sendKeys("12345@email.com");
        $((BTN_TRY_IT)).shouldBe(Condition.visible).click();
        sleep(1000);
        $(By.xpath(SIGN_UP_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(SIGN_UP_TITLE_TEXT));
        $(signupEmail).shouldBe(Condition.visible).shouldHave(Condition.value("12345@email.com"));
    }
    @Test //Open landing after login
    public void openLandingAfterLogin() {
        $(By.xpath(BTN_LOGIN)).shouldBe(Condition.visible).click();
        sleep(1000);
        $(By.xpath(lOGIN_TITLE)).shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
        $(loginField_Email).sendKeys(USER_EMAIL_01);
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        open(PEKAMA);
        $(By.xpath(BTN_DASHBOARD)).shouldBe(Condition.visible);
        $(By.xpath(BTN_LOG_OUT)).shouldBe(Condition.visible).click();
        sleep(1000);
        $(BTN_TRY_IT).shouldBe(Condition.visible);


    }
}
