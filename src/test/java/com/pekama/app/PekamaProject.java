package com.pekama.app;
import Page.Emails;
import Steps.*;
import Utils.Utils;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import java.awt.*;

import static Page.Emails.*;
import static Page.Emails.emailSubject;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekama.fillField;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class PekamaProject {
    static final Logger rootLogger = LogManager.getRootLogger();
    String testProjectTitle = "new test project - "+ Utils.getRandomString(6);
    @Before
    public void before() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = true;
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(User2.GMAIL_EMAIL.getValue(), User2.PEKAMA_PASSWORD.getValue(), URL_LogIn);

        rootLogger.info("Create project");
        dashboardNewProject.waitUntil(visible, 15000).click();
        rootLogger.info("NW - New project");
        waitForModalWindow(MW_ProjectTitle);
        rootLogger.info("select project type");
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, CaseType.TRADEMARK.getValue());
        rootLogger.info("select defining");
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, Countries.PITCAIRN_ISLANDS.getValue());
        rootLogger.info("fill title");
        fillField(MW_Project_Title, testProjectTitle);
        rootLogger.info("submit");
        submitEnabledButton(MW_ProjectFinishButton);
        MW.shouldNot(exist);
        sleep(1000);
        getActualUrl ();
        rootLogger.info("Project '"+testProjectTitle+"' created");
        waitForTextPresent(testProjectTitle);
    }
