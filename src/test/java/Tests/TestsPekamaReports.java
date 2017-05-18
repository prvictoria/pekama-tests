package Tests;
import Page.TestsCredentials.*;
import Steps.*;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;

import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.TestsCredentials.Countries.*;
import static Page.TestsCredentials.Countries.PITCAIRN_ISLANDS;
import static Page.TestsStrings.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.MessagesValidator.ValidationReport.*;
import static Steps.ObjectContact.contactType.COMPANY;
import static Steps.ObjectContact.contactType.PERSON;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.*;
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
public class TestsPekamaReports {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String TEST_USER_LOGIN = User3.GMAIL_EMAIL.getValue();
    private static final String TEST_USER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();

    private static final String GMAIL_LOGIN = TEST_USER_LOGIN;
    private static final String GMAIL_PASSWORD = User3.GMAIL_PASSWORD.getValue();
    @Rule
    public Timeout tests = Timeout.seconds(600);
    private static boolean skipBefore = false;

    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        MessagesIMAP emailTask = new MessagesIMAP();
        emailTask.imapSearchEmailDeleteAll(
                GMAIL_LOGIN,
                GMAIL_PASSWORD);
    }
    @Before
    public void login() {
        if(skipBefore==false) {
            clearBrowserCache();
            User user = new User();
            user.loginByURL(TEST_USER_LOGIN, TEST_USER_PASSWORD, URL_LogIn);
        }
        else {rootLogger.info("Before was skipped");}
    }

    @Test //1-st test in stack
    public void reports_delete_mailing_lists(){
        String thisMailingListName;

        openPageWithSpinner(URL_ReportsProjects);
        thisMailingListName = "Projects Test Mailing List";
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

        thisMailingListName = "Contacts Test unsubscribe link";
        mailingListDetectAndDelete(thisMailingListName);
    }

    @Test @Category(AllEmailsTests.class)
    public void projects_send_report_A_SendReport() {
        skipBefore =true;
        String thisMailingListName = "Projects Test Mailing List";
        rootLogger.info("Open ProjectValues reports, opened URL - " + URL_ReportsProjects);
        openPageWithSpinner(URL_ReportsProjects);

        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
    }
    @Test @Category({AllEmailsTests.class, AllImapTests.class})
    public void projects_send_report_B_CheckEmail() {
        String thisMailingListName = "Projects Test Mailing List";
        //if (thisMailingListName==null){Assert.fail("Case not created");}
        rootLogger.info("Check report email");
        String login = GMAIL_LOGIN;
        String password = GMAIL_PASSWORD;
        String reportSchedule = "999";
        String reportName = thisMailingListName;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(login, password, reportSchedule, reportName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
        skipBefore = false;
    }

    @Test @Category(AllEmailsTests.class)
    public void tasks_send_report_A_Send() {
        String thisMailingListName = "Tasks Test Mailing List";
        rootLogger.info("Open Tasks reports, opened URL - " + URL_ReportsTasks);
        openPageWithSpinner(URL_ReportsTasks);
        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        mailingListSendReport(thisMailingListName);
        skipBefore = true;
    }
    @Test @Category({AllEmailsTests.class, AllImapTests.class})
    public void tasks_send_report_B_CheckEmail() {
        String thisMailingListName = "Tasks Test Mailing List";

        //if (thisMailingListName==null){Assert.fail("Case not created");}
        rootLogger.info("Check report email");
        String login = GMAIL_LOGIN;
        String password = GMAIL_PASSWORD;
        String reportSchedule = "999";
        String reportName = thisMailingListName;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(login, password, reportSchedule, reportName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
        skipBefore = false;
    }

    @Test @Category(AllEmailsTests.class)
    public void events_send_report_A_SendReport() {
        String thisMailingListName = "Events Test Mailing List";
        rootLogger.info("Open Event reports, opened URL - " + URL_ReportsEvents);
        openPageWithSpinner(URL_ReportsEvents);

        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        skipBefore = true;
    }
    @Test @Category({AllEmailsTests.class, AllImapTests.class})
    public void events_send_report_B_CheckEmail() {
        String thisMailingListName = "Events Test Mailing List";
        //if (thisMailingListName==null){Assert.fail("Case not created");}
        rootLogger.info("Check report email");
        String login = GMAIL_LOGIN;
        String password = GMAIL_PASSWORD;
        String reportSchedule = "999";
        String reportName = thisMailingListName;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(login, password, reportSchedule, reportName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
        skipBefore = false;
    }

    @Test @Category(AllEmailsTests.class)
    public void charges_send_report_A_SendReport() {
        String thisMailingListName = "Charges Test Mailing List";
        rootLogger.info("Open Charges reports, opened URL - " + URL_ReportsCharges);
        openPageWithSpinner(URL_ReportsCharges);

        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        skipBefore = true;
    }
    @Test @Category({AllEmailsTests.class, AllImapTests.class})
    public void charges_send_report_B_CheckEmail() {
        String thisMailingListName = "Charges Test Mailing List";
        rootLogger.info("Check email - report");
        //if (thisMailingListName==null){Assert.fail("Case not created");}
        rootLogger.info("Check report email");
        String login = GMAIL_LOGIN;
        String password = GMAIL_PASSWORD;
        String reportSchedule = "999";
        String reportName = thisMailingListName;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(login, password, reportSchedule, reportName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
        skipBefore = false;
    }

    @Test @Category(AllEmailsTests.class)
    public void contacts_send_report_A_SendReport() {
        String thisMailingListName = "Contacts Test Mailing List";
        rootLogger.info("Open Contacts reports, opened URL - "+URL_ReportsContacts);
        openPageWithSpinner(URL_ReportsContacts);

        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        skipBefore = true;
    }
    @Test @Category({AllEmailsTests.class, AllImapTests.class})
    public void contacts_send_report_B_CheckEmail() {
        String thisMailingListName = "Contacts Test Mailing List";
        //if (thisMailingListName==null){Assert.fail("Case not created");}
        rootLogger.info("Check report email");
        String login = GMAIL_LOGIN;
        String password = GMAIL_PASSWORD;
        String reportSchedule = "999";
        String reportName = thisMailingListName;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(login, password, reportSchedule, reportName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
        skipBefore = false;
    }

    @Test @Category({AllEmailsTests.class, AllImapTests.class})
    public void contacts_report_unsubscribe() {
        String thisMailingListName = "Contacts Test unsubscribe link";
        rootLogger.info("Open ProjectValues reports, opened URL - "+URL_ReportsProjects);
        openPageWithSpinner(URL_ReportsContacts);

        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);

        //if (thisMailingListName==null){Assert.fail("Case not created");}
        rootLogger.info("Check report email");
        String login = GMAIL_LOGIN;
        String password = GMAIL_PASSWORD;
        String reportSchedule = "999";
        String reportName = thisMailingListName;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(login, password, reportSchedule, reportName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
        skipBefore = false;

        rootLogger.info("Check email - report usubscribe link");
        SelenideElement EMAIL_SUBJECT = EMAIL_REPORT_SUBJECT;
        String link = unsubscribeLink;

        openUrlWithBaseAuth(link);
        sleep(5000);
        $$(byText("You will no longer receive this report.")).filterBy(visible).shouldHaveSize(1);

        rootLogger.info("Check checkbox value in mailing list");
        openUrlWithBaseAuth(URL_ReportsContacts);
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
    public void projects_sort_test(){
        String projectName = null;
        openPageWithSpinner(URL_ReportsProjects);
        rootLogger.info("Create project");
        submitEnabledButton(REPORTS_BTN_NEW_PROJECT);
        projectName = submitMwNewProject();
        openPageWithSpinner(URL_ReportsProjects);
        REPORTS_SORT_BY_NONE.waitUntil(visible, 15000).click();
        REPORTS_SORT_BY_LAST_CREATED.shouldBe(visible).click();
        String actualTitle = REPORTS_LIST_PROJECT_TILE_ROW1.getText();
        rootLogger.info("Actual title in row: " + actualTitle);
        REPORTS_LIST_PROJECT_TILE_ROW1.shouldHave(matchText(projectName));
    }

    @Test
    public void projects_delete_all(){
        deleteAllProjects();
        rootLogger.info("Test passed");
    }
    @Test
    public void tasks_delete_all(){
        deleteAllTasks();
        rootLogger.info("Test passed");
    }
    @Test
    public void events_delete_all(){
        deleteAllEvents();
        rootLogger.info("Test passed");
    }
    @Test
    public void charges_delete_all(){
        deleteAllCharges();
        $$(byText(PLACEHOLDER_NO_DATA)).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        rootLogger.info("Test passed");
    }
    @Test
    public void contacts_check_gui_and_delete_all(){
        deleteAllContacts();
        $$(byText(PLACEHOLDER_NO_DATA)).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        rootLogger.info("All contacts were deleted");

        REPORTS_BTN_List.shouldBe(visible).shouldBe(enabled).shouldHave(attribute("class", "btn btn-gray btn-active"));
        REPORTS_BTN_Report.shouldBe(visible).shouldBe(enabled).shouldHave(attribute("class", "btn btn-gray"));
        REPORTS_BTN_IMPORT.shouldBe(visible).shouldBe(enabled);
        REPORTS_BTN_ADD_CONTACT.shouldBe(visible).shouldBe(enabled);
        rootLogger.info("Test passed");
    }

    @Test
    public void contacts_a_validation_modal_person(){
        ObjectContact contact = new ObjectContact();
        contact.createPerson(REPORT, null,
                null, null,
                null, null,
                null, null,null,
                null, null,
                null, null, null);
        checkText("This field is required when contact type is 'Person'.", 2);
        submitEnabledButton(MW_BTN_CANCEL);
        rootLogger.info("Validation test Passed");
    }
    @Test
    public void contacts_a_validation_modal_company(){
        ObjectContact contact = new ObjectContact();
        contact.createCompany(REPORT,  null,
                null, null,
                null, null, null, null,
                null, null, null);
        checkText("This field is required when contact type is 'Company'.");
        submitEnabledButton(MW_BTN_CANCEL);
        rootLogger.info("Validation test Passed");
    }
    @Test
    public void contacts_a_validation_modal_max_length(){
        String string = randomString(256);
        ObjectContact contact = new ObjectContact();
        contact.createPerson(REPORT, string,
                string, string,
                null, string, string, string,
                string, string, string,
                string, string, null);
        checkText("Ensure this field has no more than 255 characters.", 4);
        checkText("Ensure this field has no more than 100 characters.", 2);
        checkText("Ensure this field has no more than 254 characters.");
        checkText("Enter a valid email address.");
        checkText("Ensure this field has no more than 20 characters.", 4);
        submitEnabledButton(MW_BTN_CANCEL);
        rootLogger.info("Validation test Passed");
    }
    @Test
    public void contacts_a_validation_form_person(){
        deleteAllContacts();
        ObjectContact contact = new ObjectContact();
        contact.createCompany(REPORT, "Company1",
                null, null,
                null, null,null,
                null, null,
                null, null);
        clickContactEdit(1);
        String string = randomString(256);
        contact.editForm(1, "Person", string, string, string, null, string, string, string, string, string, string, string, string, null);
        checkText("Ensure this field has no more than 255 characters.", 4);
        checkText("Ensure this field has no more than 100 characters.", 2);
        checkText("Ensure this field has no more than 254 characters.");
        checkText("Enter a valid email address.");
        checkText("Ensure this field has no more than 20 characters.", 4);
        rootLogger.info("Validation test Passed");
    }
    @Test
    public void contacts_a_validation_form_company(){
        deleteAllContacts();
        ObjectContact contact = new ObjectContact();
        contact.createPerson(REPORT, null, "Name", "Surname", null,
                null, null,
                null, null,null,
                null, null,
                null, null);
        clickContactEdit(1);
        String string = randomString(256);
        contact.editForm(1, "Company",  string, null, null, null, string, string, string, string, string, string, string, string, null);
        checkText("Ensure this field has no more than 255 characters.", 4);
        checkText("Ensure this field has no more than 254 characters.");
        checkText("Enter a valid email address.");
        checkText("Ensure this field has no more than 20 characters.", 4);
        rootLogger.info("Validation test Passed");
    }
    @Test
    public void contacts_a_validation_form_person_separate_fields(){
        deleteAllContacts();
        ObjectContact contact = new ObjectContact();
        contact.createPerson(REPORT, null, "Name", "Surname", null,
                null, null,
                null, null,null,
                null, null,
                null, null);
        clickContactEdit(1);
        String string = randomString(256);
        contact.editForm(1, "Person",  "", string, string, null, "", "", "", "", "", "", "", "", null);
        checkText("Ensure this field has no more than 100 characters.", 2);

        refresh();
        clickContactEdit(1);
        contact.editForm(1, "Person",  "", "", "", null, "", string, string, string, "", string, "", "", null);
        checkText("Ensure this field has no more than 20 characters.", 4);

        refresh();
        clickContactEdit(1);
        contact.editForm(1, "Person",  "", "", "", null, string, "", "", "", "", "", string, string, null);
        checkText("Ensure this field has no more than 255 characters.", 2);
        checkText("Ensure this field has no more than 254 characters.");
        checkText("Enter a valid email address.");
        rootLogger.info("Validation test Passed");
    }
    @Test
    public void contacts_a_edit_form_person(){
        deleteAllContacts();
        ObjectContact contact = new ObjectContact();
        contact.createCompany(REPORT, "Company1",
                null, null,
                null, null,null,
                null, null,
                null, null);
        int listSize = REPORTS_LIST_ROWS.size();
        clickContactEdit(1);
        contact.editForm(1, "Person", "Private", "Name", "Surname", null, "123456@email.com", "8017-12-45-34", "8029-78-89-79", "01-4565645645654", "Street", "zip", "London", "Center", "United Kingdom");
        clickContactDelete(1);
        REPORTS_LIST_ROWS.shouldHaveSize(listSize-1);
        rootLogger.info("Contact CRUD test Passed");
    }
    @Test
    public void contacts_a_select_company_in_form_person(){
        deleteAllContacts();

        ObjectContact company = new ObjectContact();
        company.createCompany(REPORT, "Company",
                null, null,
                null, null,null,
                null, null,
                null, null);
        ObjectContact person = new ObjectContact();
        person.createPerson(REPORT, null,
                "Name", "Surname", null,
                null, null,
                null, null,null,
                null, null,
                null, null);
        clickContactEdit(2);
        person.editForm(2, "Person", null, "Name", "Surname", company.contactLegalEntity, null, null, null, null, null, null, null, null, null);

        refresh();
        reportsCheckContactRow(PERSON, 2, person, null, null,null);
        rootLogger.info("Contact add company link");
    }
    @Test
    public void contacts_a_select_company_in_modal_person(){
        deleteAllContacts();

        ObjectContact company = new ObjectContact();
        company.createCompany(REPORT, "Company",
                null, null,
                null, null,null,
                null, null,
                null, null);
        ObjectContact person = new ObjectContact();
        person.createPerson(REPORT, null,
                "Name", "Surname", company.contactLegalEntity,
                null, null,
                null, null,null,
                null, null,
                null, null);
        refresh();
        reportsCheckContactRow(PERSON, 2, person, null, null,null);
        rootLogger.info("Contact add company link");
    }
    @Test
    public void contacts_create_project(){
        deleteAllContacts();

        ObjectContact company = new ObjectContact();
        company.createCompany(REPORT, "Company",
                null, null,
                null, null,null,
                null, null,
                null, null);
        clickContactNewProject(1);
        submitMwNewProject("from contacts report");
        String projectUrl = getActualUrl();

        openPageWithSpinner(URL_ReportsContacts);
        reportsCheckContactRow(COMPANY, 1, company, "1 project", null,1);
        rootLogger.info("Contact add company link");
    }
    @Test
    public void contacts_z_merge() throws IOException {
        String ContactEmail1 = "email01@new.test";
        String ContactEmail2 = "email02@new.test";
        String Contact1NameSurname = nameContactName+"Z"+" "+nameContactSurname+"Z";
        String Contact2NameSurname = nameContactName+"A"+" "+nameContactSurname+"A";

        deleteAllContacts();

        rootLogger.info("Check default sort by name");
        REPORTS_SORT_BY_NAME.waitUntil(visible, 30000);

        rootLogger.info("Create 1st contact");
        ObjectContact contact1 = new ObjectContact();
        contact1.createPerson(REPORT,  null,
                nameContactName+"Z", nameContactSurname+"Z",
                null, ContactEmail1, null, null,
                null, null, null,
                null, null, PITCAIRN_ISLANDS.getValue());
        rootLogger.info("Check 1-st contact row");
        reportsCheckContactRow(
                1,
                nameContactName+"Z",
                nameContactSurname+"Z",
                ContactEmail1,
                PITCAIRN_ISLANDS.getValue());

        rootLogger.info("Create 2nd contact");
        ObjectContact contact2 = new ObjectContact();
        contact2.createPerson(REPORT, null,
                nameContactName+"A", nameContactSurname+"A",
                null, ContactEmail2, null, null,
                null, null, null,
                null, null, NETHERLANDS_ANTILES.getValue());
        rootLogger.info("Check 1-st contact row - default sort by name - ascending");
        reportsCheckContactRow(
                1,
                nameContactName+"A",
                nameContactSurname+"A",
                ContactEmail2,
                NETHERLANDS_ANTILES.getValue());

        REPORTS_ALL_CHECKBOX.click();
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

        rootLogger.info("Check merge result if 1-st contact present");
        reportsCheckContactRow(
                1,
                nameContactName+"Z",
                nameContactSurname+"Z",
                ContactEmail1,
                PITCAIRN_ISLANDS.getValue());

    }
    @Test
    public void contacts_y_import_ContactsMaxValue() {
        deleteAllContacts();
        callImportContactModal();
        submitImportContactsModal("ContactsMaxValue.csv", null);
        sleep(3000);
        refresh();
        REPORTS_LIST_ROWS.shouldHaveSize(1);

    }
    @Test
    public void contacts_y_import_ContactsMinValue() {
        deleteAllContacts();
        callImportContactModal();
        submitImportContactsModal("ContactsMinValue.csv", null);
        sleep(3000);
        refresh();
        REPORTS_LIST_ROWS.shouldHaveSize(1);
    }
    @Test
    public void contacts_y_import_ContactsValidationNotCsv() {
        callImportContactModal();
        submitImportContactsModal("png.png", "file: Not a valid CSV file");
    }
    @Test
    public void contacts_y_import_ContactsValidationNameSurname() {
        callImportContactModal();
        submitImportContactsModal("ContactsValidationNameSurname.csv", "file: Multiple headers: SAZipCode. ");
    }
}
