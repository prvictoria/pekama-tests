package Tests;
import Page.TestsCredentials;
import Steps.StepsPekama;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.*;
import org.junit.runners.*;

import java.io.IOException;
import java.security.SecureRandom;

import static Page.CommunityDashboard.*;
import static Page.CommunityProfile.*;
import static Page.CommunityWizard.*;
import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityProfile {
    @Rule
    public Timeout tests = Timeout.seconds(600);
    static final Logger log = LogManager.getLogger(TestsCommunityProfile.class);
    String TEAM = TestsCredentials.User3.TEAM_NAME.getValue();
    String PEKAMA_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    String PEKAMA_USER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    String TEST_USER_NAME = User3.NAME.getValue();
    String TEST_USER_SURNAME = User3.SURNAME.getValue();
    String TEST_USER_NAME_SURNAME = User3.NAME_SURNAME.getValue();
    String NEW_MEMBER = "newmember@qaz.com";
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void openUrlLogin() {
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(PEKAMA_USER_EMAIL, PEKAMA_USER_PASSWORD, URL_COMMUNITY_LOGIN);
        log.info("Redirect back after login");
        COMMUNITY_TAB_Profile.shouldBe(Condition.visible).shouldHave(Condition.text("my profile")).click();
    }
    @After
    public void openUrlLogout() {
        openUrlWithBaseAuth(URL_COMMUNITY_LOGOUT);
        clearBrowserCache();
    }

    @Test
    public void team_checkGui() {
        PROFILE_TEAM_NAME.shouldBe(visible).shouldHave(text(TEAM));
        PROFILE_BTN_SAVE_DESCRIPTION.shouldBe(disabled);
        PROFILE_BTN_ADD.shouldBe(disabled);
        PROFILE_TEAM_TAB.shouldBe(visible);
        PROFILE_PROFILE_TAB.shouldBe(visible);
        PROFILE_BTN_BOOST_YOUR_SCORE.shouldBe(visible);
        PROFILE_BTN_INVITE.shouldBe(visible).shouldBe(enabled);
        log.info("Gui elements present");
    }
    @Test
    public void boostScrores() {
        log.info("Check redirect to wizard");
        PROFILE_BTN_BOOST_YOUR_SCORE.click();
        submitMwBoostProfile("start");
        checkWizard1StepSelection(
                MATTER_TYPE_PATENT,
                Countries.ALL.getValue(),
                "Choose supplier type...");
        log.info("Test passed");
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
        PROFILE_INPUT_DESCRIPTION.shouldHave(value(""));
        log.info("firm's expertise text cleared");
        PROFILE_INPUT_DESCRIPTION.sendKeys(LOREM_IPSUM_SHORT);
        PROFILE_BTN_SAVE_DESCRIPTION.shouldNot(disabled).click();
        PROFILE_INPUT_DESCRIPTION.shouldHave(Condition.value(LOREM_IPSUM_SHORT));
        log.info("Saved firm's expertise with dummy text");
    }
    @Test
    public void testA_addMember() {
        log.info("Check members QTY");
        PROFILE_MEMBERS_COUNT.shouldBe(visible);
        sleep(3000);
        String actualMemberQty = PROFILE_MEMBERS_COUNT.getText();
        String defaultMemberQty = "1 Members";
        if (defaultMemberQty.equals(actualMemberQty)==false){
            deleteAllMembers(TEST_USER_NAME_SURNAME);
            openUrlWithBaseAuth(URL_COMMUNITY_PROFILE_TEAM);
            PROFILE_MEMBERS_COUNT.waitUntil(visible, 20000);
        }
        PROFILE_MEMBERS_COUNT.shouldHave(text(defaultMemberQty));
        PROFILE_BTN_INVITE.shouldBe(visible).click();
        waitForModalWindow("Members");
        MW_BTN_SUBMIT.click();
        $(byText(ERROR_MSG_BLANK_FIELD)).shouldBe(Condition.visible);
        log.info("Validation for empty EMAIL field present");
        MW_INPUT_NEW_MEMBER_EMAIL.sendKeys(NEW_MEMBER);
        log.info("Add memeber");
        MW_BTN_SUBMIT.click();
        MW_BTN_SUBMIT.waitUntil(not(visible), 40000);

        log.info("Ceck and submit score prompt");
        checkText("The community score will be raised by 5 once he joins your team.");
        submitEnabledButton(MW_BTN_OK);
        MW_BTN_OK.shouldNot(visible);

        String twoMemberQty = "2 Members";
        actualMemberQty = PROFILE_MEMBERS_COUNT.getText();
        if (twoMemberQty.equals(actualMemberQty)==false){
            Assert.fail("Member not added");
        }
        sleep(2000);
        $(byText(NEW_MEMBER+" (inactive)")).waitUntil(visible, 10000);
        log.info("New Memeber is displayed");

        log.info("Delete member");
        openUrlWithBaseAuth(URL_Members);
        deleteMember(NEW_MEMBER);
        log.info("Test passed");
    }
    @Test
    public void service_testA_addService() {
        log.info("Add new service");
        String profileServiceCaseType = MATTER_TYPE_TRADEMARK;
        String profileServiceCountry = Countries.AMERICAN_SAMOA.getValue();
        String price = "100000";
        boolean rowPresentOnPage = true;
        PROFILE_BTN_ADD.shouldBe(disabled);
        log.info("Select new service - "+ profileServiceCaseType);
        searchServicesQuery(profileServiceCaseType, profileServiceCountry,  price);
        submitEnabledButton(PROFILE_BTN_ADD);
        findServiceRow(rowPresentOnPage, profileServiceCaseType, profileServiceCountry);
        log.info("Service was created");
        PROFILE_BTN_ADD.shouldBe(disabled);
        log.info("Test passed");
    }
    @Test
    public void service_testB_addSameService() {
        log.info("Check validation by adding the same service");
        String price = "100000";
        PROFILE_BTN_ADD.shouldBe(disabled);
        log.info("Select new service - "+MATTER_TYPE_TRADEMARK);
        searchServicesQuery(MATTER_TYPE_TRADEMARK,  Countries.AMERICAN_SAMOA.getValue(),  price);
        log.info("Service NOT added");
        PROFILE_BTN_ADD.shouldBe(disabled);
        log.info("Test passed");
    }
    @Test
    public void service_testC_editServicePrice() {
        log.info("Edit service");
        sleep(2000);
        String profileServiceCaseType = MATTER_TYPE_TRADEMARK;
        String profileServiceCountry = Countries.AMERICAN_SAMOA.getValue();
        String price = "100000";
        String newPrice = "99999";
        clickServiceRowEdit(profileServiceCaseType, profileServiceCountry);
        PROFILE_BTN_ADD.shouldBe(disabled);
        changeServiceRate(profileServiceCaseType, profileServiceCountry, newPrice);
        submitEnabledButton(PROFILE_SERVICE_SAVE);
        log.info("Test passed");
    }
    @Test
    public void service_testD_deleteService() {
        log.info("Delete service");
        String profileServiceCaseType = MATTER_TYPE_TRADEMARK;
        String profileServiceCountry = Countries.AMERICAN_SAMOA.getValue();
        boolean rowPresentOnPage = false;
        clickServiceRowDelete(profileServiceCaseType, profileServiceCountry);
        submitConfirmAction();
        sleep(2000);
        findServiceRow(rowPresentOnPage, profileServiceCaseType, profileServiceCountry);
        log.info("Service was deleted");
        PROFILE_BTN_ADD.shouldBe(disabled);
        log.info("Test passed");
    }
    @Test
    public void yourProfileSaveNewName_A_SetNewName() {
        log.info("open Your profile tab");
        String newName = "new name";
        String newSurname = "new surname";
        PROFILE_PROFILE_TAB.click();
        log.info("Set new name and surname");
        fillField(PROFILE_FIELD_NAME, newName);
        fillField(PROFILE_FIELD_SURNAME, newSurname);
        submitEnabledButton(PROFILE_BTN_SAVE_NAME_AND_SURNAME);
        PROFILE_BTN_SAVE_NAME_AND_SURNAME.shouldBe(disabled);

        log.info("Check changes");
        sleep(5000);
        refresh();
        PROFILE_FIELD_NAME.waitUntil(visible, 20000).shouldHave(value(newName));
        PROFILE_FIELD_SURNAME.waitUntil(visible, 20000).shouldHave(value(newSurname));

        log.info("Restore user name and surname");
        fillField(PROFILE_FIELD_NAME, TEST_USER_NAME);
        fillField(PROFILE_FIELD_SURNAME,TEST_USER_SURNAME);
        submitEnabledButton(PROFILE_BTN_SAVE_NAME_AND_SURNAME);
        PROFILE_FIELD_NAME.shouldHave(value(TEST_USER_NAME));
        PROFILE_FIELD_SURNAME.shouldHave(value(TEST_USER_SURNAME));
        PROFILE_BTN_SAVE_NAME_AND_SURNAME.shouldBe(disabled);
        sleep(5000);
        log.info("Test passed");
    }
    @Test
    public void yourProfileSaveNewName_B_checkValidationMaxLength() {
        log.info("Open Your profile tab");
        String newName = randomString(101);
        String newSurname = randomString(101);
        PROFILE_PROFILE_TAB.click();
        fillField(PROFILE_FIELD_NAME, newName);
        fillField(PROFILE_FIELD_SURNAME, newSurname);

        log.info("Check validation");
        submitEnabledButton(PROFILE_BTN_SAVE_NAME_AND_SURNAME);
        PROFILE_BTN_SAVE_NAME_AND_SURNAME.shouldBe(disabled);
        sleep(3000);
        $$(byText(ERROR_MSG_VALIDATION_LENGTH_100)).shouldHaveSize(2);
        log.info("Test passed");
    }
    @Test
    public void yourProfileSaveNewName_B_checkValidationEmptyFields() {
        log.info("Open Your profile tab");
        String newName = "";
        String newSurname = "";
        PROFILE_PROFILE_TAB.click();
        fillField(PROFILE_FIELD_NAME, newName);
        fillField(PROFILE_FIELD_SURNAME, newSurname);

        log.info("Check validation");
        submitEnabledButton(PROFILE_BTN_SAVE_NAME_AND_SURNAME);
        PROFILE_BTN_SAVE_NAME_AND_SURNAME.shouldBe(disabled);
        sleep(3000);
        $$(byText("First name can not be blank")).shouldHaveSize(1);
        $$(byText("Last name can not be blank")).shouldHaveSize(1);
        log.info("Test passed");
    }
}
