package com.pekama.app;
import Page.PekamaTeamSettings;
import Page.TestsCredentials;
import Page.TestsCredentials.*;
import Steps.*;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import sun.util.logging.resources.logging;

import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaTemplates {
    static final Logger rootLogger = LogManager.getRootLogger();
    String TEAM = TestsCredentials.User3.TEAM_NAME.getValue();
    String TEST_USER_LOGIN = User3.GMAIL_EMAIL.getValue();
    String TEST_USER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();

    @Before
    public void login() {
        holdBrowserAfterTest(true);
        rootLogger.info("Open URL - " +URL_Dashboard);
        httpAuthUrl(URL_Dashboard);
        StepsPekama login = new StepsPekama();
        login.submitLoginCredentials(TEST_USER_LOGIN);
        rootLogger.info("Redirect after login to - "+URL_Dashboard);
        sleep(1000);
    }
//    @After
//    public void logout(){open(URL_Logout);}

    @Test
    public void templateCrudProject() {
        String projectName;
        rootLogger.info("Open URL - " +URL_TEMPLATES_PROJECT);
        openPageWithSpinner(URL_TEMPLATES_PROJECT);

        rootLogger.info("Validation - Title and Type");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_Project_Title, "1");
        MW_Project_Title.clear();
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_BLANK_FIELD);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Validation - Blank Title and Required defining");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        selectItemInDropdown(
                MW_Project_SelectType,
                MW_Project_InputType,
                CaseType.PATENT.getValue());
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_REQUIRED_FIELD);
        checkText("This field may not be null.");
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Validation - Title max length");
        projectName = randomString(256);
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_Project_Title, projectName);
        selectItemInDropdown(
                MW_Project_SelectType,
                MW_Project_InputType,
                CaseType.PATENT.getValue());
        selectItemInDropdown(
                MW_Project_SelectDefining,
                MW_Project_InputDefining,
                Countries.PITCAIRN_ISLANDS.getValue());
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        MW_BTN_CANCEL.click();
        MW.shouldNotBe(visible);

        rootLogger.info("Create template");
        projectName = "PROJECT_TEMPLATE_"+randomString(15);
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_PROJECT_TEMPLATE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_Project_Title, projectName);
        selectItemInDropdown(
                MW_Project_SelectType,
                MW_Project_InputType,
                CaseType.PATENT.getValue());
        selectItemInDropdown(
                MW_Project_SelectDefining,
                MW_Project_InputDefining,
                Countries.PITCAIRN_ISLANDS.getValue());
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        checkText(projectName);
        rootLogger.info("Delete template");
        if (SETTINGS_DELETE_X.isDisplayed()==false){
                Assert.fail("Project not created");
            }
            while (PekamaTeamSettings.SETTINGS_DELETE_X.isDisplayed()){
                SETTINGS_DELETE_X.click();
                submitConfirmAction();
            }
        rootLogger.info("Test passed");

    }
}
