/**
 * Created by VatslauX on 29-Dec-16.
 */
package com.pekama.app;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Page.CommunityDashboard.*;
import static Page.CommunityWizard.*;
import static Page.TestsUrl.*;
import static Page.PekamaLogin.*;
import static Page.TestsUrlConfiguration.*;
import static Page.TestsCredentials.*;
import static Steps.StepsCommunity.searchExpertsQuery;
import static Steps.StepsCommunity.searchExpertsSubmit;
import static Steps.StepsCommunity.searchQueryUrl;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
public class TestsCommunityDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    String userEmail = User2.GMAIL_EMAIL.getValue();
    @Before
    public void before() {
//        Configuration test = new Configuration();
//        test.holdBrowserOpen = true;
        open(URL_COMMUNITY_DASHBOARD);
    }
    @After
    public void after() {
        open(URL_COMMUNITY_LOGOUT);
    }

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
        assertEquals(URL_COMMUNITY_WIZARD, urlAfterLogin);
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
        String caseType = CaseType.PATENT.getValue();
        String country = Countries.PITCAIRN_ISLANDS.getValue();
        searchExpertsQuery(caseType, country);
        searchExpertsSubmit();
        COMMUNITY_INNRER_BTN_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible).click();
        sleep(1500);
        loginField_Email.sendKeys(userEmail);
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(2000);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(searchQueryUrl, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");

        WIZARD_BTN_REQUEST_INSTRUCTIONS.waitUntil(visible, 10000).shouldBe(disabled);
        rootLogger.info("All elements in STEP 2 displayed for authorized user");

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
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible).click();
        rootLogger.info("All elements in Outgoing Tab displayed for Guest user");
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
        assertEquals(URL_COMMUNITY_OUTGOING, urlAfterLogin);
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
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible).click();
        rootLogger.info("All elements in Incoming Tab displayed for Guest user");
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
        assertEquals(URL_COMMUNITY_INCOMING, urlAfterLogin);
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
        COMMUNITY_TAB_Profile.shouldBe(Condition.visible).click();
//        COMMUNITY_TAB_Profile.shouldBe(Condition.visible).shouldHave(Condition.text("become a supplier")).click();
        COMMUNITY_INNRER_BTN_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible).click();
        rootLogger.info("All elements in Profile Tab displayed for Guest user");
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
        assertEquals(URL_COMMUNITY_PROFILE_TEAM, urlAfterLogin);
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