//    @After
//    public void after() {
//        rootLogger.info("delete project - '"+testProjectTitle"'");
//        //todo element not found?????
//        executeJavaScript("scrollTo(0, -1000)");
//        PROJECT_BTN_DELETE.shouldBe(visible).click();
//        StepsPekama.submitConfirmAction();
//        open(URL_Dashboard);
//        rootLogger.info("Open URL - " +URL_Dashboard);
//    }
    @Test
    public void createProject_A_CheckDefaultState() {
        $$(byText(testProjectTitle)).filter(visible).shouldHaveSize(1);
        $$(byText(placeholderNoCases)).filter(visible).shouldHaveSize(1);
        $$(byText(placeholderNoNumbers)).filter(visible).shouldHaveSize(1);
        $$(byText(placeholderNoData)).filter(visible).shouldHaveSize(1);
        $$(byText("Team chat is great for conversations between groups of people, where all the group members should see the conversation all the time.")).shouldHaveSize(1);
        rootLogger.info("GUI test passed");
    }
    @Test
    public void createProject_B_editProjectName() throws AWTException {
        waitForTextPresent(testProjectTitle);
        scrollUp();
        projectTabMore_ProjectTitle.shouldHave(text(testProjectTitle));
        projectTabMore_ProjectTitle.click();
        String newProjectName = "New project name after edition "+ Utils.getRandomString(6);
        fillField(projectTabMore_TitleInput, newProjectName);
        projectTabMore_TitleSave.click();
        sleep(1000);
        refresh();

        waitForTextPresent(newProjectName);
        scrollUp();
        projectTabMore_ProjectTitle.shouldHave(text(newProjectName));
        projectTabMore_TitleEditButton.click();
        fillField(projectTabMore_TitleInput, testProjectTitle);
        projectTabMore_TitleSave.click();
        sleep(1000);
        refresh();
        projectTabMore_ProjectTitle.shouldHave(text(testProjectTitle));
    }
    @Test
    public void createProject_C_AddNumber() {
        String codeType = "Equinox code";
        String codeValue = "2000/17/55-asd";
        rootLogger.info("select number from list - ");
        selectItemInDropdown(projectTabMore_NumberNewSelect, projectTabMore_NumberNewField, codeType);
        fillField(projectTabMore_NumberReferenceField, codeValue);
        scrollDown();
        projectTabMore_NumberAdd.click();
        projectTabMore_NumberRow01Type.shouldHave(text(codeType));

        rootLogger.info("open inline form");
        projectTabMore_NumberRow01Edit.click();
        projectTabMore_Number_EDIT_REFERENCE_BTN_SAVE.waitUntil(visible, 10000).shouldBe(disabled);
        rootLogger.info("edit number inline - ");
        String newCodeValue = "8888-1111-lkjh";
        String newCodeType = "Reference Number";
        selectItemInDropdown(projectTabMore_Number_EDIT_REFERENCE_TYPE_SELECT, projectTabMore_Number_EDIT_REFERENCE_TYPE_INPUT, newCodeType);
        fillField(projectTabMore_Number_EDIT_REFERENCE_VALUE_INPUT, newCodeValue);
        submitEnabledButton(projectTabMore_Number_EDIT_REFERENCE_BTN_SAVE);
        $$(byText(newCodeValue)).shouldHaveSize(1);
        $$(byText(newCodeType)).shouldHaveSize(2);
        $$(byText(codeValue)).shouldHaveSize(0);
        $$(byText(codeType)).shouldHaveSize(0);

        rootLogger.info("delete number");
        projectTabMore_NumberRow01Collapse.click();
        projectTabMore_NumberRow01Delete.click();
        submitConfirmAction();
        $$(byText(placeholderNoNumbers)).shouldHaveSize(1);
    }
    @Test
    public void createProject_D_addClassification() {
        String classNumber = "12";
        String classDescripton = "old description";
        scrollDown();
        projectTabMore_ClassesAdd.click();
        waitForModalWindow(mwClasses_Title);
        MW_BTN_OK.shouldBe(disabled);
        mwClasses_SelectClassType.shouldHave(text("Up-to-date"));
        fillField(mwClasses_FieldClass, classNumber);
        fillField(mwClasses_FieldDescription, classDescripton);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText("Up-to-date")).shouldHaveSize(1);
        $$(byText(classDescripton)).shouldHaveSize(1);

        rootLogger.info("edit class in modal - ");
        projectTabMore_ClassRow01Edit.click();
        waitForModalWindow(mwClasses_Title);
        MW_BTN_OK.shouldBe(disabled);
        mwClasses_SelectClassType.shouldHave(text("Up-to-date"));
        selectItemInDropdown(mwClasses_SelectClassType, mwClasses_FieldClassType, "Official Data");
        String classNewNumber = "23";
        fillField(mwClasses_FieldClass, classNewNumber);
        String classNewDescripton = "new description";
        fillField(mwClasses_FieldDescription, classNewDescripton);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText("Official Data")).shouldHaveSize(1);
        $$(byText(classNewDescripton)).shouldHaveSize(1);

        rootLogger.info("delete classification");
        projectTabMore_ClassRow01delete.click();
        submitConfirmAction();
        $$(byText(placeholderNoCases)).shouldHaveSize(1);
        rootLogger.info("All calsses were deleted - "+placeholderNoCases);
    }
    @Test
    public void createProject_E_addCollaborator() {
        rootLogger.info("Add Pekama member - by default - as Collaborator");
        projectTabContacts.click();
        projectTabContacts_AddCollaborator.click();
        waitForModalWindow("Share Project");
        selectTeam(User3.TEAM_NAME.getValue());
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(OWNER)).shouldHaveSize(1);
        $$(byText(COLLABORATOR)).shouldHaveSize(1);
