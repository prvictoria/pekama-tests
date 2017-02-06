package com.pekama.app;
import Steps.*;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.awt.*;

import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaConversationProject.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.TrademarkEvents.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaProject {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String testProjectTitle = "new test project - "+ randomString(6);
    private static String testContactName = "name"+ randomString(10);
    private static String testContactSurname = "surname"+ randomString(10);
    private static String defaultProjectURL;
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
        waitForModalWindow(TILE_MW_PROJECT);
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
        defaultProjectURL = getActualUrl ();
        rootLogger.info("ProjectValues '"+testProjectTitle+"' created");
        waitForTextPresent(testProjectTitle);
    }
//    @After
//    public void after() {
//        rootLogger.info("delete project - '"+testProjectTitle"'");
//
//        scrollUp();
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
        $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
        $$(byText("Team chat is great for conversations between groups of people, where all the group members should see the conversation all the time.")).shouldHaveSize(1);
        projectButtonPlus.click();
        projectPlusNewEvent.shouldBe(visible);
        projectPlusNewConversation.shouldBe(visible);
        projectPlusNewTask.shouldBe(visible);
        projectPlusNewDocument.shouldBe(visible);
        projectPlusNewFinancial.shouldBe(visible);
        projectPlusNewNumber.shouldBe(visible);
        projectPlusNewContact.shouldBe(visible);
        rootLogger.info("GUI test passed");
    }
    @Test
    public void createProject_B_editProjectName() throws AWTException {
        waitForTextPresent(testProjectTitle);
        scrollUp();
        TAB_INFO_ProjectTitle.shouldHave(text(testProjectTitle));
        TAB_INFO_ProjectTitle.click();
        String newProjectName = "New project name after edition "+ randomString(6);
        fillField(TAB_INFO_TitleInput, newProjectName);
        TAB_INFO_TitleSave.click();
        sleep(1000);
        refresh();

        waitForTextPresent(newProjectName);
        scrollUp();
        TAB_INFO_ProjectTitle.shouldHave(text(newProjectName));
        TAB_INFO_TitleEditButton.click();
        fillField(TAB_INFO_TitleInput, testProjectTitle);
        TAB_INFO_TitleSave.click();
        sleep(1000);
        refresh();
        TAB_INFO_ProjectTitle.shouldHave(text(testProjectTitle));
    }
    @Test
    public void createProject_C_AddNumber() {
        String codeType = "Equinox code";
        String codeValue = "2000/17/55-asd";
        rootLogger.info("select number from list - ");
        selectItemInDropdown(TAB_INFO_NumberNewSelect, TAB_INFO_NumberNewField, codeType);
        fillField(TAB_INFO_NumberReferenceField, codeValue);
        scrollDown();
        TAB_INFO_NumberAdd.click();
        TAB_INFO_NumberRow01Type.shouldHave(text(codeType));

        rootLogger.info("open inline form");
        TAB_INFO_NumberRow01Edit.click();
        TAB_INFO_Number_EDIT_REFERENCE_BTN_SAVE.waitUntil(visible, 10000).shouldBe(disabled);
        rootLogger.info("edit number inline - ");
        String newCodeValue = "8888-1111-lkjh";
        String newCodeType = "Reference Number";
        selectItemInDropdown(TAB_INFO_Number_EDIT_REFERENCE_TYPE_SELECT, TAB_INFO_Number_EDIT_REFERENCE_TYPE_INPUT, newCodeType);
        fillField(TAB_INFO_Number_EDIT_REFERENCE_VALUE_INPUT, newCodeValue);
        submitEnabledButton(TAB_INFO_Number_EDIT_REFERENCE_BTN_SAVE);
        $$(byText(newCodeValue)).shouldHaveSize(1);
        $$(byText(newCodeType)).shouldHaveSize(2);
        $$(byText(codeValue)).shouldHaveSize(0);
        $$(byText(codeType)).shouldHaveSize(0);

        rootLogger.info("delete number");
        TAB_INFO_NumberRow01Collapse.click();
        TAB_INFO_NumberRow01Delete.click();
        submitConfirmAction();
        $$(byText(placeholderNoNumbers)).shouldHaveSize(1);
    }
    @Test
    public void createProject_D_addClassification() {
        String classNumber = "12";
        String classDescripton = "old description";
        scrollDown();
        TAB_INFO_ClassesAdd.click();
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
        TAB_INFO_ClassRow01Edit.click();
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
        TAB_INFO_ClassRow01delete.click();
        submitConfirmAction();
        $$(byText(placeholderNoCases)).shouldHaveSize(1);
        rootLogger.info("All calsses were deleted - "+placeholderNoCases);
    }
    @Test
    public void createProject_E_addCollaborator() {
        rootLogger.info("Add Pekama member - by default - as Collaborator");
        projectTabContacts.click();
        projectTabContacts_AddCollaborator.click();
        waitForModalWindow("Share ProjectValues");
        selectTeam(User3.TEAM_NAME.getValue());
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(OWNER)).shouldHaveSize(1);
        $$(byText(COLLABORATOR)).shouldHaveSize(1);

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
    @Test
    public void createProject_E_inviteCollaborator() {
        rootLogger.info("Invite new team to Pekama project");
        projectTabContacts.click();
        projectTabContacts_AddCollaborator.click();
        waitForModalWindow("Share ProjectValues");
        MW_SHARE_PROJECT_BTN_FIND.shouldBe(disabled);
        fillField(MW_SHARE_PROJECT_EMAIL, User5.GMAIL_EMAIL.getValue());
        submitEnabledButton(MW_SHARE_PROJECT_BTN_FIND);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(OWNER)).shouldHaveSize(1);
        $$(byText(COLLABORATOR)).shouldHaveSize(1);

        rootLogger.info("Check email - set vars");
        String USER_EMAIL = User5.GMAIL_EMAIL.getValue();
