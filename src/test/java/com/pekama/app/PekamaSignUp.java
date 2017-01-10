package com.pekama.app;

import Steps.ExternalSteps;
import Utils.HttpAuth;
import com.codeborne.selenide.Selectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.Emails.*;
import static Page.TestsUrl.*;
import static Page.PekamaSignUp.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrlConfiguration.COMMUNITY;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


/**
 * Created by VatslauX on 27-Dec-16.
 */
public class PekamaSignUp {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String passwordFieldValue = "";
    public String EXIST_USER = USER_01_EMAIL;
    private String NEW_USER = User5.GMAIL_EMAIL.getValue();
    String actualBackLink;
    String EMAIL_SUBJECT = EMAIL_CONFIRM_REGISTRATION_SUBJECT;
    String EMAIL_TITLE = EMAIL_CONFIRM_REGISTRATION_TITLE;
    String EMAIL_TEXT = EMAIL_CONFIRM_REGISTRATION_TEXT;
    String EMAIL_BTN = EMAIL_CONFIRM_REGISTRATION_BTN;
    String EMAIL_REDIRECT_LINK = EMAIL_CONFIRM_REGISTRATION_BACKLINK;




    @Before
    public void selectAgreeCheckbox() {
        rootLogger.info("Open URL - "+urlSingUp);
        HttpAuth openHost = new HttpAuth();
        String AUTH_URL = urlSingUp;
        openHost.httpAuthWhithCustomLink(AUTH_URL);
        $(signupNext).shouldBe(visible).shouldNotBe(disabled);
        $(signupAgree).shouldBe(selected);
        rootLogger.info("Opened - " +urlSingUp);
    }

    @Ignore
    @Test
    public void openUrlSignup() {
        open(urlSingUp);
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
        $(By.name(signupSubcribeNews)).shouldBe(visible).shouldBe(selected);
    //        $(signupAgree).shouldBe(visible).shouldNot(selected);
        $(By.xpath(signupTerms)).shouldBe(visible);
    //  $(signupNext).shouldBe(visible).shouldBe(disabled);
    //Test if all fields are blank
        $(signupAgree).setSelected(true).shouldBe(selected);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(5));
        $(signupError).shouldHave(text(ERROR_MSG_REQUIRED_FIELD));
        $(signupAgree).shouldBe(visible).shouldNot(selected);
        rootLogger.info("");
    //Tests if 1 fields is blank
    }
    @Test
    public void onlyEmailSubmitted() {
        $(signupEmail).sendKeys(VALID_EMAIL);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(ERROR_MSG_REQUIRED_FIELD));
        rootLogger.info("");
    }
    @Test
    public void onlyNameSubmitted() {
        $(signupName).sendKeys(VALID_NAME);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(ERROR_MSG_REQUIRED_FIELD));
        rootLogger.info("");
    }
    @Test
    public void onlySurnameSubmitted() {
        $(signupSurname).sendKeys(VALID_SURNAME);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(ERROR_MSG_REQUIRED_FIELD));
        rootLogger.info("");
    }
    @Test
    public void onlyCompanySubmitted() {
        $(signupCompany).sendKeys(VALID_COMPANY);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(ERROR_MSG_REQUIRED_FIELD));
        rootLogger.info("");
    }
    @Test
    public void onlyPasswordSubmitted() {
        $(signupPassword).sendKeys(VALID_PASSWORD);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(ERROR_MSG_REQUIRED_FIELD));
        rootLogger.info("");
    }

    @Test  //Email Validation
    public void validationEmail() {

        for (int arrayLength = 0; arrayLength < arrayInvalidEmails.length; arrayLength++) {
                $(signupEmail).sendKeys(arrayInvalidEmails[arrayLength]);
                $(signupNext).shouldBe(visible).shouldNot(disabled).click();
                $$(By.xpath(signupErrorEmail)).shouldHave(size(1));
                $(By.xpath(signupErrorEmail)).shouldHave(text(ERROR_MSG_INVALID_EMAIL));

                refresh();

                $(signupAgree).shouldBe(visible).shouldNot(selected);
                $(signupNext).shouldBe(visible).shouldBe(disabled);
                $(signupAgree).setSelected(true).shouldBe(selected);
            }
        rootLogger.info("");
    }

    @Test //Password rules validation
    public void validationPassword() {

        for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
            $(signupEmail).shouldBe(visible).sendKeys(VALID_EMAIL);
            $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
            $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
            $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
            $(signupPassword).shouldBe(visible).sendKeys(arrayInvalidPasswords[arrayLength]);
            $(signupNext).shouldBe(visible).shouldNot(disabled).click();
            sleep(1500);
            $(signupError).shouldBe(visible);
            $(signupNext).shouldBe(disabled);

            refresh();
            $(signupAgree).shouldBe(visible).shouldNot(selected);
            $(signupNext).shouldBe(visible).shouldBe(disabled);
            $(signupAgree).setSelected(true).shouldBe(selected);
        }
        rootLogger.info("P  ASSWORD VALIDATION RULES TESTS LOOP");

    }

    @Test //Exist user - check by email
    public void userExist() {
            $(signupEmail).shouldBe(visible).sendKeys(EXIST_USER);
            $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
            $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
            $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
            $(signupPassword).shouldBe(visible).sendKeys(VALID_PASSWORD);
            $(signupNext).shouldBe(visible).shouldNot(disabled).click();
            sleep(1500);
            $(By.xpath(signupErrorEmail)).shouldBe(visible).shouldHave(text(ERROR_MSG_EMAIL_IS_USED));
            $(signupNext).shouldBe(disabled);
            rootLogger.info(ERROR_MSG_EMAIL_IS_USED);
    }

    @Ignore("not implemented")
    @Test //Exist user - check by email+some
    public void userExistAlias() {

    for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
        $(signupEmail).shouldBe(visible).sendKeys(VALID_EMAIL);
        $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
        $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
        $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
        $(signupPassword).shouldBe(visible).sendKeys(arrayInvalidPasswords[arrayLength]);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        sleep(1500);
        $(signupError).shouldBe(visible);
        $(signupNext).shouldBe(disabled);

        refresh();
        $(signupAgree).shouldBe(visible).shouldNot(selected);
        $(signupNext).shouldBe(visible).shouldBe(disabled);
        $(signupAgree).setSelected(true).shouldBe(selected);
        }
    }
    @Test
    public void sendSignUpEmail() {
        String GMAIL_LOGIN = User5.GMAIL_EMAIL.getValue();
        String GMAIL_PASSWORD = User5.GMAIL_PASSWORD.getValue();
        String EMAIL_SUBJECT = EMAIL_CONFIRM_REGISTRATION_SUBJECT;
        String EMAIL_TITLE = EMAIL_CONFIRM_REGISTRATION_TITLE;
        String EMAIL_TEXT = EMAIL_CONFIRM_REGISTRATION_TEXT;
        String EMAIL_BTN = EMAIL_CONFIRM_REGISTRATION_BTN;
        String EMAIL_REDIRECT_LINK = EMAIL_CONFIRM_REGISTRATION_BACKLINK;
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
        ExternalSteps loginGmailInboxApp = new ExternalSteps();
        loginGmailInboxApp.signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        actualBackLink = loginGmailInboxApp.checkInboxEmail(EMAIL_SUBJECT, EMAIL_TITLE, EMAIL_TEXT, EMAIL_BTN, EMAIL_REDIRECT_LINK);
            if (actualBackLink == null) {
                rootLogger.info("User followed reset link");
                Assert.fail("Redirect Link not found");
            }
    }

}

