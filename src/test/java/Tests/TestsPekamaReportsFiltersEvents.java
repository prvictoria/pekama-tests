package Tests;

import Page.TestsCredentials;
import Steps.ObjectEvent;
import Steps.ObjectProject;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.TestsCredentials.*;
import static Page.TestsCredentials.CaseType.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.ObjectEvent.PatentEventTypes.*;
import static Steps.ObjectEvent.PatentEventTypes.GRANT;
import static Steps.ObjectProject.*;
import static Steps.ObjectUser.Users.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsPekamaReports.deleteAllEvents;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by VatslauX on 19-May-17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReportsFiltersEvents {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static ObjectProject project = ObjectProject.newBuilder()
            .projectMatterType(PATENT.getValue())
            .projectDefining("CANADA")
            .projectName("EVENTS_")
            .build();
    private static final ObjectUser user = new ObjectUser(newBuilder()).buildUser(OWNER);

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        user.login();
        project.createProject(projectEnterPoint.DASHBOARD, project);
        ObjectEvent event1 = new ObjectEvent(ObjectEvent.newBuilder())
                .buildEventInPatent(ABANDONMENT)
                .createEvent();
        ObjectEvent event2 = new ObjectEvent(ObjectEvent.newBuilder())
                .buildEventInPatent(CASE_CLOSED)
                .createEvent();
        ObjectEvent event3 = new ObjectEvent(ObjectEvent.newBuilder())
                .buildEventInPatent(GRANT)
                .createEvent();
        getWebDriver().quit();
    }
    @Before
    public void login() {
        user.login(URL_ReportsEvents);
    }
//    @AfterClass
//    public static void clear(){
//        //user.login(URL_ReportsEvents);
//        deleteAllEvents();
//        //getWebDriver().quit();
//    }
    @Test
    public void events_delete_all(){
        deleteAllEvents();
        rootLogger.info("Test passed");
    }
    @Ignore
    @Test
    public void events_sort_last_created(){

    }
    @Ignore
    @Test
    public void events_sort_last_date(){

    }
    @Ignore
    @Test
    public void events_sort_last_event_type(){

    }
}
