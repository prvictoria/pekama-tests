package Tests;
import Steps.*;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
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
import static Steps.IMessagesValidator.ValidationReport.*;
import static Steps.ObjectContact.contactType.COMPANY;
import static Steps.ObjectContact.contactType.PERSON;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.ObjectUser.Users.USER_01;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.CollectionCondition.*;
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
    private static final ObjectUser user = new ObjectUser(newBuilder()).buildUser(USER_01);
    private static boolean skipBefore = false;
    
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        MessagesIMAP emailTask = new MessagesIMAP();
        emailTask.imapSearchEmailDeleteAll(
                user.email,
                user.passwordEmail);
    }
    @Before
    public void login() {
        if(skipBefore==false) {
            user.login();
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

    @Test 
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
    @Test 
    public void projects_send_report_B_CheckEmail() {
        skipBefore = false;
        String thisMailingListName = "Projects Test Mailing List";
        rootLogger.info("Check report email");
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(user.email, user.passwordEmail, "999", thisMailingListName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
    }

    @Test 
    public void tasks_send_report_A_Send() {
        skipBefore = true;
        String thisMailingListName = "Tasks Test Mailing List";
        rootLogger.info("Open Tasks reports, opened URL - " + URL_ReportsTasks);
        openPageWithSpinner(URL_ReportsTasks);
        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        mailingListSendReport(thisMailingListName);
    }
    @Test 
    public void tasks_send_report_B_CheckEmail() {
        skipBefore = false;
        String thisMailingListName = "Tasks Test Mailing List";
        rootLogger.info("Check report email");
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(user.email, user.passwordEmail, "999", thisMailingListName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
    }

    @Test 
    public void events_send_report_A_SendReport() {
        skipBefore = true;
        String thisMailingListName = "Events Test Mailing List";
        rootLogger.info("Open Event reports, opened URL - " + URL_ReportsEvents);
        openPageWithSpinner(URL_ReportsEvents);

        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
    }
    @Test 
    public void events_send_report_B_CheckEmail() {
        skipBefore = false;
        String thisMailingListName = "Events Test Mailing List";
        rootLogger.info("Check report email");
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(user.email, user.passwordEmail, "999", thisMailingListName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
    }

    @Test 
    public void charges_send_report_A_SendReport() {
        skipBefore = true;
        String thisMailingListName = "Charges Test Mailing List";
        rootLogger.info("Open Charges reports, opened URL - " + URL_ReportsCharges);
        openPageWithSpinner(URL_ReportsCharges);

        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
    }
    @Test 
    public void charges_send_report_B_CheckEmail() {
        skipBefore = false;
        String thisMailingListName = "Charges Test Mailing List";
        rootLogger.info("Check email - report");
        rootLogger.info("Check report email");
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(user.email, user.passwordEmail, "999", thisMailingListName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
    }
   
    @Test 
    public void contacts_send_report_A_SendReport() {
        skipBefore = true;
        String thisMailingListName = "Contacts Test Mailing List";
        rootLogger.info("Open Contacts reports, opened URL - "+URL_ReportsContacts);
        openPageWithSpinner(URL_ReportsContacts);

        rootLogger.info("Open Dropdown and createPerson new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
    }
    @Test 
    public void contacts_send_report_B_CheckEmail() {
        skipBefore = false;
        String thisMailingListName = "Contacts Test Mailing List";
        rootLogger.info("Check report email");
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(user.email, user.passwordEmail, "999", thisMailingListName);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
    }

    @Test 
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
        String login = user.email;
        String password = user.passwordEmail;
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
    public void charges_delete_all(){
        deleteAllCharges();
        $$(byText(PLACEHOLDER_NO_DATA)).shouldHave(sizeGreaterThanOrEqual(1));
        rootLogger.info("Test passed");
    }
    @Test
    public void contacts_check_gui_and_delete_all(){
        deleteAllContacts();
        $$(byText(PLACEHOLDER_NO_DATA)).shouldHave(sizeGreaterThanOrEqual(1));
        rootLogger.info("All contacts were deleted");

        REPORTS_BTN_LIST.shouldBe(visible).shouldBe(enabled).shouldHave(attribute("class", "btn btn-gray btn-active"));
        REPORTS_BTN_REPORT.shouldBe(visible).shouldBe(enabled).shouldHave(attribute("class", "btn btn-gray"));
        REPORTS_BTN_IMPORT.shouldBe(visible).shouldBe(enabled);
        REPORTS_BTN_ADD_CONTACT.shouldBe(visible).shouldBe(enabled);
        rootLogger.info("Test passed");
    }

    @Test
    public void contacts_a_validation_modal_person(){
        ObjectContact contact = ObjectContact.newBuilder().build();
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
        ObjectContact contact = ObjectContact.newBuilder().build();
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
        ObjectContact contact = ObjectContact.newBuilder().build();
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
        ObjectContact contact = ObjectContact.newBuilder().build();
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
        ObjectContact contact = ObjectContact.newBuilder().build();
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
        ObjectContact contact = ObjectContact.newBuilder().build();
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
        ObjectContact contact = ObjectContact.newBuilder().build();
        contact.createCompany(REPORT, "Company1",
                null, null,
                null, null,null,
                null, null,
                null, null);
        REPORTS_LIST_ROWS.shouldHaveSize(1);
        clickContactEdit(1);
        contact.editForm(1, "Person", "Private", "Name", "Surname", null, "123456@email.com", "8017-12-45-34", "8029-78-89-79", "01-4565645645654", "Street", "zip", "London", "Center", "United Kingdom");
        clickContactDelete(1);
        REPORTS_LIST_ROWS.shouldHaveSize(0);
        rootLogger.info("Contact CRUD test Passed");
    }
    @Test
    public void contacts_a_select_company_in_form_person(){
        deleteAllContacts();

        ObjectContact company = ObjectContact.newBuilder().build();
        company.createCompany(REPORT, "Company",
                null, null,
                null, null,null,
                null, null,
                null, null);
        ObjectContact person = ObjectContact.newBuilder().build();
        person.createPerson(REPORT, null,
                "Name", "Surname", null,
                null, null,
                null, null,null,
                null, null,
                null, null);
        clickContactEdit(2);
        person.editForm(2, "Person", null, "Name", "Surname", company.contactLegalEntity, null, null, null, null, null, null, null, null, null);

        refresh();
        checkReportsContactRow(PERSON, 2, person, null, null,null);
        rootLogger.info("Contact add company link");
    }
    @Test
    public void contacts_a_select_company_in_modal_person(){
        deleteAllContacts();

        ObjectContact company = ObjectContact.newBuilder().build();
        company.createCompany(REPORT, "Company",
                null, null,
                null, null,null,
                null, null,
                null, null);
        ObjectContact person = ObjectContact.newBuilder().build();
        person.createPerson(REPORT, null,
                "Name", "Surname", company.contactLegalEntity,
                null, null,
                null, null,null,
                null, null,
                null, null);
        refresh();
        checkReportsContactRow(PERSON, 2, person, null, null,null);
        rootLogger.info("Contact add company link");
    }
    @Test
    public void contacts_create_project(){
        deleteAllContacts();

        ObjectContact company = ObjectContact.newBuilder().build();
        company.createCompany(REPORT, "Company",
                null, null,
                null, null,null,
                null, null,
                null, null);
        clickContactNewProject(1);
        submitMwNewProject("from contacts report");
        String projectUrl = getActualUrl();

        openPageWithSpinner(URL_ReportsContacts);
        checkReportsContactRow(COMPANY, 1, company, "1 project", null,1);
        rootLogger.info("Contact add company link");
    }
    @Test
    public void contacts_merge_positive() {
        ObjectContact contactPerson1 = ObjectContact.newBuilder().build();
        contactPerson1.setValues("Person", null, "NameZ", "SurnameZ", "email01@new.test");
        ObjectContact contactPerson2 =ObjectContact.newBuilder().build();
        contactPerson2.setValues("Person", null, "NameA", "SurnameA", "email02@new.test");

        deleteAllContacts();

        rootLogger.info("Check default sort by name");
        REPORTS_SORT_BY_NAME.waitUntil(visible, 30000);

        rootLogger.info("Create 1st contact");
        contactPerson1.createPerson(REPORT,  null,
                contactPerson1.contactFirstName, contactPerson1.contactLastName,
                null, contactPerson1.contactEmail, null, null,
                null, null, null,
                null, null, PITCAIRN_ISLANDS.getValue());
        rootLogger.info("Check 1-st contact row");
        checkReportsContactRow(
                1,
                contactPerson1.contactFirstName,
                contactPerson1.contactLastName,
                contactPerson1.contactEmail,
                PITCAIRN_ISLANDS.getValue());

        rootLogger.info("Create 2nd contact");
        contactPerson2.createPerson(REPORT, null,
                contactPerson2.contactFirstName, contactPerson2.contactLastName,
                null, contactPerson2.contactEmail, null, null,
                null, null, null,
                null, null, NETHERLANDS_ANTILES.getValue());
        rootLogger.info("Check 1-st contact row - default sort by name - ascending");
        checkReportsContactRow(
                1,
                contactPerson2.contactFirstName,
                contactPerson2.contactLastName,
                contactPerson2.contactEmail,
                NETHERLANDS_ANTILES.getValue());

        mergeContactsAll(contactPerson1);
        refresh();
        rootLogger.info("Check merge result if 1-st contact present");
        checkReportsContactRow(
                1,
                contactPerson1.contactFirstName,
                contactPerson1.contactLastName,
                contactPerson1.contactEmail,
                PITCAIRN_ISLANDS.getValue());
    }
    @Test
    public void contacts_merge_validation_person_and_company() {
        ObjectContact contactPerson = ObjectContact.newBuilder().build();
        contactPerson.setValues("Person", null, "NameZ", "SurnameZ", "email01@new.test");
        ObjectContact contactCompany =ObjectContact.newBuilder().build();
        contactCompany.setValues("Company", "Firm", null, null, "email02@new.test");

        deleteAllContacts();

        rootLogger.info("Create person contact");
        contactPerson.createPerson(REPORT,  null,
                contactPerson.contactFirstName, contactPerson.contactLastName,
                null, contactPerson.contactEmail, null, null,
                null, null, null,
                null, null, PITCAIRN_ISLANDS.getValue());

        rootLogger.info("Create company contact");
        contactCompany.createCompany(REPORT, contactCompany.contactLegalEntity,
               null, null,
                null, contactCompany.contactEmail, null, null,
                null, null, NETHERLANDS_ANTILES.getValue());
        mergeContactsAll(contactPerson);
        checkText("Contacts have different types");
    }
    @Test
    public void contacts_merge_validation_one_person_has_company() {
        ObjectContact contactCompany =ObjectContact.newBuilder().build();
        contactCompany.setValues("Company", "Z-Firm", null, null, "email02@new.test");
        ObjectContact contactPerson =ObjectContact.newBuilder().build();
        contactPerson.setValues("Person", null, "NameZ", "SurnameZ", "email01@new.test");
        ObjectContact contactAffiliated =ObjectContact.newBuilder().build();
        contactAffiliated.setValues("Person", contactCompany.contactLegalEntity, "NameA", "SurnameA", "worker@new.test");

        deleteAllContacts();
        rootLogger.info("Create company contact");
        contactCompany.createCompany(REPORT, contactCompany.contactLegalEntity,
                null, null,
                null, contactCompany.contactEmail,
                null, null,
                null, null, NETHERLANDS_ANTILES.getValue());
        rootLogger.info("Create person contact");
        contactPerson.createPerson(REPORT,  null,
                contactPerson.contactFirstName, contactPerson.contactLastName,
                null, contactPerson.contactEmail,
                null, null,
                null, null, null,
                null, null, PITCAIRN_ISLANDS.getValue());
        rootLogger.info("Create person-affiliated contact");
        contactAffiliated.createPerson(REPORT,  null,
                contactAffiliated.contactFirstName, contactAffiliated.contactLastName,
                contactCompany.contactLegalEntity, contactAffiliated.contactEmail,
                null, null,
                null, null, null,
                null, null, PITCAIRN_ISLANDS.getValue());
        selectContactRow(contactPerson.contactFirstName);
        selectContactRow(contactAffiliated.contactFirstName);

        mergeContactsSelected(contactPerson);
        checkText("Contacts have different \"Company\" values");
    }

    @Test
    public void contacts_y_import_ContactsMaxValue() {
        ObjectFile fileCsv = new ObjectFile(ObjectFile.newBuilder()).buildFile("ContactsMaxValue", "csv");
        deleteAllContacts();
        callImportContactModal();
        submitImportContactsModal(fileCsv, null);
        sleep(3000);
        refresh();
        REPORTS_LIST_ROWS.shouldHaveSize(1);

    }
    @Test
    public void contacts_y_import_ContactsMinValue() {
        ObjectFile fileCsv = new ObjectFile(ObjectFile.newBuilder()).buildFile("ContactsMinValue", "csv");
        deleteAllContacts();
        callImportContactModal();
        submitImportContactsModal(fileCsv, null);
        sleep(3000);
        refresh();
        REPORTS_LIST_ROWS.shouldHaveSize(1);
    }
    @Test
    public void contacts_y_import_ContactsValidationNotCsv() {
        ObjectFile filePng = new ObjectFile(ObjectFile.newBuilder()).buildFile("png", "png");
        callImportContactModal();
        submitImportContactsModal(filePng, "file: Not a valid CSV file");
    }
    @Test
    public void contacts_y_import_ContactsValidationNameSurname() {
        ObjectFile fileCsv = new ObjectFile(ObjectFile.newBuilder()).buildFile("ContactsValidationNameSurname", "csv");
        callImportContactModal();
        submitImportContactsModal(fileCsv, "file: Multiple headers: SAZipCode. ");
    }
}
