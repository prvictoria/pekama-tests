package Steps;
import Page.PekamaTeamSettings;
import Page.TestsCredentials;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import static Page.ModalWindows.*;
import static Page.PekamaProject.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.URL_TEMPLATES_PROJECT;
import static Steps.StepsPekama.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
/**
 * Created by Viachaslau_Balashevi on 2/23/2017.
 */
public class StepsModalWindows implements StepsFactory {
    static final Logger rootLogger = LogManager.getRootLogger();
    public static boolean waitForModalWindow(String modalTitle) {
        rootLogger.info("Wait for '"+modalTitle+"' modal window");
        MW.waitUntil(visible, 15000).shouldBe(visible);
        //MW.should(matchText(modalTitle));
        MW.shouldHave(text(modalTitle));
        rootLogger.info("modal window '"+modalTitle+"' was opened");
        return true;
    }
    public static void submitConfirmAction(){
        sleep(1000);
        MW.waitUntil(visible, 15000);
        $(byText("Are you sure?")).shouldBe(Condition.visible);
        rootLogger.info("Confirm action modal window opened");
        MW_BTN_YES.shouldBe(visible).click();
    }
    public static void submitConfirmAction(String modalTitle){
        sleep(500);
        rootLogger.info("Wait for '"+modalTitle+"' modal window");
        MW.shouldBe(visible);
        MW.shouldHave(text(modalTitle));
        rootLogger.info("Confirm action modal '"+modalTitle+"' was opened");
        MW_BTN_YES.shouldBe(visible).click();
        sleep(500);
        MW.shouldNotBe(visible);
    }
    public static void submitErrorWindow(String modalTitle, String textMessage){
        sleep(500);
        rootLogger.info("Wait for '"+modalTitle+"' modal window");
        MW.shouldBe(visible);
        MW.shouldHave(text(modalTitle));
        MW.shouldHave(text(textMessage));
        rootLogger.info("Confirm action modal '"+modalTitle+"' was opened");
        MW_BTN_OK.shouldBe(visible).click();
        sleep(500);
        MW.shouldNotBe(visible);
    }
    public static String createProject(String projectCustomName) {
        String projectType = MATTER_TYPE_TRADEMARK;
        String projectDefining = TestsCredentials.Countries.PITCAIRN_ISLANDS.getValue();
        String projectName = projectCustomName+"_"+randomString(10);
        waitForModalWindow(TILE_MW_PROJECT);
        rootLogger.info("Select project type, actual: "+projectType);
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, projectType);
        rootLogger.info("Select defining, actual: "+projectDefining);
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, projectDefining);
        rootLogger.info("Fill title");
        fillField(MW_Project_Title, projectName);
        submitEnabledButton(MW_ProjectFinishButton);
        MW.waitUntil(not(visible), 20000);
        sleep(1000);
        checkText(projectName);
        rootLogger.info("Created project: "+projectName);
        return projectName;
    }
    public static String createProject(String projectCustomName, String projectType, String projectDefining) {
        String projectName = projectCustomName+"_"+randomString(10);
        waitForModalWindow(TILE_MW_PROJECT);
        rootLogger.info("Select project type, actual: "+projectType);
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, projectType);
        rootLogger.info("Select defining, actual: "+projectDefining);
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, projectDefining);
        rootLogger.info("Fill title");
        fillField(MW_Project_Title, projectName);
        submitEnabledButton(MW_ProjectFinishButton);
        MW.waitUntil(not(visible), 20000);
        sleep(1000);
        checkText(projectName);
        rootLogger.info("Created project: "+projectName);
        return projectName;
    }
    public static String createProject() {
        String projectType = MATTER_TYPE_TRADEMARK;
        String projectDefining = TestsCredentials.Countries.PITCAIRN_ISLANDS.getValue();
        String projectName = "DEFAULT_PROJECT_"+randomString(10);
        waitForModalWindow(TILE_MW_PROJECT);
        rootLogger.info("Select project type, actual: "+projectType);
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, projectType);
        rootLogger.info("Select defining, actual: "+projectDefining);
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, projectDefining);
        rootLogger.info("Fill title");
        fillField(MW_Project_Title, projectName);
        submitEnabledButton(MW_ProjectFinishButton);
        MW.waitUntil(not(visible), 20000);
        sleep(1000);
        checkText(projectName);
        rootLogger.info("Created project: "+projectName);
        return projectName;
    }
    //in root in Project
    public static void createFileInRoot(SelenideElement fileType, String fileName) {
        PROJECT_TAB_DOCS.waitUntil(visible, 15000).click();
        TAB_DOCS_BTN_ADD.waitUntil(enabled, 15000).click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        modalWindowDeployFileTemplate(fileType, fileName);

    }
    public static void modalWindowDeployFileTemplate(SelenideElement fileType, String fileName) {
        waitForModalWindow(TITLE_MW_ADD_DOCUMENT);
        MW_DEPLOY_DOC_BTN_CREATE.shouldBe(disabled);
        fileType.shouldBe(Condition.visible).click();
        fillField(MW_DEPLOY_DOC_INPUT_FILE_NAME, fileName);
        submitEnabledButton(MW_DEPLOY_DOC_BTN_CREATE);
        MW.shouldNotBe(Condition.visible);
        $(byText(fileName)).shouldBe(Condition.visible);
        rootLogger.info(fileName+" - file present");
    }
    public static void modalWindowCreateFolder(String folderName) {
        waitForModalWindow(TITLE_MW_NEW_FOLDER);
        MW_BTN_SAVE.shouldBe(disabled);
        fillField(MW_NEW_FOLDER_INPUT_NAME, folderName);
        submitEnabledButton(MW_BTN_SAVE);
        MW.shouldNotBe(Condition.visible);
        $(byText(folderName)).shouldBe(Condition.visible);
        rootLogger.info(folderName+" - Folder present");
    }
    public static String createNumber() {
        String codeType = "Equinox code";
        String codeValue = "2000/17/55-asd";
        rootLogger.info("Create "+codeType+"with value - "+codeValue);
        PROJECT_TAB_INFO.waitUntil(visible, 15000).click();
        scrollDown();
        selectItemInDropdown(TAB_INFO_NumberNewSelect, TAB_INFO_NumberNewField, codeType);
        fillField(TAB_INFO_NumberReferenceField, codeValue);
        submitEnabledButton(TAB_INFO_NumberAdd);
        TAB_INFO_NumberRow01Type.shouldHave(text(codeType));
        rootLogger.info(codeType+" - Number was created");
        return codeValue;
    }
    public static String createClassification() {
        //default
        String classNumber = "12";
        String classDescripton = "Class description";
        String classType = "Up-to-date";
        PROJECT_TAB_INFO.waitUntil(visible, 15000).click();
        scrollDown();
        TAB_INFO_ClassesAdd.waitUntil(visible, 20000).click();
        waitForModalWindow(mwClasses_Title);
        MW_BTN_OK.shouldBe(disabled);
        mwClasses_SelectClassType.shouldHave(text(classType));
        fillField(mwClasses_FieldClass, classNumber);
        fillField(mwClasses_FieldDescription, classDescripton);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(classType)).shouldHaveSize(1);
        $$(byText(classDescripton)).shouldHaveSize(1);
        rootLogger.info(classDescripton+" - Class was created");
        return classType;
    }
    public static String createFolderInRoot(String folderName) {
        PROJECT_TAB_DOCS.waitUntil(visible, 15000).click();
        TAB_DOCS_BTN_ADD.waitUntil(enabled, 15000).click();
        rootLogger.info("Add folder");
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        modalWindowCreateFolder(folderName);
        rootLogger.info(folderName+" - Folder present");
        return folderName;
    }
    public static String createTask(String taskName) {
        PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();
        TAB_TASKS_ADD.waitUntil(enabled, 15000).click();
        TAB_TASKS_NEW_TASK.shouldBe(visible).click();
        waitForModalWindow(TITLE_MW_NEW_TASK);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_TASK_NAME, taskName);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(taskName)).shouldHaveSize(1);
        rootLogger.info(taskName+" - Task created");
        return taskName;
    }
    public static String createEvent(String eventTypeName) {
        scrollUp();
        rootLogger.info("Deploy new event");
        projectButtonPlus.shouldBe(visible).click();
        projectPlusNewEvent.shouldBe(visible).click();
        waitForModalWindow(TITLE_MW_EVENT);
        MW_BTN_SAVE.shouldBe(disabled);
        MW_INPUT_DATE.click();
        sleep(500);
        MW.click();
        fillField(MW_EVENT_INPUT_INFO, LOREM_IPSUM_SHORT);
        selectItemInDropdown(MW_EVENT_SELECT_TYPE, MW_EVENT_INPUT_TYPE, eventTypeName);
        submitEnabledButton(MW_BTN_SAVE);
        MW.shouldNotBe(visible);
        $$(byText(eventTypeName)).filter(visible).shouldHaveSize(1);
        rootLogger.info(eventTypeName+" - Event created");
        return eventTypeName;
    }
    public static String createCharge(String chargeType, String currency, String price) {
        PROJECT_TAB_CHARGES.waitUntil(visible, 15000).click();
        TAB_CHARGES_ADD.waitUntil(enabled, 15000).click();
        rootLogger.info("Create charge");
        waitForModalWindow(TITLE_MW_CHARGE);
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, chargeType);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, currency);
        fillField(MW_CHARGES_INPUT_PRICE, price);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNot(visible);
        return  chargeType;
    }
    public static void selectTeam(String... args) {
        String searchedRadio = String.format(MW_SHARE_PROJECT_SELECT_TEAM, args);
        $(byXpath(searchedRadio)).shouldBe(visible);
        $(byXpath(searchedRadio)).click();
    }
    //TEMPLATES
    public static String createTaskTemplateSet(String title){
        submitEnabledButton(SETTINGS_VALUES_ADD);

        rootLogger.info("Create Task Template set relevant to ALL");
        String setName = title+ randomString(15);
        waitForModalWindow(MW_TASK_SET_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_SET_NAME, setName);
        submitEnabledButton(MW_BTN_OK);
        MW.waitUntil(not(visible), 15000);

        checkText(setName);
        return setName;
    }
    public static String createTaskTemplateSet(String setName, String defining){
        submitEnabledButton(SETTINGS_VALUES_ADD);

        rootLogger.info("Create Task Template set relevant to defining: "+defining);
        String title = setName+randomString(15);
        waitForModalWindow(MW_TASK_SET_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_SET_NAME, title);
        fillField(MW_SET_MULTICHOICE_DEFINING, defining);
        sleep(1500);
        CSS_SelectHighlighted.click();
        sleep(1500);
        submitEnabledButton(MW_BTN_OK);
        MW.waitUntil(not(visible), 15000);

        checkText(title);
        return title;
    }

    public static String createTaskTemplateSet(String setName, String defining, String type, String event){
        submitEnabledButton(SETTINGS_VALUES_ADD);

        rootLogger.info("Create Task Template set");
        String title = setName+randomString(15);
        waitForModalWindow(MW_TASK_SET_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        if(setName!=null){
            fillField(MW_SET_NAME, title);}
        if(defining!=null){
            fillField(MW_SET_MULTICHOICE_DEFINING, defining);
            CSS_SelectHighlighted.click();}
        if(type!=null){
            fillField(MW_SET_MULTICHOICE_TYPE, type);
            CSS_SelectHighlighted.click();}
        if(event!=null){
            fillField(MW_SET_MULTICHOICE_EVENT, event);
            CSS_SelectHighlighted.click();}
        sleep(1500);
            submitEnabledButton(MW_BTN_OK);
        if(setName!=null) {
            MW.waitUntil(not(visible), 15000);}
        return title;
    }
    public static String createEventTemplateSet(String setName, String defining, String type, String event){
        submitEnabledButton(SETTINGS_VALUES_ADD);

        rootLogger.info("Create Event Template set");
        String title = setName+randomString(15);
        waitForModalWindow(MW_EVENT_SET_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        if(setName!=null){
            fillField(MW_SET_NAME, title);}
        if(defining!=null){
            fillField(MW_SET_MULTICHOICE_DEFINING, defining);
            CSS_SelectHighlighted.click();}
        if(type!=null){
            fillField(MW_SET_MULTICHOICE_TYPE, type);
            CSS_SelectHighlighted.click();}
        if(event!=null){
            fillField(MW_SET_MULTICHOICE_EVENT, event);
            CSS_SelectHighlighted.click();}
        sleep(1500);
        submitEnabledButton(MW_BTN_OK);
        if(setName!=null) {
            MW.waitUntil(not(visible), 15000);}
        return title;
    }
    public static String createMessageTemplateSet(String setName, String defining, String type, String event, String textMsg){
        submitEnabledButton(SETTINGS_VALUES_ADD);

        rootLogger.info("Create Message Template set");
        String title = setName+randomString(15);
        waitForModalWindow(MW_MESSAGE_TEMPLATE_TITLE);
        //MW_BTN_OK.shouldBe(disabled);
        if(setName!=null){
            fillField(MW_SET_NAME, title);}
        if(defining!=null){
            selectItemInDropdown(MW_MSG_TEMPLATE_SELECT_DEFINING, MW_MSG_TEMPLATE_INPUT_DEFINING, defining);}
        if(type!=null){
            selectItemInDropdown(MW_MSG_TEMPLATE_SELECT_TYPE, MW_MSG_TEMPLATE_INPUT_TYPE, type);}
        if(event!=null){
            selectItemInDropdown(MW_MSG_TEMPLATE_SELECT_EVENT, MW_MSG_TEMPLATE_INPUT_EVENT, event);}
        if(textMsg!=null){
            MW_SET_TEXT_EDITOR.setValue(textMsg);
            MW_SET_TEXT_EDITOR.setValue(textMsg);
        }
        sleep(1500);
        submitEnabledButton(MW_BTN_OK);
        if(setName!=null && textMsg!=null) {
            MW.waitUntil(not(visible), 15000);}
        return title;
    }
    public static boolean deleteTemplate(){
        rootLogger.info("Delete template");
        sleep(3000);
        if(!SETTINGS_DELETE_X.isDisplayed()){
            sleep(6000);
        }
        if (SETTINGS_DELETE_X.isDisplayed()==false){
            Assert.fail("Template not created");
        }
        int i = 0;
        while (PekamaTeamSettings.SETTINGS_DELETE_X.isDisplayed() && i<7){
            SETTINGS_DELETE_X.click();
            submitConfirmAction();
            sleep(4000);
            i++;
            if(SETTINGS_DELETE_X.isDisplayed()==false){
                return true;
            }
        }
        return false;
    }
    public static String createTaskTemplate (String templateName, String templateDueDate){
        String title = templateName+randomString(15);
        rootLogger.info("Create Task template with name: "+title);
        BTN_TEMPLATE_ADD_IN_1st_ROW.shouldBe(visible).click();

        waitForModalWindow(MW_TASK_TEMPLATE_TITLE);
        fillField(MW_TaskTemplate_FieldTitle, title);
        MW_TaskTemplate_Importance.click();
        MW_TaskImportanceDeadline.click();
        MW_TaskTemplate_Status.click();
        MW_TaskStatusNew.click();

        fillField(MW_TaskTemplate_DateOffset, templateDueDate);
        MW_TaskTemplate_DateOffsetUnit.selectOptionByValue("Days");
        submitEnabledButton(MW_BTN_OK);
        MW.waitUntil(not(visible), 15000);

        checkText(title);
        return title;
    }
    public static String createEventTemplate(String templateName, String eventType, String eventInfo, String templateDueDate, String dateOffsetUnit){
        String title = templateName+randomString(15);
        rootLogger.info("Create Event template with name: "+title);
        BTN_TEMPLATE_ADD_IN_1st_ROW.shouldBe(visible).click();
        waitForModalWindow(MW_EVENT_TEMPLATE_TITLE);
        if(templateName!=null) {
            selectItemInDropdown(
                    MW_EVENT_SELECT_TYPE,
                    MW_EVENT_INPUT_TYPE,
                    eventType);}
        if(eventInfo!=null) {
        fillField(MW_EVENT_INPUT_INFO, eventInfo);}
        if(templateDueDate!=null) {
        fillField(MW_EVENT_Template_DateOffset, templateDueDate);}
        if(dateOffsetUnit!=null) {
        MW_EVENT_Template_DateOffsetUnit.selectOptionByValue(dateOffsetUnit);}
        submitEnabledButton(MW_BTN_OK);
        if(templateName!=null && templateDueDate!=null && dateOffsetUnit!=null ) {
        MW.shouldNotBe(visible);}
        return title;
    }
    public static Integer checkTemplatesFilters(
            String defining[],
            Integer indexDefining,
            String type[],
            Integer indexType,
            String event[],
            Integer indexEvent,
            Integer listSize){
        rootLogger.info("Check template filters");
        refresh();
        SETTINGS_DELETE_X.waitUntil(visible, 15000);
        if(defining!=null && indexDefining!=null) {
            rootLogger.info("Select DEFINING filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_DEFINING,
                    TEMPLATES_FILTER_INPUT_DEFINING,
                    defining[indexDefining]);
        }
        if(type!=null && indexType!=null) {
            rootLogger.info("Select TYPE filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_TYPE,
                    TEMPLATES_FILTER_INPUT_TYPE,
                    type[indexType]
            );
        }
        if(event!=null && indexEvent!=null) {
            rootLogger.info("Select EVENT filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_EVENT,
                    TEMPLATES_FILTER_INPUT_EVENT,
                    event[indexEvent]
            );
        }
        if(listSize!=null) {
            rootLogger.info("Check templates list size");
            TEMPLATES_LIST.shouldHaveSize(listSize);}
        return listSize;
    }
}
