package Tests;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.MessagesIMAP;
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;
import java.io.IOException;

import static Page.ModalWindows.*;
import static Page.PekamaConversationProject.*;
import static Page.PekamaDashboard.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.MessagesValidator.ValidationInviteInProject.*;
import static Steps.MessagesValidator.ValidationInviteInTeamUnregistered.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsModalWindows.ModalConversationFollowerActions.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsMessages {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String TEST_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    private static final String TEST_USER_PEKAMA_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private static final String INVITER_NAME_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private static final String INVITER_TEAM_NAME = User3.TEAM_NAME.getValue();
    private static final String INVITER_NAME_SURNAME = User3.NAME_SURNAME.getValue();
    private static final String INVITER_NAME = User3.NAME.getValue();

    private static final String INVITED_EMAIL = User5.GMAIL_EMAIL.getValue();
    private static final String INVITED_EMAIL_PASSWORD = User5.GMAIL_PASSWORD.getValue();

    private static final String COLLABORATOR_NAME_SURNAME = User1.NAME_SURNAME.getValue();
    private static final String COLLABORATOR_EMAIL = User1.GMAIL_EMAIL.getValue();
    private static final String COLLABORATOR_EMAIL_PASSWORD = User1.GMAIL_PASSWORD.getValue();

    private static String testProjectName = null;
    private static String testProjectUrl = null;
    private static boolean skipBefore = false;
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
        //skipBefore = true;
        if (skipBefore==false) {
            clearBrowserCache();
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    TEST_USER_EMAIL,
                    TEST_USER_PEKAMA_PASSWORD,
                    URL_LogIn);
            rootLogger.info("Create project");
            DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 15000).click();
            testProjectName = submitMwNewProject();
            testProjectUrl = getActualUrl();
        }
        else {rootLogger.info("Before was skipped");}
    }

    @Test
    public void testA_DefaultState() {
        String subject = randomString(513);
        String randomFollower = randomString(25)+"@email.random";
        String randomGuest = randomString(25)+"@email.guest";

        scrollUp();
        CONVERSATION_BTN_New.waitUntil(visible, 20000);
        checkText("Conversations");
        rootLogger.info("Check Team chat");
        CONVERSATION_BTN_Team.waitUntil(visible, 20000);
        checkText(CONVERSATION_TEAM_TAB_NAME);
        checkText(CONVERSATION_PLACEHOLDER_IN_TEAM_TAB);

        rootLogger.info("Check External chat");
        CONVERSATION_BTN_Client.waitUntil(visible, 20000).click();
        checkText(CONVERSATION_CLIENT_TAB_NAME);
        checkText(CONVERSATION_PLACEHOLDER_IN_CLIENT_TAB);

        CONVERSATION_BTN_Team.click();
        sleep(500);

        rootLogger.info("Check New Conversation validation");
        CONVERSATION_BTN_New.click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        MW_CONVERSATION_INPUT_Subject.shouldBe(empty);
        MW_CONVERSATION_INPUT_Follower.shouldBe(empty);
        checkText(INVITER_TEAM_NAME);

        MW_CHECKBOX_ALL_TEAMS.shouldNotBe(checked);
        fillField(MW_CONVERSATION_INPUT_Subject, subject);
        submitEnabledButton(MW_BTN_CREATE);
        checkText(ERROR_MSG_VALIDATION_LENGTH_512);
        MW.pressEscape();
        MW.shouldNotBe(visible);

        rootLogger.info("Check New Conversation invite guest");
        CONVERSATION_BTN_New.click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        fillField(MW_CONVERSATION_INPUT_Follower, randomGuest);
        sleep(250);

        MW_CONVERSATION_BTN_INVITE.shouldBe(visible);
        MW_CONVERSATION_BTN_ADD_GUEST.shouldBe(visible).click();
        checkText(randomGuest);
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Check New Conversation invite follower");
        CONVERSATION_BTN_New.click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        fillField(MW_CONVERSATION_INPUT_Follower, randomFollower);
        sleep(250);

        MW_CONVERSATION_BTN_ADD_GUEST.shouldBe(visible);
        MW_CONVERSATION_BTN_INVITE.shouldBe(visible).click();
        checkText(randomFollower);
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Test passed");
    }
    @Test
    public void sendMessageInTeamChat() {
        String oldThreadTitle = "TEAM_THREAD IN PRIVATE ZONE";
        String newThreadName = "TEAM"+randomString(15);
        rootLogger.info("Create thread in private zone");
        callModalNewConversation();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        fillField(MW_CONVERSATION_INPUT_Subject, oldThreadTitle);
        MW_BTN_CREATE.click();
        MW.shouldNotBe(visible);
        sleep(2000);

        editTreadTitle(oldThreadTitle, newThreadName);

        CONVERSATION_LABEL_ACTIVE_TAB.shouldHave(text(CONVERSATION_TEAM_TAB_NAME));
        CONVERSATION_INPUT_TEXT_COLLAPSED.shouldBe(visible).click();
        sleep(4000);
        CONVERSATION_TEXT_EDITOR.shouldHave(value(""));
        CONVERSATION_TEXT_EDITOR.val(LOREM_IPSUM_SHORT);
        CONVERSATION_TEXT_EDITOR.val(LOREM_IPSUM_SHORT);
        sleep(2000);
        CONVERSATION_TEXT_EDITOR.shouldHave(text(LOREM_IPSUM_SHORT));
        submitEnabledButton(CONVERSATION_BTN_POST);
        MESSAGES_LIST.filter(visible).shouldHaveSize(1);
        MESSAGE_FIRST_TEXT.shouldHave(text(LOREM_IPSUM_SHORT));

        rootLogger.info("Delete message");
        CONVERSATION_MsgDelete.waitUntil(visible, 10000);
        CONVERSATION_MsgDelete.click();
        submitConfirmAction(TITLE_MW_DELETE_MESSAGE);
        $(byXpath("//*[@class='message-list']/li[1]//div[@class='message-holder']")).shouldNot(visible);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_C1_ExternalConversationDefaults() {
        createExternalConversation();
        editTreadTitle(null);
        validateFollowerExternal(INVITER_NAME_SURNAME);
        deleteFollower(INVITER_NAME_SURNAME);
        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_C1_ExternalConversationValidationEmptyRecipients() {
        rootLogger.info("Check no recipient validation");
        createExternalConversation();
        sendExternalMsg(
                null,
                null,
                null,
                null,
                LOREM_IPSUM_SHORT
        );
        checkText("External conversation message should have recipients");
    }
    @Test
    public void createProject_C1_ExternalConversationValidationEmptyTo() {
        String emailFollowerCc = randomString(15)+"@post.de";
        String emailFollowerBcc = randomString(15)+"@liamg.usa";
        String emailSubject = "externalEmail"+randomString(10);
        rootLogger.info("Check no recipient validation");
        createExternalConversation();
        sendExternalMsg(
                null,
                emailFollowerCc,
                emailFollowerBcc,
                emailSubject,
                LOREM_IPSUM_SHORT
        );
        checkText("recipients: External message should have at least one `TO` recipient");
    }
    @Test
    public void createProject_C1_ExternalConversationCreate() {
        String emailFollowerTo = randomString(15)+"@mail.com";
        String emailFollowerCc = randomString(15)+"@post.de";
        String emailFollowerBcc = randomString(15)+"@liamg.usa";
        String emailSubject = "externalEmail"+randomString(20);
        String emailText = LOREM_IPSUM_SHORT;

        createExternalConversation();
        sendExternalMsg(
                emailFollowerTo,
                emailFollowerCc,
                emailFollowerBcc,
                emailSubject,
                emailText
        );
        validateExternalMsg(
                emailFollowerTo,
                emailFollowerCc,
                emailFollowerBcc
        );
        deleteMsg();
        rootLogger.info("Test passed");
    }
    @Test
    public void inviteInTeamChatCollaborator(){
        skipBefore = false;
        rootLogger.info("Create thread in private zone");
        callModalNewConversation();
        String newFollower = COLLABORATOR_EMAIL;
        submitNewConversationWindow(
                ADD_FOLLOWER,
                null,
                newFollower,
                null,
                null,
                false,
                true
        );
        validateFollowerTeamChat(COLLABORATOR_NAME_SURNAME, 2, 1);
    }
    //TODO Why ?
    @Test
    public void inviteInTeamChatPekamaMemberAsGuest_Dismiss(){
        skipBefore = false;
        rootLogger.info("Create thread in private zone");
        callModalNewConversation();
        String newFollower = User1.GMAIL_EMAIL.getValue();
        submitNewConversationWindow(
                ADD_GUEST,
                null,
                newFollower,
                null,
                null,
                false,
                true
        );
        validateFollowerTeamChat(newFollower, 2, 1);
        inviteGuestInTeam(false, newFollower);
    }
    @Test
    public void inviteInTeamChatPekamaMemberAsGuestRegisteredUser_Invite(){
        skipBefore = true;
        try {
            rootLogger.info("Create thread in private zone");
            callModalNewConversation();
            String newFollower = INVITED_EMAIL;
            submitNewConversationWindow(
                    ADD_GUEST,
                    null,
                    newFollower,
                    null,
                    null,
                    false,
                    true
            );
            validateFollowerTeamChat(newFollower, 2, 1);
            inviteGuestInTeam(true, newFollower);
        }
        finally {
            deleteAllMembers();
        }
        return;
    }
    @Test
    public void inviteInTeamChatPekamaMemberAsGuestRegisteredUser_ValidationEmail(){
        skipBefore = false;
        String login = INVITED_EMAIL;
        String password = INVITED_EMAIL_PASSWORD;
        String inviterNameSurname = INVITER_NAME_SURNAME;
        String inviterFullTeamName = INVITER_NAME_FULL_TEAM_NAME;
        String inviterName = INVITER_NAME;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInTeamUnregistered(
                login, password,
                inviterNameSurname, inviterName, inviterFullTeamName);
        Assert.assertTrue(validationResult);
        Assert.assertNotNull(ValidationInviteInTeamUnregistered.teamBackLink);
        rootLogger.info("Link invite to Team is: "+ValidationInviteInTeamUnregistered.teamBackLink);
        return;
    }
    @Test
    public void inviteInTeamChatPekamaMemberAsGuestNewUser_Invite(){
        skipBefore = true;
        try {
            rootLogger.info("Create thread in private zone");
            callModalNewConversation();
            String newFollower = COLLABORATOR_EMAIL;
            submitNewConversationWindow(
                    ADD_GUEST,
                    null,
                    newFollower,
                    null,
                    null,
                    false,
                    true
            );
            validateFollowerTeamChat(newFollower, 2, 1);
            inviteGuestInTeam(true, newFollower);
        }
        finally {
            deleteAllMembers();
        }
        return;
    }
    @Test
    public void inviteInTeamChatPekamaMemberAsGuestNewUser_ValidationEmail(){
        skipBefore = false;
        String login = COLLABORATOR_EMAIL;
        String password = COLLABORATOR_EMAIL_PASSWORD;
        String inviterNameSurname = INVITER_NAME_SURNAME;
        String inviterFullTeamName = INVITER_NAME_FULL_TEAM_NAME;
        String inviterName = INVITER_NAME;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInTeamRegistered(
                login, password,
                inviterNameSurname, inviterName, inviterFullTeamName);
        Assert.assertTrue(validationResult);
        Assert.assertNotNull(ValidationInviteInTeamRegistered.teamBackLink);
        rootLogger.info("Link invite to Team is: "+ValidationInviteInTeamRegistered.teamBackLink);
        return;
    }
    @Test
    public void inviteInTeamChatGuest(){
        skipBefore = false;
        rootLogger.info("Create thread in private zone");
        callModalNewConversation();
        String newFollower = randomString(10)+"@mail.com";
        submitNewConversationWindow(
                ADD_GUEST,
                null,
                newFollower,
                null,
                null,
                false,
                true
        );
        String followerNameSurname = newFollower;
        StepsPekama.validateFollowerTeamChat(followerNameSurname, 2, 1);
    }
    @Test
    public void inviteInTeamChatNewCollaborator_Action(){
        skipBefore = true;
        rootLogger.info("Create thread in private zone");
        callModalNewConversation();
        String newFollower = User5.GMAIL_EMAIL.getValue();
        submitNewConversationWindow(
                INVITE_FOLLOWER,
                null,
                newFollower,
                null,
                null,
                false,
                true
        );
        String followerNameSurname = newFollower+" (inactive)";
        StepsPekama.validateFollowerTeamChat(followerNameSurname, 2, 1);
    }
    @Test
    public void inviteInTeamChatNewCollaborator_ValidationEmail(){
        skipBefore = false;
        rootLogger.info("Check invite email");
        String login = INVITED_EMAIL;
        String password = INVITED_EMAIL_PASSWORD;
        String inviterNameSurname = INVITER_NAME_SURNAME;
        String projectName = testProjectName;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInProject(login, password, inviterNameSurname, projectName);
        Assert.assertTrue(validationResult);
        Assert.assertNotNull(projectBackLink);
        rootLogger.info("Test passed");
        return;
    }
}