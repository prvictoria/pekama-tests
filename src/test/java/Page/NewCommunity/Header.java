package Page.NewCommunity;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class Header {
    public static final SelenideElement HEADER_TAB_PEKAMA = $(byXpath("//div[@class='header__nav']/nav[1]/a[1]"));
    public static final SelenideElement HEADER_TAB_EXPERTS = $(byXpath("//div[@class='header__nav']/nav[1]/a[2]"));
    public static final SelenideElement HEADER_TAB_CASES = $(byXpath("//div[@class='header__nav']/nav[1]/a[3]"));
    public static final SelenideElement HEADER_TAB_PROFILE = $(byXpath("//div[@class='header__nav']/nav[1]/a[4]"));

    public static final SelenideElement HEADER_TAB_ABOUT = $(byXpath("//div[@class='header__nav']/nav[2]/a[1]"));
    public static final SelenideElement HEADER_TAB_JOIN = $(byXpath("//div[@class='header__nav']/nav[2]/div/a"));
    public static final SelenideElement HEADER_TAB_ME = $(byXpath("//div[@class='header__nav']/nav[2]/a[2]"));

}
