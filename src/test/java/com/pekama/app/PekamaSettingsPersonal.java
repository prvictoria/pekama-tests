package com.pekama.app;/**
 * Created by VatslauX on 15-Jan-17.
 */

import Steps.PekamaSteps;
import Utils.Utils;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static Page.PekamaPersonalSettings.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaSettingsPersonal {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String PEKAMA_USER_EMAIL = User3.GMAIL_EMAIL.getValue();
    private String PEKAMA_USER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private String AUTH_URL = urlPersonalSettings;

    //    @Before
//    public void setUp() throws Exception {
//        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://staging.pekama.com/");
//        selenium.start();
//    }
    @BeforeClass
    public static void beforeClass() {

    }
    @Before
    public void before() {
        PekamaSteps loginIntoPekama = new PekamaSteps();
        loginIntoPekama.loginByURL(PEKAMA_USER_EMAIL, PEKAMA_USER_PASSWORD, AUTH_URL);
    }
    @After
    public void after() {
        open(urlLogout);
    }

    @Ignore
    @Test
    public void checkGui() {
        rootLogger.info("Start test GUI and links");
        $(byXpath(PERSONAL_SETTINGS_BTN)).shouldBe(Condition.visible);
        $(byXpath(TEAM_SETTINGS_BTN)).shouldBe(Condition.visible);
        $(byAttribute("data-target", personalSettingsTabPersonal)).shouldHave(Condition.text("Personal details"));
        $(byAttribute("data-target", personalSettingsTabSecurity)).shouldHave(Condition.text("Login & Security"));
        $(byAttribute("data-target", personalSettingsTabEmails)).shouldHave(Condition.text("Emails"));
        $(byAttribute("data-target", personalSettingsTabSignature)).shouldHave(Condition.text("E-mail signature"));
        $(byAttribute("data-target", personalSettingsTabIMAP)).shouldHave(Condition.text("IMAP"));
        $(byAttribute("data-target", personalSettingsTabTimeTracker)).shouldHave(Condition.text("Time Tracker"));
        rootLogger.info("Perosnal settings GUI is consistent");

    }
    @Test
    public void personalDetails_Y_SaveUserData() {
        rootLogger.info("Enater and Save User Data");
        $(byText("First name:")).waitUntil(Condition.visible, 10000);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).sendKeys(User3.NAME.getValue());
        $(byText("Last name:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).sendKeys(User3.SURNAME.getValue());
        $(byText("Phone #")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_PHONE)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_PHONE)).sendKeys(User3.PHONE.getValue());
        $(byText("Fax #")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_FAX)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_FAX)).sendKeys(User3.FAX.getValue());
        $(byText("Mobile #")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_MOBILE)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_MOBILE)).sendKeys(User3.MOBILE.getValue());
        $(byText("Legal entity:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_LEGAL_ENTITY)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_LEGAL_ENTITY)).sendKeys(User3.LEGAL_ENTITY.getValue());
        $(byText("Street address:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_STREET)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_STREET)).sendKeys(User3.STREET.getValue());
        $(byText("Post code:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_ZIP)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_ZIP)).sendKeys(User3.ZIP.getValue());
        $(byText("City:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_CITY)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_CITY)).sendKeys(User3.CITY.getValue());
        $(byText("State/Region")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_REGION)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_REGION)).sendKeys(User3.REGION.getValue());
        $(byText("Country:")).shouldBe(Condition.visible);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(2000);
        $(byXpath(personalSettingsSaveButton)).waitUntil(Condition.disabled, 10000);
        rootLogger.info("New data saved in all fields");
    }
    
    @Test
    public void personalDetails_Z_CheckSavedData() {
        rootLogger.info("Check Saved Data");
        sleep(2000);
        $(byText("First name:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).shouldHave(Condition.value(User3.NAME.getValue()));
        $(byText("Last name:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).shouldHave(Condition.value(User3.SURNAME.getValue()));
        $(byText("Phone #")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_PHONE)).shouldHave(Condition.value(User3.PHONE.getValue()));
        $(byText("Fax #")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_FAX)).shouldHave(Condition.value(User3.FAX.getValue()));
        $(byText("Mobile #")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_MOBILE)).shouldHave(Condition.value(User3.MOBILE.getValue()));
        $(byText("Legal entity:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_LEGAL_ENTITY)).shouldHave(Condition.value(User3.LEGAL_ENTITY.getValue()));
        $(byText("Street address:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_STREET)).shouldHave(Condition.value(User3.STREET.getValue()));
        $(byText("Post code:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_ZIP)).shouldHave(Condition.value(User3.ZIP.getValue()));
        $(byText("City:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_CITY)).shouldHave(Condition.value(User3.CITY.getValue()));
        $(byText("State/Region")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_REGION)).shouldHave(Condition.value(User3.REGION.getValue()));
        $(byText("Country:")).shouldBe(Condition.visible);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.disabled);
        rootLogger.info("User default data present");
    }
    @Test
    public void personalDetails_Name_A() {
        rootLogger.info("Validation Name field");
        $(byText("First name:")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.getRandomString(21);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).sendKeys(RANDOM_21_LETTER);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).clear();
        sleep(1000);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).shouldHave(Condition.value(""));
        $(byText(ERROR_MSG_BLANK_NAME)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_NAME);
    }
    @Test
    public void personalDetails_Name_B() {
        rootLogger.info("Validation maxlength Name field");
        $(byText("First name:")).shouldBe(Condition.visible);
        String RANDOM_101_LETTER = Utils.getRandomString(101);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).sendKeys(RANDOM_101_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_100)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_100);
    }
    @Test
    public void personalDetails_Surname_A() {
        rootLogger.info("Validation Name field");
        $(byText("Last name:")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.getRandomString(21);
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).sendKeys(RANDOM_21_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).shouldHave(Condition.value(""));
        $(byText(ERROR_MSG_BLANK_SURNAME)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_SURNAME);
    }
    @Test
    public void personalDetails_Surname_B() {
        rootLogger.info("Validation maxlength Surname field");
        $(byText("Last name:")).shouldBe(Condition.visible);
        String RANDOM_101_LETTER = Utils.getRandomString(101);
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).sendKeys(RANDOM_101_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_100)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_100);
    }
    @Test
    public void personalDetails_Phone_A() {
        rootLogger.info("Validation maxlength Phone field");
        $(byText("Phone #")).shouldBe(Condition.visible);
        String RANDOM_101_LETTER = Utils.getRandomString(21);
        $(byName(PERSONAL_DETAILS_INPUT_PHONE)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_PHONE)).sendKeys(RANDOM_101_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_20)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void personalDetails_Fax_A() {
        rootLogger.info("Validation maxlength Fax field");
        $(byText("Fax #")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.getRandomString(21);
        $(byName(PERSONAL_DETAILS_INPUT_FAX)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_FAX)).sendKeys(RANDOM_21_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_20)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void personalDetails_Mobile_A() {
        rootLogger.info("Validation maxlength Mobile field");
        $(byText("Mobile #")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.getRandomString(21);
        $(byName(PERSONAL_DETAILS_INPUT_MOBILE)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_MOBILE)).sendKeys(RANDOM_21_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_20)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void personalDetails_LegalEntity_A() {
        rootLogger.info("Validation maxlength Legal entity field");
        $(byText("Legal entity:")).shouldBe(Condition.visible);
        String RANDOM_256_LETTER = Utils.getRandomString(256);
        $(byName(PERSONAL_DETAILS_INPUT_LEGAL_ENTITY)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_LEGAL_ENTITY)).sendKeys(RANDOM_256_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_StreetAddress_A() {
        rootLogger.info("Validation maxlength Legal entity field");
        $(byText("Street address:")).shouldBe(Condition.visible);
        String RANDOM_256_LETTER = Utils.getRandomString(256);
        $(byName(PERSONAL_DETAILS_INPUT_STREET)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_STREET)).sendKeys(RANDOM_256_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_ZIP_A() {
        rootLogger.info("Validation maxlength Post code field");
        $(byText("Post code:")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.getRandomString(21);
        $(byName(PERSONAL_DETAILS_INPUT_ZIP)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_ZIP)).sendKeys(RANDOM_21_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_20)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_20);
    }
    @Test
    public void personalDetails_City_A() {
        rootLogger.info("Validation maxlength City field");
        $(byText("City:")).shouldBe(Condition.visible);
        String RANDOM_256_LETTER = Utils.getRandomString(256);
        $(byName(PERSONAL_DETAILS_INPUT_CITY)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_CITY)).sendKeys(RANDOM_256_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_Region_A() {
        rootLogger.info("Validation maxlength Region entity field");
        $(byText("State/Region")).shouldBe(Condition.visible);
        String RANDOM_256_LETTER = Utils.getRandomString(256);
        $(byName(PERSONAL_DETAILS_INPUT_REGION)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_REGION)).sendKeys(RANDOM_256_LETTER);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    //todo debug
    @Test
    public void personalDetails_SelectCountry_A() {
        rootLogger.info("Select new counrty");
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).click();
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).sendKeys("United Kingdom");
        $(byText("United Kingdom")).shouldBe(Condition.exactText("United Kingdom")).click();
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).shouldHave(Condition.text("United Kingdom"));
        rootLogger.info("Select user counrty");
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).click();
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).sendKeys("United States");
        $(byText("United States")).shouldBe(Condition.exactText("United States")).click();
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).shouldHave(Condition.text("United States"));
        rootLogger.info("User country"+User3.COUNTRY.getValue()+" selected");
    }

    @Test
    public void tabSecurity_PasswordValidations_A() {
        rootLogger.info("Check state by default");
        $(byName(personalSettingsCurrentPassword)).waitUntil(Condition.visible, 10000).shouldHave(Condition.value(""));
        $(byName(personalSettingsNewPassword)).shouldHave(Condition.value(""));
        $(byName(personalSettingsConfirmPassword)).shouldHave(Condition.value(""));
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.disabled);
        $(byXpath(personalSettingsEnableButton)).shouldBe(Condition.enabled);
        $(byText("2-step verification")).shouldBe(Condition.visible);
        $(byText("Disabled")).shouldBe(Condition.visible);
        rootLogger.info("State by default - PASSED");

        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields New password & Confirm Password");
        $(byName(personalSettingsCurrentPassword)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        $$(byText(ERROR_MSG_BLANK_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_C() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields Current password & Confirm Password");
        $(byName(personalSettingsNewPassword)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        $$(byText(ERROR_MSG_BLANK_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_D() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields Current password & New Password");
        $(byName(personalSettingsConfirmPassword)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        $$(byText(ERROR_MSG_BLANK_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_E() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no Current password - BUG noValidation Current password MAJOR");
        $(byName(personalSettingsNewPassword)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        $(byName(personalSettingsConfirmPassword)).sendKeys(validPassword);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_F() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no New password");
        $(byName(personalSettingsCurrentPassword)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        $(byName(personalSettingsConfirmPassword)).sendKeys(validPassword);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_G() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no Confirm password");
        $(byName(personalSettingsCurrentPassword)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        $(byName(personalSettingsNewPassword)).sendKeys(validPassword);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_H() {
        rootLogger.info("Change Password - not Old can be new password");
        $(byName(personalSettingsCurrentPassword)).waitUntil(Condition.visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        $(byName(personalSettingsNewPassword)).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        $(byName(personalSettingsConfirmPassword)).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText("")).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Ignore
    @Test
    public void tabSecurity_test_B_TwoStepVerification() {
        rootLogger.info("Open MW Enable 2-step verification");
        rootLogger.info("Check MW buttons");
        $(byXpath(MW)).shouldBe(Condition.visible);
        $(byXpath(MW_EnableVerificationClose)).shouldBe(Condition.visible);
        $(byXpath(MW_EnableVerificationNext)).shouldBe(Condition.visible);
        $(byXpath(MW_EnableVerificationCountrySelect)).shouldBe(Condition.visible).click();

        $(byXpath(MW_EnableVerificationCoutryField)).shouldBe(Condition.visible).shouldHave(Condition.value("")).sendKeys("bela");
        $(byLinkText("Belarus")).shouldBe(Condition.visible).click();
        $(byText("+375")).shouldBe(Condition.visible);

        rootLogger.info("Modal - Enable 2-Step Verification/ validate incorrect number");
        $(byName("phone")).shouldHave(Condition.value("")).sendKeys("123");
        $(byXpath(MW_EnableVerificationNext)).shouldBe(Condition.visible).click();
        $(byText("21211: The 'To' number 375123 is not a valid phone number.")).shouldBe(Condition.visible);
        rootLogger.info("Modal - Enable 2-Step Verification/ validate CORRECT number");
        $(byName(MW_EnableVerificationTelField)).shouldHave(Condition.value("")).sendKeys("291200656");
        $(byXpath(MW_EnableVerificationNext)).shouldBe(Condition.visible).click();
        $(byText("We sent a confirmation code to your phone. Please enter it in the field below.")).shouldBe(Condition.visible);

        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("")).sendKeys("12345678901234567890");
        $(byXpath(MW_EnableVerificationNext)).shouldBe(Condition.visible).click();
        $(byText("Wrong confirmation code format. It must be a number between 100000 and 999999")).shouldBe(Condition.visible);
        rootLogger.info("validation present");

        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("")).sendKeys("100000");
        $(byXpath(MW_EnableVerificationNext)).shouldBe(Condition.visible).click();
        $(byText("Wrong confirmation code. Attempts left: 2.")).shouldBe(Condition.visible);
        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("")).sendKeys("999999");
        $(byXpath(MW_EnableVerificationNext)).shouldBe(Condition.visible).click();
        $(byText("Wrong confirmation code. Attempts left: 1.")).shouldBe(Condition.visible);
        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("")).sendKeys("123123");
        $(byXpath(MW_EnableVerificationNext)).shouldBe(Condition.visible).click();
        $(byText("You're out of attempts, please request a new confirmation code.")).shouldBe(Condition.visible);
        rootLogger.info("validation 3 attempts present");


    }
    @Ignore
    @Test
    public void tabSecurity_test_C_TwoStepVerification() {
        rootLogger.info("Positive flow");
        rootLogger.info("");


        rootLogger.info("");

    }
}