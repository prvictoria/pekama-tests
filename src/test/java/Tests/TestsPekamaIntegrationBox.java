package Tests;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.Box.*;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaProject.*;
import static Page.TestsCredentials.User1;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.StepsExternal.loginBox;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
import static Tests.BeforeTestsSetUp.*;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaIntegrationBox {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String OWNER_EMAIL = User1.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD = User1.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_BOX_PASSWORD = User1.BOX_PASSWORD.getValue();

    private static String pekamaProjectUrl;
    private static String boxProjectName;
    private static String boxTeamFolderUrl;
    private static String boxProjectFolderUrl;
    private static String actualBoxProjectName;
    private static boolean skipBefore = false;

    private static final String FolderNameBeforeConnect = "Folder created before connect";
    private static final String FolderNameAfterConnect = "Folder created after connect";
    private static final String FileNameBeforeConnect = "File created before connect";
    private static final String FileNameAfterConnect = "File created after connect";
    private static boolean teamFolderIsPresent;
    private static boolean projectFolderIsPresent;
    private static boolean beforeConnectFilesPresent;
    private static boolean afterConnectFilesPresent;
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void before() {
        clearBrowserCache();
        if (skipBefore==false) {
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    OWNER_EMAIL,
                    OWNER_PASSWORD,
                    URL_Dashboard);
        }
        else {rootLogger.info("Before was skipped");}
    }
    @AfterClass
    public static void afterClass() {
       openUrlWithBaseAuth(URL_Logout);
       open(boxLogoutURL);
    }

    @Test
    public void testA_PrepareProject() {
        skipBefore=false;
        submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);
        String projectName = submitMwNewProject("BOX_TEST_PRJ");
        String projectFullName = PROJECT_FULL_NAME.getText();
        boxProjectName = ""+" "+projectName;
        //TM.PN.029350 BOX_TEST_PRJ_0F9TQOOGXM  pekama - BOX_TEST_PRJ_0F9TQOOGXM (TM.PN.029350)
        PROJECT_TAB_DOCS.waitUntil(visible, 20000).click();
        pekamaProjectUrl = getActualUrl();
        rootLogger.info(pekamaProjectUrl);
        PROJECT_TAB_DOCS.click();
        createFolderInRoot(FolderNameBeforeConnect);
        createFileInRoot(MW_DeployDoc_02TemplateExcel, FileNameBeforeConnect);
        rootLogger.info("Test passed");
    }

    @Test
    public void testB_ConnectToBOX() {
        skipBefore=false;
       // pekamaProjectUrl = "https://staging.pekama.com/a/projects/29350/files";
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
                    rootLogger.info("Try to connect box again, loop# " + count);
                    if (boxConnectProjectButton.exists() == false) {
                        rootLogger.info("Box is connected");
                        break;
                    }
                } while (count < 5);
            }
            rootLogger.info("Box cloud be connected, but button still present");
        }
        rootLogger.info("Test passed");
    }

    @Test
    public void testC_AddFilesInProject() {
        skipBefore=true;
        //pekamaProjectUrl = "https://staging.pekama.com/a/projects/29350/files";
        rootLogger.info("Add files after connect");
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }

        openUrlWithBaseAuth(pekamaProjectUrl);
        createFolderInRoot(FolderNameAfterConnect);
        checkText(FolderNameAfterConnect);
        createFileInRoot(MW_DeployDoc_02TemplateExcel, FileNameAfterConnect);
        checkText(FileNameAfterConnect);
        rootLogger.info("Files after connect created");
        rootLogger.info("Test passed");
    }
    @Test
    public void testD_checkSyncToBOX() {
        skipBefore=false;
        //boxProjectName = "BOX_TEST_PRJ_0F9TQOOGXM";
        //pekamaProjectUrl = "https://staging.pekama.com/a/projects/29350/files";
        if (pekamaProjectUrl ==null){
            Assert.fail("ProjectValues url not found");
        }

        rootLogger.info("Check created files and folders in BOX");
        loginBox(OWNER_EMAIL, OWNER_BOX_PASSWORD);
        rootLogger.info("Check Team folder");
        teamFolderIsPresent = checkTextLoop(boxNameFolderTeam1, 15000);
        $(byText(boxNameFolderTeam1)).click();
        sleep(2000);
        boxTeamFolderUrl = getActualUrl();

        rootLogger.info("Check Project folder");

        projectFolderIsPresent = checkMatchedTextLoop(boxProjectName, 15000);
        actualBoxProjectName = $(withText(boxProjectName)).getText();
        $(byText(actualBoxProjectName)).click();

        rootLogger.info("Check folders inside project");
        rootLogger.info(FolderNameBeforeConnect+" "+checkTextLoop(FolderNameBeforeConnect, 15000));
        rootLogger.info(FileNameBeforeConnect+" "+checkTextLoop(FileNameBeforeConnect, 15000));
        rootLogger.info(FolderNameAfterConnect+" "+checkTextLoop(FolderNameAfterConnect, 15000));
        rootLogger.info(FileNameAfterConnect+" "+checkTextLoop(FileNameAfterConnect, 15000));

        rootLogger.info("Check in BOX results");
        $(byText(FolderNameBeforeConnect)).shouldBe(visible);
        $(byText(FileNameBeforeConnect)).shouldBe(visible);
        rootLogger.info("Sync files before connect passed");

        $(byText(FolderNameAfterConnect)).shouldBe(visible);
        $(byText(FileNameAfterConnect)).shouldBe(visible);
        rootLogger.info("Sync files after connect passed");

        boxProjectFolderUrl = getActualUrl();
        rootLogger.info("Test passed");
    }

    @Test
    public void testE_DeleteFilesAndCheckSyncToBOX() {
        rootLogger.info("Delete files and folders");
        if (boxTeamFolderUrl == null){
        Assert.fail("Team or Project folder not found");
    }
        if (pekamaProjectUrl == null){
        Assert.fail("Project url not found");
    }
        openUrlWithBaseAuth(pekamaProjectUrl);
        PROJECT_TAB_DOCS.waitUntil(visible, 20000).click();
        projectAllCheckbox.click();
        LINK_DELETE.click();
        submitConfirmAction();
        $(byText(PLACEHOLDER_NoFiles)).shouldBe(visible);

        rootLogger.info("Check BOX sync");
        loginBox(boxProjectFolderUrl, OWNER_EMAIL, OWNER_BOX_PASSWORD);
        checkTextNotPresentLoop(FolderNameBeforeConnect, 15000);
        checkTextNotPresentLoop(FileNameBeforeConnect, 15000);
        checkTextNotPresentLoop(FolderNameAfterConnect, 15000);
        checkTextNotPresentLoop(FileNameAfterConnect, 15000);
        rootLogger.info("Check in BOX results");
        $(byText(FolderNameBeforeConnect)).shouldNotBe(visible);
        $(byText(FileNameBeforeConnect)).shouldNotBe(visible);
        rootLogger.info("Sync files before connect passed");

        $(byText(FolderNameAfterConnect)).shouldNotBe(visible);
        $(byText(FileNameAfterConnect)).shouldNotBe(visible);
        rootLogger.info("Sync files after connect passed");
        boxNoFiles.waitUntil(visible, 20000);
        boxNoFilesPlaceholder.shouldBe(exist);

        rootLogger.info("Files were deleted from BOX");
        rootLogger.info("Test passed");
    }

    @Test
    public void testF_DeleteProjectAndCheckSyncToBOX() {
        if (boxTeamFolderUrl == null){
            Assert.fail("Team or Project folder not found");
        }
        if (pekamaProjectUrl == null){
            Assert.fail("Project url not found");
        }
        
        rootLogger.info("Delete files and folders");
        openUrlWithBaseAuth(pekamaProjectUrl);
        scrollUp();
        deleteProject();

        rootLogger.info("check in box results");
        loginBox(boxTeamFolderUrl, OWNER_EMAIL, OWNER_BOX_PASSWORD);
        checkTextNotPresentLoop(boxProjectName);
        rootLogger.info("Project folder removed");
        rootLogger.info("Test passed");
    }

}