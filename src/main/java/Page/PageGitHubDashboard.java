package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PageGitHubDashboard {
    private WebDriver driver;
    public static final String USER_NAME_ATR_GITHUB = "@VatslauX";

    @FindBy(xpath = ".//*[@id='user-links']/li[3]/a/img")
    private WebElement userNameLable;

    public PageGitHubDashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    public String getLoggedInUserName() {
        return userNameLable.getAttribute("alt");

    }
}
