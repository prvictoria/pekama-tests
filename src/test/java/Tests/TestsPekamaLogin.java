package Tests;
import Steps.ObjectUser;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.Timeout;

import java.io.IOException;

import static Pages.UrlConfiguration.*;
import static Pages.UrlStrings.*;
import static Pages.PekamaLogin.*;
import static Pages.DataCredentials.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsPekama.checkText;
import static Steps.StepsPekama.fillField;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class TestsPekamaLogin {
    static final Logger rootLogger = LogManager.getRootLogger();
    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void openUrlLogin() {
        openUrlWithBaseAuth(URL_LOGIN);
    }

    @Test
    public void testEnvironment() {
        openUrlWithBaseAuth(ENVIRONMENT_PEKAMA);
        rootLogger.info(ENVIRONMENT_PEKAMA +" - opened");
        openUrlWithBaseAuth(ENVIRONMENT_COMMUNITY);
        rootLogger.info(ENVIRONMENT_COMMUNITY +" - opened");
    }
    @Test
    public void invalidPassword() {
        ObjectUser user = newBuilder().build();
        user.submitLoginCredentials(
                "testqweeco001@gmail.com",
                "12345");
        loginError.shouldHave(Condition.exactText(loginErrorMsg));
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
    }
    @Test
    public void invalidLogin() {
        ObjectUser user = newBuilder().build();
        user.submitLoginCredentials(
                "1a2a3a12aa31231@gmail.com",
                "asui67we34");
        loginError.shouldHave(Condition.exactText(loginErrorMsg));
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
    }
    @Test
    public void invalidLoginAndPassword() {
        ObjectUser user = newBuilder().build();
        user.submitLoginCredentials(
                "teastaaaqweeco001@gmail.com",
                "asui2132367we34");
        loginError.shouldHave(Condition.exactText(loginErrorMsg));
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
    }
    @Test
    public void blankLoginPassword() {
        $(loginField_Email).waitUntil(visible, 30000).getAttribute("required");
        assertTrue($(loginField_Email).getAttribute("required"), true);
        loginField_Password.getAttribute("required");
        assertTrue(loginField_Password.getAttribute("required"), true);

        ObjectUser user = newBuilder().build();
        user.submitLoginCredentials(
                null,
                "asui2132367we34");
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
        loginField_Password.clear();
        loginField_Password.shouldBe(empty);
        rootLogger.info("ILogin only was submitted");

        user.submitLoginCredentials(
                "teastaaaqweeco001@gmail.com",
                "");
        fillField(loginField_Email, "teastaaaqweeco001@gmail.com");
        loginButton_Login.click();
        loginField_Email.clear();
        loginField_Email.shouldBe(empty);
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
        rootLogger.info("Password only was submitted");

        user.submitLoginCredentials(
                "",
                "");
        loginField_Password.clear();
        loginField_Email.clear();
        loginButton_Login.click();
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
        rootLogger.info("Blank Credentials were submitted");
    }
    @Test
    public void validCredentials() {
        ObjectUser user = newBuilder().build();
        user.submitLoginCredentials(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue());
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(5000);
        String testDashboardUrl = url();
        assertEquals(URL_DASHBOARD, testDashboardUrl);
        rootLogger.info(url()+"Dashboard is opened");
        openUrlWithBaseAuth(URL_LOGOUT);
    }
}
