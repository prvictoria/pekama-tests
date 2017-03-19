package Tests;
import Page.TestsCredentials;
import Steps.*;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.CommunityDashboard.*;
import static Page.CommunityOutgoing.*;
import static Page.CommunityWizard.*;
import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsExternal.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityWizard {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String CASE_TYPE;
    private final static String TEST_CASE_COUNTRY = Countries.PITCAIRN_ISLANDS.getValue();

    private final static String REQUESTER_EMAIL = User3.GMAIL_EMAIL.getValue();
    private final static String REQUESTER_PEKAMA_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private final static String REQUESTER_NAME = TestsCredentials.User3.NAME.getValue();
    private final static String REQUESTER_SURNAME = TestsCredentials.User3.SURNAME.getValue();
    private final static String REQUESTER_FULL_TEAM_NAME = TestsCredentials.User3.FULL_TEAM_NAME.getValue();
    private final static String REQUESTER_NAME_SURNAME = TestsCredentials.User3.NAME_SURNAME.getValue();

    private final static String EXPERT_EMAIL = TestsCredentials.User1.GMAIL_EMAIL.getValue();
    private final static String EXPERT_PEKAMA_PASSWORD = TestsCredentials.User1.PEKAMA_PASSWORD.getValue();
    private final static String EXPERT_NAME = TestsCredentials.User1.NAME.getValue();
    private final static String EXPERT_SURNAME = TestsCredentials.User1.SURNAME.getValue();
    private final static String EXPERT_TEAM_NAME = TestsCredentials.User1.TEAM_NAME.getValue();
    private final static String EXPERT_FULL_TEAM_NAME = TestsCredentials.User1.FULL_TEAM_NAME.getValue();
    private final static String EXPERT_NAME_SURNAME = TestsCredentials.User1.NAME_SURNAME.getValue();
    private final static String INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
    private final static String INVITED_EMAIL = User5.GMAIL_EMAIL.getValue();
    private final static String INVITED_PASSWORD = User5.GMAIL_PASSWORD.getValue();

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment();
        CASE_TYPE = MATTER_TYPE_TRADEMARK;
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {

        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                REQUESTER_EMAIL,
                REQUESTER_PEKAMA_PASSWORD,
                URL_COMMUNITY_LOGIN);
    }
    @Test
    public void boostYourProfileDismiss() {
        //TODO BUG #141678237 https://www.pivotaltracker.com/story/show/141678237
        submitWizard1Step(CASE_TYPE);
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE);
        rootLogger.info("Boost Your profile - check modal and dismiss");
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        checkText("If you want to boost your community profile and appear higher on the search results, there are two ways to do this:");
        checkText("Send cases to other members on the platform. Every time you send a case through the Pekama platform, even to your existing colleagues, your score is boosted once the case is completed");
        MW_BOOST_YOUR_PROFILE_BTN_REFER_ATTORNEY.shouldBe(visible);
        checkText("Refer another attorney to the community - when you refer another attorney (from another firm) and this attorney signs up, your scoring is boosted.");
        MW_BOOST_YOUR_PROFILE_BTN_START_NEW_CASE.shouldBe(visible);
        checkText("Invite a member of your team to the community - when you invite a team member who signs up, your score is boosted.");
        MW_BOOST_YOUR_PROFILE_BTN_INVITE_MEMBER.shouldBe(visible);
        MW.pressEscape();
        MW.shouldNotBe(visible);
    }
    @Test
    public void boostYourProfileToWizardRedirect() {
        submitWizard1Step(CASE_TYPE);
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE);
        rootLogger.info("Boost Your profile - send new case");
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        MW_BOOST_YOUR_PROFILE_BTN_START_NEW_CASE.click();
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(Condition.disabled);
    }
    @Test @Category(AllEmailsTests.class)
    public void boostYourProfileInviteTeam_withCustomText_A() {
        submitWizard1Step(CASE_TYPE);
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE); //TODO FIX it - BOOST community not present
        rootLogger.info("Boost Your profile - send new case");
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        MW_BOOST_YOUR_PROFILE_BTN_REFER_ATTORNEY.click();

        sleep(500);
        waitForModalWindow(TITLE_MW_INVITE_AN_ATTORNEY);
        MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.click();
        fillField(MW_COMMUNITY_INVITE_FIELD_EMAIL, INVITED_EMAIL);
        submitEnabledButton(MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE);

        sleep(500);
        rootLogger.info("Check dismiss modal window");
        SelenideElement mwTitle = MW_CONFIRM_INVITE_ATTOTNEY_TITLE;
        SelenideElement mwText = MW_CONFIRM_INVITE_ATTOTNEY_TEXT;
        SelenideElement btnDismiss = MW_COMMUNITY_CONFIRM_DISMISS;
        dismissModalConfirmAction(mwTitle, mwText, btnDismiss);
        rootLogger.info("Sent email = invitation email");

        sleep(500);
        waitForModalWindow(TITLE_MW_INVITE_AN_ATTORNEY);
        MW_COMMUNITY_INVITE_FIELD_EMAIL.shouldHave(Condition.value(INVITED_EMAIL));
        fillField(MW_COMMUNITY_INVITE_FIELD_MESSAGE, "Hello world");
        submitEnabledButton(MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE);
        MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.shouldNotBe(visible);
        rootLogger.info("Test passed");
    }
    @Test @Category(AllEmailsTests.class)
    public void boostYourProfileInviteTeam_withCustomText_A_CheckEmail() {
        rootLogger.info("Check invitation email");
        checkInboxEmail(
                INVITED_EMAIL,
                INVITED_PASSWORD,
                EMAIL_INVITE_IN_COMMUNITY_SUBJECT,
                EMAIL_INVITE_IN_COMMUNITY_TITLE, "Hello world",
                EMAIL_INVITE_IN_COMMUNITY_BTN,
                EMAIL_INVITE_IN_COMMUNITY_BACKLINK);
        rootLogger.info("Email redirect link is - "+REDIRECT_LINK);
        rootLogger.info("Test passed");
    }
    @Test @Category(AllEmailsTests.class)
    public void boostYourProfileInviteTeam_withCustomText_B_CheckEmail() {
        rootLogger.info("Check congratulation for invitation email");
        checkInboxEmail(
                REQUESTER_EMAIL,
                GMAIL_PASSWORD,
                EMAIL_THANKS_FOR_INVITING_SUBJECT,
                EMAIL_CONGRATULATION_FOR_INVITING_TITLE,
                EMAIL_CONGRATULATION_FOR_INVITING_TEXT);
        rootLogger.info("Test passed");
    }
    @Test
    public void boostYourProfileInviteTeam_withDefaultText_A() {
        submitWizard1Step(CASE_TYPE);
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE);
        rootLogger.info("Boost Your profile - send new case");
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        MW_BOOST_YOUR_PROFILE_BTN_REFER_ATTORNEY.click();

        sleep(500);
        waitForModalWindow(TITLE_MW_INVITE_AN_ATTORNEY);
        MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.click();
        fillField(MW_COMMUNITY_INVITE_FIELD_EMAIL, INVITED_EMAIL);
        submitEnabledButton(MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE);

        sleep(500);
        rootLogger.info("Check dismiss warning modal window");
        SelenideElement mwTitle = MW_CONFIRM_INVITE_ATTOTNEY_TITLE;
        SelenideElement mwText = MW_CONFIRM_INVITE_ATTOTNEY_TEXT;
        SelenideElement btnAccept = MW_COMMUNITY_CONFIRM_SUBMIT;
        acceptModalConfirmAction(mwTitle, mwText, btnAccept);
        rootLogger.info("Test passed");
    }
    @Test
    public void boostYourProfileInviteTeam_withDefaultText_A_CheckEmail() {
        rootLogger.info("Check invitation email");
        checkInboxEmail(
                INVITED_EMAIL,
                INVITED_PASSWORD,
                EMAIL_INVITE_IN_COMMUNITY_SUBJECT,
                EMAIL_INVITE_IN_COMMUNITY_TITLE, "Hello world",
                EMAIL_INVITE_IN_COMMUNITY_BTN,
                EMAIL_INVITE_IN_COMMUNITY_BACKLINK);
        rootLogger.info("Email redirect link is - "+REDIRECT_LINK);
        rootLogger.info("Test passed");
    }
    @Test
    public void boostYourProfileInviteTeam_withDefaultText_B_CheckEmail() {
        checkInboxEmail(
                REQUESTER_EMAIL,
                GMAIL_PASSWORD,
                EMAIL_CONGRATULATION_SUBJECT,
                EMAIL_CONGRATULATION_TITLE,
                EMAIL_CONGRATULATION_TEXT);
        rootLogger.info("Test passed");
    }
    @Test
    public void returnBackFrom3rdStep(){
        submitWizard1Step(CASE_TYPE);
        submitWizard2Step(EXPERT_TEAM_NAME);
        rootLogger.info("Jump to 2-nd step");
        WIZARD_STEP2.click();
        checkIfExpertPresent(EXPERT_TEAM_NAME);
        rootLogger.info("Jump to 1-st step");
        WIZARD_STEP1.click();
        WIZARD_SELECT_CaseType.shouldHave(text(CASE_TYPE));
        WIZARD_SELECT_Defining.shouldHave(text(TEST_CASE_COUNTRY));
        rootLogger.info("Test passed");
    }
    @Test
    public void returnBackFrom4thStep(){
        submitWizard1Step(CASE_TYPE);
        submitWizard2Step(EXPERT_TEAM_NAME);

        rootLogger.info("3 select NO");
        WIZARD_BTN_SKIP.click();
        sleep(2000);
        BTN_SEND_INSTRUCTION.shouldBe(visible);
        rootLogger.info("Return to 1-st step");
        WIZARD_STEP1.click();
        acceptReturnToFirstWizardStep();
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(disabled);
        rootLogger.info("Test passed");
    }
    @Test
    public void createDraftCaseSimpleWay(){
        String status = COMMUNITY_STATUS_DRAFT;
        submitWizard1Step(CASE_TYPE);
        submitWizard2Step(EXPERT_TEAM_NAME);

        rootLogger.info("3rd select SKIP");
        WIZARD_BTN_SKIP.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible);

        rootLogger.info("Check Draft");
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        checkCaseNameFirstRow(CASE_TYPE, TEST_CASE_COUNTRY);
        checkCaseStatus(CASE_TYPE, TEST_CASE_COUNTRY, 1, status);
        rootLogger.info(ROW_CONTROL_LABEL_STATUS);
        rootLogger.info("Check Return back");
        String row = getFirstCaseRow(CASE_TYPE, TEST_CASE_COUNTRY);
        $(byXpath(row)).click();
        BTN_SEND_INSTRUCTION.shouldBe(visible).shouldBe(enabled);
        rootLogger.info("Test passed");
    }
    @Test
    public void createDraftCaseWithCustomName(){
        String status = COMMUNITY_STATUS_DRAFT;
        String caseName = "DRAFT_CASE_"+randomString(10);
        submitWizard1Step(CASE_TYPE);

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        String alreadyWorkedBefore = WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.getText();
        rootLogger.info(alreadyWorkedBefore);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(CASE_TYPE +" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);

        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible);

        rootLogger.info("Check introduce message");
        String msg = $("div.message-body.ng-binding > p").getText();
        rootLogger.info(msg); //todo MSG on prod - is different
//        if (alreadyWorkedBefore.equals("START NEW CONVERSATION")) {
//            Assert.assertEquals     (msgIntroduceWorkedBeforeCommunityCollaborators(
//                    REQUESTER_NAME_SURNAME,
//                    EXPERT_NAME_SURNAME,
//                    TEST_CASE_COUNTRY), msg);
//        }
//        else {
//            Assert.assertEquals(msgIntroduceNewCommunityCollaborators(
//                    REQUESTER_NAME_SURNAME,
//                    EXPERT_NAME_SURNAME,
//                    TEST_CASE_COUNTRY), msg);
//        }

        rootLogger.info("Check Draft");
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, 1, status);
        rootLogger.info(ROW_CONTROL_LABEL_STATUS);
        rootLogger.info("Check Return back");
        String row = getFirstCaseRow(caseName);
        $(byXpath(row)).click();
        BTN_SEND_INSTRUCTION.shouldBe(visible).shouldBe(enabled);
        rootLogger.info("Test passed");
    }
    @Test
    public void returnBackFrom5thStep(){
        String status = COMMUNITY_STATUS_DRAFT;
        String caseName = "CUSTOM_NAME"+randomString(10);
        submitWizard1Step(CASE_TYPE);
        submitWizard2Step(EXPERT_TEAM_NAME);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(CASE_TYPE +" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);

        WIZARD_BTN_NEXT.click();
        sleep(3000);

        submitWizard4Step();

        rootLogger.info("Back to 4th Step");
        WIZARD_STEP4.click();
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();

        rootLogger.info("Back to 1st step");
        WIZARD_STEP1.click();
        acceptReturnToFirstWizardStep();
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(disabled);
        rootLogger.info("Test passed");
    }
    @Test
    public void cancelCaseOn5thStep_A(){
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "SENT_CASE_"+randomString(10);
        submitWizard1Step(CASE_TYPE);
        submitWizard2Step(EXPERT_TEAM_NAME);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(CASE_TYPE +" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);
        WIZARD_BTN_NEXT.click();

        submitWizard4Step();

        rootLogger.info("5th step - cancel case");
        WIZARD_BTN_CANCEL.shouldBe(visible).click();
        acceptCancelCase(true);
        checkText(msgCaseCancelled(EXPERT_NAME), 2);
        rootLogger.info("Test passed");
    }
    @Test
    public void cancelCaseOn5thStep_B(){
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "SENT_CASE_"+randomString(10);
        submitWizard1Step(CASE_TYPE);
        submitWizard2Step(EXPERT_TEAM_NAME);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(CASE_TYPE +" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);
        WIZARD_BTN_NEXT.click();

        submitWizard4Step();

        rootLogger.info("5th step - cancel case");
        WIZARD_BTN_CANCEL.shouldBe(visible).click();
        acceptCancelCase(false);
        checkTextNotPresent(msgCaseCancelled(EXPERT_NAME), 2);
        rootLogger.info("Test passed");
    }

    @Test @Category(AllEmailsTests.class)
    public void createCaseInstructWithDetails_A(){
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "SENT_CASE_"+randomString(10);
        submitWizard1Step(CASE_TYPE);
        submitWizard2Step(EXPERT_TEAM_NAME);
        submitWizard3Step(caseName);
        submitWizard4Step();
        submitWizard5Step();

        sleep(2000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, 1, status);
        rootLogger.info("Test passed");
    }
    @Test @Category(AllEmailsTests.class)
    public void createCaseInstructWithDetails_B_CheckEmail() {
       checkInboxEmail(
               REQUESTER_EMAIL,
               GMAIL_PASSWORD,
               EMAIL_CONGRATULATION_SUBJECT,
               EMAIL_CONGRATULATION_TITLE,
               EMAIL_CONGRATULATION_TEXT);
       rootLogger.info("Test passed");
    }

}