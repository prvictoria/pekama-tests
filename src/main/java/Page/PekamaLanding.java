package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class PekamaLanding {

    public static final SelenideElement landing = $(byXpath(""));
    public static final SelenideElement BTN_LOGIN = $(byXpath("//a[contains(@href, '/accounts/login/')]"));
    public static final SelenideElement BTN_SIGN_UP = $(byXpath("//a[contains(@href, '/signup/')]"));
    public static final SelenideElement BTN_LOG_OUT = $(byXpath("//a[contains(@href, '/accounts/logout/')]"));
    public static final SelenideElement BTN_DASHBOARD = $(byXpath("//a[contains(@href, '/a/')]"));
    public static final SelenideElement BTN_ABOUT = $(byXpath("//a[contains(@href, '/#introduction')]"));
    public static final SelenideElement BTN_BENEFITS = $(byXpath("//a[contains(@href, '/#connect')]"));
    public static final SelenideElement BTN_VIDEO = $(byXpath("//a[contains(@href, '/#watch-video')]"));
    public static final SelenideElement BTN_TEAM = $(byXpath("//a[contains(@href, '/#team')]"));
    public static final SelenideElement FIELD_EMAIL = $(byXpath("#exampleInputEmail3"));
    public static final SelenideElement BTN_TRY_IT = $(byXpath("#tryItOutButton"));

}
