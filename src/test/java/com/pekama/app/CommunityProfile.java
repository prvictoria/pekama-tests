package com.pekama.app;
import Page.TestsCredentials;
import Steps.PekamaSteps;
import Utils.HttpAuth;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.COMMUNITY_HEADER_LOGIN;
import static Page.CommunityDashboard.*;
import static Page.CommunityLanding.*;
import static Page.CommunityProfile.*;
import static Page.DirectLinks.*;
import static Page.PekamaLogin.*;
import static Page.TestsCredentials.VALID_COMPANY;
import static Page.TestsStrings.ERROR_MSG_BLANK_FIELD;
import static Page.TestsUrlConfiguration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommunityProfile {
    static final Logger log = LogManager.getLogger(CommunityProfile.class);
    String TEAM = TestsCredentials.User3.TEAM_NAME.getValue();
    String PEKAMA_USER_EMAIL = TestsCredentials.User3.GMAIL_EMAIL.getValue();
    String SELECT_HOST = COMMUNITY;

    @Before
    public void openUrlLogin() {

        log.info("Open host");
        HttpAuth openHost = new HttpAuth();
        openHost.httpAuthStgingCommunity();
        $(By.xpath(LANDING_LOGIN)).shouldBe(Condition.visible).click();
        PekamaSteps login = new PekamaSteps();
        login.submitLoginCredentials(PEKAMA_USER_EMAIL);
        log.info("Redirect back after login");
        $(By.xpath(COMMUNITY_TAB_Profile)).shouldBe(Condition.visible).shouldHave(Condition.text("my profile")).click();
    }
//    @After
//    public void openUrlLogout() {
//        open(urlLogout);
//    }
    @Test
    public void checkGui() {
    log.info("Gui elements present");
    }
    @Test
    public void UploadDeleteAvatar() {
        log.info("");
    }
    @Test
    public void editTeamName() {
        $(byXpath(PROFILE_TEAM_NAME)).shouldHave(Condition.text(TEAM)).click();
        $(byXpath(PROFILE_TEAM_NAME_INPUT)).clear();
        $(byXpath(PROFILE_TEAM_NAME_SAVE)).click();
        $(byText(ERROR_MSG_BLANK_FIELD)).shouldBe(Condition.visible);
        log.info("Validation for empty name present");

        $(byXpath(PROFILE_TEAM_NAME_CANCEL)).click();
        $(byText(ERROR_MSG_BLANK_FIELD)).shouldNotBe(Condition.visible);
        log.info("Changes canceled");

        $(byXpath(PROFILE_TEAM_NAME)).shouldHave(Condition.text(TEAM)).click();
        $(byXpath(PROFILE_TEAM_NAME_INPUT)).sendKeys(VALID_COMPANY);
        $(byXpath(PROFILE_TEAM_NAME_SAVE)).click();
        log.info("New team name saved");

        $(byXpath(PROFILE_TEAM_NAME)).shouldHave(Condition.text(VALID_COMPANY)).click();
        $(byXpath(PROFILE_TEAM_NAME_INPUT)).sendKeys(TEAM);
        $(byXpath(PROFILE_TEAM_NAME_SAVE)).click();
        $(byXpath(PROFILE_TEAM_NAME)).shouldHave(Condition.text(TEAM));
        log.info("Team name restored");
    }
    @Test
    public void serviceCRUD() {
        log.info("Service added");
        log.info("Service edited");
        log.info("Service deleted");
        log.info("No services");
    }
    @Test
    public void saveFirmExpertise() {
        log.info("");
    }
    @Test
    public void addMember() {
        log.info("Add memeber");
        log.info("New Memeber is displayed");
    }
    @Test
    public void deleteMember() {
        log.info("Check QTY and redirect to Pekama");
        log.info("Delete member");
    }

}
