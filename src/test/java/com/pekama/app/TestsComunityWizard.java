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

public class TestsComunityWizard {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {
//        Configuration test = new Configuration();
//        test.holdBrowserOpen = true;
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
        acceptModalConfirAction(mwTitle, mwText, btnAccept);

        sleep(500);
        rootLogger.info("Check email = invitation email");
        SelenideElement EMAIL_SUBJECT = EMAIL_INVITE_IN_COMMUNITY_SUBJECT;
        String EMAIL_TITLE = EMAIL_INVITE_IN_COMMUNITY_TITLE;
        String EMAIL_TEXT = "Hello world";
        String EMAIL_BTN = EMAIL_INVITE_IN_COMMUNITY_BTN;
        SelenideElement EMAIL_REDIRECT_LINK = EMAIL_INVITE_IN_COMMUNITY_BACKLINK;
        checkInboxEmail(User5.GMAIL_EMAIL.getValue(), GMAIL_PASSWORD, EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
        rootLogger.info("Email redirect link is - "+REDIRECT_LINK);
    }

}