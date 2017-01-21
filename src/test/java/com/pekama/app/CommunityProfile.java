package com.pekama.app;
import Page.TestsCredentials;
import Steps.PekamaSteps;
import Utils.HttpAuth;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.CommunityDashboard.*;
import static Page.CommunityLanding.*;
import static Page.CommunityProfile.*;
import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.urlTSMembers;
import static Page.TestsUrlConfiguration.*;
import static Steps.CommunitySteps.searchServicesQuery;
import static Steps.PekamaSteps.submitConfirmAction;
import static Steps.PekamaSteps.submitEnabledButton;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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
        Configuration test = new Configuration();
        test.holdBrowserOpen = true;
        log.info("Open host");
        HttpAuth openHost = new HttpAuth();
        openHost.httpAuthStagingCommunity();
        LANDING_LOGIN.shouldBe(Condition.visible).click();
        PekamaSteps login = new PekamaSteps();
        login.submitLoginCredentials(PEKAMA_USER_EMAIL);
        log.info("Redirect back after login");
        COMMUNITY_TAB_Profile.shouldBe(Condition.visible).shouldHave(Condition.text("my profile")).click();
    }
//    @After
//    public void openUrlLogout() {
//        open(urlLogout);
//    }
    @Ignore("not ready")
    @Test
    public void checkGui() {
        PROFILE_TEAM_NAME.shouldBe(visible).shouldHave(text(TEAM));
        PROFILE_BTN_SAVE_DESCRIPTION.shouldNotBe(disabled);
        PROFILE_BTN_ADD.shouldBe(disabled);
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
        PROFILE_TEAM_NAME.shouldHave(Condition.text(TEAM)).click();
        PROFILE_TEAM_NAME_INPUT.clear();
        PROFILE_TEAM_NAME_SAVE.click();
        $(byText(ERROR_MSG_BLANK_FIELD)).shouldBe(Condition.visible);
        log.info("Validation for empty name present");

        PROFILE_TEAM_NAME_CANCEL.click();
        $(byText(ERROR_MSG_BLANK_FIELD)).shouldNotBe(Condition.visible);
        log.info("Changes canceled");

        PROFILE_TEAM_NAME.shouldHave(Condition.text(TEAM)).click();
        PROFILE_TEAM_NAME_INPUT.sendKeys(VALID_COMPANY);
        PROFILE_TEAM_NAME_SAVE.click();
        log.info("New team name saved");

        PROFILE_TEAM_NAME.shouldHave(Condition.text(VALID_COMPANY)).click();
        PROFILE_TEAM_NAME_INPUT.sendKeys(TEAM);
        PROFILE_TEAM_NAME_SAVE.click();
        PROFILE_TEAM_NAME.shouldHave(Condition.text(TEAM));
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
        PROFILE_BTN_SAVE_DESCRIPTION.shouldBe(disabled);
        PROFILE_INPUT_DESCRIPTION.clear();
        PROFILE_BTN_SAVE_DESCRIPTION.shouldHave(value(""));
        log.info("firm's expertise text cleared");
        PROFILE_INPUT_DESCRIPTION.sendKeys(LOREM_IPSUM_SHORT);
        PROFILE_BTN_SAVE_DESCRIPTION.shouldNot(disabled).click();
        PROFILE_INPUT_DESCRIPTION.shouldHave(Condition.value(LOREM_IPSUM_SHORT));
        log.info("firm's expertise saved with dummy text");
    }
    @Test
    public void testA_addMember() {
        PROFILE_BTN_SAVE_DESCRIPTION.shouldBe(visible).click();
        MW.shouldBe(visible);
        $(byText("Members")).shouldNotBe(Condition.visible);
        MW_BTN_SUBMIT.click();
        $(byText(ERROR_MSG_BLANK_FIELD)).shouldBe(Condition.visible);
        log.info("Validation for empty EMAIL field present");
        MW_INPUT_NEW_MEMBER_EMAIL.sendKeys(NEW_MEMBER);
        log.info("Add memeber");
        MW_BTN_SUBMIT.click();
        MW.waitUntil(not(visible), 15000);
        $(byText(NEW_MEMBER+" (inactive)")).shouldBe(Condition.visible);
        log.info("New Memeber is displayed");
    }
    @Ignore
    @Test
    public void testB_deleteMember() {
        log.info("Check QTY and redirect to Pekama");
        PROFILE_MEMBERS_COUNT.shouldBe(visible).click();
        String redirectedUrl = url();
        assertEquals(urlTSMembers, redirectedUrl);
        $(byText(NEW_MEMBER+" (inactive)")).shouldBe(Condition.visible);
        log.info("Delete member");
    }
    @Ignore
    @Test
    public void addNewService_testA() {
        log.info("Add new service");
        String profileServiceCaseType = "Trademark";
        String profileServiceCountry = "Angola";
        String price = "100000";
        boolean rowPresentOnPage = true;
        PROFILE_BTN_ADD.shouldBe(disabled);
        log.info("Select new service - "+ profileServiceCaseType);
        searchServicesQuery(profileServiceCaseType, profileServiceCountry,  price);
        submitEnabledButton(PROFILE_BTN_ADD);
        findServiceRow(profileServiceCaseType, profileServiceCountry, rowPresentOnPage);
        log.info("Service was created");
        PROFILE_BTN_ADD.shouldBe(disabled);
    }
    @Test
    public void addNewService_testB() {
        log.info("Check validation by adding the same service");
        String caseType = "Trademark";
        String country = "Angola";
        String price = "100000";
        PROFILE_BTN_ADD.shouldBe(disabled);
        log.info("Select new service - "+caseType);
        searchServicesQuery( caseType,  country,  price);
        log.info("Service added");
        PROFILE_BTN_ADD.shouldBe(disabled);
    }
    @Test
    public void addNewService_testC() {
        log.info("Edit service");
        sleep(2000);
        String profileServiceCaseType = "Trademark";
        String profileServiceCountry = "Angola";
        clickServiceRowEdit(profileServiceCaseType, profileServiceCountry);
        PROFILE_BTN_ADD.shouldBe(disabled);
    }

    @Test
    public void addNewService_testD() {
        log.info("Delete service");
        String profileServiceCaseType = "Trademark";
        String profileServiceCountry = "Angola";
        boolean rowPresentOnPage = false;
        clickServiceRowDelete(profileServiceCaseType, profileServiceCountry);
        submitConfirmAction();
        findServiceRow(profileServiceCaseType, profileServiceCountry, rowPresentOnPage);
        log.info("Service was deleted");
        PROFILE_BTN_ADD.shouldBe(disabled);
    }

}
