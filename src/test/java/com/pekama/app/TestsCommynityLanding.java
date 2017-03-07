package com.pekama.app;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;

import static Page.CommunityDashboard.*;
import static Page.CommunityLanding.*;
import static Page.ModalWindows.*;
import static Page.PekamaLogin.*;
import static Page.PekamaSignUp.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.URL_COMMUNITY_LOGOUT;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsPekama.checkText;
import static Steps.StepsPekama.hideZopim;
import static Steps.StepsPekama.submitCookie;
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

    @BeforeClass
    public static void beforeClass() {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        openUrlWithBaseAuth(ENVIRONMENT_COMMUNITY);
        submitCookie();
        hideZopim();
    }
//    @AfterClass
//    public static void after() {
//        openUrlWithBaseAuth(URL_COMMUNITY_LOGOUT);
//        rootLogger.info("Open URL - "+URL_COMMUNITY_LOGOUT);
//        clearBrowserCache();
//    }

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
    @Test
    public void openTour() {
        LANDING_SIGNUP.shouldBe(Condition.visible);
        sleep(2000);

        rootLogger.info("Open tour");
        LANDING_TAKE_TOUR.shouldBe(visible).click();
        MW.shouldBe(visible);
        String textStep1 = MW.getText();
        //rootLogger.info(textStep1);
        Assert.assertEquals(textStep1, "The Pekama IP Community\n" +
                "The Pekama IP community is a revolutionary online community that brings together patent and trademark experts from over 20 countries and helps them grow their networks and manage their work, using state-of-the-art technology.\n" +
                "The Pekama IP Community is ideal for both SENDING and RECEIVING IP work\n" +
                "Next");

//        checkText("The Pekama IP community is a revolutionary online community that brings together patent and trademark experts from over 20 countries and helps them grow their networks and manage their work, using state-of-the-art technology.");
//        checkText("The Pekama IP Community is ideal for both SENDING and RECEIVING IP work ");
        MW_TOUR_BTN_NEXT.shouldBe(visible).click();

        String textStep2 = MW.getText();
        //rootLogger.info(textStep2);
        Assert.assertEquals(textStep2,"Find new foreign associates in 2 minutes\n" +
                "Back\n" +
                "Next");
        MW_TOUR_BTN_BACK.shouldBe(visible);
        MW_TOUR_BTN_NEXT.shouldBe(visible).click();

        String textStep3 = MW.getText();
        //rootLogger.info(textStep3);
        Assert.assertEquals(textStep3,"Get preferential and transparent terms\n" +
                "Back\n" +
                "Next");
        MW_TOUR_BTN_BACK.shouldBe(visible);
        MW_TOUR_BTN_NEXT.shouldBe(visible).click();


        String textStep4 = MW.getText();
        //rootLogger.info(textStep4);
        Assert.assertEquals(textStep4,"Sending work to community members\n" +
                "See all your outgoing cases and their status in one place\n" +
                "Back\n" +
                "Next");
        MW_TOUR_BTN_BACK.shouldBe(visible);
        MW_TOUR_BTN_NEXT.shouldBe(visible).click();

        String textStep5 = MW.getText();
        //rootLogger.info(textStep5);
        Assert.assertEquals(textStep5,"Enhance your chances to receive reciprocal work from the entire community\n" +
                "Back\n" +
                "Finish");
        MW_TOUR_BTN_BACK.shouldBe(visible);
        MW_TOUR_BTN_FINISH.shouldBe(visible).click();

        MW.shouldNotBe(visible);
        rootLogger.info("Test passed");

    }
}