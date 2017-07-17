package Tests;
import Steps.ObjectFile;
import Steps.ObjectUser;
import Steps.Objects.Emails.ImapService;
import Steps.Objects.Emails.ValidatorEmailSignUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;

import static Pages.PekamaSignUp.*;
import static Pages.DataCredentials.*;
import static Pages.DataStrings.*;
import static Pages.UrlConfiguration.*;
import static Pages.UrlStrings.*;
import static Steps.ObjectFile.FileTypes.*;
import static Steps.ObjectUser.Users.USER_01;
import static Steps.ObjectUser.Users.USER_05;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaSignUp {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static ObjectUser fakeUser = newBuilder().email("fake_user@email.com").build();
    private static final ObjectUser registeredUser = new ObjectUser(newBuilder()).buildUser(USER_01);
    private static final ObjectUser newUser = new ObjectUser(newBuilder()).buildUser(USER_05);
    private static boolean skipBefore = false;
    static ObjectUser invited = new ObjectUser(newBuilder()).buildUser(USER_05);
    @Rule
    public Timeout tests = Timeout.seconds(500);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException, InterruptedException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        new ImapService()
                .setProperties()
                .connectStore(invited)
                .openFolder()
                .markEmailsForDeletion()
                .clearFolder()
                .closeStore();
    }
    @Before
    public void selectAgreeCheckbox() {
        if(skipBefore==false) {
            openUrlWithBaseAuth(URL_SIGNUP);
            submitCookie();
        }
        else {rootLogger.info("Before was skipped");}
    }
    @AfterClass
    public static void clear() throws MessagingException, InterruptedException {
        new ImapService()
                .setProperties()
                .connectStore(invited)
                .openFolder()
                .markEmailsForDeletion()
                .clearFolder()
                .closeStore();
    }
    @Test
    public void allFieldsEmpty() {
        rootLogger.info("Check default form state");
        signupEmail.shouldBe(visible).shouldHave(value(""));
        signupName.shouldBe(visible).shouldHave(value(""));
        signupSurname.shouldBe(visible).shouldHave(value(""));
        signupCompany.shouldBe(visible).shouldHave(value(""));
        signupPassword.shouldBe(visible).shouldHave(value(""));
        signupSelectBusinessType.shouldBe(visible).shouldHave(value(""));
        signupSelectYourRole.shouldBe(visible).shouldHave(value(""));
        signupPhone.shouldBe(visible).shouldHave(value(""));
        signupSelectCountry.shouldBe(visible).shouldHave(value(""));

        signupUpload.shouldBe(visible).shouldHave(value(""));
        signupAgree.shouldBe(selected);
        signupTerms.shouldBe(visible);
        signupNext.shouldBe(visible).shouldBe(enabled).click();
        checkText(ERROR_MSG_REQUIRED_FIELD, 9);
        rootLogger.info("Test if all fields are blank - passed");
    }
    @Test
    public void onlyEmailSubmitted() {
        ObjectUser fakeUser = ObjectUser.newBuilder().build();
        fakeUser.submitSignUp(
                VALID_EMAIL, null, null,
                null,null,null,
                null, null, null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 8);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlyNameSubmitted() {
        ObjectUser fakeUser = ObjectUser.newBuilder().build();
        fakeUser.submitSignUp(
                null,VALID_NAME,  null,
                null,null,null,
                null, null, null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 8);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlySurnameSubmitted() {
        ObjectUser fakeUser = ObjectUser.newBuilder().build();
        fakeUser.submitSignUp(
                null,
                null,
                null,
                VALID_SURNAME,
                null,
                null,
                null, null, null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 8);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlyCompanySubmitted() {
        ObjectUser fakeUser = ObjectUser.newBuilder().build();
        fakeUser.submitSignUp(
                null,
                null,
                null,
                null,
                VALID_COMPANY,
                null,
                null, null, null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 8);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void onlyPasswordSubmitted() {
        ObjectUser fakeUser = ObjectUser.newBuilder().build();
        fakeUser.submitSignUp(
                null,
                VALID_PASSWORD,
                null,
                null,
                null,
                null,
                null, null, null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 8);
        rootLogger.info("Tests if 1 fields is blank - passed");
    }
    @Test
    public void notSubmittedBusinessType() {
        ObjectUser fakeUser = ObjectUser.newBuilder().build();
        fakeUser.submitSignUp(
                VALID_EMAIL,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                null,
                "3", null, null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 3);
        rootLogger.info("Tests if 3 fields is blank - passed");
    }
    @Test
    public void notSubmittedYourRole() {
        ObjectUser fakeUser = ObjectUser.newBuilder().build();
        fakeUser.submitSignUp(
                VALID_EMAIL,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "3",
                null, null, null);
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        checkText(ERROR_MSG_REQUIRED_FIELD, 3);
        rootLogger.info("Tests if 3 fields is blank - passed");
    }
    @Test
    public void validationEmailField() {

        for (int arrayLength = 0; arrayLength < arrayInvalidEmails.length; arrayLength++) {
            ObjectUser userFacke = ObjectUser.newBuilder().build();
            userFacke.submitSignUp(
                    arrayInvalidEmails[arrayLength],
                    null, null,
                    null, null,
                    null,   null,
                    null, null);
            Assert.assertFalse(userFacke.isSignUpSucceed);
                checkText(ERROR_MSG_INVALID_EMAIL, 1);
                checkText(ERROR_MSG_REQUIRED_FIELD, 8);
                refresh();
            }
        rootLogger.info("Email validation LOOP - Passed");
    }
    @Test
    public void validationPassword() {
        for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
            ObjectUser fakeUser = ObjectUser.newBuilder().build();
            fakeUser.submitSignUp(
                    VALID_EMAIL,                    VALID_SURNAME,
                    VALID_NAME,                    VALID_COMPANY,
                    arrayInvalidPasswords[arrayLength],
                    "6",    "5",
                    VALID_PHONE , "1");
            Assert.assertFalse(fakeUser.isSignUpSucceed);
            signupError.filter(visible).shouldHave(sizeGreaterThanOrEqual(1));
            refresh();
        }
        rootLogger.info("Password rules validation LOOP - Passed");
    }
    @Test
    public void userExistValidation() {
        ObjectUser fakeUser = ObjectUser.newBuilder().build();
        fakeUser.submitSignUp(
                registeredUser.email,
                VALID_SURNAME, VALID_NAME,
                VALID_COMPANY, VALID_PASSWORD,
                "4", "4",
                VALID_PHONE, "1");

        Assert.assertFalse(fakeUser.isSignUpSucceed);
            signupErrorEmail.shouldHaveSize(1);
            checkText(ERROR_MSG_EMAIL_IS_USED);
            rootLogger.info(ERROR_MSG_EMAIL_IS_USED);
    }

    @Test
    public void sendSignUpEmail_A1_Send() {
        skipBefore = true;
        rootLogger.info("submitSignUp with valid user");

        invited.submitSignUp(
                invited.email,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "1",
                "1",
                VALID_PHONE, "1");

        Assert.assertTrue(invited.isSignUpSucceed);
        $(byText("Confirm your Account")).shouldBe(visible);
        $(byText("You were sent an email message with the account activation link. Please check your inbox.")).shouldBe(visible);
        rootLogger.info("Check email invite from pekama");
    }
    @Test
    public void sendSignUpEmail_A2_CheckEmail() throws MessagingException, InterruptedException, IOException {
        skipBefore = false;
        new ValidatorEmailSignUp()
                .buildReferenceEmail(invited)
                .getEmailFormInbox()
                .buildValidator()
                .checkEmailBody()
                .assertValidationResult()
                .getSignUpLink();
    }

    @Test
    public void sendSignUpEmail_D1_UploadAvatarJpg() {
        skipBefore = false;

        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "hidden image-preview"));
        ObjectFile fileJpg = new ObjectFile(ObjectFile.newBuilder()).buildFile(JPG);
        fileJpg.uploadFile(signupUploadInput);
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "image-preview"));
        rootLogger.info("Avatar uploaded");

        rootLogger.info("Submit SignUp with valid but fake user");
        rootLogger.info("BUG https://www.pivotaltracker.com/n/projects/1239770/stories/142325561");
        ObjectUser fakeUser = ObjectUser.newBuilder().email("email@email.com").build();
        fakeUser.submitSignUp(
                fakeUser.email,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "1",
                "4",
                VALID_PHONE, "1");
        Assert.assertTrue(fakeUser.isSignUpSucceed);
        $(byText("Confirm your Account")).shouldBe(visible).shouldBe(visible);
    }
    @Test
    public void sendSignUpEmail_D2_UploadAvatarPng() {
        skipBefore = false;
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "hidden image-preview"));
        ObjectFile filePng = new ObjectFile(ObjectFile.newBuilder()).buildFile(PNG);
        filePng.uploadFile(signupUploadInput);
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "image-preview"));
        rootLogger.info("Avatar uploaded");

        rootLogger.info("Submit SignUp with valid but fake user");
        ObjectUser fakeUser = ObjectUser.newBuilder().email("email@email.com").build();
        fakeUser.submitSignUp(
                fakeUser.email,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "4",
                "1",
                VALID_PHONE, "1");
        Assert.assertTrue(fakeUser.isSignUpSucceed);
        $(byText("Confirm your Account")).shouldBe(visible).shouldBe(visible);
    }
    @Test
    public void sendSignUpEmail_D3_UploadAvatarIcon() {
        skipBefore = false;
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "hidden image-preview"));
        ObjectFile fileIcon = new ObjectFile(ObjectFile.newBuilder()).buildFile(ICO);
        fileIcon.uploadFile(signupUploadInput);
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "image-preview"));
        rootLogger.info("Avatar uploaded");

        rootLogger.info("Submit SignUp with valid but fake user");
        rootLogger.info("BUG https://www.pivotaltracker.com/n/projects/1239770/stories/142325561");
        fakeUser.submitSignUp(
                fakeUser.email,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "2",
                "3",
                VALID_PHONE, "1");
        Assert.assertTrue(fakeUser.isSignUpSucceed);
        $(byText("Confirm your Account")).shouldBe(visible).shouldBe(visible);
    }
    @Test
    public void sendSignUpEmail_D4_UploadAvatarSvg_Validation() {
        skipBefore = false;
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "hidden image-preview"));
        ObjectFile fileSvg = new ObjectFile(ObjectFile.newBuilder()).buildFile(SVG);
        fileSvg.uploadFile(signupUploadInput);
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "image-preview"));
        rootLogger.info("Avatar uploaded - but not displayed");

        rootLogger.info("Submit SignUp with valid but fake user");
        fakeUser.submitSignUp(
                fakeUser.email,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "1",
                "3",
                VALID_PHONE, "1");
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        $(byText("Upload a valid image. The file you uploaded was either not an image or a corrupted image.")).shouldBe(visible);
    }
    @Test
    public void sendSignUpEmail_D5_UploadAvatarPdf_Validation() {
        skipBefore = false;
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "hidden image-preview"));
        ObjectFile filePdf = new ObjectFile(ObjectFile.newBuilder()).buildFile(PDF);
        filePdf.uploadFile(signupUploadInput);
        signupUpAvatar.waitUntil(exist, 10000).shouldHave(attribute("class", "image-preview"));

        rootLogger.info("Submit SignUp with valid but fake user");
        fakeUser.submitSignUp(
                fakeUser.email,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "3",
                "1",
                VALID_PHONE, "1");
        Assert.assertFalse(fakeUser.isSignUpSucceed);
        $(byText("Upload a valid image. The file you uploaded was either not an image or a corrupted image.")).shouldBe(visible);
    }
    @Test
    public void joinToTeam() {
        fakeUser.submitSignUp(
                VALID_EMAIL,
                VALID_SURNAME,
                VALID_NAME,
                VALID_COMPANY,
                VALID_PASSWORD,
                "2",
                "2",
                VALID_PHONE, "1");
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

