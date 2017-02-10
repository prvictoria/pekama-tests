package com.pekama.app;
import Page.TestsCredentials.*;
import Steps.*;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.Emails.EMAIL_REPORT_SUBJECT;
import static Page.Emails.EMAIL_REPORT_TEXT;
import static Page.Emails.EMAIL_SUBJECT;
import static Page.TestsCredentials.GMAIL_PASSWORD;
import static Page.TestsUrl.*;
import static Steps.StepsExternal.checkInboxEmailReport;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.httpAuthUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReports {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String PEKAMA_USER_EMAIL = User3.GMAIL_EMAIL.getValue();

    String GMAIL_LOGIN = User3.GMAIL_EMAIL.getValue();

    @Before
    public void login() {
        holdBrowserAfterTest();
        rootLogger.info("Open URL - " +URL_Dashboard);
//        StepsHttpAuth openHost = new StepsHttpAuth();
        String AUTH_URL = URL_Dashboard;
        httpAuthUrl(AUTH_URL);
        StepsPekama login = new StepsPekama();
        login.submitLoginCredentials(PEKAMA_USER_EMAIL);
        rootLogger.info("Redirect after login to - "+URL_Dashboard);
        sleep(100);

    }
//    @After
//    public void logout(){open(URL_Logout);}


    @Test
    public void sendProjectReport() {
        String thisMailingListName = "Projects Test Mailing List";
        rootLogger.info("Open ProjectValues reports, opened URL - "+URL_ReportsProjects);
        open(URL_ReportsProjects);
        sleep(3000);
        waitForSpinnerNotPresent();
        rootLogger.info("Open Dropdown and create new mailing list");

        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");

        mailingListSendReport(thisMailingListName);
        rootLogger.info("Delete mailing list");

        mailingListDeleteReport(thisMailingListName);
        rootLogger.info("Open Mailing list");

        String EMAIL_TITLE = "Pekama Report \""+thisMailingListName+"\"";
        rootLogger.info("Check email - report");
        checkInboxEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                EMAIL_REPORT_SUBJECT,
                EMAIL_TITLE,
                EMAIL_REPORT_TEXT,
                thisMailingListName);

        rootLogger.info("Email - report present in inbox");
    }
    @Ignore
    @Test
    public void sendTasksReport() {
        open(URL_ReportsTasks);
        rootLogger.info("Open Tasks reports");
    }
    @Ignore
    @Test
    public void sendEventsReport() {
        open(URL_ReportsEvents);
        rootLogger.info("Open ProjectValues reports");

    }
    @Ignore
    @Test
    public void sendChargesReport() {
        open(URL_ReportsCharges);
        rootLogger.info("Open ProjectValues reports");

    }
    @Ignore
    @Test
    public void sendContactsReport() {
        rootLogger.info("Open Contacts reports");
        open(URL_ReportsContacts);
    }
}