//todo SELECT ??? Type mismatch Can't assign non-array value to an array

        rootLogger.info("Edit role to - "+ROLE_VIEWER);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow("Change Collaborator");
        MW_BTN_OK.shouldBe(disabled);
        selectOption(MW_SHARE_PROJECT_SELECT_ROLE, ROLE_VIEWER);
      //  MW_SHARE_PROJECT_SELECT_ROLE.selectOption(new String[]{ROLE_VIEWER});
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(VIEWER)).shouldHaveSize(1);

        rootLogger.info("Edit role to - "+ROLE_ADMIN);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow("Change Collaborator");
        MW_BTN_OK.shouldBe(disabled);
        selectOption(MW_SHARE_PROJECT_SELECT_ROLE, ROLE_ADMIN);
     //   MW_SHARE_PROJECT_SELECT_ROLE.selectOption(new String[]{ROLE_ADMIN});
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(ADMIN)).shouldHaveSize(1);

        rootLogger.info("Edit role to - "+ROLE_COLLABORATOR);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow("Change Collaborator");
        MW_BTN_OK.shouldBe(disabled);
        selectOption(MW_SHARE_PROJECT_SELECT_ROLE, ROLE_COLLABORATOR);
      //  MW_SHARE_PROJECT_SELECT_ROLE.selectOption(new String[]{ROLE_COLLABORATOR});
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(COLLABORATOR)).shouldHaveSize(1);

        rootLogger.info("Delete collaborator");
        projectTabContacts_TeamDelete.click();
        submitConfirmAction();
        $$(byText(OWNER)).shouldHaveSize(1);
        $$(byText(ADMIN)).shouldHaveSize(0);
        $$(byText(COLLABORATOR)).shouldHaveSize(0);
        $$(byText(VIEWER)).shouldHaveSize(0);

    }
    @Test //todo
    public void createProject_E_inviteCollaborator() {
        rootLogger.info("Invite new team to Pekama project");
        projectTabContacts.click();
        projectTabContacts_AddCollaborator.click();
        waitForModalWindow("Share Project");
        MW_SHARE_PROJECT_BTN_FIND.shouldBe(disabled);
        fillField(MW_SHARE_PROJECT_EMAIL, User5.GMAIL_EMAIL.getValue());
        submitEnabledButton(MW_SHARE_PROJECT_BTN_FIND);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(OWNER)).shouldHaveSize(1);
        $$(byText(COLLABORATOR)).shouldHaveSize(1);

        rootLogger.info("Check email - set vars");
        String USER_EMAIL = User5.GMAIL_EMAIL.getValue();
        String thisEmailSubject = emailSubject(testProjectTitle);
        SelenideElement EMAIL_SUBJECT = $(byXpath(thisEmailSubject));
        String EMAIL_TITLE = emailInviteInProjectTitle(
                User2.NAME.getValue(),
                User2.SURNAME.getValue());
        String EMAIL_TEXT = emailInviteInProjectText(
                User2.NAME.getValue(),
                User2.SURNAME.getValue(),
                testProjectTitle);
        String EMAIL_BTN = EMAIL_INVITE_IN_PROJECT_BTN;
        SelenideElement EMAIL_REDIRECT_LINK = EMAIL_INVITE_IN_PROJECT_BACKLINK;
        rootLogger.info("Opne inbox email");
        String inviteLink = checkInboxEmail(
                USER_EMAIL,
                GMAIL_PASSWORD,
                EMAIL_SUBJECT,
                EMAIL_TITLE,
                EMAIL_TEXT,
                EMAIL_BTN,
                EMAIL_REDIRECT_LINK);
        if (inviteLink==null){Assert.fail("no link in email");};

    }
    @Test //todo
    public void createProject_F_addNewContact() {
        projectTabContacts.click();
        $$(byText(placeholderNoData)).shouldHaveSize(1);
        rootLogger.info("Check validation");
        projectTabContacts_AddContactButton.click();
        $$(byText("")).shouldHaveSize(1);
        rootLogger.info("Create new contact");
        rootLogger.info("Add new contact");

        rootLogger.info("delete contact");
    }

    @Test //todo
    public void createProject_F_addExistedContact() {
        projectTabContacts.click();
        $$(byText(placeholderNoData)).shouldHaveSize(1);

        //todo
        rootLogger.info("Select existed contact - new role");
        selectItemInDropdown(projectTabContacts_AddSelectContact, projectTabContacts_AddContactInput, "");
        String relationInvestor = "Investor";
        selectItemInDropdown(projectTabContacts_AddSelectRelation, projectTabContacts_AddRelationInput, relationInvestor);
        rootLogger.info("Add existed contact - new role");
        projectTabContacts_AddContactButton.click();

        //todo
        rootLogger.info("Edit fields contact inline");
        projectTabContacts_FormRelationSelect.selectOption("Owner");
        fillField(projectTabContacts_FormOwnership, "99");
        fillField(projectTabContacts_FormEntity, "newEntity");
        fillField(projectTabContacts_FormFirstName, "NEWperson");
        fillField(projectTabContacts_FormLastName, "NEWman03");
        fillField(projectTabContacts_FormEmail, "NEWcontact_01_mail@mail.com");
        fillField(projectTabContacts_FormPhone, "newPhone");
        fillField(projectTabContacts_FormFax, "newFax");
        fillField(projectTabContacts_FormMobile, "newMobile");
        fillField(projectTabContacts_FormStreet, "newStreet");
        fillField(projectTabContacts_FormPostal, "newZip");
        fillField(projectTabContacts_FormCity, "newCity");
        fillField(projectTabContacts_FormRegion, "newRegion");
        selectItemInDropdown(projectTabContacts_FormCountrySelect, projectTabContacts_FormCountryInput, nameCountryIreland);
        submitEnabledButton(genericButtonSave);
        sleep(500);
        refresh();
        projectTabContacts_ContactEdit.click();
        //todo
        projectTabContacts_FormOwnership.shouldHave(text(""));


    }
    @Test
    public void createProject_G_addDocument() {
        String newDoc = "new word document";
        projectTabDocs.click();
        //todo - random+name
        buttonAddNewFile.click();
        linkCreateNewDoc.shouldBe(Condition.visible).click();
        MW.shouldBe(Condition.visible);
        MW_DeployDoc_01TemplateWord.shouldBe(Condition.visible).click();
        $(byName("name")).sendKeys(newDoc);
        MW_DeployDoc_ButtonCreate.click();
        MW.shouldNotBe(Condition.visible);
        $(byText(newDoc)).shouldBe(Condition.visible);
        rootLogger.info(newDoc+" - file present");

        rootLogger.info("edit file");
        projectTabDocs_RenameExpanded.click();
        fillField(projectTabDocs_NameExpanded, "New Excel sheet");
        projectTabDocs_SaveExpanded.click();
        $(byText("New Excel sheet")).shouldBe(Condition.visible);

        rootLogger.info("delete file");
        projectAllCheckbox.click();
        $(byLinkText("Delete")).click();
        submitConfirmAction();
        $$(byText(placeholderNoFiles)).shouldHaveSize(1);

    }
    @Test
    public void createProject_H_addFolder() {
        projectTabDocs.click();
        //todo - random+name
        buttonAddNewFile.click();
        rootLogger.info("Add folder");
        linkCreateNewFolder.shouldBe(Condition.visible).click();
        MW.shouldBe(Condition.visible);
        $(byText(mwTitleNewFolder)).shouldBe(Condition.visible);
        String newDoc = "new word document";
        String newFolder = "new folder";
        $(byName("name")).sendKeys(newFolder);
        MW_BTN_SAVE.click();
        MW.shouldNotBe(Condition.visible);
        $(byText(newFolder)).shouldBe(Condition.visible);
        rootLogger.info(newFolder+" - Folder present");
        rootLogger.info("check validation duplicate folder");

        //todo
        rootLogger.info("edit folder");
        $$(byText(ERROR_DuplicatedFolder)).shouldHaveSize(1);

        rootLogger.info("delete folder");
        projectAllCheckbox.click();
        $(byLinkText("Delete")).click();
        submitConfirmAction();
        $$(byText(placeholderNoFiles)).shouldHaveSize(1);

    }
    @Test
    public void createProject_I_addTask() {
        projectTabTasks.click();
        $$(byText(placeholderEmptyList)).shouldHaveSize(1);
        $(byLinkText("Create task")).click();
        waitForModalWindow("Add new task");
        MW_BTN_OK.shouldBe(disabled);
        String taskName = "new task";
        fillField(MW_DeployTask_Title, taskName);
        MW_DATAPICKER_TODAY.click();
        MW_DATAPICKER_TODAY.pressEscape();
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(taskName)).shouldHaveSize(1);

        rootLogger.info("delete task");
        projectAllCheckbox.click();
        projectTabTask_BulkDelete.click();
        submitConfirmAction();



    }
    @Test
    public void createProject_L_deployEvent() {

    }
    @Test
    public void createProject_M_addCharges() {
        projectTabFin.click();
        $$(byText(placeholderEmptyList)).shouldHaveSize(1);
        genericButtonAdd.click();

        rootLogger.info("Create charge");
        waitForModalWindow("Add charge");

        
        rootLogger.info("Delete charge");
        $$(byText(placeholderEmptyList)).shouldHaveSize(1);


    }
    @Test
    public void createProject_N_changeTypes() {

    }
    @Test
    public void createProject_O_addFamilyProject() {

    }
    @Test
    public void createProject_P_addConversation() {

    }
    @Test
    public void createProject_S_cloneProkect() {

    }
    @Test
    public void createCommunityCase() {

    }




}