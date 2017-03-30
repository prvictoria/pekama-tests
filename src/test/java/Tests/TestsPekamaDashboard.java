package Tests;
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
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaHeader.*;
import static Page.PekamaLanding.*;
import static Page.PekamaPersonalSettings.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String testProjectTitle = "new test project - "+ randomString(6);
    private static String testContactName = "name"+ randomString(10);
    private static String testContactSurname = "surname"+ randomString(10);
    private static String defaultProjectURL;
    private static String TEST_PROJECT_TYPE = MATTER_TYPE_TRADEMARK;
    private final static String USER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private final static String USER_PEKAMA_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private final static String USER_XERO_PASSWORD = User3.XERO_PASSWORD.getValue();
    private final String TEST_USER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private final String TEST_USER_TEAM_NAME = User1.TEAM_NAME.getValue();
    @Rule
    public Timeout tests = Timeout.seconds(300);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment();
        setBrowser();
        holdBrowserAfterTest();
        TEST_PROJECT_TYPE = MATTER_TYPE_TRADEMARK;
    }
    @Before
    public void before() {
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                USER_EMAIL,
                USER_PEKAMA_PASSWORD,
                URL_LogIn);
    }
    @AfterClass
    public static void afterClass() {
        //open(URL_Logout);
        clearBrowserCache();
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
    @Test
    public void testC_RedirectGlobalSearch() {
        rootLogger.info("Check that submit search leads redirect to Project reports page");
        HEADER.waitUntil(visible, 20000);
        HEADER_SEARCH_FIELD.shouldBe(visible).shouldHave(value(""));
        fillField(HEADER_SEARCH_FIELD, "1234567890");
        HEADER_SEARCH_ICON.shouldBe(visible).click();
        REPORTS_PAGE_TITLE_PANEL
                .waitUntil(visible, 15000)
                .shouldHave(text("Projects"));
        rootLogger.info("Test passed");
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
    public void testZ_HeaderDropdownLogout() {
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
    @Test
    public void testF1_ModalNewProjectValidation() {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        submitMwNewProject(
                null,
                null,
                null,
                null,
                null);
        checkText(ERROR_MSG_REQUIRED_FIELD, 2);
        escapeModalWindow();
        rootLogger.info("Validation mandatory Type and Title passed");
    }
    @Test
    public void testF2_ModalNewProjectValidation() {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        rootLogger.info("");
        waitForModalWindow("New Project");
        selectItemInDropdown(
                MW_Project_SelectType,
                MW_Project_InputType,
                TEST_PROJECT_TYPE);
        sleep(2000);
        submitEnabledButton(MW_ProjectFinishButton);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        escapeModalWindow();
        rootLogger.info("Validation mandatory Title passed");
    }
    @Test
    public void testF3_ModalNewProjectValidation() {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        submitMwNewProject(
                null,
                MATTER_TYPE_PATENT,
                null,
                null,
                null);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        checkText("Application Number (optional):");

        rootLogger.info("Check default defining for "+MATTER_TYPE_PATENT);
        checkSelectedDefining("United Kingdom", "GB");

        String defaultDefiningPatent = MW_PROJECT_ACTUAL_DEFINING.getText();
        Assert.assertEquals("Check default defining for "+MATTER_TYPE_PATENT, "United Kingdom", defaultDefiningPatent);
        String defaultDefiningCodePatent = MW_PROJECT_ACTUAL_DEFINING_CODE.getText();
        Assert.assertEquals("Check default defining code for "+MATTER_TYPE_PATENT, "GB", defaultDefiningCodePatent);
        escapeModalWindow();
        rootLogger.info("Validation mandatory Defining and Title passed");

        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        submitMwNewProject(
                null,
                MATTER_TYPE_TRADEMARK,
                null,
                null,
                null);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        checkText("TM Serial Number (optional):");

        rootLogger.info("Check default defining for "+MATTER_TYPE_TRADEMARK);
        checkSelectedDefining("United States", "US");
        escapeModalWindow();
        rootLogger.info("Validation mandatory Defining and Title passed");

        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        submitMwNewProject(
                null,
                MATTER_TYPE_CRM,
                null,
                null,
                null);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        checkText(ERROR_MSG_NULL_NOT_VALID);

        rootLogger.info("Check that no default defining for other Matter types");
        String notDefaultDefining = MW_Project_SelectDefining.getText();
        Assert.assertEquals("Pick one...", notDefaultDefining);
        escapeModalWindow();
        rootLogger.info("Validation mandatory Defining and Title passed");
    }
    @Test
    public void testF4_ModalNewProjectValidation() {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        submitMwNewProject(
                randomString(1025),
                MATTER_TYPE_TRADEMARK,
                null,
                null,
                null);
        waitForModalWindow("New Project");
        submitEnabledButton(MW_ProjectFinishButton);
        checkText(ERROR_MSG_VALIDATION_LENGTH_1024);
        rootLogger.info("Validation Title max length passed");
    }

    @Test
    public void testF5_ModalNewProjectValidation() {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        submitMwNewProject(
                "VALID",
                MATTER_TYPE_TRADEMARK,
                null,
                randomString(256),
                null);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        rootLogger.info("Validation max length reference number - passed");
    }
    @Test
    public void testF6_ModalNewProjectValidation() {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        submitMwNewProject(
                "VALID",
                MATTER_TYPE_TRADEMARK,
                null,
                null,
                randomString(256));
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        rootLogger.info("Validation max length TM number - passed");
    }
    @Test
    public void testF7_ModalNewProjectValidation() {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        submitMwNewProject(
                "VALID",
                MATTER_TYPE_TRADEMARK,
                null,
                null,
                randomString(50));
        ExpectedConditions.alertIsPresent().wait(2000);
        confirm("Ok");
        try{confirm("Ok");}
        catch (org.openqa.selenium.TimeoutException e){
            rootLogger.info("Timeout Gateway prompt not present");}
        checkText("Official data not found. We will notify you once it become available. ");
        rootLogger.info("Wrong TM number check that tooltip present - passed");
    }
    @Test
    public void testF8_ModalNewProjectValidation() throws InterruptedException {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 30000).click();
        String projectName = submitMwNewProject(
                "VALID",
                MATTER_TYPE_TRADEMARK,
                null,
                null,
                "76686873");
        checkModalWindowNotPresent(70000);
//        try{confirm("Ok");}
//        catch (org.openqa.selenium.TimeoutException e){
//            rootLogger.info("Timeout Gateway prompt not present");}
        //checkText("Official data not found. We will notify you once it become available. ");
        checkText(projectName);
        rootLogger.info("Valid TM number check - passed");
    }
}