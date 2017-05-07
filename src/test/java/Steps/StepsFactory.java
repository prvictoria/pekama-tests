package Steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;

import static Page.PekamaLogin.*;
import static Page.TestsCredentials.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
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


}

