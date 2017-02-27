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
import static Page.PekamaConversationProject.CONVERSATION_MsgText;
import static Page.PekamaProject.*;
import static Page.PekamaProject.PROJECT_BTN_DELETE;
import static Page.TestsCredentials.*;
import static Page.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsHttpAuth.httpAuthUrl;
import static Steps.StepsModalWindows.submitConfirmAction;
import static Steps.StepsModalWindows.submitErrorWindow;
import static Steps.StepsPekama.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.pekama.app.AllTestsRunner.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityIncoming {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String caseName;
    private static String testProjectTitle;
    private static String testProjectURL;
    private final static String TEST_CASE_TYPE = CaseType.PATENT.getValue();
    private final static String TEST_CASE_COUNTRY = Countries.PITCAIRN_ISLANDS.getValue();
    private final static String TEST_CASE_NAME = "CUSTOM_NAME"+randomString(10);
    
    private String REQUESTER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private String REQUESTER_PEKAMA_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private final static String REQUESTER_NAME = TestsCredentials.User1.NAME.getValue();
    private final static String REQUESTER_SURNAME = TestsCredentials.User1.SURNAME.getValue();
    private final static String REQUESTER_FULL_TEAM_NAME = TestsCredentials.User1.FULL_TEAM_NAME.getValue();
    private final static String REQUESTER_NAME_SURNAME = TestsCredentials.User3.NAME_SURNAME.getValue();
    private String EXPERT_EMAIL = User2.GMAIL_EMAIL.getValue();
    private String EXPERT_PEKAMA_PASSWORD = User2.PEKAMA_PASSWORD.getValue();
    private String EXPERT_NAME = TestsCredentials.User2.NAME.getValue();
    private final static String EXPERT_SURNAME = TestsCredentials.User2.SURNAME.getValue();
    private String EXPERT_TEAM_NAME = TestsCredentials.User2.TEAM_NAME.getValue();
    private final static String EXPERT_FULL_TEAM_NAME = TestsCredentials.User2.FULL_TEAM_NAME.getValue();
    private static final String EXPERT_NAME_SURNAME = TestsCredentials.User2.NAME_SURNAME.getValue();
    private final static String INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
    @BeforeClass
    public static void beforeClass() {
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                REQUESTER_EMAIL,
                REQUESTER_PEKAMA_PASSWORD,
                URL_COMMUNITY_LOGIN);
        rootLogger.info("Redirect back after login");
    }
    @After
    public void after() {open(URL_COMMUNITY_LOGOUT); clearBrowserCache();}

    @Test
    public void testA_ArchiveCase() {
        rootLogger.info("Supplier Create case");
        caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
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
        caseName = createDraftCase(EXPERT_TEAM_NAME);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
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
        caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
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
        caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
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

        rootLogger.info("Get project url");
        testProjectURL = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST + ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: " + testProjectURL);
        rootLogger.info("Test passed");
        
    }
    @Test
    public void testB3_tryToDeleteProjectByOwner() {
        rootLogger.info("Open project by Owner and try to delete");
        httpAuthUrl(testProjectURL);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CONFIRMED));

        rootLogger.info("Try Delete project");
        scrollUp();
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        submitConfirmAction();
        submitErrorWindow(
                "Invalid action",
                "You can't archive a project that has active community projects associated with it");
        rootLogger.info("Test passed");
    }
    @Test
    public void testC1_ConfirmCompletion() {
        rootLogger.info("Supplier Create case");
        caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
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
        caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
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

        rootLogger.info("Get project url");
        testProjectURL = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST + ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: " + testProjectURL);
        rootLogger.info("Test passed");
        rootLogger.info("Test passed");
    }
    @Test
    public void testC3_tryToDeleteProjectByOwner() {
        rootLogger.info("Open project by Owner and try to delete");
        httpAuthUrl(testProjectURL);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_COMPLETED));

        rootLogger.info("Try Delete project");
        scrollUp();
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        submitConfirmAction();
        submitErrorWindow(
                "Invalid action",
                "You can't archive a project that has active community projects associated with it");
        rootLogger.info("Test passed");
    }
    @Test
    public void testD1_WithdrawnCase() {
        rootLogger.info("Supplier Create case");
        caseName = createCase(EXPERT_TEAM_NAME);
        withdrawCase(caseName, true);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
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
        caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Withdraw case");
        withdrawCase(caseName, false);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
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
        String caseName = createDraftCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();
        rootLogger.info("Cancel case");
        cancelCase(caseName, true);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        sleep(2000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, COMMUNITY_STATUS_CANCELLED);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        String userName = EXPERT_NAME;
        rootLogger.info("Check default message present: "+msgCaseCancelled(userName));
        checkText(msgCaseCancelled(userName));
        rootLogger.info("Test passed");
    }
    @Test
    public void testF2_cancelledCaseState() {
        rootLogger.info("Create draft case");
        String caseName = createDraftCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        rootLogger.info("Cancel case");
        cancelCase(caseName, false);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
                URL_COMMUNITY_LOGIN);
        COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        sleep(2000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, COMMUNITY_STATUS_CANCELLED);

        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();

        String userName = EXPERT_NAME;
        rootLogger.info("Check default message NOT present: "+msgCaseCancelled(userName));
        checkTextNotPresent(msgCaseCancelled(userName));
    }
    @Test
    public void testG1_ChangeStatusesInPekamaForExpert() {
        rootLogger.info("Create case");
        caseName = createCase(EXPERT_TEAM_NAME);
        //COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);
        rootLogger.info("Get project url");
        testProjectURL = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST + ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: " + testProjectURL);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
                URL_COMMUNITY_LOGIN);

        rootLogger.info("Open project by Expert and confirm instruction and complete case");
        httpAuthUrl(testProjectURL);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));

        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_CONFIRM_INSTRUCTION_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_RECEIVED));
        TAB_INFO_COMMUNITY_CASE_ACTION.click();
        acceptConfirmInstruction(false);

        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_CONFIRM_COMPLETION_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CONFIRMED));

        TAB_INFO_COMMUNITY_CASE_ACTION.click();
        acceptCompetion(false);
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_COMPLETED));
        rootLogger.info("Test passed");
    }
    @Test
    public void testG2_ChangeStatusesInPekamaForExpert() {
        rootLogger.info("Create case");
        caseName = createCase(EXPERT_TEAM_NAME);
        //COMMUNITY_TAB_Incoming.waitUntil(visible, 15000).click();
        checkCaseNameFirstRow(caseName);
        rootLogger.info("Get project url");
        testProjectURL = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST + ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: " + testProjectURL);

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
                URL_COMMUNITY_LOGIN);

        rootLogger.info("Open project by Expert and confirm instruction and complete case");
        httpAuthUrl(testProjectURL);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));

        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_CONFIRM_INSTRUCTION_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_RECEIVED));
        CONVERSATION_MsgText.shouldHave(text(MSG_DEFAULT_SENT_INSTRUCTION));

        TAB_INFO_COMMUNITY_CASE_ACTION.click();
        acceptConfirmInstruction(true);

        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_CONFIRM_COMPLETION_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CONFIRMED));
        CONVERSATION_MsgText.shouldHave(text(MSG_DEFAULT_CONFIRM_INSTRUCTIONS));

        TAB_INFO_COMMUNITY_CASE_ACTION.click();
        acceptCompetion(true);
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_COMPLETED));
        CONVERSATION_MsgText.shouldHave(text(MSG_DEFAULT_CONFIRM_COMPLETION));

        rootLogger.info("Test passed");
    }
}