/**
 * Created by VatslauX on 29-Dec-16.
 */
package com.pekama.app;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.*;
import static Page.DirectLinks.*;
import static Page.PekamaHeader.*;
import static Page.PekamaLogin.*;
import static Page.RunConfig.*;
import static Page.TestData.*;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommunityDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    @Before
    public void before() {
        open(COMMUNITY_DASHBOARD);
    }
//    @After
//    public void after() {
//        open("");
//    }

    @Test
    public void checkDashboardGui() {
        $(By.xpath(COMMUNITY_HEADER_LOGO)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_HEADER_MANAGEMENT)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_HEADER_SIGNUP)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_HEADER_LOGIN)).shouldBe(Condition.visible);
        rootLogger.info("All elements in Header present on default screen");
        $(By.xpath(COMMUNITY_TAB_Supplier)).shouldBe(Condition.visible).shouldHave(Condition.text("find a supplier"));
        $(By.xpath(COMMUNITY_TAB_Incoming)).shouldBe(Condition.visible).shouldHave(Condition.text("incoming cases"));
        $(By.xpath(COMMUNITY_TAB_Outgoing)).shouldBe(Condition.visible).shouldHave(Condition.text("outgoing cases"));
        $(By.xpath(COMMUNITY_TAB_Profile)).shouldBe(Condition.visible).shouldHave(Condition.text("become a supplier"));
        rootLogger.info("Tabs names correct and user isn`t logged in");
    }
    @Test
    public void redirectBackFromHeaderLogin() {
        $(By.xpath(COMMUNITY_HEADER_LOGIN)).shouldBe(Condition.visible).click();
        sleep(1500);
        $(loginField_Email).sendKeys(USER_EMAIL_01);
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(COMMUNITY_WIZARD, urlAfterLogin);
        rootLogger.info("User redirected back to Wizard");
        $(By.xpath(COMMUNITY_HEADER_UserDropdown)).click();
        $(By.xpath(COMMUNITY_HEADER_LogOut)).shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
    }
}
