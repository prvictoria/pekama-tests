/**
 * Created by VatslauX on 29-Dec-16.
 */
package com.pekama.app;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.*;
import static Page.CommunityWizard.*;
import static Page.TestsUrl.*;
import static Page.ModalWindows.CSS_SelectHighlighted;
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
    String userEmail = User2.GMAIL_EMAIL.getValue();
    @Before
    public void before() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = true;
        open(COMMUNITY_DASHBOARD);
    }
//    @After
//    public void after() {
//        open("");
//    }

    @Test
    public void checkDashboardGui() {
        COMMUNITY_HEADER_LOGO.shouldBe(Condition.visible);
        COMMUNITY_HEADER_MANAGEMENT.shouldBe(Condition.visible);
        COMMUNITY_HEADER_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        rootLogger.info("All elements in Header present on default screen");
        COMMUNITY_TAB_Supplier.shouldBe(Condition.visible).shouldHave(Condition.text("find a supplier"));
        COMMUNITY_TAB_Incoming.shouldBe(Condition.visible).shouldHave(Condition.text("incoming cases"));
        COMMUNITY_TAB_Outgoing.shouldBe(Condition.visible).shouldHave(Condition.text("outgoing cases"));
        COMMUNITY_TAB_Profile.shouldBe(Condition.visible).shouldHave(Condition.text("become a supplier"));
        rootLogger.info("Tabs names correct and user isn`t logged in");
    }

    @Test
    public void redirectBackFromHeaderLogin() {
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible).click();
        sleep(1500);
        $(loginField_Email).sendKeys(userEmail);
        $(loginField_Password).sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(COMMUNITY_WIZARD, urlAfterLogin);
        rootLogger.info("User redirected back to Wizard");
        COMMUNITY_HEADER_UserDropdown.click();
        COMMUNITY_HEADER_LogOut.shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");
    }

    @Test
    public void checkWizardLoginRedirect() {
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        COMMUNITY_TAB_Supplier.shouldBe(Condition.visible).shouldHave(Condition.text("find a supplier")).click();
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(disabled);
        COMMUNITY_SELECT_CaseType.click();
        COMMUNITY_INPUT_CaseType.sendKeys("patent");
        CSS_SelectHighlighted.click();
        COMMUNITY_SELECT_Defining.click();
        COMMUNITY_INPUT_Defining.sendKeys("united kingdom");
        CSS_SelectHighlighted.click();
        WIZARD_BTN_GetStarted.click();
        rootLogger.info("All elements in STEP 1 displayed for Guest user");
        //Refactor Xpath
        WIZARD_BTN_YES.shouldBe(Condition.visible);
        WIZARD_BTN_NO.click();
        WIZARD_BTN_NEXT.shouldBe(Condition.visible).click();
        rootLogger.info("All elements in STEP 2 displayed for Guest user");
        sleep(2000);
//        COMMUNITY_INNRER_BTN_SIGNUP)).shouldBe(Condition.visible);
//        COMMUNITY_INNRER_BTN_LOGIN)).shouldBe(Condition.visible);
        rootLogger.info("All elements in STEP 3 displayed for Guest user");
        rootLogger.info("All elements in Wizard Tab displayed for Guest user");

        COMMUNITY_INNRER_BTN_LOGIN.click();
        sleep(1500);
        loginField_Email.sendKeys(userEmail);
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
//        assertEquals(COMMUNITY_PROFILE, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        COMMUNITY_HEADER_UserDropdown.click();
        COMMUNITY_HEADER_LogOut.shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");
    }

    @Test
    public void checkOutgoingLoginRedirect() {
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        COMMUNITY_TAB_Outgoing.shouldBe(Condition.visible).shouldHave(Condition.text("outgoing cases")).click();
        COMMUNITY_INNRER_BTN_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible);
        rootLogger.info("All elements in Outgoing Tab displayed for Guest user");

        COMMUNITY_INNRER_BTN_LOGIN.click();
        sleep(1500);
        loginField_Email.sendKeys(userEmail);
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(COMMUNITY_OUTGOING, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        COMMUNITY_HEADER_UserDropdown.click();
        COMMUNITY_HEADER_LogOut.shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");

    }
    @Test
    public void checkIncomingLoginRedirect() {
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        COMMUNITY_TAB_Incoming.shouldBe(Condition.visible).shouldHave(Condition.text("incoming cases")).click();
        COMMUNITY_INNRER_BTN_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible);
        rootLogger.info("All elements in Incoming Tab displayed for Guest user");

        COMMUNITY_INNRER_BTN_LOGIN.click();
        sleep(1500);
        loginField_Email.sendKeys(userEmail);
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(COMMUNITY_INCOMING, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        COMMUNITY_HEADER_UserDropdown.click();
        COMMUNITY_HEADER_LogOut.shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");

    }

    @Test
    public void checkProfileLoginRedirect() {
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        COMMUNITY_TAB_Profile.shouldBe(Condition.visible).shouldHave(Condition.text("become a supplier")).click();
        COMMUNITY_INNRER_BTN_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible);
        rootLogger.info("All elements in Profile Tab displayed for Guest user");

        COMMUNITY_INNRER_BTN_LOGIN.click();
        sleep(1500);
        loginField_Email.sendKeys(userEmail);
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1500);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(COMMUNITY_PROFILE, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        COMMUNITY_HEADER_UserDropdown.click();
        COMMUNITY_HEADER_LogOut.shouldBe(Condition.visible).click();
        sleep(1500);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(COMMUNITY+"/", urlAfterLogout);
        rootLogger.info("User logged out");
        rootLogger.info("Test Passed");
    }
}
