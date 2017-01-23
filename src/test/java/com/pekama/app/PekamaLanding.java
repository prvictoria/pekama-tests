package com.pekama.app;
import Utils.HttpAuth;
import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;

import static Page.PekamaLanding.*;
import static Page.PekamaLogin.*;
import static Page.PekamaSignUp.*;
import static Page.TestsUrlConfiguration.*;
import static Page.TestsCredentials.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PekamaLanding {
    @Before
    public void openUrlLogin() {
        HttpAuth openHost = new HttpAuth();
        String AUTH_URL = PEKAMA;
        openHost.httpAuthUrl(AUTH_URL);
    }
    @Test //GUI
    public void openLandingGui() {
        BTN_LOGIN.shouldBe(Condition.visible);
        BTN_SIGN_UP.shouldBe(Condition.visible);
        BTN_ABOUT.shouldBe(Condition.visible);
        BTN_BENEFITS.shouldBe(Condition.visible);
        BTN_VIDEO.shouldBe(Condition.visible);
        BTN_TEAM.shouldBe(Condition.visible);
        FIELD_EMAIL.shouldBe(Condition.visible).shouldHave(Condition.value(""));
        BTN_TRY_IT.shouldBe(Condition.visible);
  }
    @Test //Goto login page
    public void openLoginPage() {
        BTN_LOGIN.shouldBe(Condition.visible).click();
        sleep(1000);
        lOGIN_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
    }
    @Test //Goto signup page
    public void openSignupPage() {
        BTN_SIGN_UP.shouldBe(Condition.visible).click();
        sleep(1000);
        SIGN_UP_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(SIGN_UP_TITLE_TEXT));
    }
    @Test //Goto Tty it - email is prepopulated
    public void openSignupPageWithEmail() {
        FIELD_EMAIL.shouldBe(Condition.visible).sendKeys("12345@email.com");
        BTN_TRY_IT.shouldBe(Condition.visible).click();
        sleep(1000);
        SIGN_UP_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(SIGN_UP_TITLE_TEXT));
        signupEmail.shouldBe(Condition.visible).shouldHave(Condition.value("12345@email.com"));
    }
    @Test //Open landing after login
    public void openLandingAfterLogin() {
        BTN_LOGIN.shouldBe(Condition.visible).click();
        sleep(1000);
        lOGIN_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
        loginField_Email.sendKeys(User1.GMAIL_EMAIL.getValue());
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        open(PEKAMA);
        BTN_DASHBOARD.shouldBe(Condition.visible);
        BTN_LOG_OUT.shouldBe(Condition.visible).click();
        sleep(1000);
        $(BTN_TRY_IT).shouldBe(Condition.visible);
    }
}
