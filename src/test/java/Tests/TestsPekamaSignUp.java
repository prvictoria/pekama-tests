package Tests;
import Steps.StepsHttpAuth;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.Timeout;

import java.io.IOException;

import static Page.Emails.*;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.*;
import static Page.PekamaSignUp.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Steps.StepsExternal.checkInboxEmail;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class TestsPekamaSignUp {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String passwordFieldValue = "";
    public String EXIST_USER = User1.GMAIL_EMAIL.getValue();
    private String NEW_USER = User5.GMAIL_EMAIL.getValue();
    String actualBackLink;
    SelenideElement EMAIL_SUBJECT = EMAIL_CONFIRM_REGISTRATION_SUBJECT;
    String EMAIL_TITLE = EMAIL_CONFIRM_REGISTRATION_TITLE;
    String EMAIL_TEXT = EMAIL_CONFIRM_REGISTRATION_TEXT;
    String EMAIL_BTN = EMAIL_CONFIRM_REGISTRATION_BTN;
    SelenideElement EMAIL_REDIRECT_LINK = EMAIL_CONFIRM_REGISTRATION_BACKLINK;
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void selectAgreeCheckbox() {
        rootLogger.info("Open URL - "+URL_SingUp);
        StepsHttpAuth openHost = new StepsHttpAuth();
        String AUTH_URL = URL_SingUp;
        openHost.openUrlWithBaseAuth(AUTH_URL);
        $(signupNext).shouldBe(visible).shouldNotBe(disabled);
        $(signupAgree).shouldBe(selected);
        rootLogger.info("Opened - " +URL_SingUp);
    }
    @AfterClass
    public static void afterClass() {
        clearBrowserCache();
    }
    @Test
    public void allFieldsEmpty() {
    //check default form state
        $(signupEmail).shouldBe(visible).shouldBe(empty);
        $(signupName).shouldBe(visible).shouldBe(empty);
        $(signupSurname).shouldBe(visible).shouldBe(empty);
        $(signupCompany).shouldBe(visible).shouldBe(empty);
        $(signupPassword).shouldBe(visible).shouldBe(empty);
        $(signupUpload).shouldBe(visible).shouldBe();
        $(signupSubscribeNews).shouldBe(visible).shouldBe(selected);
    //        $(signupAgree).shouldBe(visible).shouldNot(selected);
        signupTerms.shouldBe(visible);
    //  $(signupNext).shouldBe(visible).shouldBe(disabled);
  //        $(signupAgree).setSelected(true).shouldBe(selected);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(5));
        $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
//        $(signupAgree).shouldBe(visible).shouldNot(selected);
        rootLogger.info("Test if all fields are blank - passed");
    }
    @Test
    public void onlyEmailSubmitted() {
        $(signupEmail).sendKeys(VALID_EMAIL);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);

        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlyNameSubmitted() {
        $(signupName).sendKeys(VALID_NAME);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
        rootLogger.info("Test submit only"+signupName+"- Passed");
    }
    @Test
    public void onlySurnameSubmitted() {
        $(signupSurname).sendKeys(VALID_SURNAME);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
        rootLogger.info("Test submit only"+signupSurname+"- Passed");
    }
    @Test
    public void onlyCompanySubmitted() {
        $(signupCompany).sendKeys(VALID_COMPANY);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
        rootLogger.info("Test submit "+signupName+"- Passed");
    }
    @Test
    public void onlyPasswordSubmitted() {
        $(signupPassword).sendKeys(VALID_PASSWORD);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(byText(ERROR_MSG_REQUIRED_FIELD)).shouldBe(visible);
        rootLogger.info("Test submit only"+signupPassword+"- Passed");
    }
    @Test
    public void validationEmail() {

        for (int arrayLength = 0; arrayLength < arrayInvalidEmails.length; arrayLength++) {
                $(signupEmail).sendKeys(arrayInvalidEmails[arrayLength]);
                $(signupNext).shouldBe(visible).shouldNot(disabled).click();
                $$(signupErrorEmail).shouldHave(size(1));
                $(byText(ERROR_MSG_INVALID_EMAIL)).shouldBe(visible);
                refresh();
//                $(signupAgree).shouldBe(visible).shouldNot(selected);
//                $(signupNext).shouldBe(visible).shouldBe(disabled);
                $(signupAgree).setSelected(true).shouldBe(selected);
            }
        rootLogger.info("Email validation LOOP - Passed");
    }
    @Ignore // TODO: 2/14/2017 not actual for daily tests - need refactoring
    @Test
    public void validationPassword() {

        for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
            $(signupEmail).shouldBe(visible).sendKeys(VALID_EMAIL);
            $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
            $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
            $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
            $(signupPassword).shouldBe(visible).sendKeys(arrayInvalidPasswords[arrayLength]);
            $(signupNext).shouldBe(visible).shouldNot(disabled).click();
            sleep(1500);
            signupError.shouldHave(size(1));
//            $(signupNext).shouldBe(disabled);
            refresh();
//            $(signupAgree).shouldBe(visible).shouldNot(selected);
//            $(signupNext).shouldBe(visible).shouldBe(disabled);
            $(signupAgree).setSelected(true).shouldBe(selected);
        }
        rootLogger.info("Password rules validation LOOP - Passed");

    }
    @Test
    public void userExist() {
            $(signupEmail).shouldBe(visible).sendKeys(EXIST_USER);
            $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
            $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
            $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
            $(signupPassword).shouldBe(visible).sendKeys(VALID_PASSWORD);
            $(signupNext).shouldBe(visible).shouldNot(disabled).click();
            sleep(1500);
            $$(signupErrorEmail).shouldHaveSize(1);
            $(byText(ERROR_MSG_EMAIL_IS_USED)).shouldBe(visible);
//            $(signupNext).shouldBe(disabled);
            rootLogger.info(ERROR_MSG_EMAIL_IS_USED);
    }
    @Ignore("not implemented")
    @Test
    public void userExistAlias() {

    for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
        rootLogger.info("Exist user - check by email+some");
        $(signupEmail).shouldBe(visible).sendKeys(VALID_EMAIL);
        $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
        $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
        $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
        $(signupPassword).shouldBe(visible).sendKeys(arrayInvalidPasswords[arrayLength]);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        sleep(1500);
        $$(signupError).shouldHaveSize(1);
        $(signupNext).shouldBe(disabled);

        refresh();
        $(signupAgree).shouldBe(visible).shouldNot(selected);
        $(signupNext).shouldBe(visible).shouldBe(disabled);
        $(signupAgree).setSelected(true).shouldBe(selected);
        }
    }
    @Test @Category(AllEmailsTests.class)
    public void sendSignUpEmail() {
        String GMAIL_LOGIN = User5.GMAIL_EMAIL.getValue();
        String GMAIL_PASSWORD = User5.GMAIL_PASSWORD.getValue();
        rootLogger.info("Check signUp email");
        $(signupEmail).shouldBe(visible).sendKeys(NEW_USER);
        $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
        $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
        $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
        $(signupPassword).shouldBe(visible).sendKeys(VALID_PASSWORD);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        sleep(1000);
        $(byText("Confirm your Account")).shouldBe(visible);
        $(byText("You were sent an email message with the account activation link. Please check your inbox.")).shouldBe(visible);
        rootLogger.info(ERROR_MSG_EMAIL_IS_USED);
        actualBackLink = checkInboxEmail(
                GMAIL_LOGIN,
                GMAIL_PASSWORD,
                EMAIL_SUBJECT,
                EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
            if (actualBackLink == null) {
                rootLogger.info("User followed reset link");
                Assert.fail("Redirect Link not found");
            }
    }
    @Test
    public void joinToTeam() {
        $(signupEmail).shouldBe(visible).sendKeys(VALID_EMAIL);
        $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
        $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
        $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
        $(signupPassword).shouldBe(visible).sendKeys("1#qQ 1#qQ 1#qQ 1#qQ");
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        sleep(1000);
        rootLogger.info("Check join To Team page redirect");
        $(byText(SIGN_UP_JOIN_PAGE_TITLE)).waitUntil(visible, 10000);
        $(byText(SIGN_UP_JOIN_PAGE_TEXT)).shouldBe(visible);
        $(byText(SIGN_UP_JOIN_PAGE_TEAM_SECTION_TITLE)).shouldBe(visible);
        SIGN_UP_JOIN_PAGE_FINISH_BTN.shouldBe(visible);
        SIGN_UP_JOIN_PAGE_DEFAULT_RADIO.shouldBe(visible).shouldBe(selected);
        rootLogger.info("join To Team page elements present");
    }
}

