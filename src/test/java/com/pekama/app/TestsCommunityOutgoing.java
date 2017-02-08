package com.pekama.app;
import Steps.*;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.COMMUNITY_TAB_Outgoing;
import static Page.CommunityWizard.*;
import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityOutgoing {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {
        holdBrowserAfterTest();
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(User2.GMAIL_EMAIL.getValue(),             User2.PEKAMA_PASSWORD.getValue(), URL_COMMUNITY_LOGIN);
        rootLogger.info("Redirect back after login");
    }

    @After
    public void after() {
        open(URL_COMMUNITY_LOGOUT);
        rootLogger.info("Open URL - "+URL_COMMUNITY_LOGOUT);
    }

    @Test
    public void testA_RedirectToWizardIfCaseDraft() {
        rootLogger.info("Check default state");
        rootLogger.info("Check redirect back");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("Test passed");
    }

    @Test
    public void testB_CheckCaseWithUniqueName() {

        rootLogger.info("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("Test passed");
    }

    @Test
    public void testC_ArchiveCase() {

        rootLogger.info("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("Test passed");
    }


    @Test
    public void testD_CancelCase() {

        rootLogger.info("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("Test passed");
    }


    @Test
    public void testE_OpenCaseRow() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("Test passed");
    }

}