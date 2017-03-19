package Tests;
import Page.PekamaTeamSettings;
import Page.TestsCredentials;
import Page.TestsCredentials.*;
import Steps.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.ModalWindows.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaTemplates {
    static final Logger rootLogger = LogManager.getRootLogger();
    String TEAM = TestsCredentials.User3.TEAM_NAME.getValue();
    String TEST_USER_LOGIN = User3.GMAIL_EMAIL.getValue();
    String TEST_USER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void login() {
        rootLogger.info("Open URL - " +URL_Dashboard);
        openUrlWithBaseAuth(URL_Dashboard);
        StepsPekama login = new StepsPekama();
        login.submitLoginCredentials(TEST_USER_LOGIN);
        rootLogger.info("Redirect after login to - "+URL_Dashboard);
        sleep(1000);
    }
    @After
    public void logout(){openUrlWithBaseAuth(URL_Logout);}
    @AfterClass
    public static void afterClass() {
        clearBrowserCache();
    }
    @Test
    public void templateCrudProject() {
        String projectName;
        rootLogger.info("Open URL - " +URL_TEMPLATES_PROJECT);
        openPageWithSpinner(URL_TEMPLATES_PROJECT);

        rootLogger.info("Validation - Title and Type");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_Project_Title, "1");
        MW_Project_Title.clear();
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_BLANK_FIELD);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Validation - Blank Title and Required defining");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        selectItemInDropdown(
                MW_Project_SelectType,
                MW_Project_InputType,
                MATTER_TYPE_PATENT);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        checkText("This field may not be null.");
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Validation - Title max length");
        projectName = randomString(256);
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_Project_Title, projectName);
        selectItemInDropdown(
                MW_Project_SelectType,
                MW_Project_InputType,
                MATTER_TYPE_PATENT);
        selectItemInDropdown(
                MW_Project_SelectDefining,
                MW_Project_InputDefining,
                Countries.PITCAIRN_ISLANDS.getValue());
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Create template");
        projectName = "PROJECT_TEMPLATE_"+randomString(15);
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_Project_Title, projectName);
        selectItemInDropdown(
                MW_Project_SelectType,
                MW_Project_InputType,
                MATTER_TYPE_PATENT);
        selectItemInDropdown(
                MW_Project_SelectDefining,
                MW_Project_InputDefining,
                Countries.PITCAIRN_ISLANDS.getValue());
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        checkText(projectName);
        rootLogger.info("Delete template");
        if (SETTINGS_DELETE_X.isDisplayed()==false){
                Assert.fail("Project not created");
            }
        while (PekamaTeamSettings.SETTINGS_DELETE_X.isDisplayed()){
            SETTINGS_DELETE_X.click();
            submitConfirmAction();
            sleep(3000);
            if(SETTINGS_DELETE_X.isDisplayed()==false){
                break;
            }
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateCrudTask (){
        String setName = "SET_ALL_"+randomString(15);
        String templateName = "TEMPLATE_"+randomString(15);
        String templateDueDate = "10";
        rootLogger.info("Open URL - " +URL_TEMPLATES_TASKS);
        openPageWithSpinner(URL_TEMPLATES_TASKS);

        rootLogger.info("Create set relevant to ALL");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_TASK_SET_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_SET_NAME, setName);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        checkText(setName);
        templateRow.shouldHave(text(setName)).click();

        rootLogger.info("Create template");
        BTN_TEMPLATE_ADD_IN_1st_ROW.shouldBe(visible).click();
        waitForModalWindow(MW_TASK_TEMPLATE_TITLE);
        fillField(MW_TaskTemplate_FieldTitle, templateName);
        MW_TaskTemplate_Importance.click();
        MW_TaskImportanceDeadline.click();
        MW_TaskTemplate_Status.click();
        MW_TaskStatusNew.click();

        fillField(MW_TaskTemplate_DateOffset, templateDueDate);
        MW_TaskTemplate_DateOffsetUnit.selectOptionByValue("Days");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        checkText(templateName);

        rootLogger.info("Delete template");
        if (SETTINGS_DELETE_X.isDisplayed()==false){
            Assert.fail("Project not created");
        }
        while (PekamaTeamSettings.SETTINGS_DELETE_X.isDisplayed()){
            SETTINGS_DELETE_X.click();
            submitConfirmAction();
            sleep(3000);
            if(SETTINGS_DELETE_X.isDisplayed()==false){
                break;
            }
        }
        rootLogger.info("Test passed");
   }
    @Test
    public void templateCrudMessage () {
        String templateName = "TEMPLATE_"+randomString(15);
        String templateDueDate = "10";
        rootLogger.info("Open URL - " +URL_TEMPLATES_MSG);
        openPageWithSpinner(URL_TEMPLATES_MSG);

        rootLogger.info("Create message template relevant to ALL");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_MESSAGE_TEMPLATE_TITLE);
        // MW_BTN_OK.shouldBe(disabled); todo BUG
        fillField(MW_SET_NAME, templateName);
        MW_SET_TEXT_EDITOR.setValue(LOREM_IPSUM_SHORT);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        checkText(templateName);

        rootLogger.info("Delete template");
        if (SETTINGS_DELETE_X.isDisplayed()==false){
            Assert.fail("Project not created");
        }
        while (PekamaTeamSettings.SETTINGS_DELETE_X.isDisplayed()){
            SETTINGS_DELETE_X.click();
            submitConfirmAction();
            sleep(3000);
            if(SETTINGS_DELETE_X.isDisplayed()==false){
                break;
            }
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateCrudEvent (){
        String setName = "SET_ALL_"+randomString(15);
        String templateName = "TEMPLATE_"+randomString(15);
        String templateDueDate = "10";
        rootLogger.info("Open URL - " + URL_TEMPLATES_EVENT);
        openPageWithSpinner(URL_TEMPLATES_EVENT);

        rootLogger.info("Create set relevant to ALL");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_EVENT_SET_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_SET_NAME, setName);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        checkText(setName);
        templateRow.shouldHave(text(setName)).click();

        rootLogger.info("Create template");
        BTN_TEMPLATE_ADD_IN_1st_ROW.shouldBe(visible).click();
        waitForModalWindow(MW_EVENT_TEMPLATE_TITLE);
        selectItemInDropdown(
                MW_EVENT_SELECT_TYPE,
                MW_EVENT_INPUT_TYPE,
                TrademarkEvents.APPLICATION_REGISTERED.getValue()
        );
        fillField(MW_EVENT_INPUT_INFO, LOREM_IPSUM_SHORT);
        fillField(MW_EVENT_Template_DateOffset, templateDueDate);
        MW_EVENT_Template_DateOffsetUnit.selectOptionByValue("Days");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        TEMPLATES_TEXT_FIELD.shouldHave(value(LOREM_IPSUM_SHORT));

        rootLogger.info("Delete template");
        if (SETTINGS_DELETE_X.isDisplayed()==false){
            Assert.fail("Project not created");
        }
        while (PekamaTeamSettings.SETTINGS_DELETE_X.isDisplayed()){
            SETTINGS_DELETE_X.click();
            submitConfirmAction();
            sleep(3000);
            if(SETTINGS_DELETE_X.isDisplayed()==false){
                break;
            }
        }
        rootLogger.info("Test passed");
    }
}
