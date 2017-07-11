package Steps;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


import static Pages.CommunityDashboard.*;
import static Pages.CommunityWizard.BTN_SEND_INSTRUCTION;
import static Pages.CommunityWizard.WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS;
import static Pages.ModalWindows.*;
import static Pages.PekamaConversationProject.*;
import static Pages.PekamaDashboard.*;
import static Pages.PekamaProject.*;

import static Pages.PekamaProjectCharges.*;
import static Pages.DataStrings.*;
import static Pages.UrlConfiguration.INTRODUCER_NAME;
import static Pages.UrlStrings.*;
import static Steps.Steps.clickSelector;
import static Steps.StepsCommunity.*;
import static Steps.StepsModalWindows.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by VatslauX on 02-May-17.
 */
public class StepsPekamaProject extends StepsPekama {

    //in root in Project
    public static void deleteAllFiles(){
        rootLogger.info("Delete all files");
        projectAllCheckbox.click();
        LINK_DELETE.click();
        submitConfirmAction();
        checkText(PLACEHOLDER_NoFiles);
        rootLogger.info(PLACEHOLDER_NoFiles);
    }
    public static String uploadFileInRoot(ObjectFile file, boolean allTeamsIsSelected, Boolean submitModal) {
        callUploadFilesModal();
        String fileName = uploadModalUploadFiles(file);
        selectAllTeams(allTeamsIsSelected);
        submitModalUploadFiles(submitModal);
        checkText(fileName);
        rootLogger.info(fileName+" - file present");
        return fileName;
    }
    public static String createFileInRoot(SelenideElement fileType, String fileName) {
        callNewDocModal();
        submitModalDeployFileTemplate(fileType, fileName);
        checkText(fileName);
        rootLogger.info(fileName+" - file present");
        return fileName;
    }
    public static String createFileInRoot(String templateName, String fileName) {
        callNewDocModal();
        submitModalDeployFileTemplate(templateName, fileName);
        checkText(fileName);
        rootLogger.info(fileName+" - file present");
        return fileName;
    }
    public static String createFolderInRoot(String folderName) {
        callNewFolderModal();
        submitModalCreateFolder(folderName);
        checkText(folderName);
        rootLogger.info(folderName+" - folder present");
        return folderName;
    }
    public static void callNewDocModal(){
        clickSelector(PROJECT_TAB_DOCS);
        clickSelector(TAB_DOCS_BTN_ADD);
        clickSelector(TAB_DOC_NEW_DOCUMENT);
    }
    public static void callNewFolderModal(){
        clickSelector(PROJECT_TAB_DOCS);
        clickSelector(TAB_DOCS_BTN_ADD);
        clickSelector(TAB_DOC_ADD_FOLDER);
    }
    public static void callUploadFilesModal(){
        clickSelector(PROJECT_TAB_DOCS);
        clickSelector(TAB_DOCS_BTN_ADD);
        clickSelector(TAB_DOC_UPLOAD);
    }

    public static void fileMenuMakeAction(String actionName, String... args) {
        String menu = String.format(TAB_DOCS_FILES_MENU_OPEN, args);
        $(byXpath(menu)).shouldBe(visible);
        $(byXpath(menu)).click();
        String action = String.format(actionName, args);
        $(byXpath(action)).shouldBe(visible);
        $(byXpath(action)).click();
        rootLogger.info("Action done");
    }
    public static void clickFileRow(String... args) {
        String row = String.format(TAB_DOCS_FILES_EXPAND_FILE, args);
        $(byXpath(row)).shouldBe(visible);
        $(byXpath(row)).click();
        rootLogger.info(args+" - row opened");
    }
    public static void clickFolderRow(String... args) {
        String row = String.format(TAB_DOCS_FILES_EXPAND_FOLDER, args);
        $(byXpath(row)).shouldBe(visible);
        $(byXpath(row)).click();
        rootLogger.info(args+" - row opened");
    }

    public static boolean deleteProject(){
        rootLogger.info("Delete Pekama project");
        scrollUp();
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        submitConfirmAction();
        String url = URL_DASHBOARD;
        if (url.equals(getActualUrl())){
            return true;
        }
        else return false;
    }

// INFO TAB =====================================================================
    public static String setProjectDefining(String defining){
        if(defining!=null) {
            selectItemInDropdown(
                    TAB_INFO_SELECT_Defining,
                    TAB_INFO_INPUT_Defining,
                    defining);
            sleep(500);
            checkText(defining);
            return defining;
        }
        return null;
    }
    public static String setProjectType(String type){
        if(type!=null) {
            selectItemInDropdown(
                    TAB_INFO_SELECT_Type,
                    TAB_INFO_INPUT_Type,
                    type);
            sleep(500);
            checkText(type);
            return type;
        }
        return null;
    }
    public static String setProjectSubType(String subType){
        if(subType!=null) {
            selectItemInDropdown(
                    TAB_INFO_SELECT_SubType,
                    TAB_INFO_INPUT_SubType,
                    subType);
            sleep(500);
            checkText(subType);
            return subType;
        }
        return null;
    }

