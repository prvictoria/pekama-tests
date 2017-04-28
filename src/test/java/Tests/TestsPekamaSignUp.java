package Tests;
import Steps.MessagesIMAP;
import Steps.User;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.Emails.*;
import static Page.PekamaSignUp.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.MessagesValidator.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaSignUp {
    static final Logger rootLogger = LogManager.getRootLogger();
    static String login = null;
    static String password = null;
    public String EXIST_USER = User1.GMAIL_EMAIL.getValue();
    private String NEW_USER = User5.GMAIL_EMAIL.getValue();
    String actualBackLink;
    SelenideElement EMAIL_SUBJECT = EMAIL_CONFIRM_REGISTRATION_SUBJECT;
    String EMAIL_TITLE = EMAIL_CONFIRM_REGISTRATION_TITLE;
    String EMAIL_TEXT = EMAIL_CONFIRM_REGISTRATION_TEXT;
    String EMAIL_BTN = EMAIL_CONFIRM_REGISTRATION_BTN;
    SelenideElement EMAIL_REDIRECT_LINK = EMAIL_CONFIRM_REGISTRATION_BACKLINK;
    private static boolean skipBefore = false;
    @Rule
    public Timeout tests = Timeout.seconds(500);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void selectAgreeCheckbox() {
        if(skipBefore==false) {
            clearBrowserCache();
            rootLogger.info("Open URL - " + URL_SingUp);
            String AUTH_URL = URL_SingUp;
            openUrlWithBaseAuth(AUTH_URL);
            $(signupNext).shouldBe(visible).shouldNotBe(disabled);
            $(signupAgree).shouldBe(selected);
            submitCookie();
            rootLogger.info("Opened - " + URL_SingUp);
        }
        else {rootLogger.info("Before was skipped");}
    }

    @Test
    public void allFieldsEmpty() {
    //check default form state
        signupEmail.shouldBe(visible).shouldHave(value(""));
        signupName.shouldBe(visible).shouldHave(value(""));
        signupSurname.shouldBe(visible).shouldHave(value(""));
        signupCompany.shouldBe(visible).shouldHave(value(""));
        signupPassword.shouldBe(visible).shouldHave(value(""));
        signupUpload.shouldBe(visible).shouldHave(value(""));
        // signupSubscribeNews.shouldBe(visible).shouldBe(selected);
        signupAgree.shouldBe(selected);
        signupTerms.shouldBe(visible);
        signupNext.shouldBe(visible).shouldBe(enabled).click();
        checkText(ERROR_MSG_REQUIRED_FIELD, 7);
        rootLogger.info("Test if all fields are blank - passed");
    }
    @Test
    public void onlyEmailSubmitted() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                VALID_EMAIL,
                null,
                null,
                null,
                null,
                null,
                null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 6);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlyNameSubmitted() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                null,
                null,
                VALID_NAME,
                null,
                null,
                null,
                null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 6);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlySurnameSubmitted() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                null,
                null,
                null,
                VALID_SURNAME,
                null,
                null,
                null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 6);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlyCompanySubmitted() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                null,
                null,
                null,
                null,
                VALID_COMPANY,
                null,
                null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 6);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlyPasswordSubmitted() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                null,
                VALID_PASSWORD,
                null,
                null,
                null,
                null,
                null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 6);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void notSubmittedBusinessType() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                VALID_EMAIL,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                null,
                "3");
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 1);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void notSubmittedYourRole() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                VALID_EMAIL,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "3",
                null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 1);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void validationEmailField() {

        for (int arrayLength = 0; arrayLength < arrayInvalidEmails.length; arrayLength++) {
            User userFacke = new User();
            userFacke.submitSignUp(
                    arrayInvalidEmails[arrayLength],
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            Assert.assertFalse(userFacke.isSignUpSucceed);
                checkText(ERROR_MSG_INVALID_EMAIL, 1);
                checkText(ERROR_MSG_REQUIRED_FIELD, 6);
                refresh();
            }
        rootLogger.info("Email validation LOOP - Passed");
    }
    @Test
    public void validationPassword() {
        for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
            User fakeUser = new User();
            fakeUser.submitSignUp(
                    VALID_EMAIL,
                    VALID_SURNAME,
                    VALID_NAME,
                    VALID_COMPANY,
                    arrayInvalidPasswords[arrayLength],
                    "6",
                    "5");
            Assert.assertFalse(fakeUser.isSignUpSucceed);
            signupError.filter(visible).shouldHave(sizeGreaterThanOrEqual(1));
            refresh();
        }
        rootLogger.info("Password rules validation LOOP - Passed");
    }
    @Test
    public void userExist() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                EXIST_USER,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "4",
                "4");
        Assert.assertFalse(fakeUser.isSignUpSucceed);
            signupErrorEmail.shouldHaveSize(1);
            checkText(ERROR_MSG_EMAIL_IS_USED);
           // $(byText(ERROR_MSG_EMAIL_IS_USED)).shouldBe(visible);
            rootLogger.info(ERROR_MSG_EMAIL_IS_USED);
    }

    @Test @Category(AllEmailsTests.class)
    public void sendSignUpEmail_A_Send() {

        ValidationSignUp.userEmail = User5.GMAIL_EMAIL.getValue();
        rootLogger.info("submitSignUp with valid user");
        User fakeUser = new User();
        fakeUser.submitSignUp(
                NEW_USER,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "1",
                "1");
        Assert.assertTrue(fakeUser.isSignUpSucceed);
        $(byText("Confirm your Account")).shouldBe(visible);
        $(byText("You were sent an email message with the account activation link. Please check your inbox.")).shouldBe(visible);
        rootLogger.info("Check email invite from pekama");
        skipBefore = true;
    }
    @Test @Category(AllEmailsTests.class)
    public void sendSignUpEmail_B_CheckEmail() {
        login = User5.GMAIL_EMAIL.getValue();
        password = User5.GMAIL_PASSWORD.getValue();
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailSignUp(login, password);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
        skipBefore = false;
    }

    @Test
    public void joinToTeam() {
        User fakeUser = new User();
        fakeUser.submitSignUp(
                VALID_EMAIL,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "2",
                "2");

        Assert.assertTrue(fakeUser.isSignUpSucceed);

        rootLogger.info("Check join To Team page redirect");
        $(byText(SIGN_UP_JOIN_PAGE_TITLE)).waitUntil(visible, 10000);
        $(byText(SIGN_UP_JOIN_PAGE_TEXT)).shouldBe(visible);
        $(byText(SIGN_UP_JOIN_PAGE_TEAM_SECTION_TITLE)).shouldBe(visible);
        SIGN_UP_JOIN_PAGE_FINISH_BTN.shouldBe(visible);
        SIGN_UP_JOIN_PAGE_DEFAULT_RADIO.shouldBe(visible).shouldBe(selected);
        rootLogger.info("join To Team page elements present");
    }
}

