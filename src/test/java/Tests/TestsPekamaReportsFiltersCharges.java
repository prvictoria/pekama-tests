package Tests;

import Page.TestsCredentials;
import Steps.MessagesIMAP;
import Steps.ObjectCharges;
import Steps.User;
import org.apache.logging.log4j.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import javax.mail.MessagingException;
import java.io.IOException;

import static Page.ModalWindows.CHARGES_TYPE_ASSOCIATE;
import static Page.ModalWindows.CHARGES_TYPE_EXPENSES;
import static Page.ModalWindows.CHARGES_TYPE_FEES;
import static Page.PekamaReports.REPORTS_BTN_NEW_PROJECT;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.GBP;
import static Page.TestsStrings.ILS;
import static Page.TestsStrings.USD;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectCharges.checkInvoiceRowReports;
import static Steps.StepsModalWindows.submitMwNewProject;
import static Steps.StepsPekama.openPageWithSpinner;
import static Steps.StepsPekama.submitEnabledButton;
import static Steps.StepsPekamaReports.deleteAllCharges;
import static Steps.StepsPekamaReports.selectSortOrder;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class TestsPekamaReportsFiltersCharges {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String TEST_USER_LOGIN = User1.GMAIL_EMAIL.getValue();
    private static final String TEST_USER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private static final String USER_TEAM_NAME = User1.TEAM_NAME.getValue();
    private static ObjectCharges invoice1 = new ObjectCharges();
    private static ObjectCharges invoice2 = new ObjectCharges();
    private static ObjectCharges invoice3 = new ObjectCharges();
    private static String projectName;
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
            user.loginByURL(TEST_USER_LOGIN, TEST_USER_PASSWORD, URL_LogIn);
        }
        else {rootLogger.info("Before was skipped");
        }
        deleteAllCharges();

        openPageWithSpinner(URL_ReportsProjects);
        rootLogger.info("Create project and charges");
        submitEnabledButton(REPORTS_BTN_NEW_PROJECT);
        projectName = submitMwNewProject();
        invoice1.create(USER_TEAM_NAME, "Billed",
                CHARGES_TYPE_EXPENSES, 1,
                "abc", GBP, 1);
        invoice2.create(USER_TEAM_NAME, "Not Billed",
                CHARGES_TYPE_ASSOCIATE, 0,
                "def",ILS, 999);
        invoice3.create(USER_TEAM_NAME, "Billed & Paid",
                CHARGES_TYPE_FEES, -1,
                "xyz",USD, 100);
        getWebDriver().quit();
    }
    @Before
    public void login() {
        User user = new User();
        user.loginByURL(TEST_USER_LOGIN, TEST_USER_PASSWORD, URL_ReportsCharges);
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
