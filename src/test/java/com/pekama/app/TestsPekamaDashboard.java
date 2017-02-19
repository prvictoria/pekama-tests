package com.pekama.app;/**
 * Created by VatslauX on 19-Feb-17.
 */

import Page.TestsCredentials;
import Page.TestsCredentials.*;
import Steps.StepsPekama;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.PekamaDashboard.*;
import static Page.TestsUrl.*;
import static Steps.StepsPekama.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static String testProjectTitle = "new test project - "+ randomString(6);
    private static String testContactName = "name"+ randomString(10);
    private static String testContactSurname = "surname"+ randomString(10);
    private static String defaultProjectURL;
    private final static String TEST_USER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private final static String TEST_USER_PEKAMA_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private final static String TEST_USER_XERO_PASSWORD = User3.XERO_PASSWORD.getValue();
    private final String TEST_USER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private final String TEST_USER_TEAM_NAME = User1.TEAM_NAME.getValue();
    @Before
    public void before() {
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                TEST_USER_EMAIL,
                TEST_USER_PEKAMA_PASSWORD,
                URL_LogIn);
    }


    @Test
    public void testA_Gui() {
        DASHBOARD_ProjectsTitle.waitUntil(visible, 20000).shouldBe(visible);
        DASHBOARD_NewProject.shouldBe(visible);
        checkText("No projects yet. Create your first project");

        DASHBOARD_YourProfileTitle.shouldBe(visible);
        //DASHBOARD_TeamName.shouldHave(text(TEST_USER_TEAM_NAME));
        DASHBOARD_Invite.shouldBe(visible);
        DASHBOARD_BuyProjects.shouldBe(visible);

        DASHBOARD_UpcomingTitle.shouldBe(visible);
        DASHBOARD_UpcomingMyDeadlines_RADIO.shouldHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingMyTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        sleep(500);

        DASHBOARD_UpcomingAllDeadlines.click();
        DASHBOARD_UpcomingMyDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllDeadlines_RADIO.shouldHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingMyTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        sleep(500);

        DASHBOARD_UpcomingMyTasks.click();
        DASHBOARD_UpcomingMyDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingMyTasks_RADIO.shouldHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        sleep(500);

        DASHBOARD_UpcomingAllTasks.click();
        DASHBOARD_UpcomingMyDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllDeadlines_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingMyTasks_RADIO.shouldNotHave(cssClass("active-calendar-filter"));
        DASHBOARD_UpcomingAllTasks_RADIO.shouldHave(cssClass("active-calendar-filter"));

        DASHBOARD_TasksTitle.shouldBe(visible);
        DASHBOARD_TasksToDo.shouldBe(visible);
        checkText("No tasks yet. You can create new tasks from projects");
        DASHBOARD_TasksDoing.shouldBe(visible).click();
        checkText("No tasks yet. You can create new tasks from projects");
        sleep(500);

        rootLogger.info("GUI Test passed");
    }

    @Test
    public void testB_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Test
    public void testC_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }


    @Test
    public void testD_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }


    @Test
    public void testE_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

}