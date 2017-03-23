package Tests;
import Steps.StepsPekama;
import Utils.Utils;
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
import static Page.UrlStrings.*;
import static Steps.StepsExternal.authGmail;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static Page.PekamaPersonalSettings.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaSettingsPersonal {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String TEST_USER_LOGIN = User3.GMAIL_EMAIL.getValue();
    private static final String TEST_USER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private static final String testUserGmailPassword = User3.GMAIL_PASSWORD.getValue();
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
        clearBrowserCache();
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                TEST_USER_LOGIN,
                TEST_USER_PASSWORD,
                URL_LogIn);
        openUrlWithBaseAuth(URL_PersonalSettings);
    }

    @Test
    public void checkGui() {
        rootLogger.info("Start test GUI and links");
        PERSONAL_SETTINGS_BTN.waitUntil(visible, 20000).shouldBe(Condition.visible);
        TEAM_SETTINGS_BTN.waitUntil(visible, 20000).shouldBe(Condition.visible);
        PERSONAL_DETAILS_TAB_TITLE.shouldHave(text("Personal details"));
        SECURITY_TAB_TITLE.shouldHave(text("Login & Security"));
        EMAILS_TAB_TITLE.shouldHave(text("Emails"));
        SIGNATURE_TAB_TITLE.shouldHave(text("E-mail signature"));
        IMAP_TAB_TITLE.shouldHave(text("IMAP"));
        TIME_TRACKER_TAB_TITLE.shouldHave(text("Time Tracker"));
        rootLogger.info("Personal settings GUI is consistent");
    }
    @Test
    public void personalDetails_Y_SaveUserData() {
        rootLogger.info("Enter and Save User Data");
        $(byText("First name:")).waitUntil(Condition.visible, 10000);
        PERSONAL_DETAILS_INPUT_NAME.clear();
        PERSONAL_DETAILS_INPUT_NAME.sendKeys(User3.NAME.getValue());
        $(byText("Last name:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_SURNAME.clear();
        PERSONAL_DETAILS_INPUT_SURNAME.sendKeys(User3.SURNAME.getValue());
        $(byText("Phone #")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_PHONE.clear();
        PERSONAL_DETAILS_INPUT_PHONE.sendKeys(User3.PHONE.getValue());
        $(byText("Fax #")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_FAX.clear();
        PERSONAL_DETAILS_INPUT_FAX.sendKeys(User3.FAX.getValue());
        $(byText("Mobile #")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_MOBILE.clear();
        PERSONAL_DETAILS_INPUT_MOBILE.sendKeys(User3.MOBILE.getValue());
        $(byText("Legal entity:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_LEGAL_ENTITY.clear();
        PERSONAL_DETAILS_INPUT_LEGAL_ENTITY.sendKeys(User3.LEGAL_ENTITY.getValue());
        $(byText("Street address:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_STREET.clear();
        PERSONAL_DETAILS_INPUT_STREET.sendKeys(User3.STREET.getValue());
        $(byText("Post code:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_ZIP.clear();
        PERSONAL_DETAILS_INPUT_ZIP.sendKeys(User3.ZIP.getValue());
        $(byText("City:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_CITY.clear();
        PERSONAL_DETAILS_INPUT_CITY.sendKeys(User3.CITY.getValue());
        $(byText("State/Region")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_REGION.clear();
        PERSONAL_DETAILS_INPUT_REGION.sendKeys(User3.REGION.getValue());
        $(byText("Country:")).shouldBe(Condition.visible);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        sleep(4000);
        PERSONAL_DETAILS_SAVE_BTN.waitUntil(Condition.disabled, 10000);
        rootLogger.info("New data saved in all fields");
    }
    @Test
    public void Z_personalDetails_Z_CheckSavedData() {
        rootLogger.info("Check Saved Data");
        sleep(2000);
        $(byText("First name:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_NAME.shouldHave(Condition.value(User3.NAME.getValue()));
        $(byText("Last name:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_SURNAME.shouldHave(Condition.value(User3.SURNAME.getValue()));
        $(byText("Phone #")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_PHONE.shouldHave(Condition.value(User3.PHONE.getValue()));
        $(byText("Fax #")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_FAX.shouldHave(Condition.value(User3.FAX.getValue()));
        $(byText("Mobile #")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_MOBILE.shouldHave(Condition.value(User3.MOBILE.getValue()));
        $(byText("Legal entity:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_LEGAL_ENTITY.shouldHave(Condition.value(User3.LEGAL_ENTITY.getValue()));
        $(byText("Street address:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_STREET.shouldHave(Condition.value(User3.STREET.getValue()));
        $(byText("Post code:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_ZIP.shouldHave(Condition.value(User3.ZIP.getValue()));
        $(byText("City:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_CITY.shouldHave(Condition.value(User3.CITY.getValue()));
        $(byText("State/Region")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_REGION.shouldHave(Condition.value(User3.REGION.getValue()));
        $(byText("Country:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_SAVE_BTN.shouldBe(Condition.disabled);
        rootLogger.info("User default data present");
    }
    @Test
    public void personalDetails_Name_A() {
        rootLogger.info("Validation Name field");
        $(byText("First name:")).shouldBe(Condition.visible);
        String RANDOM_20_LETTER = Utils.randomString(20);
        PERSONAL_DETAILS_INPUT_SURNAME.sendKeys(RANDOM_20_LETTER);
        PERSONAL_DETAILS_INPUT_NAME.clear();
        sleep(1000);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        sleep(500);
        PERSONAL_DETAILS_INPUT_NAME.shouldHave(Condition.value(""));
        $(byText(ERROR_MSG_BLANK_NAME)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_NAME);
    }
    @Test
    public void Z_personalDetails_Name_B() {
        rootLogger.info("Validation max length Name field");
        $(byText("First name:")).shouldBe(Condition.visible);
        String RANDOM_101_LETTER = Utils.randomString(101);
        PERSONAL_DETAILS_INPUT_NAME.clear();
        PERSONAL_DETAILS_INPUT_NAME.sendKeys(RANDOM_101_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_100)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_100);
    }
    @Test
    public void personalDetails_NameSurname_A() {
        rootLogger.info("Validation Name field");
        $(byText("Last name:")).shouldBe(Condition.visible);
        PERSONAL_DETAILS_INPUT_NAME.clear();
        PERSONAL_DETAILS_INPUT_SURNAME.clear();
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        sleep(500);
        PERSONAL_DETAILS_INPUT_SURNAME.shouldHave(Condition.value(""));
        PERSONAL_DETAILS_INPUT_NAME.shouldHave(Condition.value(""));
        $(byText(ERROR_MSG_BLANK_NAME)).shouldBe(Condition.visible);
        $(byText(ERROR_MSG_BLANK_SURNAME)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_SURNAME);
    }
    @Test
    public void personalDetails_Surname_A() {
        rootLogger.info("Validation Name field");
        $(byText("Last name:")).shouldBe(Condition.visible);
        String RANDOM_20_LETTER = Utils.randomString(20);
        PERSONAL_DETAILS_INPUT_NAME.sendKeys(RANDOM_20_LETTER);
        PERSONAL_DETAILS_INPUT_SURNAME.clear();
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        PERSONAL_DETAILS_INPUT_SURNAME.shouldHave(Condition.value(""));
        $(byText(ERROR_MSG_BLANK_SURNAME)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_SURNAME);
    }
    @Test
    public void personalDetails_Surname_B() {
        rootLogger.info("Validation max length Surname field");
        $(byText("Last name:")).shouldBe(Condition.visible);
        String RANDOM_101_LETTER = Utils.randomString(101);
        PERSONAL_DETAILS_INPUT_SURNAME.clear();
        PERSONAL_DETAILS_INPUT_SURNAME.sendKeys(RANDOM_101_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_100)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_100);
    }
    @Test
    public void personalDetails_Phone_A() {
        rootLogger.info("Validation max length Phone field");
        $(byText("Phone #")).shouldBe(Condition.visible);
        String RANDOM_101_LETTER = Utils.randomString(21);
        PERSONAL_DETAILS_INPUT_PHONE.clear();
        PERSONAL_DETAILS_INPUT_PHONE.sendKeys(RANDOM_101_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_20)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void personalDetails_Fax_A() {
        rootLogger.info("Validation max length Fax field");
        $(byText("Fax #")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.randomString(21);
        PERSONAL_DETAILS_INPUT_FAX.clear();
        PERSONAL_DETAILS_INPUT_FAX.sendKeys(RANDOM_21_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_20)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void personalDetails_Mobile_A() {
        rootLogger.info("Validation max length Mobile field");
        $(byText("Mobile #")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.randomString(21);
        PERSONAL_DETAILS_INPUT_MOBILE.clear();
        PERSONAL_DETAILS_INPUT_MOBILE.sendKeys(RANDOM_21_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_20)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void personalDetails_LegalEntity_A() {
        rootLogger.info("Validation max length Legal entity field");
        $(byText("Legal entity:")).shouldBe(Condition.visible);
        String RANDOM_256_LETTER = Utils.randomString(256);
        PERSONAL_DETAILS_INPUT_LEGAL_ENTITY.clear();
        PERSONAL_DETAILS_INPUT_LEGAL_ENTITY.sendKeys(RANDOM_256_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_StreetAddress_A() {
        rootLogger.info("Validation max length Legal entity field");
        $(byText("Street address:")).shouldBe(Condition.visible);
        String RANDOM_256_LETTER = Utils.randomString(256);
        PERSONAL_DETAILS_INPUT_STREET.clear();
        PERSONAL_DETAILS_INPUT_STREET.sendKeys(RANDOM_256_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_PostalCode_A() {
        rootLogger.info("Validation max length Post code field");
        $(byText("Post code:")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.randomString(21);
        PERSONAL_DETAILS_INPUT_ZIP.clear();
        PERSONAL_DETAILS_INPUT_ZIP.sendKeys(RANDOM_21_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_20)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void personalDetails_City_A() {
        rootLogger.info("Validation max length City field");
        $(byText("City:")).shouldBe(Condition.visible);
        String RANDOM_256_LETTER = Utils.randomString(256);
        PERSONAL_DETAILS_INPUT_CITY.clear();
        PERSONAL_DETAILS_INPUT_CITY.sendKeys(RANDOM_256_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_Region_A() {
        rootLogger.info("Validation max length Region entity field");
        $(byText("State/Region")).shouldBe(Condition.visible);
        String RANDOM_256_LETTER = Utils.randomString(256);
        PERSONAL_DETAILS_INPUT_REGION.clear();
        PERSONAL_DETAILS_INPUT_REGION.sendKeys(RANDOM_256_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_SelectCountry_A() {
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
        rootLogger.info("User country - "+User3.COUNTRY.getValue()+" - was selected");
    }

    @Test
    public void tabSecurity_PasswordValidations_A() {
        rootLogger.info("Check state by default");
        SECURITY_TAB_TITLE.shouldBe(visible).click();
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(Condition.visible, 10000).shouldHave(Condition.value(""));
        SECURITY_TAB_NEW_PASSWORD.shouldHave(Condition.value(""));
        SECURITY_TAB_CONFIRM_PASSWORD.shouldHave(Condition.value(""));
        SECURITY_SAVE_BTN.shouldBe(Condition.visible).shouldBe(Condition.disabled);
        SECURITY_ENABLE_BTN.shouldBe(Condition.visible).shouldBe(Condition.enabled);
        $(byText("2-step verification")).shouldBe(Condition.visible);
        $(byText("Disabled")).shouldBe(Condition.visible);
        rootLogger.info("State by default - PASSED");

        String validPassword = Utils.randomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields New password & Confirm Password");
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        SECURITY_SAVE_BTN.shouldBe(Condition.disabled);
        $$(byText(ERROR_MSG_WRONG_PASSWORD)).shouldHaveSize(1);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_B() {
        String validPassword = Utils.randomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields Current password & Confirm Password");
        SECURITY_TAB_TITLE.shouldBe(visible).click();
        SECURITY_TAB_NEW_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_C() {
        String validPassword = Utils.randomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields Current password & New Password");
        SECURITY_TAB_TITLE.shouldBe(visible).click();
        SECURITY_TAB_CONFIRM_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_D() {
        String validPassword = Utils.randomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - BUG noValidation - No Current password checks MAJOR(Not reproduced)");
        SECURITY_TAB_TITLE.shouldBe(visible).click();
        SECURITY_TAB_NEW_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_E() {
        String validPassword = Utils.randomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no New password");
        SECURITY_TAB_TITLE.shouldBe(visible).click();
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_F() {
        String validPassword = Utils.randomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no Confirm password");
        SECURITY_TAB_TITLE.shouldBe(visible).click();
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        SECURITY_TAB_NEW_PASSWORD.sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_G() {
        rootLogger.info("Change Password - not Old can be new password");
        SECURITY_TAB_TITLE.shouldBe(visible).click();
        SECURITY_TAB_CURRENT_PASSWORD.waitUntil(Condition.visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        SECURITY_TAB_NEW_PASSWORD.sendKeys(User3.PEKAMA_PASSWORD.getValue());
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys(User3.PEKAMA_PASSWORD.getValue());
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_NEW_PASSOWRD_EQUALS_TO_OLD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_I() {
        rootLogger.info("All fields - empty string submitted");
        SECURITY_TAB_TITLE.shouldBe(visible).click();
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
        String RANDOM_129_LETTER = Utils.randomString(129);
        SECURITY_TAB_TITLE.shouldBe(visible).click();
        SECURITY_TAB_CURRENT_PASSWORD.sendKeys(User3.PEKAMA_PASSWORD.getValue());
        SECURITY_TAB_NEW_PASSWORD.sendKeys(RANDOM_129_LETTER);
        SECURITY_TAB_CONFIRM_PASSWORD.sendKeys(RANDOM_129_LETTER);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_VALIDATION_LENGTH_128)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }

    @Test
    public void tabSecurity_TwoStepVerification_A() {
        SECURITY_TAB_TITLE.shouldBe(visible).click();
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
        MW_EnableVerificationTelField.sendKeys("291200656");
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
        EMAILS_TAB_TITLE.shouldBe(visible).click();
        rootLogger.info("Check Defaults");
        EMAILS_TAB_RADIO_ALWAYS.shouldBe(Condition.visible).shouldBe(Condition.selected);
        EMAILS_TAB_RADIO_OFFLINE.shouldBe(Condition.visible).shouldNotBe(Condition.selected);
        EMAILS_TAB_RECEIVE_ATTACHMENTS.shouldBe(Condition.visible).shouldBe(Condition.checked);
        EMAILS_TAB_RADIO_NO_EMAILS.shouldBe(Condition.visible).shouldNotBe(Condition.selected);
        EMAILS_TAB_GET_COPY_OWN_EMAILS.shouldBe(Condition.visible).shouldNotBe(Condition.checked);
        rootLogger.info("Check selections");
//todo - not hot
//        EMAILS_TAB_RADIO_OFFLINE)).selectRadio("on");
//        EMAILS_TAB_RADIO_OFFLINE)).shouldBe(Condition.visible).shouldBe(Condition.selected);


    }
    @Test
    public void tabSignature_A_Create() {
        SIGNATURE_TAB_TITLE.shouldBe(visible).click();
        fillTextEditor(LOREM_IPSUM_LONG);
        submitEnabledButton(SIGNATURE_SAVE_BTN);
        rootLogger.info("Check entered text");
        refresh();
        SIGNATURE_TAB_TITLE.click();
        SIGNATURE_TAB_TEXT_EDITOR.shouldHave(text(LOREM_IPSUM_LONG));

    }

    @Test
    public void tabSignature_B_Delete() {
        SIGNATURE_TAB_TITLE.shouldBe(visible).click();
        submitEnabledButton(SIGNATURE_DELETE_BTN);
        SIGNATURE_TAB_TITLE.click();
        SIGNATURE_TAB_TEXT_EDITOR.shouldHave(text(""));

    }

    @Test
    public void tabIMAP_A_ManualConnect() {
        IMAP_TAB_TITLE.shouldBe(visible).click();
        sleep(2000);
        if (IMAP_TAB_BTN_DELETE.isDisplayed())
        {
            rootLogger.info("Delete detected account");
            IMAP_TAB_BTN_DELETE.click();
            submitConfirmAction();
            sleep(500);
        }
        rootLogger.info("Check Defaults");
        IMAP_TAB_FIELD_USENAME.shouldBe(Condition.visible);
        IMAP_TAB_FIELD_PASSWORD.shouldBe(Condition.visible);
        IMAP_TAB_FIELD_SERVER_NAME.shouldBe(Condition.visible);
        IMAP_TAB_FIELD_PORT.shouldBe(Condition.visible);
        IMAP_TAB_BTN_CONNECT_GMAIL.shouldBe(Condition.visible);
        IMAP_TAB_BTN_SAVE_AND_CHECK.shouldBe(Condition.visible);
        IMAP_TAB_FIELD_USENAME.shouldBe(Condition.visible);
        IMAP_TAB_BTN_CHECK.shouldBe(Condition.visible);
        IMAP_TAB_SSL.shouldBe(Condition.visible);

        rootLogger.info("Connect email manual");
        fillField(IMAP_TAB_FIELD_USENAME, TEST_USER_LOGIN);
        fillField(IMAP_TAB_FIELD_PASSWORD, testUserGmailPassword);
        fillField(IMAP_TAB_FIELD_SERVER_NAME, "imap.gmail.com");
        fillField(IMAP_TAB_FIELD_PORT, "993");
        IMAP_TAB_SSL.click();
        submitEnabledButton(IMAP_TAB_BTN_SAVE_AND_CHECK);
        IMAP_TAB_BTN_DELETE.waitUntil(visible, 30000);

        rootLogger.info("Delete connected account");
        submitEnabledButton(IMAP_TAB_BTN_DELETE);
        submitConfirmAction();
        sleep(500);
        IMAP_TAB_BTN_DELETE.shouldNotBe(visible);
    }

    @Test
    public void tabIMAP_B_GoggleAuthConnect() {
        IMAP_TAB_TITLE.shouldBe(visible).click();
        sleep(2000);
        rootLogger.info("Check Defaults");
            if (IMAP_TAB_BTN_DELETE.isDisplayed())
            {
                rootLogger.info("Delete detected account");
                IMAP_TAB_BTN_DELETE.click();
                submitConfirmAction();
                sleep(500);
            }
            if (IMAP_TAB_FIELD_USENAME.isDisplayed()){
            IMAP_TAB_FIELD_USENAME.shouldBe(Condition.visible);
            IMAP_TAB_FIELD_PASSWORD.shouldBe(Condition.visible);
            IMAP_TAB_FIELD_SERVER_NAME.shouldBe(Condition.visible);
            IMAP_TAB_FIELD_PORT.shouldBe(Condition.visible);
            IMAP_TAB_BTN_CONNECT_GMAIL.shouldBe(Condition.visible);
            IMAP_TAB_BTN_SAVE_AND_CHECK.shouldBe(Condition.visible);
            IMAP_TAB_FIELD_USENAME.shouldBe(Condition.visible);
            IMAP_TAB_BTN_CHECK.shouldBe(Condition.visible);
            IMAP_TAB_SSL.shouldBe(Condition.visible);

            rootLogger.info("Connect Gmail via Auth2");
            IMAP_TAB_BTN_CONNECT_GMAIL.click();
            sleep(2000);
            authGmail(TEST_USER_LOGIN);
            sleep(1000);
            switchTo().window("Pekama | Projects");

            rootLogger.info("Delete connected account");
            submitEnabledButton(IMAP_TAB_BTN_DELETE);
            submitConfirmAction();
            sleep(500);
            IMAP_TAB_BTN_DELETE.shouldNotBe(visible);
        }
        PERSONAL_DETAILS_TAB_TITLE.shouldBe(visible).click();
        refresh();
        rootLogger.info("Check Data retreved from Gmail");
        sleep(2000);
        PERSONAL_DETAILS_INPUT_NAME.shouldHave(Condition.value("TestEmail03"));
        PERSONAL_DETAILS_INPUT_SURNAME.shouldHave(Condition.value("GmailAccount03"));

    }
    @Test
    public void tabTimeTracker_A() {
        TIME_TRACKER_TAB_TITLE.shouldBe(visible).click();
        rootLogger.info("Check Defaults");
        TIME_TRACKER_TAB_SAVE_BTN.shouldBe(disabled);

        rootLogger.info("DeSelect: Prompt me to bill my time whenever I leave a project ");
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