package Pages.NewCommunity;

import com.codeborne.selenide.SelenideElement;

import static Steps.StepsPekama.scrollDown;
import static Steps.StepsPekama.scrollUp;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PageAbout extends ModuleHeader{
    public static final String ABOUT_URL = "https://communitystaging.pekama.com/about";

    public static final SelenideElement ABOUT_ = $(byXpath(""));
    public static final SelenideElement ABOUT_BUTTON1_START = $(byXpath("//div[@class='bigicon-container']//a[./i]"));
    public static final SelenideElement ABOUT_BUTTON2_START = $(byXpath("//div[@class='about-us-container']//a[./i]"));

    public PageAbout clickUpperStartButton(){
        scrollUp();
        clickSelector(ABOUT_BUTTON1_START);
        return this;
    }
    public PageAbout clickBottomStartButton(){
        scrollDown();
        clickSelector(ABOUT_BUTTON1_START);
        return this;
    }
}
