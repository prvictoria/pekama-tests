package com.pekama.app;
import Page.TestsCredentials.*;
import Steps.*;
import Utils.*;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.openqa.selenium.By;

import static Page.PekamaReports.*;
import static Page.TestsUrl.*;
import static Steps.PekamaSteps.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PekamaReports {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String PEKAMA_USER_EMAIL = User3.GMAIL_EMAIL.getValue();

    @Before
    public void selectAgreeCheckbox() {
        rootLogger.info("Open URL - " +urlDashboard);
        HttpAuth openHost = new HttpAuth();
        String AUTH_URL = urlDashboard;
        openHost.httpAuthWhithCustomLink(AUTH_URL);
        PekamaSteps login = new PekamaSteps();
        login.submitLoginCredentials(PEKAMA_USER_EMAIL);
        rootLogger.info("Redirect after login to - "+urlDashboard);
        sleep(100);

    }
    @Ignore
    @Test
    public void sendProjectReport() {
        String thisMailingListName = "Projects Report Mailing List";
        rootLogger.info("Open Project reports, opened URL - "+urlReportsProjects);
        open(urlReportsProjects);
        sleep(3000);
        waitForSpinnerNotPresent();
        rootLogger.info("Open Dropdown and create new mailing list");
        PekamaSteps mailingList = new PekamaSteps();
//        mailingList.mailingListCreateNew(thisMailingListName);
//        rootLogger.info("Send report");
        mailingList.mailingListSendReport(thisMailingListName);
        rootLogger.info("Delete mailing list");
        mailingList.mailingListDeleteReport(thisMailingListName);
        rootLogger.info("Open Mailing list");


        rootLogger.info("Check email - report");
        //todo use gmail step + add assertions
        rootLogger.info("Email - report present in inbox");
    }
    @Ignore
    @Test
    public void sendTasksReport() {
        rootLogger.info("Open Project reports");

    }
    @Ignore
    @Test
    public void sendEventsReport() {
        rootLogger.info("Open Project reports");

    }
    @Ignore
    @Test
    public void sendChargesReport() {
        rootLogger.info("Open Project reports");

    }

    @Test
    public void sendContactsReport() {
        rootLogger.info("Open Contacts reports");
        open(urlReportsContacts);
    }
}
