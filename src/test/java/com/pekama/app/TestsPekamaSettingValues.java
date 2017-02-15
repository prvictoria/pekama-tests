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
        holdBrowserAfterTest();
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
    public void statusCRUD(){
        String value = randomString(256);

        rootLogger.info("Open Matter type - " +TEST_MATTER_TYPE);
        checkText(TEST_MATTER_TYPE);
        $(byLinkText(TEST_MATTER_TYPE)).click();
        rootLogger.info("Check Matter statuses - ");
        scrollUp();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).shouldHave(text("Mark Statuses")).click();
        $(byLinkText("Mark Statuses")).shouldBe(visible);
        $(byLinkText("Mark Types")).shouldBe(visible);
        $(byLinkText("Sub Types")).shouldBe(visible);
        $(byLinkText("Event Types")).shouldBe(visible);
        $(byLinkText("Reference Types")).shouldBe(visible);
        $(byLinkText("Countries")).shouldBe(visible);
        $(byLinkText("Mark Statuses")).click();

        submitEnabledButton(SETTINGS_VALUES_ADD);

        rootLogger.info("Check MW Status validation");
        waitForModalWindow(MW_STATUS_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_SELECT_STATE.selectOptionByValue(PROJECT_STATUS_STATE_CLOSED);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);

        rootLogger.info("Create Status");
        value = randomString(20);
        waitForModalWindow(MW_STATUS_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_SELECT_STATE.selectOptionByValue(PROJECT_STATUS_STATE_CLOSED);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, PROJECT_STATUS_STATE_CLOSED);

        rootLogger.info("Edit Status");
        valueGetRowEdit(value).click();
        value = randomString(15);
        waitForModalWindow(MW_STATUS_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_SELECT_STATE.selectOptionByValue(PROJECT_STATUS_STATE_ACTIVE);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, PROJECT_STATUS_STATE_ACTIVE);

        valueDelete(value);
        valueCheckRowIsDisplayed(value, false);
    }
    @Test
    public void typeCRUD(){
        rootLogger.info("Open Matter type - " +TEST_MATTER_TYPE);
        checkText(TEST_MATTER_TYPE);
        $(byLinkText(TEST_MATTER_TYPE)).click();
        rootLogger.info("Check Matter statuses - ");
        scrollUp();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).click();
        $(byLinkText("Mark Statuses")).shouldBe(visible);
        $(byLinkText("Mark Types")).shouldBe(visible).click();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).shouldHave(text("Mark Types"));

        submitEnabledButton(SETTINGS_VALUES_ADD);
        String value = randomString(256);
        rootLogger.info("Check MW Type validation");
        waitForModalWindow(MW_TYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);

        value = randomString(0);
        rootLogger.info("Check MW Type validation");
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_BLANK_FIELD);

        value = randomString(10);
        rootLogger.info("Check MW Type validation");
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText("Please, choose either at least one relevant submatter type or the \"relevant to all\" option");

        rootLogger.info("Create Type - relevant to ALL");
        value = randomString(20);
        waitForModalWindow(MW_TYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_RELEVANT_ALL.setSelected(true);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, "ALL");

        rootLogger.info("Edit Type - set relevant to");
        valueGetRowEdit(value).click();
        value = randomString(15);
        waitForModalWindow(MW_TYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_RELEVANT_ALL.setSelected(false);
        fillField(MW_STATUS_INPUT_RELEVANT, Countries.PITCAIRN_ISLANDS.getValue());
        CSS_SelectHighlighted.click();
        MW.click();
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, Countries.PITCAIRN_ISLANDS.getValue()+";");

        valueDelete(value);
        valueCheckRowIsDisplayed(value, false);
    }
    @Test
    public void subtypeCRUD(){
        rootLogger.info("Open Matter type - " +TEST_MATTER_TYPE);
        checkText(TEST_MATTER_TYPE);
        $(byLinkText(TEST_MATTER_TYPE)).click();
        rootLogger.info("Check Matter statuses - ");
        scrollUp();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).click();
        $(byLinkText("Mark Statuses")).shouldBe(visible);
        $(byLinkText("Sub Types")).shouldBe(visible).click();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).shouldHave(text("Sub Types"));

        submitEnabledButton(SETTINGS_VALUES_ADD);
        String value = randomString(256);
        rootLogger.info("Check MW Sub Type validation");
        waitForModalWindow(MW_SUBTYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);

        value = randomString(0);
        rootLogger.info("Check MW Sub Type validation");
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_BLANK_FIELD);

        value = randomString(10);
        rootLogger.info("Check MW Sub Type validation");
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText("Please, choose either at least one relevant submatter type or the \"relevant to all\" option");

        rootLogger.info("Create Sub Type - relevant to ALL");
        value = randomString(20);
        waitForModalWindow(MW_SUBTYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_RELEVANT_ALL.setSelected(true);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, "ALL");

        rootLogger.info("Edit Sub Type - set relevant to");
        valueGetRowEdit(value).click();
        value = randomString(15);
        waitForModalWindow(MW_SUBTYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_RELEVANT_ALL.setSelected(false);
        fillField(MW_STATUS_INPUT_RELEVANT, Countries.PITCAIRN_ISLANDS.getValue());
        CSS_SelectHighlighted.click();
        MW.click();
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, Countries.PITCAIRN_ISLANDS.getValue()+";");

        valueDelete(value);
        valueCheckRowIsDisplayed(value, false);
    }
    @Test
    public void eventTypeCRUD(){
        rootLogger.info("Open Matter type - " +TEST_MATTER_TYPE);
        checkText(TEST_MATTER_TYPE);
        $(byLinkText(TEST_MATTER_TYPE)).click();
        rootLogger.info("Check Matter statuses - ");
        scrollUp();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).click();
        $(byLinkText("Mark Statuses")).shouldBe(visible);
        $(byLinkText("Event Types")).shouldBe(visible).click();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).shouldHave(text("Event Types"));

        submitEnabledButton(SETTINGS_VALUES_ADD);
        String value = randomString(256);
        rootLogger.info("Check MW Event Type validation");
        waitForModalWindow(MW_EVENT_TYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);

        value = randomString(0);
        rootLogger.info("Check MW Event Type validation");
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_BLANK_FIELD);

        value = randomString(10);
        rootLogger.info("Check MW Event Type validation");
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText("Please, choose either at least one relevant submatter type or the \"relevant to all\" option");

        rootLogger.info("Create Event Type - relevant to ALL");
        value = randomString(20);
        waitForModalWindow(MW_EVENT_TYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_RELEVANT_ALL.setSelected(true);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, "ALL");

        rootLogger.info("Edit Event Type - set relevant to");
        valueGetRowEdit(value).click();
        value = randomString(15);
        waitForModalWindow(MW_EVENT_TYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        MW_STATUS_RELEVANT_ALL.setSelected(false);
        fillField(MW_STATUS_INPUT_RELEVANT, "Paris Convention");
        CSS_SelectHighlighted.click();
        MW.click();
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, "Paris Convention;");

        valueDelete(value);
        valueCheckRowIsDisplayed(value, false);
    }
    @Test
    public void referenceTypeCRUD(){
        rootLogger.info("Open Matter type - " +TEST_MATTER_TYPE);
        checkText(TEST_MATTER_TYPE);
        $(byLinkText(TEST_MATTER_TYPE)).click();
        rootLogger.info("Check Matter statuses - ");
        scrollUp();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).click();
        $(byLinkText("Mark Statuses")).shouldBe(visible);
        $(byLinkText("Reference Types")).shouldBe(visible).click();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).shouldHave(text("Reference Types"));

        submitEnabledButton(SETTINGS_VALUES_ADD);
        String value = randomString(256);
        rootLogger.info("Check MW Reference Type validation");
        waitForModalWindow(MW_REFERENCE_TYPE_TITLE);
        // MW_BTN_OK.shouldBe(disabled); todo - BUG
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);

        value = randomString(0);
        rootLogger.info("Check MW Reference Type validation");
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_BLANK_FIELD);

        rootLogger.info("Create Reference Type");
        value = randomString(20);
        waitForModalWindow(MW_REFERENCE_TYPE_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_VALUE, value);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);

        rootLogger.info("Edit Reference Type");
        valueGetRowEdit(value).click();
        value = randomString(15);
        waitForModalWindow(MW_REFERENCE_TYPE_TITLE);
        //MW_BTN_OK.shouldBe(disabled); todo - BUG
        fillField(MW_STATUS_VALUE, value);
        MW.click();
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);

        valueDelete(value);
        valueCheckRowIsDisplayed(value, false);
    }
    @Test
    public void definingCRUD(){
        rootLogger.info("Open Matter type - " +TEST_MATTER_TYPE);
        checkText(TEST_MATTER_TYPE);
        $(byLinkText(TEST_MATTER_TYPE)).click();
        rootLogger.info("Check Matter statuses - ");
        scrollUp();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).click();
        $(byLinkText("Mark Statuses")).shouldBe(visible);
        $(byLinkText("Countries")).shouldBe(visible).click();
        SETTINGS_VALUES_DROPDOWN.waitUntil(visible, 20000).shouldHave(text("Countries"));

        submitEnabledButton(SETTINGS_VALUES_ADD);
        String value = "10";
        rootLogger.info("Check MW Defining validation");
        waitForModalWindow(MW_COUNTRY_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_INPUT_NUMBER, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_REQUIRED_FIELD, 2);

        value = randomString(256);
        rootLogger.info("Check MW Defining validation");
        waitForModalWindow(MW_COUNTRY_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_INPUT_COUNTRY, value);
        fillField(MW_STATUS_INPUT_CODE, value);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        checkText(ERROR_MSG_VALIDATION_LENGTH_2);

        rootLogger.info("Create Defining");
        value = randomString(20);
        waitForModalWindow(MW_COUNTRY_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_INPUT_COUNTRY, value);
        fillField(MW_STATUS_INPUT_CODE, "AA");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, "AA");

        rootLogger.info("Edit Defining");
        valueGetRowEdit(value).click();
        value = randomString(15);
        waitForModalWindow(MW_COUNTRY_TITLE);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_STATUS_INPUT_COUNTRY, value);
        fillField(MW_STATUS_INPUT_CODE, "zz");
        MW.click();
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        valueCheckRowIsDisplayed(value, true);
        valueCheckStatusState(value, "zz");

        valueDelete(value);
        valueCheckRowIsDisplayed(value, false);
    }
}
