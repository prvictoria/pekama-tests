package Steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static Page.PekamaLogin.*;
import static Page.PekamaReports.REPORTS_ALL_CHECKBOX;
import static Page.PekamaReports.REPORTS_PLACEHOLDER_NO_DATA;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.PLACEHOLDER_NO_DATA;
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

    //Selenide steps
    public static void clickSelector(SelenideElement element){
        element.waitUntil(exist, 15000).waitUntil(visible, 10000).click();
    }
    public static Boolean  checkTextInSelector(SelenideElement element, String text){
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

}

