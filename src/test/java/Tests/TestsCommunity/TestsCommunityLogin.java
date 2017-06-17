package Tests.TestsCommunity;

import Page.NewCommunity.PageJoin;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.*;

import java.io.IOException;

import static Page.NewCommunity.PageJoin.*;
import static Page.UrlConfig.*;
import static Steps.ObjectUser.Users.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsPekama.checkText;
import static Steps.StepsPekama.fillField;
import static Steps.StepsPekama.openUrlIfActualNotEquals;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class TestsCommunityLogin {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final ObjectUser registeredUser = new ObjectUser(newBuilder()).buildUser(USER_01);
    private PageJoin pageJoin;

    @BeforeClass
    public void setUp() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @BeforeMethod
    public void before() {
        pageJoin = new PageJoin();
        openUrlIfActualNotEquals(JOIN_URL);
        refresh();
    }
    @Test
    public void loginValidationAllFieldsEmpty(){
        ObjectUser user = newBuilder().build();
        pageJoin.submitLogin(user);
        pageJoin.validateSubmitLogin(false, "This field may not be blank.", 2);
    }
    @Test
    public void loginValidationLoginFieldEmpty(){
        ObjectUser user = newBuilder().passwordPekama("121212121").build();
        pageJoin.submitLogin(user);
        pageJoin.validateSubmitLogin(false, "This field may not be blank.", 1);
    }
    @Test
    public void loginValidationPasswordFieldEmpty(){
        ObjectUser user = newBuilder().email("1234@email.com").build();
        pageJoin.submitLogin(user);
        pageJoin.validateSubmitLogin(false, "This field may not be blank.", 1);
    }
    @Test
    public void loginValidationWrongCredentials(){
        ObjectUser user = newBuilder().email(registeredUser.email).passwordPekama("1212121212").build();
        pageJoin.submitLogin(user);
        pageJoin.validateSubmitLogin(false, "Please enter a correct email and password. Note that the password field is case-sensitive.", 1);
    }


    @Test
    public void loginUpperCaseLogin(){
        ObjectUser user = newBuilder().email(registeredUser.email.toUpperCase()).passwordPekama(registeredUser.passwordPekama).build();
        pageJoin.submitLogin(user);
        pageJoin.validateSubmitLogin(true, null, null);
        pageJoin.logout();

    }
    @Test
    public void loginLowerCaseLogin(){
        ObjectUser user = newBuilder().email(registeredUser.email.toLowerCase()).passwordPekama(registeredUser.passwordPekama).build();
        pageJoin.submitLogin(user);
        pageJoin.validateSubmitLogin(true, null, null);
        pageJoin.logout();
    }
}