    public static void clickStartNewCase(){
        checkText("No community cases.");
        TAB_INFO_COMMUNITY_TITLE.waitUntil(visible, 20000).shouldHave(text("Services from the Pekama IP Community"));
        rootLogger.info("Check redirect to Community Wizard");
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        checkThatWindowsQtyIs(2);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("No redirect to Community");
        }
        //TODO wizard assert
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);
    }
    public static void switchToPekamaWindow(String projectUrl){
        rootLogger.info("Open Pekama");
        switchTo().window(PAGE_TITLE_PEKAMA);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Community");
        }
        String actualUrl = getActualUrl();
        Assert.assertEquals
                ("Opened url not same to the project url", projectUrl, actualUrl);
        refresh();
    }
    public static void redirectToCommunityFormCaseRow(){
        rootLogger.info("Check redirect to Community after click case row");
        PROJECT_TAB_INFO.shouldBe(visible).click();
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        sleep(2000);
        checkThatWindowsQtyIs(3);
        switchToCommunityWindow();
        //TODO new UI
        BTN_SEND_INSTRUCTION.waitUntil(visible, 20000);
        sleep(1000);
    }
    public static Boolean checkProjectCommunityCase(ObjectCase objectCase){
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).shouldBe(visible);

        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(objectCase.caseTitle));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(objectCase.caseMatterType));
        //todo logic if different statuses
        if(objectCase.caseStatus.equals("asdsasasas")){
            TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text("cancel case"));
            TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(objectCase.caseStatus));
        }

        return true;
    }
    public static Boolean checkCollaborators(ObjectUser owner, ObjectUser expert){
        rootLogger.info("Check project members");
        PROJECT_TAB_CONTACTS.shouldBe(visible).click();
        checkText(OWNER);
        checkText(owner.teamFullName);
        checkText(ADMIN);
        checkText(INTRODUCER_NAME);
        checkText(VIEWER);
        checkText(expert.teamFullName);
        return true;
    }
    // INFO TAB - NUMBERS ========================================================
    public static void numberCreate(String numberType, String numberValue){
        rootLogger.info("select number from list - ");
        scrollDown();
        selectItemInDropdown(TAB_INFO_NumberNewSelect, TAB_INFO_NumberNewField, numberType);
        fillField(TAB_INFO_NumberReferenceField, numberValue);
        TAB_INFO_NumberAdd.click();
    }
    public static void numberEditInFirstRow(String newNumberType, String newNumberValue){
        rootLogger.info("Open inline form");
        TAB_INFO_NumberRow01Edit.click();
        TAB_INFO_Number_EDIT_REFERENCE_BTN_SAVE.waitUntil(visible, 10000).shouldBe(disabled);
        selectItemInDropdown(TAB_INFO_Number_EDIT_REFERENCE_TYPE_SELECT, TAB_INFO_Number_EDIT_REFERENCE_TYPE_INPUT, newNumberType);
        fillField(TAB_INFO_Number_EDIT_REFERENCE_VALUE_INPUT, newNumberValue);
        submitEnabledButton(TAB_INFO_Number_EDIT_REFERENCE_BTN_SAVE);
    }
    public static void numberDelete(String numberType){
        if(numberType==null){
            rootLogger.info("Delete number");
            TAB_INFO_NumberRow01Collapse.click();
            TAB_INFO_NumberRow01Delete.click();
            submitConfirmAction();
            return;
        }
        else {

        }
    }
    public static Boolean numberValidateFirstRow(String numberType, String numberValue){
        if(numberType==null && numberValue==null){
            NUMBERS_LIST.filter(visible).shouldHaveSize(1);
            rootLogger.info("BUG #140183099 - https://www.pivotaltracker.com/n/projects/1239770/stories/140183099 ");
            checkText(placeholderNoNumbers);
            //TODO bug
            return true;
        }
        if(numberType!=null) {
            NUMBERS_LIST.filter(visible).shouldHaveSize(2);
            TAB_INFO_NumberRow01Type.shouldHave(text(numberType));
        }
        if(numberValue!=null) {
            NUMBERS_LIST.filter(visible).shouldHaveSize(2);
            TAB_INFO_NumberRow01Number.shouldHave(text(numberValue));
        }
        return true;
    }
    public static Boolean numberValidateInlineForm(String numberType, String numberValue){
        if(numberType!=null) {
            checkText(numberType, 2);
        }
        if( numberValue!=null) {
            checkText(numberValue, 1);
        }
        return true;
    }
    // INFO TAB - CLASSIFICATION ========================================================
    public static void classificationCreate(String classType, String classNumber, String classDescription){
        PROJECT_TAB_INFO.waitUntil(visible, 20000).click();
        scrollDown();
        TAB_INFO_ClassesAdd.waitUntil(visible, 20000).click();

        waitForModalWindow(mwClasses_Title);
        MW_BTN_OK.shouldBe(disabled);
        MW_Classes_ClassType.shouldHave(text("Up-to-date"));
        if(classType!=null) {
            selectItemInDropdown(MW_Classes_ClassType_SELECT, MW_Classes_ClassType_INPUT, classType);
        }
        if(classNumber!=null) {
            fillField(mwClasses_FieldClass, classNumber);
        }
        if(classDescription!=null) {
            fillField(mwClasses_FieldDescription, classDescription);
        }
        if(classType!=null | classNumber!=null | classDescription!=null) {
            rootLogger.info("Deploy new classification");
            submitEnabledButton(MW_BTN_OK);
            return;
        }
    }
    public static void classificationEditFirstRow(String classType, String classNumber, String classDescription){
        PROJECT_TAB_INFO.waitUntil(visible, 20000).click();
        scrollDown();
        TAB_INFO_ClassRow01Edit.waitUntil(visible, 10000).click();

        rootLogger.info("Edit classification in modal window");
        waitForModalWindow(mwClasses_Title);
        MW_BTN_OK.shouldBe(disabled);
        if(classType!=null) {
            selectItemInDropdown(MW_Classes_ClassType_SELECT, MW_Classes_ClassType_INPUT, classType);
        }
        if(classNumber!=null) {
            fillField(mwClasses_FieldClass, classNumber);
        }
        if(classDescription!=null) {
            fillField(mwClasses_FieldDescription, classDescription);
        }
        if(classType!=null | classNumber!=null | classDescription!=null) {
            submitEnabledButton(MW_BTN_OK);
            return;
        }
    }
    public static void classificationDelete(){
        rootLogger.info("Delete classification");
        TAB_INFO_ClassRow01delete.click();
        submitConfirmAction();
    }
    public static Boolean classificationValidateClasses(String classType, String classDescription){
        MW.shouldNotBe(visible);
        if(classType==null && classDescription==null){
            $$(byText(PLACEHOLDER_NO_CASES)).shouldHaveSize(1);
            rootLogger.info("All calsses were deleted - "+ PLACEHOLDER_NO_CASES);
            return true;
        }
        if (classType!=null) {
            $$(byText(classType)).shouldHaveSize(1);
        }
        if (classDescription!=null) {
            $$(byText(classDescription)).shouldHaveSize(1);
        }

        return true;
    }
