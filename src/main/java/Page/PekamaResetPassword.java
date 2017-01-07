package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaResetPassword {
    public static final String RESET_PAGE_TITLE = "//*[@class='small-title']";
    public static final String RESET_PAGE_TITLE_TEXT = "Reset Password";
    public static final String RESET_PAGE_EMAIL = "//input[@name='email']";
    public static final String RESET_PAGE_RESET_BTN = "//input[@value='Reset password']";
    public static final String RESET_PAGE_ERROR = "//ul[@class='errorlist']/li";
    public static final String RESET_PAGE_ERROR_MSG = "Enter a valid email address.";
    public static final String RESET_PAGE_SUCCESS = "//*[@class='form-group']";
    public static final String RESET_PAGE_SUCCESS_MSG = "You've been sent an email with password restoration instructions.";

    public static final String NEWPASSWORD_TITLE = "//*[@class='small-title']";
    public static final String NEWPASSWORD_TITLE_TEXT = "Password restoration";
    public static final String NEWPASSWORD_TEXT = "Please enter your new password twice, so we can verify you typed it in correctly.";

    public static final String NEWPASSWORD_PAGE_NEW_PASSWORD = "//*[@name='new_password1'] ";
    public static final String NEWPASSWORD_PAGE_CONFIRM_PASSWORD = "//*[@name='new_password2'] ";
    public static final String NEWPASSWORD_PAGE_SUBMIT_PASSWORD = "//*[@value='Restore'] ";

}
