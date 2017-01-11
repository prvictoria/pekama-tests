package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaSignUp {
    public static final String signup = "//form[@id='signup-form']";
    public static final String SIGN_UP_TITLE = "//*[@class='details-title']";
    public static final String SIGN_UP_TITLE_TEXT = "Your Details";

    public static final String signupNewButtonDisabled = "//form[@id='signup-form']//*[@class='btn btn-primary disabled']//*[contains(text(),'Next Step')]";
    public static final String signupNewButtonEnabled = "//*[@class='btn btn-primary']//*[contains(text(),'Next Step')]";
    public static final String signupPasswordEmptyAlert = "";
    public static final String signupCompanyEmptyAlert = "";
    public static final String signupFirstnameEmptyAlert = "";
    public static final String signupLastnameEmptyAlert = "";
    public static final String signupEmailEmptyAlert = "";
    public static final String signupgGenericEmptyAlert = "//form[@id='signup-form']//li[contains(.,'Please fill out this field.')]";
    public static final String signupResetPassw = "";

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
    public static final String signupErrorEmail = "//div[@class='signup-details']//div[@class='row'][1]//li";
    public static final String signupErrorPassword = "//div[@class='signup-details']//div[@class='row'][3]//li[2]";

    public static final String SIGNUP_ERROR_SHORT_PASSWORD = "//div[@class='signup-details']//li[contains(.,'This password is too common.')]";
    public static final String SIGNUP_ERROR_COMMON_PASSWORD = "//div[@class='signup-details']//li[contains(.,'This password is too short. It must contain at least 8 characters.')]";
    public static final String SIGNUP_ERROR_INVALID_PASSWORD = "//div[@class='signup-details']//li[starts-with(.,'Your password must contain at least one lowercase, one uppercase and one special character')]";

    public static final String[] arrayInvalidEmails = {"\"ab\"c@flxmd.by","ab\"c\"@flxmd.by","abc\"@flxmd.by", "\"ab\"c\"@flxmd.by", "事件王@flxmd.by", "ÀÇÈ@flxmd.by", "! # $ % * / ? | ^ { } ` ~ & ' + - =@flxmd.by", "abc@@eflxmd.by", "abcflxmd.by", "@abc@flxmd.by", "abc@", "@", "abc@flxmd..by", "abc@!#$%*/?|^{}`~&'+=com", "abc@fl\"xmd.by", "abc@ 事件|王.com", "abc@flx md.by"};
    //    not validated "bc@flxmd123.by",  "abc@flxmd-flxmd.by", "abc@ÀÇÈ.com",
    //public static final String[] arrayShortPasswords = {"1234567", "abcdefg", "1#qQ"};
    //public static final String[] arrayCommonPasswords = {"1234567", "abcdefg", "1234567890", "qwertyuiop", "QWERTYUIOP", "!\"#$%&()*+-,./:;<=>?[]{}~'", "1234567890qwertyuiop", "1234567890QWERTYUIOP", "1234567890#", "qwertyuiopQWERTYUIOP", "qwertyuiop#", "QWERTYUIOP#", "1#qQ"};
    public static final String[] arrayInvalidPasswords = {"1234567", "abcdefg", "1234567890", "qwertyuiop", "QWERTYUIOP", "!\"#$%&()*+-,./:;<=>?[]{}~'", "1234567890qwertyuiop", "1234567890QWERTYUIOP", "1234567890#", "qwertyuiopQWERTYUIOP", "qwertyuiop#", "QWERTYUIOP#", "1#qQ"}; //1#qQ 1#qQ 1#qQ 1#qQ - no empty space validation

    public static final String SIGN_UP_JOIN_PAGE_TITLE = "Teams on Your Domain";
    public static final String SIGN_UP_JOIN_PAGE_TEXT = "We found some existing teams on your domain.";
    public static final String SIGN_UP_JOIN_PAGE_TEAM_SECTION_TITLE = "Please mark a Team you belong to";
    public static final String SIGN_UP_JOIN_PAGE_DEFAULT_RADIO = "//label[contains(.,\"I don't belong to any of these Teams\")]/input";
    public static final String SIGN_UP_JOIN_PAGE_DEFAULT_RADIO_TEXT = "I don't belong to any of these Teams";
    public static final String SIGN_UP_JOIN_PAGE_FINISH_BTN = "//*[@id='finish-button']";
}
