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

import static Page.TestsCredentials.Countries.NETHERLANDS_ANTILES;
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
    private static String TEST_CASE_TYPE = null;

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
        TEST_CASE_TYPE = MATTER_TYPE_TRADEMARK;
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


    }
    @Test
    public void tabInfo_B_editProjectName_validation_nax(){
        rootLogger.info("Validation max length Project name");
        String newProjectName = randomString(1025);
        TAB_INFO_TitleEditButton.click();
        fillField(TAB_INFO_TitleInput, newProjectName);
        TAB_INFO_TitleSave.click();
        checkText(ERROR_MSG_VALIDATION_LENGTH_1024);
        rootLogger.info("Test passed");
    }
    @Test
    public void tabInfo_B_editProjectName_validation_empty(){
        rootLogger.info("Validation null length Project name");
        String newProjectName = "";
        TAB_INFO_TitleEditButton.click();
        fillField(TAB_INFO_TitleInput, newProjectName);
        TAB_INFO_TitleSave.click();
        checkText(ERROR_MSG_BLANK_FIELD);
        rootLogger.info("Test passed");
    }
    
    @Ignore //todo
    @Test
    public void tabInfo_C0_AddNumber_validation() {

    }
    @Test
    public void tabInfo_C1_AddNumber() {
        nextIsImapTest = false;
        String numberType = "Equinox code";
        String numberValue = "2000/17/55-asd";
        numberCreate(numberType, numberValue);
        numberValidateFirstRow(numberType, numberValue);

        String newNumberType = "Reference Number";
        String newNumberValue = "8888-1111-lkjh";
        numberEditInFirstRow(newNumberType, newNumberValue);
        numberValidateInlineForm(newNumberType, newNumberValue);

        numberDelete(null);
        numberValidateFirstRow(null, null);
    }
    @Test
    public void tabInfo_D1_ClassificationValidation() {
        nextIsImapTest = false;
        classificationCreate("Up-to-date", null, null);
        checkText("This field is required.");
        submitEnabledButton(MW_BTN_CANCEL);

        classificationCreate(null, null, LOREM_IPSUM_SHORT);
        checkText("This field is required.");
        submitEnabledButton(MW_BTN_CANCEL);

        classificationCreate(null, "ABCDEFG", null);
        checkText("A valid integer is required.");
        submitEnabledButton(MW_BTN_CANCEL);

        classificationCreate(null, "46", null);
        checkText("Ensure this value is less than or equal to 45.");
        submitEnabledButton(MW_BTN_CANCEL);

        classificationCreate(null, "0", null);
        checkText("Ensure this value is greater than or equal to 1.");
        submitEnabledButton(MW_BTN_CANCEL);
    }
    @Test
    public void tabInfo_D2_ClassificationCrud() {
        nextIsImapTest = false;
        String classNumber = "12";
        String classDescription = "old description";
        classificationCreate(null, classNumber, classDescription);
        classificationValidateClasses("Up-to-date", classDescription);

        String classNewNumber = "23";
        String classNewDescripton = "new description";
        classificationEditFirstRow("Official Data", classNewNumber, classNewDescripton);
        classificationValidateClasses("Official Data", classNewDescripton);

        classificationDelete();
        classificationValidateClasses(null, null);

    }
    @Test
    public void tabInfo_N_selectValues() {
        scrollUp();
        TAB_INFO_PROJECT_TYPE.shouldHave(text(TEST_CASE_TYPE));
        sleep(1000);
        setProjectDefining(NETHERLANDS_ANTILES.getValue());
        sleep(1000);
        setProjectType(TrademarkTypes.BASIC.getValue());
        sleep(2000);
        setProjectSubType("Certification Mark");
        rootLogger.info("Test passed");
    }
}
