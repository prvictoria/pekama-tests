package Steps;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.PekamaSignUp.*;
import static Page.TestsCredentials.*;
import static Page.UrlStrings.*;
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
public class ObjectUser {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String email;
    public String passwordPekama;
    public String passwordEmail;
    public String passwordBox;
    public String passwordXero;
    public String passwordLinkedIn;

    public String name;
    public String surname;
    public String nameSurname;
    public String company;
    public String businessType;
    public String role;
    public String phone;
    public String fax;
    public String mobile;
    public String legalEntity;
    public String street;
    public String zip;
    public String city;
    public String region;
    public String country;

    public String teamName;
    public String teamFullName;
    public String teamCode;
    public String teamInitials;

    public Boolean isSignUpSucceed = false;
    public Boolean isLoginSucceed = false;

    private ObjectUser(Builder builder) {
        email = builder.email;
        passwordPekama = builder.passwordPekama;
        name = builder.name;
        surname = builder.surname;
        company = builder.company;
        businessType = builder.businessType;
        role = builder.role;
        phone = builder.phone;
        country = builder.country;
        isSignUpSucceed = builder.isSignUpSucceed;
        isLoginSucceed = builder.isLoginSucceed;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder {
        private String email;
        private String passwordPekama;
        private String name;
        private String surname;
        private String company;
        private String businessType;
        private String role;
        private String phone;
        private String country;
        private Boolean isSignUpSucceed;
        private Boolean isLoginSucceed;

        private Builder() {
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder passwordPekama(String password) {
            this.passwordPekama = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder businessType(String businessType) {
            this.businessType = businessType;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder isSignUpSucceed(Boolean isSignUpSucceed) {
            this.isSignUpSucceed = isSignUpSucceed;
            return this;
        }

        public Builder isLoginSucceed(Boolean isLoginSucceed) {
            this.isLoginSucceed = isLoginSucceed;
            return this;
        }

        public ObjectUser build() {
            return new ObjectUser(this);
        }
    }





    public void loginByURL(String email, String password, String url){
        this.email = email;
        this.passwordPekama = password;
        openUrlWithBaseAuth(url);
        submitCookie(10);
        hideZopim();
        submitLoginCredentials(email, password);
        if(getActualUrl().equals(URL_PEKAMA_DASHBOARD)){
            this.isLoginSucceed = true;
        }
    }

    public void submitLoginCredentials(String email, String password){
        this.email = email;
        this.passwordPekama = password;

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
        this.passwordPekama = password;
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
            signupPassword.waitUntil(visible, 20000).sendKeys(this.passwordPekama);
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
        rootLogger.info("Reset email passwordPekama is: "+email);
        this.passwordPekama = email;
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
        rootLogger.info("New passwordPekama "+newPassword);
        this.passwordPekama = newPassword;
        return newPassword;
    }
    public String submitResetPassword(String newPassword, String confirmPassword){
        if(newPassword!=null){
            NEWPASSWORD_PAGE_NEW_PASSWORD.waitUntil(visible, 10000).sendKeys(newPassword);
            rootLogger.info("New passwordPekama "+newPassword);
        }
        if(confirmPassword!=null){
            NEWPASSWORD_PAGE_CONFIRM_PASSWORD.shouldBe(Condition.visible).sendKeys(confirmPassword);
            rootLogger.info("Confirm passwordPekama "+confirmPassword);
        }
        NEWPASSWORD_PAGE_RESTORE_BTN.shouldBe(visible).click();
        sleep(1000);
        this.passwordPekama = newPassword;
        return newPassword;
    }



}