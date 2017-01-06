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
    public static final String signupMsg_RequiredField = "This field is required.";
    public static final String signupMsg_InvalidEmail = "Enter a valid email address.";
    public static final String signupMsg_UsedEmail = "This email address is already taken";

    public static final String SIGNUP_ERROR_SHORT_PASSWORD = "//div[@class='signup-details']//li[contains(.,'This password is too common.')]";
    public static final String SIGNUP_ERROR_COMMON_PASSWORD = "//div[@class='signup-details']//li[contains(.,'This password is too short. It must contain at least 8 characters.')]";
    public static final String SIGNUP_ERROR_INVALID_PASSWORD = "//div[@class='signup-details']//li[starts-with(.,'Your password must contain at least one lowercase, one uppercase and one special character')]";

    public static final String[] arrayInvalidEmails = {"\"ab\"c@flxmd.by","ab\"c\"@flxmd.by","abc\"@flxmd.by", "\"ab\"c\"@flxmd.by", "事件王@flxmd.by", "ÀÇÈ@flxmd.by", "! # $ % * / ? | ^ { } ` ~ & ' + - =@flxmd.by", "abc@@eflxmd.by", "abcflxmd.by", "@abc@flxmd.by", "abc@", "@", "abc@flxmd..by", "abc@!#$%*/?|^{}`~&'+=com", "abc@fl\"xmd.by", "abc@ 事件|王.com", "abc@flx md.by"};
    //    not validated "bc@flxmd123.by",  "abc@flxmd-flxmd.by", "abc@ÀÇÈ.com",
    //public static final String[] arrayShortPasswords = {"1234567", "abcdefg", "1#qQ"};
    //public static final String[] arrayCommonPasswords = {"1234567", "abcdefg", "1234567890", "qwertyuiop", "QWERTYUIOP", "!\"#$%&()*+-,./:;<=>?[]{}~'", "1234567890qwertyuiop", "1234567890QWERTYUIOP", "1234567890#", "qwertyuiopQWERTYUIOP", "qwertyuiop#", "QWERTYUIOP#", "1#qQ"};
    public static final String[] arrayInvalidPasswords = {"1234567", "abcdefg", "1234567890", "qwertyuiop", "QWERTYUIOP", "!\"#$%&()*+-,./:;<=>?[]{}~'", "1234567890qwertyuiop", "1234567890QWERTYUIOP", "1234567890#", "qwertyuiopQWERTYUIOP", "qwertyuiop#", "QWERTYUIOP#", "1#qQ", "1#qQ 1#qQ 1#qQ 1#qQ"};

}
