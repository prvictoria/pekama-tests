package com.pekama.app;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.Box.*;
import static Page.ModalWindows.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.User1;
import static Page.TestsStrings.*;
import static Page.UrlStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.pekama.app.AllTestsRunner.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaIntegrationBox {
    static final Logger rootLogger = LogManager.getRootLogger();
    private final String OWNER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private final String OWNER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private final String OWNER_BOX_PASSWORD = User1.BOX_PASSWORD.getValue();

    private final String AUTH_URL = URL_Dashboard;
    private String pekamaProjectUrl;
    private String boxProjectName;
    private String boxProjectFolderUrl;

    private final String FolderNameBeforeConnect = "Folder created before connect";
    private final String FolderNameAfterConnect = "Folder created after connect";
    private final String FileNameBeforeConnect = "File created before connect";
    private final String FileNameAfterConnect = "File created after connect";


    @BeforeClass // TODO: 20-Feb-17 need implement tests
    public void beforeClass(){
        setBrowser();
        holdBrowserAfterTest();
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                OWNER_EMAIL,
                OWNER_PASSWORD,
                AUTH_URL);
    }
    @Ignore
    @Before
    public void before() {
    }
    @AfterClass
    public static void afterClass() {
        open(URL_Logout);
        clearBrowserCache();
    }

    @Ignore
    @Test
    public void testA_PrepareProject() {
        boxProjectName = createProject();
        pekamaProjectUrl = url();
        PROJECT_TAB_DOCS.click();
        createFolderInRoot(FolderNameBeforeConnect);
        createFileInRoot(MW_DeployDoc_02TemplateExcel, FileNameBeforeConnect);

    }
    @Ignore
    @Test
    public void testB_ConnectToBOX() {
        if (pekamaProjectUrl == null){
            Assert.fail("Project url not found");
        }
        if(boxConnectProjectButton.isDisplayed()) {
            boxConnectProjectButton.click();
            rootLogger.info("Process connect to BOX");
            sleep(6000);
        }
        if (checkThatWindowsQtyIs(2)==true){
            rootLogger.info("If BOX auth window present - switch to it");
            switchToChildWindow();
            sleep(6000);
            if(boxWindowSubmit.isDisplayed()) {
                boxWindowEmail.sendKeys(OWNER_EMAIL);
                boxWindowPassword.sendKeys(OWNER_BOX_PASSWORD);
                boxWindowSubmit.click();
                sleep(2000);
                boxWindowSubmit.shouldNotBe(visible);
                rootLogger.info("Login BOX submitted");
                sleep(4000);
            }
            if(boxWindowAccept.isDisplayed()) {
                boxWindowAccept.click();
                sleep(2000);
                boxWindowAccept.shouldNotBe(visible);
                rootLogger.info("BOX app authorized");
                sleep(4000);
            }
            switchTo().window(PAGE_TITLE_PEKAMA);
            sleep(2000);
            rootLogger.info(checkPageTitle(PAGE_TITLE_PEKAMA));

        }
        if (checkThatWindowsQtyIs(1)==true) {
            if (boxConnectProjectButton.exists() == true) {
                int count = 0;
                do {
                    boxConnectProjectButton.click();
                    sleep(10000);
                    refresh();
                    count++;
                    rootLogger.info("Try to connect box again" + count);
                    if (boxConnectProjectButton.exists() == false) {
                        rootLogger.info("Box is connected");
                        break;
                    }
                } while (count < 5);
            }
            rootLogger.info("Box cloud be connected, but button still present");
        }

    }
    @Ignore
    @Test
    public void testC_AddFilesInProject() {
        rootLogger.info("Add files after connect");
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }
        String actualUrl = url();
        Assert.assertEquals("Check current url", pekamaProjectUrl, actualUrl);
        createFolderInRoot(FolderNameBeforeConnect);
        createFileInRoot(MW_DeployDoc_02TemplateExcel, FileNameBeforeConnect);
        rootLogger.info("Files after connect created");
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
        boxNameFolderTeam1.shouldBe(visible).click();
        boxNameFolderTeam1.shouldNotBe(visible);

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
        $(byText(boxProjectName)).shouldBe(visible).click();
        $(byText(boxProjectName)).shouldNotBe(visible);
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
        $(byText(FolderNameAfterConnect)).shouldBe(visible);
        $(byText(FolderNameBeforeConnect)).shouldBe(visible);
        $(byText(FileNameAfterConnect)).shouldBe(visible);
        $(byText(FileNameBeforeConnect)).shouldBe(visible);
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
        submitConfirmAction();
        $(byText(PLACEHOLDER_NoFiles)).shouldBe(visible);

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
        boxNoFilesPlaceholder.shouldBe(visible);
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