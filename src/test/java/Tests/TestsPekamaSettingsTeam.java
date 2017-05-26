package Tests;
import Steps.MessagesIMAP;
import Steps.ObjectUser;
import Utils.Retry;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.ModalWindows.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.MessagesValidator.ValidationInviteInTeamUnregistered.teamBackLink;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaSettingsTeam {
    static final Logger rootLogger = LogManager.getRootLogger();
    private final String TEST_USER_LOGIN = User1.GMAIL_EMAIL.getValue();
    private final String TEST_USER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private final String TEST_USER_NAME_SURNAME = User1.NAME_SURNAME.getValue();
    private final String TEST_USER_TEAM_NAME = User1.TEAM_NAME.getValue();
    private final String TEST_USER_FULL_TEAM_NAME = User1.FULL_TEAM_NAME.getValue();
    private final String TEST_USER_NAME = User1.NAME.getValue();
    private static boolean skipBefore = false;

    @Rule public Timeout tests = Timeout.seconds(300);
    @Rule public Retry retry = new Retry(2);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        if (skipBefore==false){
            clearBrowserCache();
            rootLogger.info("Open host");
            ObjectUser user = ObjectUser.newBuilder().build();
            user.loginByURL(
                    TEST_USER_LOGIN,
                    TEST_USER_PASSWORD,
                    URL_TeamSettings);
        }
        else {rootLogger.info("Before was skipped");}
    }
