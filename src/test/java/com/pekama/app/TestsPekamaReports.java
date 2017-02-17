package com.pekama.app;
import Page.TestsCredentials.*;
import Steps.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReports {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String TEST_USER_LOGIN = User3.GMAIL_EMAIL.getValue();
    private String GMAIL_LOGIN = TEST_USER_LOGIN;

    @Before
    public void login() {
        holdBrowserAfterTest();
        rootLogger.info("Open URL - " +URL_Dashboard);
        httpAuthUrl(URL_Dashboard);
        StepsPekama login = new StepsPekama();
        login.submitLoginCredentials(TEST_USER_LOGIN);
        rootLogger.info("Redirect after login to - "+URL_Dashboard);
        sleep(1000);
    }
    @After
    public void logout(){open(URL_Logout);}

    @Test //1-st test in stack
    public void deleteAllMailingLists(){
        String thisMailingListName;

        openPageWithSpinner(URL_ReportsProjects);
        thisMailingListName = "Projects Test unsubscribe link";
        mailingListDetectAndDelete(thisMailingListName);

        thisMailingListName = "ProjectsTest Mailing List";
        mailingListDetectAndDelete(thisMailingListName);

        openPageWithSpinner(URL_ReportsTasks);
        thisMailingListName = "Tasks Test Mailing List";
        mailingListDetectAndDelete(thisMailingListName);

        openPageWithSpinner(URL_ReportsEvents);
        thisMailingListName = "Events Test Mailing List";
        mailingListDetectAndDelete(thisMailingListName);

        openPageWithSpinner(URL_ReportsCharges);
        thisMailingListName = "Charges Test Mailing List";
        mailingListDetectAndDelete(thisMailingListName);

        openPageWithSpinner(URL_ReportsContacts);
        thisMailingListName = "Contacts Test Mailing List";
        mailingListDetectAndDelete(thisMailingListName);
    }

    @Test
    public void sendProjectReport() {
        String thisMailingListName = "Projects Test Mailing List";
        rootLogger.info("Open ProjectValues reports, opened URL - "+URL_ReportsProjects);
        openPageWithSpinner(URL_ReportsProjects);

        rootLogger.info("Open Dropdown and create new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        rootLogger.info("Delete mailing list");
        mailingListDeleteReport(thisMailingListName);

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }

    @Test
    public void sendTasksReport() {
        String thisMailingListName = "Tasks Test Mailing List";
        rootLogger.info("Open Tasks reports, opened URL - "+URL_ReportsTasks);
        openPageWithSpinner(URL_ReportsTasks);

        rootLogger.info("Open Dropdown and create new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        rootLogger.info("Delete mailing list");
        mailingListDeleteReport(thisMailingListName);

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }

    @Test
    public void sendEventsReport() {
        String thisMailingListName = "Events Test Mailing List";
        rootLogger.info("Open Event reports, opened URL - "+URL_ReportsEvents);
        openPageWithSpinner(URL_ReportsEvents);

        rootLogger.info("Open Dropdown and create new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        rootLogger.info("Delete mailing list");
        mailingListDeleteReport(thisMailingListName);

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }
    @Test
    public void sendChargesReport() {
        String thisMailingListName = "Charges Test Mailing List";
        rootLogger.info("Open Charges reports, opened URL - "+URL_ReportsCharges);
        openPageWithSpinner(URL_ReportsCharges);

        rootLogger.info("Open Dropdown and create new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        rootLogger.info("Delete mailing list");
        mailingListDeleteReport(thisMailingListName);

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }
    @Test
    public void sendContactsReport() {
        String thisMailingListName = "Contacts Test Mailing List";
        rootLogger.info("Open Contacts reports, opened URL - "+URL_ReportsContacts);
        openPageWithSpinner(URL_ReportsContacts);

        rootLogger.info("Open Dropdown and create new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        rootLogger.info("Delete mailing list");
        mailingListDeleteReport(thisMailingListName);

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }
    @Test
    public void unsubscribeLink() {
        String thisMailingListName = "Projects Test unsubscribe link";
        rootLogger.info("Open ProjectValues reports, opened URL - "+URL_ReportsProjects);
        openPageWithSpinner(URL_ReportsProjects);

        rootLogger.info("Open Dropdown and create new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);

        rootLogger.info("Check email - report usubscribe link");
        SelenideElement EMAIL_SUBJECT = EMAIL_REPORT_SUBJECT;
        signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        rootLogger.info("Detect email");
        detectEmail(EMAIL_SUBJECT);
        rootLogger.info("Open email");
        openEmail(EMAIL_SUBJECT);
        rootLogger.info("Check Unsubscribe Link");
        String link = checkUnsubscribeLink();
        rootLogger.info("Delete email");
        deleteEmail();
        rootLogger.info("Empty trash");
        inboxEmptyTrash();
        rootLogger.info("Email deleted");
        open(GMAIL_URL_SIGN_OUT);

        open(link);
        sleep(5000);
        $$(byText("You will no longer receive this report.")).filterBy(visible).shouldHaveSize(1);

        rootLogger.info("Check checkbox value in mailing list");
        open(URL_ReportsProjects);
        sleep(3000);
        waitForSpinnerNotPresent();
        boolean checkboxValue = mailingListCheckboxValue(thisMailingListName);

            if (link == null)
            {Assert.fail("Unsubscribe Link not found");}
            if (checkboxValue==false)
            {Assert.fail("Checkbox sending interval is still - has ON value");}
        rootLogger.info("Test passed");
    }
    @Test
    public void objectProjectDeleteAll(){
        openPageWithSpinner(URL_ReportsProjects);
        rootLogger.info("Create project");
        submitEnabledButton(REPORTS_BTN_New);
        String projectName = createProject();
        openPageWithSpinner(URL_ReportsProjects);
        REPORTS_SORT_BY_NONE.waitUntil(visible, 15000).click();
        REPORTS_SORT_BY_LAST_CREATED.shouldBe(visible).click();
        String actualTitle = REPORTS_LIST_PROJECT_TILE_ROW1.getText();
        rootLogger.info("Actual title in row: "+actualTitle);
        REPORTS_LIST_PROJECT_TILE_ROW1.shouldHave(matchText(projectName));

        rootLogger.info("Delete all projects");
        REPORTS_AllCheckbox.setSelected(true);
        REPORTS_DELETE.click();
        submitConfirmAction();
        sleep(4000);
        checkText("Projects", 2);
        waitForSpinnerNotPresent();
        REPORTS_LIST_PROJECT_TILE_ROW1.shouldNotHave(matchText(projectName));
        rootLogger.info("Test passed");
    }
    @Test
    public void objectContactDelete(){
        openPageWithSpinner(URL_ReportsContacts);
        rootLogger.info("Check default sort by name");
        REPORTS_SORT_BY_NAME.waitUntil(visible, 30000);

        if ($(byLinkText(PLACEHOLDER_NO_DATA)).isDisplayed()==false){
            sleep(5000);}
        while ($(byText(PLACEHOLDER_NO_DATA)).isDisplayed()==false) {
            REPORTS_AllCheckbox.waitUntil(visible, 20000).click();
            REPORTS_DELETE.shouldBe(visible).click();
            submitConfirmAction();
            checkText(PLACEHOLDER_NO_DATA);
        }
        checkText(PLACEHOLDER_NO_DATA);
        rootLogger.info("All contacts were deleted");
    }

    @Test
    public void objectContactMerge(){
        String ContactEmail1 = "email01@new.test";
        String ContactEmail2 = "email02@new.test";
        String Contact1NameSurname = nameContactName+"Z"+" "+nameContactSurname+"Z";
        String Contact2NameSurname = nameContactName+"A"+" "+nameContactSurname+"A";

        rootLogger.info("Create 1st contact");
        openPageWithSpinner(URL_ReportsContacts);
        submitEnabledButton(REPORTS_BTN_AddContact);
        waitForModalWindow(TITLE_MW_CONTACT);
        fillField(MW_Contact_NAME, nameContactName+"Z");
        fillField(MW_Contact_SURNAME, nameContactSurname+"Z");
        fillField(MW_Contact_EMAIL, ContactEmail1);
        selectItemInDropdown(
                MW_Contact_SelectCountry,
                MW_Contact_InputCountry,
                Countries.PITCAIRN_ISLANDS.getValue());
        MW_Contact_SelectCountryName.shouldHave(text(Countries.PITCAIRN_ISLANDS.getValue()));
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        rootLogger.info("Check 1-st contact row");
        reportsCheckContactRow(
                1,
                nameContactName+"Z",
                nameContactSurname+"Z",
                ContactEmail1,
                Countries.PITCAIRN_ISLANDS.getValue());

        rootLogger.info("Create 2nd contact");
        submitEnabledButton(REPORTS_BTN_AddContact);
        waitForModalWindow(TITLE_MW_CONTACT);
        fillField(MW_Contact_NAME, nameContactName+"A");
        fillField(MW_Contact_SURNAME, nameContactSurname+"A");
        fillField(MW_Contact_EMAIL, ContactEmail2);
        selectItemInDropdown(
                MW_Contact_SelectCountry,
                MW_Contact_InputCountry,
                Countries.NETHERLANDS_ANTILES.getValue());
        MW_Contact_SelectCountryName.shouldHave(text(Countries.NETHERLANDS_ANTILES.getValue()));
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        rootLogger.info("Check 1-st contact row - default sort by name - ascending");
        reportsCheckContactRow(
                1,
                nameContactName+"A",
                nameContactSurname+"A",
                ContactEmail2,
                Countries.NETHERLANDS_ANTILES.getValue());

        REPORTS_AllCheckbox.click();
        rootLogger.info("Merge contacts, base - 1-st");
        REPORTS_MERGE.shouldBe(visible).click();
        waitForModalWindow("Merge Contacts");
        MW_BTN_OK.shouldBe(disabled);
        selectItemInDropdown(
                MW_MergeContact_Select,
                MW_MergeContact_Input,
                Contact1NameSurname);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);

        rootLogger.info("Cech merge result if 1-st contact present");
        reportsCheckContactRow(
                1,
                nameContactName+"Z",
                nameContactSurname+"Z",
                ContactEmail1,
                Countries.PITCAIRN_ISLANDS.getValue());
 }
}
