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
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.PekamaReports.REPORTS_BTN_NEW_PROJECT_TEMPLATE;
import static Page.PekamaTeamSettings.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.*;
import static Tests.BeforeTestsSetUp.*;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
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
    static String templateProjectName;
    static String setName;
    static String templateName;
    static String templateDueDate;
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
    public void logout(){
        openUrlWithBaseAuth(URL_Logout);
        clearBrowserCache();}
    @Test
    public void templateProject_A_Validation() {
        String projectName;
        rootLogger.info("Open URL - " + URL_TEMPLATES_PROJECT);
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
        rootLogger.info("Test passed");
    }
    @Test
    public void templateProject_B_Create() {
        openPageWithSpinner(URL_TEMPLATES_PROJECT);
        rootLogger.info("Create template");
        templateProjectName = "PROJECT_IN_"+MATTER_TYPE_PATENT+"_"+randomString(15);
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_Project_Title, templateProjectName);
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
        checkText(templateProjectName);
        if (SETTINGS_DELETE_X.isDisplayed() == false) {
            Assert.fail("Project not created");
        }
    }
    @Test
    public void templateProject_C_DeployFromDashboard() {
        rootLogger.info("Deploy Project template from dashboard");
        String defaultProjectTitle = "New project (temporary name)";
        if (templateProjectName==null){Assert.fail("No project templates created");}

        DASHBOARD_BTN_PROJECT_TEMPLATES.waitUntil(visible, 20000).click();
        taskSelectProjectTemplateFormDropDown(templateProjectName);
        scrollUp();
        waitForTextPresent(defaultProjectTitle);
        rootLogger.info("Test passed");
    }
    @Test
    public void templateProject_D_DeployFromReports() {
        rootLogger.info("Deploy Project template from Project Reports");
        openPageWithSpinner(URL_ReportsProjects);
        rootLogger.info("Deploy Project template from dashboard");
        String defaultProjectTitle = "New project (temporary name)";
        if (templateProjectName==null){Assert.fail("No project templates created");}

        REPORTS_BTN_NEW_PROJECT_TEMPLATE.waitUntil(visible, 20000).click();
        taskSelectProjectTemplateFormDropDown(templateProjectName);
        scrollUp();
        waitForTextPresent(defaultProjectTitle);
        rootLogger.info("Test passed");
    }
    @Test
    public void templateProject_Z_Delete() {
        openPageWithSpinner(URL_TEMPLATES_PROJECT);
        rootLogger.info("Delete template");
        sleep(5000);
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
    public void templateTask_B1_Create () {
        setName = "SET_RELEVANT_TO_ALL_" + randomString(15);
        templateName = "TASK_TEMPLATE_" + randomString(15);
        templateDueDate = "10";
        rootLogger.info("Open URL - " + URL_TEMPLATES_TASKS_TRADEMARK);
        openPageWithSpinner(URL_TEMPLATES_TASKS_TRADEMARK);

        createTaskTemplateSet(setName);

        templateRow.shouldHave(text(setName)).click();
        TEMPLATES_AUTO_DEPLOY.shouldNotBe(selected);

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
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_B2_DeployInProject () {
        if (templateName==null){Assert.fail("No Task template");}
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
        String eventType = TrademarkEvents.MARK_CREATED.getValue();
        String projectName = createProject("TASK_DEPLOY_TEST_");
        taskDeploy(eventType, setName);
        Assert.assertTrue(verifyTaskFirstRow(
                templateName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_C1_CreateAutoDeploy () {
        setName = "SET_RELEVANT_TO_PitcairnIslands_" + randomString(15);
        templateName = "TASK_TEMPLATE_IN_PitcairnIslands_"+randomString(15);
        templateDueDate = "10";
        rootLogger.info("Open URL - "+URL_TEMPLATES_TASKS_TRADEMARK);
        openPageWithSpinner(URL_TEMPLATES_TASKS_TRADEMARK);

        rootLogger.info("Create Task Template set relevant to ALL");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_TASK_SET_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_SET_NAME, setName);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);

        createTaskTemplateSet(setName, Countries.PITCAIRN_ISLANDS.getValue());
        templateRow.shouldHave(text(setName)).click();
        TEMPLATES_AUTO_DEPLOY.shouldNotBe(selected).setSelected(true);
        TEMPLATES_AUTO_DEPLOY.shouldBe(selected);

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
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_C2_CheckAutoDeploy_Positive() {
        templateName = "TASK_TEMPLATE_IN_PitcairnIslands_R9ZG6KKV1KEQRSR";
        if (templateName==null){Assert.fail("No Task template");}
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
        String projectName = createProject(
                "TASK_AUTO_DEPLOY_",
                MATTER_TYPE_TRADEMARK,
                Countries.PITCAIRN_ISLANDS.getValue());
        PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();

        Assert.assertTrue(verifyTaskFirstRow(
                templateName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_NOT_STARTED,
                true)
        );
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_C3_CheckAutoDeploy_Negative() {
        templateName = "TASK_TEMPLATE_IN_PitcairnIslands_R9ZG6KKV1KEQRSR";
        if (templateName==null){Assert.fail("No Task template");}
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
        String projectName = createProject(
                "TASK_AUTO_DEPLOY_",
                MATTER_TYPE_TRADEMARK,
                Countries.NETHERLANDS_ANTILES.getValue());
        PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();

        TASKS_ROWS.shouldHaveSize(0);
        checkText(PLACEHOLDER_EMPTY_LIST);
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_Z_Delete (){
        openPageWithSpinner(URL_TEMPLATES_TASKS);
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
