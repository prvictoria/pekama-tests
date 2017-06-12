package Tests.TestsCommunity;

import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.*;

import java.io.IOException;

import static Page.NewCommunity.PageSignIn.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Page.PekamaLogin.*;
import static Page.TestsCredentials.*;
import static Steps.ObjectUser.Users.USER_01;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsNewCommunity.Account.logout;
import static Steps.StepsNewCommunity.Login.submitLogin;
import static Steps.StepsNewCommunity.Login.validateSubmitLogin;
import static Steps.StepsPekama.checkText;
import static Steps.StepsPekama.fillField;
import static Steps.StepsPekama.openUrlIfActualNotEquals;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class TestsCommunityLogin {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final ObjectUser registeredUser = new ObjectUser(newBuilder()).buildUser(USER_01);

    @BeforeClass
    public void setUp() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        openUrlIfActualNotEquals(JOIN_URL);
    }
    @BeforeMethod
    public void before() {
        refresh();
    }
    @Test
    public void loginValidationAllFieldsEmpty(){
        ObjectUser user = newBuilder().build();
        submitLogin(user);
        validateSubmitLogin(false, "This field may not be blank.", 2);
    }
    @Test
    public void loginValidationLoginFieldEmpty(){
        ObjectUser user = newBuilder().passwordPekama("121212121").build();
        submitLogin(user);
        validateSubmitLogin(false, "This field may not be blank.", 1);
    }
    @Test
    public void loginValidationPasswordFieldEmpty(){
        ObjectUser user = newBuilder().email("1234@email.com").build();
        submitLogin(user);
        validateSubmitLogin(false, "This field may not be blank.", 1);
    }
    @Test
    public void loginValidationWrongCredentials(){
        ObjectUser user = newBuilder().email(registeredUser.email).passwordPekama("1212121212").build();
        submitLogin(user);
        validateSubmitLogin(false, "Please enter a correct email and password. Note that the password field is case-sensitive.", 1);
    }


    @Test
    public void loginUpperCaseLogin(){
        ObjectUser user = newBuilder().email(registeredUser.email.toUpperCase()).passwordPekama(registeredUser.passwordPekama).build();
        submitLogin(user);
        validateSubmitLogin(true, null, null);
        logout();

    }
    @Test
    public void loginLowerCaseLogin(){
        ObjectUser user = newBuilder().email(registeredUser.email.toLowerCase()).passwordPekama(registeredUser.passwordPekama).build();
        submitLogin(user);
        validateSubmitLogin(true, null, null);
        logout();
    }
}


