package com.pekama.app;/**
 * Created by VatslauX on 04-Jan-17.
 */

import Steps.*;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import static Page.CommunityWizard.*;
import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Selenide.*;

public class ComunityWizard {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = true;
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(User2.GMAIL_EMAIL.getValue(), User2.PEKAMA_PASSWORD.getValue(), URL_COMMUNITY_LOGIN);
        rootLogger.info("Redirect back after login");
    }
//    @After
//    public void after() {
//        open(URL_COMMUNITY_LOGOUT);
//    }

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
        WIZARD_BTN_GetStarted.shouldBe(Condition.visible).shouldBe(Condition.disabled);
    }
    @Test
    public void boostYourProfileInviteTeam() {
        String caseType = CaseType.PATENT.getValue();
        String country = Countries.PITCAIRN_ISLANDS.getValue();
        searchExpertsQuery(caseType, country);
        searchExpertsSubmit();
        submitEnabledButton(PROFILE_BTN_BOOST_YOUR_PROFILE);
        rootLogger.info("Boost Your profile - send new case");

        waitForModalWindow(TITLE_MW_BOOST_YOUR_PROFILE);
        MW_BOOST_YOUR_PROFILE_BTN_REFER_ATTORNEY.click();
        rootLogger.info("Check dismiss modal window");

        sleep(500);
        waitForModalWindow(TITLE_MW_INVITE_AN_ATTORNEY);
        MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE.click();
        fillField(MW_COMMUNITY_INVITE_FIELD_EMAIL, User5.GMAIL_EMAIL.getValue());
        submitEnabledButton(MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE);



//        rootLogger.info("Check invitation email");
//        SelenideElement EMAIL_SUBJECT = null;
//        String EMAIL_TITLE = null;
//        String EMAIL_TEXT = null;
//        String EMAIL_BTN = null;
//        SelenideElement EMAIL_REDIRECT_LINK = null;
//        checkInboxEmail(User5.GMAIL_EMAIL.getValue(), GMAIL_PASSWORD, EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
    }

}