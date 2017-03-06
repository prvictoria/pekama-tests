package com.pekama.app;
import Page.TestsCredentials;
import Steps.StepsPekama;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.StepsExternal.checkInboxEmail;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;
import static com.pekama.app.AllTestsRunner.setBrowser;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaSettingsTeam {
    static final Logger rootLogger = LogManager.getRootLogger();
    private final String TEST_USER_LOGIN = TestsCredentials.User1.GMAIL_EMAIL.getValue();
    private final String TEST_USER_PASSWORD = TestsCredentials.User1.PEKAMA_PASSWORD.getValue();
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                TEST_USER_LOGIN,
                TEST_USER_PASSWORD,
                URL_TeamSettings);
    }
    @After
    public void after() {
        openUrlWithBaseAuth(URL_Logout);
        clearBrowserCache();
    }

    @Test
    public void profile_testA_GUI() {
        rootLogger.info("Start test GUI and links");
        checkText("Team details");
        SETTINGS_TEAM_TAB_PROFILE.waitUntil(visible, 15000).shouldHave(Condition.text("Profile"));
        SETTINGS_TEAM_TAB_MEMBERS.waitUntil(visible, 15000).shouldHave(Condition.text("Members"));
        SETTINGS_TEAM_TAB_VALUES.waitUntil(visible, 15000).shouldHave(Condition.text("Values"));
        SETTINGS_TEAM_TAB_TASK_TEMPLATES.waitUntil(visible, 15000).shouldHave(Condition.text("Task Templates"));
        SETTINGS_TEAM_TAB_MESSAGE_TEMPLATES.waitUntil(visible, 15000).shouldHave(Condition.text("Message Templates"));
        SETTINGS_TEAM_TAB_EVENT_TEMPLATES.waitUntil(visible, 15000).shouldHave(Condition.text("Event Templates"));
        SETTINGS_TEAM_TAB_DOCUMENT_TEMPLATES.waitUntil(visible, 15000).shouldHave(Condition.text("Document Templates"));
        SETTINGS_TEAM_TAB_STORAGE.waitUntil(visible, 15000).shouldHave(Condition.text("Storage"));
        rootLogger.info("Texts and tabs present");

    }

    @Test
    public void members_testA_AddAndDelete() {
        String testEmail = "123@mail.com";
        rootLogger.info("Add member");
        SETTINGS_TEAM_TAB_MEMBERS.waitUntil(visible, 20000).click();
        TAB_MEMBERS_BTN_ADD.shouldBe(visible).click();
        waitForModalWindow("Members");
        fillField(MW_MEMBERS_EMAIL, testEmail);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNot(visible);
        checkMember(testEmail);

        rootLogger.info("Delete member");
        deleteMember(testEmail);
        rootLogger.info("Test passed");

    }

    @Test
    public void members_testB_addSameMember() {
        String testEmail = "123@mail.com";
        rootLogger.info("Add member");
        SETTINGS_TEAM_TAB_MEMBERS.waitUntil(visible, 20000).click();
        TAB_MEMBERS_BTN_ADD.shouldBe(visible).click();
        waitForModalWindow("Members");
        fillField(MW_MEMBERS_EMAIL, testEmail);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNot(visible);
        checkMember(testEmail);

        TAB_MEMBERS_BTN_ADD.shouldBe(visible).click();
        waitForModalWindow("Members");
        fillField(MW_MEMBERS_EMAIL, testEmail);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldHave(text("Already a member"));
        MW_BTN_CANCEL.click();
        checkMember(testEmail);

        rootLogger.info("Delete member");
        deleteMember(testEmail);
        rootLogger.info("Test passed");
    }
    @Test @Category(AllEmailsTests.class)
    public void testC_inviteNewMember() {
        String testEmail = TestsCredentials.User5.GMAIL_EMAIL.getValue();
        SelenideElement EMAIL_SUBJECT = EMAIL_INVITE_IN_TEAM_SUBJECT;
        String EMAIL_TITLE = EMAIL_INVITE_IN_TEAM_TITLE;
        String EMAIL_TEXT = EMAIL_INVITE_IN_TEAM_TEXT;
        String EMAIL_BTN = EMAIL_INVITE_IN_TEAM_BTN;
        SelenideElement EMAIL_REDIRECT_LINK = EMAIL_INVITE_IN_TEAM_BACKLINK;
        rootLogger.info("Add member");
        SETTINGS_TEAM_TAB_MEMBERS.waitUntil(visible, 20000).click();
        TAB_MEMBERS_BTN_ADD.shouldBe(visible).click();
        waitForModalWindow("Members");
        fillField(MW_MEMBERS_EMAIL, testEmail);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNot(visible);
        checkMember(testEmail);

        rootLogger.info("Delete member");
        deleteMember(testEmail);

        rootLogger.info("Check email inbox");
        String actualBackLink = checkInboxEmail(
                testEmail, GMAIL_PASSWORD, EMAIL_SUBJECT,
                EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
        if (actualBackLink == null) {Assert.fail("Redirect Link not found");}
        rootLogger.info("Test passed");
    }

     @Test
    public void values_testA_GUI() {
        rootLogger.info("Start test GUI and links");
        SETTINGS_TEAM_TAB_VALUES.waitUntil(visible, 20000).click();
         SETTINGS_VALUES_TAB_TRADEMARK.shouldHave(text("Trademark")).shouldBe(visible);
         sleep(2000);
         $(byXpath("//*[@href='/a/settings/team/values/patents']")).shouldHave(text("Patent")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/litigations']")).shouldHave(text("Litigation")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/oppositions']")).shouldHave(text("Opposition")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/copyrights']")).shouldHave(text("Copyright")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/teamwork']")).shouldHave(text("TeamWork Channel")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/Domains']")).shouldHave(text("Domain Name")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/Corporate']")).shouldHave(text("Corporate")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/CNF']")).shouldHave(text("Conference")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/Designs']")).shouldHave(text("Design")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/Immigration']")).shouldHave(text("Immigration")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/general']")).shouldHave(text("General")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/disputes']")).shouldHave(text("Dispute")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/companies']")).shouldHave(text("Company")).shouldBe(visible);
         $(byXpath("//*[@href='/a/settings/team/values/CRM']")).shouldHave(text("Client Relation (CRM)")).shouldBe(visible);
         SETTINGS_VALUES_TAB_TASKS.shouldHave(text("Tasks")).shouldBe(visible);
         SETTINGS_VALUES_TAB_CHARGES.shouldHave(text("Charges")).shouldBe(visible);

        rootLogger.info("Defauls state passed");
    }

    @Test
    public void tabProfile_testB_Validation() {
        String longString = randomString(256);
        rootLogger.info("Check max length validation");
        TAB_PROFILE_BTN_SAVE.waitUntil(visible, 20000).shouldBe(disabled);
        $(byText("Title:")).shouldBe(visible);
        fillField(TAB_PROFILE_TITLE, longString);
        $(byText("Code:")).shouldBe(visible);
        fillField(TAB_PROFILE_CODE, longString);
        $(byText("Organization type:")).shouldBe(visible);
        $(byText("This organization is:")).shouldBe(visible);
        $(byText("Email:")).shouldBe(visible);
        fillField(TAB_PROFILE_EMAIL, longString);
        $(byText("@organizations.pekama.com")).shouldBe(visible);
        $(byText("Additional Info")).shouldBe(visible);
        $(byText("Street address:")).shouldBe(visible);
        fillField(TAB_PROFILE_STREET, longString);
        $(byText("Post code:")).shouldBe(visible);
        fillField(TAB_PROFILE_ZIP, longString);
        $(byText("City:")).shouldBe(visible);
        fillField(TAB_PROFILE_CITY, longString);
        $(byText("State/Region")).shouldBe(visible);
        fillField(TAB_PROFILE_REGION, longString);
        $(byText("Country:")).shouldBe(visible);
        TAB_PROFILE_BTN_SAVE.shouldBe(enabled).click();
        checkText(ERROR_MSG_VALIDATION_LENGTH_255, 4);
        checkText(ERROR_MSG_VALIDATION_LENGTH_4);
        checkText(ERROR_MSG_VALIDATION_LENGTH_64);
        checkText(ERROR_MSG_VALIDATION_LENGTH_20);
        rootLogger.info("Test passed");
    }
    @Test
    public void tabProfile_testC_ValidationEmpty() {
        String teamName = "";
        String code = "";
        rootLogger.info("Check max length validation");
        TAB_PROFILE_BTN_SAVE.waitUntil(visible, 20000).shouldBe(disabled);
        $(byText("Title:")).shouldBe(visible);
        fillField(TAB_PROFILE_TITLE, teamName);
        $(byText("Code:")).shouldBe(visible);
        fillField(TAB_PROFILE_CODE, code);
        TAB_PROFILE_BTN_SAVE.shouldBe(enabled).click();
        checkText(ERROR_MSG_BLANK_FIELD, 2);
    }
}