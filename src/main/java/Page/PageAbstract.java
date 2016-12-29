package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public abstract class PageAbstract {
    private WebDriver driver;

    public PageAbstract(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
