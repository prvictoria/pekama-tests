package com.pekama.app;
import Page.TestsCredentials.*;
import Steps.*;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import sun.util.logging.resources.logging;

import static Page.Emails.EMAIL_REPORT_SUBJECT;
import static Page.Emails.EMAIL_REPORT_TEXT;
import static Page.Emails.GMAIL_URL_SIGN_OUT;
import static Page.TestsCredentials.GMAIL_PASSWORD;
import static Page.TestsUrl.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Steps.StepsHttpAuth.httpAuthUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReports {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String PEKAMA_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    String GMAIL_LOGIN = User3.GMAIL_EMAIL.getValue();

    @Before
    public void login() {
        holdBrowserAfterTest(false);
        rootLogger.info("Open URL - " +URL_Dashboard);
        String AUTH_URL = URL_Dashboard;
        httpAuthUrl(AUTH_URL);
        StepsPekama login = new StepsPekama();
        login.submitLoginCredentials(PEKAMA_USER_EMAIL);
        rootLogger.info("Redirect after login to - "+URL_Dashboard);
        sleep(100);
    }
    @After
    public void logout(){open(URL_Logout);}


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

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }

    @Test
    public void sendTasksReport() {
        rootLogger.info("Open Tasks reports");
        open(URL_ReportsTasks);

        String thisMailingListName = "Tasks Test Mailing List";
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

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }

    @Test
    public void sendEventsReport() {
        rootLogger.info("Open Event reports");
        open(URL_ReportsEvents);

        String thisMailingListName = "Events Test Mailing List";
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

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");

    }

    @Test
    public void sendChargesReport() {
        open(URL_ReportsCharges);
        rootLogger.info("Open Charges reports");

        String thisMailingListName = "Charges Test Mailing List";
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

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }

    @Test
    public void sendContactsReport() {
        rootLogger.info("Open Contacts reports");
        open(URL_ReportsContacts);

        String thisMailingListName = "Contacts Test Mailing List";
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

        rootLogger.info("Check email - report");
        checkEmailReport(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                thisMailingListName);
        rootLogger.info("Email - report present in inbox");
        rootLogger.info("Test passed");
    }

    @Test
    public void unsubscribeLink() {
        String thisMailingListName = "Projects Test unsubscribe link";
        rootLogger.info("Open ProjectValues reports, opened URL - "+URL_ReportsProjects);
        open(URL_ReportsProjects);
        sleep(3000);
        waitForSpinnerNotPresent();

        rootLogger.info("Open Dropdown and create new mailing list");
        mailingListCreateNew(thisMailingListName);
        rootLogger.info("Send report");
        mailingListSendReport(thisMailingListName);

        rootLogger.info("Check email - report usubscribe link");
        SelenideElement EMAIL_SUBJECT = EMAIL_REPORT_SUBJECT;
        signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        rootLogger.info("Detect email");
        detectEmail(EMAIL_SUBJECT);
        rootLogger.info("Open email");
        openEmail(EMAIL_SUBJECT);
        rootLogger.info("Check Unsubscribe Link");
        String link = checkUnsubscribeLink();
        rootLogger.info("Delete email");
        deleteEmail();
        rootLogger.info("Empty trash");
        inboxEmptyTrash();
        rootLogger.info("Email deleted");
        open(GMAIL_URL_SIGN_OUT);

        open(link);
        $$(byText("You will no longer receive this report.")).shouldHaveSize(1);

        rootLogger.info("Check checkbox value in mailing list");
        open(URL_ReportsProjects);
        sleep(3000);
        waitForSpinnerNotPresent();
        boolean checkboxValue = mailingListCheckboxValue(thisMailingListName);
        rootLogger.info("Delete mailing list");
        mailingListDeleteReport(thisMailingListName);
            if (link == null)
            {Assert.fail("Unsubscribe Link not found");}
            if (checkboxValue==false)
            {Assert.fail("Checkbox sending interval is still - has ON value");}
        rootLogger.info("Test passed");
    }
}
