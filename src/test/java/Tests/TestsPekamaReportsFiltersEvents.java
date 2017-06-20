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
import static Steps.ObjectEvent.checkReportsEventRow;
import static Steps.ObjectProject.*;
import static Steps.ObjectUser.Users.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsPekamaReports.checkActualSortOrderInReports;
import static Steps.StepsPekamaReports.deleteAllEvents;
import static Steps.StepsPekamaReports.selectSortOrderAndCheck;
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
            .projectName("EVENTS")
            .build();
    public final static ObjectEvent eventAuto = new ObjectEvent(ObjectEvent.newBuilder())
            .buildEventInPatent(CASE_CREATED, 0);
    public final static ObjectEvent event1 = new ObjectEvent(ObjectEvent.newBuilder())
            .buildEventInPatent(ABANDONMENT, +20);
    public final static ObjectEvent event2 = new ObjectEvent(ObjectEvent.newBuilder())
            .buildEventInPatent(CASE_CLOSED, +10);
    public final static ObjectEvent event3 = new ObjectEvent(ObjectEvent.newBuilder())
            .buildEventInPatent(GRANT, -20);
    private static final ObjectUser user = new ObjectUser(newBuilder()).buildUser(OWNER);
    static String a;

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        a = "";
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        user.login();
        deleteAllEvents();
        project.createProject(projectEnterPoint.DASHBOARD, project);
        event1.createEvent();
        event2.createEvent();
        event3.createEvent();
        getWebDriver().quit();
    }
    @Before
    public void login() {
        user.login(URL_ReportsEvents);
    }

    @Test
    public void events_sort_last_created(){
        rootLogger.info("Validate sort order and rows by: default = Last created");
        rootLogger.info("BUG https://www.pivotaltracker.com/n/projects/1239770/stories/146474897");
        checkActualSortOrderInReports("Last created", true); //TODO bug - should be false ?
        checkReportsEventRow(1, event3, user, project);
        checkReportsEventRow(2, event2, user, project);
        checkReportsEventRow(3, event1, user, project);
        checkReportsEventRow(4, eventAuto, user, project);
        rootLogger.info("Validate sort order and rows by: "+"Last created");
        selectSortOrderAndCheck("Last created", false);
        checkReportsEventRow(1, event3, user, project);
        checkReportsEventRow(2, event2, user, project);
        checkReportsEventRow(3, event1, user, project);
        checkReportsEventRow(4, eventAuto, user, project);
        rootLogger.info("Test passed");

    }
    @Test
    public void events_sort_last_date(){
        rootLogger.info("Validate sort order and rows by: "+"Date");
        selectSortOrderAndCheck("Date", false);
        checkReportsEventRow(1, event1, user, project);
        checkReportsEventRow(2, event2, user, project);
        checkReportsEventRow(3, eventAuto, user, project);
        checkReportsEventRow(4, event3, user, project);
        rootLogger.info("Validate sort order and rows by: "+"Date");
        selectSortOrderAndCheck("Date", true);
        checkReportsEventRow(1, event3, user, project);
        checkReportsEventRow(2, eventAuto, user, project);
        checkReportsEventRow(3, event2, user, project);
        checkReportsEventRow(4, event1, user, project);
        rootLogger.info("Test passed");
    }
    @Test
    public void events_sort_last_event_type(){
        rootLogger.info("Validate sort order and rows by: "+"Event Type");
        selectSortOrderAndCheck("Event Type", true);
        checkReportsEventRow(1, event1 , user, project);
        checkReportsEventRow(2, event2, user, project);
        checkReportsEventRow(3, eventAuto, user, project);
        checkReportsEventRow(4, event3, user, project);
        rootLogger.info("Validate sort order and rows by: "+"Event Type");
        selectSortOrderAndCheck("Event Type", false);
        checkReportsEventRow(1, event3, user, project);
        checkReportsEventRow(2, eventAuto, user, project);
        checkReportsEventRow(3, event2, user, project);
        checkReportsEventRow(4, event1, user, project);
        rootLogger.info("Test passed");
    }
}
