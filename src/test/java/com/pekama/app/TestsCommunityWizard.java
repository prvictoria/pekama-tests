package com.pekama.app;
import Page.TestsCredentials;
import Steps.*;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

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
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.pekama.app.BeforeTestsSetUp.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityWizard {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String TEST_CASE_TYPE;
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

    @BeforeClass
    public static void beforeClass() {
        setEnvironment();
        TEST_CASE_TYPE = MATTER_TYPE_TRADEMARK;
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
        //rootLogger.info("Redirect back after login");
        //open(URL_COMMUNITY_WIZARD);
    }
    @AfterClass
    public static void after() {
        //open(URL_COMMUNITY_LOGOUT);
        //rootLogger.info("Open URL - "+URL_COMMUNITY_LOGOUT);
        clearBrowserCache();
    }
    @Rule
    public Timeout tests = Timeout.seconds(600);

    @Test
    public void boostYourProfileToWizardRedirect() {
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE);
        rootLogger.info("Boost Your profile - send new case");
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        MW_BOOST_YOUR_PROFILE_BTN_START_NEW_CASE.click();
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(Condition.disabled);
    }
    @Test
    public void boostYourProfileInviteTeam_withCustomText() {
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE);
        rootLogger.info("Boost Your profile - send new case");
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        MW_BOOST_YOUR_PROFILE_BTN_REFER_ATTORNEY.click();

        sleep(500);
        waitForModalWindow(TITLE_MW_INVITE_AN_ATTORNEY);
        MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.click();
        fillField(MW_COMMUNITY_INVITE_FIELD_EMAIL, User5.GMAIL_EMAIL.getValue());
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
        MW_COMMUNITY_INVITE_FIELD_EMAIL.shouldHave(Condition.value(User5.GMAIL_EMAIL.getValue()));
        fillField(MW_COMMUNITY_INVITE_FIELD_MESSAGE, "Hello world");
        submitEnabledButton(MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE);
        MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.shouldNotBe(visible);

        rootLogger.info("Check invitation email");
        SelenideElement EMAIL_SUBJECT = EMAIL_INVITE_IN_COMMUNITY_SUBJECT;
        String EMAIL_TITLE = EMAIL_INVITE_IN_COMMUNITY_TITLE;
        String EMAIL_TEXT = "Hello world";
        String EMAIL_BTN = EMAIL_INVITE_IN_COMMUNITY_BTN;
        SelenideElement EMAIL_REDIRECT_LINK = EMAIL_INVITE_IN_COMMUNITY_BACKLINK;
        checkInboxEmail(User5.GMAIL_EMAIL.getValue(), GMAIL_PASSWORD, EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
        rootLogger.info("Email redirect link is - "+REDIRECT_LINK);
    }
    @Test
    public void boostYourProfileInviteTeam_withDefaultText() {
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE);
        rootLogger.info("Boost Your profile - send new case");
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        MW_BOOST_YOUR_PROFILE_BTN_REFER_ATTORNEY.click();

        sleep(500);
        waitForModalWindow(TITLE_MW_INVITE_AN_ATTORNEY);
        MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.click();
        fillField(MW_COMMUNITY_INVITE_FIELD_EMAIL, User5.GMAIL_EMAIL.getValue());
        submitEnabledButton(MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE);

        sleep(500);
        rootLogger.info("Check dismiss modal window");
        SelenideElement mwTitle = MW_CONFIRM_INVITE_ATTOTNEY_TITLE;
        SelenideElement mwText = MW_CONFIRM_INVITE_ATTOTNEY_TEXT;
        SelenideElement btnAccept = MW_COMMUNITY_CONFIRM_SUBMIT;
        acceptModalConfirmAction(mwTitle, mwText, btnAccept);

        sleep(500);
        rootLogger.info("Check email = invitation email");
        SelenideElement EMAIL_SUBJECT = EMAIL_INVITE_IN_COMMUNITY_SUBJECT;
        String EMAIL_TITLE = EMAIL_INVITE_IN_COMMUNITY_TITLE;
        String EMAIL_TEXT = "Hello world";
        String EMAIL_BTN = EMAIL_INVITE_IN_COMMUNITY_BTN;
        SelenideElement EMAIL_REDIRECT_LINK = EMAIL_INVITE_IN_COMMUNITY_BACKLINK;
        checkInboxEmail(
                User5.GMAIL_EMAIL.getValue(),
                GMAIL_PASSWORD,
                EMAIL_SUBJECT,
                EMAIL_TITLE,
                EMAIL_TEXT,
                EMAIL_BTN,
                EMAIL_REDIRECT_LINK);
        rootLogger.info("Email redirect link is - "+REDIRECT_LINK);
        rootLogger.info("Test passed");
    }
    @Test
    public void returnBackFrom3rdStep(){
        rootLogger.info("1st Search");
        
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        WIZARD_STEP2.click();
        checkIfExpertPresent(EXPERT_TEAM_NAME);

        WIZARD_STEP1.click();
        WIZARD_SELECT_CaseType.shouldHave(text(TEST_CASE_TYPE));
        WIZARD_SELECT_Defining.shouldHave(text(TEST_CASE_COUNTRY));
        rootLogger.info("Test passed");
    }
    @Test
    public void returnBackFrom4thStep(){
        rootLogger.info("1st Search");
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

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
        rootLogger.info("1st Search");
        String status = COMMUNITY_STATUS_DRAFT;
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select SKIP");
        WIZARD_BTN_SKIP.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible);

        rootLogger.info("Check Draft");
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        checkCaseNameFirstRow(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        checkCaseStatus(TEST_CASE_TYPE, TEST_CASE_COUNTRY, 1, status);
        rootLogger.info(ROW_CONTROL_LABEL_STATUS);
        rootLogger.info("Check Return back");
        String row = getFirstCaseRow(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        $(byXpath(row)).click();
        BTN_SEND_INSTRUCTION.shouldBe(visible).shouldBe(enabled);
        rootLogger.info("Test passed");
    }
    @Test
    public void createDraftCaseWithCustomName(){
        rootLogger.info("1st Search");
        String status = COMMUNITY_STATUS_DRAFT;
        String caseName = "DRAFT_CASE_"+randomString(10);
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        String alreadyWorkedBefore = WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.getText();
        rootLogger.info(alreadyWorkedBefore);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(TEST_CASE_TYPE+" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);

        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible);

        rootLogger.info("Check introduce message");
        String msg = $("div.message-body.ng-binding > p").getText();
        rootLogger.info(msg);
        if (alreadyWorkedBefore.equals("START NEW CONVERSATION")) {
            Assert.assertEquals     (msgIntroduceWorkedBeforeCommunityCollaborators(
                    REQUESTER_NAME_SURNAME,
                    EXPERT_NAME_SURNAME,
                    TEST_CASE_COUNTRY), msg);
        }
        else {
            Assert.assertEquals(msgIntroduceNewCommunityCollaborators(
                    REQUESTER_NAME_SURNAME,
                    EXPERT_NAME_SURNAME,
                    TEST_CASE_COUNTRY), msg);
        }

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
        rootLogger.info("1st Search");
        String status = COMMUNITY_STATUS_DRAFT;
        String caseName = "CUSTOM_NAME"+randomString(10);
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(TEST_CASE_TYPE+" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);

        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();
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
        rootLogger.info("1st Search");
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "SENT_CASE_"+randomString(10);
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(TEST_CASE_TYPE+" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);
        WIZARD_BTN_NEXT.click();

        rootLogger.info("4th select NEXT");
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();

        rootLogger.info("5th step - cancel case");
        WIZARD_BTN_CANCEL.shouldBe(visible).click();
        acceptCancelCase(true);
        checkText(msgCaseCancelled(EXPERT_NAME), 2);
        rootLogger.info("Test passed");
    }
    @Test
    public void cancelCaseOn5thStep_B(){
        rootLogger.info("1st Search");
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "SENT_CASE_"+randomString(10);
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(TEST_CASE_TYPE+" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);
        WIZARD_BTN_NEXT.click();

        rootLogger.info("4th select NEXT");
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();

        rootLogger.info("5th step - cancel case");
        WIZARD_BTN_CANCEL.shouldBe(visible).click();
        acceptCancelCase(false);
        checkTextNotPresent(msgCaseCancelled(EXPERT_NAME), 2);
        rootLogger.info("Test passed");
    }

    @Test @Category(AllEmailsTests.class)
    public void createCaseInstructWithDetails_A(){
        rootLogger.info("1st Search");
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "SENT_CASE_"+randomString(10);
        searchExpertsQuery(TEST_CASE_TYPE, TEST_CASE_COUNTRY);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(TEST_CASE_TYPE+" in "+TEST_CASE_COUNTRY));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);

        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();
        WIZARD_BTN_INSTRUCT_NOW.shouldBe(visible).click();
        waitForModalWindow("Congratulations!");
        MW_CONGRATULATION_OK.click();
        MW.shouldNotBe(visible);
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