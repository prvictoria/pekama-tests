package com.pekama.app;
import Steps.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.CommunityDashboard.*;
import static Page.CommunityOutgoing.*;
import static Page.PekamaProject.TAB_INFO_COMMUNITY_CASE_NAME;
import static Page.TestsCredentials.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsHttpAuth.httpAuthUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityOutgoing {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {
        holdBrowserAfterTest(false);
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(User2.GMAIL_EMAIL.getValue(),             User2.PEKAMA_PASSWORD.getValue(), URL_COMMUNITY_LOGIN);
        rootLogger.info("Redirect back after login");
    }
    @After
    public void after() {
        open(URL_COMMUNITY_LOGOUT);
        rootLogger.info("Open URL - "+URL_COMMUNITY_LOGOUT);
    }

    @Test
    public void testA_EditCaseName() {
        rootLogger.info("Create case");
        String caseName = createCase();
        rootLogger.info("Edit case name");
        String newName = editCaseName(caseName);
        rootLogger.info("Check max length field validation");
        editCaseName(newName, 1025);
        rootLogger.info("Test passed");
    }
    @Test
    public void testB_RedirectToPekamaProject() {
        rootLogger.info("Create case");
        String caseName = createCase();
        rootLogger.info("Follow project link");
        String link = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT)).getAttribute("href");
        rootLogger.info("project link is: "+link);
        String target = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT)).getAttribute("target");
        rootLogger.info("open in new window target present: "+target);
        httpAuthUrl(link);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));
        rootLogger.info("Community case"+caseName+"present in Project");
        //no ability to handle auth redirect
        // https://github.com/webdriverio/webdriverio/issues/1658
        //$(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT)).click();
        rootLogger.info("Test passed");
    }
    @Test
    public void testC_ArchiveCase() {

        rootLogger.info("Create case");
        String caseName = createCase();
        rootLogger.info("Archive case");
        archiveCase(caseName);
        rootLogger.info("Test passed");
    }
    @Test
    public void testD1_WithdrawCase() {
        rootLogger.info("Create case");
        String caseName = createCase();
        rootLogger.info("Withdraw case");
        withdrawCase(caseName, true);
        rootLogger.info("Test passed");
    }
    @Test
    public void testD2_WithdrawCase() {
        rootLogger.info("Create case");
        String caseName = createCase();
        rootLogger.info("Cancel case");
        withdrawCase(caseName, false);
        rootLogger.info("Test passed");
    }
    @Test
    public void testE_OpenCaseRow() {
        rootLogger.info("Create case");
        String caseName = createCase();
        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        rootLogger.info("Check expanded row");
        $(byText(MSG_DEFAULT_INSTRUCTION)).shouldBe(visible);
        rootLogger.info("Test passed");
    }

}