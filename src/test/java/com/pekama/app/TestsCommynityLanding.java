package com.pekama.app;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;

import static Page.CommunityDashboard.*;
import static Page.CommunityLanding.*;
import static Page.PekamaLogin.*;
import static Page.PekamaSignUp.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.URL_COMMUNITY_LOGOUT;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;
import static com.pekama.app.AllTestsRunner.setBrowser;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class TestsCommynityLanding {
    static final Logger rootLogger = LogManager.getRootLogger();
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        openUrlWithBaseAuth(ENVIRONMENT_COMMUNITY);
    }
    @AfterClass
    public static void after() {
        openUrlWithBaseAuth(URL_COMMUNITY_LOGOUT);
        rootLogger.info("Open URL - "+URL_COMMUNITY_LOGOUT);
        clearBrowserCache();
    }

    @Test
    public void checkGui() {
        sleep(2000);
        LANDING_ABOUT.shouldBe(Condition.visible);
        LANDING_WHY.shouldBe(Condition.visible);
        LANDING_SIGNUP.shouldBe(Condition.visible);
        LANDING_LOGIN.shouldBe(Condition.visible);
        LANDING_EXPLORE_UPPER.shouldBe(Condition.visible);
        LANDING_EXPLORE_FOOTER.shouldBe(Condition.visible);
        rootLogger.info("Community landing elements checked and present");
    }
    @Test
    public void openSingUp() {
        LANDING_SIGNUP.shouldBe(Condition.visible).click();
        sleep(2000);
        $(signupAgree).shouldBe(visible).shouldBe(selected);
        $(signupNext).shouldBe(visible); //todo BUG -  .shouldBe(disabled);
        rootLogger.info("Sign up page was opened");
    }
    @Test
    public void openLogIn() {
        LANDING_LOGIN.shouldBe(Condition.visible).click();
        sleep(2000);
        loginButton_Login.shouldBe(visible);
        rootLogger.info("Login page was opened");
    }
    @Test
    public void clickExploreUpper() {
        LANDING_EXPLORE_UPPER.shouldBe(Condition.visible).click();
        sleep(2000);
        COMMUNITY_HEADER_MANAGEMENT.shouldBe(visible);
        rootLogger.info("Community Wizard page was opened");
    }
    @Test
    public void clickExploreFooter() {
        LANDING_EXPLORE_FOOTER.shouldBe(Condition.visible).click();
        sleep(2000);
        COMMUNITY_HEADER_MANAGEMENT.shouldBe(visible);
        rootLogger.info("Community Wizard page was opened");
    }
}