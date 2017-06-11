package Page.NewCommunity;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PageSignIn {
    public static final String JOIN_URL = "https://communitystaging.pekama.com/login";

    //LOGIN FORM
    public static final String JOIN_LOGIN_FORM = "//form[@name='logInForm']";
    public static final SelenideElement JOIN_LOGIN_EMAIL = $(byXpath("//form[@name='logInForm']//input[@name='email']"));
    public static final SelenideElement JOIN_LOGIN_PASSWORD = $(byXpath("//form[@name='logInForm']//input[@name='password']"));
    public static final SelenideElement JOIN_LOGIN_REMEMBER_ME = $(byXpath("//form[@name='logInForm']//input[@type='checkbox']/following-sibling::i"));
    public static final SelenideElement JOIN_LOGIN_REMEMBER_ME_VALUE = $(byXpath("//form[@name='logInForm']//input[@type='checkbox']"));
    public static final SelenideElement JOIN_LOGIN_RESET_PASSWORD = $(byXpath("//form[@name='logInForm']//a[contains(.,'Reset my password')]"));
    public static final SelenideElement JOIN_LOGIN_SUBMIT = $(byXpath("//form[@name='logInForm']//button"));

    //RESET PASSWORD
    public static final String JOIN_RESET_FORM = "//form[@name='resetPasswordForm']";
    public static final SelenideElement JOIN_RESET_EMAIL = $(byXpath(JOIN_RESET_FORM+"//input[@name='email']"));
    public static final SelenideElement JOIN_RESET_BACK = $(byXpath(JOIN_RESET_FORM+"//button[contains(.,'Back')]"));
    public static final SelenideElement JOIN_RESET_SUBMIT = $(byXpath(JOIN_RESET_FORM+"//button[contains(.,'Submit')]"));

    //JOIN SIGN UP
    public static final String JOIN_SIGN_UP_FORM = "//form[@name='signUpForm']";
    public static final SelenideElement JOIN_NAME = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='first_name']"));
    public static final SelenideElement JOIN_SURNAME = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='last_name']"));
    public static final SelenideElement JOIN_COMPANY = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='company']"));
    public static final SelenideElement JOIN_EMAIL = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='email']"));
    public static final SelenideElement JOIN_PASSWORD = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='password']"));
    public static final SelenideElement JOIN_SELECT_BUSINESS_TYPE = $(byXpath(JOIN_SIGN_UP_FORM+"//label[text()='Buisness type']/following-sibling::div//span"));
    public static final SelenideElement JOIN_INPUT_BUSINESS_TYPE = $(byXpath(JOIN_SIGN_UP_FORM+"//label[text()='Buisness type']/following-sibling::div//input[@type='search']"));
    public static final SelenideElement JOIN_SELECT_ROLE = $(byXpath(JOIN_SIGN_UP_FORM+"//label[text()='Your role']/following-sibling::div//span"));
    public static final SelenideElement JOIN_INPUT_ROLE = $(byXpath(JOIN_SIGN_UP_FORM+"//label[text()='Your role']/following-sibling::div//input[@type='search']"));
    public static final SelenideElement JOIN_SIGN_UP_SUBMIT = $(byXpath(JOIN_SIGN_UP_FORM+"//button"));

    public static final SelenideElement JOIN_AGREE_TERMS = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@type='checkbox']/following-sibling::i"));
    public static final SelenideElement JOIN_AGREE_TERMS_VALUE = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@type='checkbox']"));
    public static final SelenideElement JOIN_TERMS_LINK = $(byXpath(JOIN_SIGN_UP_FORM+"//*[@href='https://staging.pekama.com/about/terms/']"));
    public static final SelenideElement JOIN_ = $(byXpath(JOIN_SIGN_UP_FORM+""));



}
