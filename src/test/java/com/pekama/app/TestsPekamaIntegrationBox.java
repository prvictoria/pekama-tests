package com.pekama.app;/**
 * Created by VatslauX on 18-Jan-17.
 */

import static Page.Box.*;
import static Page.ModalWindows.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.*;
import Utils.Utils;
import Steps.StepsPekama;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.TestsStrings.*;
import static Page.TestsUrl.URL_Dashboard;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

//todo draft cases
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaIntegrationBox {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String PEKAMA_USER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private String PEKAMA_USER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private String AUTH_URL = URL_Dashboard;
    private String pekamaProjectUrl;
    static final String FolderNameBeforeConnect = "Folder created before connect";
    static final String FolderNameAfterConnect = "Folder created after connect";
    static final String FileNameBeforeConnect = "File created before connect";
    static final String FileNameAfterConnect = "File created after connect";
    static final String boxProjectName = "";
    static final String boxProjectFolderUrl = "";

    @Before
    public void before() {
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(PEKAMA_USER_EMAIL, PEKAMA_USER_PASSWORD, AUTH_URL);
        rootLogger.info("");
    }

    @After
    public void after() {

    }
    @Ignore // TODO: 08-Feb-17
    @Test
    public void testA_PrepareProject() {
        String testProjectName = nameProjectBOX+Utils.randomString(5);
        rootLogger.info("Create project - full path via MW");


        projectTabDocs.click();
        $(byText(placeholderNoFiles)).shouldBe(Condition.visible);

        rootLogger.info("Add folder");
        //todo - random+name
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        MW.shouldBe(Condition.visible);
        $(byText(TITLE_MW_NEW_FOLDER)).shouldBe(Condition.visible);
        $(byName("name")).sendKeys(FolderNameBeforeConnect);
        MW_BTN_SAVE.click();
        MW.shouldNotBe(Condition.visible);
        $(byText(FolderNameBeforeConnect)).shouldBe(Condition.visible);
        rootLogger.info("Add folder");
        //todo - random+name
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        MW.shouldBe(Condition.visible);
        MW_DeployDoc_01TemplateWord.shouldBe(Condition.visible).click();
        $(byName("name")).sendKeys(FileNameBeforeConnect);
        MW_DEPLOY_DOC_BTN_CREATE.click();
        MW.shouldNotBe(Condition.visible);
        $(byText(FileNameBeforeConnect)).shouldBe(Condition.visible);

        pekamaProjectUrl = url();
    }
    @Ignore // TODO: 08-Feb-17
    @Test
    public void testB_ConnectToBOX() {
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
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
    @Ignore // TODO: 08-Feb-17
    @Test
    public void testC_AddFilesInProject() {
        rootLogger.info("Add files after connect");
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }
        open(pekamaProjectUrl);
        sleep(4000);
        if (boxConnectProjectButton!=null){
            Assert.fail("ProjectValues not connected to BOX");
        }

        rootLogger.info("");
    }
    @Ignore // TODO: 08-Feb-17
    @Test
    public void testD_checkSyncFromPekama() {
        rootLogger.info("Check created files and folders in BOX");
        open(boxLoginURL);

        if (boxNameFolderTeam1.exists() == false) {
            int count = 1;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info("Try to connect box again" + count);
                if (boxNameFolderTeam1.exists() == true) {
                    break;
                }
            } while (count < 5);
        }
        boxNameFolderTeam1.shouldBe(Condition.visible).click();
        boxNameFolderTeam1.shouldNotBe(Condition.visible);

        if ($(byText(boxProjectName)).exists() == false) {
            int count = 1;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info("Try to connect box again" + count);
                if ($(byText(boxProjectName)).exists() == true) {
                    break;
                }
            } while (count < 5);
        }
        $(byText(boxProjectName)).shouldBe(Condition.visible).click();
        $(byText(boxProjectName)).shouldNotBe(Condition.visible);
        if ($(byText(FolderNameAfterConnect)).exists() == false) {
            int count = 1;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info("Try to connect box again" + count);
                if ($(byText(FolderNameAfterConnect)).exists() == true) {
                    break;
                }
            } while (count < 5);
        }
        if ($(byText(FolderNameBeforeConnect)).exists() == false) {
            int count = 1;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info("Try to connect box again" + count);
                if ($(byText(FolderNameBeforeConnect)).exists() == true) {
                    break;
                }
            } while (count < 5);
        }
        if ($(byText(FileNameAfterConnect)).exists() == false) {
            int count = 1;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info("Try to connect box again" + count);
                if ($(byText(FileNameAfterConnect)).exists() == true) {
                    break;
                }
            } while (count < 5);
        }
        if ($(byText(FileNameBeforeConnect)).exists() == false) {
            int count = 1;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info("Try to connect box again" + count);
                if ($(byText(FileNameBeforeConnect)).exists() == true) {
                    break;
                }
            } while (count < 5);
        }
        rootLogger.info("Check in BOX results");
        $(byText(FolderNameAfterConnect)).shouldBe(Condition.visible);
        $(byText(FolderNameBeforeConnect)).shouldBe(Condition.visible);
        $(byText(FileNameAfterConnect)).shouldBe(Condition.visible);
        $(byText(FileNameBeforeConnect)).shouldBe(Condition.visible);
        String boxProjectFolderUrl = url();

    }
    @Ignore // TODO: 08-Feb-17
    @Test
    public void testE_DeleteFilesAndCheckBOX() {
        rootLogger.info("Delete files and folders");
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }
        sleep(4000);
        if (boxConnectProjectButton!=null){
            Assert.fail("ProjectValues not connected to BOX");
        }
        open(boxProjectFolderUrl);
        projectAllCheckbox.click();
        LINK_DELETE.click();
        StepsPekama.submitConfirmAction();
        $(byText(placeholderNoFiles)).shouldBe(Condition.visible);

        if ($(byText(FileNameBeforeConnect)).exists() == true) {
            int count = 1;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info("Try to connect box again" + count);
                if ($(byText(FileNameBeforeConnect)).exists() == false) {
                    break;
                }
            } while (count < 5);
        }
        boxNoFilesPlaceholder.shouldBe(Condition.visible);
        rootLogger.info("Files were deleted from BOX");
     }
    @Ignore // TODO: 08-Feb-17
    @Test
    public void testF_DeleteProjectAndCheckBOX() {
        rootLogger.info("Delete files and folders");
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }
        sleep(4000);
        if (boxConnectProjectButton!=null){
            Assert.fail("ProjectValues not connected to BOX");
        }
        open(boxProjectFolderUrl);
        //todo delete project method
        //todo check in box results
        rootLogger.info("");
    }

}