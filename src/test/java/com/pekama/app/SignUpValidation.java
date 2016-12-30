package com.pekama.app;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.DirectLinks.*;
import static Page.PekamaSignUp.*;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by VatslauX on 27-Dec-16.
 */
public class SignUpValidation {

    private String passwordFieldValue = "";
//    public void openUrlSignup() {
//        open(urlSignup);
//    }
    @Before
    public void selectAgreeCheckbox() {
        open(urlSingUp);
        $(signupAgree).shouldBe(visible).shouldNot(selected);
        $(signupNext).shouldBe(visible).shouldBe(disabled);
        $(signupAgree).setSelected(true).shouldBe(selected);
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
//        $(signupNext).shouldBe(visible).shouldBe(disabled);
//Test if all fields are blank
        $(signupAgree).setSelected(true).shouldBe(selected);
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(5));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
        $(signupAgree).shouldBe(visible).shouldNot(selected);
//Tests if 1 fields is blank
    }
    @Test
    public void onlyEmailSubmitted() {
        $(signupEmail).sendKeys("1234567890@email.com");
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
    }
    @Test
    public void onlyNameSubmitted() {
        $(signupName).sendKeys("1234567890@email.com");
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
    }
    @Test
    public void onlySurnameSubmitted() {
        $(signupSurname).sendKeys("1234567890@email.com");
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
    }
    @Test
    public void onlyCompanySubmitted() {
        $(signupCompany).sendKeys("1234567890@email.com");
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
    }
    @Test
    public void onlyPasswordSubmitted() {
        $(signupPassword).sendKeys("1234567890@Email.com");
        $(signupNext).shouldBe(visible).shouldNot(disabled).click();
        $$(signupError).shouldHave(size(4));
        $(signupError).shouldHave(text(signupMsg_RequiredField));
    }
//Email Validation
    @Test
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
    }
//Password rules validation
    @Test
    public void validationPassword() {

        for (int arrayLength = 0; arrayLength < arrayInvalidPasswords.length; arrayLength++) {

            $(signupPassword).sendKeys(arrayInvalidPasswords[arrayLength]);
            passwordFieldValue = $(signupPassword).getValue();
            System.out.println(passwordFieldValue);
            $(signupNext).shouldBe(visible).shouldNot(disabled).click();

            $(By.xpath(signupError)).shouldBe(visible);
            $(signupNext).shouldBe(visible).shouldBe(disabled);

            }
            
            refresh();
            $(signupAgree).shouldBe(visible).shouldNot(selected);
            $(signupNext).shouldBe(visible).shouldBe(disabled);
            $(signupAgree).setSelected(true).shouldBe(selected);
    }
}

