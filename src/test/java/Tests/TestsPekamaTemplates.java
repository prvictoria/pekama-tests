package Tests;
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
import static Page.PekamaConversationProject.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaPersonalSettings.*;
import static Page.PekamaProject.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsCredentials.Countries.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.Steps.clickSelector;
import static Steps.StepsModalWindows.*;
import static Steps.StepsModalWindows.modalDocumentTemplateOptions.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekama.UploadFiles.*;
import static Steps.StepsPekamaProject.*;
import static Steps.StepsPekamaSettings.checkTemplatesFilters;
import static Steps.StepsPekamaSettings.setAutoDeploy;
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
    static String messageTemplateName;

    static String[] defining = {AMERICAN_SAMOA.getValue(), PITCAIRN_ISLANDS.getValue(), NETHERLANDS_ANTILES.getValue()};
    static String[] type = {PatentTypes.CONVENTION.getValue(), PatentTypes.VALIDATION.getValue(), PatentTypes.CONTINUATION.getValue()};
    static String[] event = {PatentEvents.APPLICATION.getValue(), PatentEvents.CHANGE.getValue(), PatentEvents.WITHDRAW.getValue()};
    @Rule
    public Timeout tests = Timeout.seconds(500);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void login() {
        clearBrowserCache();
        rootLogger.info("Open URL - " +URL_Dashboard);
        User user = new User();
        user.loginByURL(TEST_USER_LOGIN, TEST_USER_PASSWORD, URL_Dashboard);
    }
