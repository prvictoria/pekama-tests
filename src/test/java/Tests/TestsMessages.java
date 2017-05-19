package Tests;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Enumeration;

import static Page.ModalWindows.*;
import static Page.PekamaConversationProject.*;
import static Page.PekamaDashboard.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.MessagesValidator.ValidationEmailMessage.*;
import static Steps.MessagesValidator.ValidationInviteInProject.*;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.*;
import static Steps.StepsModalWindows.ModalConversationFollowerActions.*;
import static Steps.StepsModalWindows.emailPlaceholders.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
import static Tests.BeforeTestsSetUp.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsMessages {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String INVITER_EMAIL = User3.GMAIL_EMAIL.getValue();
    private static final String INVITER_EMAIL_PASSWORD = User3.GMAIL_PASSWORD.getValue();
    private static final String INVITER_PEKAMA_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private static final String INVITER_NAME_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private static final String INVITER_TEAM_NAME = User3.TEAM_NAME.getValue();
    private static final String INVITER_NAME_SURNAME = User3.NAME_SURNAME.getValue();
    private static final String INVITER_NAME = User3.NAME.getValue();

    private static final String INVITED_EMAIL = User5.GMAIL_EMAIL.getValue();
    private static final String INVITED_EMAIL_PASSWORD = User5.GMAIL_PASSWORD.getValue();

    private static final String COLLABORATOR_NAME_SURNAME = User1.NAME_SURNAME.getValue();
    private static final String COLLABORATOR_EMAIL = User1.GMAIL_EMAIL.getValue();
    private static final String COLLABORATOR_EMAIL_PASSWORD = User1.GMAIL_PASSWORD.getValue();
    private static final String COLLABORATOR_PEKAMA_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private static final String GUEST_EMAIL = User5.GMAIL_EMAIL.getValue();
    private static final String GUEST_EMAIL_PASSWORD = User5.GMAIL_PASSWORD.getValue();

    private static String subjectLineExample = null;
    private static String testProjectName = null;
    private static String testProjectUrl = null;
    private static String repryLink = null;
    private static boolean skipBefore = false;
    private static boolean debug = false;
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
//        skipBefore = true;
//        debug =true;
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(debug==false) {
            MessagesIMAP emailTask = new MessagesIMAP();
            emailTask.imapSearchEmailDeleteAll(
                    INVITER_EMAIL,
                    INVITER_EMAIL_PASSWORD);
            emailTask.imapSearchEmailDeleteAll(
                    INVITED_EMAIL,
                    INVITED_EMAIL_PASSWORD);
            emailTask.imapSearchEmailDeleteAll(
                    COLLABORATOR_EMAIL,
                    COLLABORATOR_EMAIL_PASSWORD);
        }
        else {rootLogger.info("Debug mode");}
    }
    @Before
    public void before() {
        if (skipBefore==false) {
            clearBrowserCache();
            User creator = new User();
            creator.loginByURL(
                    INVITER_EMAIL,
                    INVITER_PEKAMA_PASSWORD,
                    URL_PEKAMA_LOGIN);
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
    public void sendMessageInTeamChat_UserZone() {
        String oldThreadTitle = "TEAM THREAD IN PRIVATE ZONE";
        String newThreadName = "TEAM"+randomString(15);
        rootLogger.info("Create thread in private zone");
        callModalNewConversation();
        submitNewConversationWindow(
                null,
                oldThreadTitle,
                null,
                null,
                null,
                false,
                true
        );
        editTreadTitle(oldThreadTitle, newThreadName);
        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);
        deleteLastMessage();
        LAST_MESSAGE.shouldNot(visible);
        rootLogger.info("Test passed");
    }
    @Test
    public void createThreadInTeamChat_AllZone() {
        String threadTitle = "TEAM THREAD IN ALL ZONE";
        rootLogger.info("Create thread in private zone");
        callModalNewConversation();
        submitNewConversationWindow(
                null,
                threadTitle,
                null,
                null,
                null,
                true,
                true
        );
        rootLogger.info("Verify Email parameters modal window - generic");
        callModalEmailParameters();
        validateEmailParametersWindowDefaults();
        validateEmailParametersWindow("#subject# | #title# | #major-numbers#", threadTitle+" | "+testProjectName+" |" );
        // TEAM THREAD IN ALL ZONE | DEFAULT_PROJECT_ZENQQ9CKT0 |
    }
    @Test
    public void checkEmailParametersModal_A1_CustomEmailSubject(){
        skipBefore = true;
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_FOLLOWER,
                null,
                COLLABORATOR_EMAIL,
                null,
                null,
                false,
                true
        );
        callModalEmailParameters();
        writeEmailPlaceholder ("CUSTOM");
        validateEmailParametersWindow("CUSTOM", "CUSTOM");
        submitEmailParametersWindow(true);

        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);
    }
    @Test @Category({AllImapTests.class})
    public void checkEmailParametersModal_A2_CheckEmailSubject() throws IOException, MessagingException {
        skipBefore = false;
        rootLogger.info("Check follower-collaborator email");
        sleep(10000);
        MessagesIMAP emailTask2 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask2.validateEmailMessage(
                        COLLABORATOR_EMAIL,
                        COLLABORATOR_EMAIL_PASSWORD,
                        "CUSTOM",
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        COLLABORATOR_NAME_SURNAME,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );
    }

    @Test
    public void checkEmailParametersModal_B_SubjectNull(){
        callModalNewConversation();
        submitNewConversationWindow(
                null,
                null,
                null,
                null,
                null,
                false,
                true
        );
        callModalEmailParameters();
        writeEmailPlaceholder ("");
        selectEmailPlaceholder(SUBJECT);
        validateEmailParametersWindow("#subject#", null);
        submitEmailParametersWindow(true);
    }
    @Test
    public void checkEmailParametersModal_C1_SubjectPlaceholder(){
        skipBefore = true;
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_FOLLOWER,
                "THREAD SUBJECT",
                COLLABORATOR_EMAIL,
                null,
                null,
                false,
                true
        );
        callModalEmailParameters();
        writeEmailPlaceholder ("");
        selectEmailPlaceholder(SUBJECT);
        validateEmailParametersWindow("#subject#", "THREAD SUBJECT");
        submitEmailParametersWindow(true);

        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);
    }
    @Test @Category({AllImapTests.class})
    public void checkEmailParametersModal_C2_CheckEmailSubject() throws IOException, MessagingException {
        skipBefore = false;
        rootLogger.info("Check follower-collaborator email");
        sleep(10000);
        MessagesIMAP emailTask2 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask2.validateEmailMessage(
                        COLLABORATOR_EMAIL,
                        COLLABORATOR_EMAIL_PASSWORD,
                        "THREAD SUBJECT",
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        COLLABORATOR_NAME_SURNAME,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );
    }
    @Test
    public void checkEmailParametersModal_D1_ProjectTitlePlaceholder(){
        skipBefore = true;
        Assert.assertNotNull(testProjectName);
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_FOLLOWER,
                null,
                COLLABORATOR_EMAIL,
                null,
                null,
                false,
                true
        );
        callModalEmailParameters();
        writeEmailPlaceholder ("");
        selectEmailPlaceholder(TITLE);
        validateEmailParametersWindow("#title#", testProjectName);
        submitEmailParametersWindow(true);

        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);
    }
    @Test @Category({AllImapTests.class})
    public void checkEmailParametersModal_D2_CheckEmailSubject() throws IOException, MessagingException {
        skipBefore = false;
        Assert.assertNotNull(testProjectName);
        rootLogger.info("Check follower-collaborator email");
        sleep(10000);
        MessagesIMAP emailTask2 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask2.validateEmailMessage(
                        COLLABORATOR_EMAIL,
                        COLLABORATOR_EMAIL_PASSWORD,
                        testProjectName,
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        COLLABORATOR_NAME_SURNAME,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );
    }
    @Test
    public void checkEmailParametersModal_E_ProjectTitlePlaceholderDefault(){
        callModalNewConversation();
        submitNewConversationWindow(
                null,
                null,
                null,
                null,
                null,
                false,
                true
        );
        callModalEmailParameters();
        validateEmailParametersWindow("#title#", testProjectName);
        submitEmailParametersWindow(false);
    }
    @Test
    public void checkEmailParametersModal_F1_ProjectNumberPlaceholder(){
        skipBefore = true;
        subjectLineExample =  "Project "+parseProjectNumber();
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_FOLLOWER,
                null,
                COLLABORATOR_EMAIL,
                null,
                null,
                false,
                true
        );
        callModalEmailParameters();
        writeEmailPlaceholder ("");
        selectEmailPlaceholder(PRJ_NUMBER);
        validateEmailParametersWindow("#project-number#", subjectLineExample);
        submitEmailParametersWindow(true);

        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);
    }
    @Test @Category({AllImapTests.class})
    public void checkEmailParametersModal_F2_CheckEmailSubject() throws IOException, MessagingException {
        skipBefore = false;
        Assert.assertNotNull(subjectLineExample);
        rootLogger.info("Check #project-number# subject");
        sleep(10000);
        MessagesIMAP emailTask2 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask2.validateEmailMessage(
                        COLLABORATOR_EMAIL,
                        COLLABORATOR_EMAIL_PASSWORD,
                        subjectLineExample,
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        COLLABORATOR_NAME_SURNAME,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );
    }
    @Test
    public void checkEmailParametersModal_G1_MajorNumberPlaceholder(){
        skipBefore = true;
        numberCreate("Reference Number", "ref/99-88-66-2017");
        subjectLineExample =  "Reference Number"+": "+"ref/99-88-66-2017";
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_FOLLOWER,
                null,
                COLLABORATOR_EMAIL,
                null,
                null,
                false,
                true
        );
        callModalEmailParameters();
        writeEmailPlaceholder ("");
        selectEmailPlaceholder(MAJOR_NUMBERS);
        validateEmailParametersWindow("#major-numbers#", subjectLineExample);
        submitEmailParametersWindow(true);

        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);
    }
    @Test @Category({AllImapTests.class})
    public void checkEmailParametersModal_G2_CheckEmailSubject() throws IOException, MessagingException {
        skipBefore = false;
        Assert.assertNotNull(subjectLineExample);
        rootLogger.info("Check #major-numbers# subject");
        sleep(10000);
        MessagesIMAP emailTask2 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask2.validateEmailMessage(
                        COLLABORATOR_EMAIL,
                        COLLABORATOR_EMAIL_PASSWORD,
                        subjectLineExample,
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        COLLABORATOR_NAME_SURNAME,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );
    }
