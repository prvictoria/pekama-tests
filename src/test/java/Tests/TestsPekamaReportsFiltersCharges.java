package Tests;

import Steps.ObjectCharges;
import Steps.ObjectContact;
import Steps.User;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;

import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaTeamSettings.TAB_MEMBERS_BTN_ADD;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.ContactRelation.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectCharges.checkInvoiceRowReports;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.submitMwNewProject;
import static Steps.StepsPekama.*;
import static Steps.StepsPekama.openPageWithSpinner;
import static Steps.StepsPekama.submitEnabledButton;
import static Steps.StepsPekamaProject.selectAndAddContact;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReportsFiltersCharges {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String OWNER_LOGIN = User1.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_TEAM_NAME = User1.TEAM_NAME.getValue();
    private static ObjectCharges invoice1 = new ObjectCharges();
    private static ObjectCharges invoice2 = new ObjectCharges();
    private static ObjectCharges invoice3 = new ObjectCharges();
    private static ObjectContact contact1 = new ObjectContact();
    private static ObjectContact contact2 = new ObjectContact();
    private static ObjectContact contact3 = new ObjectContact();
    private static String projectName;
    private static String projectUrl;
    private static boolean skipBefore = false;

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(skipBefore==false) {
            User user = new User();
            user.loginByURL(OWNER_LOGIN, OWNER_PASSWORD, URL_LogIn);

        rootLogger.info("Create project");
        submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);
        projectName = submitMwNewProject();
        projectUrl = getActualUrl();

        deleteAllMembers();
        addMember("A-member@email.com", TAB_MEMBERS_BTN_ADD);
        addMember("B-member@office.eu", TAB_MEMBERS_BTN_ADD);

        deleteAllContacts();
        rootLogger.info("Create contacts in reports");
        contact1.createCompany(REPORT, "ip lawyers ltd",
                null, null,
                null, null,null,
                null, null,
                null, null);
        contact2.createCompany(REPORT, "Law firm",
                null, null,
                null, null,null,
                null, null,
                null, null);
        contact3.createPerson(REPORT, null,
                "Name", "Surname", null,
                null, null,
                null, null,null,
                null, null,
                null, null);
        deleteAllCharges();

        openPageWithSpinner(URL_ReportsProjects);
        rootLogger.info("Add contacts to project");
        openUrlWithBaseAuth(projectUrl);
        selectAndAddContact(contact1, DOMESTIC_REPRESENTATIVE.getValue());
        selectAndAddContact(contact2, OWNER_COMPANY.getValue());
        selectAndAddContact(contact3, INVESTOR.getValue());

            rootLogger.info("Create charges in project");
            invoice1.create(OWNER_TEAM_NAME, contact1.contactLegalEntity,
                    null, "Billed",
                    CHARGES_TYPE_EXPENSES, 10,
                    "abc", GBP, 1);
            invoice2.create(OWNER_TEAM_NAME, null,
                    "A-member", "Not Billed",
                    CHARGES_TYPE_ASSOCIATE, 0,
                    "def",ILS, 999);
            invoice3.create(OWNER_TEAM_NAME, contact3.contactNameSurname,
                    "B-member", "Billed & Paid",
                    CHARGES_TYPE_FEES, -10,
                    "xyz",USD, 100);
        getWebDriver().quit();
        }
        else {rootLogger.info("Before suite was skipped");
        }
    }
    @Before
    public void login() {
        clearBrowserCache();
        User user = new User();
        user.loginByURL(OWNER_LOGIN, OWNER_PASSWORD, URL_ReportsCharges);
    }
    @Test
    public void charges_sort_last_created (){
        rootLogger.info("Validate sort order and rows by: "+"Last created");
        selectSortOrderAndCheck(null, false);
        checkInvoiceRowReports(1, invoice3);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice1);
        selectSortOrderAndCheck("Last created", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
    }
    @Test
    public void charges_sort_last_modified (){
        rootLogger.info("Validate sort order and rows by: "+"Last modified");
        selectSortOrderAndCheck("Last modified", false);
        checkInvoiceRowReports(1, invoice3);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice1);
        selectSortOrderAndCheck("Last modified", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);

    }
    @Test
    public void charges_sort_date (){
        rootLogger.info("Validate sort order and rows by: "+"Date");
        selectSortOrderAndCheck("Date", true);
        checkInvoiceRowReports(1, invoice3);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice1);
        selectSortOrderAndCheck("Date", false);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
    }
    @Test
    public void charges_sort_amount (){
        rootLogger.info("Validate sort order and rows by: "+"Amount");
        selectSortOrderAndCheck("Amount", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice2);
        selectSortOrderAndCheck("Amount", false);
        checkInvoiceRowReports(1, invoice2);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice1);
    }
    @Test
    public void charges_sort_from (){
        rootLogger.info("Validate sort order and rows by: "+"From");
        selectSortOrderAndCheck("From", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
        selectSortOrderAndCheck("From", false);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
    }
    @Test
    public void charges_sort_to (){
        rootLogger.info("Validate sort order and rows by: "+"To");
        selectSortOrderAndCheck("To", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice2);
        selectSortOrderAndCheck("To", false);
        checkInvoiceRowReports(1, invoice2);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice1);
    }
    @Test
    public void charges_sort_type (){
        rootLogger.info("Validate sort order and rows: "+"Type");
        selectSortOrderAndCheck("Type", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
        selectSortOrderAndCheck("Type", false);
        checkInvoiceRowReports(1, invoice3);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice1);
    }
    @Test
    public void charges_sort_status (){
        rootLogger.info("Validate sort order and rows: "+"Status");
        selectSortOrderAndCheck("Status", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice2);
        selectSortOrderAndCheck("Status", false);
        checkInvoiceRowReports(1, invoice2);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice1);
    }
}