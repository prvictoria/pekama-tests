package com.pekama.app;
import Page.TestsCredentials.*;
import Steps.*;
import Utils.*;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.*;
import org.junit.*;

import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.TestsUrl.*;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
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
    }
    @Test
    public void sendProjectReport() {
        rootLogger.info("Open Project reports, opened URL - "+urlReportsProjects);
        open(urlReportsProjects);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH)).click();
        //todo step
        rootLogger.info("Open Dropdown and save new mailing list");
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN)).shouldBe(visible);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).shouldBe(disabled);
        String THIS_ML_NAME = "Projects_ML";
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT)).sendKeys(THIS_ML_NAME);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).click();
        //todo step
        rootLogger.info("Open Mailing list");
        String REPORT_NAME = THIS_ML_NAME;
        $(byXpath(REPORTS_MAILING_LISTS_BTN_CALL_ML)).click();
        $(byXpath(MW)).shouldBe(visible);
        $(byText("Mailing List")).shouldNotBe(Condition.visible);

        rootLogger.info("Set checkbox");
        $(byXpath(MW_MAILING_1USER_SELECT)).click();
        rootLogger.info("Set interval");
        $(byXpath(MW_MAILING_1USER_INTERVAL)).sendKeys("999");

        rootLogger.info("Send Project report");
        $(byXpath(MW_MAILING_LIST_BTN_SEND_NOW)).click();


        rootLogger.info("Check email - report");
        //todo use gmail step + add assertions
        rootLogger.info("Email - report present in inbox");
    }
    @Test
    public void sendTasksReport() {
        rootLogger.info("Open Project reports");

    }
    @Test
    public void sendEventsReport() {
        rootLogger.info("Open Project reports");

    }
    @Test
    public void sendChargesReport() {
        rootLogger.info("Open Project reports");

    }
    @Test
    public void sendContactsReport() {
        rootLogger.info("Open Project reports");

    }
}
