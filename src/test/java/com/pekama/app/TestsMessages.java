package com.pekama.app;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */

import Steps.*;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.SoftAssertionError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import java.awt.*;

import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaConversationProject.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.TrademarkEvents.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Page.Xero.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekama.checkPageTitle;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.pekama.app.AllTestsRunner.*;
import static org.junit.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsMessages {
    static final Logger rootLogger = LogManager.getRootLogger();
    private final static String TEST_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    private final static String TEST_USER_PEKAMA_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private final String TEST_USER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private final String TEST_USER_TEAM_NAME = User3.TEAM_NAME.getValue();
    private final String COLLABORATOR_TEAM_NAME = User1.TEAM_NAME.getValue();
    @Before
    public void before() {
            holdBrowserAfterTest();
            rootLogger.info("Open host");
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    TEST_USER_EMAIL,
                    TEST_USER_PEKAMA_PASSWORD,
                    URL_LogIn);
            rootLogger.info("Create project");
            DASHBOARD_NewProject.waitUntil(visible, 15000).click();
            String testProjectName = createProject();
            String testProjectUrl = getActualUrl ();
    }
    @After
    public void after() {

    }

    @Test
    public void testA_DefaultState() {
        String subject = randomString(513);
        String randomFollower = randomString(25)+"@email.random";
        String randomGuest = randomString(25)+"@email.guest";

        CONVERSATION_BTN_New.waitUntil(visible, 20000);
        checkText("Conversations");
        rootLogger.info("Check Team chat");
        CONVERSATION_BTN_Team.waitUntil(visible, 20000);
        checkText(CONVERSATION_TEAM_TAB_NAME);
        checkText(CONVERSATION_PLACEHOLDER_IN_TEAM_TAB);

        rootLogger.info("Check External chat");
        CONVERSATION_BTN_Client.waitUntil(visible, 20000).click();
        checkText(CONVERSATION_CLIENT_TAB_NAME);
        checkText(CONVERSATION_PLACEHOLDER_IN_CLIENT_TAB);

        CONVERSATION_BTN_Team.click();
        sleep(500);

        rootLogger.info("Check New Conversation validation");
        CONVERSATION_BTN_New.click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        MW_CONVERSATION_INPUT_Subject.shouldBe(empty);
        MW_CONVERSATION_INPUT_Follower.shouldBe(empty);
        checkText(TEST_USER_TEAM_NAME);

        MW_CHECKBOX_ALL_TEAMS.shouldNotBe(checked);
        fillField(MW_CONVERSATION_INPUT_Subject, subject);
        submitEnabledButton(MW_BTN_CREATE);
        checkText(ERROR_MSG_VALIDATION_LENGTH_512);
        MW.pressEscape();
        MW.shouldNotBe(visible);

        rootLogger.info("Check New Conversation invite guest");
        CONVERSATION_BTN_New.click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        fillField(MW_CONVERSATION_INPUT_Follower, randomGuest);
        sleep(250);

        MW_CONVERSATION_BTN_INVITE.shouldBe(visible);
        MW_CONVERSATION_BTN_ADD_GUEST.shouldBe(visible).click();
        checkText(randomGuest);
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Check New Conversation invite follower");
        CONVERSATION_BTN_New.click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        fillField(MW_CONVERSATION_INPUT_Follower, randomFollower);
        sleep(250);

        MW_CONVERSATION_BTN_ADD_GUEST.shouldBe(visible);
        MW_CONVERSATION_BTN_INVITE.shouldBe(visible).click();
        checkText(randomFollower);
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Test passed");
    }
    @Test
    public void createProject_B_addTeamConversation() {
        rootLogger.info("Create thread in private zone");
        CONVERSATION_BTN_Team.shouldBe(visible);

        CONVERSATION_BTN_New.click();
        waitForModalWindow(TITLE_MW_CONVERSATION);
        fillField(MW_CONVERSATION_INPUT_Subject, "TEAM_THREAD IN PRIVATE ZONE");
        MW_BTN_CREATE.click();
        MW.shouldNotBe(visible);
        sleep(2000);
        CONVERSATION_EDIT_TITLE.click();
        CONVERSATION_FIELD_TITLE.shouldHave(value("TEAM_THREAD IN PRIVATE ZONE"));
        CONVERSATION_FIELD_TITLE.pressEscape();
        CONVERSATION_TITLE.shouldHave(text("TEAM_THREAD IN PRIVATE ZONE"));

        CONVERSATION_LABEL_ACTIVE_TAB.shouldHave(text(CONVERSATION_TEAM_TAB_NAME));

        CONVERSATION_INPUT_TEXT_COLLAPSED.click();
        CONVERSATION_TEXT_EDITOR.sendKeys("new message 1-st row");
        CONVERSATION_TEXT_EDITOR.shouldHave(text("new message 1-st row"));
        CONVERSATION_TEXT_EDITOR.pressEnter();
        CONVERSATION_TEXT_EDITOR.sendKeys("new message 2-nd row");
        CONVERSATION_TEXT_EDITOR.pressEnter();
        CONVERSATION_TEXT_EDITOR.sendKeys("new message 3-rd row");
        submitEnabledButton(CONVERSATION_BTN_POST);
//        $(byXpath("//*[@class='message-list']/li[1]//div[@class='message-holder']")).shouldHave(text("new message 1-st row"));
//        checkText("new message 1-st row");
        $(byXpath("//*[@class='message-list']/li[1]//div[@class='message-holder']")).isDisplayed();
        rootLogger.info("Delete message");
        CONVERSATION_MsgDelete.waitUntil(visible, 10000);
        CONVERSATION_MsgDelete.click();
        submitConfirmAction(TITLE_MW_DELETE_MESSAGE);
        $(byXpath("//*[@class='message-list']/li[1]//div[@class='message-holder']")).shouldNot(visible);
        rootLogger.info("Test passed");
    }
    @Test  //todo
    public void createProject_C_addExternalConversation() {


        rootLogger.info("Test passed");
    }

}