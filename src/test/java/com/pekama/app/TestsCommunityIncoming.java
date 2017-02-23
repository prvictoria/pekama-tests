package com.pekama.app;
import org.junit.FixMethodOrder;
import Page.TestsCredentials;
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.CommunityDashboard.*;
import static Page.CommunityOutgoing.*;
import static Page.TestsCredentials.*;
import static Page.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityIncoming {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String expertTeam = TestsCredentials.User2.TEAM_NAME.getValue();
    private String expertName = TestsCredentials.User2.NAME.getValue();
    private String expertEmail = User2.GMAIL_EMAIL.getValue();
    private String expertPassword = User2.PEKAMA_PASSWORD.getValue();
    private String supplierEmail = User1.GMAIL_EMAIL.getValue();
    private String supplierPassword = User1.PEKAMA_PASSWORD.getValue();
    private static String caseName;
    @Before
    public void before() {
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                supplierEmail,
                supplierPassword,
                URL_COMMUNITY_LOGIN);
        rootLogger.info("Redirect back after login");
    }
    @After
    public void after() {open(URL_COMMUNITY_LOGOUT);}

    @Test
    public void testA_ArchiveCase() {
        rootLogger.info("Supplier Create case");
        caseName = createCase(expertTeam);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);

        rootLogger.info("Archive case");
        archiveCase(caseName);
        rootLogger.info("Test passed");
    }
    @Test
    public void testA2_CheckDraftCase() {
        rootLogger.info("Supplier Create case");
        caseName = createDraftCase(expertTeam);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        sleep(2000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, COMMUNITY_STATUS_INQUIRY);
        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();

        rootLogger.info("Test passed");
    }
    @Test
    public void testB1_ConfirmInstruction() {
        rootLogger.info("Supplier Create case");
        caseName = createCase(expertTeam);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, COMMUNITY_STATUS_RECEIVED);
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
        rootLogger.info("Supplier Create case");
        caseName = createCase(expertTeam);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
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
        rootLogger.info("Supplier Create case");
        caseName = createCase(expertTeam);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
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
        rootLogger.info("Supplier Create case");
        caseName = createCase(expertTeam);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
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
        rootLogger.info("Supplier Create case");
        caseName = createCase(expertTeam);
        withdrawCase(caseName, true);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
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
        rootLogger.info("Supplier Create case");
        caseName = createCase(expertTeam);
        rootLogger.info("Withdraw case");
        withdrawCase(caseName, false);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
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

    @Test
    public void testF1_cancelledCaseState() {
        rootLogger.info("Create draft case");
        String caseName = createDraftCase(expertTeam);
        COMMUNITY_TAB_Outgoing.click();
        rootLogger.info("Cancel case");
        cancelCase(caseName, true);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        sleep(2000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, COMMUNITY_STATUS_CANCELLED);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        String userName = expertName;
        rootLogger.info("Check default message present: "+msgCaseCancelled(userName));
        checkText(msgCaseCancelled(userName));
        rootLogger.info("Test passed");
    }

    @Test
    public void testF2_cancelledCaseState() {
        rootLogger.info("Create draft case");
        String caseName = createDraftCase(expertTeam);
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        rootLogger.info("Cancel case");
        cancelCase(caseName, false);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                expertEmail,
                expertPassword,
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        sleep(2000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, COMMUNITY_STATUS_CANCELLED);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();

        String userName = expertName;
        rootLogger.info("Check default message NOT present: "+msgCaseCancelled(userName));
        checkTextNotPresent(msgCaseCancelled(userName));
    }
}