//    @After
//    public void logout(){
//        openUrlWithBaseAuth(URL_Logout);
//    }
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
                PITCAIRN_ISLANDS.getValue());
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
                PITCAIRN_ISLANDS.getValue());
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
        deleteTemplate();
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_A1_Validation () {
        openPageWithSpinner(URL_TEMPLATES_TASKS_PATENT);
        rootLogger.info("Validation - Check if title is required");
        createTaskTemplateSet(
                null,
                defining[0],
                type[0],
                event[0]);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        MW_BTN_CANCEL.click();
        checkTextNotPresent(ERROR_MSG_REQUIRED_FIELD);
    }
    @Test
    public void templateTask_B1_Create() {
        templateName = null;
        setName = null;
        setName = "SET_RELEVANT_TO_ALL_";
        templateName = "TASK_TEMPLATE_";
        templateDueDate = "10";
        rootLogger.info("Open URL - " + URL_TEMPLATES_TASKS_TRADEMARK);
        openPageWithSpinner(URL_TEMPLATES_TASKS_TRADEMARK);

        setName = createTaskTemplateSet(setName);

        templateRow.shouldHave(text(setName)).click();
        TEMPLATES_AUTO_DEPLOY.shouldNotBe(selected);

        templateName = createTaskTemplate(templateName, templateDueDate);
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_B2_DeployInProject() {
        if (templateName==null){Assert.fail("No Task template");}
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
        String eventType = TrademarkEvents.MARK_CREATED.getValue();
        String projectName = submitMwNewProject("TASK_DEPLOY_TEST_");
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
    public void templateTask_C1_CreateAutoDeploy() {
        templateName = null;
        setName = null;
        setName = "SET_RELEVANT_TO_PitcairnIslands_";
        templateName = "TASK_TEMPLATE_IN_PitcairnIslands_";
        templateDueDate = "10";
        rootLogger.info("Open URL - "+URL_TEMPLATES_TASKS_TRADEMARK);
        openPageWithSpinner(URL_TEMPLATES_TASKS_TRADEMARK);

        setName = createTaskTemplateSet(
                setName, PITCAIRN_ISLANDS.getValue());
        setAutoDeploy(true);
        templateName = createTaskTemplate (templateName, templateDueDate);
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_C2_CheckAutoDeploy_Positive() {
        if (templateName==null){Assert.fail("No Task template");}
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
        String projectName = submitMwNewProject(
                "TASK_AUTO_DEPLOY_",
                MATTER_TYPE_TRADEMARK,
                PITCAIRN_ISLANDS.getValue());
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
        if (templateName==null){Assert.fail("No Task template");}
        try {
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
        String projectName = submitMwNewProject(
                "TASK_AUTO_DEPLOY_",
                MATTER_TYPE_TRADEMARK,
                NETHERLANDS_ANTILES.getValue());
        PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();

        TASKS_ROWS.shouldHaveSize(0);
        checkText(PLACEHOLDER_EMPTY_LIST);}
        finally {
            openPageWithSpinner(URL_TEMPLATES_TASKS_TRADEMARK);
            deleteTemplate();
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_D1_CreateTemplateSetParametrized (){
        openPageWithSpinner(URL_TEMPLATES_TASKS_PATENT);
        rootLogger.info("Check if user able to createPerson set with custom DEFINING");
        setName = "SET_TASKS_WITH_DEFINING_";
        createTaskTemplateSet(
                setName,
                defining[0],
               null,
               null);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom TYPE");
        setName = "SET_TASKS_WITH_TYPE_";
        createTaskTemplateSet(
                setName,
                null,
                type[0],
                null);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom EVENT");
        setName = "SET_TASKS_WITH_EVENT";
        createTaskTemplateSet(
                setName,
                null,
                null,
                event[0]);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom All fields");
        setName = "SET_TASKS_ALL_CUSTOM_";
        createTaskTemplateSet(
                setName,
                defining[1],
                type[1],
                event[1]);
        templateRow.shouldHave(text(setName));
        rootLogger.info("Test passed");
    }
    @Test
    public void templateTask_D2_CheckFilters (){
        openPageWithSpinner(URL_TEMPLATES_TASKS_PATENT);
        try {
            checkTemplatesFilters(
                    null,
                    null,
                    null,
                    4);
            checkTemplatesFilters(
                    defining[2],
                    null,
                    null,
                    2);
            checkTemplatesFilters(
                    null,
                    type[2],
                    null,
                    2);
            checkTemplatesFilters(
                    null,
                    null,
                    event[2],
                    2);
            checkTemplatesFilters(
                    defining[1],
                    type[1],
                    event[1],
                    1);
        }
        finally {
            deleteTemplate();
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateMessage_A1_Validation () {
        String setName = null;
        String textMsg = LOREM_IPSUM_SHORT;
        openPageWithSpinner(URL_TEMPLATES_MSG_TRADEMARK);
        rootLogger.info("Validation - Check if title is required");
        createMessageTemplateSet(
                null,
                null,
                null,
                null,
                textMsg);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        MW_BTN_CANCEL.click();
        checkTextNotPresent(ERROR_MSG_REQUIRED_FIELD);

        setName = "MESSAGE_T_VALIDATION_";
        rootLogger.info("Validation - Check if msg body is required");
        createMessageTemplateSet(
                setName,
                null,
                null,
                null,
                null);
        checkText(ERROR_MSG_BLANK_FIELD);
        MW_BTN_CANCEL.click();
        checkTextNotPresent(ERROR_MSG_BLANK_FIELD);
        rootLogger.info("Test passed");
    }
    @Test
    public void templateMessage_B1_Create () {
        try {
            rootLogger.info("Open URL - " + URL_TEMPLATES_MSG);
            openPageWithSpinner(URL_TEMPLATES_MSG_TRADEMARK);
            String textMsg = LOREM_IPSUM_SHORT;
            rootLogger.info("Create message template relevant to ALL");
            String templateName = "MESSAGE_T_";
            messageTemplateName = createMessageTemplateSet(
                    templateName,
                    null,
                    null,
                    null,
                    textMsg);
            templateRow.shouldHave(text(messageTemplateName));
            TEXT_EDITOR.shouldHave(text(textMsg));

        }
        finally {
            //deleteTemplate();
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateMessage_B2_Deploy () {
        //messageTemplateName = "MESSAGE_T_9C0675CCF2N9RUH";
        if(messageTemplateName==null){Assert.fail("Message template not created");}
        try {
            DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
            submitMwNewProject(
                    "MSG_TEMPLATE_TEST_",
                    MATTER_TYPE_TRADEMARK,
                    Countries.PITCAIRN_ISLANDS.getValue());
            scrollUp();
            createConversationTeam("DEPLOY_TEST_");
            deployMessageTemplate(messageTemplateName, 1);
            submitEnabledButton(CONVERSATION_BTN_POST);
            MESSAGES_LIST.filter(visible).shouldHaveSize(1);
            MESSAGE_FIRST_TEXT.shouldHave(text(LOREM_IPSUM_SHORT));
        }
        finally {
            openPageWithSpinner(URL_TEMPLATES_MSG_TRADEMARK);
            deleteTemplate();
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateMessage_D1_CreateTemplateSetParametrized (){
        String setName = null;
        String textMsg = LOREM_IPSUM_SHORT;
        openPageWithSpinner(URL_TEMPLATES_MSG_PATENT);
//        rootLogger.info("Clear all templates before test");
//        deleteTemplate();

        rootLogger.info("Check if user able to createPerson set with custom DEFINING");
        setName = "MESSAGE_T_DEFINIG_";
        createMessageTemplateSet(
                setName,
                defining[0],
                null,
                null,
                textMsg);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom TYPE");
        setName = "MESSAGE_T_TYPE_";
        createMessageTemplateSet(
                setName,
                null,
                type[0],
                null,
                textMsg);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom EVENT");
        setName = "MESSAGE_T_EVENT_";
        createMessageTemplateSet(
                setName,
                null,
                null,
                event[0],
                textMsg);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom All fields");
        setName = "MESSAGE_T_ALL_CUSTOM_";
        createMessageTemplateSet(
                setName,
                defining[1],
                type[1],
                event[1],
                textMsg);
        templateRow.shouldHave(text(setName));
        rootLogger.info("Test passed");
    }
    @Test
    public void templateMessage_D2_CheckFilters (){
        openPageWithSpinner(URL_TEMPLATES_MSG_PATENT);
        try {
            checkTemplatesFilters(
                    null,
                    null,
                    null,
                    4);
            checkTemplatesFilters(
                    defining[2],
                    null,
                    null,
                    2);
            checkTemplatesFilters(
                    null,
                    type[2],
                    null,
                    2);
            checkTemplatesFilters(
                    null,
                    null,
                    event[2],
                    2);
            checkTemplatesFilters(
                    defining[1],
                    type[1],
                    event[1],
                    1);
        }
        finally {
            deleteTemplate();
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateEvent_A1_Validation () {
        openPageWithSpinner(URL_TEMPLATES_EVENT_PATENT);
        rootLogger.info("Validation - Check if title is required");
        createEventTemplateSet(
                null,
                defining[0],
                type[0],
                event[0]);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        MW_BTN_CANCEL.click();
        checkTextNotPresent(ERROR_MSG_REQUIRED_FIELD);
    }
    @Test
    public void templateEvent_B1_Create (){
        String setName = "SET_RELEVANT_TO_ALL_";
        String templateName = "TEMPLATE_"+randomString(15);
        String templateDueDate = "10";
        String eventInfo = LOREM_IPSUM_SHORT;
        String dateOffsetUnit = "Days";
        rootLogger.info("Open URL - " + URL_TEMPLATES_EVENT_TRADEMARK);
        openPageWithSpinner(URL_TEMPLATES_EVENT_TRADEMARK);

        rootLogger.info("Create set relevant to ALL");
        createEventTemplateSet(
                setName,
                null,
                null,
                null);
        templateRow.shouldHave(text(setName)).click();

        createEventTemplate(
                templateName,
                TrademarkEvents.OPPOSITION_END_DATE.getValue(),
                eventInfo,
                templateDueDate,
                dateOffsetUnit);
        TEMPLATES_TEXT_FIELD.shouldHave(value(LOREM_IPSUM_SHORT));
        rootLogger.info("Test passed");
    }
    @Test
    public void templateEvent_B2_DeployInProject() {
        String eventType = TrademarkEvents.OPPOSITION_END_DATE.getValue();
        templateName = "";
        if (templateName==null){Assert.fail("No Task template");}
        try {
            DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
            String projectName = submitMwNewProject("TASK_DEPLOY_TEST_");
            checkDeployedEvent(eventType, LOREM_IPSUM_SHORT);
            }
        finally {
            openPageWithSpinner(URL_TEMPLATES_EVENT_TRADEMARK);
            deleteTemplate();
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateEvent_C1_CreateWithCustomRelevant (){
        String setName = "SET_RELEVANT_TO_CUSTOM_";
        String templateName = "MSG_CUSTOM_TEMPLATE_";
        String templateDueDate = "10";
        String eventInfo = LOREM_IPSUM_SHORT;
        String dateOffsetUnit = "Days";
        rootLogger.info("Open URL - " + URL_TEMPLATES_EVENT_TRADEMARK);
        openPageWithSpinner(URL_TEMPLATES_EVENT_TRADEMARK);

        rootLogger.info("Create set relevant to ALL");
        createEventTemplateSet(
                setName,
                defining[1],
                TrademarkTypes.CONVENTION.getValue(),
                TrademarkEvents.MARK_CREATED.getValue());
        templateRow.shouldHave(text(setName)).click();

        createEventTemplate(
                templateName,
                TrademarkEvents.APPLICATION_REGISTERED.getValue(),
                eventInfo,
                templateDueDate,
                dateOffsetUnit);
        TEMPLATES_TEXT_FIELD.shouldHave(value(LOREM_IPSUM_SHORT));
        rootLogger.info("Test passed");
    }
    @Test
    public void templateEvent_C2_DeployInProject() {
        String eventType = TrademarkEvents.APPLICATION_REGISTERED.getValue();
        templateName = "";
        if (templateName==null){Assert.fail("No Task template");}
        try {
            DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
            String projectName = submitMwNewProject("TASK_DEPLOY_TEST_");
            setProjectType(TrademarkTypes.CONVENTION.getValue());
            clickPlusButtonNewEvent();
            deployEvent(TrademarkEvents.MARK_CREATED.getValue());
            checkDeployedEvent(eventType, LOREM_IPSUM_SHORT);
        }
        finally {
            openPageWithSpinner(URL_TEMPLATES_EVENT_TRADEMARK);
            deleteTemplate();
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateEvent_D1_CreateTemplateSetParametrized (){
        openPageWithSpinner(URL_TEMPLATES_EVENT_PATENT);
        rootLogger.info("Check if user able to createPerson set with custom DEFINING");
        setName = "SET_EVENTS_WITH_DEFINING_";
        createEventTemplateSet(
                setName,
                defining[0],
                null,
                null);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom TYPE");
        setName = "SET_EVENTS_WITH_TYPE_";
        createEventTemplateSet(
                setName,
                null,
                type[0],
                null);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom EVENT");
        setName = "SET_EVENTS_WITH_EVENT";
        createEventTemplateSet(
                setName,
                null,
                null,
                event[0]);
        templateRow.shouldHave(text(setName));

        rootLogger.info("Check if user able to createPerson set with custom All fields");
        setName = "SET_EVENTS_ALL_CUSTOM_";
        createEventTemplateSet(
                setName,
                defining[1],
                type[1],
                event[1]);
        templateRow.shouldHave(text(setName));
        rootLogger.info("Test passed");
    }
    @Test
    public void templateEvent_D2_CheckFilters (){
        openPageWithSpinner(URL_TEMPLATES_EVENT_PATENT);
        try {
            checkTemplatesFilters(
                    null,
                    null,
                    null,
                    4);
            checkTemplatesFilters(
                    defining[2],
                    null,
                    null,
                    2);
            checkTemplatesFilters(
                    null,
                    type[2],
                    null,
                    2);
            checkTemplatesFilters(
                    null,
                    null,
                    event[2],
                    2);
            checkTemplatesFilters(
                    defining[1],
                    type[1],
                    event[1],
                    1);
        }
        finally {
            deleteTemplate();
        }
        rootLogger.info("Test passed");
    }
    @Test
    public void templateDoc_A1_EmptyNameValidation() throws IOException {
        openPageWithSpinner(URL_TEMPLATES_DOC);
        submitEnabledButton(SETTINGS_VALUES_ADD);
        submitModalDocTemplate(CANCEL, PDF, false);
    }
    @Test
    public void templateDoc_A2_AbortUploadPdf() throws IOException {
        openPageWithSpinner(URL_TEMPLATES_DOC);
        submitEnabledButton(SETTINGS_VALUES_ADD);
        submitModalDocTemplate(ABORT_UPLOAD, PDF, false);
    }
    @Test
    public void templateDoc_A3_CreateDifferent() throws IOException {
        openPageWithSpinner(URL_TEMPLATES_DOC);
        try {
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, JPG, false);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, ICO, false);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, PNG, false);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, SVG, false);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, PDF, false);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, WORD, false);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, EXCEL, false);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, ZIP, false);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            submitModalDocTemplate(SUBMIT, GOOGLE, false);
        }
        finally {
            refresh();
            deleteTemplate();
        }
    }
    @Test
    public void templateDoc_A4_CreatePdfAutodeploy() throws IOException {
        try{
            templateName = null;
            openPageWithSpinner(URL_TEMPLATES_DOC_TRADEMARK);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            templateName = submitModalDocTemplate(SUBMIT, PDF, true);
            openUrlWithBaseAuth(URL_PEKAMA_DASHBOARD);
            submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);
            submitMwNewProject("Doc auto-deploy test");
            clickSelector(PROJECT_TAB_DOCS);
            checkText(StepsModalWindows.fileName);
        }
        finally {
            openPageWithSpinner(URL_TEMPLATES_DOC_TRADEMARK);
            deleteTemplate();
        }
    }

    @Test
    public void templateDoc_A5_CreatePdfManualdeploy() throws IOException {
        try{
            templateName = null;
            openPageWithSpinner(URL_TEMPLATES_DOC_TRADEMARK);
            submitEnabledButton(SETTINGS_VALUES_ADD);
            templateName = submitModalDocTemplate(SUBMIT, PDF, false);
            openUrlWithBaseAuth(URL_PEKAMA_DASHBOARD);
            submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);
            submitMwNewProject("Doc auto-deploy test");
            createFileInRoot(templateName, null);
            checkText(StepsModalWindows.fileName);
        }
        finally {
            openPageWithSpinner(URL_TEMPLATES_DOC_TRADEMARK);
            deleteTemplate();
        }
    }
}
