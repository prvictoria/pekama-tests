package Tests;

import Steps.ObjectContact;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;

import java.io.IOException;

import static Page.TestsCredentials.*;
import static Page.TestsCredentials.Countries.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.ObjectContact.contactType.COMPANY;
import static Steps.ObjectContact.contactType.PERSON;
import static Steps.ObjectContact.enterPoint.REPORT;
import static Steps.StepsModalWindows.submitMwNewProject;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by VatslauX on 19-May-17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReportsFiltersContacts {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String OWNER_LOGIN = User8.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD = User8.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_TEAM_NAME = User8.TEAM_NAME.getValue();
    private static ObjectContact company1 = new ObjectContact();
    private static ObjectContact company2 = new ObjectContact();
    private static ObjectContact person3 = new ObjectContact();
    private static ObjectContact person4 = new ObjectContact();
    private static ObjectContact person5 = new ObjectContact();

    private static boolean skipBefore = false;

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(skipBefore==false) {
            ObjectUser user = ObjectUser.newBuilder().build();
            user.loginByURL(OWNER_LOGIN, OWNER_PASSWORD, URL_PEKAMA_LOGIN);

            deleteAllContacts();
            rootLogger.info("Create contacts in reports");
            company1.createCompany(REPORT, "Lawyers & Brothers Co",
                    "newmail@dot.com", null,
                    null, null,null,
                    null, null,
                    null, AFGHANISTAN.getValue());
            company2.createCompany(REPORT, "Law firm",
                    "boss@dot.com", null,
                    null, null,null,
                    null, null,
                    null, PITCAIRN_ISLANDS.getValue());
            person3.createPerson(REPORT, null,
                    "John", "Drizer", "Law firm",
                    "mr.rabbit@dot.com", null,
                    null, null,null,
                    null, null,
                    null, NETHERLANDS_ANTILES.getValue());
            person4.createPerson(REPORT, null,
                    "Elton", "Hopkins", null,
                    null, null,
                    null, null,null,
                    null, null,
                    null, null);
            person5.createPerson(REPORT, null,
                    "Joanna", "Kispers", "Lawyers & Brothers Co",
                    null, null,
                    null, null,null,
                    null, null,
                    null, null);
            getWebDriver().quit();
        }
        else {rootLogger.info("Before suite was skipped");
        }
    }
    @Before
    public void login() {
        //clearBrowserCache();
        ObjectUser user = ObjectUser.newBuilder().build();
        user.loginByURL(OWNER_LOGIN, OWNER_PASSWORD, URL_ReportsContacts);
    }
    @Test
    public void contacts_sort_1_by_name(){
        checkActualSortOrderInReports("Name", true);
        checkReportsContactRow(PERSON, 1, person4, null, null,null);
        checkReportsContactRow(PERSON, 2, person5, null, null,null);
        checkReportsContactRow(PERSON, 3, person3, null, null,null);
        checkReportsContactRow(COMPANY, 4, company2, null, null,null);
        checkReportsContactRow(COMPANY, 5, company1, null, null,null);
        selectSortOrderAndCheck("Name", false);
        checkReportsContactRow(COMPANY, 1, company1, null, null,null);
        checkReportsContactRow(COMPANY, 2, company2, null, null,null);
        checkReportsContactRow(PERSON, 3, person3, null, null,null);
        checkReportsContactRow(PERSON, 4, person5, null, null,null);
        checkReportsContactRow(PERSON, 5, person4, null, null,null);
    }
    @Test
    public void contacts_sort_2_by_type(){
        selectSortOrderAndCheck("Type", true);
        checkReportsContactRow(COMPANY, 1, company1, null, null,null);
        checkReportsContactRow(COMPANY, 2, company2, null, null,null);
        checkReportsContactRow(PERSON, 3, person3, null, null,null);
        checkReportsContactRow(PERSON, 4, person4, null, null,null);
        checkReportsContactRow(PERSON, 5, person5, null, null,null);
        selectSortOrderAndCheck("Type", false);
        checkReportsContactRow(PERSON, 1, person3, null, null,null);
        checkReportsContactRow(PERSON, 2, person4, null, null,null);
        checkReportsContactRow(PERSON, 3, person5, null, null,null);
        checkReportsContactRow(COMPANY, 4, company1, null, null,null);
        checkReportsContactRow(COMPANY, 5, company2, null, null,null);
    }
    @Test
    public void contacts_sort_3_by_company(){
        selectSortOrderAndCheck("Company", true);
        checkReportsContactRow(PERSON, 1, person5, null, null,null);
        checkReportsContactRow(PERSON, 2, person3, null, null,null);
        checkReportsContactRow(COMPANY, 3, company1, null, null,null);
        checkReportsContactRow(COMPANY, 4, company2, null, null,null);
        checkReportsContactRow(PERSON, 5, person4, null, null,null);
        selectSortOrderAndCheck("Company", false);
        checkReportsContactRow(COMPANY, 1, company1, null, null,null);
        checkReportsContactRow(COMPANY, 2, company2, null, null,null);
        checkReportsContactRow(PERSON, 3, person4, null, null,null);
        checkReportsContactRow(PERSON, 4, person3, null, null,null);
        checkReportsContactRow(PERSON, 5, person5, null, null,null);
    }
    @Test
    public void contacts_sort_4_by_country(){
        selectSortOrderAndCheck("Country", true);
        checkReportsContactRow(COMPANY, 1, company1, null, null,null);
        checkReportsContactRow(PERSON, 2, person3, null, null,null);
        checkReportsContactRow(COMPANY, 3, company2, null, null,null);
        checkReportsContactRow(PERSON, 4, person4, null, null,null);
        checkReportsContactRow(PERSON, 5, person5, null, null,null);
        selectSortOrderAndCheck("Country", false);
        checkReportsContactRow(PERSON, 1, person4, null, null,null);
        checkReportsContactRow(PERSON, 2, person5, null, null,null);
        checkReportsContactRow(COMPANY, 3, company2, null, null,null);
        checkReportsContactRow(PERSON, 4, person3, null, null,null);
        checkReportsContactRow(COMPANY, 5, company1, null, null,null);
    }
    @Test
    public void contacts_sort_5_by_last_created(){
        selectSortOrderAndCheck("Last created", false);
        checkReportsContactRow(PERSON, 1, person5, null, null,null);
        checkReportsContactRow(PERSON, 2, person4, null, null,null);
        checkReportsContactRow(PERSON, 3, person3, null, null,null);
        checkReportsContactRow(COMPANY, 4, company2, null, null,null);
        checkReportsContactRow(COMPANY, 5, company1, null, null,null);
        selectSortOrderAndCheck("Last created", true);
        checkReportsContactRow(COMPANY, 1, company1, null, null,null);
        checkReportsContactRow(COMPANY, 2, company2, null, null,null);
        checkReportsContactRow(PERSON, 3, person3, null, null,null);
        checkReportsContactRow(PERSON, 4, person4, null, null,null);
        checkReportsContactRow(PERSON, 5, person5, null, null,null);
    }
    @Test
    public void contacts_sort_5_by_last_modified(){
        selectSortOrderAndCheck("Last modified", false);
        checkReportsContactRow(PERSON, 1, person5, null, null,null);
        checkReportsContactRow(PERSON, 2, person4, null, null,null);
        checkReportsContactRow(PERSON, 3, person3, null, null,null);
        checkReportsContactRow(COMPANY, 4, company2, null, null,null);
        checkReportsContactRow(COMPANY, 5, company1, null, null,null);
        selectSortOrderAndCheck("Last modified", true);
        checkReportsContactRow(COMPANY, 1, company1, null, null,null);
        checkReportsContactRow(COMPANY, 2, company2, null, null,null);
        checkReportsContactRow(PERSON, 3, person3, null, null,null);
        checkReportsContactRow(PERSON, 4, person4, null, null,null);
        checkReportsContactRow(PERSON, 5, person5, null, null,null);
    }
}
