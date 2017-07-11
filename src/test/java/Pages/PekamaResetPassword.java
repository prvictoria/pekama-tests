package Pages;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.Page;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class PekamaResetPassword extends Page {
    public static final SelenideElement RESET_PAGE_TITLE = $(byXpath("//*[@class='small-title']"));
    public static final String RESET_PAGE_TITLE_TEXT ="Reset Password";
    public static final SelenideElement RESET_PAGE_EMAIL = $(byXpath("//input[@name='email']"));
    public static final SelenideElement RESET_PAGE_RESET_BTN = $(byXpath("//input[@value='Reset password']"));
    public static final ElementsCollection RESET_PAGE_ERROR = $$(byXpath("//ul[@class='errorlist']/li"));

    public static final SelenideElement RESET_PAGE_SUCCESS = $(byXpath("//*[@class='form-group']"));
    public static final String RESET_PAGE_SUCCESS_MSG = "You've been sent an email with password restoration instructions.";

    public static final String RESET_PAGE_FINISHED_TITLE = "Password reset complete";
    public static final String RESET_PAGE_FINISHED_TEXT = "Your password has been set.";
    public static final SelenideElement RESET_PAGE_FINISHED_BTN_LOGIN = $(byXpath("//div[@class='inner-content row']//*[@type='button']"));

    public static final SelenideElement NEWPASSWORD_TITLE = $(byXpath("//*[@class='small-title']"));
    public static final String NEWPASSWORD_TITLE_TEXT = "Password restoration";
    public static final String NEWPASSWORD_TEXT = "Please enter your new password twice, so we can verify you typed it in correctly.";

    public static final SelenideElement NEWPASSWORD_PAGE_NEW_PASSWORD = $(byXpath("//*[@name='new_password1'] "));
    public static final SelenideElement NEWPASSWORD_PAGE_CONFIRM_PASSWORD = $(byXpath("//*[@name='new_password2'] "));
    public static final SelenideElement NEWPASSWORD_PAGE_RESTORE_BTN = $(byXpath("//*[@value='Restore'] "));

    public static final SelenideElement FAILED_RESET_TITLE = $(byXpath("//*[@class='small-title']"));
    public static final String FAILED_RESET_TITLE_TEXT = "Password reset unsuccessful";
    public static final String FAILED_RESET_TEXT_1 = "The password reset link was invalid";
    public static final String FAILED_RESET_TEXT_2 = "possibly because it has already been used.";
    public static final String FAILED_RESET_TEXT_3 = "Please request a new password reset.";

    //
}
