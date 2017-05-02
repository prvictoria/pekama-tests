package Tests;
import Page.TestsCredentials;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.awt.*;
import java.io.IOException;

import static Page.CommunityDashboard.*;
import static Page.CommunityWizard.WIZARD_BTN_CANCEL;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
import static Tests.BeforeTestsSetUp.*;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCommunityIntegrationRedirect {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String testProjectTitle;
    private static String testProjectURL;
    private static String caseName = "INTEGRATION_CASE_"+randomString(10);

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
        openUrlWithBaseAuth(URL_LogIn);
    }
    @Test
    public void redirectFormPekamaToCommunity_A1_RequesterDraft() {
        User requester = new User();
        requester.submitLoginCredentials(REQUESTER_EMAIL, REQUESTER_PEKAMA_PASSWORD);
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 30000).click();
        testProjectTitle = submitMwNewProject();
        testProjectURL = getActualUrl();

        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        switchToCommunity();
        submitCookie(30);
        wizardSelectExpert(EXPERT_TEAM_NAME);
        submitWizard2Step(caseName);

        rootLogger.info("Check that if requester clicks on case (with status == DRAFT/CANCELLED) it leads to redirect to the Community (Wizard) with relevant case in detailed view");
        switchToPekama();
        refresh();
        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToWindowByIndex(2, 3);
        COMMUNITY_BTN_SendInstructions.waitUntil(visible, 20000);
        rootLogger.info("Redirected to Wizard - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_A2_SupplierDraft() {
//        testProjectURL = "https://staging.pekama.com/a/projects/30551/info";
//        testProjectTitle = "DEFAULT_PROJECT_SUV77RO3Q0";
//        caseName = "INTEGRATION_CASE_KW3QPAGWUG";
        User requester = new User();
        requester.submitLoginCredentials(EXPERT_EMAIL, EXPERT_PEKAMA_PASSWORD);

        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");
        openUrlWithBaseAuth(testProjectURL);
        checkText(testProjectTitle);
        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity(2);
        submitCookie(30);
        checkIncomingDetailedCaseView(caseName, COMMUNITY_STATUS_INQUIRY);
        rootLogger.info("Redirected to Incoming - Test passed");
    }

    @Test
    public void redirectFormPekamaToCommunity_B1_RequesterCancelled() throws AWTException {
        User requester = new User();
        requester.submitLoginCredentials(REQUESTER_EMAIL, REQUESTER_PEKAMA_PASSWORD);
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 30000).click();
        testProjectTitle = submitMwNewProject();
        testProjectURL = getActualUrl();

        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        switchToCommunity();
        submitCookie(15);
        wizardSelectExpert(EXPERT_TEAM_NAME);
        submitWizard2Step(caseName);
        submitWizard3Step();
        WIZARD_BTN_CANCEL.shouldBe(visible).click();
        acceptCancelCase(false);

        rootLogger.info("Check that if requester clicks on case (with status == DRAFT/CANCELLED) it leads to redirect to the Community (Wizard) with relevant case in detailed view");

        switchToPekama(2);
        refresh();
        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToWindowByIndex(2, 3);
        checkText(COMMUNITY_STATUS_CANCELLED);
        rootLogger.info("Redirected to Wizard - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_B2_SupplierCancelled() {
//        testProjectURL = "https://staging.pekama.com/a/projects/30563/info";
//        testProjectTitle = "DEFAULT_PROJECT_8I6L92BGF2";
//        caseName = "INTEGRATION_CASE_7SBHS1FQJ0";
        User requester = new User();
        requester.submitLoginCredentials(EXPERT_EMAIL, EXPERT_PEKAMA_PASSWORD);

        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");
        openUrlWithBaseAuth(testProjectURL);
        checkText(testProjectTitle);
        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity(2);
        submitCookie(15);
        checkIncomingDetailedCaseView(caseName, COMMUNITY_STATUS_CANCELLED);
        rootLogger.info("Redirected to Incoming - Test passed");
    }

    @Test
    public void redirectFormPekamaToCommunity_C1_RequesterSent() {
        User requester = new User();
        requester.submitLoginCredentials(REQUESTER_EMAIL, REQUESTER_PEKAMA_PASSWORD);
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 30000).click();
        testProjectTitle = submitMwNewProject();
        testProjectURL = getActualUrl();

        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        switchToCommunity();
        submitCookie(15);
        wizardSelectExpert(EXPERT_TEAM_NAME);
        submitWizard2Step(caseName);
        submitWizard3Step();
        submitWizard4Step();

        rootLogger.info("Check that if requester clicks on case(with status != DRAFT/CANCELLED) it leads to redirect to the Community (Outgoing cases) with relevant case in detailed view");
        switchToPekama();
        refresh();
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToWindowByIndex(2, 3);
        submitCookie(5);
        checkOutgoingDetailedCaseView(caseName, COMMUNITY_STATUS_SENT);
        rootLogger.info("Redirected to Outgoing - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_C2_SupplierSent() {
//        testProjectURL = "https://staging.pekama.com/a/projects/30551/info";
//        testProjectTitle = "DEFAULT_PROJECT_SUV77RO3Q0";
//        caseName = "INTEGRATION_CASE_KW3QPAGWUG";
        User requester = new User();
        requester.submitLoginCredentials(EXPERT_EMAIL, EXPERT_PEKAMA_PASSWORD);

        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");
        openUrlWithBaseAuth(testProjectURL);
        checkText(testProjectTitle);
        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity(2);
        submitCookie(15);
        checkIncomingDetailedCaseView(caseName, COMMUNITY_STATUS_RECEIVED);
        rootLogger.info("Redirected to Incoming - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_C3_SupplierConfirmed() {
        User requester = new User();
        requester.submitLoginCredentials(EXPERT_EMAIL, EXPERT_PEKAMA_PASSWORD);

        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");
        openUrlWithBaseAuth(testProjectURL);
        checkText(testProjectTitle);

        confirmCaseInPekama(false);
        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity(2);
        submitCookie(15);
        checkIncomingDetailedCaseView(caseName, COMMUNITY_STATUS_CONFIRMED);
        rootLogger.info("Redirected to Incoming - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_C4_RequesterConfirmed() {
        User requester = new User();
        requester.submitLoginCredentials(REQUESTER_EMAIL, REQUESTER_PEKAMA_PASSWORD);

        openUrlWithBaseAuth(testProjectURL);
        checkText(testProjectTitle);
        rootLogger.info("Check that if requester clicks on case(with status != DRAFT/CANCELLED) it leads to redirect to the Community (Outgoing cases) with relevant case in detailed view");

        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity();
        submitCookie(5);
        checkOutgoingDetailedCaseView(caseName, COMMUNITY_STATUS_CONFIRMED);
        rootLogger.info("Redirected to Outgoing - Test passed");
    }

    @Test
    public void redirectFormPekamaToCommunity_C5_SupplierCompleted() {
        User requester = new User();
        requester.submitLoginCredentials(EXPERT_EMAIL, EXPERT_PEKAMA_PASSWORD);

        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");
        openUrlWithBaseAuth(testProjectURL);
        checkText(testProjectTitle);

        completeCaseInPekama(false);

        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity(2);
        submitCookie(15);
        checkIncomingDetailedCaseView(caseName, COMMUNITY_STATUS_COMPLETED);
        rootLogger.info("Redirected to Incoming - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_C6_RequesterCompleted() {
        User requester = new User();
        requester.submitLoginCredentials(REQUESTER_EMAIL, REQUESTER_PEKAMA_PASSWORD);

        openUrlWithBaseAuth(testProjectURL);
        checkText(testProjectTitle);
        rootLogger.info("Check that if requester clicks on case(with status != DRAFT/CANCELLED) it leads to redirect to the Community (Outgoing cases) with relevant case in detailed view");

        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity();
        submitCookie(5);
        checkOutgoingDetailedCaseView(caseName, COMMUNITY_STATUS_COMPLETED);
        rootLogger.info("Redirected to Outgoing - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_D1_RequesterWithdrawn() {
        User requester = new User();
        requester.submitLoginCredentials(REQUESTER_EMAIL, REQUESTER_PEKAMA_PASSWORD);
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 30000).click();
        testProjectTitle = submitMwNewProject();
        testProjectURL = getActualUrl();

        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        switchToCommunity();
        submitCookie(15);
        wizardSelectExpert(EXPERT_TEAM_NAME);
        submitWizard2Step(caseName);
        submitWizard3Step();
        submitWizard4Step();

        rootLogger.info("Check that if requester clicks on case(with status != DRAFT/CANCELLED) it leads to redirect to the Community (Outgoing cases) with relevant case in detailed view");
        switchToPekama();
        refresh();
        withdrawCaseInPekama(false);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToWindowByIndex(2, 3);
        submitCookie(5);
        checkOutgoingDetailedCaseView(caseName, COMMUNITY_STATUS_WITHDRAWN);
        rootLogger.info("Redirected to Outgoing - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_D2_SupplierWithdrawn() {
        User requester = new User();
        requester.submitLoginCredentials(EXPERT_EMAIL, EXPERT_PEKAMA_PASSWORD);

        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");
        openUrlWithBaseAuth(testProjectURL);
        checkText(testProjectTitle);
        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity(2);
        submitCookie(15);
        checkIncomingDetailedCaseView(caseName, COMMUNITY_STATUS_WITHDRAWN);
        rootLogger.info("Redirected to Incoming - Test passed");
    }


}
