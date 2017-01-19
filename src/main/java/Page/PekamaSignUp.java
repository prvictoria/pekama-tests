package Page;

import com.codeborne.selenide.SelenideElement;

import java.lang.reflect.Array;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaSignUp {
    public static final SelenideElement signup = $(byXpath("//form[@id='signup-form']"));
    public static final SelenideElement SIGN_UP_TITLE = $(byXpath("//*[@class='details-title']"));
    public static final String SIGN_UP_TITLE_TEXT = "Your Details";

    public static final SelenideElement signupNewButtonDisabled = $(byXpath("//form[@id='signup-form']//*[@class='btn btn-primary disabled']//*[contains(text(),'Next Step')]"));
    public static final SelenideElement signupNewButtonEnabled = $(byXpath("//*[@class='btn btn-primary']//*[contains(text(),'Next Step')]"));
    public static final SelenideElement signupPasswordEmptyAlert = $(byXpath(""));
    public static final SelenideElement signupCompanyEmptyAlert = $(byXpath(""));
    public static final SelenideElement signupFirstnameEmptyAlert = $(byXpath(""));
    public static final SelenideElement signupLastnameEmptyAlert = $(byXpath(""));
    public static final SelenideElement signupEmailEmptyAlert = $(byXpath(""));
    public static final SelenideElement signupgGenericEmptyAlert = $(byXpath("//form[@id='signup-form']//li[contains(.,'Please fill out this field.')]"));
    public static final SelenideElement signupResetPassw = $(byXpath(""));

    public static final SelenideElement signupEmail = $(byId("signup-email"));
    public static final SelenideElement signupName = $(byId("signup-firstname"));
    public static final SelenideElement signupSurname = $(byId("signup-lastname"));
    public static final SelenideElement signupCompany = $(byId("inputCompany"));
    public static final SelenideElement signupPassword = $(byId("inputPassword"));
    public static final SelenideElement signupUpload = $(byId("avatar-upload-link"));
    public static final SelenideElement signupAgree = $(byId("agree_to_tou"));
    public static final SelenideElement signupSubcribeNews = $(byId("user_info-receive_news"));
    public static final SelenideElement signupTerms = $(byXpath("//a[@href='/accounts/terms_of_use/']"));
    public static final SelenideElement signupNext = $(byId("next-button"));
    public static final SelenideElement signupError = $(".list-unstyled.error-list>li");
    public static final SelenideElement signupErrorEmail = $(byXpath("//div[@class='signup-details']//div[@class='row'][1]//li"));
    public static final SelenideElement signupErrorPassword = $(byXpath("//div[@class='signup-details']//div[@class='row'][3]//li[2]"));

    public static final SelenideElement SIGNUP_ERROR_SHORT_PASSWORD = $(byXpath("//div[@class='signup-details']//li[contains(.,'This password is too common.')]"));
    public static final SelenideElement SIGNUP_ERROR_COMMON_PASSWORD = $(byXpath("//div[@class='signup-details']//li[contains(.,'This password is too short. It must contain at least 8 characters.')]"));
    public static final SelenideElement SIGNUP_ERROR_INVALID_PASSWORD = $(byXpath("//div[@class='signup-details']//li[starts-with(.,'Your password must contain at least one lowercase, one uppercase and one special character')]"));

    public static final String[] arrayInvalidEmails = {"\"ab\"c@flxmd.by","ab\"c\"@flxmd.by","abc\"@flxmd.by", "\"ab\"c\"@flxmd.by", "事件王@flxmd.by", "ÀÇÈ@flxmd.by", "! # $ % * / ? | ^ { } ` ~ & ' + - =@flxmd.by", "abc@@eflxmd.by", "abcflxmd.by", "@abc@flxmd.by", "abc@", "@", "abc@flxmd..by", "abc@!#$%*/?|^{}`~&'+=com", "abc@fl\"xmd.by", "abc@ 事件|王.com", "abc@flx md.by"};
    //    not validated "bc@flxmd123.by",  "abc@flxmd-flxmd.by", "abc@ÀÇÈ.com",
    //public static final SelenideElement[] arrayShortPasswords = $(byXpath({"1234567", "abcdefg", "1#qQ"};
    //public static final SelenideElement[] arrayCommonPasswords = $(byXpath({"1234567", "abcdefg", "1234567890", "qwertyuiop", "QWERTYUIOP", "!\"#$%&()*+-,./:;<=>?[]{}~'", "1234567890qwertyuiop", "1234567890QWERTYUIOP", "1234567890#", "qwertyuiopQWERTYUIOP", "qwertyuiop#", "QWERTYUIOP#", "1#qQ"};
    public static final String[] arrayInvalidPasswords = {"1234567", "abcdefg", "1234567890", "qwertyuiop", "QWERTYUIOP", "!\"#$%&()*+-,./:;<=>?[]{}~'", "1234567890qwertyuiop", "1234567890QWERTYUIOP", "1234567890#", "qwertyuiopQWERTYUIOP", "qwertyuiop#", "QWERTYUIOP#", "1#qQ"}; //1#qQ 1#qQ 1#qQ 1#qQ - no empty space validation

    public static final SelenideElement SIGN_UP_JOIN_PAGE_TITLE = $(byText("Teams on Your Domain"));
    public static final SelenideElement SIGN_UP_JOIN_PAGE_TEXT = $(byText("We found some existing teams on your domain."));
    public static final SelenideElement SIGN_UP_JOIN_PAGE_TEAM_SECTION_TITLE = $(byText("Please mark a Team you belong to"));
    public static final SelenideElement SIGN_UP_JOIN_PAGE_DEFAULT_RADIO = $(byText("//label[contains(.,\"I don't belong to any of these Teams\")]/input"));
    public static final SelenideElement SIGN_UP_JOIN_PAGE_DEFAULT_RADIO_TEXT = $(byText("I don't belong to any of these Teams"));
    public static final SelenideElement SIGN_UP_JOIN_PAGE_FINISH_BTN = $(byXpath("//*[@id='finish-button']"));
}