//    @After
//    public void after() {
//        if (skipBefore==false) {
//            openUrlWithBaseAuth(URL_Logout);
//        }
//        else {rootLogger.info("After was skipped");}
//    }

    @Test
    public void profile_testA_GUI() {
        rootLogger.info("Start test GUI and links");
        checkText("Team details");
        SETTINGS_TEAM_TAB_PROFILE.waitUntil(visible, 15000).shouldHave(Condition.text("Profile"));
        SETTINGS_TEAM_TAB_MEMBERS.waitUntil(visible, 15000).shouldHave(Condition.text("Members"));
        SETTINGS_TEAM_TAB_VALUES.waitUntil(visible, 15000).shouldHave(Condition.text("Values"));
        SETTINGS_TEAM_TAB_TASK_TEMPLATES.waitUntil(visible, 15000).shouldHave(Condition.text("Task Templates"));
        SETTINGS_TEAM_TAB_MESSAGE_TEMPLATES.waitUntil(visible, 15000).shouldHave(Condition.text("Message Templates"));
        SETTINGS_TEAM_TAB_EVENT_TEMPLATES.waitUntil(visible, 15000).shouldHave(Condition.text("Event Templates"));
        SETTINGS_TEAM_TAB_DOCUMENT_TEMPLATES.waitUntil(visible, 15000).shouldHave(Condition.text("Document Templates"));
        SETTINGS_TEAM_TAB_STORAGE.waitUntil(visible, 15000).shouldHave(Condition.text("Storage"));

        SETTINGS_TEAM_INFO_TEAM_NAME.shouldHave(text(TEST_USER_TEAM_NAME));
        SETTINGS_TEAM_INFO_ACTUAL_TEAM.shouldHave(text(TEST_USER_FULL_TEAM_NAME));
        SETTINGS_TEAM_INFO_REGISTRATION_DATE.getText();
        rootLogger.info("Texts and tabs present");

    }
    @Test
    public void logoUpload_jpeg() throws IOException {
        try {
            SETTINGS_TEAM_INFO_DELETE_LOGO.waitUntil(visible, 15000).shouldBe(disabled);
            SETTINGS_TEAM_INFO_UPLOAD_LOGO.shouldBe(visible).click();
            executeAutoItScript(UploadFiles.JPG);
        }
        finally {
            SETTINGS_TEAM_INFO_DELETE_LOGO.waitUntil(visible, 15000).shouldBe(enabled).click();
            sleep(4000);
            SETTINGS_TEAM_INFO_DELETE_LOGO.waitUntil(visible, 15000).shouldBe(disabled);
            SETTINGS_TEAM_INFO_PLACEHOLDER.waitUntil(visible, 10000);
            rootLogger.info("Test passed");
        }
    }
    @Test
    public void logoUpload_pdf_Validation() throws IOException {
        SETTINGS_TEAM_INFO_UPLOAD_LOGO.shouldBe(visible).click();
        executeAutoItScript(UploadFiles.PDF);
        checkText("Upload a valid image. The file you uploaded was either not an image or a corrupted image.");
        rootLogger.info("Test passed - error present");
    }
    @Test
    public void members_testA_AddAndDelete() {
        String testEmail = "123@mail.com";
        try {
            rootLogger.info("Add member");
            SETTINGS_TEAM_TAB_MEMBERS.waitUntil(visible, 20000).click();
            addMember(testEmail, TAB_MEMBERS_BTN_ADD);
            checkMember(testEmail);
        }
        finally {
            rootLogger.info("Delete member");
            deleteMember(testEmail);
        }


        rootLogger.info("Test passed");
    }

    @Test
    public void members_testB_addSameMember() {
        rootLogger.info("Add member");
        SETTINGS_TEAM_TAB_MEMBERS.waitUntil(visible, 20000).click();
        deleteLoopIconX();
        TAB_MEMBERS_BTN_ADD.shouldBe(visible).click();
        String newMemberEmail = submitAddMemberWindow();
        checkMemberInactive(newMemberEmail);

        TAB_MEMBERS_BTN_ADD.shouldBe(visible).click();
        submitAddMemberWindow(newMemberEmail, false);
        MW.shouldHave(text("Already a member"));
        MW_BTN_CANCEL.click();
        checkMemberInactive(newMemberEmail);

        rootLogger.info("Delete member");
        deleteMemberInactive(newMemberEmail);
        rootLogger.info("Test passed");
    }
    @Test 
    public void testC_inviteNewMember_A_Invite() {
        String newMemberEmail = User5.GMAIL_EMAIL.getValue();
        String newMemberPassword = User5.GMAIL_PASSWORD.getValue();
        rootLogger.info("Add member");
        SETTINGS_TEAM_TAB_MEMBERS.waitUntil(visible, 20000).click();
        addMember(newMemberEmail, TAB_MEMBERS_BTN_ADD);
        checkMember(newMemberEmail);

        rootLogger.info("Delete member");
        deleteMember(newMemberEmail);
        skipBefore = true;
    }
    @Test 
    public void testC_inviteNewMember_B_CheckEmail() {
        rootLogger.info("Check invite email");
        String login = User5.GMAIL_EMAIL.getValue();
        String password = User5.GMAIL_PASSWORD.getValue();
        String inviterNameSurname = TEST_USER_NAME_SURNAME;
        String inviterFullTeamName = TEST_USER_FULL_TEAM_NAME;
        String inviterName = TEST_USER_NAME;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInTeamUnregistered(
                login, password,
                inviterNameSurname, inviterName, inviterFullTeamName);
        Assert.assertTrue(validationResult);
        Assert.assertNotNull(teamBackLink);
        rootLogger.info("Link invite to Team is: "+teamBackLink);
        rootLogger.info("Test passed");
        skipBefore = false;
    }

    @Test
    public void values_testA_GUI() {
         rootLogger.info("Start test GUI and links");
         SETTINGS_TEAM_TAB_VALUES.waitUntil(visible, 20000).click();
         SETTINGS_VALUES_TAB_TRADEMARK.shouldHave(text("Trademark")).shouldBe(visible);
         sleep(2000);
         $(byXpath("//*[@href='/a/settings/team/values/patents']")).shouldHave(text("Patent")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/litigations']")).shouldHave(text("Litigation")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/oppositions']")).shouldHave(text("Opposition")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/copyrights']")).shouldHave(text("Copyright")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/teamwork']")).shouldHave(text("TeamWork Channel")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/Domains']")).shouldHave(text("Domain Name")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/Corporate']")).shouldHave(text("Corporate")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/CNF']")).shouldHave(text("Conference")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/Designs']")).shouldHave(text("Design")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/Immigration']")).shouldHave(text("Immigration")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/general']")).shouldHave(text("General")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/disputes']")).shouldHave(text("Dispute")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/companies']")).shouldHave(text("Company")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/CRM']")).shouldHave(text("Client Relation (CRM)")).shouldBe(visible);
         SETTINGS_VALUES_TAB_TASKS.shouldHave(text("Tasks")).shouldBe(visible);
         SETTINGS_VALUES_TAB_CHARGES.shouldHave(text("Charges")).shouldBe(visible);

        rootLogger.info("Defauls state passed");
    }

    @Test
    public void tabProfile_testB_Validation() {
        String longString = randomString(256);
        rootLogger.info("Check max length validation");
        TAB_PROFILE_BTN_SAVE.waitUntil(visible, 20000).shouldBe(disabled);
        $(byText("Title:")).shouldBe(visible);
        fillField(TAB_PROFILE_TITLE, longString);
        $(byText("Code:")).shouldBe(visible);
        fillField(TAB_PROFILE_CODE, longString);
        $(byText("Business type:")).shouldBe(visible);
        $(byText("Your role:")).shouldBe(visible);
        $(byText("Email:")).shouldBe(visible);
        fillField(TAB_PROFILE_EMAIL, longString);
        $(byText("@organizations.pekama.com")).shouldBe(visible);
        $(byText("Additional Info")).shouldBe(visible);
        $(byText("Street address:")).shouldBe(visible);
        fillField(TAB_PROFILE_STREET, longString);
        $(byText("Post code:")).shouldBe(visible);
        fillField(TAB_PROFILE_ZIP, longString);
        $(byText("City:")).shouldBe(visible);
        fillField(TAB_PROFILE_CITY, longString);
        $(byText("State/Region")).shouldBe(visible);
        fillField(TAB_PROFILE_REGION, longString);
        $(byText("Country:")).shouldBe(visible);
        TAB_PROFILE_BTN_SAVE.shouldBe(enabled).click();
        checkText(ERROR_MSG_VALIDATION_LENGTH_255, 4);
        checkText(ERROR_MSG_VALIDATION_LENGTH_4);
        checkText(ERROR_MSG_VALIDATION_LENGTH_64);
        checkText(ERROR_MSG_VALIDATION_LENGTH_20);
        rootLogger.info("Test passed");
    }
    @Test
    public void tabProfile_testC_ValidationEmpty() {
        String teamName = "";
        String code = "";
        rootLogger.info("Check max length validation");
        TAB_PROFILE_BTN_SAVE.waitUntil(visible, 20000).shouldBe(disabled);
        $(byText("Title:")).shouldBe(visible);
        fillField(TAB_PROFILE_TITLE, teamName);
        $(byText("Code:")).shouldBe(visible);
        fillField(TAB_PROFILE_CODE, code);
        TAB_PROFILE_BTN_SAVE.shouldBe(enabled).click();
        checkText(ERROR_MSG_BLANK_FIELD, 2);
    }
}