package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

import static Page.PekamaSignUp.*;
import static Page.PekamaSignUp.signupNext;
import static Page.PekamaSignUp.signupPassword;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.URL_SingUp;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by Viachaslau_Balashevi on 3/29/2017.
 */
public class SignUp {
    static final Logger rootLogger = LogManager.getRootLogger();
    String email;
    String password;
    String name;
    String surname;
    String company;
    Boolean isSignUpSucceed;
    public boolean signUp(String email,  String password, String name, String surname, String company){
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.company = company;
        if(email!=null) {
            signupEmail.shouldBe(visible).sendKeys(email);
        }
        if(name!=null) {
            signupName.shouldBe(visible).sendKeys(name);
        }
        if(surname!=null) {
            signupSurname.shouldBe(visible).sendKeys(surname);
        }
        if(company!=null) {
            signupCompany.shouldBe(visible).sendKeys(company);
        }
        if(password!=null) {
            signupPassword.shouldBe(visible).sendKeys(password);
        }
        signupNext.shouldBe(visible).shouldNot(disabled).click();
        sleep(3000);

        if($(byText("Confirm your Account")).exists()){
            isSignUpSucceed = true;
            return true;}
        if($(byText("Teams on Your Domain")).exists()){
            isSignUpSucceed = true;
            return true;}
        isSignUpSucceed = false;
        return false;
    }
    @Test
    public void testDebug()throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        String AUTH_URL = URL_SingUp;
        openUrlWithBaseAuth(AUTH_URL);
        SignUp user1 = new SignUp();
        user1.signUp(
                "123@mail.com",
                "Aa@2132312",
                "21312321",
                "asd",
                "sasasa");
        rootLogger.info(user1.company);
        rootLogger.info(user1.isSignUpSucceed);
    }
}