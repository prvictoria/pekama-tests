package Steps;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Page extends Steps{
    public static final SelenideElement SPINNER_IN_BUTTON = $(byXpath("//button//i[@class='pkm-icon-spinner icon-spin']"));
}
