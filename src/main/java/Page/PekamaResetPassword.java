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

    public static final String RESET_PAGE_SUCCESS = "//*[@class='form-group']";
    public static final String RESET_PAGE_SUCCESS_MSG = "You've been sent an email with password restoration instructions.";

    public static final String RESET_PAGE_FINISHED_TITLE = "Password reset complete";
    public static final String RESET_PAGE_FINISHED_TEXT = "Your password has been set.";
    public static final String RESET_PAGE_FINISHED_BTN_LOGIN = "//div[@class='inner-content row']//*[@type='button']";

    public static final String NEWPASSWORD_TITLE = "//*[@class='small-title']";
    public static final String NEWPASSWORD_TITLE_TEXT = "Password restoration";
    public static final String NEWPASSWORD_TEXT = "Please enter your new password twice, so we can verify you typed it in correctly.";

    public static final String NEWPASSWORD_PAGE_NEW_PASSWORD = "//*[@name='new_password1'] ";
    public static final String NEWPASSWORD_PAGE_CONFIRM_PASSWORD = "//*[@name='new_password2'] ";
    public static final String NEWPASSWORD_PAGE_RESTORE_BTN = "//*[@value='Restore'] ";

    public static final String FAILED_RESET_TITLE = "//*[@class='small-title']";
    public static final String FAILED_RESET_TITLE_TEXT = "Password reset unsuccessful";
    public static final String FAILED_RESET_TEXT_1 = "The password reset link was invalid";
    public static final String FAILED_RESET_TEXT_2 = "possibly because it has already been used.";
    public static final String FAILED_RESET_TEXT_3 = "Please request a new password reset.";
}