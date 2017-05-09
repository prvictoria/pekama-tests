package Draft;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by VatslauX on 09-May-17.
 */
public class Draft {
    static final org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();
    //ABSTRACT METHODS
//    public abstract void addMember(String email, SelenideElement button);

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
