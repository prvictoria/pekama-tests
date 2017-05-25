package Steps;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.PekamaSignUp.*;
import static Page.TestsCredentials.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by Viachaslau_Balashevi on 3/29/2017.
 */
public class User extends Steps {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String email;
    public String password;
    public String name;
    public String surname;
    public String company;
    public String businessType;
    public String role;
    public String phone;
    public String country;
    public Boolean isSignUpSucceed;
    public Boolean isLoginSucceed;

    public void loginByURL(String email, String password, String url){
        this.email = email;
        this.password = password;
        openUrlWithBaseAuth(url);
        submitCookie(10);
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
    public boolean submitSignUp(String email, String name, String surname, String company, String password, String businessTypeValue, String yourRoleValue, String phone, String country){
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.company = company;
        this.password = password;
        this.businessType = businessTypeValue;
        this.role = yourRoleValue;
        this.phone = phone;
        this.country = country;

        if(email!=null) {
            signupEmail.waitUntil(visible, 20000).sendKeys(this.email);
        }
        if(name!=null) {
            signupName.waitUntil(visible, 20000).sendKeys(this.name);
        }
        if(surname!=null) {
            signupSurname.waitUntil(visible, 20000).sendKeys(this.surname );
        }
        if(company!=null) {
            signupCompany.waitUntil(visible, 20000).sendKeys(this.company);
        }
        if(password!=null) {
            signupPassword.waitUntil(visible, 20000).sendKeys(this.password);
        }
        if(businessTypeValue !=null){
            signupSelectBusinessType.selectOptionByValue(this.businessType);
        }
        if(yourRoleValue!=null){
            signupSelectYourRole.selectOptionByValue(this.role);
        }
        if(phone!=null) {
            signupPhone.waitUntil(visible, 20000).sendKeys(this.phone);
        }
        if(country!=null){
            signupSelectCountry.selectOptionByValue(this.country);
        }
        submitEnabledButton(signupNext);
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
    public String submitReset(String email){
        if(email==null) {
            email = "123@"+randomString(10);
        }
            RESET_PAGE_TITLE.waitUntil(visible, 20000).shouldHave(Condition.text(RESET_PAGE_TITLE_TEXT));
            RESET_PAGE_EMAIL.sendKeys(email);
            RESET_PAGE_RESET_BTN.shouldBe(visible).click();
            sleep(1000);
        rootLogger.info("Reset email password is: "+email);
        this.password = email;
        return email;
    }
    public String submitResetPassword(String newPassword){
        if(newPassword==null){
        newPassword = VALID_PASSWORD+randomString(10);
        }
        NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(newPassword);
        NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(newPassword);
        NEWPASSWORD_PAGE_RESTORE_BTN.shouldBe(visible).click();
        sleep(1000);
        rootLogger.info("New password "+newPassword);
        this.password = newPassword;
        return newPassword;
    }
    public String submitResetPassword(String newPassword, String confirmPassword){
        if(newPassword!=null){
            NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(newPassword);
            rootLogger.info("New password "+newPassword);
        }
        if(confirmPassword!=null){
            NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(confirmPassword);
            rootLogger.info("Confirm password "+confirmPassword);
        }
        NEWPASSWORD_PAGE_RESTORE_BTN.shouldBe(visible).click();
        sleep(1000);
        this.password = newPassword;
        return newPassword;
    }


}