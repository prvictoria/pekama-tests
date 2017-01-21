package com.pekama.app;
import Page.TestsCredentials;
import Page.TestsUrlConfiguration;
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
import static Page.TestsUrl.*;
import static Page.TestsUrlConfiguration.*;
import static Steps.CommunitySteps.*;
import static Steps.PekamaSteps.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommunityProfile {
    static final Logger log = LogManager.getLogger(CommunityProfile.class);
    String TEAM = TestsCredentials.User3.TEAM_NAME.getValue();
    String PEKAMA_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    String PEKAMA_USER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    String AUTH_URL = COMMUNITY_LOGIN;
    String NEW_MEMBER = "qazwsx@qaz.com";


    @Before
    public void openUrlLogin() {
//        Configuration test = new Configuration();
//        test.holdBrowserOpen = true;
        log.info("Open host");
        PekamaSteps loginIntoPekama = new PekamaSteps();
        loginIntoPekama.loginByURL(PEKAMA_USER_EMAIL, PEKAMA_USER_PASSWORD, AUTH_URL);
        log.info("Redirect back after login");
        COMMUNITY_TAB_Profile.shouldBe(Condition.visible).shouldHave(Condition.text("my profile")).click();
    }
    @After
    public void openUrlLogout() {
        open(COMMUNITY_LOGOUT);
    }

    @Test
    public void checkGui() {
        PROFILE_TEAM_NAME.shouldBe(visible).shouldHave(text(TEAM));
        PROFILE_BTN_SAVE_DESCRIPTION.shouldBe(disabled);
        PROFILE_BTN_ADD.shouldBe(disabled);
        PROFILE_TEAM_TAB.shouldBe(visible);
        PROFILE_PROFILE_TAB.shouldBe(visible);
        PROFILE_BTN_BOOST_SCORE.shouldBe(visible);
        PROFILE_BTN_INVITE.shouldBe(visible).shouldBe(enabled);
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
    @Ignore//todo delete members
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

    @Test
    public void service_testA_addService() {
        log.info("Add new service");
        String profileServiceCaseType = CaseType.TRADEMARK.getValue();
        String profileServiceCountry = Countries.AFGANISTAN.getValue();
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
    public void service_testB_addSameService() {
        log.info("Check validation by adding the same service");
        String price = "100000";
        PROFILE_BTN_ADD.shouldBe(disabled);
        log.info("Select new service - "+CaseType.TRADEMARK.getValue());
        searchServicesQuery(CaseType.TRADEMARK.getValue(),  Countries.AFGANISTAN.getValue(),  price);
        log.info("Service added");
        PROFILE_BTN_ADD.shouldBe(disabled);
    }
    @Test
    public void service_testC_editServicePrice() {
        log.info("Edit service");
        sleep(2000);
        String profileServiceCaseType = CaseType.TRADEMARK.getValue();
        String profileServiceCountry = Countries.AFGANISTAN.getValue();
        String price = "100000";
        String newPrice = "99999";
        clickServiceRowEdit(profileServiceCaseType, profileServiceCountry);
        PROFILE_BTN_ADD.shouldBe(disabled);
        changeServiceRate(profileServiceCaseType, profileServiceCountry, newPrice);
        submitEnabledButton(PROFILE_SERVICE_SAVE);
    }

    @Test
    public void service_testD_deleteService() {
        log.info("Delete service");
        String profileServiceCaseType = CaseType.TRADEMARK.getValue();
        String profileServiceCountry = Countries.AFGANISTAN.getValue();
        boolean rowPresentOnPage = false;
        clickServiceRowDelete(profileServiceCaseType, profileServiceCountry);
        submitConfirmAction();
        findServiceRow(profileServiceCaseType, profileServiceCountry, rowPresentOnPage);
        log.info("Service was deleted");
        PROFILE_BTN_ADD.shouldBe(disabled);
    }

    @Test
    public void yourProfileSaveNewName_A_SetNewName() {
        log.info("open Yuor profile tab");
        String newName = "new name";
        String newSurname = "new surname";
        PROFILE_PROFILE_TAB.click();
        PROFILE_FIELD_NAME.shouldHave(value(User3.NAME.getValue()));
        PROFILE_FIELD_SURNAME.shouldHave(value(User3.SURNAME.getValue()));
        enterCharsetInField(PROFILE_FIELD_NAME, newName);
        enterCharsetInField(PROFILE_FIELD_SURNAME, newSurname);
        submitEnabledButton(PROFILE_BTN_SAVE_NAME_AND_SURNAME);
        PROFILE_BTN_SAVE_NAME_AND_SURNAME.shouldBe(disabled);
        sleep(2000);
        refresh();
        PROFILE_FIELD_NAME.shouldHave(value(newName));
        PROFILE_FIELD_SURNAME.shouldHave(value(newSurname));
        log.info("Service was deleted");

    }
    @Test
    public void yourProfileSaveNewName_B_returnUserName() {
        log.info("open Yuor profile tab");
        String newName = "new name";
        String newSurname = "new surname";
        PROFILE_PROFILE_TAB.click();
        PROFILE_FIELD_NAME.shouldHave(value(newName));
        PROFILE_FIELD_SURNAME.shouldHave(value(newSurname));
        enterCharsetInField(PROFILE_FIELD_NAME, User3.NAME.getValue());
        enterCharsetInField(PROFILE_FIELD_SURNAME, User3.SURNAME.getValue());
        submitEnabledButton(PROFILE_BTN_SAVE_NAME_AND_SURNAME);
        PROFILE_BTN_SAVE_NAME_AND_SURNAME.shouldBe(disabled);
        sleep(2000);
        refresh();
        PROFILE_FIELD_NAME.shouldHave(value(User3.NAME.getValue()));
        PROFILE_FIELD_SURNAME.shouldHave(value(User3.SURNAME.getValue()));
        log.info("Service was deleted");

    }
}
