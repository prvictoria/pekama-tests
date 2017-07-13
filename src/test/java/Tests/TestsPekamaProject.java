package Tests;
import Pages.PekamaProject;
import Steps.*;
import Steps.Objects.Emails.ImapService;
import Steps.Objects.Emails.ValidatorEmailInviteInProject;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;
import javax.mail.MessagingException;
import java.io.IOException;

import static Pages.ModalWindows.*;
import static Pages.PekamaDashboard.*;
import static Pages.PekamaProject.*;
import static Pages.DataCredentials.*;
import static Pages.DataCredentials.Countries.*;
import static Pages.DataCredentials.TrademarkEvents.*;
import static Pages.DataStrings.*;
import static Pages.UrlConfiguration.*;
import static Pages.UrlConfiguration.setEnvironment;
import static Pages.UrlStrings.*;
import static Steps.ObjectFile.*;
import static Steps.ObjectUser.Users.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.Steps.clickSelector;
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
    private static String projectUrl;
    private static String TEST_CASE_TYPE = null;
    private final static String TEST_CASE_NAME = "CUSTOM_NAME"+randomString(10);
    private final static String INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
    private static final ObjectUser owner = new ObjectUser(newBuilder()).buildUser(USER_03);
    private static final ObjectUser collaborator = new ObjectUser(newBuilder()).buildUser(USER_05);
    private static final ObjectUser invited = new ObjectUser(newBuilder()).buildUser(USER_05);
    private static final ObjectUser expert = new ObjectUser(newBuilder()).buildUser(USER_02);
    private static ObjectProject project1 = ObjectProject.newBuilder()
                                                        .projectName("INNER_VALIDATION")
                                                        .projectMatterType(TEST_CASE_TYPE)
                                                        .projectDefining(PITCAIRN_ISLANDS.getValue())
                                                        .build();

    @Rule
    public Timeout tests = Timeout.seconds(600);
    private static boolean skipBefore = false;
    private static boolean nextIsImapTest = false;
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException, InterruptedException {
        setEnvironment ();
        setBrowser();
        if(nextIsImapTest==false) {
            holdBrowserAfterTest();
            TEST_CASE_TYPE = MATTER_TYPE_TRADEMARK;
            new ImapService()
                    .setProperties()
                    .connectStore(collaborator)
                    .openFolder()
                    .markEmailsForDeletion()
                    .clearFolder()
                    .closeStore();
        }
        else {rootLogger.info("Before suite was skipped");}
    }
    @Before
    public void before() {
        if(nextIsImapTest==false) {
            owner.login();
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
        checkText("No community cases.");
        scrollUp();
        clickSelector(PROJECT_BTN_DELETE);
        submitConfirmAction();
        sleep(4000);
        String url = url();
        Assert.assertEquals(URL_DASHBOARD, url);
        rootLogger.info("Project deleted by Owner");
        rootLogger.info("Check if user opens project link");
        openUrlWithBaseAuth(projectUrl);
        sleep(3000);
        $(byXpath("//*[@class='alert alert-danger not-found-message']"))
                .shouldHave(text("This project was deleted by its owner. "));

        String notFoundUrl = url();
        Assert.assertEquals(URL_404, notFoundUrl);
    }


    @Test
    public void tabContacts_E_addCollaborator() {
        nextIsImapTest = false;
        rootLogger.info("Add Pekama member - by default - as Collaborator");
        PROJECT_TAB_CONTACTS.click();
        projectTabContacts_AddCollaborator.click();
        waitForModalWindow(TITLE_MW_SHARE_PROJECT);
        selectTeam(collaborator.teamName);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(PekamaProject.OWNER)).shouldHaveSize(1);
        $$(byText(PekamaProject.COLLABORATOR)).shouldHaveSize(1);

        rootLogger.info("Edit role to - "+ROLE_VIEWER);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow(TITLE_MW_CHANGE_COLLABORATOR);
        MW_BTN_OK.shouldBe(disabled);
        selectOption(MW_SHARE_PROJECT_SELECT_ROLE, ROLE_VIEWER);
      //  MW_SHARE_PROJECT_SELECT_ROLE.selectOption(new String[]{ROLE_VIEWER});
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(PekamaProject.VIEWER)).shouldHaveSize(1);

        rootLogger.info("Edit role to - "+ROLE_ADMIN);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow(TITLE_MW_CHANGE_COLLABORATOR);
        MW_BTN_OK.shouldBe(disabled);
        selectOption(MW_SHARE_PROJECT_SELECT_ROLE, ROLE_ADMIN);
     //   MW_SHARE_PROJECT_SELECT_ROLE.selectOption(new String[]{ROLE_ADMIN});
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(PekamaProject.ADMIN)).shouldHaveSize(1);

        rootLogger.info("Edit role to - "+ROLE_COLLABORATOR);
        projectTabContacts_TeamEdit.click();
        waitForModalWindow(TITLE_MW_CHANGE_COLLABORATOR);
        MW_BTN_OK.shouldBe(disabled);
        selectOption(MW_SHARE_PROJECT_SELECT_ROLE, ROLE_COLLABORATOR);
      //  MW_SHARE_PROJECT_SELECT_ROLE.selectOption(new String[]{ROLE_COLLABORATOR});
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(PekamaProject.COLLABORATOR)).shouldHaveSize(1);

        rootLogger.info("Delete collaborator");
        projectTabContacts_TeamDelete.click();
        submitConfirmAction();
        $$(byText(PekamaProject.OWNER)).shouldHaveSize(1);
        $$(byText(PekamaProject.ADMIN)).shouldHaveSize(0);
        $$(byText(PekamaProject.COLLABORATOR)).shouldHaveSize(0);
        $$(byText(PekamaProject.VIEWER)).shouldHaveSize(0);
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
        $$(byText(PekamaProject.OWNER)).shouldHaveSize(1);
        $$(byText(PekamaProject.COLLABORATOR)).shouldHaveSize(1);
    }
    @Test
    public void tabContacts_E_inviteCollaborator_ValidationEmail() throws InterruptedException, MessagingException, IOException {
        nextIsImapTest = false;
        rootLogger.info("Check report email");

        ObjectProject project = ObjectProject.newBuilder().projectName(testProjectTitle).build();
        new ValidatorEmailInviteInProject()
                .buildReferenceEmail(invited, owner, project)
                .getEmailFormInbox()
                .buildValidator()
                .checkEmailBody()
                .assertValidationResult()
                .getInviteLink();
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
        ObjectFile filePdf =  new ObjectFile(ObjectFile.newBuilder()).buildFile(FileTypes.PDF);
        ObjectFile fileGoogleDoc = new ObjectFile(ObjectFile.newBuilder()).buildFile(FileTypes.GOOGLE);
        String fileName = uploadFileInRoot(filePdf, true, true);
        Assert.assertNotNull(fileName);

        fileName = uploadFileInRoot(fileGoogleDoc, false, true);
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
}