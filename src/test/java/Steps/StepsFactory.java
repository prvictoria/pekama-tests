package Steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static Page.PekamaLogin.*;
import static Page.TestsCredentials.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public abstract class StepsFactory {
    static final Logger rootLogger = LogManager.getRootLogger();

    //ABSTRACT METHODS
//    public abstract void addMember(String email, SelenideElement button);

    @Deprecated
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String urlLogIn){
        openUrlWithBaseAuth(urlLogIn);
        hideZopim();
        rootLogger.info(urlLogIn+ "opened");
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }
    @Deprecated
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String USER_PEKAMA_PASSWORD, String urlLogIn){
        openUrlWithBaseAuth(urlLogIn);
        hideZopim();
        rootLogger.info(urlLogIn+ "opened");
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }

    public boolean switchToFrame( String id ) {
        boolean switched = false;
        List<WebElement> elements = new ArrayList<WebElement>();

        elements = getWebDriver().findElements( By.tagName("iframe") );
        for ( WebElement el : elements ) {
            if ( el.getAttribute("id").equals("frameid")) {
                int i = Integer.parseInt(null);
                switchTo().frame( elements.get(i) );
                switched = true;
            }
        }
        if ( switched == false ) rootLogger.info("Failed to switch windows.");
        return switched;
    }
    public static void scrollTo(SelenideElement locator) {
        WebDriver dr = getWebDriver() ;
        ((JavascriptExecutor) dr)
                .executeScript("arguments[0].scrollIntoView(true);", locator.$(""));
    }
    public static void scrollBottom() {
        WebDriver dr = getWebDriver() ;
        ((JavascriptExecutor) dr)
                .executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
    }
    public static void setSlider(SelenideElement handleLocator, SelenideElement trackLocator, int percentage) {
        WebDriver dr = getWebDriver() ;
        int width = Integer.parseInt(trackLocator.getCssValue("width").replace("px", ""));
        int dx = (int) (percentage / 100.0 * width);
        (new Actions(dr)).dragAndDropBy(handleLocator, -999, 0).perform();
        (new Actions(dr)).dragAndDropBy(handleLocator, dx, 0).perform();
    }
//// switch frame by its index
//		driver.switchTo().frame(0);
//		driver.findElement(By.linkText("All Classes")).click();
//
//    // switch to parent frame
//		driver.switchTo().parentFrame();
//
//    // switch frame by its name
//		driver.switchTo().frame("packageFrame");
//		driver.findElement(By.linkText("AbstractWebDriverEventListener")).click();
//
//    // switch to parent frame
//		driver.switchTo().parentFrame();
//
//    // switch frame by identifying it as a web element
//		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@title='Package, class and interface descriptions']")));
//		driver.findElement(By.linkText("Deprecated")).click();
    public boolean handleAlert(String alertName) {
        String alertMessage = "";
        boolean handled = false;
        try {
            if( switchTo().alert() != null ) {
                Alert alert = switchTo().alert();
                alertMessage =  alert.getText();
                alert.dismiss();
                handled = true;
                if (alertMessage.contains(alertName) ) {
                    System.out.println("Caught name field error.");
                }
            }
        } catch ( Exception e ) {
            System.out.println("Failure to catch and dismiss alert message box.");
        }
        return handled;
    }
}

