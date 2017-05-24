package Tests;

import Page.TestsCredentials;
import Steps.ObjectCharges;
import Steps.ObjectContact;
import Steps.ObjectProject;
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
import static Page.PekamaTeamSettings.TAB_MEMBERS_BTN_ADD;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.ContactRelation.*;
import static Page.TestsCredentials.Countries.*;
import static Page.TestsCredentials.MatterType.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectCharges.checkInvoiceRowReports;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.ObjectProject.projectEnterPoint.REPORTS;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.submitMwNewProject;
import static Steps.StepsPekama.*;
import static Steps.StepsPekama.openPageWithSpinner;
import static Steps.StepsPekama.submitEnabledButton;
import static Steps.StepsPekamaProject.numberCreate;
import static Steps.StepsPekamaProject.selectAndAddContact;
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
    private static final String OWNER_LOGIN = User8.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD = User8.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_TEAM_NAME = User8.TEAM_NAME.getValue();
    private static ObjectProject project1 = new ObjectProject();
    private static ObjectProject project2 = new ObjectProject();
    private static ObjectProject project3 = new ObjectProject();
    private static ObjectProject project4 = new ObjectProject();



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
            user.loginByURL(OWNER_LOGIN, OWNER_PASSWORD, URL_ReportsProjects);

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
            project1.setProjectValues(null, "Basic Filing", "Large");
            project2.create(REPORTS, project2);
            project2.setProjectValues(null, "Potential Cooperation", null);
            project3.create(REPORTS, project3);
            project3.setProjectValues(null, "Opposition", "Word Mark");
            project4.create(REPORTS, project4);
            project4.setProjectValues(null, null, null);
            getWebDriver().quit();
        }
        else {rootLogger.info("Before suite was skipped");
        }
    }
    @Before
    public void login() {
        clearBrowserCache();
        User user = new User();
        user.loginByURL(OWNER_LOGIN, OWNER_PASSWORD, URL_ReportsProjects);
    }
    @Test
    public void project_sort_none_default(){
        checkText(project1.projectName);
        return;
    }
}
