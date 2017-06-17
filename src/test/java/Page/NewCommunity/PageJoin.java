package Page.NewCommunity;

import Steps.ILogout;
import Steps.IResetPassword;
import Steps.ISignUp;
import Steps.ObjectUser;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static Page.NewCommunity.PageMyAccount.ACCOUNT_LOGOUT;
import static Steps.Steps.clickSelector;
import static Steps.StepsPekama.*;
import static Steps.StepsPekama.checkText;
import static Steps.StepsPekama.openUrlIfActualNotEquals;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PageJoin extends ModuleHeader implements IResetPassword, ISignUp, ILogout {
    public static final String JOIN_URL = "https://communitystaging.pekama.com/login";

    //MODULE LOGIN FORM
    public static final String JOIN_LOGIN_FORM = "//form[@name='logInForm']";
    public static final SelenideElement JOIN_LOGIN_EMAIL = $(byXpath("//form[@name='logInForm']//input[@name='email']"));
    public static final SelenideElement JOIN_LOGIN_PASSWORD = $(byXpath("//form[@name='logInForm']//input[@name='password']"));
    public static final SelenideElement JOIN_LOGIN_REMEMBER_ME = $(byXpath("//form[@name='logInForm']//input[@type='checkbox']/following-sibling::i"));
    public static final SelenideElement JOIN_LOGIN_REMEMBER_ME_VALUE = $(byXpath("//form[@name='logInForm']//input[@type='checkbox']"));
    public static final SelenideElement JOIN_LOGIN_RESET_PASSWORD = $(byXpath("//form[@name='logInForm']//a[contains(.,'Reset my password')]"));
    public static final SelenideElement JOIN_LOGIN_SUBMIT = $(byXpath("//form[@name='logInForm']//button"));

    //MODULE RESET PASSWORD
    public static final String JOIN_RESET_FORM = "//form[@name='resetPasswordForm']";
    public static final SelenideElement JOIN_RESET_EMAIL = $(byXpath(JOIN_RESET_FORM+"//input[@name='email']"));
    public static final SelenideElement JOIN_RESET_BACK = $(byXpath(JOIN_RESET_FORM+"//button[contains(.,'Back')]"));
    public static final SelenideElement JOIN_RESET_SUBMIT = $(byXpath(JOIN_RESET_FORM+"//button[contains(.,'Submit')]"));

    //MODULE JOIN SIGN UP
    public static final String JOIN_SIGN_UP_FORM = "//form[@name='signUpForm']";
    public static final SelenideElement JOIN_NAME = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='first_name']"));
    public static final SelenideElement JOIN_SURNAME = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='last_name']"));
    public static final SelenideElement JOIN_COMPANY = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='company']"));
    public static final SelenideElement JOIN_EMAIL = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='email']"));
    public static final SelenideElement JOIN_PASSWORD = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@name='password']"));
    public static final SelenideElement JOIN_SELECT_BUSINESS_TYPE = $(byXpath(JOIN_SIGN_UP_FORM+"//label[text()='Buisness type']/following-sibling::div//span"));
    public static final SelenideElement JOIN_INPUT_BUSINESS_TYPE = $(byXpath(JOIN_SIGN_UP_FORM+"//label[text()='Buisness type']/following-sibling::div//input[@type='search']"));
    public static final SelenideElement JOIN_SELECT_ROLE = $(byXpath(JOIN_SIGN_UP_FORM+"//label[text()='Your role']/following-sibling::div//span"));
    public static final SelenideElement JOIN_INPUT_ROLE = $(byXpath(JOIN_SIGN_UP_FORM+"//label[text()='Your role']/following-sibling::div//input[@type='search']"));
    public static final SelenideElement JOIN_PHONE = $(byXpath(JOIN_SIGN_UP_FORM+""));
    public static final SelenideElement JOIN_SELECT_COUNTRY = $(byXpath(JOIN_SIGN_UP_FORM+""));
    public static final SelenideElement JOIN_INPUT_COUNTRY = $(byXpath(JOIN_SIGN_UP_FORM+""));
    public static final SelenideElement JOIN_SIGN_UP_SUBMIT = $(byXpath(JOIN_SIGN_UP_FORM+"//button"));

    public static final SelenideElement JOIN_AGREE_TERMS = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@type='checkbox']/following-sibling::i"));
    public static final SelenideElement JOIN_AGREE_TERMS_VALUE = $(byXpath(JOIN_SIGN_UP_FORM+"//input[@type='checkbox']"));
    public static final SelenideElement JOIN_TERMS_LINK = $(byXpath(JOIN_SIGN_UP_FORM+"//*[@href='https://staging.pekama.com/about/terms/']"));
    public static final SelenideElement JOIN_ = $(byXpath(JOIN_SIGN_UP_FORM+""));

    public void submitLogin(ObjectUser user){
        openUrlIfActualNotEquals(JOIN_URL);
        if(user.email!=null){
            fillField(JOIN_LOGIN_EMAIL, user.email, "Email filled");
        }
        if(user.passwordPekama!=null){
            fillField(JOIN_LOGIN_PASSWORD, user.passwordPekama, "Password filled");
        }
        clickSelector(JOIN_LOGIN_SUBMIT);
        return;
    }
    public Boolean validateSubmitLogin(Boolean submittedDataIsValid, String error, Integer errorQty){
        openUrlIfActualNotEquals(JOIN_URL);
        if(submittedDataIsValid==true){
            HEADER_TAB_JOIN.shouldNot(visible);
            HEADER_TAB_ME.should(visible);
            return true;
        }
        else {
            checkText(error, errorQty);
            return false;
        }
    }

    @Override
    public void submitResetPassword(ObjectUser user){
        openUrlIfActualNotEquals(JOIN_URL);
        clickSelector(JOIN_LOGIN_RESET_PASSWORD);
        if(user.email!=null){
            fillField(JOIN_RESET_EMAIL, user.email);
        }
        clickSelector(JOIN_RESET_SUBMIT);
    }

    public Boolean validateSubmitResetPassword(
            Boolean submittedDataIsValid, String error){
        openUrlIfActualNotEquals(JOIN_URL);
        if(submittedDataIsValid==true){
            checkText("The password restoration instructions has been sent to your email address, please check your inbox.");
            return true;
        }
        else {
            checkText(error);
            return false;
        }
    }

    @Override
    public Boolean submitSignUp(ObjectUser user){
        if(user.name!=null) {
            JOIN_NAME.waitUntil(visible, 20000).sendKeys(user.name);
        }
        if(user.surname!=null) {
            JOIN_SURNAME.waitUntil(visible, 20000).sendKeys(user.surname );
        }
        if(user.company!=null) {
            JOIN_COMPANY.waitUntil(visible, 20000).sendKeys(user.company);
        }
        if(user.email!=null) {
            JOIN_EMAIL.waitUntil(visible, 20000).sendKeys(user.email);
        }
        if(user.passwordPekama!=null) {
            JOIN_PASSWORD.waitUntil(visible, 20000).sendKeys(user.passwordPekama);
        }
        if(user.businessType !=null){
            selectItemInDropdown(
                    JOIN_SELECT_BUSINESS_TYPE,
                    JOIN_INPUT_BUSINESS_TYPE,
                    user.businessType);
        }
        if(user.role!=null){
            selectItemInDropdown(
                    JOIN_SELECT_ROLE,
                    JOIN_INPUT_ROLE,
                    user.role);
        }
        //TODO STUB!
        if(user.phone!=null) {
            JOIN_PHONE.waitUntil(visible, 20000).sendKeys(user.phone);
        }
        //TODO STUB!
        if(user.country!=null){
            selectItemInDropdown(
                    JOIN_SELECT_COUNTRY,
                    JOIN_INPUT_COUNTRY,
                    user.country);
        }
        selectAgreeTerms();
        submitEnabledButton(JOIN_SIGN_UP_SUBMIT);
        sleep(4000);

        if($(byText("Confirm your Account")).exists()){
            user.isSignUpSucceed = true;
            return true;}
        if($(byText("Teams on Your Domain")).exists()){
            user.isSignUpSucceed = true;
            return true;}
        user.isSignUpSucceed = false;
        return false;
    }

    public void selectAgreeTerms(){
        Assert.assertTrue(JOIN_AGREE_TERMS_VALUE.attr("class").contains("ng-empty"));
        JOIN_AGREE_TERMS.click();
        Assert.assertTrue(JOIN_AGREE_TERMS_VALUE.attr("class").contains("ng-not-empty"));
    }

    @Override
    public Boolean validateSubmitSignUp(Boolean submittedDataIsValid){
        openUrlIfActualNotEquals(JOIN_URL);
        if(submittedDataIsValid==true){
            checkText("You were sent an email message with the account activation link. Please check your inbox.");
            return true;
        }
        else {

            return false;
        }
    }

    @Override
    public void logout(){
        clickMeTab();
        clickSelector(ACCOUNT_LOGOUT);
        sleep(4000);
        HEADER_TAB_JOIN.should(visible);
        HEADER_TAB_ME.shouldNot(visible);
    }

}
