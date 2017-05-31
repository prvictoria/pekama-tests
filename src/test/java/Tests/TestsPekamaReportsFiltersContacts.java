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

import static Page.TestsCredentials.Countries.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.ObjectContact.contactType.*;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.ObjectUser.Users.OWNER;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsModalWindows.submitMwNewProject;
import static Steps.StepsPekama.openUrlIfActualNotEquals;
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
    private static ObjectContact company1 = ObjectContact.newBuilder()
            .contactType("Company")
            .contactLegalEntity("Lawyers & Brothers Co")
            .contactEmail("newmail@dot.com")
            .contactCountry(AFGHANISTAN.getValue())
            .build()
            .logContactFields();;
    private static ObjectContact company2 = ObjectContact.newBuilder()
            .contactType("Company")
            .contactLegalEntity("Law firm")
            .contactEmail("boss@dot.com")
            .contactCountry(PITCAIRN_ISLANDS.getValue())
            .build()
            .logContactFields();;
    private static ObjectContact person3 = ObjectContact.newBuilder()
            .contactType("Person")
            .contactFirstName("John")
            .contactLastName("Drizer")
            .contactCompany("Law firm")
            .contactEmail("mr.rabbit@dot.com")
            .contactCountry(NETHERLANDS_ANTILES.getValue())
            .build()
            .logContactFields();
    private static ObjectContact person4 = ObjectContact.newBuilder()
            .contactType("Person")
            .contactFirstName("Elton")
            .contactLastName("Hopkins")
            .contactCompany(null)
            .contactEmail(null)
            .contactCountry(null)
            .build()
            .logContactFields();
    private static ObjectContact person5 = ObjectContact.newBuilder()
            .contactType("Person")
            .contactFirstName("Joanna")
            .contactLastName("Kispers")
            .contactCompany("Lawyers & Brothers Co")
            .contactEmail(null)
            .contactCountry(null)
            .build()
            .logContactFields();
    private static final ObjectUser user = new ObjectUser(newBuilder()).buildUser(OWNER);
    private static boolean skipBefore = false;

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(skipBefore==false) {
            user.login();

            deleteAllContacts();
            rootLogger.info("Create contacts in reports");
            company1.createContact(REPORT);
            company2.createContact(REPORT);
            person3.createContact(REPORT);
            person4.createContact(REPORT);
            person5.createContact(REPORT);
            getWebDriver().quit();
        }
        else {rootLogger.info("Before suite was skipped");
        }
    }
    @Before
    public void login() {
        user.login(URL_ReportsContacts);
        openUrlIfActualNotEquals(URL_ReportsContacts);
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
