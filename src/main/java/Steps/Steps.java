package Steps;
import Page.PageGitHubDashboard;
import Page.PageGitHubLogin;
import com.pekama.app.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Page.PageGitHubDashboard.*;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class Steps {
    private WebDriver driver;

    public Steps() {
        this.driver = new FirefoxDriver();
    }
    public void loginGitHub() {
        PageGitHubLogin page = new PageGitHubLogin(driver);
        page.openPage();
        page.login();

    }

    public boolean isUserLoggedIn() {
        PageGitHubDashboard startPage = new PageGitHubDashboard(driver);
        String actuaUserName = startPage.getLoggedInUserName();
        return actuaUserName.equals(USER_NAME_ATR_GITHUB);
    }
    public void closeBrowser() {
        this.driver.close();
    }
    public void initBrowser() {
        this.driver = new FirefoxDriver();
    }
}
