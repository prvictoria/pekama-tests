package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 04-Jan-17.
 */
public class CommunityLanding  extends Page {
    public static final SelenideElement LANDING_ = $(byXpath(""));
    public static final SelenideElement LANDING_ABOUT = $(byXpath("//*[@href='#about-section']"));
    public static final SelenideElement LANDING_WHY = $(byXpath("//*[@href='#why-section']"));
    public static final SelenideElement LANDING_SIGNUP = $(byXpath("//*[@type='button' and @href='/signup/?next=/a/']"));
    public static final SelenideElement LANDING_LOGIN = $(byXpath("//*[@type='button' and @href='/accounts/login/?next=/a/']"));
    public static final SelenideElement LANDING_LOGOUT = $(byXpath("//*[@type='button' and @href='/accounts/logout/"));
    public static final SelenideElement LANDING_DASHBOARD = $(byXpath("//*[@type='button' and @href='/a/"));
    public static final SelenideElement LANDING_EXPLORE_UPPER = $(byXpath("//div[@class='global-community']/a"));
    public static final SelenideElement LANDING_EXPLORE_FOOTER = $(byXpath("//div[@class='explore-button-holder']/a"));
//    public static final SelenideElement LANDING_ = $(byXpath(""));
//    public static final SelenideElement LANDING_ = $(byXpath(""));
//    public static final SelenideElement LANDING_ = $(byXpath(""));
//    public static final SelenideElement LANDING_ = $(byXpath(""));
}
