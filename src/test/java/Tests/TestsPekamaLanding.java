package Tests;
import Steps.StepsHttpAuth;
import com.codeborne.selenide.Condition;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static Page.PekamaLanding.*;
import static Page.PekamaLogin.*;
import static Page.PekamaSignUp.*;
import static Page.UrlConfig.*;
import static Page.TestsCredentials.*;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaLanding {
    @Rule
    public Timeout tests = Timeout.seconds(400);
    @BeforeClass
    public static void beforeClass() throws IOException {
        setEnvironment();
        setBrowser();
        holdBrowserAfterTest();
    }
    @Before
    public void openUrlLogin() {
        StepsHttpAuth openHost = new StepsHttpAuth();
        String AUTH_URL = ENVIRONMENT_PEKAMA;
        openHost.openUrlWithBaseAuth(AUTH_URL);
    }
    @Test //GUI
    public void openLandingGui() {
        BTN_LOGIN.shouldBe(Condition.visible);
        BTN_SIGN_UP.shouldBe(Condition.visible);
        BTN_ABOUT.shouldBe(Condition.visible);
        BTN_BENEFITS.shouldBe(Condition.visible);
        BTN_VIDEO.shouldBe(Condition.visible);
        BTN_TEAM.shouldBe(Condition.visible);
        FIELD_EMAIL.shouldBe(Condition.visible).shouldHave(Condition.value(""));
        BTN_TRY_IT.shouldBe(Condition.visible);
  }
    @Test //Goto login page
    public void openLoginPage() {
        BTN_LOGIN.shouldBe(Condition.visible).click();
        sleep(1000);
        lOGIN_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
    }
    @Test //Goto signup page
    public void openSignupPage() {
        BTN_SIGN_UP.shouldBe(Condition.visible).click();
        sleep(1000);
        SIGN_UP_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(SIGN_UP_TITLE_TEXT));
    }
    @Test //Goto Tty it - email is prepopulated
    public void openSignupPageWithEmail() {
        FIELD_EMAIL.shouldBe(Condition.visible).sendKeys("12345@email.com");
        BTN_TRY_IT.shouldBe(Condition.visible).click();
        sleep(1000);
        SIGN_UP_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(SIGN_UP_TITLE_TEXT));
        signupEmail.shouldBe(Condition.visible).shouldHave(Condition.value("12345@email.com"));
    }
    @Test //Open landing after login
    public void openLandingAfterLogin() {
        BTN_LOGIN.shouldBe(Condition.visible).click();
        sleep(1000);
        lOGIN_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
        loginField_Email.sendKeys(User1.GMAIL_EMAIL.getValue());
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        openUrlWithBaseAuth(ENVIRONMENT_PEKAMA);
        BTN_DASHBOARD.shouldBe(Condition.visible);
        BTN_LOG_OUT.shouldBe(Condition.visible).click();
        sleep(1000);
        $(BTN_TRY_IT).shouldBe(Condition.visible);
    }
}
