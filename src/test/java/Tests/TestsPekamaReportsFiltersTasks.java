package Tests;

import Steps.ObjectProject;
import Steps.ObjectTask;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Pages.DataCredentials.CaseType.PATENT;
import static Pages.UrlConfiguration.setEnvironment;
import static Pages.UrlStrings.URL_ReportsTasks;
import static Steps.ObjectUser.Users.OWNER;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsPekamaReports.deleteAllTasks;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by VatslauX on 19-May-17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReportsFiltersTasks {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static ObjectProject project = ObjectProject.newBuilder()
            .projectMatterType(PATENT.getValue())
            .projectDefining("CANADA")
            .projectName("EVENTS_")
            .build();
    private static final ObjectUser user = new ObjectUser(newBuilder()).buildUser(OWNER);
    private static ObjectTask task1 = null;
    private static ObjectTask task2 = null;
    private static ObjectTask task3 = null;
    private static ObjectTask task4 = null;

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        user.login();
        project.createProject(ObjectProject.projectEnterPoint.DASHBOARD, project);

        getWebDriver().quit();
    }
    @Before
    public void login() {
        user.login(URL_ReportsTasks);
    }

    @Test
    public void tasks_delete_all(){
        deleteAllTasks();
        rootLogger.info("Test passed");
    }
}
