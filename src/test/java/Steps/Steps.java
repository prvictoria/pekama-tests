package Steps;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.Assert;

import java.io.File;

import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public abstract class Steps {
    static final Logger rootLogger = LogManager.getRootLogger();

    abstract Boolean checkActualUrl(ObjectUser user, String url);
    //Selenide steps
    public static void clickSelector(SelenideElement element){
        element.waitUntil(exist, 20000).waitUntil(visible, 10000).click();
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

}

