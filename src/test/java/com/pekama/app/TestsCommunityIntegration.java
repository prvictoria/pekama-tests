package com.pekama.app;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Page.TestsCredentials;
import Steps.StepsPekama;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;

import static Page.CommunityWizard.WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityIntegration {
    static final Logger rootLogger = LogManager.getRootLogger();
    private final static String REQUESTER_USER_EMAIL = TestsCredentials.User3.GMAIL_EMAIL.getValue();
    private final static String REQUESTER_USER_PEKAMA_PASSWORD = TestsCredentials.User3.PEKAMA_PASSWORD.getValue();
    private final static String TEST_USER_FULL_TEAM_NAME = TestsCredentials.User3.FULL_TEAM_NAME.getValue();
    private final static String COLLABORATOR_TEAM_NAME = TestsCredentials.User1.TEAM_NAME.getValue();

    private final static String EXPERT_USER_EMAIL = TestsCredentials.User2.GMAIL_EMAIL.getValue();
    private final static String EXPERT_USER_PEKAMA_PASSWORD = TestsCredentials.User2.PEKAMA_PASSWORD.getValue();
    private static String testProjectName;
    private static String testProjectURL;

    @Before
    public void before() {
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                REQUESTER_USER_EMAIL,
                REQUESTER_USER_PEKAMA_PASSWORD,
                URL_LogIn);
        rootLogger.info("Create project");
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        testProjectName = createProject();
        testProjectURL = getActualUrl();
    }
    @After
    public void after() { }

    @Test
    public void checkRedirectToCommunityWizard() {
        if (testProjectName==null || testProjectURL==null){
            Assert.fail("Project not created for precondition");
        }
        checkText("No community cases.");
        TAB_INFO_COMMUNITY_TITLE.waitUntil(visible, 20000).shouldHave(text("Services from the Pekama IP Community"));
        rootLogger.info("Check redirect to Community Wizard");
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        checkThatWindowsQtyIs(2);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("No redirect to Community");
        }
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);
        rootLogger.info("Test passed");
    }

    @Ignore
    @Test  //todo
    public void createDraftCommunityCaseFormPekamaAndWitdraw() {
        if (testProjectName==null || testProjectURL==null){
            Assert.fail("Project not created for precondition");
        }
        checkText("No community cases.");
        TAB_INFO_COMMUNITY_TITLE.waitUntil(visible, 20000).shouldHave(text("Services from the Pekama IP Community"));
        rootLogger.info("Check redirect to Community Wizard");
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        checkThatWindowsQtyIs(2);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("No redirect to Community");
        }
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);
        rootLogger.info("Test passed");
    }

    @Ignore
    @Test  //todo
    public void createTwoCommunityCaseFormPekama() {
        TAB_INFO_COMMUNITY_TITLE.shouldHave(text("Services from the Pekama IP Community"));
        TAB_INFO_COMMUNITY_BTN_START_NEW.click();

        rootLogger.info("Test passed");
    }
    @Ignore
    @Test
    public void WithdrawAsRequester() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }
    @Ignore
    @Test
    public void CancelAsRequester() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }
    @Ignore
    @Test
    public void DeleteProjectIfCasePresent() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Ignore
    @Test
    public void ChangeStatusesAsExpert() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Ignore
    @Test
    public void testE_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

}