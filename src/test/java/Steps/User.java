package Steps;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.NEWPASSWORD_PAGE_CONFIRM_PASSWORD;
import static Page.PekamaResetPassword.NEWPASSWORD_PAGE_NEW_PASSWORD;
import static Page.PekamaResetPassword.NEWPASSWORD_PAGE_RESTORE_BTN;
import static Page.PekamaSignUp.*;
import static Page.PekamaSignUp.signupNext;
import static Page.PekamaSignUp.signupPassword;
import static Page.TestsCredentials.GENERIC_PEKAMA_PASSWORD;
import static Page.TestsCredentials.VALID_PASSWORD;
import static Page.UrlConfig.setEnvironment;
import static Page.UrlStrings.URL_Dashboard;
import static Page.UrlStrings.URL_SingUp;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by Viachaslau_Balashevi on 3/29/2017.
 */
public class User {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String email;
    public String password;
    public String name;
    public String surname;
    public String company;
    public Boolean isSignUpSucceed;
    public Boolean isLoginSucceed;

    public void loginByURL(String email, String password, String url){
        this.email = email;
        this.password = password;
        openUrlWithBaseAuth(url);
        submitCookie();
        hideZopim();
        submitLoginCredentials(email, password);
    }
    public void submitLoginCredentials(String email, String password){
        this.email = email;
        this.password = password;

        scrollUp();
        if(email!=null) {
            fillField(loginField_Email, email);
            rootLogger.info(email + " - login selected");
        }
        if(password!=null) {
            fillField(loginField_Password, password);
        }
        submitCookie();
        hideZopim();
        submitEnabledButton(loginButton_Login);
        sleep(5000);
    }

    public boolean submitSignUp(String email, String password, String name, String surname, String company){
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.company = company;
        if(email!=null) {
            signupEmail.waitUntil(visible, 20000).sendKeys(email);
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
    public String submitResetPassword(String newPassword){
        if(newPassword==null){
        newPassword = VALID_PASSWORD+randomString(10);
        }
        NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(newPassword);
        NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(newPassword);
        this.password = newPassword;
        return newPassword;
    }
    public String submitResetPassword(String newPassword, String confirmPassword){
        if(newPassword!=null){
            NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(newPassword);
        }
        if(confirmPassword!=null){
            NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(confirmPassword);
        }
        NEWPASSWORD_PAGE_RESTORE_BTN.shouldBe(visible).click();
        this.password = newPassword;
        return newPassword;
    }
    @Test
    public void testDebug()throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        String AUTH_URL = URL_SingUp;
        openUrlWithBaseAuth(AUTH_URL);
        User user1 = new User();
        user1.submitSignUp(
                "123@mail.com",
                "Aa@2132312",
                "21312321",
                "asd",
                "sasasa");
        rootLogger.info(user1.company);
        rootLogger.info(user1.isSignUpSucceed);
    }
}