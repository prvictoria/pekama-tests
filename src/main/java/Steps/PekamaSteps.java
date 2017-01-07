package Steps;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static Page.DirectLinks.urlLogIn;
import static Page.PekamaLogin.*;
import static Page.PekamaLogin.btnSignup;
import static Page.TestsCredentials.USER_EMAIL_01;
import static Page.TestsCredentials.USER_PEKAMA_PASSWORD;
import static Page.TestsUrlConfiguration.TEST_ENVIROMENT_PEKAMA;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by VatslauX on 03-Jan-17.
 */
public class PekamaSteps {
    static final Logger logging = LogManager.getLogger(PekamaSteps.class);

    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL){
        open(urlLogIn); //HOST define PEKAMA or COMMUNITY redirect after login
        logging.info(urlLogIn+ "opened");
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        logging.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        logging.info("Valid Credentials were submitted");
    }
}
