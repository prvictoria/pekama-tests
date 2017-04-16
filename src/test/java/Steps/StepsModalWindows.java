package Steps;
import Page.PekamaTeamSettings;
import Page.TestsCredentials;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static Page.ModalWindows.*;
import static Page.PekamaConversationProject.CONVERSATION_BTN_New;
import static Page.PekamaConversationProject.CONVERSATION_BTN_TEMPLATE;
import static Page.PekamaConversationProject.CONVERSATION_INPUT_TEXT_COLLAPSED;
import static Page.PekamaProject.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.URL_SingUp;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.ModalConversationFollowerActions.*;
import static Steps.StepsModalWindows.ModalConversationTeamActions.ADD_TEAM;
import static Steps.StepsModalWindows.ModalConversationTeamActions.INVITE_TEAM;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
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
        MW.waitUntil(visible, 20000).shouldBe(visible);
        MW.shouldHave(text(modalTitle));
        rootLogger.info("modal window '"+modalTitle+"' was opened");
        return true;
    }
    public static boolean waitForModalWindowNotPresent(String modalTitle) {
        rootLogger.info("Wait for '"+modalTitle+"' modal window no present");
        MW.waitUntil(not(visible), 20000).shouldNotBe(visible);
        MW.shouldNotHave(text(modalTitle));
        rootLogger.info("modal window '"+modalTitle+"' was not visible");
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
    public static void submitAddMemberWindow(String newMemberEmail, Boolean emailIsValid){
        rootLogger.info("Invite new member in team");
        waitForModalWindow("Members");
        if (newMemberEmail!=null) {
            fillField(MW_MEMBERS_EMAIL, newMemberEmail);
        }
        submitEnabledButton(MW_BTN_OK);
        if (newMemberEmail!=null && emailIsValid==true) {
            MW.waitUntil(not(visible), 30000);
        }

    }
    public enum ModalConversationFollowerActions {ADD_FOLLOWER, INVITE_FOLLOWER, ADD_GUEST}
    public enum ModalConversationTeamActions {ADD_TEAM, INVITE_TEAM}
    public static void submitNewConversationWindow(ModalConversationFollowerActions selectFollowerAction, String threadName, String newFollower, String inviteTeam, ModalConversationTeamActions selectTeamAction, Boolean accessForAll, Boolean submittedDataIsValid){

        rootLogger.info("Create team chat thread");
        waitForModalWindow(TITLE_MW_CONVERSATION);
        if (threadName!=null) {
            MW_CONVERSATION_INPUT_Subject.shouldBe(empty);
            fillField(MW_CONVERSATION_INPUT_Subject, threadName);
        }
        if (newFollower!=null) {
            MW_CONVERSATION_INPUT_Follower.shouldBe(empty);
            fillField(MW_CONVERSATION_INPUT_Follower, newFollower);
            if(selectFollowerAction ==ADD_FOLLOWER){
                checkText(newFollower);
                //Only 1-st button in list
                MW_CONVERSATION_BTN_FOLLOWER_LIST.shouldHaveSize(2);
                MW_CONVERSATION_BTN_ADD_FOLLOWER.shouldBe(visible).click();
            }
            if(selectFollowerAction ==INVITE_FOLLOWER){
                MW_CONVERSATION_BTN_INVITE.shouldBe(visible).click();
                checkText(newFollower);
            }
            if(selectFollowerAction ==ADD_GUEST){
                MW_CONVERSATION_BTN_ADD_GUEST.shouldBe(visible).click();
                checkText(newFollower);
            }
        }
        if (accessForAll==true) {
            MW_ALL_TEAMS_CHECKBOX.setSelected(true).shouldBe(selected);
        }
        //TODO logic
        if (inviteTeam!=null && accessForAll==false && accessForAll!=null) {
            fillField(MW_CONVERSATION_INPUT_Subject, threadName);
            if(selectTeamAction ==ADD_TEAM){

            }
            if(selectTeamAction ==INVITE_TEAM){

            }
        }
        submitEnabledButton(MW_BTN_CREATE);
        if (submittedDataIsValid==true) {
            MW.waitUntil(not(visible), 30000);
        }
    }
    public static String submitAddMemberWindow(){
        rootLogger.info("Invite new member in team");
        String newMemberEmail = randomString(10)+"@member.com";
        waitForModalWindow("Members");
        fillField(MW_MEMBERS_EMAIL, newMemberEmail);
        submitEnabledButton(MW_BTN_OK);
        MW.waitUntil(not(visible), 30000);
        return newMemberEmail;
    }
    public static String submitMwBoostProfile(String option){
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        if(option.equals("start")){
            rootLogger.info("Boost Your profile - send new case");
            MW_BOOST_YOUR_PROFILE_BTN_START_NEW_CASE.click();
            waitForModalWindowNotPresent(TITLE_MW_BOOST_YOUR_PROFILE);
            return option;
        }
        if(option.equals("refer")){
            rootLogger.info("Boost Your profile - Invite an Attorney");
            MW_BOOST_YOUR_PROFILE_BTN_REFER_ATTORNEY.click();
            return option;
        }
        if(option.equals("invite")){
            MW_BOOST_YOUR_PROFILE_BTN_INVITE_MEMBER.click();
            waitForModalWindowNotPresent(TITLE_MW_BOOST_YOUR_PROFILE);
            return option;
        }
        escapeModalWindow();
        return null;
    }
    public static String submitMwInviteAttorney(Boolean invite, String email, String message){
        waitForModalWindow(TITLE_MW_INVITE_AN_ATTORNEY);
        if(invite==false){
            rootLogger.info("Cancel");
            MW_COMMUNITY_INVITE_ATTORNEY_BTN_CANCEL.click();
            return null;
        }
        if(invite==true){
            rootLogger.info("Click Invite");
            MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.click();
        }
        if(email!=null){
            rootLogger.info("Fill email address");
            fillField(MW_COMMUNITY_INVITE_FIELD_EMAIL, email);
        }
        if(message!=null){
            rootLogger.info("Type custom text");
            fillField(MW_COMMUNITY_INVITE_FIELD_MESSAGE, message);
        }
        if(invite==true){
            rootLogger.info("Click Invite");
            MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.click();
            return message;
        }
        return null;
    }
    public static String submitMwNewProject(String projectCustomName) {
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
    public static String submitMwNewProject(String projectCustomName, String projectType, String projectDefining) {
        String projectName = projectCustomName+"_"+randomString(10);
        waitForModalWindow(TILE_MW_PROJECT);
        if(projectType!=null) {
            rootLogger.info("Select project type, actual: " + projectType);
            selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, projectType);
        }
        if(projectDefining!=null) {
            rootLogger.info("Select defining, actual: " + projectDefining);
            selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, projectDefining);
        }
        if(projectCustomName!=null) {
            rootLogger.info("Fill title");
            fillField(MW_Project_Title, projectName);
        }
        submitEnabledButton(MW_ProjectFinishButton);
        if(projectType!=null && projectDefining!=null && projectCustomName!=null) {
            MW.waitUntil(not(visible), 20000);
            sleep(1000);
            checkText(projectName);
            rootLogger.info("Created project: " + projectName);
        }
        return projectName;
    }
    public static String submitMwNewProject(String projectCustomName, String projectType, String projectDefining, String referenceNumber, String tmNumber) {
        String projectName = projectCustomName+"_"+randomString(10);
        waitForModalWindow(TILE_MW_PROJECT);
        //MW_ProjectFinishButton.shouldBe(disabled);
        if(projectType!=null) {
            rootLogger.info("Select project type, actual: " + projectType);
            selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, projectType);
        }
        if(projectDefining!=null) {
            rootLogger.info("Select defining, actual: " + projectDefining);
            selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, projectDefining);
        }
        if(projectCustomName!=null) {
            rootLogger.info("Fill title");
            fillField(MW_Project_Title, projectName);
        }
        if(referenceNumber!=null) {
            rootLogger.info("Fill ref number");
            fillField(MW_Project_Reference, referenceNumber);
        }
        if(tmNumber!=null && (projectType.equals(MATTER_TYPE_TRADEMARK) || projectType.equals(MATTER_TYPE_PATENT))) {
            rootLogger.info("Fill TM number");
            fillField(MW_Project_TMNumber, tmNumber);
            sleep(1000);
            MW_Project_TMNumber.pressTab();
            sleep(1000);
        }
        sleep(2000);
        submitEnabledButton(MW_ProjectFinishButton);
        sleep(3000);
        if(MW_ProjectFinishButton.exists()==false){
        rootLogger.info("Created project: " + projectName);
            return projectName;
        }
        return null;
    }
    public static String submitMwNewProject() {
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
    public static boolean checkSelectedDefining(String defining, String code){
        if(defining!=null){
            MW_PROJECT_ACTUAL_DEFINING.waitUntil(exist, 15000);
            String defaultDefiningPatent = MW_PROJECT_ACTUAL_DEFINING.getText();
            Assert.assertEquals("Check defining", defining, defaultDefiningPatent);
        }
        if(code!=null){
            MW_PROJECT_ACTUAL_DEFINING_CODE.waitUntil(exist, 15000);
            String defaultDefiningCodePatent = MW_PROJECT_ACTUAL_DEFINING_CODE.getText();
            Assert.assertEquals("Check defining code", code, defaultDefiningCodePatent);
        }
        return true;
    }
    public static void escapeModalWindow(){
        MW.pressEscape();
        MW.shouldNotBe(visible);
    }
    public static void checkModalWindowNotPresent(int time){
        MW.waitUntil(not(visible), time);
        sleep(1000);
    }

    //in root in Project
    public static void createFileInRoot(SelenideElement fileType, String fileName) {
        PROJECT_TAB_DOCS.waitUntil(visible, 15000).click();
        TAB_DOCS_BTN_ADD.waitUntil(enabled, 15000).click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        submitModalDeployFileTemplate(fileType, fileName);

    }
    public static void submitModalDeployFileTemplate(SelenideElement fileType, String fileName) {
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
    public static String eventDeploy(String eventTypeName) {
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
        if(eventTypeName!=null) {
            selectItemInDropdown(MW_EVENT_SELECT_TYPE, MW_EVENT_INPUT_TYPE, eventTypeName);
        }
        submitEnabledButton(MW_BTN_SAVE);
        if(eventTypeName!=null) {
            MW.shouldNotBe(visible);
            $$(byText(eventTypeName)).filter(visible).shouldHaveSize(1);
            rootLogger.info(eventTypeName + " - Event created");
        }
        return eventTypeName;
    }
    public static String eventDeploy(String eventType, String eventInfo, Integer dueDateFromToday) {
        clickPlusButtonNewEvent();

        waitForModalWindow(TITLE_MW_EVENT);
        MW_BTN_SAVE.shouldBe(disabled);
        if(dueDateFromToday!=null) {
            setDueDateFromToday(dueDateFromToday);
        }
        if(eventType !=null) {
        fillField(MW_EVENT_INPUT_INFO, eventInfo);
        }
        if(eventType !=null) {
            selectItemInDropdown(MW_EVENT_SELECT_TYPE, MW_EVENT_INPUT_TYPE, eventType);
        }
        submitEnabledButton(MW_BTN_SAVE);
        if(eventType !=null) {
            MW.shouldNotBe(visible);
            $$(byText(eventType)).filter(visible).shouldHaveSize(1);
            rootLogger.info(eventType + " - Event created");
        }
        return eventType;
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
        refresh();
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
    public static String createConversationTeam(String subject){
        String title = subject+randomString(10);
        CONVERSATION_BTN_New.waitUntil(visible, 15000).click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        if(title!=null) {
        fillField(MW_CONVERSATION_INPUT_Subject, title);}
        MW_BTN_CREATE.click();
        MW.shouldNotBe(visible);
        return title;
    }
    public static String deployMessageTemplate(String messageTemplateName, Integer listSize){
        sleep(2000);
        CONVERSATION_INPUT_TEXT_COLLAPSED.shouldBe(visible).click();
        sleep(2000);
        CONVERSATION_BTN_TEMPLATE.shouldBe(visible).click();
        waitForModalWindow("Templates");
        if(listSize!=null){
        MW_DEPLOY_MSG_TEMPLATE_LIST.shouldHaveSize(listSize);
        }
        MW_DEPLOY_MSG_TEMPLATE_TEMPLATE(messageTemplateName).shouldBe(visible).click();
        MW_BTN_OK.shouldBe(visible).click();
        MW.shouldNotBe(visible);
        return messageTemplateName;
    }
    public static int setDueDateFromToday(Integer dueDateFromToday){
        if(dueDateFromToday!=null) {
            fillField(MW_INPUT_DATE, getDate(dueDateFromToday));
            sleep(500);
            MW.click();
        }
        return dueDateFromToday;
    }
    public static void clickPlusButtonNewEvent(){
        scrollUp();
        rootLogger.info("Deploy new event");
        projectButtonPlus.waitUntil(visible, 15000).click();
        projectPlusNewEvent.shouldBe(visible).click();
    }
    public static String deployEvent(String eventType){
        waitForModalWindow(TITLE_MW_EVENT);
        MW_BTN_SAVE.shouldBe(disabled);
        rootLogger.info("Set date today");
        MW_INPUT_DATE.click();
        sleep(500);
        MW.click();
        fillField(MW_EVENT_INPUT_INFO, LOREM_IPSUM_SHORT);
        selectItemInDropdown(
                MW_EVENT_SELECT_TYPE,
                MW_EVENT_INPUT_TYPE,
                eventType);
        submitEnabledButton(MW_BTN_SAVE);
        MW.waitUntil(not(visible), 15000);
        return eventType;
    }

    public static boolean checkDeployedEvent(String eventType, String eventInfo){
        scrollUp();
        $$(byText(eventType)).filter(visible).shouldHaveSize(1);
        $(byText(eventType)).click();
        $$(byText(eventInfo)).filter(visible).shouldHaveSize(1);
        return true;
    }

    @Test
    public void testDebug()throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest(true);
        String AUTH_URL = URL_SingUp;
        openUrlWithBaseAuth(AUTH_URL);
        User user1 = new User();
        user1.submitSignUp(
                "123@mail.com",
                "Aa@2132312",
                "21312321",
                "asd",
                "sasasa");
        rootLogger.info(user1.company);
    }
}
