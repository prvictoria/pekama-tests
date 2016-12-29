package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PageGitHubLogin {
    private WebDriver driver;
    private final static String URL_GITHUB= "https://github.com/login";
    public static final String USER_NAME_GITHUB = "VatslauX";
    public static final String USER_PASSWORD_GITHUB = "14wresd7iu12qew8";
    public static final String USER_ICON_GITHUB = ".//*[@id='user-links']/li[3]/a/img";
    @FindBy(name = "login")
    private WebElement loginField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(name = "commit")
    private WebElement submitField;

    public PageGitHubLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.get(URL_GITHUB);
    }

    public void login() {
        loginField.sendKeys(USER_NAME_GITHUB);
        passwordField.sendKeys(USER_PASSWORD_GITHUB);
        submitField.sendKeys(USER_ICON_GITHUB);
    }
}
