package Tests;

import Steps.ObjectProject;
import Steps.ObjectUser;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;

import static Page.TestsCredentials.*;
import static Page.TestsCredentials.Countries.*;
import static Page.TestsCredentials.MatterType.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectProject.checkReportsProjectRow;
import static Steps.ObjectProject.projectEnterPoint.REPORTS;
import static Steps.ObjectUser.Users.OWNER;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsModalWindows.submitMwNewProject;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReportsFiltersProjects {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static ObjectProject project1 = new ObjectProject();
    private static ObjectProject project2 = new ObjectProject();
    private static ObjectProject project3 = new ObjectProject();
    private static ObjectProject project4 = new ObjectProject();
    private static ObjectUser owner = new ObjectUser(newBuilder()).buildUser(OWNER);

    private static boolean skipBefore = false;

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();

        if(skipBefore==false) {
            owner.login(owner, URL_ReportsProjects);

            deleteAllProjects();
            project1.setValues("SortPrj1", PATENT.getValue(),
                    "Canada", null, null);
            project2.setValues("SortPrj2", CRM.getValue(),
                    CRM_CONFERENCE.getValue(), "ref1", null);
            project3.setValues("SortPrj3", TRADEMARK.getValue(),
                    NETHERLANDS_ANTILES.getValue(), null, null);
            project4.setValues("SortPrj4", COPYRIGHT.getValue(),
                    USA.getValue(), "ref2", null);

            project1.create(REPORTS, project1);
            project1.selectProjectValues(null, "Basic Filing", "Large");
            project2.create(REPORTS, project2);
            project2.selectProjectValues(null, "Potential Cooperation", null);
            project3.create(REPORTS, project3);
            project3.selectProjectValues(null, "Opposition", "Word Mark");
            project4.create(REPORTS, project4);
            project4.selectProjectValues(null, null, null);
            getWebDriver().quit();
        }
        else {rootLogger.info("Before suite was skipped");
        }
    }
    @Before
    public void login() {
        //clearBrowserCache();
        owner.login(owner, URL_ReportsProjects);
    }
    @AfterClass
    public static void clear(){
        owner.login(owner, URL_ReportsProjects);
        deleteAllProjects();
        getWebDriver().quit();
    }
    @Test
    public void project_sort_by_none_default_and_name(){
        rootLogger.info("Validate sort order and rows by: default");
        checkActualSortOrderInReports("None", null);
        rootLogger.info("Validate sort order and rows by: "+"Name");
        selectSortOrderAndCheck("Name", true);
        checkReportsProjectRow(1, project1);
        checkReportsProjectRow(2, project2);
        checkReportsProjectRow(3, project3);
        checkReportsProjectRow(4, project4);
        selectSortOrderAndCheck("Name", false);
        checkReportsProjectRow(1, project4);
        checkReportsProjectRow(2, project3);
        checkReportsProjectRow(3, project2);
        checkReportsProjectRow(4, project1);
        return;
    }
    @Test
    public void project_sort_by_matter_type(){
        rootLogger.info("Validate sort order and rows by: "+"Project Type");
        selectSortOrderAndCheck("Project Type", true);
        checkReportsProjectRow(1, project2);
        checkReportsProjectRow(2, project4);
        checkReportsProjectRow(3, project1);
        checkReportsProjectRow(4, project3);
        selectSortOrderAndCheck("Project Type", false);
        checkReportsProjectRow(1, project3);
        checkReportsProjectRow(2, project1);
        checkReportsProjectRow(3, project4);
        checkReportsProjectRow(4, project2);
        return;
    }
    @Test
    public void project_sort_by_country(){
        rootLogger.info("Validate sort order and rows by: "+"Country");
        selectSortOrderAndCheck("Country", true);
        checkReportsProjectRow(1, project1);
        checkReportsProjectRow(2, project2);
        checkReportsProjectRow(3, project3);
        checkReportsProjectRow(4, project4);
        selectSortOrderAndCheck("Country", false);
        checkReportsProjectRow(1, project4);
        checkReportsProjectRow(2, project3);
        checkReportsProjectRow(3, project2);
        checkReportsProjectRow(4, project1);
        return;
    }
    @Ignore //Todo select Status in project
    @Test
    public void project_sort_by_status(){
        rootLogger.info("Validate sort order and rows by: "+"Status");
        selectSortOrderAndCheck("Status", true);
        checkReportsProjectRow(1, project1);
        checkReportsProjectRow(2, project2);
        checkReportsProjectRow(3, project3);
        checkReportsProjectRow(4, project4);
        selectSortOrderAndCheck("Status", false);
        checkReportsProjectRow(1, project1);
        checkReportsProjectRow(2, project2);
        checkReportsProjectRow(3, project3);
        checkReportsProjectRow(4, project4);
        return;
    }
    @Test
    public void project_sort_by_last_created(){
        rootLogger.info("Validate sort order and rows by: "+"Last created");
        selectSortOrderAndCheck("Last created", false);
        checkReportsProjectRow(1, project4);
        checkReportsProjectRow(2, project3);
        checkReportsProjectRow(3, project2);
        checkReportsProjectRow(4, project1);
        selectSortOrderAndCheck("Last created", true);
        checkReportsProjectRow(1, project1);
        checkReportsProjectRow(2, project2);
        checkReportsProjectRow(3, project3);
        checkReportsProjectRow(4, project4);
        return;
    }
    @Test
    public void project_sort_by_last_modified(){
        rootLogger.info("Validate sort order and rows by: "+"Last modified");
        selectSortOrderAndCheck("Last modified", false);
        checkReportsProjectRow(1, project4);
        checkReportsProjectRow(2, project3);
        checkReportsProjectRow(3, project2);
        checkReportsProjectRow(4, project1);
        selectSortOrderAndCheck("Last modified", true);
        checkReportsProjectRow(1, project1);
        checkReportsProjectRow(2, project2);
        checkReportsProjectRow(3, project3);
        checkReportsProjectRow(4, project4);
        return;
    }
    @Test
    public void project_sort_by_type(){
        rootLogger.info("Validate sort order and rows by: "+"Type");
        selectSortOrderAndCheck("Type", true);
        checkReportsProjectRow(1, project1);
        checkReportsProjectRow(2, project3);
        checkReportsProjectRow(3, project2);
        checkReportsProjectRow(4, project4);
        selectSortOrderAndCheck("Type", false);
        checkReportsProjectRow(1, project4);
        checkReportsProjectRow(2, project2);
        checkReportsProjectRow(3, project3);
        checkReportsProjectRow(4, project1);
        return;
    }
    @Test
    public void project_sort_by_sub_type(){
        rootLogger.info("Validate sort order and rows by: "+"Sub Type");
        selectSortOrderAndCheck("Sub Type", true);
        checkReportsProjectRow(1, project1);
        checkReportsProjectRow(2, project3);
        checkReportsProjectRow(3, project2);
        checkReportsProjectRow(4, project4);
        selectSortOrderAndCheck("Sub Type", false);
        checkReportsProjectRow(1, project2);
        checkReportsProjectRow(2, project4);
        checkReportsProjectRow(3, project3);
        checkReportsProjectRow(4, project1);
        return;
    }
}
