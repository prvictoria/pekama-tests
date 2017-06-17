package Page.NewCommunity;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
/**
 * Created by VatslauX on 12-Jun-17.
 */
public class PageMyAccount extends ModuleHeader {
    public static final String ACCOUNT_URL = "https://communitystaging.pekama.com/personal";

    public static final SelenideElement ACCOUNT_LOGOUT = $(byXpath("//a[text()='Sign out']"));
    //ABOUT FORM
    public static final String ACCOUNT_ABOUT_FORM = "//form[@name='logInForm']";
    public static final SelenideElement ACCOUNT_ABOUT_NAME = $(byXpath(ACCOUNT_ABOUT_FORM+""));
    public static final SelenideElement ACCOUNT_ABOUT_SURNAME = $(byXpath(ACCOUNT_ABOUT_FORM+""));
}
