package Tests;
import Page.TestsCredentials;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.StepsPekama;
import Steps.User;
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
public class TestCommunityIntegrationRedirect {
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
        openUrlWithBaseAuth(URL_LogIn);
    }
    @Test
    public void redirectFormPekamaToCommunity_A1_RequesterDraft() {
        User requester = new User();
        requester.submitLoginCredentials(REQUESTER_EMAIL, REQUESTER_PEKAMA_PASSWORD);
        testProjectTitle = submitMwNewProject();
        testProjectURL = getActualUrl();
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        switchToCommunity();
        sleep(4000);
        submitCookie();
        wizardSelectExpert(EXPERT_TEAM_NAME);
        submitWizard2Step(CASE_NAME);
        
        rootLogger.info("Check that if requester clicks on case (with status == DRAFT/CANCELLED) it leads to redirect to the Community (Wizard) with relevant case in detailed view");
        submitWizard3Step();
        switchToPekama();
        TAB_INFO_COMMUNITY_CASES_LIST.shouldHaveSize(1);
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        switchToCommunity(3);
        rootLogger.info("Redirected to Wizard - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_A2_SupplierDraft() {
        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");

        rootLogger.info("Redirected to Incoming - Test passed");
    }

    @Test
    public void redirectFormPekamaToCommunity_B1_RequesterCancelled() {
        rootLogger.info("Check that if requester clicks on case (with status == DRAFT/CANCELLED) it leads to redirect to the Community (Wizard) with relevant case in detailed view");
        rootLogger.info("Redirected to Wizard - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_B2_SupplierCancelled() {
        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");

        rootLogger.info("Redirected to Incoming - Test passed");
    }

    @Test
    public void redirectFormPekamaToCommunity_C1_RequesterSent() {
        rootLogger.info("Check that if requester clicks on case(with status != DRAFT/CANCELLED) it leads to redirect to the Community (Outgoing cases) with relevant case in detailed view");

        rootLogger.info("Redirected to Outgoing - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_C1_SupplierSent() {
        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");

        rootLogger.info("Redirected to Incoming - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_D1_RequesterWithdrawn() {
        rootLogger.info("Check that if requester clicks on case(with status != DRAFT/CANCELLED) it leads to redirect to the Community (Outgoing cases) with relevant case in detailed view");

        rootLogger.info("Redirected to Outgoing - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_D2_SupplierWithdrawn() {
        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");

        rootLogger.info("Redirected to Incoming - Test passed");
    }

    @Test
    public void redirectFormPekamaToCommunity_SupplierConfirmed() {
        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");

        rootLogger.info("Redirected to Incoming - Test passed");
    }
    @Test
    public void redirectFormPekamaToCommunity_SupplierCompleted() {
        rootLogger.info("Check that if supplier clicks on case (with any status) it leads to redirect to the Community (Incoming cases) with relevant case in detailed view");

        rootLogger.info("Redirected to Incoming - Test passed");
    }
}
