package com.pekama.app;
import Page.TestsCredentials.*;
import Steps.*;
import org.apache.logging.log4j.*;
import org.junit.*;

import static Page.TestsUrl.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.httpAuthUrl;
import static com.codeborne.selenide.Selenide.*;

public class PekamaReports {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String PEKAMA_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    String thisMailingListName = "Projects Report Mailing List";

    @Test
    public void selectAgreeCheckbox() {
        rootLogger.info("Open URL - " +URL_Dashboard);
//        StepsHttpAuth openHost = new StepsHttpAuth();
        String AUTH_URL = URL_Dashboard;
        httpAuthUrl(AUTH_URL);
        StepsPekama login = new StepsPekama();
        login.submitLoginCredentials(PEKAMA_USER_EMAIL);
        rootLogger.info("Redirect after login to - "+URL_Dashboard);
        sleep(100);

    }
    @Ignore
    @Test
    public void sendProjectReport() {

        rootLogger.info("Open Project reports, opened URL - "+URL_ReportsProjects);
        open(URL_ReportsProjects);
        sleep(3000);
        waitForSpinnerNotPresent();
        rootLogger.info("Open Dropdown and create new mailing list");
        StepsPekama mailingList = new StepsPekama();
        mailingList.mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);
        rootLogger.info("Delete mailing list");
        mailingListDeleteReport(thisMailingListName);
        rootLogger.info("Open Mailing list");


        rootLogger.info("Check email - report");
        //todo use gmail step + add assertions
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
        rootLogger.info("Open Project reports");

    }
    @Ignore
    @Test
    public void sendChargesReport() {
        open(URL_ReportsCharges);
        rootLogger.info("Open Project reports");

    }
    @Ignore
    @Test
    public void sendContactsReport() {
        rootLogger.info("Open Contacts reports");
        open(URL_ReportsContacts);
    }
}
