package Tests;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.*;
import Steps.Intrefaces.IMessagesValidator;
import Steps.Objects.Emails.ImapService;
import Steps.Objects.Emails.ValidatorEmailFromMessage;
import Steps.Objects.Emails.ValidatorEmailInviteInProject;
import Steps.Objects.Emails.ValidatorEmailSignUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;

import static Pages.ModalWindows.*;
import static Pages.PekamaConversationProject.*;
import static Pages.PekamaDashboard.*;
import static Pages.DataCredentials.*;
import static Pages.DataStrings.*;
import static Pages.UrlConfiguration.*;
import static Pages.UrlStrings.*;
import static Steps.Intrefaces.IMessagesValidator.ValidationEmailMessage.*;
import static Steps.ObjectUser.Users.*;
import static Steps.ObjectUser.newBuilder;
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
    private final static ObjectUser collaborator = new ObjectUser(newBuilder()).buildUser(USER_01);
    private final static ObjectUser guest = new ObjectUser(newBuilder()).buildUser(USER_05);
    private final static ObjectUser invited = guest;
    private static ObjectUser owner = new ObjectUser(newBuilder()).buildUser(USER_03);

    private static String subjectLineExample = null;
    private static String testProjectName = null;
    private static String testProjectUrl = null;
    private static String repryLink = null;
    private static boolean skipBefore = true;
    private static boolean debug = true;
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException, InterruptedException {

        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(debug==false) {
            new ImapService().emailAllEmailsCleaner();
            owner.login();
            deleteAllMembers();
            getWebDriver().quit();
        }
        else {rootLogger.info("Debug mode");}
    }
    @Before
    public void before() {
        if (skipBefore==false) {
            owner.login();
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
        checkText(owner.teamName);

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
                collaborator.email,
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
    public void checkEmailParametersModal_A2_CheckEmailSubject() throws IOException, MessagingException, InterruptedException {
        skipBefore = false;
        rootLogger.info("Check follower-collaborator email");

        new ValidatorEmailFromMessage()
                .buildReferenceEmail("CUSTOM", collaborator, owner)
                .getEmailFormInbox()
                .buildValidator()
                .checkEmailBody()
                .assertValidationResult();


//        MessagesIMAP emailTask2 = new MessagesIMAP();
//        Assert.assertTrue(
//                emailTask2.validateEmailMessage(
//                        collaborator.email,
//                        collaborator.passwordEmail,
//                        "CUSTOM",
//                        LOREM_IPSUM_SHORT,
//                        owner.nameSurname,
//                        collaborator.nameSurname,
//                        new IMessagesValidator.ValidationEmailMessage()
//                )
//        );
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
                collaborator.email,
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
                        collaborator.email,
                        collaborator.passwordEmail,
                        "THREAD SUBJECT",
                        LOREM_IPSUM_SHORT,
                        owner.nameSurname,
                        collaborator.nameSurname,
                        new IMessagesValidator.ValidationEmailMessage()
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
                collaborator.email,
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
                        collaborator.email,
                        collaborator.passwordEmail,
                        testProjectName,
                        LOREM_IPSUM_SHORT,
                        owner.nameSurname,
                        collaborator.nameSurname,
                        new IMessagesValidator.ValidationEmailMessage()
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
                collaborator.email,
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
                        collaborator.email,
                        collaborator.passwordEmail,
                        subjectLineExample,
                        LOREM_IPSUM_SHORT,
                        owner.nameSurname,
                        collaborator.nameSurname,
                        new IMessagesValidator.ValidationEmailMessage()
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
                collaborator.email,
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
                        collaborator.email,
                        collaborator.passwordEmail,
                        subjectLineExample,
                        LOREM_IPSUM_SHORT,
                        owner.nameSurname,
                        collaborator.nameSurname,
                        new IMessagesValidator.ValidationEmailMessage()
                )
        );
    }
