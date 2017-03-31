package Tests;
import Page.TestsCredentials;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.CommunityDashboard.*;
import static Page.CommunityOutgoing.BTN_CONFIRM_COMPLETION_NAME;
import static Page.CommunityOutgoing.BTN_CONFIRM_INSTRUCTION_NAME;
import static Page.CommunityOutgoing.BTN_WITHDRAW_NAME;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityIntegration {
    static final Logger rootLogger = LogManager.getRootLogger();

    private static String testProjectTitle;
    private static String testProjectURL;
    private final static String CASE_NAME = "INTEGRATION_CASE_"+randomString(10);

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
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        clearBrowserCache();
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                REQUESTER_EMAIL,
                REQUESTER_PEKAMA_PASSWORD,
                URL_LogIn);
        rootLogger.info("Create project");
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        testProjectTitle = submitMwNewProject();
        testProjectURL = getActualUrl();
        if (testProjectTitle ==null || testProjectURL==null){
            Assert.fail("Project not created for precondition");
        }
        checkText("No community cases.");
        TAB_INFO_COMMUNITY_TITLE.waitUntil(visible, 20000).shouldHave(text("Services from the Pekama IP Community"));

        rootLogger.info("Check redirect to Community Wizard");
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        switchToCommunity();
        sleep(4000);
        submitCookie();
        wizardSelectExpert(EXPERT_TEAM_NAME);
        submitWizard2Step(CASE_NAME);
        rootLogger.info("Case was created");
    }

    @Test
    public void deleteProjectIfCaseIsDraft() {
        switchToPekamaWindow();
        String actualUrl = getActualUrl();
        Assert.assertEquals
                ("Opened url not same to the project url", testProjectURL, actualUrl);
        refresh();

        rootLogger.info("Delete Pekama project");
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(CASE_NAME));
        deleteProject();

        rootLogger.info("Check Community state");
        switchToCommunityWindow();
        rootLogger.info("Check Outgoing cases");
        openUrlWithBaseAuth(URL_COMMUNITY_OUTGOING);
        sleep(4000);
        checkCaseStatus(CASE_NAME, COMMUNITY_STATUS_CANCELLED);

        rootLogger.info("Check Wizard");
        $(byXpath(getFirstCaseRow(CASE_NAME))).click();
        sleep(4000);
        checkText(COMMUNITY_STATUS_CANCELLED);
        checkText("This project was deleted by its owner.");
        rootLogger.info("Test passed");
    }
    @Test
    public void deleteProjectIfCaseIsCancelled() {
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        rootLogger.info("Cancel case");
        cancelCase(CASE_NAME, false);

        switchToPekamaWindow();
        String actualUrl = getActualUrl();
        Assert.assertEquals
                ("Opened url not same to the project url", testProjectURL, actualUrl);
        refresh();
        sleep(4000);

        rootLogger.info("Check community elements");
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(MATTER_TYPE_TRADEMARK));
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

        deleteProject();

        rootLogger.info("Check Community state");
        switchToCommunityWindow();
        rootLogger.info("Check Outgoing cases");
        openUrlWithBaseAuth(URL_COMMUNITY_OUTGOING);
        sleep(4000);
        checkCaseStatus(CASE_NAME, COMMUNITY_STATUS_CANCELLED);

        rootLogger.info("Check Wizard");
        $(byXpath(getFirstCaseRow(CASE_NAME))).click();
        sleep(4000);
        checkText(COMMUNITY_STATUS_CANCELLED);
        checkText("This project was deleted by its owner.");
        rootLogger.info("Test passed");
    }
    @Test
    public void deleteProjectIfCaseIsWithdrawn() {
        submitWizard3Step();
        submitWizard4Step();

        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        withdrawCase(CASE_NAME, false);

        switchToPekamaWindow();
        String actualUrl = getActualUrl();
        Assert.assertEquals
                ("Opened url not same to the project url", testProjectURL, actualUrl);
        refresh();
        sleep(4000);

        rootLogger.info("Check community elements");
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(MATTER_TYPE_TRADEMARK));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_WITHDRAWN));

        rootLogger.info("Check project members");
        PROJECT_TAB_CONTACTS.shouldBe(visible).click();
        checkText(OWNER);
        checkText(REQUESTER_FULL_TEAM_NAME);
        checkText(ADMIN);
        checkText(EXPERT_FULL_TEAM_NAME);

        deleteProject();

        rootLogger.info("Check Community state");
        switchToCommunityWindow();
        rootLogger.info("Check Outgoing cases");
        openUrlWithBaseAuth(URL_COMMUNITY_OUTGOING);
        sleep(4000);
        checkCaseStatus(CASE_NAME, COMMUNITY_STATUS_WITHDRAWN);

        rootLogger.info("Check Wizard");
        $(byXpath(getFirstCaseRow(CASE_NAME))).click();
        checkText("This project was deleted by its owner.");
        rootLogger.info("Test passed");
    }
    @Test
    public void deleteProjectIfCaseIsActive() {
        submitWizard3Step();
        submitWizard4Step();

        switchToPekamaWindow();
        refresh();
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(MATTER_TYPE_TRADEMARK));
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
    @Test
    public void confirmInstructionInPekamaAsExpert_MsgTrue() {
        submitWizard3Step();
        submitWizard4Step();
        switchToPekamaWindow();

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
                URL_LogIn);

        rootLogger.info("Open project by Expert and confirm instruction and complete case");
        openUrlWithBaseAuth(testProjectURL);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(CASE_NAME));

        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(MATTER_TYPE_TRADEMARK));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_CONFIRM_INSTRUCTION_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_RECEIVED));
        TAB_INFO_COMMUNITY_CASE_ACTION.click();
        acceptConfirmInstruction(true);

        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_CONFIRM_COMPLETION_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CONFIRMED));

        TAB_INFO_COMMUNITY_CASE_ACTION.click();
        acceptCompletion(true);
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_COMPLETED));
        rootLogger.info("Test passed");
    }
    @Test
    public void confirmInstructionInPekamaAsExpert_MsgFalse() {
        submitWizard3Step();
        submitWizard4Step();
        switchToPekamaWindow();

        rootLogger.info("Expert login");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                EXPERT_EMAIL,
                EXPERT_PEKAMA_PASSWORD,
                URL_LogIn);

        rootLogger.info("Open project by Expert and confirm instruction and complete case");
        openUrlWithBaseAuth(testProjectURL);
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(CASE_NAME));

        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(MATTER_TYPE_TRADEMARK));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_CONFIRM_INSTRUCTION_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_RECEIVED));
        TAB_INFO_COMMUNITY_CASE_ACTION.click();
        acceptConfirmInstruction(false);

        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text(BTN_CONFIRM_COMPLETION_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CONFIRMED));

        TAB_INFO_COMMUNITY_CASE_ACTION.click();
        acceptCompletion(false);
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_COMPLETED));
        rootLogger.info("Test passed");
    }
    @Test
    public void cancelCaseInPekama_MsgFalse() {
        submitWizard3Step();
        submitWizard4Step();
        switchToPekamaWindow();
        String actualUrl = getActualUrl();
        Assert.assertEquals
                ("Opened url not same to the project url", testProjectURL, actualUrl);
        refresh();

        rootLogger.info("Withdraw case");
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_SENT));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldBe(visible).click();
        acceptWithdrawCase(false);

        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_WITHDRAWN));
        rootLogger.info("Test passed");
    }
    @Test
    public void cancelCaseInPekama_MsgTrue() {
        submitWizard3Step();
        submitWizard4Step();
        switchToPekamaWindow();
        String actualUrl = getActualUrl();
        Assert.assertEquals
                ("Opened url not same to the project url", testProjectURL, actualUrl);
        refresh();

        rootLogger.info("Withdraw case");
        sleep(4000);
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_SENT));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldBe(visible).click();
        acceptWithdrawCase(true);

        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_WITHDRAWN));
        rootLogger.info("Test passed");
    }
}