package com.pekama.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.DirectLinks.urlSingUp;
import static Page.PekamaSignUp.*;
import static Page.TestsCredentials.*;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


/**
 * Created by VatslauX on 27-Dec-16.
 */
public class PekamaSignUp {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String passwordFieldValue = "";
    public String EXIST_USER = USER_01_EMAIL;


    @Before
    public void selectAgreeCheckbox() {
        open(urlSingUp);
        $(signupAgree).shouldBe(visible).shouldNot(selected);
        $(signupNext).shouldBe(visible).shouldBe(disabled);
        $(signupAgree).setSelected(true).shouldBe(selected);
        rootLogger.info("");
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
        $(signupError).shouldHave(text(signupMsg_RequiredField));
        $(signupAgree).shouldBe(visible).shouldNot(selected);
        rootLogger.info("");
    //Tests if 1 fields is blank
    }
    @Test
    public void onlyEmailSubmitted() {
        $(signupEmail).sendKeys(VALID_EMAIL);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
        rootLogger.info("");
    }
    @Test
    public void onlyNameSubmitted() {
        $(signupName).sendKeys(VALID_NAME);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
        rootLogger.info("");
    }
    @Test
    public void onlySurnameSubmitted() {
        $(signupSurname).sendKeys(VALID_SURNAME);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
        rootLogger.info("");
    }
    @Test
    public void onlyCompanySubmitted() {
        $(signupCompany).sendKeys(VALID_COMPANY);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
        rootLogger.info("");
    }
    @Test
    public void onlyPasswordSubmitted() {
        $(signupPassword).sendKeys(VALID_PASSWORD);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
        rootLogger.info("");
    }

    @Test  //Email Validation
    public void validationEmail() {

        for (int arrayLength = 0; arrayLength < arrayInvalidEmails.length; arrayLength++) {
                $(signupEmail).sendKeys(arrayInvalidEmails[arrayLength]);
                $(signupNext).shouldBe(visible).shouldNot(disabled).click();
                $$(By.xpath(signupErrorEmail)).shouldHave(size(1));
                $(By.xpath(signupErrorEmail)).shouldHave(text(signupMsg_InvalidEmail));

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
        rootLogger.info("");

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
            $(By.xpath(signupErrorEmail)).shouldBe(visible).shouldHave(text(signupMsg_UsedEmail));
            $(signupNext).shouldBe(disabled);
            rootLogger.info("");
    }

//    @Ignore("not ready")
//    @Test //Exist user - check by email+some
//    public void userExistAlias() {
//
//    for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {
//        $(signupEmail).shouldBe(visible).sendKeys(VALID_EMAIL);
//        $(signupName).shouldBe(visible).sendKeys(VALID_NAME);
//        $(signupSurname).shouldBe(visible).sendKeys(VALID_SURNAME);
//        $(signupCompany).shouldBe(visible).sendKeys(VALID_COMPANY);
//        $(signupPassword).shouldBe(visible).sendKeys(arrayInvalidPasswords[arrayLength]);
//        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
//        sleep(1500);
//        $(signupError).shouldBe(visible);
//        $(signupNext).shouldBe(disabled);
//
//        refresh();
//        $(signupAgree).shouldBe(visible).shouldNot(selected);
//        $(signupNext).shouldBe(visible).shouldBe(disabled);
//        $(signupAgree).setSelected(true).shouldBe(selected);
//    }
//
//}
}

