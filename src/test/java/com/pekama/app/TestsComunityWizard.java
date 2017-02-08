package com.pekama.app;/**
 * Created by VatslauX on 04-Jan-17.
 */
import Steps.*;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import static Page.CommunityWizard.*;
import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;

public class TestsComunityWizard {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(User2.GMAIL_EMAIL.getValue(), User2.PEKAMA_PASSWORD.getValue(), URL_COMMUNITY_LOGIN);
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
        WIZARD_BTN_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_REQUEST_INSTRUCTIONS);

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
        WIZARD_BTN_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_REQUEST_INSTRUCTIONS);

        rootLogger.info("3 select NO");
        WIZARD_BTN_NO.click();
        BTN_SEND_INSTRUCTION.shouldBe(visible);
        rootLogger.info("Return to 1-st step");
        WIZARD_STEP1.click();
        acceptReturnToFirstWizardStep();
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(disabled);
        rootLogger.info("Test passed");
    }
    @Test
    public void createDraftCaseSimpleWay(){
        rootLogger.info("Test passed");
    }
    @Test
    public void createDraftCaseWithDetailes(){
        rootLogger.info("Test passed");
    }

    @Test
    public void createCaseInstructSimpleWay(){
        rootLogger.info("Test passed");
    }
    @Test
    public void createCaseInstructWithDetailes(){
        rootLogger.info("Test passed");
    }

    @Test
    public void returnBackFrom5thStep(){
        rootLogger.info("Test passed");
    }

}