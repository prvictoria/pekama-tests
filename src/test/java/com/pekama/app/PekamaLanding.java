package com.pekama.app;

import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.DirectLinks.urlSingUp;
import static Page.PekamaLanding.*;
import static Page.PekamaLogin.*;
import static Page.RunConfig.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        $(By.linkText(BTN_LOGIN)).shouldBe(Condition.visible);
        $(By.linkText(BTN_SIGN_UP)).shouldBe(Condition.visible);
        $(By.linkText(BTN_ABOUT)).shouldBe(Condition.visible);
        $(By.linkText(BTN_BENEFITS)).shouldBe(Condition.visible);
        $(By.linkText(BTN_VIDEO)).shouldBe(Condition.visible);
        $(By.linkText(BTN_TEAM)).shouldBe(Condition.visible);
        $(FIELD_EMAIL).shouldBe(Condition.visible).shouldHave(Condition.value(""));
        $(BTN_TRY_IT).shouldBe(Condition.visible);



    }
    @Test //Goto login page
    public void openLoginPage() {

    }
    @Test //Goto signup page
    public void openSignupPage() {

    }
    @Test //Goto Tty it - email is prepopulated
    public void openSignupPageWithEmail() {

    }
    @Test //Open landing after login
    public void openLandingAfterLogin() {

    }
}
