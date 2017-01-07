/**
 * Created by VatslauX on 29-Dec-16.
 */
package com.pekama.app;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.*;
import static Page.CommunityWizard.*;
import static Page.DirectLinks.*;
import static Page.ModalWindows.genericSelectHighlighted;
import static Page.PekamaLogin.*;
import static Page.TestsUrlConfiguration.*;
import static Page.TestsCredentials.*;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
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
        $(loginField_Email).sendKeys(USER_01_EMAIL);
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
        rootLogger.info("Test Passed");
    }
//    @Ignore
    @Test
    public void checkWizardLoginRedirect() {
        $(By.xpath(COMMUNITY_HEADER_LOGIN)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_TAB_Supplier)).shouldBe(Condition.visible).shouldHave(Condition.text("find a supplier")).click();
        $(By.xpath(WIZARD_BTN_GetStarted)).shouldBe(visible).shouldBe(disabled);
        $(By.xpath(COMMUNITY_SELECT_CaseType)).click();
        $(By.xpath(COMMUNITY_INPUT_CaseType)).sendKeys("patent");
        $(genericSelectHighlighted).click();
        $(By.xpath(COMMUNITY_SELECT_Defining)).click();
        $(By.xpath(COMMUNITY_INPUT_Defining)).sendKeys("united kingdom");
        $(genericSelectHighlighted).click();
        $(By.xpath(WIZARD_BTN_GetStarted)).click();
        rootLogger.info("All elements in STEP 1 displayed for Guest user");
        //Refactor Xpath
        $(By.xpath(WIZARD_BTN_YES)).shouldBe(Condition.visible);
        $(By.xpath(WIZARD_BTN_NO)).click();
        $(By.xpath(WIZARD_BTN_NEXT)).shouldBe(Condition.visible).click();
        rootLogger.info("All elements in STEP 2 displayed for Guest user");
        sleep(2000);
//        $(By.xpath(COMMUNITY_INNRER_BTN_SIGNUP)).shouldBe(Condition.visible);
//        $(By.xpath(COMMUNITY_INNRER_BTN_LOGIN)).shouldBe(Condition.visible);
//        rootLogger.info("All elements in STEP 3 displayed for Guest user");
//        rootLogger.info("All elements in Wizard Tab displayed for Guest user");

        $(By.xpath(COMMUNITY_INNRER_BTN_LOGIN)).click();
        sleep(1500);
        $(loginField_Email).sendKeys(USER_01_EMAIL);
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
//        assertEquals(COMMUNITY_PROFILE, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        $(By.xpath(COMMUNITY_HEADER_UserDropdown)).click();
        $(By.xpath(COMMUNITY_HEADER_LogOut)).shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");

    }
    @Test
    public void checkOutgoingLoginRedirect() {
        $(By.xpath(COMMUNITY_HEADER_LOGIN)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_TAB_Outgoing)).shouldBe(Condition.visible).shouldHave(Condition.text("outgoing cases")).click();
        $(By.xpath(COMMUNITY_INNRER_BTN_SIGNUP)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_INNRER_BTN_LOGIN)).shouldBe(Condition.visible);
        rootLogger.info("All elements in Outgoing Tab displayed for Guest user");

        $(By.xpath(COMMUNITY_INNRER_BTN_LOGIN)).click();
        sleep(1500);
        $(loginField_Email).sendKeys(USER_01_EMAIL);
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(COMMUNITY_OUTGOING, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        $(By.xpath(COMMUNITY_HEADER_UserDropdown)).click();
        $(By.xpath(COMMUNITY_HEADER_LogOut)).shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");

    }
    @Test
    public void checkIncomingLoginRedirect() {
        $(By.xpath(COMMUNITY_HEADER_LOGIN)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_TAB_Incoming)).shouldBe(Condition.visible).shouldHave(Condition.text("incoming cases")).click();
        $(By.xpath(COMMUNITY_INNRER_BTN_SIGNUP)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_INNRER_BTN_LOGIN)).shouldBe(Condition.visible);
        rootLogger.info("All elements in Incoming Tab displayed for Guest user");

        $(By.xpath(COMMUNITY_INNRER_BTN_LOGIN)).click();
        sleep(1500);
        $(loginField_Email).sendKeys(USER_01_EMAIL);
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(COMMUNITY_INCOMING, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        $(By.xpath(COMMUNITY_HEADER_UserDropdown)).click();
        $(By.xpath(COMMUNITY_HEADER_LogOut)).shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");

    }
    @Test
    public void checkProfileLoginRedirect() {
        $(By.xpath(COMMUNITY_HEADER_LOGIN)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_TAB_Profile)).shouldBe(Condition.visible).shouldHave(Condition.text("become a supplier")).click();
        $(By.xpath(COMMUNITY_INNRER_BTN_SIGNUP)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_INNRER_BTN_LOGIN)).shouldBe(Condition.visible);
        rootLogger.info("All elements in Profile Tab displayed for Guest user");

        $(By.xpath(COMMUNITY_INNRER_BTN_LOGIN)).click();
        sleep(1500);
        $(loginField_Email).sendKeys(USER_01_EMAIL);
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(COMMUNITY_PROFILE, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        $(By.xpath(COMMUNITY_HEADER_UserDropdown)).click();
        $(By.xpath(COMMUNITY_HEADER_LogOut)).shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");

    }
}
