package com.pekama.app;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Page.TestsCredentials.User1;
import Page.TestsCredentials.User3;
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.TestsStrings.PAGE_TITLE_PEKAMA;
import static Page.TestsUrl.URL_LogIn;
import static Steps.StepsPekama.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String testProjectTitle = "new test project - "+ randomString(6);
    private static String testContactName = "name"+ randomString(10);
    private static String testContactSurname = "surname"+ randomString(10);
    private static String defaultProjectURL;
    private final static String TEST_USER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private final static String TEST_USER_PEKAMA_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private final static String TEST_USER_XERO_PASSWORD = User3.XERO_PASSWORD.getValue();
    private final String TEST_USER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private final String TEST_USER_TEAM_NAME = User1.TEAM_NAME.getValue();
    @Before
    public void before() {
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                TEST_USER_EMAIL,
                TEST_USER_PEKAMA_PASSWORD,
                URL_LogIn);
    }

    @Test
    public void testA_Gui() {
        DASHBOARD_ProjectsTitle.waitUntil(visible, 20000).shouldBe(visible);
        DASHBOARD_NewProject.shouldBe(visible);
        checkText("No projects yet. Create your first project");

        DASHBOARD_YourProfileTitle.shouldBe(visible);
        //DASHBOARD_TeamName.shouldHave(text(TEST_USER_TEAM_NAME));
        DASHBOARD_Invite.shouldBe(visible);
        DASHBOARD_BuyProjects.shouldBe(visible);

        DASHBOARD_UpcomingTitle.shouldBe(visible);
        DASHBOARD_UpcomingMyDeadlines_RADIO.shouldHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingMyTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        sleep(500);

        DASHBOARD_UpcomingAllDeadlines.click();
        DASHBOARD_UpcomingMyDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllDeadlines_RADIO.shouldHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingMyTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        sleep(500);

        DASHBOARD_UpcomingMyTasks.click();
        DASHBOARD_UpcomingMyDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingMyTasks_RADIO.shouldHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        sleep(500);

        DASHBOARD_UpcomingAllTasks.click();
        DASHBOARD_UpcomingMyDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingMyTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllTasks_RADIO.shouldHave(cssClass("active-calendar-filter"));

        DASHBOARD_TasksTitle.shouldBe(visible);
        DASHBOARD_TasksToDo.shouldBe(visible);
        checkText("No tasks yet. You can create new tasks from projects");
        DASHBOARD_TasksDoing.shouldBe(visible).click();
        checkText("No tasks yet. You can create new tasks from projects");
        sleep(500);

        rootLogger.info("GUI Test passed");
    }
    @Test
    public void testB_BuyProject() {
        DASHBOARD_BuyProjects.waitUntil(visible, 30000).click();
        rootLogger.info("Check MW Buy Projects");
        waitForModalWindow(MW_BUY_PROJECTS_TITLE);
        MW_BUY_PROJECTS_BTN.shouldBe(visible);
        MW_BUY_PROJECTS_InputQTY.shouldHave(value("5"));
        MW_BUY_PROJECTS_TotalPrice.shouldHave(text("$245"));
        MW_BUY_PROJECTS_Discount.shouldHave(text("Your Discount 0%"));

        MW_BUY_PROJECTS_BTN.click();
        MW_BUY_PROJECTS_BTN.shouldBe(disabled);
        sleep(4000);
        rootLogger.info("Qty selected passed");

        switchTo().frame("stripe_checkout_app");
        //switchTo().window("Stripe Checkout");
        MW_CHECKOUT.shouldBe(visible);
        MW_CHECKOUT_TITLE.shouldHave(text("5 MemoBoxes"));
        MW_CHECKOUT_CardNumberField.sendKeys("4242424242424242");
        MW_CHECKOUT_CardDate.sendKeys( "01 / 21");
        MW_CHECKOUT_CardCVV.sendKeys( "123");
        submitEnabledButton(MW_CHECKOUT_Submit);
        MW_CHECKOUT.waitUntil(not(visible), 15000);
        rootLogger.info("Checkout submitted");
        switchTo().window(PAGE_TITLE_PEKAMA);
        checkPageTitle(PAGE_TITLE_PEKAMA);
        rootLogger.info("Test passed");
    }
    @Ignore // TODO: 19-Feb-17
    @Test
    public void testC_RedirectGlobalSearch() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Ignore // TODO: 19-Feb-17
    @Test
    public void testD_OpenAllHeaderControls() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Ignore // TODO: 19-Feb-17
    @Test
    public void testE_LogoutViaDropdown() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

}