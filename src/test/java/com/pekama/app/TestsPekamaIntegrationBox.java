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
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.User1;
import static Page.TestsStrings.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.pekama.app.AllTestsRunner.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaIntegrationBox {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String OWNER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_BOX_PASSWORD = User1.BOX_PASSWORD.getValue();

    private static final String AUTH_URL = URL_Dashboard;
    private static String pekamaProjectUrl;
    private static String boxProjectName;
    private static String boxTeamFolderUrl;
    private static String boxProjectFolderUrl;


    private static final String FolderNameBeforeConnect = "Folder created before connect";
    private static final String FolderNameAfterConnect = "Folder created after connect";
    private static final String FileNameBeforeConnect = "File created before connect";
    private static final String FileNameAfterConnect = "File created after connect";
    private static boolean teamFolderIsPresent;
    private static boolean projectFolderIsPresent;
    private static boolean beforeConnectFilesPresent;
    private static boolean afterConnectFilesPresent;

    @BeforeClass // TODO: 20-Feb-17 need implement tests
    public static void beforeClass(){
        setEnvironment ();
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
        open(boxLogoutURL);
        clearBrowserCache();
    }


    @Test
    public void testA_PrepareProject() {
        submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);
        boxProjectName = createProject("BOX_TEST_PRJ");
        PROJECT_TAB_DOCS.waitUntil(visible, 20000).click();
        pekamaProjectUrl = getActualUrl();
        rootLogger.info(pekamaProjectUrl);
        PROJECT_TAB_DOCS.click();
        createFolderInRoot(FolderNameBeforeConnect);
        createFileInRoot(MW_DeployDoc_02TemplateExcel, FileNameBeforeConnect);

    }

    @Test
    public void testB_ConnectToBOX() {
//        pekamaProjectUrl = "https://staging.pekama.com/a/projects/29334/files";

        if (pekamaProjectUrl == null){
            Assert.fail("Project url not found");
        }
        openUrlWithBaseAuth(pekamaProjectUrl);
        boxConnectProjectButton.waitUntil(visible, 20000).click();
            rootLogger.info("Process connect to BOX");
            sleep(6000);

        if (checkThatWindowsQtyIs(2)==true){
            rootLogger.info("If BOX auth window present - switch to it");
            switchToChildWindow();
            sleep(6000);
            if(boxWindowSubmit.isDisplayed()) {
                boxWindowEmail.sendKeys(OWNER_EMAIL);
                boxWindowPassword.sendKeys(OWNER_BOX_PASSWORD);
                boxWindowSubmit.click();
                boxWindowSubmit.shouldNot(visible);
                rootLogger.info("Login BOX submitted");
                sleep(4000);
            }
            if(boxWindowAccept.isDisplayed()) {
                boxWindowAccept.click();
                //boxWindowAccept.shouldNot(visible);
                rootLogger.info("BOX app authorized");
                sleep(4000);
            }
        if (checkThatWindowsQtyIs(2)==true)
            {Assert.fail("BOX auth not finished");}

            switchTo().window(PAGE_TITLE_PEKAMA);
            sleep(2000);
            rootLogger.info(checkPageTitle(PAGE_TITLE_PEKAMA));
        }
        if (checkThatWindowsQtyIs(1)==true) {
            if (boxConnectProjectButton.exists() == true) {
                int count = 0;
                do {
                    boxConnectProjectButton.click();
                    sleep(4000);
                    refresh();
                    sleep(2000);
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

    @Test
    public void testC_AddFilesInProject() {
        rootLogger.info("Add files after connect");
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }

        openUrlWithBaseAuth(pekamaProjectUrl);
       // PROJECT_TAB_DOCS.waitUntil(visible, 20000).click();
        createFolderInRoot(FolderNameAfterConnect);
        checkText(FolderNameAfterConnect);
        createFileInRoot(MW_DeployDoc_02TemplateExcel, FileNameAfterConnect);
        checkText(FileNameAfterConnect);
        rootLogger.info("Files after connect created");
    }
    @Test
    public void testD_checkSyncFromPekama() {
        rootLogger.info("Check created files and folders in BOX");
        open(boxLoginURL);
        sleep(6000);
        if(BOX_BTN_SIGN_IN.isDisplayed()) {
            boxWindowEmail.sendKeys(OWNER_EMAIL);
            boxWindowPassword.sendKeys(OWNER_BOX_PASSWORD);
            BOX_BTN_SIGN_IN.click();
            rootLogger.info("Login BOX submitted");
            sleep(4000);
        }
        rootLogger.info("Check Team folder");
        teamFolderIsPresent = checkTextLoop(boxNameFolderTeam1, 15000);
        $(byText(boxNameFolderTeam1)).click();
        sleep(2000);
        boxTeamFolderUrl = getActualUrl();

        rootLogger.info("Check Project folder");
        projectFolderIsPresent = checkTextLoop(boxProjectName, 15000);
        $(byText(boxProjectName)).click();

        rootLogger.info("Check folders inside project");
        checkTextLoop(FolderNameBeforeConnect, 15000);
        checkTextLoop(FileNameBeforeConnect, 5000);
        checkTextLoop(FolderNameAfterConnect, 15000);
        checkTextLoop(FileNameAfterConnect, 5000);

        rootLogger.info("Check in BOX results");
        $(byText(FolderNameAfterConnect)).shouldBe(visible);
        $(byText(FolderNameBeforeConnect)).shouldBe(visible);
        $(byText(FileNameAfterConnect)).shouldBe(visible);
        $(byText(FileNameBeforeConnect)).shouldBe(visible);
        boxProjectFolderUrl = getActualUrl();
    }

    @Test
    public void testE_DeleteFilesAndCheckBOX() {
        rootLogger.info("Delete files and folders");
        if ((teamFolderIsPresent == false) || (projectFolderIsPresent =false)){
            Assert.fail("Team or Project folder not found");
        }
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }
        sleep(4000);
        if (boxConnectProjectButton!=null){
            Assert.fail("ProjectValues not connected to BOX");
        }
        openUrlWithBaseAuth(pekamaProjectUrl);
        PROJECT_TAB_DOCS.waitUntil(visible, 20000).click();
        projectAllCheckbox.click();
        LINK_DELETE.click();
        submitConfirmAction();
        $(byText(PLACEHOLDER_NoFiles)).shouldBe(visible);

        rootLogger.info("Check BOX sync");
        open(boxProjectFolderUrl);
        checkTextNotPresentLoop(FolderNameBeforeConnect, 5000);
        checkTextNotPresentLoop(FileNameBeforeConnect, 5000);
        checkTextNotPresentLoop(FolderNameAfterConnect, 5000);
        checkTextNotPresentLoop(FileNameAfterConnect, 5000);
        boxNoFilesPlaceholder.shouldBe(visible);
        rootLogger.info("Files were deleted from BOX");
    }

    @Test
    public void testF_DeleteProjectAndCheckBOX() {
        if ((teamFolderIsPresent == false) || (projectFolderIsPresent =false)){
            Assert.fail("Team or Project folder not found");
        }
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }
        sleep(4000);
        if (boxConnectProjectButton!=null){
            Assert.fail("ProjectValues not connected to BOX");
        }
        rootLogger.info("Delete files and folders");
        openUrlWithBaseAuth(pekamaProjectUrl);
        deleteProject();

        rootLogger.info("check in box results");
        open(boxTeamFolderUrl);
        checkTextNotPresentLoop(boxProjectName);
        boxNoFilesPlaceholder.shouldBe(visible);
        rootLogger.info("Project folder removed");
    }

}