//EXTERNAL EM TESTS ==========================================================================
    @Test
    public void createProject_C1_ExternalConversationDefaults() {
        createExternalConversation();
        editTreadTitle(null);
        validateFollowerExternal(owner.nameSurname);
        deleteFollower(owner.nameSurname);
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
        String newFollower = collaborator.email;
        submitNewConversationWindow(
                ADD_FOLLOWER,
                null,
                newFollower,
                null,
                null,
                false,
                true
        );
        validateFollowerTeamChat(collaborator.nameSurname, 2, 1);
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
            String newFollower = collaborator.email;
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
        String login = invited.email;
        String password = invited.passwordEmail;
        String inviterNameSurname = owner.nameSurname;
        String inviterFullTeamName = owner.teamFullName;
        String inviterName = owner.name;
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
            String newFollower = collaborator.email;
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
        String login = collaborator.email;
        String password = collaborator.passwordEmail;
        String inviterNameSurname = owner.nameSurname;
        String inviterFullTeamName = owner.teamFullName;
        String inviterName = owner.name;
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
    public void inviteInTeamChatNewCollaborator_Action_unregistered(){
        skipBefore = true;
        rootLogger.info("Create thread in private zone");
        callModalNewConversation();
        submitNewConversationWindow(
                INVITE_FOLLOWER,
                null,
                invited.email,
                null,
                null,
                false,
                true
        );
        String followerNameSurname =  invited.email+" (inactive)";
        StepsPekamaProject.validateFollowerTeamChat(followerNameSurname, 2, 0);
    }
    @Test @Category({AllImapTests.class})
    public void inviteInTeamChatNewCollaborator_ValidationEmail() throws InterruptedException, MessagingException, IOException {
        skipBefore = false;
        rootLogger.info("Check invite email");

        ObjectProject project = ObjectProject.newBuilder().projectName(testProjectName).build();
        new ValidatorEmailInviteInProject()
                .buildReferenceEmail(invited, owner, project)
                .getEmailFormInbox()
                .buildValidator()
                .checkEmailBody()
                .assertValidationResult()
                .getInviteLink();
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
                collaborator.email,
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
                        owner.email,
                        owner.passwordEmail,
                        "COPY_OF_MY_OWN_MESSAGE",
                        LOREM_IPSUM_SHORT,
                        owner.nameSurname,
                        collaborator.nameSurname,
                        new IMessagesValidator.ValidationEmailMessage()
                )
        );
        repryLink = ValidationEmailMessage.replyLink;
    }
    @Test @Category({AllImapTests.class})
    public void checkThatUserGetCopyOwnMessages_C_CheckRedirectReplyLinkFollower() throws IOException, MessagingException {
        skipBefore = true;
        Assert.assertNotNull(repryLink);
        collaborator.login();
        openUrlWithBaseAuth(repryLink);
        checkTreadTitle("COPY_OF_MY_OWN_MESSAGE");
        rootLogger.info("Test Passed");
    }
    @Test @Category({AllImapTests.class})
    public void checkThatUserGetCopyOwnMessages_D_CheckRedirectReplyLinkCreator() throws IOException, MessagingException {
        skipBefore = false;
        Assert.assertNotNull(repryLink);
        owner.login();
        openUrlWithBaseAuth(repryLink);
        checkText("You followed a link meant for one of your other accounts. Please sign in with that account to proceed.");
        rootLogger.info("Test Passed");

    }
    @Test 
    public void checkThatGuestFollowerGetEmail_A_PostMessage() throws IOException, MessagingException {
        userNameSurname = owner.nameSurname;
        followerEmailOrTeamNameSurname = guest.email;
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
                guest.email,
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
                        owner.email,
                        owner.passwordEmail,
                        "EMAIL_TO_GUEST_MESSAGE",
                        LOREM_IPSUM_SHORT,
                        owner.nameSurname,
                        guest.email,
                        new IMessagesValidator.ValidationEmailMessage()
                )
        );

        rootLogger.info("Check guest email");
        sleep(10000);
        MessagesIMAP emailTask2 = new MessagesIMAP();
        Assert.assertTrue(
                emailTask2.validateEmailMessage(
                        guest.email,
                        guest.passwordEmail,
                        "EMAIL_TO_GUEST_MESSAGE",
                        LOREM_IPSUM_SHORT,
                        owner.nameSurname,
                        guest.email,
                        new IMessagesValidator.ValidationEmailMessage()
                )
        );
    }

    @Test 
    public void checkThatFollowerGetEmail() throws IOException, MessagingException {
        userNameSurname = owner.nameSurname;
        followerEmailOrTeamNameSurname = collaborator.nameSurname;

        openUrlWithBaseAuth(URL_PEKAMA_LOGOUT);

        rootLogger.info("Set FOLLOWER email settings");
        collaborator.login();
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
        owner.login();
        openUrlWithBaseAuth(testProjectUrl);
        callModalNewConversation();
        submitNewConversationWindow(
                ADD_FOLLOWER,
                "EMAIL_TO_FOLLOWER_MESSAGE",
                collaborator.email,
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
                        collaborator.email,
                        collaborator.passwordEmail,
                        "EMAIL_TO_FOLLOWER_MESSAGE",
                        LOREM_IPSUM_SHORT,
                        owner.nameSurname,
                        collaborator.nameSurname,
                        new IMessagesValidator.ValidationEmailMessage()
                )
        );
    }
}