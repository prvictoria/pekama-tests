package com.pekama.app;/**
 * Created by VatslauX on 18-Jan-17.
 */

import static Page.Box.*;
import static Page.TestsCredentials.*;

import Steps.PekamaSteps;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import sun.util.logging.resources.logging;

import static Page.TestsUrl.urlDashboard;
import static Page.TestsUrl.urlPersonalSettings;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaIntegrationBox {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String PEKAMA_USER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private String PEKAMA_USER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private String AUTH_URL = urlDashboard;
    private String boxProjectUrl;

    @Before
    public void before() {
        PekamaSteps loginIntoPekama = new PekamaSteps();
        loginIntoPekama.loginByURL(PEKAMA_USER_EMAIL, PEKAMA_USER_PASSWORD, AUTH_URL);
        rootLogger.info("");
    }

    @After
    public void after() {

    }

    @Test
    public void testA_PrepareProject() {
        rootLogger.info("Create project - full path");
        rootLogger.info("Rename project");
        rootLogger.info("Add files and folders");
        boxProjectUrl = url();
    }

    @Test
    public void testB_ConnectToBOX() {
        if (boxProjectUrl==null){
            Assert.fail("Project url not found");
        }
        if(boxConnectProjectButton.isDisplayed()) {
            boxConnectProjectButton.click();
            rootLogger.info("Process connect to BOX");
            sleep(3000);
        }
        if(boxWindowSubmit.isDisplayed()) {
            boxWindowEmail.sendKeys(User1.GMAIL_EMAIL.getValue());
            boxWindowPassword.sendKeys(User1.BOX_PASSWORD.getValue());
            boxWindowSubmit.submit();
            rootLogger.info("Process connect to BOX");
        }
        if(boxWindowAccept.isDisplayed()) {
            boxWindowAccept.click();
            rootLogger.info("Process connect to BOX");
        }
        if (boxConnectProjectButton.exists() == true) {
            int count = 1;
            do {
                boxConnectProjectButton.click();
                sleep(10000);
                refresh();
                count++;
                rootLogger.info("Try to connect box again" + count);
                if (boxConnectProjectButton.exists() == false) {
                    break;
                }
            } while (count < 5);
        }

        rootLogger.info("");
    }

    @Test
    public void testC_AddFilesInProject() {
        rootLogger.info("Add files after connect");
        if (boxProjectUrl==null){
            Assert.fail("Project url not found");
        }
        open(boxProjectUrl);
        sleep(4000);
        if (boxConnectProjectButton!=null){
            Assert.fail("Project not connected to BOX");
        }

        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }
    @Test
    public void testD_checkSyncFromPekama() {
        rootLogger.info("Check created files and folders in BOX");
        open(boxLoginURL);

        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("Check in BOX results");
    }
    @Test
    public void testE_DeleteFilesAndCheckBOX() {
        rootLogger.info("Delete files and folders");
        if (boxProjectUrl==null){
            Assert.fail("Project url not found");
        }
        sleep(4000);
        if (boxConnectProjectButton!=null){
            Assert.fail("Project not connected to BOX");
        }

        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
     }
    @Test
    public void testF_DeleteProjectAndCheckBOX() {
        rootLogger.info("Delete files and folders");
        if (boxProjectUrl==null){
            Assert.fail("Project url not found");
        }
        sleep(4000);
        if (boxConnectProjectButton!=null){
            Assert.fail("Project not connected to BOX");
        }
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

}