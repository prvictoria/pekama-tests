package com.pekama.app;/**
 * Created by VatslauX on 09-Feb-17.
 */

import Page.TestsCredentials;
import Steps.StepsPekama;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.*;
import static Page.CommunityOutgoing.*;
import static Page.TestsCredentials.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityIncoming {
    static final Logger rootLogger = LogManager.getRootLogger();
    String expertTeam = TestsCredentials.User1.TEAM_NAME.getValue();
    String expertEmail = User1.GMAIL_EMAIL.getValue();
    String expertPassword = User1.PEKAMA_PASSWORD.getValue();
    private static String caseName;
    @Before
    public void before() {
        holdBrowserAfterTest(false);
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User2.GMAIL_EMAIL.getValue(),
                User2.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        rootLogger.info("Redirect back after login");
        rootLogger.info("Create case");
        caseName = createCase(expertTeam);

    }
    @After
    public void after() {
        open(URL_COMMUNITY_LOGOUT);
        rootLogger.info("Open URL - "+URL_COMMUNITY_LOGOUT);
    }

    @Test
    public void testA_ArchiveCase() {
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);

        rootLogger.info("Archive case");
        archiveCase(caseName);
        rootLogger.info("Test passed");
    }

    @Test
    public void testB1_ConfirmInstruction() {
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);

        rootLogger.info("Confirm instructions with message");
        confirmInstruction(caseName, true);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        rootLogger.info("Check message");
        $(byText(MSG_DEFAULT_CONFIRM_INSTRUCTIONS)).shouldBe(visible);
        rootLogger.info("Test passed");
    }
    @Test
    public void testB2_ConfirmInstruction() {
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);

        rootLogger.info("Confirm instructions with message");
        confirmInstruction(caseName, false);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        rootLogger.info("Check message");
        $(byText(MSG_DEFAULT_CONFIRM_INSTRUCTIONS)).shouldNotBe(visible);
        rootLogger.info("Test passed");
    }
    @Test
    public void testC1_ConfirmCompletion() {
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);

        rootLogger.info("Confirm Completion with message");
        confirmInstruction(caseName, true);
        confirmCompletion(caseName, true);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        rootLogger.info("Check message");
        $(byText(MSG_DEFAULT_CONFIRM_COMPLETION)).shouldBe(visible);
        rootLogger.info("Test passed");
    }
    @Test
    public void testC2_ConfirmCompletion() {
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);

        rootLogger.info("Confirm Completion with message");
        confirmInstruction(caseName, false);
        confirmCompletion(caseName, false);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        rootLogger.info("Check message");
        $(byText(MSG_DEFAULT_CONFIRM_COMPLETION)).shouldNotBe(visible);
        rootLogger.info("Test passed");
    }
    @Test
    public void testD1_WithdrawnCase() {
        rootLogger.info("Withdraw case");
        withdrawCase(caseName, true);

        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        sleep(2000);
        checkCaseNameFirstRow(caseName);


        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();

        rootLogger.info("Check default message present");
        $(byText(MSG_DEFAULT_WITHDRAW)).shouldBe(visible);

        rootLogger.info("Test passed");
    }
    @Test
    public void testD2_WithdrawnCase() {
        rootLogger.info("Withdraw case");
        withdrawCase(caseName, false);

        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        sleep(2000);
        checkCaseNameFirstRow(caseName);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();

        rootLogger.info("Check default message NOT present");
        $(byText(MSG_DEFAULT_WITHDRAW)).shouldNotBe(visible);

        rootLogger.info("Test passed");
    }

}