// CONTACTS TAB ======================================================================
    public static void callNewContactModal(String testContactName){
        PROJECT_TAB_CONTACTS.waitUntil(visible, 15000).click();
        rootLogger.info("Select createPerson new");
        projectTabContacts_AddSelectContact.shouldBe(visible).click();
        fillField(projectTabContacts_AddContactInput, testContactName);
        projectTabContacts_CREATE_NEW_CONTACT.shouldBe(visible).click();
    }
    public static void selectAndAddContact(ObjectContact contact, String relation){
        if(contact==null || relation==null){
            Assert.fail("Nothing to select");
        }
        clickSelector(PROJECT_TAB_CONTACTS);
        if(contact.contactType.equals("Company")){
            selectItemInDropdown(projectTabContacts_AddSelectContact, projectTabContacts_AddContactInput, contact.contactLegalEntity);
        }
        if(contact.contactType.equals("Person")){
            selectItemInDropdown(projectTabContacts_AddSelectContact, projectTabContacts_AddContactInput, contact.contactNameSurname);
        }
        if(relation!=null){
            contact.contactRelation = relation;
            selectItemInDropdown(projectTabContacts_AddSelectRelation, projectTabContacts_AddRelationInput, relation);
        }
        submitEnabledButton(projectTabContacts_AddContactButton);
    }
