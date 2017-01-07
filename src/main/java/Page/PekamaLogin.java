package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaLogin {
    public static final String lOGIN_TITLE = "//*[@class='title']";
    public static final String lOGIN_TITLE_TEXT = "Login";

    public static final String loginLinkedin = "";
    public static final String loginGoogle = "";
    public static final String loginField_Email = "#loginEmail";
    public static final String loginField_Password = "#loginPassword";
    public static final String loginButton_Login = "//button[@type='submit']";

    public static final String btnLogin = "//a[@href='/accounts/login/']";
    public static final String btnSignup = "//a[@href='/signup/']";
    public static final String loginError = "//ul[@class='errorlist nonfield']/li";
    public static final String loginErrorMsg = "Please enter a correct email address and password. Note that both fields may be case-sensitive.";
    public static final String LINK_FORGOT_PASSWORD = "//a[contains(@href, '/accounts/password/reset/')]";

}
