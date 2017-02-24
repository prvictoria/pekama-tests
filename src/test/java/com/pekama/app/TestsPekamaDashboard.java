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
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.CommunityLanding.*;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaHeader.*;
import static Page.PekamaLanding.*;
import static Page.PekamaPersonalSettings.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsStrings.*;
import static Page.UrlStrings.*;
import static Steps.StepsHttpAuth.httpAuthUrl;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String testProjectTitle = "new test project - "+ randomString(6);
    private static String testContactName = "name"+ randomString(10);
    private static String testContactSurname = "surname"+ randomString(10);
    private static String defaultProjectURL;
    private final static String USER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private final static String USER_PEKAMA_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private final static String USER_XERO_PASSWORD = User3.XERO_PASSWORD.getValue();
    private final String TEST_USER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private final String TEST_USER_TEAM_NAME = User1.TEAM_NAME.getValue();
    @BeforeClass
    public static void beforeClass() {
        assertionMode();
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                USER_EMAIL,
                USER_PEKAMA_PASSWORD,
                URL_LogIn);
    }
    @Before
    public void before() {
        httpAuthUrl(URL_Dashboard);
        sleep(2000);
    }
    @AfterClass
    public static void afterClass() {
        open(URL_Logout);
    }

    @Test
    public void testA_Gui() {
        DASHBOARD_ProjectsTitle.waitUntil(visible, 20000).shouldBe(visible);
        DASHBOARD_BTN_NEW_PROJECT.shouldBe(visible);
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
        rootLogger.info("Check that submit search leads redirect to Project reports page");

        rootLogger.info("");
    }

    @Test
    public void testD_OpenAllHeaderControls() {
        HEADER.waitUntil(visible, 20000);
        HEADER_DASHBOARD.waitUntil(visible, 20000).shouldBe(visible);
        HEADER_PROJECTS.shouldBe(visible);
        HEADER_TASKS.shouldBe(visible);
        HEADER_EVENTS.shouldBe(visible);
        HEADER_CHARGES.shouldBe(visible);
        HEADER_CONTACTS.shouldBe(visible);
        HEADER_TUTORIAL_BTN.shouldBe(visible);
        HEADER_SEARCH_FIELD.shouldBe(visible);
        HEADER_SEARCH_ICON.shouldBe(visible);

        HEADER_PROJECTS.click();
        REPORTS_PAGE_TITLE_PANEL
                .waitUntil(visible, 15000)
                .shouldHave(text("Projects"));
        HEADER_TASKS.click();
        REPORTS_PAGE_TITLE_PANEL
                .waitUntil(visible, 15000)
                .shouldHave(text("Tasks"));
        HEADER_PROJECTS.click();
        HEADER_EVENTS
                .waitUntil(visible, 15000)
                .shouldHave(text("Events"));
        HEADER_CHARGES.click();
        REPORTS_PAGE_TITLE_PANEL
                .waitUntil(visible, 15000)
                .shouldHave(text("Charges"));
        HEADER_CONTACTS.click();
        REPORTS_PAGE_TITLE_PANEL
                .waitUntil(visible, 15000)
                .shouldHave(text("Contacts"));
        HEADER_DASHBOARD.click();
        $(byXpath("//div[@class='panel-heading']//h4"))
                .waitUntil(visible, 15000);
        $$(byXpath("//div[@class='panel-heading']//h4"))
                .filter(visible)
                .shouldHaveSize(4);
        //HEADER_TUTORIAL_BTN.shouldBe(visible).click();
        rootLogger.info("Test passed");
    }

    @Test
    public void testE1_HeaderDropdownLogout() {
        HEADER.waitUntil(visible, 20000);
        HEADER_UserDropdown.shouldBe(visible).click();
        HEADER_LogOut.shouldBe(visible).click();
        submitConfirmAction();
        BTN_LOGIN.waitUntil(visible, 20000);
        BTN_SIGN_UP.shouldBe(visible);
        rootLogger.info("Test passed");
    }
    @Test
    public void testE2_HeaderDropdownOpenSettings() {
        rootLogger.info("Open personal settings");
        HEADER.waitUntil(visible, 20000);
        HEADER_UserDropdown.shouldBe(visible).click();
        HEADER_PersonalSettings.shouldBe(visible).click();
        PERSONAL_DETAILS_TAB_TITLE.waitUntil(visible, 20000);

        rootLogger.info("Open team settings");
        HEADER_UserDropdown.shouldBe(visible).click();
        HEADER_TeamSettings.shouldBe(visible).click();
        SETTINGS_TEAM_TAB_PROFILE.waitUntil(visible, 20000);
        rootLogger.info("Test passed");
    }
    @Ignore // TODO: 19-Feb-17
    @Test
    public void testF1_ModalNewProjectValidation() {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        rootLogger.info("");


        rootLogger.info("");
    }

}