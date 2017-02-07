package com.pekama.app;
import Page.*;
import Steps.StepsPekama;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsCredentials.*;
import static Page.TestsUrl.*;
import static Steps.StepsExternal.checkInboxEmail;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaSettingsTeam {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = true;
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                TestsCredentials.User1.GMAIL_EMAIL.getValue(),
                TestsCredentials.User1.PEKAMA_PASSWORD.getValue(),
                URL_TeamSettings);
    }

//    @After
//    public void after() {
//        open(URL_COMMUNITY_LOGOUT);
//    }

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
        rootLogger.info("Textes and tabs present");

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
    @Test
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
    @Ignore //todo
    @Test
    public void values_testA_GUI() {
        rootLogger.info("Start test GUI and links");

        $(byText("Conference")).find(byLinkText("link=Conference")).shouldBe(visible);
        $(byText("Client Relation (CRM)")).find(byLinkText("link=Client Relation (CRM)")).shouldBe(visible);
        $(byText("Corporate")).find(byLinkText("link=Corporate")).shouldBe(visible);
        $(byText("Design")).find(byLinkText("link=Design")).shouldBe(visible);
        $(byText("Domain Name")).find(byLinkText("link=Domain Name")).shouldBe(visible);
        $(byText("Immigration")).find(byLinkText("link=Immigration")).shouldBe(visible);
        $(byText("Company")).find(byLinkText("link=Company")).shouldBe(visible);
        $(byText("Copyright")).find(byLinkText("link=Copyright")).shouldBe(visible);
        $(byText("Dispute")).find(byLinkText("link=Dispute")).shouldBe(visible);
        $(byText("General")).find(byLinkText("link=General")).shouldBe(visible);
        $(byText("Litigation")).find(byLinkText("link=Litigation")).shouldBe(visible);
        $(byText("Opposition")).find(byLinkText("link=Opposition")).shouldBe(visible);
        $(byText("Patent")).find(byLinkText("link=Patent")).shouldBe(visible);
        $(byText("TeamWork Channel")).find(byLinkText("link=TeamWork Channel")).shouldBe(visible);
        $(byText("Trademark")).find(byLinkText("link=Trademark")).shouldBe(visible);
        $(byText("Tasks")).find(byLinkText("link=Tasks")).shouldBe(visible);
        $(byText("Financials")).find(byLinkText("link=Financials")).shouldBe(visible);

        rootLogger.info("Defauls state passed");
    }
    @Ignore // TODO: 07-Feb-17
    @Test
    public void tabProfile_testB_() {

        rootLogger.info("");
        $(byText("Title:")).shouldBe(visible);
        $(byText("Code:")).shouldBe(visible);
        $(byText("Organization type:")).shouldBe(visible);
        $(byText("This organization is:")).shouldBe(visible);
        $(byText("Email:")).shouldBe(visible);
        $(byText("@organizations.pekama.com")).shouldBe(visible);
        $(byText("Additional Info")).shouldBe(visible);
        $(byText("Street address:")).shouldBe(visible);
        $(byText("Post code:")).shouldBe(visible);
        $(byText("City:")).shouldBe(visible);
        $(byText("State/Region")).shouldBe(visible);
        $(byText("Country:")).shouldBe(visible);
        $(byText("Title")).shouldBe(visible);
        $(byText("Title")).shouldBe(visible);

        rootLogger.info("");
    }

    @Ignore //todo
    @Test
    public void testC_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Ignore //todo
    @Test
    public void testD_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Ignore //todo
    @Test
    public void testE_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

}