//        String thisEmailSubject = emailSubject(testProjectTitle);
  //      SelenideElement EMAIL_SUBJECT = $(byXpath(thisEmailSubject));
       // SelenideElement EMAIL_SUBJECT = $(byXpath(emailSubject(testProjectTitle)));
        SelenideElement EMAIL_SUBJECT = emailSubject(testProjectTitle);

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
    @Test
    public void createProject_F1_addNewContact_Person() {
        projectTabContacts.click();
        $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
        rootLogger.info("Select create new");
        projectTabContacts_AddSelectContact.click();
        fillField(projectTabContacts_AddContactInput, testContactName);
        projectTabContacts_CREATE_NEW_CONTACT.click();
        rootLogger.info("Create new contact");
        waitForModalWindow("Contact");
        checkInputValue(MW_Contact_NAME, testContactName);
        fillField(MW_Contact_SURNAME, testContactSurname);
        checkInputValue(MW_Contact_SURNAME, testContactSurname);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(testContactName+" "+testContactSurname)).filter(visible).shouldHaveSize(1);
        rootLogger.info("Select relation");
        selectItemInDropdown(projectTabContacts_AddSelectRelation, projectTabContacts_AddRelationInput, ContactRelation.ATTORNEY.getValue());
        rootLogger.info("Add contact to ProjectValues");
        projectTabContacts_AddContactButton.click();
        projectTabContacts_ContactName.shouldHave(Condition.exactText(testContactName+" "+testContactSurname));
        projectTabContacts_ContactRelation.shouldHave(Condition.exactText((ContactRelation.ATTORNEY.getValue())));

        rootLogger.info("delete contact relation");
        projectTabContacts_ContactDelete.click();
        submitConfirmAction();
        $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
    }

    @Test
    public void createProject_F2_addExistedContact() {
        projectTabContacts.click();
        $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
        rootLogger.info("Select existed contact");
        selectItemInDropdown(projectTabContacts_AddSelectContact, projectTabContacts_AddContactInput, testContactName);
        rootLogger.info("Select relation");
        selectItemInDropdown(projectTabContacts_AddSelectRelation, projectTabContacts_AddRelationInput, ContactRelation.DOMESTIC_REPRESENTATIVE.getValue());
        projectTabContacts_AddContactButton.click();
        projectTabContacts_ContactName.shouldHave(Condition.exactText(testContactName+" "+testContactSurname));
        projectTabContacts_ContactRelation.shouldHave(Condition.exactText((ContactRelation.DOMESTIC_REPRESENTATIVE.getValue())));

        //todo
        rootLogger.info("Edit fields contact inline");
        projectTabContacts_ContactEdit.click();
        projectTabContacts_FormRelationSelect.selectOption(ContactRelation.CLIENT_COMPANY.getValue());
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

        rootLogger.info("Check saved values");
        projectTabContacts_ContactEdit.shouldBe(visible).click();
        projectTabContacts_FormCountrySelect.shouldHave(Condition.text(nameCountryIreland));
        projectTabContacts_FormRelationSelect.shouldHave(Condition.text(ContactRelation.CLIENT_COMPANY.getValue()));
        String savedOption = projectTabContacts_FormRelationSelect.getSelectedText();
        Assert.assertEquals(ContactRelation.CLIENT_COMPANY.getValue(), savedOption);
        checkInputValue(projectTabContacts_FormOwnership, "99");
        checkInputValue(projectTabContacts_FormEntity, "newEntity");
        checkInputValue(projectTabContacts_FormFirstName, "NEWperson");
        checkInputValue(projectTabContacts_FormLastName, "NEWman03");
        checkInputValue(projectTabContacts_FormEmail, "NEWcontact_01_mail@mail.com");
        checkInputValue(projectTabContacts_FormPhone, "newPhone");
        checkInputValue(projectTabContacts_FormFax, "newFax");
        checkInputValue(projectTabContacts_FormMobile, "newMobile");
        checkInputValue(projectTabContacts_FormStreet, "newStreet");
        checkInputValue(projectTabContacts_FormPostal, "newZip");
        checkInputValue(projectTabContacts_FormCity, "newCity");
        checkInputValue(projectTabContacts_FormRegion, "newRegion");

    }
    @Test
    public void createProject_G1_addWordDocument() {
        String newDoc = "new word document";
        projectTabDocs.click();
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        waitForModalWindow(TITLE_MW_ADD_DOCUMENT);
        MW_DeployDoc_01TemplateWord.shouldBe(Condition.visible).click();
        fillField(MW_DEPLOY_DOC_INPUT_FILE_NAME, newDoc);
        MW_DEPLOY_DOC_BTN_CREATE.click();
        MW.shouldNotBe(Condition.visible);
        $(byText(newDoc)).shouldBe(Condition.visible);
        rootLogger.info(newDoc+" - file present");

        rootLogger.info("edit file");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_RENAME, newDoc);
        fillField(TAB_DOCS_FILE_INPUT_NAME, "New Excel sheet");
        TAB_DOCS_FILE_SAVE.click();
        $(byText("New Excel sheet")).shouldBe(Condition.visible);

        rootLogger.info("delete file");
        projectAllCheckbox.click();
        LINK_DELETE.click();
        submitConfirmAction();

        $(byText(placeholderNoFiles)).shouldBe(Condition.visible);
        $$(byText(placeholderNoFiles)).filter(visible).shouldHaveSize(1);
      //  $(byText("No files found.Upload your first file. ")).shouldBe(Condition.visible);
        rootLogger.info(placeholderNoFiles);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_G2_addExcelDocument() {
        String newExcel = "new excel spreadsheet";
        projectTabDocs.click();
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        deployFileTemplate(MW_DeployDoc_02TemplateExcel, newExcel);

        rootLogger.info("edit file");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_RENAME, newExcel);
        fillField(TAB_DOCS_FILE_INPUT_NAME, "renamed file");
        TAB_DOCS_FILE_SAVE.click();
        $(byText("renamed file")).shouldBe(Condition.visible);

        rootLogger.info("delete file via menu");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_DELETE, "renamed file");
        submitConfirmAction();

        $(byText(placeholderNoFiles)).shouldBe(Condition.visible);
        $$(byText(placeholderNoFiles)).filter(visible).shouldHaveSize(1);
        rootLogger.info(placeholderNoFiles);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_H1_addFolder() {
        String newFolder = "new folder";
        projectTabDocs.click();
        TAB_DOCS_BTN_ADD.click();
        rootLogger.info("Add folder");
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        createFolder(newFolder);

        rootLogger.info("edit folder");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_RENAME, newFolder);
        fillField(TAB_DOCS_FILE_INPUT_NAME, "renamed folder");
        TAB_DOCS_FILE_SAVE.click();
        $(byText("renamed folder")).shouldBe(Condition.visible);

        rootLogger.info("delete folder");
        projectAllCheckbox.click();
        $(byLinkText("Delete")).click();
        submitConfirmAction();
        $$(byText(placeholderNoFiles)).shouldHaveSize(1);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_H2_validationDuplicateFolder() {
        String newFolder1 = "folder1";
        String newFolder2 = "folder2";
        projectTabDocs.click();
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        rootLogger.info("Add folder");
        createFolder(newFolder1);

        rootLogger.info("Add same folder");
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        waitForModalWindow(TITLE_MW_NEW_FOLDER);
        MW_BTN_SAVE.shouldBe(disabled);
        fillField(MW_NEW_FOLDER_INPUT_NAME, newFolder1);
        submitEnabledButton(MW_BTN_SAVE);
        $$(byText(ERROR_DuplicatedFolder)).shouldHaveSize(1);

        rootLogger.info("Check max length field validation");
        MW_NEW_FOLDER_INPUT_NAME.clear();
        fillField(MW_NEW_FOLDER_INPUT_NAME, randomString(1025));
        submitEnabledButton(MW_BTN_SAVE);
        $$(byText(ERROR_MSG_VALIDATION_LENGTH_1024)).shouldHaveSize(1);

        rootLogger.info("Create 2-nd folder");
        fillField(MW_NEW_FOLDER_INPUT_NAME, newFolder2);
        submitEnabledButton(MW_BTN_SAVE);
        MW.shouldNotBe(Condition.visible);
        $(byText(newFolder2)).shouldBe(Condition.visible);
        rootLogger.info(newFolder2+" - Folder present");

        rootLogger.info("check validation duplicate folder while rename");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_RENAME, newFolder2);
        fillField(TAB_DOCS_FILE_INPUT_NAME, newFolder1);
        TAB_DOCS_FILE_SAVE.click();
        $$(byText(ERROR_DuplicatedFolder)).shouldHaveSize(1);
        rootLogger.info("Test passed");

    }
    @Test
    public void createProject_H3_addSubFoldersTree() {
        String newFolder1 = "folder1";
        String newFolder2 = "folder2";
        String newFolder3 = "folder3";
        String newExcel = "excel";
        projectTabDocs.click();
        sleep(1000);
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        rootLogger.info("Add folder");
        createFolder(newFolder1);
        clickFolderRow(newFolder1);
        rootLogger.info("Add sub-folder");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_ADD_SUBFOLDER, newFolder1);
        createFolder(newFolder2);
        clickFolderRow(newFolder2);
        rootLogger.info("Add sub-sub-folder");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_ADD_SUBFOLDER, newFolder2);
        createFolder(newFolder3);
        clickFolderRow(newFolder3);
       // rootLogger.info("Add doc sub-sub-folder");

        rootLogger.info("Delete file via inline control");
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        deployFileTemplate(MW_DeployDoc_02TemplateExcel, newExcel);
        clickFileRow(newExcel);
        TAB_DOCS_FILE_DELETE.shouldBe(visible).click();
        submitConfirmAction();
        rootLogger.info("Test passed");
        $$(byText(newExcel)).shouldHaveSize(0);

    }
    @Test
    public void createProject_I_addTask() {
        String taskName = "new task";
        projectTabTasks.click();
        $$(byText(placeholderEmptyList)).shouldHaveSize(1);
        TAB_TASKS_ADD.click();
        TAB_TASKS_NEW_TASK.shouldBe(visible).click();

        waitForModalWindow(TITLE_MW_NEW_TASK);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_DeployTask_Title, taskName);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(taskName)).shouldHaveSize(1);

        rootLogger.info("delete task");
        projectAllCheckbox.click();
        TAB_TASKS_BTN_DELETE.click();
        submitConfirmAction();

    }
    @Test
    public void createProject_L1_autodeployEvent() {
        scrollUp();
        rootLogger.info("Check timeline state");
        BTN_HIDE_TIMELINE.shouldBe(visible);

        rootLogger.info("Check auto-deploy less important event");
        TIMELINE_CheckboxLessImportant.setSelected(true);
        $$(byText(MARK_CREATED.getValue())).filter(visible).shouldHaveSize(1);

        rootLogger.info("Edit event info");
        TIMELINE_EditEvent.click();
        waitForModalWindow(TITLE_MW_EVENT);
        MW_BTN_SAVE.shouldBe(disabled);
        fillField(MW_EVENT_INPUT_INFO, LOREM_IPSUM_SHORT);
        submitEnabledButton(MW_BTN_SAVE);
        MW.shouldNotBe(visible);
        $(byText(MARK_CREATED.getValue())).click();
        $$(byText(LOREM_IPSUM_SHORT)).filter(visible).shouldHaveSize(1);

        rootLogger.info("Delete event");
        TIMELINE_DeleteEvent.click();
        submitConfirmAction();
        $$(byText(MARK_CREATED.getValue())).filter(visible).shouldHaveSize(0);
        checkTextNotPresent(MARK_CREATED.getValue());
        rootLogger.info("Test passed");

    }
    @Test
    public void createProject_L2_deployNewEvent() {
        scrollUp();
        rootLogger.info("Deploy new event");
        BTN_HIDE_TIMELINE.shouldBe(visible);
        projectButtonPlus.shouldBe(visible).click();
        projectPlusNewEvent.shouldBe(visible).click();
        waitForModalWindow(TITLE_MW_EVENT);
        MW_BTN_SAVE.shouldBe(disabled);
        MW_INPUT_DATE.click();
        MW_INPUT_DATE.pressEscape();
        fillField(MW_EVENT_INPUT_INFO, LOREM_IPSUM_SHORT);
        selectItemInDropdown(MW_EVENT_SELECT_TYPE, MW_EVENT_INPUT_TYPE, APPLICATION_REGISTERED.getValue());
        submitEnabledButton(MW_BTN_SAVE);
        MW.shouldNotBe(visible);
        $$(byText(APPLICATION_REGISTERED.getValue())).filter(visible).shouldHaveSize(1);

        rootLogger.info("Check expanded timeline");
        TIMELINE_CheckboxShrinkedEventsView.setSelected(true);
        TIMELINE_CheckboxLessImportant.setSelected(true);
        checkText(APPLICATION_REGISTERED.getValue());
        checkText(MARK_CREATED.getValue());
        rootLogger.info("Test passed");
        //todo -bug
//        TIMELINE_CheckboxLessImportant.setSelected(true);
//        checkText(APPLICATION_REGISTERED.getValue());
//        checkTextNotPresent(MARK_CREATED.getValue());
    }
    @Test
    public void createProject_L3_lessImportantEvent() {
        scrollUp();
        rootLogger.info("Check DISPLAY less important event ");
        TIMELINE_CheckboxLessImportant.setSelected(true);
        checkText(MARK_CREATED.getValue());

        rootLogger.info("Check HIDE less important event ");
        TIMELINE_CheckboxLessImportant.setSelected(true);
        checkTextNotPresent(MARK_CREATED.getValue());
        rootLogger.info("Test passed");
    }

    @Test
    public void createProject_M_addChargePositive() {
        projectTabFin.click();
        checkText(placeholderEmptyList);
        TAB_CHARGES_ADD.click();
        rootLogger.info("Create charge");
        waitForModalWindow(TITLE_MW_CHARGE);
        MW_CHARGES_SELECT_FROM.shouldHave(text(User2.FULL_TEAM_NAME.getValue()));
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, CHARGES_TYPE_ASSOCIATE);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, GBP);
        fillField(MW_CHARGES_INPUT_PRICE, "1000");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNot(visible);
        checkTextNotPresent(placeholderEmptyList);
        checkText(User2.FULL_TEAM_NAME.getValue()+" ->");
        checkText(CHARGES_TYPE_ASSOCIATE);
        checkText(getCurrentDate());
        checkText("1,000.00 GBP");

        rootLogger.info("Delete charge");
        projectAllCheckbox.click();
        TAB_CHACRGES_BTN_DELETE.click();
        submitConfirmAction();
        checkText(placeholderEmptyList);
        rootLogger.info("Test passed");
    }
    @Test  //todo
    public void createProject_N_selectValues() {
        TAB_INFO_PROJECT_TYPE.shouldHave(text(CaseType.TRADEMARK.getValue()));
        selectItemInDropdown(TAB_INFO_SELECT_Defining, TAB_INFO_INPUT_Defining, Countries.NETHERLAND_ANTILES.getValue());
        selectItemInDropdown(TAB_INFO_SELECT_Type, TAB_INFO_INPUT_Type, "Basic Filing");
        selectItemInDropdown(TAB_INFO_SELECT_SubType, TAB_INFO_INPUT_SubType, "Certification Mark");
        sleep(1000);
        checkText(Countries.NETHERLAND_ANTILES.getValue());
        checkText("Basic Filing");
        checkText("Certification Mark");
        rootLogger.info("Test passed");
    }
    @Test  //todo
    public void createProject_O_addFamilyProject() {
        projectTabFamily.click();
        TAB_FAMILY_NEW.click();
        waitForModalWindow(TILE_MW_PROJECT);
        rootLogger.info("select project type");
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, CaseType.TRADEMARK.getValue());
        rootLogger.info("select defining");
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, Countries.PITCAIRN_ISLANDS.getValue());
        rootLogger.info("fill title");
        fillField(MW_Project_Title, "FAMILY-"+testProjectTitle);
        rootLogger.info("submit");
        submitEnabledButton(MW_ProjectFinishButton);
        MW.shouldNot(exist);
        sleep(1000);

        open(defaultProjectURL);
        projectTabFamily.click();
        checkText("FAMILY-"+testProjectTitle);
        rootLogger.info("Test passed");
    }

    @Test  //todo
    public void createProject_P_addTeamConversation() {
        rootLogger.info("Create thered in private zone");
        CONVERSATION_BTN_Team.shouldBe(visible);
        checkText(CONVERSATION_PLACEHOLDER_IN_TEAM_TAB);
        CONVERSATION_BTN_Client.click();
        checkText(CONVERSATION_PLACEHOLDER_IN_CLIENT_TAB);
        CONVERSATION_BTN_Team.click();
        CONVERSATION_BTN_New.click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        fillField(MW_CONVERSATION_INPUT_Subject, "TEAM_THREAD IN PRIVATE ZONE");
        MW_BTN_CREATE.click();
        MW.shouldNotBe(visible);
        checkText("TEAM_THREAD IN PRIVATE ZONE");
        CONVERSATION_LABEL_ACTIVE_TAB.shouldHave(text(CONVERSATION_TEAM_TAB_NAME));
        CONVERSATION_INPUT_TEXT_COLLAPSED.click();
        fillField(CONVERSATION_TEXT_EDITOR, "new message");
        CONVERSATION_BTN_POST.click();
        checkText("new message");

        rootLogger.info("Delete message");
        CONVERSATION_MsgDelete.click();
        submitConfirmAction();
        checkTextNotPresent("new message");

        rootLogger.info("Test passed");
    }

    @Test  //todo
    public void createProject_P_addExternalConversation() {


        rootLogger.info("Test passed");
    }

    @Test  //todo
    public void createProject_S_cloneProject() {
        String currentURL = url();
        scrollUp();
        PROJECT_BTN_CLONE.click();
        submitConfirmAction();
        String newURL = url();
        Assert.assertNotEquals(currentURL, newURL);
        rootLogger.info("Test passed");
        projectTabFamily.click();
        checkText(testProjectTitle);
    }

    @Test  //todo
    public void createProject_W_search() {
        String testEventType = TrademarkEvents.CASE_SUSPENDED.getValue();
        String testSearchFileName = "FILE-"+randomString(10);
        String testSearchFolderName = "FOLDER-"+randomString(10);
        String testSearchTaskName = "TASK-"+randomString(10);
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;


        rootLogger.info("Create Event");
        createEvent(testEventType);
        rootLogger.info("Create Doc");
        createFileInRoot(MW_DeployDoc_01TemplateWord, testSearchFileName);
        rootLogger.info("Create Folder");
        createFolderInRoot(testSearchFolderName);
        rootLogger.info("Create Task");
        createTask(testSearchTaskName);
        rootLogger.info("Create Charge");
        createCharge(testSearchChargesType, EUR, "5000");

        projectTabSearch.click();
        checkText(PLACEHOLDER_NO_DATA);
        
        rootLogger.info("Search: "+testEventType);
        fillField(TAB_SEARCH_INPUT, testEventType);
        TAB_SEARCH_BTN.click();
        checkText(testEventType);

        rootLogger.info("Search: "+testSearchFileName);
        fillField(TAB_SEARCH_INPUT, testSearchFileName);
        TAB_SEARCH_BTN.click();
        checkText(testSearchFileName);

        rootLogger.info("Search: "+testSearchFolderName);
        fillField(TAB_SEARCH_INPUT, testSearchFolderName);
        TAB_SEARCH_BTN.click();
        checkText(testSearchFolderName);

        rootLogger.info("Search: "+testSearchTaskName);
        fillField(TAB_SEARCH_INPUT, testSearchTaskName);
        TAB_SEARCH_BTN.click();
        checkText(testSearchTaskName);

        rootLogger.info("Search: "+testSearchChargesType);
        fillField(TAB_SEARCH_INPUT, testSearchChargesType);
        TAB_SEARCH_BTN.click();
        checkText(testSearchChargesType);

        rootLogger.info("Search: clear text");
        fillField(TAB_SEARCH_INPUT, "");
        TAB_SEARCH_BTN.click();
        checkText(PLACEHOLDER_NO_DATA);

        rootLogger.info("Test passed");
    }

    @Ignore
    @Test  //todo
    public void createCommunityCase() {
        TAB_INFO_COMMUNITY_TITLE.shouldHave(text("Services from the Pekama IP Community"));
        TAB_INFO_COMMUNITY_BTN_START_NEW.click();







        rootLogger.info("Test passed");
    }

}