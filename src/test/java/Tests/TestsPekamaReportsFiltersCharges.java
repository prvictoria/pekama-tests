package Tests;

import Page.TestsCredentials;
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
import static Page.PekamaReports.*;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.ContactRelation.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectCharges.checkInvoiceRowReports;
import static Steps.ObjectContact.enterPoint.*;
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
            clearBrowserCache();
            User user = new User();
            user.loginByURL(OWNER_LOGIN, OWNER_PASSWORD, URL_LogIn);
        }
        else {rootLogger.info("Before suite was skipped");
        }
        addMember("A-member@email.com", DASHBOARD_INVITE);
        addMember("B-member@office.eu", DASHBOARD_INVITE);
        deleteAllCharges();
        deleteAllContacts();
        contact1.createCompany(REPORT, "Company",
                null, null,
                null, null,null,
                null, null,
                null, null);
        contact1.createCompany(REPORT, "Law firm",
                null, null,
                null, null,null,
                null, null,
                null, null);
        openPageWithSpinner(URL_ReportsProjects);
        rootLogger.info("Create project and charges");
        submitEnabledButton(REPORTS_BTN_NEW_PROJECT);
        projectName = submitMwNewProject();
        projectUrl = getActualUrl();
        selectAndAddContact(contact1, DOMESTIC_REPRESENTATIVE.getValue());
        selectAndAddContact(contact2, OWNER_COMPANY.getValue());
        selectAndAddContact(contact3, INVESTOR.getValue());
        invoice1.create(OWNER_TEAM_NAME, contact1.contactLegalEntity,
                null, "Billed",
                CHARGES_TYPE_EXPENSES, 1,
                "abc", GBP, 1);
        invoice2.create(OWNER_TEAM_NAME, "Not Billed",
                contact2.contactLegalEntity, "A-member",
                CHARGES_TYPE_ASSOCIATE, 0,
                "def",ILS, 999);
        invoice3.create(OWNER_TEAM_NAME, "Billed & Paid",
                null, "B-member",
                CHARGES_TYPE_FEES, -1,
                "xyz",USD, 100);
        getWebDriver().quit();
    }
    @Before
    public void login() {
        User user = new User();
        user.loginByURL(OWNER_LOGIN, OWNER_PASSWORD, URL_ReportsCharges);
    }
    @Test
    public void charges_sort_last_created (){
        rootLogger.info("Validate sort order and rows by: "+"Last created");
        selectSortOrder(null, false);
        checkInvoiceRowReports(1, invoice3);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice1);
        selectSortOrder("Last created", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
    }
    @Test
    public void charges_sort_last_modified (){
        rootLogger.info("Validate sort order and rows by: "+"Last modified");
        selectSortOrder("Last modified", false);
        checkInvoiceRowReports(1, invoice3);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice1);
        selectSortOrder("Last modified", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);

    }
    @Test
    public void charges_sort_date (){
        rootLogger.info("Validate sort order and rows by: "+"Date");
        selectSortOrder("Date", true);
        checkInvoiceRowReports(1, invoice3);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice1);
        selectSortOrder("Date", false);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
    }
    @Test
    public void charges_sort_amount (){
        rootLogger.info("Validate sort order and rows by: "+"Amount");
        selectSortOrder("Amount", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice2);
        selectSortOrder("Amount", false);
        checkInvoiceRowReports(1, invoice2);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice1);
    }
    @Test
    public void charges_sort_from (){
        rootLogger.info("Validate sort order and rows by: "+"From");
        selectSortOrder("From", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
        selectSortOrder("From", false);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
    }
    @Test
    public void charges_sort_to (){
        rootLogger.info("Validate sort order and rows by: "+"To");
        selectSortOrder("To", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
        selectSortOrder("To", false);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
    }
    @Test
    public void charges_sort_type (){
        rootLogger.info("Validate sort order and rows: "+"Type");
        selectSortOrder("Type", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice3);
        selectSortOrder("Type", false);
        checkInvoiceRowReports(1, invoice3);
        checkInvoiceRowReports(2, invoice2);
        checkInvoiceRowReports(3, invoice1);
    }
    @Test
    public void charges_sort_status (){
        rootLogger.info("Validate sort order and rows: "+"Status");
        selectSortOrder("Status", true);
        checkInvoiceRowReports(1, invoice1);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice2);
        selectSortOrder("Status", false);
        checkInvoiceRowReports(1, invoice2);
        checkInvoiceRowReports(2, invoice3);
        checkInvoiceRowReports(3, invoice1);
    }
}
