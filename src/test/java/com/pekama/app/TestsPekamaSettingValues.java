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
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaSettingValues {
    static final Logger rootLogger = LogManager.getRootLogger();
    private final String TEST_USER_LOGIN = User1.GMAIL_EMAIL.getValue();
    private final String TEST_USER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private final String TEST_MATTER_TYPE = CaseType.TRADEMARK.getValue();

    @Before
    public void before() {
        holdBrowserAfterTest(true);
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                TEST_USER_LOGIN,
                TEST_USER_PASSWORD,
                URL_VALUES);
    }
    @After
    public void after() {
        open(URL_Logout);
    }

    @Test
    public void crudStatus(){
        String value1 = "A-status";
        //String value2 = "Z-status";

        rootLogger.info("Open Matter type - " +TEST_MATTER_TYPE);
        checkText(TEST_MATTER_TYPE);
        $(byLinkText(TEST_MATTER_TYPE)).click();
        rootLogger.info("Check Matter statuses - ");
        submitEnabledButton(SETTINGS_VALUES_ADD);
        waitForModalWindow(MW_STATUS_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value1);
        MW_STATUS_SELECT_STATE.selectOptionByValue(PROJECT_STATUS_STATE_ACTIVE);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);

        valueDelete(value1);


    }



}
