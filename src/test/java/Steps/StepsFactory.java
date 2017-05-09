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

    //Selenide steps
    public void click(SelenideElement element){
        element.waitUntil(exist, 15000).waitUntil(visible, 10000).click();
    }
}

