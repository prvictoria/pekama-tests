package com.pekama.app;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.*;
import static Page.CommunityLanding.*;
import static Page.PekamaLogin.loginButton_Login;
import static Page.PekamaSignUp.*;
import static Page.TestsUrlConfiguration.*;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by VatslauX on 27-Dec-16.
 */
public class TestsCommynityLanding {
    static final Logger rootLogger = LogManager.getRootLogger();
    @Before
    public void before() {
        open(COMMUNITY);
    }
//    @After
//    public void after() {
//        open("");
//    }

    @Test
    public void checkGui() {
        sleep(2000);
        LANDING_ABOUT.shouldBe(Condition.visible);
        LANDING_WHY.shouldBe(Condition.visible);
        LANDING_SIGNUP.shouldBe(Condition.visible);
        LANDING_LOGIN.shouldBe(Condition.visible);
        LANDING_EXPLORE_UPPER.shouldBe(Condition.visible);
        LANDING_EXPLORE_FOOTER.shouldBe(Condition.visible);
        rootLogger.info("Community landing elements checked and present");
    }
    @Test
    public void openSingUp() {
        LANDING_SIGNUP.shouldBe(Condition.visible).click();
        sleep(2000);
        $(signupAgree).shouldBe(visible).shouldNot(selected);
        $(signupNext).shouldBe(visible).shouldBe(disabled);
        rootLogger.info("Sign up page was opened");
    }
    @Test
    public void openLogIn() {
        LANDING_LOGIN.shouldBe(Condition.visible).click();
        sleep(2000);
        loginButton_Login.shouldBe(visible);
        rootLogger.info("Login page was opened");
    }
    @Test
    public void clickExploreUpper() {
        LANDING_EXPLORE_UPPER.shouldBe(Condition.visible).click();
        sleep(2000);
        COMMUNITY_HEADER_MANAGEMENT.shouldBe(visible);
        rootLogger.info("Community Wizard page was opened");
    }
    @Test
    public void clickExploreFooter() {
        LANDING_EXPLORE_FOOTER.shouldBe(Condition.visible).click();
        sleep(2000);
        COMMUNITY_HEADER_MANAGEMENT.shouldBe(visible);
        rootLogger.info("Community Wizard page was opened");
    }
}