package Tests;
import Page.TestsCredentials;
import Steps.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.CommunityDashboard.*;
import static Page.CommunityOutgoing.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityOutgoing {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String testProjectTitle;
    private static String testProjectURL;
    private final static String TEST_CASE_TYPE = CaseType.PATENT.getValue();
    private final static String TEST_CASE_COUNTRY = Countries.PITCAIRN_ISLANDS.getValue();
    private final static String TEST_CASE_NAME = "CUSTOM_NAME"+randomString(10);

    private final static String REQUESTER_EMAIL = TestsCredentials.User3.GMAIL_EMAIL.getValue();
    private final static String REQUESTER_PEKAMA_PASSWORD = TestsCredentials.User3.PEKAMA_PASSWORD.getValue();
    private final static String REQUESTER_NAME = TestsCredentials.User3.NAME.getValue();
    private final static String REQUESTER_SURNAME = TestsCredentials.User3.SURNAME.getValue();
    private final static String REQUESTER_FULL_TEAM_NAME = TestsCredentials.User3.FULL_TEAM_NAME.getValue();
    private final static String REQUESTER_NAME_SURNAME = TestsCredentials.User3.NAME_SURNAME.getValue();

    private final static String EXPERT_EMAIL = TestsCredentials.User2.GMAIL_EMAIL.getValue();
    private final static String EXPERT_PEKAMA_PASSWORD = TestsCredentials.User2.PEKAMA_PASSWORD.getValue();
    private final static String EXPERT_NAME = TestsCredentials.User2.NAME.getValue();
    private final static String EXPERT_SURNAME = TestsCredentials.User2.SURNAME.getValue();
    private final static String EXPERT_TEAM_NAME = TestsCredentials.User2.TEAM_NAME.getValue();
    private final static String EXPERT_FULL_TEAM_NAME = TestsCredentials.User2.FULL_TEAM_NAME.getValue();
    private static final String EXPERT_NAME_SURNAME = TestsCredentials.User2.NAME_SURNAME.getValue();
    private final static String INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        clearBrowserCache();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                REQUESTER_EMAIL,
                REQUESTER_PEKAMA_PASSWORD,
                URL_COMMUNITY_LOGIN);
        rootLogger.info("Redirect back after login");
    }
    @After
    public void after() {
        openUrlWithBaseAuth(URL_COMMUNITY_LOGOUT);
        rootLogger.info("Open URL - "+URL_COMMUNITY_LOGOUT);
    }
    @Test
    public void testA_EditCaseName() {
        rootLogger.info("Create case");
        String caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Edit case name");
        String newName = editCaseName(caseName);
        rootLogger.info("Check max length field validation");
        editCaseName(newName, 1025);
        rootLogger.info("Test passed");
    }
    @Ignore //TODO obsolete
    @Test
    public void testB_RedirectToPekamaProject() {
        rootLogger.info("Create case");
        String caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Follow project link");
        String testProjectLink = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT)).getAttribute("href");
        rootLogger.info("project link is: "+testProjectLink);
        String target = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT)).getAttribute("target");
        rootLogger.info("open in new window target present: "+target);
        openUrlWithBaseAuth(testProjectLink);
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
        String caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Archive case");
        archiveCase(caseName);
        rootLogger.info("Test passed");
    }
    @Test
    public void testD1_WithdrawCase() {
        rootLogger.info("Create case");
        String caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Withdraw case");
        withdrawCase(caseName, true);
        rootLogger.info("Test passed");
    }
    @Test
    public void testD2_WithdrawCase() {
        rootLogger.info("Create case");
        String caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Cancel case");
        withdrawCase(caseName, false);
        rootLogger.info("Test passed");
    }
    @Test
    public void testE_SentCaseCheckMessage() {
        rootLogger.info("Create case");
        String caseName = createCase(EXPERT_TEAM_NAME);
        rootLogger.info("Open case row");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        rootLogger.info("Check expanded row");
        $(byText(MSG_DEFAULT_SENT_INSTRUCTION)).shouldBe(visible);
        rootLogger.info("Test passed");
    }
    @Test
    public void testF1_cancelCase() {
        rootLogger.info("Create draft case");
        String caseName = createDraftCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        rootLogger.info("Cancel case");
        cancelCase(caseName, true);
        rootLogger.info("Test passed");

    }
    @Test
    public void testF2_cancelCase() {
        rootLogger.info("Create draft case");
        String caseName = createDraftCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        rootLogger.info("Cancel case");
        cancelCase(caseName, false);
        rootLogger.info("Test passed");
    }
    @Ignore //TODO obsolete flow - no link
    @Test
    public void testF3_cancelCaseCheckPekama() {
        rootLogger.info("Create draft case");
        String caseName = createDraftCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        rootLogger.info("Cancel case");
        cancelCase(caseName, false);

        rootLogger.info("Get project url");
        String testProjectLink = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: "+testProjectLink);

        rootLogger.info("Check Pekama project State");
        openUrlWithBaseAuth(testProjectLink);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CANCELLED));

        rootLogger.info("Check project members");
        PROJECT_TAB_CONTACTS.shouldBe(visible).click();
        checkText(OWNER);
        checkText(REQUESTER_FULL_TEAM_NAME);
        checkText(ADMIN);
        checkText(INTRODUCER_NAME);
        checkText(VIEWER);
        checkText(EXPERT_FULL_TEAM_NAME);

        rootLogger.info("Test passed");
    }
    @Ignore //TODO obsolete flow - no link
    @Test
    public void testG1_deleteDraftCase() {
        rootLogger.info("Create draft case");
        String caseName = createDraftCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);

        rootLogger.info("Get project url");
        String testProjectLink = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: "+testProjectLink);

        rootLogger.info("Delete Pekama project");
        openUrlWithBaseAuth(testProjectLink);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));

        scrollUp();
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        submitConfirmAction();

        rootLogger.info("Check Outgoing cases");
        openUrlWithBaseAuth(URL_COMMUNITY_OUTGOING);
        sleep(4000);
        checkCaseStatus(caseName, COMMUNITY_STATUS_CANCELLED);

        rootLogger.info("Check Wizard");
        $(byXpath(getFirstCaseRow(caseName))).click();
        sleep(4000);
        checkText(COMMUNITY_STATUS_CANCELLED);
        checkText("This project was deleted by its owner.");
        rootLogger.info("Test passed");
    }
    @Ignore //TODO obsolete flow - no link
    public void testG2_deleteCancelledCase() {
        rootLogger.info("Create draft case");
        String caseName = createDraftCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        rootLogger.info("Cancel case");
        cancelCase(caseName, false);

        rootLogger.info("Get project url");
        String testProjectLink = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: "+testProjectLink);

        rootLogger.info("Check Pekama project State");
        openUrlWithBaseAuth(testProjectLink);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CANCELLED));

        rootLogger.info("Delete project");
        scrollUp();
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        submitConfirmAction();

        rootLogger.info("Check Outgoing cases");
        openUrlWithBaseAuth(URL_COMMUNITY_OUTGOING);
        sleep(4000);
        checkCaseStatus(caseName, COMMUNITY_STATUS_CANCELLED);

        rootLogger.info("Check Wizard");
        $(byXpath(getFirstCaseRow(caseName))).click();
        sleep(4000);
        checkText(COMMUNITY_STATUS_CANCELLED);
        checkText("This project was deleted by its owner.");

        //todo rootLogger.info("Check Incoming cases");

        rootLogger.info("Test passed");
    }
    @Ignore //TODO obsolete flow - no link
    public void testG3_deleteWithdrawnCase() {
        rootLogger.info("Create case");
        String caseName = createCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        rootLogger.info("withdraw case");
        withdrawCase(caseName, false);

        rootLogger.info("Get project url");
        String testProjectLink = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: "+testProjectLink);

        rootLogger.info("Check Pekama project State");
        openUrlWithBaseAuth(testProjectLink);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_WITHDRAWN));

        rootLogger.info("Delete project");
        scrollUp();
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        submitConfirmAction();

        rootLogger.info("Check Outgoing cases");
        openUrlWithBaseAuth(URL_COMMUNITY_OUTGOING);
        sleep(4000);
        checkCaseStatus(caseName, COMMUNITY_STATUS_WITHDRAWN);
        $(byXpath(getFirstCaseRow(caseName))).click();
        checkText("This project was deleted by its owner.");

        //todo rootLogger.info("Check Incoming cases");

        rootLogger.info("Test passed");
    }
    @Ignore //TODO obsolete flow - no link
    public void testG4_deleteSentCaseError() {
        rootLogger.info("Create case");
        String caseName = createCase(EXPERT_TEAM_NAME);
        COMMUNITY_TAB_Outgoing.click();

        rootLogger.info("Get project url");
        String testProjectLink = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST + ROW_CONTROL_LINK_PROJECT))
                .getAttribute("href");
        rootLogger.info("project link is: " + testProjectLink);

        rootLogger.info("Check Pekama project State");
        openUrlWithBaseAuth(testProjectLink);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(caseName));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_WITHDRAW_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_SENT));

        rootLogger.info("Try Delete project");
        scrollUp();
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        submitConfirmAction();
        submitErrorWindow(
                "Invalid action",
                "You can't archive a project that has active community projects associated with it");

        rootLogger.info("Test passed");
    }


}