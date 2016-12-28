package com.pekama.app;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.pekama.app.LoginPekama.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by VatslauX on 27-Dec-16.
 */
public class SignUpValidation {
    public static final String urlSignup = host+"/signup/";
    public static final String signupEmail = "#signup-email";
    public static final String signupName = "#signup-firstname";
    public static final String signupSurname = "#signup-lastname";
    public static final String signupCompany = "#inputCompany";
    public static final String signupPassword = "#inputPassword";
    public static final String signupUpload = "#avatar-upload-link";
    public static final String signupAgree = "#agree_to_tou";
    public static final String signupSubcribeNews = "user_info-receive_news";
    public static final String signupTerms = "//a[@href='/accounts/terms_of_use/']";
    public static final String signupNext = "#next-button";
    public static final String signupError = ".list-unstyled.error-list>li";
    public static final String signupMsg_RequiredField = "This field is required.";

//    public void openUrlSignup() {
//        open(urlSignup);
//    }
    @Before
    public void selectArggeCheckbox() {
        open(urlSignup);
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

//Password rules validation
}
