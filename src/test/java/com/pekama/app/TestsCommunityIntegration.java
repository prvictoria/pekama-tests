package com.pekama.app;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */

import Page.TestsCredentials;
import Page.TestsCredentials.CaseType;
import Page.TestsCredentials.Countries;
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.CommunityDashboard.*;
import static Page.CommunityWizard.*;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsStrings.*;
import static Page.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.Messages.msgCaseCancelled;
import static Steps.StepsCommunity.*;
import static Steps.StepsPekama.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.pekama.app.AllTestsRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityIntegration {
    static final Logger rootLogger = LogManager.getRootLogger();

    private static String testProjectName;
    private static String testProjectURL;
    private final static String TEST_CASE_TYPE = CaseType.TRADEMARK.getValue();
    private final static String TEST_CASE_COUNTRY = Countries.PITCAIRN_ISLANDS.getValue();
    private final static String TEST_CASE_NAME = "CUSTOM_NAME"+randomString(10);

    private final static String REQUESTER_EMAIL = TestsCredentials.User3.GMAIL_EMAIL.getValue();
    private final static String REQUESTER_PEKAMA_PASSWORD = TestsCredentials.User3.PEKAMA_PASSWORD.getValue();
    private final static String REQUESTER_NAME = TestsCredentials.User3.NAME.getValue();
    private final static String REQUESTER_SURNAME = TestsCredentials.User3.SURNAME.getValue();
    private final static String REQUESTER_FULL_TEAM_NAME = TestsCredentials.User3.FULL_TEAM_NAME.getValue();
    private final static String REQUESTER_NAME_SURNAME = TestsCredentials.User3.NAME_SURNAME.getValue();

    private final static String EXPERT_EMAIL = TestsCredentials.User2.GMAIL_EMAIL.getValue();
    private final static String EXPERT_PEKAMA_PASSWORD = TestsCredentials.User2.PEKAMA_PASSWORD.getValue();
    private final static String EXPERT_NAME = TestsCredentials.User2.NAME.getValue();
    private final static String EXPERT_SURNAME = TestsCredentials.User2.SURNAME.getValue();
    private final static String EXPERT_TEAM_NAME = TestsCredentials.User2.TEAM_NAME.getValue();
    private final static String EXPERT_FULL_TEAM_NAME = TestsCredentials.User2.FULL_TEAM_NAME.getValue();
    private static final String EXPERT_NAME_SURNAME = TestsCredentials.User2.NAME_SURNAME.getValue();
    private final static String INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";


    @BeforeClass
    public static void beforeClass() {
        assertionMode();
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                REQUESTER_EMAIL,
                REQUESTER_PEKAMA_PASSWORD,
                URL_LogIn);
    }
    @Before
    public void before() {
        open(URL_Dashboard);
        rootLogger.info("Create project");
        DASHBOARD_BTN_NEW_PROJECT.waitUntil(visible, 20000).click();
        testProjectName = createProject();
        testProjectURL = getActualUrl();
    }
    @AfterClass
    public static void after() {
        open(URL_Logout);
    }

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

    @Test
    public void createDraftCommunityCaseFormPekama() {
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
        submitCookie();
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);

        rootLogger.info("Create draft case");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);

        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);
        fillField(WIZARD_FIELD_CASE_NAME, TEST_CASE_NAME);
        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible);
        rootLogger.info("Case was created");




        rootLogger.info("Open Pekama");
        switchTo().window(PAGE_TITLE_PEKAMA);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Community");
        }
        String actualUrl = getActualUrl();
        Assert.assertEquals
                ("Opened url not same to the project url", testProjectURL, actualUrl);

        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).shouldBe(visible);

        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(TEST_CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_DRAFT));

        rootLogger.info("Check project members");
        PROJECT_TAB_CONTACTS.shouldBe(visible).click();
        checkText(OWNER); checkText(REQUESTER_FULL_TEAM_NAME);
        checkText(ADMIN); checkText(INTRODUCER_NAME);
        checkText(VIEWER); checkText(EXPERT_FULL_TEAM_NAME);

        rootLogger.info("Check redirect to Community after click case row");
        PROJECT_TAB_INFO.shouldBe(visible).click();
        TAB_INFO_COMMUNITY_CASE_ROW.click();
        sleep(2000);
        // TODO: 21-Feb-17 click ROW
        checkThatWindowsQtyIs(2);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Community");
        }
        BTN_SEND_INSTRUCTION.waitUntil(visible, 20000);
        sleep(1000);
        close();
        rootLogger.info("Test passed");
    }

    @Test
    public void createCaseAndCheckPekamaState() {
        if (testProjectName==null || testProjectURL==null){
            Assert.fail("Project not created for precondition");
        }
        checkText("No community cases.");
        TAB_INFO_COMMUNITY_TITLE.waitUntil(visible, 20000)
                .shouldHave(text("Services from the Pekama IP Community"));
        rootLogger.info("Check redirect to Community Wizard");
        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).click();
        checkThatWindowsQtyIs(2);
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("No redirect to Community");
        }
        submitCookie();
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);

        rootLogger.info("Create case");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);
        fillField(WIZARD_FIELD_CASE_NAME, TEST_CASE_NAME);
        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();
        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();
        WIZARD_BTN_INSTRUCT_NOW.shouldBe(visible).click();
        waitForModalWindow("Congratulations!");
        MW_CONGRATULATION_OK.click();
        MW.shouldNotBe(visible);
        sleep(2000);
        checkCaseNameFirstRow(TEST_CASE_NAME);
        rootLogger.info("Case was created");

        rootLogger.info("Open Pekama");
        sleep(2000);
        close();
        switchTo().window(PAGE_TITLE_PEKAMA);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Pekama");
        }

        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).shouldBe(visible);
        rootLogger.info("Check sent message");
        checkText(MSG_DEFAULT_SENT_INSTRUCTION);

        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(TEST_CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldHave(text("withdraw instructions"));
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_SENT));

        rootLogger.info("Check project members");
        PROJECT_TAB_CONTACTS.shouldBe(visible).click();
        checkText(OWNER); checkText(REQUESTER_FULL_TEAM_NAME);
        checkText(ADMIN); checkText(INTRODUCER_NAME);
        checkText(COLLABORATOR); checkText(EXPERT_FULL_TEAM_NAME);

        rootLogger.info("Test passed");
    }

    @Ignore
    @Test
    public void CancelAsRequester() {
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
        submitCookie();
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000)
                .shouldBe(disabled);

        rootLogger.info("Create case");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(EXPERT_TEAM_NAME);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);
        fillField(WIZARD_FIELD_CASE_NAME, TEST_CASE_NAME);
        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();
        WIZARD_BTN_NEXT.click();
        sleep(3000);
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();
        WIZARD_BTN_INSTRUCT_NOW.shouldBe(visible).click();
        waitForModalWindow("Congratulations!");
        MW_CONGRATULATION_OK.click();
        MW.shouldNotBe(visible);
        sleep(2000);
        checkCaseNameFirstRow(TEST_CASE_NAME);
        rootLogger.info("Case was created");
        rootLogger.info("Cansel case");
        cancelCase(TEST_CASE_NAME, true);
        rootLogger.info("Open Pekama");
        sleep(2000);
        close();
        switchTo().window(PAGE_TITLE_PEKAMA);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Pekama");
        }

        TAB_INFO_COMMUNITY_BTN_START_NEW.waitUntil(visible, 20000).shouldBe(visible);
        rootLogger.info("Check sent message");
        checkText(msgCaseCancelled(EXPERT_NAME));

        TAB_INFO_COMMUNITY_CASE_NAME.waitUntil(visible, 15000).shouldHave(text(TEST_CASE_NAME));
        TAB_INFO_COMMUNITY_CASE_TYPE.shouldHave(text(TEST_CASE_TYPE));
        TAB_INFO_COMMUNITY_CASE_ACTION.shouldNot(exist);
        TAB_INFO_COMMUNITY_CASE_STATUS.shouldHave(text(COMMUNITY_STATUS_CANCELLED));

        rootLogger.info("Check project members");
        PROJECT_TAB_CONTACTS.shouldBe(visible).click();
        checkText(OWNER); checkText(REQUESTER_FULL_TEAM_NAME);
        checkText(ADMIN); checkText(INTRODUCER_NAME);
        checkText(VIEWER); checkText(EXPERT_FULL_TEAM_NAME);

        rootLogger.info("Test passed");
    }

    @Ignore
    @Test
    public void WithdrawAsRequester() {

        rootLogger.info("");

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
    public void deleteProjectByOwnerIfCaseActive() {

        rootLogger.info("");

        rootLogger.info("Test passed");
    }

    @Test
    public void deleteProjectByOwnerIfCaseInactive() {

        rootLogger.info("");

        rootLogger.info("Test passed");
    }

    @Ignore
    @Test
    public void ChangeStatusesAsExpert() {

        rootLogger.info("");

        rootLogger.info("Test passed");
    }

    @Ignore
    @Test
    public void test() {

        rootLogger.info("");


        rootLogger.info("Test passed");
    }

}