// TASKS TAB =========================================================================
    public static void deleteAllTasks(){
        clickSelector(PROJECT_TAB_TASKS);
        int i = 0;
        while (i<15){
            sleep(1000);
            if($(byText(PLACEHOLDER_EMPTY_LIST)).isDisplayed()){
                return;
            }
            rootLogger.info("Delete All tasks");
            clickSelector(projectAllCheckbox);
                sleep(1000);
                    if(TAB_TASKS_BTN_DELETE.isDisplayed()){
                        clickSelector(TAB_TASKS_BTN_DELETE);
                        submitConfirmAction();
                        checkText(PLACEHOLDER_EMPTY_LIST);
                    }
            i++;
        }
    }
    public static void deleteTaskCard(){
        submitEnabledButton(PROJECT_TASK_CARD_BTN_DEL_TASK);
        submitConfirmAction();
    }
    public static void openTask(int rowNumber){
        TASKS_ROW_CLICKABLE(rowNumber).click();
    }
    public static String postComment(String text){
        fillField(PROJECT_TASK_CARD_TEXT_FIELD, text);
        submitEnabledButton(PROJECT_TASK_CARD_BTN_SEND);
        checkText(text);
        return text;
    }
    public static void selectSortOrderInProject(String order, Boolean orderIsAscending){
        if (order!=null){
            clickSelector(SORT_ORDER_TYPE);
            clickSelector(SELECT_SORT_ORDER(order));
            String ActualTaskOrder = SORT_ORDER_TYPE.getText();
            Assert.assertEquals(order, ActualTaskOrder);
        }
        if(orderIsAscending==true){
            SORT_ORDER_ASCENDING.shouldBe(visible);
        }
        else {
            SORT_ORDER_DESCENDING.shouldBe(visible);
        }
    }
    public static Boolean selectTaskOrderAndCheck(String taskOrder,  Boolean orderIsAscending){
        TAB_TASKS_ACTUAL_ORDER.shouldBe(visible).click();
        if(taskOrder.equals(TASKS_ORDER_DUE_DATE))
            TASKS_ORDER_BTN_DUE_DATE.shouldBe(visible).click();
        if(taskOrder.equals(TASKS_ORDER_LAST_CREATED))
            TASKS_ORDER_BTN_LAST_CREATED.shouldBe(visible).click();
        if(taskOrder.equals(TASKS_ORDER_LAST_MODIFIED))
            TASKS_ORDER_BTN_LAST_MODIFIED.shouldBe(visible).click();
        if(taskOrder.equals(TASKS_ORDER_TITLE))
            TASKS_ORDER_BTN_TITLE.shouldBe(visible).click();
        if(taskOrder.equals(TASKS_ORDER_ASSIGNEE))
            TASKS_ORDER_BTN_ASSIGNEE.shouldBe(visible).click();

        if(orderIsAscending==true){
            TAB_TASKS_ORDER_ASCENDING.shouldBe(visible);
        }
        else {
            TAB_TASKS_ORDER_DESCENDING.shouldBe(visible);
        }

        String ActualTaskOrder = TAB_TASKS_ACTUAL_ORDER.getText();
        Assert.assertEquals(taskOrder, ActualTaskOrder);
        return true;
    }
    public static String taskCreate(){
        taskAddNew();
        String taskName = taskNewModalSetName();
        taskNewModalSubmit();
        return taskName;
    }
    public static String taskDeploy(String eventType, String templateName){
        taskAddDeploy();
        String taskName = taskModalDeployTemplate(eventType, templateName);
        return taskName;
    }

    public static String taskCreate(int dueDateFromToday, String importance, String status){
        taskAddNew();
        String taskName = taskNewModalSetName();
        taskNewModalSetDueDateFromToday(dueDateFromToday);
        taskNewModalSelectImportance(importance);
        taskNewModalSelectStatus(status);
        taskNewModalSubmit();
        return taskName;
    }
    public static boolean verifyTaskFirstRow(String taskName, String importance, String status, String action){
        String taskStatus;
        String taskAction;
        rootLogger.info("Check task row state");
        TASKS_NAME_IN_FIRST_ROW.shouldHave(text(taskName));
        TASKS_PRIORITY_IN_FIRST_ROW.shouldHave(text(importance));
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(status)).getText();
        rootLogger.info("Task status is - "+taskStatus);
        if (status.equals(TASK_STATUS_ACCEPTED)){
            taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(TASK_STATUS_ACCEPTED)).getText();
            rootLogger.info("Task was "+taskStatus);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldNot(exist);

            return true;
        }
        if (status.equals(TASK_STATUS_CANCELLED)){
            taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(TASK_STATUS_CANCELLED)).getText();
            rootLogger.info("Task was "+taskStatus);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldNot(exist);
            rootLogger.info("ObjectUser not able to do any action");
            return true;
        }
        if(status.equals(TASK_STATUS_COMPLETED))
        {
            taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldHave(text("accept")).getText();
            rootLogger.info("ObjectUser able to - "+taskAction+" task");
            taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldHave(text("reject")).getText();
            rootLogger.info("ObjectUser able to - "+taskAction+" task");
            return true;
        }
        else {
            taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text(action)).getText();
            rootLogger.info("ObjectUser able to - " + taskAction + " task");
            return true;
        }
    }
    public static boolean verifyTaskFirstRow(String taskName, String importance, String status, boolean assigneeNotDefined){
        String taskStatus;
        rootLogger.info("Check task row state");
        TASKS_NAME_IN_FIRST_ROW.shouldHave(text(taskName));
        TASKS_PRIORITY_IN_FIRST_ROW.shouldHave(text(importance));
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(status)).getText();
        rootLogger.info("Task status is - "+taskStatus);
        if (assigneeNotDefined==true){
            taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(status)).getText();
            rootLogger.info("Task was "+taskStatus);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldNot(exist);
            return true;
        }
        return false;
    }
    public static String taskSelectStatusFormDropDown(String statusName){
        try {
            TASKS_BTN_STATUS_IN_FIRST_ROW.waitUntil(visible, 15000).click();
            TASKS_STATUS_SELECTED_IN_DROPDOWN_MENU(statusName).click();
            sleep(1000);
            return statusName;
        }
        catch (Exception e)
        {   rootLogger.info("ERROR: Status "+statusName+" not selected");
            return null;
        }
    }
    public final static SelenideElement PROJECT_TEMPLATE_SELECTED_IN_DROPDOWN_MENU(String templateProjectName) {
        String selectedTemplatePath = "//button[@type='button']/following-sibling::ul//a[@href and text()='%s']";
        String selectedTemplateString = String.format(selectedTemplatePath, templateProjectName);
        SelenideElement selectedTemplate = $(byXpath(selectedTemplateString));
        return selectedTemplate;
    }
    public static String taskSelectProjectTemplateFormDropDown(String templateProjectName){
        sleep(2000);
        int size = DROPDOWN_PROJECT_TEMPLATES_LIST.size();
        if (size==0){
            Assert.fail("Dropdown not present");}
        try {
            PROJECT_TEMPLATE_SELECTED_IN_DROPDOWN_MENU(templateProjectName).click();
            sleep(1000);
            return templateProjectName;
        }
        catch (Exception e)
        {   rootLogger.info("ERROR: Status "+templateProjectName+" not selected");
            return null;
        }
    }
    public static boolean taskAddNew(){
        try {
            rootLogger.info("Call new task MW in Project");
            PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();
            TAB_TASKS_ADD.waitUntil(visible, 20000).click();
            TAB_TASKS_NEW_TASK.waitUntil(visible, 20000).click();
            return true;
        }
        catch (Exception e){return false;}
    }
    public static boolean taskAddDeploy(){
        try {
            rootLogger.info("Call new Task Templates MW in Project");
            PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();
            TAB_TASKS_ADD.waitUntil(visible, 20000).click();
            TAB_TASKS_DEPLOY_TASK.waitUntil(visible, 20000).click();
            return true;
        }
        catch (Exception e){return false;}
    }
    public static boolean taskSelectFilterAllOrActive(boolean selectAllFilter){
        if (selectAllFilter == true) {
            try {
                TAB_TASKS_ALL.waitUntil(visible, 15000).click();
                TAB_TASKS_ALL.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding active-link"));
                TAB_TASKS_ACTIVE.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding"));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        if (selectAllFilter == false) {
            try {
                TAB_TASKS_ACTIVE.waitUntil(visible, 15000).click();
                TAB_TASKS_ACTIVE.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding active-link"));
                TAB_TASKS_ALL.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding"));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public static String taskNewModalSetName(){
        String taskName = "TASK_" + randomString(10);
        waitForModalWindow(TITLE_MW_NEW_TASK);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_TASK_NAME, taskName);
        return taskName;
    }
    public static String taskModalDeployTemplate(String eventType, String templateSetName){
        waitForModalWindow("Task Templates");
        MW_DeployTask_Apply.shouldBe(disabled);
        selectItemInDropdown(
                MW_DeployTask_SelectEvent,
                MW_DeployTask_InputEvent,
                eventType);
        String selectTaskTemplateByNamePath = mw+"//label[@class='clickable' and ./span[text()='"+ templateSetName +"']]/input";
        $(byXpath(selectTaskTemplateByNamePath)).click();
        submitEnabledButton(MW_DeployTask_Apply);
        MW.waitUntil(not(visible), 15000);
        return templateSetName;
    }

    public static String taskNewModalSetName(String taskTitle){
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_TASK_NAME, taskTitle);
        return taskTitle;
    }
    public static String taskNewModalSetDueDateFromToday(int dueDateFromToday){
        fillField(MW_TASK_INPUT_DUE_DATE, getDate(dueDateFromToday));
        String date = MW_TASK_INPUT_DUE_DATE.getValue();
        sleep(500);
        MW.click();
        return date;
    }
    public static void taskNewModalSelectAssignor(String assignor){
        selectItemInDropdown(
                MW_TASK_SELECT_ASSIGNOR,
                MW_TASK_INPUT_ASSIGNOR,
                assignor
        );
    }
    public static void taskNewModalSelectAssignee(String assignee){
        selectItemInDropdown(
                MW_TASK_SELECT_ASSIGNEE,
                MW_TASK_INPUT_ASSIGNEE,
                assignee
        );
    }
    public static void taskNewModalSelectImportance(String importance){
        selectItemInDropdown(
                MW_TASK_SELECT_IMPORTANCE,
                MW_TASK_INPUT_IMPORTANCE,
                importance
        );
    }
    public static void taskNewModalSelectStatus(String status){
        selectItemInDropdown(
                MW_TASK_SELECT_STATUS,
                MW_TASK_INPUT_STATUS,
                status
        );
    }
    public static void taskNewModalSubmit(){
        submitEnabledButton(MW_BTN_OK);
        MW.waitUntil(not(visible), 15000);
    }
//TAB CHARGES
    public static void callChargesModal(){
        clickSelector(PROJECT_TAB_CHARGES);
        clickSelector(TAB_CHARGES_ADD);
        waitForModalWindow(TITLE_MW_CHARGE);
    }
    public static void callXeroModal(){
        clickSelector(PROJECT_TAB_CHARGES);
        clickSelector(projectAllCheckbox);
        clickSelector(TAB_CHARGES_XERO);
        sleep(3000);
    }
    public static void deleteAllCharges(){
        clickSelector(PROJECT_TAB_CHARGES);
        sleep(1500);
        if($(byText(PLACEHOLDER_EMPTY_LIST)).isDisplayed()==false){
            clickSelector(projectAllCheckbox);
            clickSelector(TAB_CHARGES_BTN_DELETE);
            submitConfirmAction();
            checkText(PLACEHOLDER_EMPTY_LIST);
        }
    }
    public static SelenideElement elementInChargesRow(Integer rowCount, final String path){
        if(rowCount>10){Assert.fail("Only 10 rows on the page");}
        String rowBtn = TAB_CHARGES_ROW_BY_INDEX(rowCount)+path;
        SelenideElement btn = $(byXpath(rowBtn));
        return btn;
    }
    public static void selectChargesRow(Integer rowCount){
        submitEnabledButton(elementInChargesRow(rowCount, TAB_CHARGES_ROW_SELECT));
    }
    public static void clickChargesRow(Integer rowCount){
        submitEnabledButton(elementInChargesRow(rowCount, TAB_CHARGES_ROW_FROM_TO));
    }

 // CONVERSATION ====================================================================
    public static boolean callModalNewConversation(){
        rootLogger.info("Create new thread in Talk to your Team tab");
        scrollUp();
        CONVERSATION_BTN_Team.waitUntil(visible, 15000);
        CONVERSATION_BTN_New.shouldBe(visible).click();
        return true;
    }
    public static boolean createExternalConversation(){
        rootLogger.info("Create thread in Talk to Your client tab");
        scrollUp();
        CONVERSATION_BTN_Client.shouldBe(visible).click();
        CONVERSATION_BTN_New.click();
        sleep(2000);
        CONVERSATION_LABEL_ACTIVE_TAB.shouldHave(text(CONVERSATION_CLIENT_TAB_NAME));
        return true;
    }
    public static void callModalEmailParameters(){
        rootLogger.info("Open Email parameters modal window");
        scrollUp();
        CONVERSATION_BTN_EMAIL_PARAMETERS.waitUntil(visible, 15000).click();
        sleep(500);
    }
    public static boolean sendExternalMsg(
            String emailFollowerTo,
            String emailFollowerCc,
            String emailFollowerBcc,
            String emailSubject,
            String emailText){
        rootLogger.info("Send mas with custom data");
        if(emailFollowerTo!=null) {
            fillField(CONVERSATION_EXTERNAL_INPUT_TO, emailFollowerTo);
            sleep(1500);
            CONVERSATION_EXTERNAL_INPUT_TO.click();
            sleep(500);
        }
        if(emailFollowerCc!=null) {
            fillField(CONVERSATION_EXTERNAL_INPUT_CC, emailFollowerCc);
            sleep(1500);
            CONVERSATION_EXTERNAL_INPUT_CC.click();
            sleep(500);
        }
        if(emailFollowerBcc!=null) {
            fillField(CONVERSATION_EXTERNAL_INPUT_BCC, emailFollowerBcc);
            sleep(1500);
            CONVERSATION_EXTERNAL_INPUT_BCC.click();
            sleep(500);
        }
        if(emailSubject!=null) {
            fillField(CONVERSATION_EXTERNAL_INPUT_SUBJECT, emailSubject);
            sleep(2000);
        }
        if(emailText!=null) {
            fillTextEditor(emailText);
            sleep(1000);
        }
        submitEnabledButton(CONVERSATION_BTN_POST);
        return true;
    }

    public static boolean sendExternalMsg(){
        rootLogger.info("Send mas with dummy data");
        String emailFollowerTo = randomString(15)+"@mail.com";
        fillField(CONVERSATION_EXTERNAL_INPUT_TO, emailFollowerTo);
        sleep(2000);
        CONVERSATION_EXTERNAL_INPUT_TO.click();

        String emailFollowerCc = randomString(15)+"@post.de";
        fillField(CONVERSATION_EXTERNAL_INPUT_CC, emailFollowerCc);
        sleep(2000);
        CONVERSATION_EXTERNAL_INPUT_CC.click();

        String emailFollowerBcc = randomString(15)+"@liamg.usa";
        fillField(CONVERSATION_EXTERNAL_INPUT_BCC, emailFollowerBcc);
        sleep(2000);
        CONVERSATION_EXTERNAL_INPUT_BCC.click();

        String emailSubject = "externalEmail"+randomString(20);
        fillField(CONVERSATION_EXTERNAL_INPUT_SUBJECT, emailSubject);
        sleep(3000);

        fillTextEditor(LOREM_IPSUM_SHORT);
        sleep(1000);

        submitEnabledButton(CONVERSATION_BTN_POST);
        return true;
    }
    public static boolean validateFollowerExternal(String followerNameSurname){
        rootLogger.info("Check default follower");
        CONVERSATION_FOLLOWERS_UI.shouldHave(text("Show")).click();
        CONVERSATION_FOLLOWERS_UI.shouldHave(text("Hide"));
        CONVERSATION_FOLLOWERS_LIST.shouldHaveSize(1);
        SelenideElement FirstFollower = CONVERSATION_FOLLOWERS_LIST.get(0);
        FirstFollower.shouldHave(text(followerNameSurname));
        return true;
    }
    public static boolean validateFollowerExternal(String followerNameSurname, Integer followersQty, Integer followerIndex){
        rootLogger.info("Check new follower");
        CONVERSATION_FOLLOWERS_UI.shouldHave(text("Show")).click();
        CONVERSATION_FOLLOWERS_UI.shouldHave(text("Hide"));
        if(followersQty!=null) {
            CONVERSATION_FOLLOWERS_LIST.shouldHaveSize(followersQty);
        }
        if(followerNameSurname!=null && followerIndex!=null) {
            CONVERSATION_FOLLOWERS_LIST.filter(visible);
            SelenideElement FirstFollower = CONVERSATION_FOLLOWERS_LIST.get(followerIndex);
            FirstFollower.waitUntil(visible, 10000).shouldHave(text(followerNameSurname));
        }
        return true;
    }

    public static boolean validateFollowerTeamChat(String followerNameSurname, Integer followersQty, Integer followerIndex){
        rootLogger.info("Check new follower");
        if(followersQty!=null) {
            CONVERSATION_FOLLOWERS_LIST.filter(visible).shouldHaveSize(followersQty);
        }
        if(followerNameSurname!=null && followerIndex!=null) {
            CONVERSATION_FOLLOWERS_LIST.filter(visible);
            SelenideElement FirstFollower = CONVERSATION_FOLLOWERS_LIST.get(followerIndex);
            rootLogger.info(FirstFollower.getText());
            //TODO validation one value from array - follower place is dynamic
            //FirstFollower.waitUntil(visible, 10000).shouldHave(text(followerNameSurname));
        }
        return true;
    }
    public static boolean inviteGuestInTeam(Boolean invite, String followerEmail){
        CONVERSATION_INVITE_ALERT_TITLE.shouldHave(text("Next followers you can invite to your team:"));
        CONVERSATION_INVITE_ALERT_FOLLOWER_EMAIL.shouldHave(text(followerEmail));
        if(invite==false){
            CONVERSATION_INVITE_ALERT_DISMISS.shouldBe(visible).click();
            CONVERSATION_INVITE_ALERT_TITLE.waitUntil(not(visible), 20000);
            return false;
        }
        if(invite==true){
            CONVERSATION_INVITE_ALERT_INVITE.shouldBe(visible).click();
            CONVERSATION_INVITE_ALERT_TITLE.waitUntil(not(visible), 20000);
            return true;
        }
        return false;
    }
    public static boolean deleteFollower(String followerNameSurname){
        rootLogger.info("Delete first follower");
        CONVERSATION_FOLLOWERS_ONE_NAME.shouldHave(text(followerNameSurname));
        CONVERSATION_FOLLOWERS_ONE_DELETE.shouldBe(visible).click();
        CONVERSATION_FOLLOWERS_INPUT.shouldHave(value(""));
        return true;
    }
    public static boolean checkTreadTitle(String threadTitle){
        CONVERSATION_TITLE.shouldHave(text(threadTitle));
        return true;
    }
    public static String editTreadTitle(String oldThreadTitle, String newThreadName){
        rootLogger.info("Edit thread title");
        CONVERSATION_EDIT_TITLE.click();
        CONVERSATION_FIELD_TITLE.shouldHave(value(oldThreadTitle));
        if(newThreadName==null) {
            rootLogger.info("Set random thread name");
            newThreadName = "EXTERNAL"+randomString(15);
        }
        fillField(CONVERSATION_FIELD_TITLE, newThreadName);
        CONVERSATION_SAVE_TITLE.click();
        CONVERSATION_TITLE.shouldHave(text(newThreadName));
        return  newThreadName;
    }
    public static String editTreadTitle(String newThreadName){
        rootLogger.info("Edit thread title");
        CONVERSATION_EDIT_TITLE.click();
        CONVERSATION_FIELD_TITLE.shouldHave(value(""));
        if(newThreadName==null) {
            rootLogger.info("Set random thread name");
            newThreadName = "EXTERNAL"+randomString(15);
        }
        fillField(CONVERSATION_FIELD_TITLE, newThreadName);
        CONVERSATION_SAVE_TITLE.click();
        CONVERSATION_TITLE.shouldHave(text(newThreadName));
        return  newThreadName;
    }
    public static boolean validateExternalMsg(String emailFollowerTo, String emailFollowerCc, String emailFollowerBcc){
        CONVERSATION_MsgBody.waitUntil(visible, 20000).shouldBe(visible);
        $$(byText(LOREM_IPSUM_SHORT)).filter(visible).shouldHaveSize(1);
        checkText(LOREM_IPSUM_SHORT);
        CONVERSATION_MsgTaskIcon.shouldBe(visible);
        if (emailFollowerTo!=null) {
            CONVERSATION_MsgTo.shouldHave(text(emailFollowerTo));
        }
        if (emailFollowerCc!=null) {
            CONVERSATION_MsgCC.shouldHave(text(emailFollowerCc));
        }
        if (emailFollowerBcc!= null) {
            CONVERSATION_MsgBCC.shouldHave(text(emailFollowerBcc));
        }
        return true;
    }
    public static boolean deleteMsg(){
        rootLogger.info("Delete message");
        CONVERSATION_MsgDelete.shouldBe(visible).click();
        submitConfirmAction("Delete message?");
        CONVERSATION_MsgBody.shouldNotBe(visible);
        return true;
    }
    public static void deleteLastMessage(){
        rootLogger.info("Delete message");
        CONVERSATION_MsgDelete.waitUntil(visible, 10000).click();
        submitConfirmAction(TITLE_MW_DELETE_MESSAGE);
    }
    public static boolean sendTeamChatMsg(){
        return true;
    }


    public static void switchToCommunity(){
        checkThatWindowsQtyIs(2);
        switchToCommunityWindow();
        sleep(2000);
    }
    public static void switchToCommunity(int windowsQty){
        checkThatWindowsQtyIs(windowsQty);
        switchToCommunityWindow();
        sleep(2000);
    }
    public static void switchToWindowByIndex(int windowIndex, int windowsQty){
        checkThatWindowsQtyIs(windowsQty);
        switchTo().window(windowIndex);
        sleep(2000);
    }
    public static void switchToPekama(){
        checkThatWindowsQtyIs(2);
        switchToPekamaWindow();
        sleep(2000);
    }
    public static void switchToPekama(int windowsQty){
        checkThatWindowsQtyIs(windowsQty);
        switchToPekamaWindow();
        sleep(2000);
    }
    //Community area
    public static void withdrawCaseInPekama(boolean sendMsgToCollaborator){
        rootLogger.info("Withdraw case");
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_SENT));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldBe(visible).click();
        acceptWithdrawCase(sendMsgToCollaborator);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_WITHDRAWN));
    }
    public static void cancelCaseInPekama(boolean sendMsgToCollaborator){
        rootLogger.info("Cancel case");
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_DRAFT));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldBe(visible).click();
        acceptCancelCase(sendMsgToCollaborator);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CANCELLED));
    }
    public static void confirmCaseInPekama(boolean sendMsgToCollaborator){
        rootLogger.info("Confirm case");
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_RECEIVED));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldBe(visible).click();
        acceptConfirmInstruction(sendMsgToCollaborator);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CONFIRMED));
    }
    public static void completeCaseInPekama(boolean sendMsgToCollaborator){
        rootLogger.info("Complete case");
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CONFIRMED));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldBe(visible).click();
        acceptCompletion(sendMsgToCollaborator);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_COMPLETED));
    }

    public static String getFullProjectTitle(){
        scrollUp();
        String fullProjectTitle = PROJECT_FULL_NAME.shouldBe(visible).getText();
        rootLogger.info("Full project title is: "+fullProjectTitle);
        return fullProjectTitle;
    }
    public static String parseProjectNumber(){
        String fullTitle = getFullProjectTitle();
        String projectNumber = fullTitle.substring(fullTitle.indexOf("(") + 1, fullTitle.indexOf(")"));
        return projectNumber;
    }

    //DEBUG ==============================================================
    @Ignore
    @Test
    public void debugTest(){
        String str = "INNER_VALIDATION_UPWB6PKMVH (TM.PN.031545)";
        String result = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        rootLogger.info(result);
    }
}
