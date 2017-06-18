package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.Page;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaLogin extends Page {
    public static final SelenideElement lOGIN_TITLE = $(byXpath("//*[@class='title']"));
    public static final String lOGIN_TITLE_TEXT = "Login";

    public static final SelenideElement loginLinkedin = $(byXpath(""));
    public static final SelenideElement loginGoogle = $(byXpath(""));
    public static final SelenideElement loginField_Email = $(byId("loginEmail"));
    public static final SelenideElement loginField_Password = $(byId("loginPassword"));
    public static final SelenideElement loginButton_Login = $(byXpath("//button[@type='submit']"));

    public static final SelenideElement btnLogin = $(byXpath("//a[@href='/accounts/login/']"));
    public static final SelenideElement btnSignup = $(byXpath("//a[@href='/signup/']"));
    public static final SelenideElement loginError = $(byXpath("//ul[@class='errorlist nonfield']/li"));
    public static final String loginErrorMsg = "Please enter a correct email address and password. Note that both fields may be case-sensitive.";
    public static final SelenideElement LINK_FORGOT_PASSWORD = $(byXpath("//a[contains(@href, '/accounts/password/reset/')]"));

}
