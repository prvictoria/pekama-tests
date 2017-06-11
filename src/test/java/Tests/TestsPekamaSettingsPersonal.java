package Tests;
import Steps.ObjectFile;
import Steps.ObjectUser;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.setEnvironment;
import static Steps.ObjectFile.FileTypes.*;
import static Steps.ObjectUser.Users.USER_06;
import static Steps.ObjectUser.newBuilder;
import static Steps.Steps.clickSelectIfEnabled;
import static Steps.Steps.clickSelector;
import static Steps.StepsExternal.submitAuthGmail;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaSettings.*;
import static Tests.BeforeTestsSetUp.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static Page.PekamaPersonalSettings.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaSettingsPersonal {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final ObjectUser user = new ObjectUser(newBuilder()).buildUser(USER_06);
    @Rule
    public Timeout tests = Timeout.seconds(400);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();

    }
    @Before
    public void before() {
        user.login();
    }

    @Test
    public void avatarUpload_jpeg() {
        ObjectFile file =  new ObjectFile(ObjectFile.newBuilder()).buildFile(JPG);
        openSettingsTabPersonalDetails();
        clickSelectIfEnabled(PERSONAL_DETAILS_DELETE_AVATAR);
        clickSelector(PERSONAL_DETAILS_UPLOAD_AVATAR_BTN);
        file.uploadFile();
        clickSelector(PERSONAL_DETAILS_DELETE_AVATAR);
        PERSONAL_DETAILS_DELETE_AVATAR.waitUntil(disabled, 10000);
        rootLogger.info("Test passed");
    }
    @Test
    public void avatarUpload_png() {
        ObjectFile file =  new ObjectFile(ObjectFile.newBuilder()).buildFile(PNG);
        openSettingsTabPersonalDetails();
        clickSelectIfEnabled(PERSONAL_DETAILS_DELETE_AVATAR);
        clickSelector(PERSONAL_DETAILS_UPLOAD_AVATAR_BTN);
        sleep(2000);
        file.uploadFile();
        clickSelector(PERSONAL_DETAILS_DELETE_AVATAR);
        PERSONAL_DETAILS_DELETE_AVATAR.waitUntil(disabled, 10000);
        rootLogger.info("Test passed");
    }
    @Test
    public void avatarUpload_pdf_Validation() {
        ObjectFile file =  new ObjectFile(ObjectFile.newBuilder()).buildFile(PDF);
        openSettingsTabPersonalDetails();
        clickSelectIfEnabled(PERSONAL_DETAILS_DELETE_AVATAR);
        clickSelector(PERSONAL_DETAILS_UPLOAD_AVATAR_BTN);
        sleep(2000);
        file.uploadFile();
        checkText("Upload a valid image. The file you uploaded was either not an image or a corrupted image.");
        rootLogger.info("Test passed - error present");
    }
    @Test
    public void checkGui() {
        openSettingsTabPersonalDetails();
        rootLogger.info("Start test GUI and links");
        PERSONAL_SETTINGS_BTN.waitUntil(visible, 20000).shouldBe(Condition.visible);
        TEAM_SETTINGS_BTN.waitUntil(visible, 20000).shouldBe(Condition.visible);
        PERSONAL_DETAILS_TAB_TITLE.shouldHave(text("Personal details"));
        SECURITY_TAB_TITLE.shouldHave(text("Login & Security"));
        EMAILS_TAB_TITLE.shouldHave(text("Emails"));
        SIGNATURE_TAB_TITLE.shouldHave(text("E-mail signature"));
        IMAP_TAB_TITLE.shouldHave(text("IMAP"));
        TIME_TRACKER_TAB_TITLE.shouldHave(text("Time Tracker"));

        $(byText("First name:")).waitUntil(Condition.visible, 10000);
        $(byText("Last name:")).shouldBe(Condition.visible);
        $(byText("Phone #")).shouldBe(Condition.visible);
        $(byText("Fax #")).shouldBe(Condition.visible);
        $(byText("Mobile #")).shouldBe(Condition.visible);
        $(byText("Legal entity:")).shouldBe(Condition.visible);
        $(byText("Street address:")).shouldBe(Condition.visible);
        $(byText("Post code:")).shouldBe(Condition.visible);
        $(byText("City:")).shouldBe(Condition.visible);
        $(byText("State/Region")).shouldBe(Condition.visible);
        $(byText("Country:")).shouldBe(Condition.visible);
        rootLogger.info("Personal settings GUI is consistent");
    }
    @Test
    public void tabPersonalDetails_Y_SaveUserData() {
        rootLogger.info("Enter and Save Default ObjectUser Data");
        submitPersonalForm(user);
        rootLogger.info("New data saved in all fields");
    }
    @Test
    public void tabPersonalDetails_Z_CheckSavedData() {
        rootLogger.info("Check Saved Data");
        checkPersonalForm(user);
    }

    @Test
    public void tabPersonalDetails_Name_A_empty() {
        rootLogger.info("Validation Name field");
        ObjectUser fakeUser = newBuilder().name("").surname(randomString(20)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_BLANK_NAME);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_NAME);
    }
    @Test
    public void tabPersonalDetails_Name_B_validation() {
        rootLogger.info("Validation max length Name field");
        ObjectUser fakeUser = newBuilder().name(randomString(101)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_100);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_100);
    }
    @Test
    public void tabPersonalDetails_Name_C_save() {
        rootLogger.info("Save and check Name field");
        ObjectUser fakeUser = newBuilder().name(randomString(100)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_NameSurname_A_empty() {
        rootLogger.info("Validation Name field");
        ObjectUser fakeUser = newBuilder().name("").surname("").build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_BLANK_NAME);
        checkText(ERROR_MSG_BLANK_SURNAME);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_SURNAME);
    }

    @Test
    public void tabPersonalDetails_Surname_A_empty() {
        rootLogger.info("Validation Surname field");
        ObjectUser fakeUser = newBuilder().name(randomString(20)).surname("").build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_BLANK_SURNAME);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_SURNAME);
    }
    @Test
    public void tabPersonalDetails_Surname_B_validation() {
        rootLogger.info("Validation max length Surname field");
        ObjectUser fakeUser = newBuilder().surname(randomString(101)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_100);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_100);
    }
    @Test
    public void tabPersonalDetails_Surname_C_save() {
        rootLogger.info("Save and check Name field");
        ObjectUser fakeUser = newBuilder().surname(randomString(100)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_Phone_A_validation() {
        rootLogger.info("Validation max length Phone field");
        ObjectUser fakeUser = newBuilder().phone(randomString(21)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_20);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void tabPersonalDetails_Phone_B_save() {
        rootLogger.info("Save and check Fax field");
        ObjectUser fakeUser = newBuilder().phone(randomString(20)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_Fax_A_validation() {
        ObjectUser fakeUser = newBuilder().fax(randomString(21)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_20);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void tabPersonalDetails_Fax_B_save() {
        rootLogger.info("Save and check Fax field");
        ObjectUser fakeUser = newBuilder().fax(randomString(20)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_Mobile_A_validation() {
        rootLogger.info("Validation max length Mobile field");
        ObjectUser fakeUser = newBuilder().mobile(randomString(21)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_20);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void tabPersonalDetails_Mobile_B_save() {
        rootLogger.info("Save and check Region field");
        ObjectUser fakeUser = newBuilder().mobile(randomString(20)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_LegalEntity_A_validation() {
        rootLogger.info("Validation max length Legal entity field");
        ObjectUser fakeUser = newBuilder().legalEntity(randomString(256)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void tabPersonalDetails_LegalEntity_B_save() {
        rootLogger.info("Save and check Region field");
        ObjectUser fakeUser = newBuilder().legalEntity(randomString(255)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_StreetAddress_A_validation() {
        rootLogger.info("Validation max length Legal entity field");
        ObjectUser fakeUser = newBuilder().street(randomString(256)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void tabPersonalDetails_StreetAddress_B_save() {
        rootLogger.info("Save and check Region field");
        ObjectUser fakeUser = newBuilder().street(randomString(255)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_PostalCode_A_validation() {
        rootLogger.info("Validation max length Post code field");
        ObjectUser fakeUser = newBuilder().zip(randomString(21)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_20);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void tabPersonalDetails_PostalCode_B_save() {
        rootLogger.info("Save and check Region field");
        ObjectUser fakeUser = newBuilder().zip(randomString(20)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_City_A_validation() {
        rootLogger.info("Validation max length City field");
        ObjectUser fakeUser = newBuilder().city(randomString(256)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void tabPersonalDetails_City_B_save() {
        rootLogger.info("Save and check City field");
        ObjectUser fakeUser = newBuilder().city(randomString(255)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_Region_A_validation() {
        openSettingsTabPersonalDetails();
        rootLogger.info("Validation max length Region field");
        ObjectUser fakeUser = newBuilder().region(randomString(256)).build();
        submitPersonalForm(fakeUser);
        checkText(ERROR_MSG_VALIDATION_LENGTH_255);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void tabPersonalDetails_Region_B_save() {
        rootLogger.info("Save and check Region field");
        ObjectUser fakeUser = newBuilder().region(randomString(254)).build();
        submitPersonalForm(fakeUser);
        refresh();
        checkPersonalForm(fakeUser);
    }

    @Test
    public void tabPersonalDetails_SelectCountry_A() {
        openSettingsTabPersonalDetails();
        rootLogger.info("Select new country");
       PERSONAL_DETAILS_COUNTRY_SELECT.shouldBe(visible).click();
       PERSONAL_DETAILS_COUNTRY_INPUT.sendKeys("United Kingdom");
        $(CSS_SelectHighlighted).click();
        //$(byText("United Kingdom")).shouldBe(Condition.exactText("United Kingdom")).click();
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        refresh(); sleep(2000);
       PERSONAL_DETAILS_COUNTRY_SELECT.shouldHave(text("United Kingdom"));

        rootLogger.info("Select user country");
       PERSONAL_DETAILS_COUNTRY_SELECT.click();
       PERSONAL_DETAILS_COUNTRY_INPUT.sendKeys(User3.COUNTRY.getValue());
        $(CSS_SelectHighlighted).click();
        //$(byText("United States")).shouldBe(Condition.exactText("United States")).click();
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        refresh(); sleep(2000);
       PERSONAL_DETAILS_COUNTRY_SELECT.shouldHave(text("United States"));
        rootLogger.info("ObjectUser country - "+User3.COUNTRY.getValue()+" - was selected");
    }

    @Test
    public void tabSecurity_PasswordValidations_A() {
        rootLogger.info("Check state by default");
        openSettingsTabSecurity();
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(Condition.visible, 10000).shouldHave(Condition.value(""));
        SECURITY_TAB_NEW_PASSWORD.shouldHave(Condition.value(""));
        SECURITY_TAB_CONFIRM_PASSWORD.shouldHave(Condition.value(""));
        SECURITY_SAVE_BTN.shouldBe(Condition.visible).shouldBe(Condition.disabled);
        SECURITY_ENABLE_BTN.shouldBe(Condition.visible).shouldBe(Condition.enabled);
        $(byText("2-step verification")).shouldBe(Condition.visible);
        $(byText("Disabled")).shouldBe(Condition.visible);
        rootLogger.info("State by default - PASSED");

        String validPassword = randomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields New passwordPekama & Confirm Password");
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        SECURITY_SAVE_BTN.shouldBe(Condition.disabled);
        $$(byText(ERROR_MSG_WRONG_PASSWORD)).shouldHaveSize(1);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_B() {
        String validPassword = randomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields Current passwordPekama & Confirm Password");
        openSettingsTabSecurity();
        SECURITY_TAB_NEW_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_C() {
        String validPassword = randomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields Current passwordPekama & New Password");
        openSettingsTabSecurity();
        SECURITY_TAB_CONFIRM_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_D() {
        String validPassword = randomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - BUG noValidation - No Current passwordPekama checks MAJOR(Not reproduced)");
        openSettingsTabSecurity();
        SECURITY_TAB_NEW_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_E() {
        String validPassword = randomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no New passwordPekama");
        openSettingsTabSecurity();
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_F() {
        String validPassword = randomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no Confirm passwordPekama");
        openSettingsTabSecurity();
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        SECURITY_TAB_NEW_PASSWORD.sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_G() {
        rootLogger.info("Change Password - not Old can be new passwordPekama");
        openSettingsTabSecurity();
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(visible, 10000).sendKeys(user.passwordPekama);
        SECURITY_TAB_NEW_PASSWORD.sendKeys(user.passwordPekama);
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys(user.passwordPekama);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_NEW_PASSOWRD_EQUALS_TO_OLD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_I() {
        rootLogger.info("All fields - empty string submitted");
        openSettingsTabSecurity();
        SECURITY_TAB_CURRENT_PASSWORD.sendKeys("1");
        SECURITY_TAB_CURRENT_PASSWORD.clear();
        SECURITY_TAB_NEW_PASSWORD.sendKeys("1");
        SECURITY_TAB_NEW_PASSWORD.clear();
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys("1");
        SECURITY_TAB_CONFIRM_PASSWORD.clear();
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_BLANK_FIELD)).shouldHaveSize(3);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_K() {
        rootLogger.info("Max length validation");
        String RANDOM_129_LETTER = randomString(129);
        openSettingsTabSecurity();
        SECURITY_TAB_CURRENT_PASSWORD.sendKeys(User3.PEKAMA_PASSWORD.getValue());
        SECURITY_TAB_NEW_PASSWORD.sendKeys(RANDOM_129_LETTER);
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys(RANDOM_129_LETTER);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_VALIDATION_LENGTH_128)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }

    @Test
    public void tabSecurity_TwoStepVerification_A() {
        openSettingsTabSecurity();
        rootLogger.info("Open MW Enable 2-step verification");
        SECURITY_ENABLE_BTN.shouldBe(Condition.visible).click();
        rootLogger.info("Check MW buttons");
        MW.shouldBe(Condition.visible);
        MW_EnableVerificationClose.shouldBe(Condition.visible);
        MW_EnableVerificationNext.shouldBe(Condition.visible);
        MW_EnableVerificationCountrySelect.shouldBe(Condition.visible).click();
        MW_EnableVerificationCoutryField.shouldBe(Condition.visible).shouldHave(Condition.value("")).sendKeys("Belarus");
        $(CSS_SelectHighlighted).shouldBe(Condition.visible).click();
        $(byText("+375")).shouldBe(Condition.visible);

        rootLogger.info("Modal - Enable 2-Step Verification/ validate incorrect number");
        MW_EnableVerificationTelField.shouldHave(Condition.value("")).sendKeys("123");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("21211: The 'To' number 375123 is not a valid phone number.")).shouldBe(Condition.visible);
        rootLogger.info("Modal - Enable 2-Step Verification/ validate CORRECT number");
        MW_EnableVerificationTelField.clear();
        MW_EnableVerificationTelField.sendKeys("291234567");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("We sent a confirmation code to your phone. Please enter it in the field below.")).shouldBe(Condition.visible);

        MW_EnableVerificationConfirmCodeField.clear();
        MW_EnableVerificationConfirmCodeField.shouldHave(Condition.value("")).sendKeys("12345678901234567890");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("Wrong confirmation code format. It must be a number between 100000 and 999999")).shouldBe(Condition.visible);
        rootLogger.info("validation present");

        MW_EnableVerificationConfirmCodeField.clear();
        MW_EnableVerificationConfirmCodeField.shouldHave(Condition.value("")).sendKeys("100000");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("Wrong confirmation code. Attempts left: 2.")).shouldBe(Condition.visible);
        MW_EnableVerificationConfirmCodeField.clear();
        MW_EnableVerificationConfirmCodeField.shouldHave(Condition.value("")).sendKeys("999999");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("Wrong confirmation code. Attempts left: 1.")).shouldBe(Condition.visible);
        MW_EnableVerificationConfirmCodeField.clear();
        MW_EnableVerificationConfirmCodeField.shouldHave(Condition.value("")).sendKeys("123123");
        MW_EnableVerificationConfirmCodeField.shouldHave(Condition.value("123123"));
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("You're out of attempts, please request a new confirmation code.")).shouldBe(Condition.visible);
        MW_EnableVerificationClose.shouldBe(Condition.visible).click();
        MW.shouldNotBe(Condition.visible);
        rootLogger.info("validation 3 attempts present");

    }
    @Test
    public void tabEmails_A() {
        try{
            openSettingsTabEmails();
            rootLogger.info("Check Defaults");
            checkReceiveEmailOptions(
                    true,
                    false,
                    false,
                    true,
                    false);
            selectReceiveEmailOptions(
                    false,
                    true,
                    false,
                    false,
                    true
            );
            selectReceiveEmailOptions(
                    false,
                    false,
                    true,
                    false,
                    false
            );
    }
    finally {
            selectReceiveEmailOptions(
                    true,
                    false,
                    false,
                    true,
                    false
            );
        }
    }
    @Test
    public void tabSignature_A_Create() {
        openSettingsTabSignature();
        fillTextEditor(LOREM_IPSUM_LONG);
        submitEnabledButton(SIGNATURE_SAVE_BTN);
        rootLogger.info("Check entered text");
        refresh();
        SIGNATURE_TAB_TITLE.click();
        SIGNATURE_TAB_TEXT_EDITOR.shouldHave(text(LOREM_IPSUM_LONG));

    }

    @Test
    public void tabSignature_B_Delete() {
        openSettingsTabSignature();
        submitEnabledButton(SIGNATURE_DELETE_BTN);
        SIGNATURE_TAB_TITLE.click();
        SIGNATURE_TAB_TEXT_EDITOR.shouldHave(text(""));

    }

    @Test
    public void tabIMAP_A1_ManualConnect() {
        deleteImap();
        rootLogger.info("Check Defaults");
        IMAP_TAB_FIELD_USENAME.shouldBe(Condition.visible).shouldBe(empty);
        IMAP_TAB_FIELD_PASSWORD.shouldBe(Condition.visible).shouldBe(empty);
        IMAP_TAB_FIELD_SERVER_NAME.shouldBe(Condition.visible).shouldBe(empty);
        IMAP_TAB_FIELD_PORT.shouldBe(Condition.visible).shouldBe(empty);
        IMAP_TAB_BTN_CONNECT_GMAIL.shouldBe(Condition.visible);
        IMAP_TAB_BTN_SAVE_AND_CHECK.shouldBe(Condition.visible);
        IMAP_TAB_BTN_CHECK.shouldBe(Condition.visible);
        IMAP_TAB_SSL.shouldBe(Condition.visible);
        connectImap(user);
        validateImap(true);
    }
    @Test
    public void tabIMAP_A2_CheckConnectionAndDelete() {
        openSettingsTabIMAP();
        submitEnabledButton(IMAP_TAB_BTN_CHECK);
        validateImap(true);
        deleteImap();
    }
    @Test
    public void tabIMAP_A3_InvalidCredentials(){
        ObjectUser fakeUser = newBuilder().email("1234355@email.com").passwordEmail("12121212").build();
        connectImap(fakeUser);
        validateImap( false);
    }
    @Test
    public void tabIMAP_B_GoggleAuthConnect() {
        deleteImap();
        clickConnectGoogle();
        submitAuthGmail(user);
        IMAP_TAB_FIELD_USENAME.shouldHave(value(user.email));

        rootLogger.info("Check that User name and surname retrieved from Gmail");
        PERSONAL_DETAILS_TAB_TITLE.shouldBe(visible).click();
        refresh();
        sleep(2000);
        PERSONAL_DETAILS_INPUT_NAME.shouldHave(Condition.value("TestEmail06"));
        PERSONAL_DETAILS_INPUT_SURNAME.shouldHave(Condition.value("GmailAccount06"));

    }
    @Test
    public void tabTimeTracker_A() {
        openSettingsTabTimeTracker();
        rootLogger.info("Check Defaults");
        TIME_TRACKER_TAB_SAVE_BTN.shouldBe(disabled);

        rootLogger.info("De-Select: Prompt me to bill my time whenever I leave a project ");
        TIME_TRACKER_TAB_ENABLE.setSelected(false).shouldNotBe(selected);
        submitEnabledButton(TIME_TRACKER_TAB_SAVE_BTN);
        TIME_TRACKER_TAB_SAVE_BTN.shouldBe(disabled);
        TIME_TRACKER_TAB_ENABLE.shouldNotBe(selected);

        rootLogger.info("Select: Prompt me to bill my time whenever I leave a project ");
        TIME_TRACKER_TAB_ENABLE.setSelected(true).shouldBe(selected);
        submitEnabledButton(TIME_TRACKER_TAB_SAVE_BTN);
        TIME_TRACKER_TAB_SAVE_BTN.shouldBe(disabled);
        TIME_TRACKER_TAB_ENABLE.shouldBe(selected);

        rootLogger.info("Set Hourly rate");
        fillField(TIME_TRACKER_TAB_RATE, "1000");
        submitEnabledButton(TIME_TRACKER_TAB_SAVE_BTN);
        TIME_TRACKER_TAB_SAVE_BTN.shouldBe(disabled);
        TIME_TRACKER_TAB_RATE.shouldHave(value("1000"));

        rootLogger.info("Clear Hourly rate");
        fillField(TIME_TRACKER_TAB_RATE, "");
        submitEnabledButton(TIME_TRACKER_TAB_SAVE_BTN);
        TIME_TRACKER_TAB_SAVE_BTN.shouldBe(disabled);
        TIME_TRACKER_TAB_RATE.shouldHave(value(""));

        String currency;
        rootLogger.info("Select currency");
        selectItemInDropdown(
                TIME_TRACKER_TAB_SELECT_CURRENCY,
                TIME_TRACKER_TAB_INPUT_CURRENCY,
                USD);
        submitEnabledButton(TIME_TRACKER_TAB_SAVE_BTN);
        TIME_TRACKER_TAB_SAVE_BTN.shouldBe(disabled);
        currency = TIME_TRACKER_TAB_SELECT_CURRENCY.getText();
        Assert.assertEquals(USD, currency);

        rootLogger.info("Select currency");
        selectItemInDropdown(
                TIME_TRACKER_TAB_SELECT_CURRENCY,
                TIME_TRACKER_TAB_INPUT_CURRENCY,
                GBP);
        submitEnabledButton(TIME_TRACKER_TAB_SAVE_BTN);
        TIME_TRACKER_TAB_SAVE_BTN.shouldBe(disabled);
        currency = TIME_TRACKER_TAB_SELECT_CURRENCY.getText();
        Assert.assertEquals(GBP, currency);
    }
}