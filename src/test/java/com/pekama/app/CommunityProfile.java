package com.pekama.app;
import Page.TestsCredentials;
import Steps.PekamaSteps;
import Utils.HttpAuth;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.*;
import static Page.CommunityLanding.*;
import static Page.CommunityProfile.*;
import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.urlTSMembers;
import static Page.TestsUrlConfiguration.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommunityProfile {
    static final Logger log = LogManager.getLogger(CommunityProfile.class);
    String TEAM = TestsCredentials.User3.TEAM_NAME.getValue();
    String PEKAMA_USER_EMAIL = TestsCredentials.User3.GMAIL_EMAIL.getValue();
    String SELECT_HOST = COMMUNITY;
    String NEW_MEMBER = "qazwsx@qaz.com";
    @Before
    public void openUrlLogin() {
        log.info("Open host");
        HttpAuth openHost = new HttpAuth();
        openHost.httpAuthStagingCommunity();
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
    @Ignore("not ready")
    @Test
    public void checkGui() {
        $(byXpath(PROFILE_TEAM_NAME)).shouldBe(visible).shouldHave(text(TEAM));
        $(byXpath(PROFILE_BTN_SAVE_DESCRIPTION)).shouldNotBe(disabled);
        $(byXpath(PROFILE_BTN_ADD)).shouldBe(disabled);
//        $(byXpath(PROFILE_TEAM_NAME)).shouldBe(visible);
//        $(byXpath(PROFILE_TEAM_NAME)).shouldNotBe(visible);
//        $(byXpath(PROFILE_TEAM_NAME)).shouldNotBe(visible);
//        $(byXpath(PROFILE_TEAM_NAME)).shouldNotBe(visible);
        log.info("Gui elements present");
    }
    @Ignore("not ready")
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
    @Ignore("not ready")
    @Test
    public void serviceCRUD() {
        log.info("Service added");
        log.info("Service edited");
        log.info("Service deleted");
        log.info("No services");
    }
    @Test
    public void saveFirmExpertise() {
        $(byXpath(PROFILE_BTN_SAVE_DESCRIPTION)).shouldBe(disabled);
        $(byXpath(PROFILE_INPUT_DESCRIPTION)).clear();
        $(byXpath(PROFILE_BTN_SAVE_DESCRIPTION)).shouldHave(value(""));
        log.info("firm's expertise text cleared");
        $(byXpath(PROFILE_INPUT_DESCRIPTION)).sendKeys(LOREM_IPSUM_SHORT);
        $(byXpath(PROFILE_BTN_SAVE_DESCRIPTION)).shouldNot(disabled).click();
        $(byXpath(PROFILE_INPUT_DESCRIPTION)).shouldHave(Condition.value(LOREM_IPSUM_SHORT));
        log.info("firm's expertise saved with dummy text");
    }
    @Test
    public void testA_addMember() {
        $(byXpath(PROFILE_BTN_SAVE_DESCRIPTION)).shouldBe(visible).click();
        $(byXpath(MW)).shouldBe(visible);
        $(byText("Members")).shouldNotBe(Condition.visible);
        $(byXpath(MW_BTN_SUBMIT)).click();
        $(byText(ERROR_MSG_BLANK_FIELD)).shouldBe(Condition.visible);
        log.info("Validation for empty EMAIL field present");
        $(byXpath(MW_INPUT_NEW_MEMEBER_EMAIL)).sendKeys(NEW_MEMBER);
        log.info("Add memeber");
        $(byXpath(MW_BTN_SUBMIT)).click();
        $(byXpath(MW)).waitUntil(not(visible), 15000);
        $(byText(NEW_MEMBER+" (inactive)")).shouldBe(Condition.visible);
        log.info("New Memeber is displayed");
    }
    @Ignore
    @Test
    public void testB_deleteMember() {
        log.info("Check QTY and redirect to Pekama");
        $(byXpath(PROFILE_MEMBERS_COUNT)).shouldBe(visible).click();
        String redirectedUrl = url();
        assertEquals(urlTSMembers, redirectedUrl);
        $(byText(NEW_MEMBER+" (inactive)")).shouldBe(Condition.visible);
        log.info("Delete member");
    }

}
