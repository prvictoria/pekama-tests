package Tests;
import Page.TestsCredentials;
import Steps.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;
import javax.mail.MessagingException;
import java.awt.*;
import java.io.IOException;

import static Page.CommunityDashboard.*;
import static Page.CommunityWizard.*;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.Countries.*;
import static Page.TestsCredentials.TrademarkEvents.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.MessagesValidator.ValidationInviteInProject.projectBackLink;
import static Steps.ObjectTask.checkTaskData;
import static Steps.StepsCommunity.checkCaseNameFirstRow;
import static Steps.StepsCommunity.selectExpert;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
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
    private static String projectUrl;
    private final static String TEST_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    private final static String TEST_USER_NAME_SURNAME = User3.NAME_SURNAME.getValue();
    private final static String TEST_USER_PEKAMA_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private final static String TEST_USER_XERO_PASSWORD = User3.XERO_PASSWORD.getValue();
    private final static String TEST_USER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private final static String COLLABORATOR_TEAM_NAME = User1.TEAM_NAME.getValue();
    private static String TEST_CASE_TYPE = null;
    private final static String TEST_CASE_COUNTRY = PITCAIRN_ISLANDS.getValue();
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
    private static ObjectContact contact = new ObjectContact();
    @Rule
    public Timeout tests = Timeout.seconds(600);
    private static boolean skipBefore = false;
    private static boolean nextIsImapTest = false;
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        if(nextIsImapTest==false) {
            holdBrowserAfterTest();
            TEST_CASE_TYPE = MATTER_TYPE_TRADEMARK;
            MessagesIMAP emailTask = new MessagesIMAP();
            emailTask.imapSearchEmailDeleteAll(
                    User5.GMAIL_EMAIL.getValue(),
                    User5.GMAIL_PASSWORD.getValue());
        }
        else {rootLogger.info("Before suite was skipped");}
    }
    @Before
    public void before() {
        if(nextIsImapTest==false) {
            clearBrowserCache();
            User requester = new User();
            requester.loginByURL(
                    REQUESTER_EMAIL, REQUESTER_PEKAMA_PASSWORD, URL_LogIn);
            if (skipBefore == false) {
                DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 30000).click();
                testProjectTitle = submitMwNewProject(
                        "INNER_VALIDATION",
                        TEST_CASE_TYPE,
                        PITCAIRN_ISLANDS.getValue(),
                        null,
                        null);
                projectUrl = getActualUrl();
                rootLogger.info("Project url: " + projectUrl);
                rootLogger.info("ProjectValues '" + testProjectTitle + "' created");
                waitForTextPresent(testProjectTitle);
            }
        }
        else {rootLogger.info("Before was skipped");}
    }
    @Test
    public void project_delete_as_owner(){
        nextIsImapTest = false;
        scrollUp();
        clickSelector(PROJECT_BTN_DELETE);
        submitConfirmAction();
        sleep(4000);
        String url = url();
        Assert.assertEquals(URL_Dashboard, url);
        rootLogger.info("Project deleted by Owner");
        rootLogger.info("Check if user opens project link");
        openUrlWithBaseAuth(projectUrl);
        sleep(3000);
        $(byXpath("//*[@class='alert alert-danger not-found-message']"))
                .shouldHave(text("This project was deleted by its owner. "));

        String notFoundUrl = url();
        Assert.assertEquals(URL_NotFound, notFoundUrl);
    }

    @Test
    public void tabInfo_C_AddNumber() {
        nextIsImapTest = false;
        String numberType = "Equinox code";
        String numberValue = "2000/17/55-asd";
        numberCreate(numberType, numberValue);
        numberValidateFirstRow(numberType, numberValue);

        String newNumberType = "Reference Number";
        String newNumberValue = "8888-1111-lkjh";
        numberEditInFirstRow(newNumberType, newNumberValue);
        numberValidateInlineForm(newNumberType, newNumberValue);

        numberDelete(null);
        numberValidateFirstRow(null, null);
    }
    @Test
    public void tabInfo_D1_ClassificationValidation() {
        nextIsImapTest = false;
        classificationCreate("Up-to-date", null, null);
        checkText("This field is required.");
        submitEnabledButton(MW_BTN_CANCEL);

        classificationCreate(null, null, LOREM_IPSUM_SHORT);
        checkText("This field is required.");
        submitEnabledButton(MW_BTN_CANCEL);

        classificationCreate(null, "ABCDEFG", null);
        checkText("A valid integer is required.");
        submitEnabledButton(MW_BTN_CANCEL);

        classificationCreate(null, "46", null);
        checkText("Ensure this value is less than or equal to 45.");
        submitEnabledButton(MW_BTN_CANCEL);

        classificationCreate(null, "0", null);
        checkText("Ensure this value is greater than or equal to 1.");
        submitEnabledButton(MW_BTN_CANCEL);
    }
    @Test
    public void tabInfo_D2_ClassificationCrud() {
        nextIsImapTest = false;
        String classNumber = "12";
        String classDescription = "old description";
        classificationCreate(null, classNumber, classDescription);
        classificationValidateClasses("Up-to-date", classDescription);

        String classNewNumber = "23";
        String classNewDescripton = "new description";
        classificationEditFirstRow("Official Data", classNewNumber, classNewDescripton);
        classificationValidateClasses("Official Data", classNewDescripton);

        classificationDelete();
        classificationValidateClasses(null, null);

    }
    @Test
    public void tabContacts_E_addCollaborator() {
        nextIsImapTest = false;
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
    @Test
    public void tabContacts_E_inviteCollaborator_Action() {
        nextIsImapTest = true;
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
    }
    @Test
    public void tabContacts_E_inviteCollaborator_ValidationEmail() {
        nextIsImapTest = false;
        rootLogger.info("Check report email");
        String login = User5.GMAIL_EMAIL.getValue();
        String password = User5.GMAIL_PASSWORD.getValue();
        String inviterNameSurname = TEST_USER_NAME_SURNAME;
        String projectName = testProjectTitle;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInProject(login, password, inviterNameSurname, projectName);
        Assert.assertTrue(validationResult);
        Assert.assertNotNull(projectBackLink);
        rootLogger.info("Link invite to project is: "+projectBackLink);
        rootLogger.info("Test passed");
    }

    @Test
    public void tabDoc_A1_addWordDocument() {
        nextIsImapTest = false;
        String newDoc = "new word document";
        createFileInRoot(MW_DEPLOY_DOC_01TemplateWord, newDoc);
        rootLogger.info("edit file");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_RENAME, newDoc);
        fillField(TAB_DOCS_FILE_INPUT_NAME_IN_ROW, "New Excel sheet");
        TAB_DOCS_FILE_SAVE_IN_ROW.click();
        $(byText("New Excel sheet")).shouldBe(Condition.visible);
        deleteAllFiles();
        rootLogger.info("Test passed");
    }
    @Test
    public void tabDoc_A2_addExcelDocument() {
        nextIsImapTest = false;
        String newExcel = "new excel spreadsheet";
        PROJECT_TAB_DOCS.click();
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        submitModalDeployFileTemplate(MW_DEPLOY_DOC_02TemplateExcel, newExcel);

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
    public void tabDoc_B1_addFolder() {
        String newFolder = "new folder";
        createFolderInRoot(newFolder);

        rootLogger.info("delete folder");
        projectAllCheckbox.click();
        $(byLinkText("Delete")).click();
        submitConfirmAction();
        $$(byText(PLACEHOLDER_NoFiles)).shouldHaveSize(1);
        rootLogger.info("Test passed");
    }
    @Test
    public void tabDoc_B2_validationDuplicateFolder() {
        String newFolder1 = "folder1";
        String newFolder2 = "folder2";
        createFolderInRoot(newFolder1);

        rootLogger.info("Add same folder");
        callNewFolderModal();
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
    public void tabDoc_B3_addSubFoldersTree() {
        String newFolder1 = "folder1";
        String newFolder2 = "folder2";
        String newFolder3 = "folder3";
        String newExcel = "excel";
        PROJECT_TAB_DOCS.click();
        sleep(1000);
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        rootLogger.info("Add folder");
        submitModalCreateFolder(newFolder1);
        clickFolderRow(newFolder1);
        rootLogger.info("Add sub-folder");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_ADD_SUBFOLDER, newFolder1);
        submitModalCreateFolder(newFolder2);
        clickFolderRow(newFolder2);
        rootLogger.info("Add sub-sub-folder");
        fileMenuMakeAction(TAB_DOCS_FILES_MENU_ADD_SUBFOLDER, newFolder2);
        submitModalCreateFolder(newFolder3);
        clickFolderRow(newFolder3);
       // rootLogger.info("Add doc sub-sub-folder");

        rootLogger.info("Delete file via inline control");
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        submitModalDeployFileTemplate(MW_DEPLOY_DOC_02TemplateExcel, newExcel);
        clickFileRow(newExcel);
        TAB_DOCS_FILE_DELETE.shouldBe(visible).click();
        submitConfirmAction();
        rootLogger.info("Test passed");
        $$(byText(newExcel)).shouldHaveSize(0);

    }
    @Test
    public void tabDoc_С1_uploadFileInDifferentZones() {
        String fileName = uploadFileInRoot(UploadFiles.PDF, true, true);
        Assert.assertNotNull(fileName);

        fileName = uploadFileInRoot(UploadFiles.GOOGLE, false, true);
        Assert.assertNotNull(fileName);
        rootLogger.info("Test passed");
    }
    @Test
    public void event_CRUD_autoDeployedEvent() {
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
    public void eventPlusBtn_deployNewEvent() {
        scrollUp();
        rootLogger.info("Deploy new event");
        BTN_HIDE_TIMELINE.shouldBe(visible);
        clickPlusButtonNewEvent();
        deployEvent(APPLICATION_REGISTERED.getValue());
        checkDeployedEvent(APPLICATION_REGISTERED.getValue(), LOREM_IPSUM_SHORT);

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
    public void eventCheck_lessImportantEvent() {
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
    public void tabInfo_N_selectValues() {
        scrollUp();
        TAB_INFO_PROJECT_TYPE.shouldHave(text(TEST_CASE_TYPE));
        sleep(1000);
        setProjectDefining(NETHERLANDS_ANTILES.getValue());
        sleep(1000);
        setProjectType(TrademarkTypes.BASIC.getValue());
        sleep(2000);
        setProjectSubType("Certification Mark");
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
        checkText(USA.getValue());
        checkText("FAMILY-"+testProjectTitle);

        PROJECT_TAB_FAMILY.click();
        String familyText = testProjectTitle.toUpperCase();
        TAB_FAMILY_1ST_ROW_TITLE.should(matchText(familyText));
       // checkText(result);
        rootLogger.info("Test passed");
    }
    @Test
    public void tabInfo_S_cloneProject() {
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







    @Test
    public void tabInfo_checkRedirectToCommunityWizard() {
        if (testProjectTitle ==null || projectUrl ==null){
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
    public void tabInfo_createDraftCommunityCaseFormPekama() {
        if (testProjectTitle ==null || projectUrl ==null){
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
        rootLogger.info("Create Draft case");
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
                ("Opened url not same to the project url", projectUrl, actualUrl);
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
        switchToCommunityWindow();
        BTN_SEND_INSTRUCTION.waitUntil(visible, 20000);
        sleep(1000);
        rootLogger.info("Test passed");
    }
    @Test
    public void tabInfo_createCaseAndCheckPekamaState() {
        if (testProjectTitle ==null || projectUrl ==null){
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
        checkText(ADMIN);
        checkText(EXPERT_FULL_TEAM_NAME);
        checkTextNotPresent(INTRODUCER_NAME); //Removed from case - Admin function
        rootLogger.info("Test passed");
    }
}