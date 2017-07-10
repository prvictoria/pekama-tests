package Steps;

import Steps.Intrefaces.ISelectDropdown;
import Steps.Intrefaces.ISwitchToWindow;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.Assert;

import java.io.File;

import static Steps.StepsModalWindows.submitConfirmAction;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Steps implements ISwitchToWindow, ISelectDropdown {
    static final Logger rootLogger = LogManager.getRootLogger();

    //Selenide steps
    public static void openUrl(String url){
        String actualUrl = getActualUrl();
        if(actualUrl.equals(url)==false){
            openPageWithSpinner(url);
        }
    }
    public static void clickSelector(SelenideElement element){
        element.waitUntil(exist, 20000).waitUntil(visible, 10000).waitUntil(enabled, 5000).click();
    }
    public static Boolean checkTextInSelector(SelenideElement element, String text){
        element.waitUntil(exist, 10000)
               .waitUntil(visible, 15000)
               .shouldHave(text(text));
        return true;
    }
    public static Boolean checkColourInSelector(SelenideElement element, String colourRgb){
        String selectedColor = element.getCssValue("color");
        Assert.assertTrue(selectedColor.equals("rgb("+colourRgb+")"));
        return true;
    }
    public static String absolutePath(String path) {
        return new File(path).getAbsolutePath();
    }
    public static void clickDeleteAndConfirm(SelenideElement element){
        sleep(2000);
        if(element.isDisplayed()) {
            element.click();
            submitConfirmAction();
            sleep(2000);
            waitForSpinnerNotPresent();
        }
    }
    public static void clickSelectIfEnabled(SelenideElement selector){
        selector.waitUntil(visible, 10000);
        if(selector.isEnabled()){
            clickSelector(selector);
            sleep(3000);
        }
    }

    @Override
    public Boolean switchToWindow(String windowName){
            rootLogger.info("Switch to "+windowName);
            switchTo().window(windowName);
        if (checkPageTitle(windowName)==true){
            return true;
        }
            if (checkPageTitle(windowName)==false){
                Assert.fail("Page is not "+windowName);
                return false;
            }
        return false;
    }

    @Override
    public void selectItemInDropdown(SelenideElement uiSelectName, SelenideElement uiSelectInput, final String selectableItemPath, String inputValue) {
        //rootLogger.info("select - "+inputValue);
        uiSelectName.waitUntil(visible, 20000).click();
        fillField(uiSelectInput, inputValue);
        FORMAT_ELEMENT_PATTERN (selectableItemPath, inputValue).waitUntil(visible, 15000).click();
        rootLogger.info("Selected value - "+inputValue);
    }
    @Override
    public void selectItemInDropdown(SelenideElement uiSelectName, SelenideElement uiSelectInput, SelenideElement selectableItem, String inputValue) {
        //rootLogger.info("select - "+inputValue);
        uiSelectName.waitUntil(visible, 20000).click();
        fillField(uiSelectInput, inputValue);
        selectableItem.waitUntil(visible, 15000).click();
        rootLogger.info("Selected value - "+inputValue);
    }

    public static final SelenideElement FORMAT_ELEMENT_PATTERN (String formatPath, String text) {
        String path = String.format(formatPath, text);
        SelenideElement element = $(byXpath(path));
        return element;
    }
}

