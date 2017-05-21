package Tests;

import Steps.*;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.SoftAssertionError;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.awt.*;
import java.io.IOException;

import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import static Page.PekamaProject.*;
import static Page.PekamaTeamSettings.*;
import static Steps.ObjectCharges.checkInvoiceRow;
import static Steps.ObjectTask.checkTaskData;
import static Steps.StepsFactory.*;
import static Steps.StepsFactory.clickSelector;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;

import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.ContactRelation.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.*;
/**
 * Created by VatslauX on 20-May-17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaProjectInfo {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String OWNER_LOGIN_EMAIL = User3.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_TEAM_NAME = User3.TEAM_NAME.getValue();
    private final static String OWNER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();

    private static String projectName;
    private static String projectUrl;
    private static Boolean skipBefore = false;
    private static Boolean nextIsImapTest = false;
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(skipBefore==false) {
            clearBrowserCache();
            User user = new User();
            user.loginByURL(OWNER_LOGIN_EMAIL, OWNER_PASSWORD, URL_LogIn);

            rootLogger.info("Create project");
            submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);
            projectName = submitMwNewProject();
            projectUrl = getActualUrl();

//            deleteAllMembers();
//            addMember("A-member@email.com", TAB_MEMBERS_BTN_ADD);
//            addMember("B-member@office.eu", TAB_MEMBERS_BTN_ADD);

            getWebDriver().quit();
        }
        else {rootLogger.info("Before suite was skipped");
        }
    }
    @Before
    public void login() {
        User user = new User();
        user.loginByURL(OWNER_LOGIN_EMAIL, OWNER_PASSWORD, projectUrl);
        clickSelector(PROJECT_TAB_INFO);
    }

    @Test
    public void tabInfo_A_CheckDefaultStateAndDelete() {
        nextIsImapTest = false;
        scrollUp();
        $$(byText(projectName)).filter(visible).shouldHaveSize(1);
        $$(byText(PLACEHOLDER_NO_CASES)).filter(visible).shouldHaveSize(1);
        // $$(byText(placeholderNoNumbers)).filter(visible).shouldHaveSize(1);
        // todo BUG #140183099 - https://www.pivotaltracker.com/n/projects/1239770/stories/140183099
        $$(byText(PLACEHOLDER_NO_DATA)).filter(visible).shouldHaveSize(1);
        $$(byText(PLACEHOLDER_TeamChat)).shouldHaveSize(1);

        projectButtonPlus.click();
        projectPlusNewEvent.shouldBe(visible);
        projectPlusNewConversation.shouldBe(visible);
        projectPlusNewTask.shouldBe(visible);
        projectPlusNewDocument.shouldBe(visible);
        projectPlusNewFinancial.shouldBe(visible);
        projectPlusNewNumber.shouldBe(visible);
        projectPlusNewContact.shouldBe(visible);
        rootLogger.info("GUI - test passed");
        projectButtonPlus.pressEscape();

        rootLogger.info("Test passed");
    }
    @Test
    public void tabInfo_B_editProjectName() throws AWTException {
        nextIsImapTest = false;
        rootLogger.info("Rename Project by Owner");
        getFullProjectTitle();
        waitForTextPresent(projectName);
        scrollUp();
        TAB_INFO_ProjectTitle.shouldHave(text(projectName));
        TAB_INFO_ProjectTitle.click();
        String newProjectName = "New project name after edition "+ randomString(6);
        fillField(TAB_INFO_TitleInput, newProjectName);
        TAB_INFO_TitleSave.click();
        sleep(1000);
        refresh();
        rootLogger.info("Rename Project by Owner - test passed");
        waitForTextPresent(newProjectName);
        scrollUp();
        TAB_INFO_ProjectTitle.shouldHave(text(newProjectName));
        TAB_INFO_TitleEditButton.click();
        fillField(TAB_INFO_TitleInput, projectName);
        TAB_INFO_TitleSave.click();
        sleep(1000);
        refresh();
        TAB_INFO_ProjectTitle.shouldHave(text(projectName));

        rootLogger.info("Validation max length Project name");
        newProjectName = randomString(1025);
        TAB_INFO_TitleEditButton.click();
        fillField(TAB_INFO_TitleInput, newProjectName);
        TAB_INFO_TitleSave.click();
        sleep(1000);
        checkText(ERROR_MSG_VALIDATION_LENGTH_1024);
        rootLogger.info("Test passed");
    }
}