//EXTERNAL EM TESTS ==========================================================================
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
    @Test @Category({AllImapTests.class})
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
        StepsPekamaProject.validateFollowerTeamChat(followerNameSurname, 2, 1);
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
        StepsPekamaProject.validateFollowerTeamChat(followerNameSurname, 2, 0);
    }
    @Test @Category({AllImapTests.class})
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

    @Test 
    public void checkThatUserGetCopyOwnMessages_A_PostMessage() throws IOException, MessagingException {
        skipBefore = true;
        rootLogger.info("Set email settings");
        openSettingsTabEmails();
        selectReceiveEmailOptions(
                true,
                false,
                false,
                true,
                true
        );
        openUrlWithBaseAuth(testProjectUrl);

        rootLogger.info("Create thread");
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_FOLLOWER,
                "COPY_OF_MY_OWN_MESSAGE",
                COLLABORATOR_EMAIL,
                null,
                null,
                false,
                true
        );
        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);
    }
    @Test @Category({AllImapTests.class})
    public void checkThatUserGetCopyOwnMessages_B_CheckEmail() throws IOException, MessagingException {
        skipBefore = true;
        rootLogger.info("Check Creator email");
        sleep(10000);
        MessagesIMAP emailTask = new MessagesIMAP();
        Assert.assertTrue(
                emailTask.validateEmailMessage(
                        INVITER_EMAIL,
                        INVITER_EMAIL_PASSWORD,
                        "COPY_OF_MY_OWN_MESSAGE",
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        COLLABORATOR_NAME_SURNAME,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );
        repryLink = ValidationEmailMessage.replyLink;
    }
    @Test @Category({AllImapTests.class})
    public void checkThatUserGetCopyOwnMessages_C_CheckRedirectReplyLinkFollower() throws IOException, MessagingException {
        skipBefore = true;
      //  repryLink = "https://u1528369.ct.sendgrid.net/wf/click?upn=nlPIBkCFx3ihDwn5X-2FQH25GimnAenIWRK2CNbjwb1wz4MhLyrPlDXqARqX-2FoYxFNbrjSdkTycqH9IUseFSWnM-2F3L2QDDFm6XOpyOMUSqms0-3D_FhKIrNJz0J-2FLui-2BhorTXHazj59U-2BmXqSH3Q93OorhOgeYov1Ufk9vFFXG5Ntep8eoNf46zw8iVivjaYI07Za3OlTl3RkPuH16WaCuXZo-2FdTBRfJTZhKbG8zpyau5YKjB3L3x1mhTWFfaA05p1O7I8EaImFM6KER0npwusk-2FxVocP3SA3-2FbPdMBYnC7pACNNzlLXAQPDcxyBkV1Akw6IOB8DFbnm2tqEGbncvHn53U0EBKnYal25sNsT92EF8Dc68PQ2SvDGda-2FvBdR5UBu81pVgjqAVqOHxI26M0AiFGmwqgKkDIiCJWOX5KCYmZfR-2FO";
        Assert.assertNotNull(repryLink);
        User follower = new User();
        follower.loginByURL(
                COLLABORATOR_EMAIL,
                COLLABORATOR_PEKAMA_PASSWORD,
                URL_PEKAMA_LOGIN);
        openUrlWithBaseAuth(repryLink);
        checkTreadTitle("COPY_OF_MY_OWN_MESSAGE");
        rootLogger.info("Test Passed");

    }
    @Test @Category({AllImapTests.class})
    public void checkThatUserGetCopyOwnMessages_D_CheckRedirectReplyLinkCreator() throws IOException, MessagingException {
        skipBefore = false;
        //repryLink = "https://u1528369.ct.sendgrid.net/wf/click?upn=nlPIBkCFx3ihDwn5X-2FQH25GimnAenIWRK2CNbjwb1wz4MhLyrPlDXqARqX-2FoYxFNbrjSdkTycqH9IUseFSWnM-2F3L2QDDFm6XOpyOMUSqms0-3D_FhKIrNJz0J-2FLui-2BhorTXHazj59U-2BmXqSH3Q93OorhOgeYov1Ufk9vFFXG5Ntep8eoNf46zw8iVivjaYI07Za3OlTl3RkPuH16WaCuXZo-2FdTBRfJTZhKbG8zpyau5YKjB3L3x1mhTWFfaA05p1O7I8EaImFM6KER0npwusk-2FxVocP3SA3-2FbPdMBYnC7pACNNzlLXAQPDcxyBkV1Akw6IOB8DFbnm2tqEGbncvHn53U0EBKnYal25sNsT92EF8Dc68PQ2SvDGda-2FvBdR5UBu81pVgjqAVqOHxI26M0AiFGmwqgKkDIiCJWOX5KCYmZfR-2FO";
        Assert.assertNotNull(repryLink);
        User creator = new User();
        creator.loginByURL(
                INVITER_EMAIL,
                INVITER_PEKAMA_PASSWORD,
                URL_PEKAMA_LOGIN);
        openUrlWithBaseAuth(repryLink);
        checkText("You followed a link meant for one of your other accounts. Please sign in with that account to proceed.");
        rootLogger.info("Test Passed");

    }
    @Test 
    public void checkThatGuestFollowerGetEmail_A_PostMessage() throws IOException, MessagingException {
        userNameSurname = INVITER_NAME_SURNAME;
        followerEmailOrTeamNameSurname = GUEST_EMAIL;
        rootLogger.info("Set email settings");
        openSettingsTabEmails();
        selectReceiveEmailOptions(
                true,
                false,
                false,
                true,
                true
        );
        openUrlWithBaseAuth(testProjectUrl);

        rootLogger.info("Create thread");
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_GUEST,
                "EMAIL_TO_GUEST_MESSAGE",
                GUEST_EMAIL,
                null,
                null,
                false,
                true
        );
        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);

        rootLogger.info("Check Creator email");
        sleep(10000);
        MessagesIMAP emailTask1 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask1.validateEmailMessage(
                        INVITER_EMAIL,
                        INVITER_EMAIL_PASSWORD,
                        "EMAIL_TO_GUEST_MESSAGE",
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        GUEST_EMAIL,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );

        rootLogger.info("Check guest email");
        sleep(10000);
        MessagesIMAP emailTask2 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask2.validateEmailMessage(
                        GUEST_EMAIL,
                        GUEST_EMAIL_PASSWORD,
                        "EMAIL_TO_GUEST_MESSAGE",
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        GUEST_EMAIL,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );
    }

    @Test 
    public void checkThatFollowerGetEmail() throws IOException, MessagingException {
        userNameSurname = INVITER_NAME_SURNAME;
        followerEmailOrTeamNameSurname = COLLABORATOR_NAME_SURNAME;

        openUrlWithBaseAuth(URL_PEKAMA_LOGOUT);

        rootLogger.info("Set FOLLOWER email settings");
        User follower = new User();
        follower.loginByURL(
                COLLABORATOR_EMAIL,
                COLLABORATOR_PEKAMA_PASSWORD,
                URL_PEKAMA_LOGIN);
        openSettingsTabEmails();
        selectReceiveEmailOptions(
                true,
                false,
                false,
                true,
                false
        );
        openUrlWithBaseAuth(URL_PEKAMA_LOGOUT);

        rootLogger.info("Create thread");
        User creator = new User();
        creator.loginByURL(
                INVITER_EMAIL,
                INVITER_PEKAMA_PASSWORD,
                URL_PEKAMA_LOGIN);
        openUrlWithBaseAuth(testProjectUrl);
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_FOLLOWER,
                "EMAIL_TO_FOLLOWER_MESSAGE",
                COLLABORATOR_EMAIL,
                null,
                null,
                false,
                true
        );
        expandTextEditorInTeamChat();
        postMessage(LOREM_IPSUM_SHORT);

        rootLogger.info("Check follower-collaborator email");
        sleep(10000);
        MessagesIMAP emailTask2 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask2.validateEmailMessage(
                        COLLABORATOR_EMAIL,
                        COLLABORATOR_EMAIL_PASSWORD,
                        "EMAIL_TO_FOLLOWER_MESSAGE",
                        LOREM_IPSUM_SHORT,
                        INVITER_NAME_SURNAME,
                        COLLABORATOR_NAME_SURNAME,
                        new MessagesValidator.ValidationEmailMessage()
                )
        );
    }
}