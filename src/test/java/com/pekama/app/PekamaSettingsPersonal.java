package com.pekama.app;/**
 * Created by VatslauX on 15-Jan-17.
 */

import com.codeborne.selenide.*;
import com.thoughtworks.selenium.DefaultSelenium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.ModalWindows.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static Page.PekamaPersonalSettings.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaSettingsPersonal {
    static final Logger rootLogger = LogManager.getRootLogger();
//    @Before
//    public void setUp() throws Exception {
//        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://staging.pekama.com/");
//        selenium.start();
//    }
    @Before
    public void before() {
        open("");
    }

    @After
    public void after() {
        open("");
    }

    @Test
    public void checkGui() {
        rootLogger.info("Start test GUI and links");
        $(byAttribute("data-target", personalSettingsTabPersonal)).shouldHave(Condition.text("Personal details"));
        $(byAttribute("data-target", personalSettingsTabSecurity)).shouldHave(Condition.text("Login & Security"));
        $(byAttribute("data-target", personalSettingsTabEmails)).shouldHave(Condition.text("Emails"));
        $(byAttribute("data-target", personalSettingsTabSignature)).shouldHave(Condition.text("E-mail signature"));
        $(byAttribute("data-target", personalSettingsTabIMAP)).shouldHave(Condition.text("IMAP"));
        $(byAttribute("data-target", personalSettingsTabTimeTracker)).shouldHave(Condition.text("Time Tracker"));
        rootLogger.info("Perosnal settings GUI is consistent");
        $(byText("Team details"));
    }

    @Test
    public void checkFieldsState() {
        //$$(":input").shouldHave(size(4));
        $(byText("Title:")).shouldBe(Condition.visible);
        $(byText("Code:")).shouldBe(Condition.visible);
        $(byText("Organization type:")).shouldBe(Condition.visible);
        $(byText("This organization is:")).shouldBe(Condition.visible);
        $(byText("Email:")).shouldBe(Condition.visible);
        $(byText("@organizations.pekama.com")).shouldBe(Condition.visible);
        $(byText("Additional Info")).shouldBe(Condition.visible);
        $(byText("Street address:")).shouldBe(Condition.visible);
        $(byText("Post code:")).shouldBe(Condition.visible);
        $(byText("City:")).shouldBe(Condition.visible);
        $(byText("State/Region")).shouldBe(Condition.visible);
        $(byText("Country:")).shouldBe(Condition.visible);
        $(byText("Title")).shouldBe(Condition.visible);
        $(byText("Title")).shouldBe(Condition.visible);


        rootLogger.info("Fields are consistent");
    }

    @Test
    public void tabSecurity_testA_PasswordValidations() {
        rootLogger.info("Check state by default");
        $(byName(personalSettingsCurrentPassword)).shouldHave(Condition.value(""));
        $(byName(personalSettingsNewPassword)).shouldHave(Condition.value(""));
        $(byName(personalSettingsConfirmPassword)).shouldHave(Condition.value(""));
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.disabled);
        $(byXpath(personalSettingsEnableButton)).shouldBe(Condition.enabled);
        $(byText("2-step verification")).shouldBe(Condition.visible);
        $(byText("Disabled")).shouldBe(Condition.visible);
        rootLogger.info("State by default - PASSED");

        rootLogger.info("Change Password - validation empty New password & Confirm");
        $(byName(personalSettingsCurrentPassword)).sendKeys("");
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        $$(byText(ERROR_MSG_BLANK_FIELD)).shouldHaveSize(1);

        $(byName(personalSettingsNewPassword)).sendKeys("");
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        $$(byText(ERROR_MSG_BLANK_FIELD)).shouldHaveSize(1);

        $(byName(personalSettingsConfirmPassword)).sendKeys("");
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(2);
        $$(byText(ERROR_MSG_BLANK_FIELD)).shouldHaveSize(1);

        rootLogger.info("Change Password - no Current password - BUG noValidation Current password MAJOR");
        $(byName(personalSettingsNewPassword)).sendKeys(VALID_PASSWORD);
        $(byName(personalSettingsConfirmPassword)).sendKeys(VALID_PASSWORD);
        $(byXpath(personalSettingsSaveButton)).shouldBe(Condition.enabled).click();
        $$(byText(ERROR_MSG_REQUIRED_FIELD)).shouldHaveSize(1);

        rootLogger.info("Change Password - no New password");
        rootLogger.info("Change Password - no Confirm password");
        rootLogger.info("Change Password - not same new new password");
        rootLogger.info("");
        rootLogger.info("");
        rootLogger.info("");


    }


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
    @Test
    public void tabSecurity_test_C_TwoStepVerification() {
        rootLogger.info("Positive flow");
        rootLogger.info("");


        rootLogger.info("");

    }
}