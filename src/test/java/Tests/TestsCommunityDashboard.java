/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
package Tests;
import Steps.User;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;

import java.io.IOException;

import static Page.CommunityDashboard.*;
import static Page.CommunityLanding.LANDING_SIGNUP;
import static Page.CommunityWizard.*;
import static Page.ModalWindows.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Page.PekamaLogin.*;
import static Page.TestsCredentials.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
public class TestsCommunityDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    String email = User2.GMAIL_EMAIL.getValue();
    String password = User2.PEKAMA_PASSWORD.getValue();
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
        openUrlWithBaseAuth(URL_COMMUNITY_DASHBOARD);
    }
    @After
    public void after() {
        openUrlWithBaseAuth(URL_COMMUNITY_LOGOUT);
    }

    @Test
    public void checkDashboardGui() {
        COMMUNITY_HEADER_LOGO.shouldBe(Condition.visible).click();
        COMMUNITY_HEADER_MANAGEMENT.shouldBe(Condition.visible);
        COMMUNITY_HEADER_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        rootLogger.info("All elements in Header present on default screen");
        COMMUNITY_TAB_Supplier.shouldBe(Condition.visible).shouldHave(Condition.text("find a supplier"));
        COMMUNITY_TAB_Incoming.shouldBe(Condition.visible).shouldHave(Condition.text("incoming cases"));
        COMMUNITY_TAB_Outgoing.shouldBe(Condition.visible).shouldHave(Condition.text("outgoing cases"));
        COMMUNITY_TAB_Experts.shouldBe(Condition.visible).shouldHave(Condition.text("my experts"));
        COMMUNITY_TAB_Profile.shouldBe(Condition.visible).shouldHave(Condition.text("become a supplier"));
        rootLogger.info("Tabs names correct and user isn`t logged in");

        COMMUNITY_HEADER_MANAGEMENT.click();
        waitForModalWindow("You are about to be taken to Pekama's case management platform.");
        checkText("This is an advanced platform to manage IP cases, docket deadline and store documents.");
        MW_BTN_NEXT.shouldBe(visible).pressEscape();
        checkTextNotPresent("You are about to be taken to Pekama's case management platform.");
    }

    @Test
    public void redirectBackFromHeaderLogin() {
        sleep(1500);
        String urlBeforeLogin = getActualUrl();
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible).click();
        User user = new User();
        user.submitLoginCredentials(email, password);

        sleep(2000);
        rootLogger.info("Check default selection");
        checkWizard1StepSelection(
                MATTER_TYPE_PATENT,
                Countries.ALL.getValue(),
                "Choose supplier type...");
        String urlAfterLogin = getActualUrl();
        rootLogger.info(urlAfterLogin);
        assertEquals(urlBeforeLogin, urlAfterLogin);
        rootLogger.info("User redirected back to Wizard");
        Assert.assertTrue(logoutCommunity());
        rootLogger.info("Test Passed");
    }

    @Test
    public void checkWizardLoginRedirect() {
        sleep(1500);
        String urlBeforeLogin = getActualUrl();
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        COMMUNITY_TAB_Supplier.shouldBe(Condition.visible).shouldHave(Condition.text("find a supplier")).click();

        COMMUNITY_INNRER_BTN_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible).click();
        User user = new User();
        user.submitLoginCredentials(email, password);

        sleep(3000);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(urlBeforeLogin, urlAfterLogin);
        rootLogger.info("User redirected back to Wizard");

        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 10000).shouldBe(disabled);
        rootLogger.info("All elements in STEP#1 displayed for authorized user");

        Assert.assertTrue(logoutCommunity());
        rootLogger.info("Test Passed");
    }

    @Test
    public void checkOutgoingLoginRedirect() {
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        COMMUNITY_TAB_Outgoing.shouldBe(Condition.visible).shouldHave(Condition.text("outgoing cases")).click();
        COMMUNITY_INNRER_BTN_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible).click();
        rootLogger.info("All elements in Outgoing Tab displayed for Guest user");
        User user = new User();
        user.submitLoginCredentials(email, password);

        sleep(3000);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(URL_COMMUNITY_OUTGOING, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");

        Assert.assertTrue(logoutCommunity());
        rootLogger.info("Test Passed");

    }
    @Test
    public void checkIncomingLoginRedirect() {
        COMMUNITY_HEADER_LOGIN.shouldBe(Condition.visible);
        COMMUNITY_TAB_Incoming.shouldBe(Condition.visible).shouldHave(Condition.text("incoming cases")).click();
        COMMUNITY_INNRER_BTN_SIGNUP.shouldBe(Condition.visible);
        COMMUNITY_INNRER_BTN_LOGIN.shouldBe(Condition.visible).click();
        rootLogger.info("All elements in Incoming Tab displayed for Guest user");
        User user = new User();
        user.submitLoginCredentials(email, password);

        sleep(3000);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(URL_COMMUNITY_INCOMING, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        Assert.assertTrue(logoutCommunity());
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
        User user = new User();
        user.submitLoginCredentials(email, password);

        sleep(3000);
        String urlAfterLogin = url();
        rootLogger.info(urlAfterLogin);
        assertEquals(URL_COMMUNITY_PROFILE_TEAM, urlAfterLogin);
        rootLogger.info("User redirected back to Incoming");
        Assert.assertTrue(logoutCommunity());
        rootLogger.info("Test Passed");
    }
}
