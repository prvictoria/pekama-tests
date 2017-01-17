package com.pekama.app;
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
import static Steps.PekamaSteps.submitEnabledButton;
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

    @Test
    public void checkGui() {
        rootLogger.info("Start test GUI and links");
        $(byXpath(PERSONAL_SETTINGS_BTN)).shouldBe(Condition.visible);
        $(byXpath(TEAM_SETTINGS_BTN)).shouldBe(Condition.visible);
        $(byXpath(PERSONAL_DETAILS_TAB_TITLE)).shouldHave(Condition.text("Personal details"));
        $(byXpath(SECURITY_TAB_TITLE)).shouldHave(Condition.text("Login & Security"));
        $(byXpath(EMAILS_TAB_TITLE)).shouldHave(Condition.text("Emails"));
        $(byXpath(SIGNATURE_TAB_TITLE)).shouldHave(Condition.text("E-mail signature"));
        $(byXpath(IMAP_TAB_TITLE)).shouldHave(Condition.text("IMAP"));
        $(byXpath(TIME_TRACKER_TAB_TITLE)).shouldHave(Condition.text("Time Tracker"));
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        sleep(2000);
        $(byXpath(PERSONAL_DETAILS_SAVE_BTN)).waitUntil(Condition.disabled, 10000);
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
        $(byXpath(PERSONAL_DETAILS_SAVE_BTN)).shouldBe(Condition.disabled);
        rootLogger.info("User default data present");
    }
    @Test
    public void personalDetails_Name_A() {
        rootLogger.info("Validation Name field");
        $(byText("First name:")).shouldBe(Condition.visible);
        String RANDOM_20_LETTER = Utils.getRandomString(20);
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).sendKeys(RANDOM_20_LETTER);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).clear();
        sleep(1000);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        sleep(500);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_100)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_100);
    }
    @Test
    public void personalDetails_NameSurname_A() {
        rootLogger.info("Validation Name field");
        $(byText("Last name:")).shouldBe(Condition.visible);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).clear();
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        sleep(500);
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).shouldHave(Condition.value(""));
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).shouldHave(Condition.value(""));
        $(byText(ERROR_MSG_BLANK_NAME)).shouldBe(Condition.visible);
        $(byText(ERROR_MSG_BLANK_SURNAME)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_BLANK_SURNAME);
    }
    @Test
    public void personalDetails_Surname_A() {
        rootLogger.info("Validation Name field");
        $(byText("Last name:")).shouldBe(Condition.visible);
        String RANDOM_20_LETTER = Utils.getRandomString(20);
        $(byName(PERSONAL_DETAILS_INPUT_NAME)).sendKeys(RANDOM_20_LETTER);
        $(byName(PERSONAL_DETAILS_INPUT_SURNAME)).clear();
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_PostalCode_A() {
        rootLogger.info("Validation maxlength Post code field");
        $(byText("Post code:")).shouldBe(Condition.visible);
        String RANDOM_21_LETTER = Utils.getRandomString(21);
        $(byName(PERSONAL_DETAILS_INPUT_ZIP)).clear();
        $(byName(PERSONAL_DETAILS_INPUT_ZIP)).sendKeys(RANDOM_21_LETTER);
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
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
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        $(byText(ERROR_MSG_VALIDATION_LENGTH_255)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+ERROR_MSG_VALIDATION_LENGTH_255);
    }
    @Test
    public void personalDetails_SelectCountry_A() {
        rootLogger.info("Select new counrty");
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).click();
        $(byXpath(PERSONAL_DETAILS_COUNTRY_INPUT)).sendKeys("United Kingdom");
        $(CSS_SelectHighlighted).click();
        //$(byText("United Kingdom")).shouldBe(Condition.exactText("United Kingdom")).click();
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        refresh(); sleep(2000);
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).shouldHave(Condition.text("United Kingdom"));

        rootLogger.info("Select user counrty");
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).click();
        $(byXpath(PERSONAL_DETAILS_COUNTRY_INPUT)).sendKeys(User3.COUNTRY.getValue());
        $(CSS_SelectHighlighted).click();
        //$(byText("United States")).shouldBe(Condition.exactText("United States")).click();
        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        refresh(); sleep(2000);
        $(byXpath(PERSONAL_DETAILS_COUNTRY_SELECT)).shouldHave(Condition.text("United States"));
        rootLogger.info("User country - "+User3.COUNTRY.getValue()+" - was selected");
    }

    @Test
    public void tabSecurity_PasswordValidations_A() {
        rootLogger.info("Check state by default");
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_CURRENT_PASSWORD)).waitUntil(Condition.visible, 10000).shouldHave(Condition.value(""));
        $(byName(SECURITY_TAB_NEW_PASSWORD)).shouldHave(Condition.value(""));
        $(byName(SECURITY_TAB_CONFIRM_PASSWORD)).shouldHave(Condition.value(""));
        $(byXpath(SECURITY_SAVE_BTN)).shouldBe(Condition.visible).shouldBe(Condition.disabled);
        $(byXpath(SECURITY_ENABLE_BTN)).shouldBe(Condition.visible).shouldBe(Condition.enabled);
        $(byText("2-step verification")).shouldBe(Condition.visible);
        $(byText("Disabled")).shouldBe(Condition.visible);
        rootLogger.info("State by default - PASSED");

        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields New password & Confirm Password");
        $(byName(SECURITY_TAB_CURRENT_PASSWORD)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $(byXpath(SECURITY_SAVE_BTN)).shouldBe(Condition.disabled);
        $$(byText(ERROR_MSG_WRONG_PASSWORD)).shouldHaveSize(1);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_B() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields Current password & Confirm Password");
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_NEW_PASSWORD)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_C() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Validation empty fields Current password & New Password");
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_CONFIRM_PASSWORD)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_D() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - BUG noValidation - No Current password checks MAJOR(Not reproduced)");
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_NEW_PASSWORD)).waitUntil(Condition.visible, 10000).sendKeys(validPassword);
        $(byName(SECURITY_TAB_CONFIRM_PASSWORD)).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_E() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no New password");
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_CURRENT_PASSWORD)).waitUntil(Condition.visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        $(byName(SECURITY_TAB_CONFIRM_PASSWORD)).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_F() {
        String validPassword = Utils.getRandomString(8)+VALID_PASSWORD;
        rootLogger.info("Change Password - no Confirm password");
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_CURRENT_PASSWORD)).waitUntil(Condition.visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        $(byName(SECURITY_TAB_NEW_PASSWORD)).sendKeys(validPassword);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_G() {
        rootLogger.info("Change Password - not Old can be new password");
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_CURRENT_PASSWORD)).waitUntil(Condition.visible, 10000).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        $(byName(SECURITY_TAB_NEW_PASSWORD)).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        $(byName(SECURITY_TAB_CONFIRM_PASSWORD)).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_NEW_PASSOWRD_EQUALS_TO_OLD)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_I() {
        rootLogger.info("All fields - empty string submitted");
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_CURRENT_PASSWORD)).sendKeys("1");
        $(byName(SECURITY_TAB_CURRENT_PASSWORD)).clear();
        $(byName(SECURITY_TAB_NEW_PASSWORD)).sendKeys("1");
        $(byName(SECURITY_TAB_NEW_PASSWORD)).clear();
        $(byName(SECURITY_TAB_CONFIRM_PASSWORD)).sendKeys("1");
        $(byName(SECURITY_TAB_CONFIRM_PASSWORD)).clear();
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_BLANK_FIELD)).shouldHaveSize(3);
        rootLogger.info("Validation error present");
    }
    @Test
    public void tabSecurity_PasswordValidations_K() {
        rootLogger.info("Max length validation");
        String RANDOM_129_LETTER = Utils.getRandomString(129);
        $(byXpath(SECURITY_TAB_TITLE)).click();
        $(byName(SECURITY_TAB_CURRENT_PASSWORD)).sendKeys(User3.PEKAMA_PASSWORD.getValue());
        $(byName(SECURITY_TAB_NEW_PASSWORD)).sendKeys(RANDOM_129_LETTER);
        $(byName(SECURITY_TAB_CONFIRM_PASSWORD)).sendKeys(RANDOM_129_LETTER);
        submitEnabledButton(SECURITY_SAVE_BTN);
        $$(byText(ERROR_MSG_VALIDATION_LENGTH_128)).shouldHaveSize(1);
        rootLogger.info("Validation error present");
    }

    @Test
    public void tabSecurity_TwoStepVerification_A() {
        $(byXpath(SECURITY_TAB_TITLE)).click();
        rootLogger.info("Open MW Enable 2-step verification");
        $(byXpath(SECURITY_ENABLE_BTN)).shouldBe(Condition.visible).click();
        rootLogger.info("Check MW buttons");
        $(byXpath(MW)).shouldBe(Condition.visible);
        $(byXpath(MW_EnableVerificationClose)).shouldBe(Condition.visible);
        $(byXpath(MW_EnableVerificationNext)).shouldBe(Condition.visible);
        $(byXpath(MW_EnableVerificationCountrySelect)).shouldBe(Condition.visible).click();
        $(byXpath(MW_EnableVerificationCoutryField)).shouldBe(Condition.visible).shouldHave(Condition.value("")).sendKeys("Belarus");
        $(CSS_SelectHighlighted).shouldBe(Condition.visible).click();
        $(byText("+375")).shouldBe(Condition.visible);

        rootLogger.info("Modal - Enable 2-Step Verification/ validate incorrect number");
        $(byName(MW_EnableVerificationTelField)).shouldHave(Condition.value("")).sendKeys("123");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("21211: The 'To' number 375123 is not a valid phone number.")).shouldBe(Condition.visible);
        rootLogger.info("Modal - Enable 2-Step Verification/ validate CORRECT number");
        $(byName(MW_EnableVerificationTelField)).clear();
        $(byName(MW_EnableVerificationTelField)).sendKeys("291200656");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("We sent a confirmation code to your phone. Please enter it in the field below.")).shouldBe(Condition.visible);

        $(byName(MW_EnableVerificationConfirmCodeField)).clear();
        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("")).sendKeys("12345678901234567890");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("Wrong confirmation code format. It must be a number between 100000 and 999999")).shouldBe(Condition.visible);
        rootLogger.info("validation present");

        $(byName(MW_EnableVerificationConfirmCodeField)).clear();
        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("")).sendKeys("100000");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("Wrong confirmation code. Attempts left: 2.")).shouldBe(Condition.visible);
        $(byName(MW_EnableVerificationConfirmCodeField)).clear();
        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("")).sendKeys("999999");
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("Wrong confirmation code. Attempts left: 1.")).shouldBe(Condition.visible);
        $(byName(MW_EnableVerificationConfirmCodeField)).clear();
        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("")).sendKeys("123123");
        $(byName(MW_EnableVerificationConfirmCodeField)).shouldHave(Condition.value("123123"));
        submitEnabledButton(MW_EnableVerificationNext);
        $(byText("You're out of attempts, please request a new confirmation code.")).shouldBe(Condition.visible);
        $(byXpath(MW_EnableVerificationClose)).shouldBe(Condition.visible).click();
        $(byXpath(MW)).shouldNotBe(Condition.visible);
        rootLogger.info("validation 3 attempts present");

    }
    @Ignore // todo - need phone SMS-to-email integration
    @Test
    public void tabSecurity_TwoStepVerification_B() {
        rootLogger.info("Positive flow");
        rootLogger.info("");
      rootLogger.info("");

    }
    @Test
    public void tabEmails_A() {
        $(byXpath(EMAILS_TAB_TITLE)).click();
        rootLogger.info("Check Defaults");
        $(byXpath(EMAILS_TAB_RADIO_ALWAYS)).shouldBe(Condition.visible).shouldBe(Condition.selected);
        $(byXpath(EMAILS_TAB_RADIO_OFFLINE)).shouldBe(Condition.visible).shouldNotBe(Condition.selected);
        $(byXpath(EMAILS_TAB_RECIEVE_ATTACHMENTS)).shouldBe(Condition.visible).shouldBe(Condition.checked);
        $(byXpath(EMAILS_TAB_RADIO_NO_EMAILS)).shouldBe(Condition.visible).shouldNotBe(Condition.selected);
        $(byXpath(EMAILS_TAB_GET_COPY_OWN_EMAILS)).shouldBe(Condition.visible).shouldNotBe(Condition.checked);
        rootLogger.info("Check selections");
//todo - not hot
//        $(byXpath(EMAILS_TAB_RADIO_OFFLINE)).selectRadio("on");
//        $(byXpath(EMAILS_TAB_RADIO_OFFLINE)).shouldBe(Condition.visible).shouldBe(Condition.selected);


    }
    @Test
    public void tabSignature_A() {
        $(byXpath(SIGNATURE_TAB_TITLE)).click();
        rootLogger.info("Enter new text");
        $(byXpath(SIGNATURE_TAB_TEXT_EDITOR)).click();
        $(byXpath(SIGNATURE_TAB_TEXT_EDITOR)).clear();
        $(byXpath(SIGNATURE_TAB_TEXT_EDITOR)).sendKeys(LOREM_IPSUM_LONG);
        $(byXpath(SIGNATURE_TAB_TEXT_EDITOR)).shouldHave(Condition.text(LOREM_IPSUM_LONG));
        submitEnabledButton(SIGNATURE_SAVE_BTN);
        rootLogger.info("Check entered text");
        refresh();
        $(byXpath(SIGNATURE_TAB_TITLE)).click();
        $(byXpath(SIGNATURE_TAB_TEXT_EDITOR)).shouldHave(Condition.text(LOREM_IPSUM_LONG));
    }
    @Test
    public void tabIMAP_A() {
        $(byXpath(IMAP_TAB_TITLE)).click();
        rootLogger.info("Check Defaults");


        rootLogger.info("Check selections");



    }
    @Test
    public void tabTimeTracker_A() {
        $(byXpath(TIME_TRACKER_TAB_TITLE)).click();
        rootLogger.info("Check Defaults");


        rootLogger.info("Check selections");



    }

}