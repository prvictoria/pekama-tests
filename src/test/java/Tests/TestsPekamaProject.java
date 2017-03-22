package Tests;
import Page.TestsCredentials;
import Steps.StepsPekama;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.SoftAssertionError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.awt.*;
import java.io.IOException;

import static Page.CommunityDashboard.*;
import static Page.CommunityWizard.*;
import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.TrademarkEvents.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Page.Xero.*;
import static Steps.Messages.*;
import static Steps.StepsCommunity.checkCaseNameFirstRow;
import static Steps.StepsCommunity.selectExpert;
import static Steps.StepsExternal.*;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaProject {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String testProjectTitle = "new test project - "+ randomString(6);
    private static String testContactName = "name"+ randomString(10);
    private static String testContactSurname = "surname"+ randomString(10);
    private static String testProjectURL;
    private final static String TEST_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    private final static String TEST_USER_PEKAMA_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private final static String TEST_USER_XERO_PASSWORD = User3.XERO_PASSWORD.getValue();
    private final static String TEST_USER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private final static String COLLABORATOR_TEAM_NAME = User1.TEAM_NAME.getValue();
    private final static String TEST_CASE_TYPE = CaseType.TRADEMARK.getValue();
    private final static String TEST_CASE_COUNTRY = Countries.PITCAIRN_ISLANDS.getValue();
    private final static String TEST_CASE_NAME = "CUSTOM_NAME"+randomString(10);

    private final static String REQUESTER_EMAIL = TestsCredentials.User3.GMAIL_EMAIL.getValue();
    private final static String REQUESTER_PEKAMA_PASSWORD = TestsCredentials.User3.PEKAMA_PASSWORD.getValue();
    private final static String REQUESTER_NAME = TestsCredentials.User3.NAME.getValue();
    private final static String REQUESTER_SURNAME = TestsCredentials.User3.SURNAME.getValue();
    private final static String REQUESTER_FULL_TEAM_NAME = TestsCredentials.User3.FULL_TEAM_NAME.getValue();
    private final static String REQUESTER_NAME_SURNAME = TestsCredentials.User3.NAME_SURNAME.getValue();

    private final static String EXPERT_EMAIL = TestsCredentials.User2.GMAIL_EMAIL.getValue();
    private final static String EXPERT_PEKAMA_PASSWORD = TestsCredentials.User2.PEKAMA_PASSWORD.getValue();
    private final static String EXPERT_NAME = TestsCredentials.User2.NAME.getValue();
    private final static String EXPERT_SURNAME = TestsCredentials.User2.SURNAME.getValue();
    private final static String EXPERT_TEAM_NAME = TestsCredentials.User2.TEAM_NAME.getValue();
    private final static String EXPERT_FULL_TEAM_NAME = TestsCredentials.User2.FULL_TEAM_NAME.getValue();
    private static final String EXPERT_NAME_SURNAME = TestsCredentials.User2.NAME_SURNAME.getValue();
    private final static String INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        clearBrowserCache();
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                TEST_USER_EMAIL,
                TEST_USER_PEKAMA_PASSWORD,
                URL_LogIn);
        rootLogger.info("Create project");
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
        rootLogger.info("NW - New project");
        waitForModalWindow(TILE_MW_PROJECT);
        rootLogger.info("select project type");
        selectItemInDropdown(
                MW_Project_SelectType,
                MW_Project_InputType,
                CaseType.TRADEMARK.getValue());
        rootLogger.info("select defining");
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, Countries.PITCAIRN_ISLANDS.getValue());
        rootLogger.info("fill title");
        fillField(MW_Project_Title, testProjectTitle);
        rootLogger.info("submit");
        submitEnabledButton(MW_ProjectFinishButton);
        MW.shouldNot(exist);
        sleep(1000);
        testProjectURL = getActualUrl ();
        rootLogger.info("Project url: "+ testProjectURL);
        rootLogger.info("ProjectValues '"+testProjectTitle+"' created");
        waitForTextPresent(testProjectTitle);
    }
    @Test
    public void createProject_A_CheckDefaultStateAndDelete() {
        scrollUp();
        $$(byText(testProjectTitle)).filter(visible).shouldHaveSize(1);
        $$(byText(PLACEHOLDER_NO_CASES)).filter(visible).shouldHaveSize(1);
        // $$(byText(placeholderNoNumbers)).filter(visible).shouldHaveSize(1);
        // todo BUG #140183099 - https://www.pivotaltracker.com/n/projects/1239770/stories/140183099
        $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
        $$(byText(PLACEHOLDER_TeamChat)).shouldHaveSize(1);

        projectButtonPlus.click();
        projectPlusNewEvent.shouldBe(visible);
        projectPlusNewConversation.shouldBe(visible);
        projectPlusNewTask.shouldBe(visible);
        projectPlusNewDocument.shouldBe(visible);
        projectPlusNewFinancial.shouldBe(visible);
        projectPlusNewNumber.shouldBe(visible);
        projectPlusNewContact.shouldBe(visible);
        rootLogger.info("GUI - test passed");
        projectButtonPlus.pressEscape();
        PROJECT_BTN_DELETE.click();
        submitConfirmAction();
        sleep(4000);
        String url = url();
        Assert.assertEquals(URL_Dashboard, url);
        rootLogger.info("Project deleted by Owner");

        rootLogger.info("Check if user opens project link");
        openUrlWithBaseAuth(testProjectURL);
        sleep(3000);
        $(byXpath("//*[@class='alert alert-danger not-found-message']"))
                .shouldHave(text("This project was deleted by its owner. "));

        String notFoundUrl = url();
        Assert.assertEquals(URL_NotFound, notFoundUrl);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_B_editProjectName() throws AWTException {
        rootLogger.info("Rename Project by Owner");
        waitForTextPresent(testProjectTitle);
        scrollUp();
        TAB_INFO_ProjectTitle.shouldHave(text(testProjectTitle));
        TAB_INFO_ProjectTitle.click();
        String newProjectName = "New project name after edition "+ randomString(6);
        fillField(TAB_INFO_TitleInput, newProjectName);
        TAB_INFO_TitleSave.click();
        sleep(1000);
        refresh();
        rootLogger.info("Rename Project by Owner - test passed");
        waitForTextPresent(newProjectName);
        scrollUp();
        TAB_INFO_ProjectTitle.shouldHave(text(newProjectName));
        TAB_INFO_TitleEditButton.click();
        fillField(TAB_INFO_TitleInput, testProjectTitle);
        TAB_INFO_TitleSave.click();
        sleep(1000);
        refresh();
        TAB_INFO_ProjectTitle.shouldHave(text(testProjectTitle));

        rootLogger.info("Validation max length Project name");
        newProjectName = randomString(1025);
        TAB_INFO_TitleEditButton.click();
        fillField(TAB_INFO_TitleInput, newProjectName);
        TAB_INFO_TitleSave.click();
        sleep(1000);
        checkText(ERROR_MSG_VALIDATION_LENGTH_1024);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_C_AddNumber() {
        String codeType = "Equinox code";
        String codeValue = "2000/17/55-asd";
        rootLogger.info("select number from list - ");
        scrollDown();
        selectItemInDropdown(TAB_INFO_NumberNewSelect, TAB_INFO_NumberNewField, codeType);
        fillField(TAB_INFO_NumberReferenceField, codeValue);
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
        // $$(byText(placeholderNoNumbers)).filter(visible).shouldHaveSize(1);
        // todo BUG #140183099 - https://www.pivotaltracker.com/n/projects/1239770/stories/140183099
    }
    @Test
    public void createProject_D_addClassification() {
        String classNumber = "12";
        String classDescripton = "old description";
        scrollDown();
        TAB_INFO_ClassesAdd.waitUntil(visible, 20000).click();
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
        $$(byText(PLACEHOLDER_NO_CASES)).shouldHaveSize(1);
        rootLogger.info("All calsses were deleted - "+ PLACEHOLDER_NO_CASES);
    }
    @Test
    public void createProject_E_addCollaborator() {
        rootLogger.info("Add Pekama member - by default - as Collaborator");
        PROJECT_TAB_CONTACTS.click();
        projectTabContacts_AddCollaborator.click();
        waitForModalWindow(TITLE_MW_SHARE_PROJECT);
        selectTeam(COLLABORATOR_TEAM_NAME);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(OWNER)).shouldHaveSize(1);
        $$(byText(COLLABORATOR)).shouldHaveSize(1);

        rootLogger.info("Edit role to - "+ROLE_VIEWER);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow(TITLE_MW_CHANGE_COLLABORATOR);
        MW_BTN_OK.shouldBe(disabled);
        selectOption(MW_SHARE_PROJECT_SELECT_ROLE, ROLE_VIEWER);
      //  MW_SHARE_PROJECT_SELECT_ROLE.selectOption(new String[]{ROLE_VIEWER});
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(VIEWER)).shouldHaveSize(1);

        rootLogger.info("Edit role to - "+ROLE_ADMIN);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow(TITLE_MW_CHANGE_COLLABORATOR);
        MW_BTN_OK.shouldBe(disabled);
        selectOption(MW_SHARE_PROJECT_SELECT_ROLE, ROLE_ADMIN);
     //   MW_SHARE_PROJECT_SELECT_ROLE.selectOption(new String[]{ROLE_ADMIN});
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(ADMIN)).shouldHaveSize(1);

        rootLogger.info("Edit role to - "+ROLE_COLLABORATOR);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow(TITLE_MW_CHANGE_COLLABORATOR);
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
    @Test @Category(AllEmailsTests.class)
    public void createProject_E_inviteCollaborator() {
        rootLogger.info("Invite new team to Pekama project");
        PROJECT_TAB_CONTACTS.click();
        projectTabContacts_AddCollaborator.click();
        waitForModalWindow(TITLE_MW_SHARE_PROJECT);
        MW_SHARE_PROJECT_BTN_FIND.shouldBe(disabled);
        fillField(MW_SHARE_PROJECT_EMAIL, User5.GMAIL_EMAIL.getValue());
        submitEnabledButton(MW_SHARE_PROJECT_BTN_FIND);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(OWNER)).shouldHaveSize(1);
        $$(byText(COLLABORATOR)).shouldHaveSize(1);

        rootLogger.info("Check email - set vars");
        String USER_EMAIL = User5.GMAIL_EMAIL.getValue();
        String GMAIL_PASSWORD = User5.GMAIL_PASSWORD.getValue();
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
        rootLogger.info("Open inbox email");
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
        PROJECT_TAB_CONTACTS.click();
        // $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
        //todo BUG #140196199 https://www.pivotaltracker.com/n/projects/1239770/stories/140196199
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
        // $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
        //todo BUG #140196199 https://www.pivotaltracker.com/n/projects/1239770/stories/140196199
    }

    @Test
    public void createProject_F2_addExistedContact() {
        PROJECT_TAB_CONTACTS.click();
        // $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
        //todo BUG #140196199 https://www.pivotaltracker.com/n/projects/1239770/stories/140196199
        rootLogger.info("Select existed contact");
        selectItemInDropdown(projectTabContacts_AddSelectContact, projectTabContacts_AddContactInput, testContactName);
        rootLogger.info("Select relation");
        selectItemInDropdown(projectTabContacts_AddSelectRelation, projectTabContacts_AddRelationInput, ContactRelation.DOMESTIC_REPRESENTATIVE.getValue());
        projectTabContacts_AddContactButton.click();
        projectTabContacts_ContactName.shouldHave(Condition.exactText(testContactName+" "+testContactSurname));
        projectTabContacts_ContactRelation.shouldHave(Condition.exactText((ContactRelation.DOMESTIC_REPRESENTATIVE.getValue())));
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
        PROJECT_TAB_DOCS.click();
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
        fillField(TAB_DOCS_FILE_INPUT_NAME_IN_ROW, "New Excel sheet");
        TAB_DOCS_FILE_SAVE_IN_ROW.click();
        $(byText("New Excel sheet")).shouldBe(Condition.visible);

        rootLogger.info("delete file");
        projectAllCheckbox.click();
        LINK_DELETE.click();
        submitConfirmAction();

        $(byText(PLACEHOLDER_NoFiles)).shouldBe(Condition.visible);
        $$(byText(PLACEHOLDER_NoFiles)).filter(visible).shouldHaveSize(1);
      //  $(byText("No files found.Upload your first file. ")).shouldBe(Condition.visible);
        rootLogger.info(PLACEHOLDER_NoFiles);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_G2_addExcelDocument() {
        String newExcel = "new excel spreadsheet";
        PROJECT_TAB_DOCS.click();
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        modalWindowDeployFileTemplate(MW_DeployDoc_02TemplateExcel, newExcel);

        rootLogger.info("edit file");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_RENAME, newExcel);
        fillField(TAB_DOCS_FILE_INPUT_NAME_IN_ROW, "renamed file");
        TAB_DOCS_FILE_SAVE_IN_ROW.click();
        $(byText("renamed file")).shouldBe(Condition.visible);

        rootLogger.info("delete file via menu");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_DELETE, "renamed file");
        submitConfirmAction();

        $(byText(PLACEHOLDER_NoFiles)).shouldBe(Condition.visible);
        $$(byText(PLACEHOLDER_NoFiles)).filter(visible).shouldHaveSize(1);
        rootLogger.info(PLACEHOLDER_NoFiles);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_H1_addFolder() {
        String newFolder = "new folder";
        PROJECT_TAB_DOCS.click();
        TAB_DOCS_BTN_ADD.click();
        rootLogger.info("Add folder");
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        modalWindowCreateFolder(newFolder);

        rootLogger.info("edit folder");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_RENAME, newFolder);
        fillField(TAB_DOCS_FILE_INPUT_NAME_IN_ROW, "renamed folder");
        TAB_DOCS_FILE_SAVE_IN_ROW.click();
        $(byText("renamed folder")).shouldBe(Condition.visible);

        rootLogger.info("delete folder");
        projectAllCheckbox.click();
        $(byLinkText("Delete")).click();
        submitConfirmAction();
        $$(byText(PLACEHOLDER_NoFiles)).shouldHaveSize(1);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_H2_validationDuplicateFolder() {
        String newFolder1 = "folder1";
        String newFolder2 = "folder2";
        PROJECT_TAB_DOCS.click();
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        rootLogger.info("Add folder");
        modalWindowCreateFolder(newFolder1);

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
        fillField(TAB_DOCS_FILE_INPUT_NAME_IN_ROW, newFolder1);
        TAB_DOCS_FILE_SAVE_IN_ROW.click();
        $$(byText(ERROR_DuplicatedFolder)).shouldHaveSize(1);
        rootLogger.info("Test passed");

    }
    @Test
    public void createProject_H3_addSubFoldersTree() {
        String newFolder1 = "folder1";
        String newFolder2 = "folder2";
        String newFolder3 = "folder3";
        String newExcel = "excel";
        PROJECT_TAB_DOCS.click();
        sleep(1000);
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        rootLogger.info("Add folder");
        modalWindowCreateFolder(newFolder1);
        clickFolderRow(newFolder1);
        rootLogger.info("Add sub-folder");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_ADD_SUBFOLDER, newFolder1);
        modalWindowCreateFolder(newFolder2);
        clickFolderRow(newFolder2);
        rootLogger.info("Add sub-sub-folder");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_ADD_SUBFOLDER, newFolder2);
        modalWindowCreateFolder(newFolder3);
        clickFolderRow(newFolder3);
       // rootLogger.info("Add doc sub-sub-folder");

        rootLogger.info("Delete file via inline control");
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        modalWindowDeployFileTemplate(MW_DeployDoc_02TemplateExcel, newExcel);
        clickFileRow(newExcel);
        TAB_DOCS_FILE_DELETE.shouldBe(visible).click();
        submitConfirmAction();
        rootLogger.info("Test passed");
        $$(byText(newExcel)).shouldHaveSize(0);

    }
    @Test
    public void createProject_L1_autoDeployEvent() {
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
        sleep(500);
        MW.click();
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
//todo - bug #123521411 https://www.pivotaltracker.com/n/projects/1239770/stories/123521411
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
        PROJECT_TAB_CHARGES.click();
        checkText(PLACEHOLDER_EmptyList);
        TAB_CHARGES_ADD.click();
        rootLogger.info("Create charge");
        waitForModalWindow(TITLE_MW_CHARGE);
        MW_CHARGES_SELECT_FROM.shouldHave(text(TEST_USER_FULL_TEAM_NAME));
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, CHARGES_TYPE_ASSOCIATE);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, GBP);
        fillField(MW_CHARGES_INPUT_PRICE, "1000");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNot(visible);
        checkTextNotPresent(PLACEHOLDER_EmptyList);
        checkText(TEST_USER_FULL_TEAM_NAME+" ->");
        checkText(CHARGES_TYPE_ASSOCIATE);
        checkText(getCurrentDate());
        checkText("1,000.00 GBP");

        rootLogger.info("Delete charge");
        projectAllCheckbox.click();
        TAB_CHARGES_BTN_DELETE.click();
        submitConfirmAction();
        checkText(PLACEHOLDER_EmptyList);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_N_selectValues() {
        TAB_INFO_PROJECT_TYPE.shouldHave(text(CaseType.TRADEMARK.getValue()));
        selectItemInDropdown(
                TAB_INFO_SELECT_Defining,
                TAB_INFO_INPUT_Defining,
                Countries.NETHERLANDS_ANTILES.getValue());
        sleep(2000);
        selectItemInDropdown(
                TAB_INFO_SELECT_Type,
                TAB_INFO_INPUT_Type,"Basic Filing");
        sleep(2000);
        selectItemInDropdown(
                TAB_INFO_SELECT_SubType,
                TAB_INFO_INPUT_SubType, "Certification Mark");
        sleep(1000);
        checkText(Countries.NETHERLANDS_ANTILES.getValue());
        checkText("Basic Filing");
        checkText("Certification Mark");
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_O_addFamilyProject() {
        PROJECT_TAB_FAMILY.click();
        TAB_FAMILY_NEW.click();
        waitForModalWindow(TILE_MW_PROJECT);
        rootLogger.info("select project type");
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, CaseType.TRADEMARK.getValue());
        rootLogger.info("fill title");
        fillField(MW_Project_Title, "FAMILY-"+testProjectTitle);
        rootLogger.info("submit");
        submitEnabledButton(MW_ProjectFinishButton);
        MW.shouldNot(exist);
        sleep(2000);
        checkText(Countries.USA.getValue());
        checkText("FAMILY-"+testProjectTitle);

        PROJECT_TAB_FAMILY.click();
        String familyText = testProjectTitle.toUpperCase();
        TAB_FAMILY_1ST_ROW_TITLE.should(matchText(familyText));
       // checkText(result);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_S_cloneProject() {
        String currentURL = url();
        scrollUp();
        PROJECT_BTN_CLONE.click();
        submitConfirmAction();
        sleep(2000);
        String newURL = url();
        Assert.assertNotEquals(currentURL, newURL);

        PROJECT_TAB_FAMILY.click();
        String familyText = testProjectTitle.toUpperCase();
        rootLogger.info(familyText);
        TAB_FAMILY_1ST_ROW_TITLE.should(matchText(familyText));
        rootLogger.info("Test passed");
    }
    @Ignore
    @Test //todo - need to fix index update speed
    public void createProject_W_search() {
        String testEventType = TrademarkEvents.CASE_SUSPENDED.getValue();
        String testSearchFileName = "FILE-"+randomString(10);
        String testSearchFolderName = "FOLDER-"+randomString(10);
        String testSearchTaskName = "TASK-"+randomString(10);
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;

        String codeValue = createNumber();
        String classType = createClassification();
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

        sleep(5000);
        PROJECT_TAB_SEARCH.waitUntil(visible, 15000).click();
        checkText(PLACEHOLDER_NO_DATA);
        
        rootLogger.info("Search: "+testEventType);
        fillField(TAB_SEARCH_INPUT, testEventType);
        TAB_SEARCH_BTN.click();
        checkText(testEventType, 2); //result + timeline

//        rootLogger.info("Search: "+testSearchFileName);
//        fillField(TAB_SEARCH_INPUT, testSearchFileName);
//        TAB_SEARCH_BTN.click();
//        checkText(testSearchFileName);

//        rootLogger.info("Search: "+testSearchFolderName);
//        fillField(TAB_SEARCH_INPUT, testSearchFolderName);
//        TAB_SEARCH_BTN.click();
//        checkText(testSearchFolderName);

        rootLogger.info("Search: "+testSearchTaskName);
        fillField(TAB_SEARCH_INPUT, testSearchTaskName);
        TAB_SEARCH_BTN.click();
        checkText(testSearchTaskName);

//        rootLogger.info("Search: "+testSearchChargesType);
//        fillField(TAB_SEARCH_INPUT, testSearchChargesType);
//        TAB_SEARCH_BTN.click();
//        checkText(testSearchChargesType);

        rootLogger.info("Search: "+codeValue);
        fillField(TAB_SEARCH_INPUT, codeValue);
        TAB_SEARCH_BTN.click();
        checkText(PLACEHOLDER_NO_DATA);

        rootLogger.info("Search: "+classType);
        fillField(TAB_SEARCH_INPUT, classType);
        TAB_SEARCH_BTN.click();
        checkText(PLACEHOLDER_NO_DATA);

        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_ChargesXero_A_SendBill()  throws SoftAssertionError {
        String xeroLogin = TEST_USER_EMAIL;
        String xeroPassword = TEST_USER_XERO_PASSWORD;
        String price = "5000";
        rootLogger.info("Create Charge");
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;
        createCharge(testSearchChargesType, EUR, price);
        rootLogger.info("Start Xero flow");

        projectAllCheckbox.click();
        TAB_CHARGES_XERO.click();
        sleep(3000);

        if ($(byText("Invoice created")).isDisplayed() == false) {
              rootLogger.info("Modal window not displayed");
            try {
                switchTo().window(PAGE_TITLE_XERO_LOGIN);
                String url = getActualUrl();
                rootLogger.info(url);
                if (checkPageTitle(PAGE_TITLE_XERO_LOGIN)==false){
                    Assert.fail("Xero window NOT found");}
                fillField(extXeroEmail, xeroLogin);
                fillField(extXeroPassword, xeroPassword);
                submitEnabledButton(extXeroLogin);
                rootLogger.info("Xero login window submitted");

                switchTo().window(PAGE_TITLE_XERO_AUTH);
                url = getActualUrl();
                rootLogger.info(url);
                if (checkPageTitle(PAGE_TITLE_XERO_AUTH)==false){
                    Assert.fail("Window Xero Authorise window not found");}
                submitEnabledButton(extXeroAccept);
                rootLogger.info("Xero auth window submitted");
                sleep(5000);

                switchTo().window(PAGE_TITLE_PEKAMA);
                url = getActualUrl();
                rootLogger.info(url);
                if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
                    Assert.fail("Return to Pekama failed");}
                sleep(2000);
            }
                catch (SoftAssertionError e) {
                   rootLogger.info("Return to Pekama failed");
            }
        }

        if ($(byText("Invoice created")).isDisplayed()) {
              rootLogger.info("Modal window displayed");
              waitForModalWindow("Invoice created");
              submitEnabledButton(MW_BTN_YES);
              MW.waitUntil(not(visible), 10000);
              sleep(5000);
            checkThatWindowsQtyIs(2);
            for(String winHandle : getWebDriver().getWindowHandles()){
                  rootLogger.info(winHandle);
                  switchTo().window(winHandle);
                  getActualUrl();
              }

              if (checkPageTitle(PAGE_TITLE_XERO_LOGIN)==true){
                  try {
                      getActualUrl();
                      fillField(extXeroEmail, xeroLogin);
                      fillField(extXeroPassword, xeroPassword);
                      submitEnabledButton(extXeroLogin);
                      sleep(5000);
                      rootLogger.info("Xero login window submitted");
                  } catch (SoftAssertionError e) {
                      if (checkPageTitle(PAGE_TITLE_XERO_LOGIN) == false) {
                          rootLogger.info("Xero window NOT found");
                      }
                  }
              }
              if (checkPageTitle(PAGE_TITLE_XERO_BILLING) == true){
                  try {
                      getActualUrl();
                      sleep(3000);
                  } catch (SoftAssertionError e) {
                      if (checkPageTitle(PAGE_TITLE_XERO_BILLING) == false) {
                          rootLogger.info("Window Xero Authorise not found - goto label");
                      }
                  }
              }
        }
        sleep(3000);
        checkText("5,000.00", 2);
        close();
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_ChargesXero_B_ValidationNotSameCurrency(){
//        String xeroLogin = TEST_USER_EMAIL;
//        String xeroPassword = TEST_USER_XERO_PASSWORD;
        String price = "5000";
        rootLogger.info("Create Charge");
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;
        createCharge(testSearchChargesType, EUR, price);
        createCharge(testSearchChargesType, USD, price);
        rootLogger.info("Start Xero flow");
        projectAllCheckbox.click();
        TAB_CHARGES_XERO.click();

        waitForModalWindow("ERRORS");
        checkText("Financials have different currency codes");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
    }
    @Test
    public void createProject_ChargesXero_B_ValidationNotAllowedCurrency(){
//        String xeroLogin = TEST_USER_EMAIL;
//        String xeroPassword = TEST_USER_XERO_PASSWORD;
        String price = "5000";
        rootLogger.info("Create Charge");
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;
        createCharge(testSearchChargesType, USD, price);
        createCharge(testSearchChargesType, USD, price);
        rootLogger.info("Start Xero flow");
        projectAllCheckbox.click();
        TAB_CHARGES_XERO.click();

        waitForModalWindow("ERRORS");
        checkText("Organisation is not subscribed to currency USD");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
    }
    @Test//(timeout=240000)
    public void createProject_ChargesXero_C_MergeCharges(){
        String xeroLogin = TEST_USER_EMAIL;
        String xeroPassword = TEST_USER_XERO_PASSWORD;
        String price1 = "7777";
        String price2 = "1111";
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;

        rootLogger.info("Create 2 Charges");
        createCharge(testSearchChargesType, EUR, price1);
        createCharge(testSearchChargesType, EUR, price2);

        rootLogger.info("Start Xero flow");
        projectAllCheckbox.click();
        TAB_CHARGES_XERO.waitUntil(visible, 20000).click();
        sleep(3000);
        if ($(byText("Invoice created")).isDisplayed()) {
            rootLogger.info("Modal window displayed");
            waitForModalWindow("Invoice created");
            submitEnabledButton(MW_BTN_YES);
            MW.shouldNotBe(visible);}
            sleep(2000);
        try {
            switchTo().window(PAGE_TITLE_XERO_LOGIN);
            String url = getActualUrl();
            rootLogger.info(url);

            fillField(extXeroEmail, xeroLogin);
            fillField(extXeroPassword, xeroPassword);
            submitEnabledButton(extXeroLogin);
            rootLogger.info("Xero login window submitted");}
        catch (SoftAssertionError e) {
            if (checkPageTitle(PAGE_TITLE_XERO_LOGIN) == false) {
                rootLogger.info("Xero window NOT found");
            }
        }
        sleep(6000);
        try {
            switchTo().window(PAGE_TITLE_XERO_BILLING);
            sleep(6000);
            String url = getActualUrl();
            rootLogger.info(url);
            }
        catch (SoftAssertionError e) {
            if (checkPageTitle(PAGE_TITLE_XERO_BILLING) == false) {
                rootLogger.info("Window Xero Authorise not found");
            }

        }
        finally {
            sleep(3000);
            checkText("7,777.00", 2);
            checkText("1,111.00", 2);
            extXeroBillTotal.shouldHave(value("8,888.00"));
            checkValue("8,888.00", 2);
            close();
            rootLogger.info("Test passed");
       }
    }
    @Test
    public void createProject_ChargesModalWindowValidation() {
        String bigDecimal = "12345678901234567890";
        String floatString1 = "1.2345678901234567890";
        String floatString2 = "123456789012345678.90";
        PROJECT_TAB_CHARGES.waitUntil(visible, 15000).click();

        rootLogger.info("Validation empty field");
        TAB_CHARGES_ADD.waitUntil(enabled, 15000).click();
        waitForModalWindow(TITLE_MW_CHARGE);
        MW_CHARGES_SELECT_FROM.shouldHave(text(TEST_USER_FULL_TEAM_NAME));
        fillField(MW_CHARGES_INPUT_ITEM, LOREM_IPSUM_SHORT);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_REQUIRED_FIELD, 2);
        MW_BTN_CANCEL.click();
        MW.waitUntil(not(visible),20000);

        rootLogger.info("Validation max value HOUR, MIN, RATE");
        sleep(2000);
        submitEnabledButton(TAB_CHARGES_ADD);
        waitForModalWindow(TITLE_MW_CHARGE);
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, CHARGES_TYPE_ASSOCIATE);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, GBP);
        fillField(MW_CHARGES_INPUT_HOUR, bigDecimal);
        fillField(MW_CHARGES_INPUT_MIN, bigDecimal);
        fillField(MW_CHARGES_INPUT_RATE, bigDecimal);
        submitEnabledButton(MW_BTN_OK);
        checkText("Ensure that there are no more than 18 digits in total.", 2);
        checkText("Ensure this value is less than or equal to 2147483647.", 2);
        MW_BTN_CANCEL.click();
        MW.waitUntil(not(visible),20000);

        rootLogger.info("Validation max value - QTY, PRICE, VAT, DISCOUNT");
        TAB_CHARGES_ADD.waitUntil(enabled, 15000).click();
        waitForModalWindow(TITLE_MW_CHARGE);
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, CHARGES_TYPE_ASSOCIATE);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, GBP);
        fillField(MW_CHARGES_INPUT_QTY, bigDecimal);
        fillField(MW_CHARGES_INPUT_PRICE, bigDecimal);
        fillField(MW_CHARGES_INPUT_VAT, bigDecimal);
        fillField(MW_CHARGES_INPUT_DISCOUNT, bigDecimal);
        submitEnabledButton(MW_BTN_OK);
        checkText("Ensure this value is less than or equal to 2147483647." );
        checkText("Ensure that there are no more than 18 digits in total.", 2 );
        checkText("Ensure that there are no more than 7 digits in total.");
        MW_BTN_CANCEL.click();
        MW.waitUntil(not(visible),20000);

        rootLogger.info("Validation float - PRICE should be decimal");
        TAB_CHARGES_ADD.waitUntil(enabled, 15000).click();
        waitForModalWindow(TITLE_MW_CHARGE);
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, CHARGES_TYPE_ASSOCIATE);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, GBP);
        MW_CHARGES_INPUT_PRICE.clear();
        MW_CHARGES_INPUT_PRICE.setValue(floatString1);
        submitEnabledButton(MW_BTN_OK);
        checkText("Ensure that there are no more than 4 decimal places." );

        MW_CHARGES_INPUT_PRICE.clear();
        MW_CHARGES_INPUT_PRICE.setValue(floatString2);
        submitEnabledButton(MW_BTN_OK);
        checkText("Ensure that there are no more than 14 digits before the decimal point." );
        MW_BTN_CANCEL.click();
        MW.waitUntil(not(visible),20000);
        rootLogger.info("Test passed");

    }
    @Test
    public void createProject_Task_CRUD() {
        String taskName = "new task";
        PROJECT_TAB_TASKS.click();
        $$(byText(PLACEHOLDER_EmptyList)).shouldHaveSize(1);
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
    public void createProject_Task_Importance() {
        String taskName;
        taskName = taskCreate(
                4,
                TASK_IMPORTANCE_DEADLINE,
                "New");
        TASKS_ROWS.shouldHaveSize(1);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_DEADLINE,
                "Not Started",
                "Start")
        );
        taskName = taskCreate(
                3,
                TASK_IMPORTANCE_FATAL,
                "New");
        TASKS_ROWS.shouldHaveSize(2);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_FATAL,
                "Not Started",
                "Start")
        );
        taskName = taskCreate(
                2,
                TASK_IMPORTANCE_FINAL_DEADLINE,
                "New");
        TASKS_ROWS.shouldHaveSize(3);
        rootLogger.info("Check default task order - due date acceding");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_FINAL_DEADLINE,
                "Not Started",
                "Start")
        );
        taskName = taskCreate(
                1,
                TASK_IMPORTANCE_REMINDER,
                "New");
        TASKS_ROWS.shouldHaveSize(4);
        rootLogger.info("Check default task order - due date acceding");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_REMINDER,
                "Not Started",
                "Start")
        );
        taskName = taskCreate(
                0,
                TASK_IMPORTANCE_TASK,
                "New");
        TASKS_ROWS.shouldHaveSize(5);
        rootLogger.info("Check default task order - due date acceding");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                "Not Started",
                "Start")
        );
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_TasksAccept() {
        String taskAction = null;
        String taskStatus = null;
        String taskName = taskCreate();
        rootLogger.info(taskName);
        TASKS_ROWS.shouldHaveSize(1);
        rootLogger.info("New task - start");
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("Not Started")).getText();
        taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("Start")).getText();
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("Start")).click();

        rootLogger.info("Finish task status");
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("In progress")).getText();
        taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("finish")).getText();
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("finish")).click();

        rootLogger.info("Select Accepts task");
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("Completed")).getText();
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldHave(text("accept"));
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldHave(text("reject"));
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.click();

        rootLogger.info("Task was accepted");
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("Accepted")).getText();
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldNot(exist);
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldNot(exist);
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldNot(exist);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_TasksReject() {
        String taskAction;
        String taskStatus;
        String taskName = taskCreate();
        rootLogger.info(taskName);
        TASKS_ROWS.shouldHaveSize(1);
        TASKS_NAME_IN_FIRST_ROW.shouldHave(text(taskName));
        TASKS_PRIORITY_IN_FIRST_ROW.shouldHave(text("Task"));
        rootLogger.info("New task");
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("Not Started")).getText();
        rootLogger.info("Task status is - "+taskStatus);
        taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("Start")).getText();
        rootLogger.info("User able to - "+taskAction+" task");
        rootLogger.info("Click "+taskAction+" task");
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("Start")).click();

        rootLogger.info("Finish task status");
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("In progress")).getText();
        rootLogger.info("Task status is - "+taskStatus);
        taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("finish")).getText();
        rootLogger.info("User able to - "+taskAction+" task");
        rootLogger.info("Click "+taskAction+" task");
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("finish")).click();

        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("Completed")).getText();
        rootLogger.info("Task status is - "+taskStatus);
        taskAction =  TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldHave(text("accept")).getText();
        rootLogger.info("User able to - "+taskAction+" task");
        taskAction =  TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldHave(text("reject")).getText();
        rootLogger.info("User able to - "+taskAction+" task");
        rootLogger.info("Click "+taskAction+" task");
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.click();


        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("Rejected")).getText();
        rootLogger.info("Task status is - "+taskStatus);
        taskAction =  TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.getText();
        rootLogger.info("User able to - "+taskAction+" task");
        rootLogger.info("Click "+taskAction+" task");
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("Restart")).click();


        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text("In progress")).getText();
        rootLogger.info("Task status is - "+taskStatus);
        taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text("finish")).getText();
        rootLogger.info("User able to - "+taskAction+" task");
        rootLogger.info("Test passed");
    }
    @Ignore
    @Test  //todo
    public void createProject_TasksSorting() {

    }

    @Test
    public void checkRedirectToCommunityWizard() {
        if (testProjectTitle ==null || testProjectURL==null){
            Assert.fail("Project not created for precondition");
        }
        checkText("No community cases.");
        TAB_INFO_COMMUNITY_TITLE.waitUntil(visible, 20000).shouldHave(text("Services from the Pekama IP Community"));
        rootLogger.info("Check redirect to Community Wizard");
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        checkThatWindowsQtyIs(2);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("No redirect to Community");
        }
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);
        rootLogger.info("Test passed");
    }

    @Test
    public void createDraftCommunityCaseFormPekama() {
        if (testProjectTitle ==null || testProjectURL==null){
            Assert.fail("Project not created for precondition");
        }
        checkText("No community cases.");
        TAB_INFO_COMMUNITY_TITLE.waitUntil(visible, 20000).shouldHave(text("Services from the Pekama IP Community"));

        rootLogger.info("Check redirect to Community Wizard");
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        checkThatWindowsQtyIs(2);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("No redirect to Community");
        }
        submitCookie();
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);
        submitCookie();
        hideZopim();
        rootLogger.info("Create draft case");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);

        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);
        fillField(WIZARD_FIELD_CASE_NAME, TEST_CASE_NAME);
        submitEnabledButton(WIZARD_BTN_NEXT);
        BTN_SEND_INSTRUCTION.waitUntil(visible, 15000);
        rootLogger.info("Case was created");

        rootLogger.info("Open Pekama");
        switchTo().window(PAGE_TITLE_PEKAMA);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Community");
        }
        String actualUrl = getActualUrl();
        Assert.assertEquals
                ("Opened url not same to the project url", testProjectURL, actualUrl);
        refresh();
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).shouldBe(visible);

        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(TEST_CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text("cancel case"));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_DRAFT));

        rootLogger.info("Check project members");
        PROJECT_TAB_CONTACTS.shouldBe(visible).click();
        checkText(OWNER);
        checkText(REQUESTER_FULL_TEAM_NAME);
        checkText(ADMIN);
        checkText(INTRODUCER_NAME);
        checkText(VIEWER);
        checkText(EXPERT_FULL_TEAM_NAME);

        rootLogger.info("Check redirect to Community after click case row");
        PROJECT_TAB_INFO.shouldBe(visible).click();
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        sleep(2000);
        checkThatWindowsQtyIs(3);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("No redirect to Community");
        }
        BTN_SEND_INSTRUCTION.waitUntil(visible, 20000);
        sleep(1000);
        rootLogger.info("Test passed");
    }

    @Test
    public void createCaseAndCheckPekamaState() {
        if (testProjectTitle ==null || testProjectURL==null){
            Assert.fail("Project not created for precondition");
        }
        checkText("No community cases.");
        TAB_INFO_COMMUNITY_TITLE.waitUntil(visible, 20000)
                .shouldHave(text("Services from the Pekama IP Community"));
        rootLogger.info("Check redirect to Community Wizard");
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        checkThatWindowsQtyIs(2);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("No redirect to Community");
        }
        submitCookie();
        hideZopim();
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);

        rootLogger.info("Create case");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        sleep(3000);
        submitCookie();
        hideZopim();
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);
        fillField(WIZARD_FIELD_CASE_NAME, TEST_CASE_NAME);
        WIZARD_BTN_NEXT.click();
        BTN_SEND_INSTRUCTION.waitUntil(visible, 20000).click();
        WIZARD_BTN_INSTRUCT_NOW.shouldBe(visible).click();
        waitForModalWindow("Congratulations!");
        MW_CONGRATULATION_OK.click();
        MW.shouldNotBe(visible);
        sleep(2000);
        checkCaseNameFirstRow(TEST_CASE_NAME);
        rootLogger.info("Case was created");

        rootLogger.info("Open Pekama");
        sleep(2000);
        switchTo().window(PAGE_TITLE_PEKAMA);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Pekama");
        }

        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).shouldBe(visible);
        rootLogger.info("Check sent message");
        checkText(MSG_DEFAULT_SENT_INSTRUCTION);

        refresh();
        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(TEST_CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text("withdraw instructions"));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_SENT));

        rootLogger.info("Check project members");
        PROJECT_TAB_CONTACTS.shouldBe(visible).click();
        checkText(OWNER);
        checkText(REQUESTER_FULL_TEAM_NAME);
        checkText(ADMIN, 2);
        checkText(INTRODUCER_NAME);
        checkText(EXPERT_FULL_TEAM_NAME);
        rootLogger.info("Test passed");
    }

}