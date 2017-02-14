package com.pekama.app;
import Steps.*;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.CommunityDashboard.*;
import static Page.CommunityOutgoing.*;
import static Page.CommunityWizard.*;
import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsCommunity.checkCaseNameFirstRow;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityWizard {
    static final Logger rootLogger = LogManager.getRootLogger();
    String testUserEmail = User2.GMAIL_EMAIL.getValue();
    String testUserPassword = User2.PEKAMA_PASSWORD.getValue();
    @Before
    public void before() {
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(testUserEmail, testUserPassword, URL_COMMUNITY_LOGIN);
        rootLogger.info("Redirect back after login");
    }
    @After
    public void after() {
        open(URL_COMMUNITY_LOGOUT);
        rootLogger.info("Open URL - "+URL_COMMUNITY_LOGOUT);
    }

    @Test
    public void boostYourProfileToWizardRedirect() {
        String caseType = CaseType.PATENT.getValue();
        String country = Countries.PITCAIRN_ISLANDS.getValue();
        searchExpertsQuery(caseType, country);
        searchExpertsSubmit();
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE);
        rootLogger.info("Boost Your profile - send new case");
        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        MW_BOOST_YOUR_PROFILE_BTN_START_NEW_CASE.click();
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(Condition.disabled);
    }
    @Test
    public void boostYourProfileInviteTeam_withCustomText() {
        String caseType = CaseType.PATENT.getValue();
        String country = Countries.PITCAIRN_ISLANDS.getValue();
        searchExpertsQuery(caseType, country);
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

        String caseType = CaseType.PATENT.getValue();
        String country = Countries.PITCAIRN_ISLANDS.getValue();
        searchExpertsQuery(caseType, country);
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
        checkInboxEmail(User5.GMAIL_EMAIL.getValue(), GMAIL_PASSWORD, EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
        rootLogger.info("Email redirect link is - "+REDIRECT_LINK);
        rootLogger.info("Test passed");
    }
    @Test
    public void returnBackFrom3rdStep(){
        String expertTeam = User1.TEAM_NAME.getValue();
        rootLogger.info("1st Search");
        String caseType = CaseType.PATENT.getValue();
        String country = Countries.PITCAIRN_ISLANDS.getValue();
        searchExpertsQuery(caseType, country);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        WIZARD_STEP2.click();
        checkIfExpertPresent(expertTeam);

        WIZARD_STEP1.click();
        WIZARD_SELECT_CaseType.shouldHave(text(caseType));
        WIZARD_SELECT_Defining.shouldHave(text(country));
        rootLogger.info("Test passed");
    }
    @Test
    public void returnBackFrom4thStep(){
        rootLogger.info("1st Search");
        String expertTeam = User1.TEAM_NAME.getValue();
        String caseType = CaseType.PATENT.getValue();
        String country = Countries.PITCAIRN_ISLANDS.getValue();
        searchExpertsQuery(caseType, country);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
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
        String expertTeam = User1.TEAM_NAME.getValue();
        String caseType = CaseType.PATENT.getValue();
        String caseCountry = Countries.PITCAIRN_ISLANDS.getValue();
        String status = COMMUNITY_STATUS_DRAFT;
        searchExpertsQuery(caseType, caseCountry);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select SKIP");
        WIZARD_BTN_SKIP.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible);

        rootLogger.info("Check Draft");
        COMMUNITY_TAB_Outgoing.click();
        sleep(3000);
        checkCaseNameFirstRow(caseType, caseCountry);
        checkCaseStatus(caseType, caseCountry, 1, status);
        rootLogger.info(ROW_CONTROL_LABEL_STATUS);
        rootLogger.info("Check Return back");
        String row = getFirstCaseRow(caseType, caseCountry);
        $(byXpath(row)).click();
        BTN_SEND_INSTRUCTION.shouldBe(visible).shouldBe(enabled);
        rootLogger.info("Test passed");
    }
    @Test
    public void createDraftCaseWithCustomName(){
        rootLogger.info("1st Search");
        String expertTeam = User1.TEAM_NAME.getValue();
        String caseType = CaseType.PATENT.getValue();
        String caseCountry = Countries.PITCAIRN_ISLANDS.getValue();
        String status = COMMUNITY_STATUS_DRAFT;
        String caseName = "CUSTOM_NAME"+randomString(10);
        searchExpertsQuery(caseType, caseCountry);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(caseType+" in "+caseCountry));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);

        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible);

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
        String expertTeam = User1.TEAM_NAME.getValue();
        String caseType = CaseType.PATENT.getValue();
        String caseCountry = Countries.PITCAIRN_ISLANDS.getValue();
        String status = COMMUNITY_STATUS_DRAFT;
        String caseName = "CUSTOM_NAME"+randomString(10);
        searchExpertsQuery(caseType, caseCountry);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(caseType+" in "+caseCountry));
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
    @Ignore //no need
    @Test
    public void createCaseInstructSimpleWay(){
        rootLogger.info("Test passed");
    }
    @Test
    public void createCaseInstructWithDetails_A(){
        rootLogger.info("1st Search");
        String expertTeam = User1.TEAM_NAME.getValue();
        String caseType = CaseType.PATENT.getValue();
        String caseCountry = Countries.PITCAIRN_ISLANDS.getValue();
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "CUSTOM_NAME"+randomString(10);
        searchExpertsQuery(caseType, caseCountry);
        searchExpertsSubmit();

        rootLogger.info("2nd select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(caseType+" in "+caseCountry));
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

    @Test
    public void createCaseInstructWithDetails_B_CheckEmail() {
       checkInboxEmail(
               testUserEmail,
               GMAIL_PASSWORD,
               EMAIL_CONGRATULATION_SUBJECT,
               EMAIL_CONGRATULATION_TITLE,
               EMAIL_CONGRATULATION_TEXT);

    }



}