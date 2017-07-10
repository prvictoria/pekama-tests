package Steps;

import Steps.Intrefaces.ILogin;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.PekamaLogin.*;
import static Page.PekamaResetPassword.*;
import static Page.PekamaSignUp.*;
import static Page.PekamaTeamSettings.*;
import static Page.PekamaTeamSettings.TAB_PROFILE_BTN_SAVE;
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
public class ObjectUser implements ILogin {
    static final Logger rootLogger = LogManager.getRootLogger();
    private String index;
    public String email;
    public String passwordPekama;
    public String passwordEmail;
    public String passwordBox;
    public String passwordXero;
    public String passwordLinkedIn;

    public String name;
    public String surname;
    public String nameSurname;
    public String initials;
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
    public String teamEmail;
    public String teamStreet;
    public String teamZip;
    public String teamCity;
    public String teamRegion;
    public String teamCountry;
    public Boolean isSignUpSucceed = false;
    public Boolean isLoggedIn = false;

    public ObjectUser(Builder builder) {
        email = builder.email;
        passwordPekama = builder.passwordPekama;
        passwordEmail = builder.passwordEmail;
        passwordBox = builder.passwordBox;
        passwordXero = builder.passwordXero;
        passwordLinkedIn = builder.passwordLinkedIn;
        name = builder.name;
        surname = builder.surname;
        nameSurname = builder.nameSurname;
        initials = builder.initials;
        company = builder.company;
        businessType = builder.businessType;
        role = builder.role;
        phone = builder.phone;
        fax = builder.fax;
        mobile = builder.mobile;
        legalEntity = builder.legalEntity;
        street = builder.street;
        zip = builder.zip;
        city = builder.city;
        region = builder.region;
        country = builder.country;
        teamName = builder.teamName;
        teamFullName = builder.teamFullName;
        teamCode = builder.teamCode;
        teamInitials = builder.teamInitials;
        teamEmail = builder.teamEmail;
        teamStreet = builder.teamStreet;
        teamZip = builder.teamZip;
        teamCity = builder.teamCity;
        teamRegion = builder.teamRegion;
        teamCountry = builder.teamCountry;
        isSignUpSucceed = builder.isSignUpSucceed;
        isLoggedIn = builder.isLoggedIn;
    }
    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String email;
        private String passwordPekama;
        private String passwordEmail;
        private String passwordBox;
        private String passwordXero;
        private String passwordLinkedIn;
        private String name;
        private String surname;
        private String nameSurname;
        private String initials;
        private String company;
        private String businessType;
        private String role;
        private String phone;
        private String fax;
        private String mobile;
        private String legalEntity;
        private String street;
        private String zip;
        private String city;
        private String region;
        private String country;
        private String teamName;
        private String teamFullName;
        private String teamCode;
        private String teamInitials;
        private String teamEmail;
        private String teamStreet;
        private String teamZip;
        private String teamCity;
        private String teamRegion;
        private String teamCountry;
        private Boolean isSignUpSucceed;
        private Boolean isLoggedIn;
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

        public Builder passwordEmail(String passwordEmail) {
            this.passwordEmail = passwordEmail;
            return this;
        }

        public Builder passwordBox(String passwordBox) {
            this.passwordBox = passwordBox;
            return this;
        }

        public Builder passwordXero(String passwordXero) {
            this.passwordXero = passwordXero;
            return this;
        }

        public Builder passwordLinkedIn(String passwordLinkedIn) {
            this.passwordLinkedIn = passwordLinkedIn;
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

        public Builder nameSurname(String nameSurname) {
            this.nameSurname = nameSurname;
            return this;
        }

        public Builder initials(String initials) {
            this.initials = initials;
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

        public Builder fax(String fax) {
            this.fax = fax;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder legalEntity(String legalEntity) {
            this.legalEntity = legalEntity;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder teamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public Builder teamFullName(String teamFullName) {
            this.teamFullName = teamFullName;
            return this;
        }

        public Builder teamCode(String teamCode) {
            this.teamCode = teamCode;
            return this;
        }

        public Builder teamInitials(String teamInitials) {
            this.teamInitials = teamInitials;
            return this;
        }

        public Builder teamEmail(String teamEmail) {
            this.teamEmail = teamEmail;
            return this;
        }

        /**
         * Sets the {@code teamStreet} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param teamStreet the {@code teamStreet} to set
         * @return a reference to this Builder
         */
        public Builder teamStreet(String teamStreet) {
            this.teamStreet = teamStreet;
            return this;
        }

        /**
         * Sets the {@code teamZip} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param teamZip the {@code teamZip} to set
         * @return a reference to this Builder
         */
        public Builder teamZip(String teamZip) {
            this.teamZip = teamZip;
            return this;
        }

        /**
         * Sets the {@code teamCity} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param teamCity the {@code teamCity} to set
         * @return a reference to this Builder
         */
        public Builder teamCity(String teamCity) {
            this.teamCity = teamCity;
            return this;
        }

        /**
         * Sets the {@code teamRegion} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param teamRegion the {@code teamRegion} to set
         * @return a reference to this Builder
         */
        public Builder teamRegion(String teamRegion) {
            this.teamRegion = teamRegion;
            return this;
        }

        /**
         * Sets the {@code teamCountry} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param teamCountry the {@code teamCountry} to set
         * @return a reference to this Builder
         */
        public Builder teamCountry(String teamCountry) {
            this.teamCountry = teamCountry;
            return this;
        }

        public Builder isSignUpSucceed(Boolean isSignUpSucceed) {
            this.isSignUpSucceed = isSignUpSucceed;
            return this;
        }

        public Builder isLoggedIn(Boolean isLoggedIn) {
            this.isLoggedIn = isLoggedIn;
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

    @Override
    public String login() {
        openUrlWithBaseAuth(URL_PEKAMA_LOGIN);
        submitCookie(10);
        hideZopim();
        submitLoginCredentials(this.email, this.passwordPekama);
        checkActualUrl(this, URL_PEKAMA_DASHBOARD);
        return getActualUrl();
    }

    @Override
    public String login(String url) {
        openUrlWithBaseAuth(url);
        submitCookie(10);
        hideZopim();
        submitLoginCredentials(this.email, this.passwordPekama);
        checkActualUrl(this, url);
        return getActualUrl();
    }

    @Override
    public String login(ObjectUser user){
        this.email = user.email;
        this.passwordPekama = user.passwordPekama;
        openUrlWithBaseAuth(URL_PEKAMA_LOGIN);
        submitCookie(10);
        hideZopim();
        submitLoginCredentials(this.email, this.passwordPekama);
        checkActualUrl(user, URL_PEKAMA_DASHBOARD);
        return getActualUrl();
    }
    @Override
    public String login(ObjectUser user, String url){
        this.email = user.email;
        this.passwordPekama = user.passwordPekama;
        openUrlWithBaseAuth(url);
        submitCookie(10);
        hideZopim();
        submitLoginCredentials(this.email, this.passwordPekama);
        checkActualUrl(user, url);
        return getActualUrl();
    }


    public void login(String email, String password, String url){
        this.email = email;
        this.passwordPekama = password;
        openUrlWithBaseAuth(url);
        submitCookie(10);
        hideZopim();
        submitLoginCredentials(email, password);
        if(getActualUrl().equals(URL_PEKAMA_DASHBOARD)){
            this.isLoggedIn = true;
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
        sleep(2000);
        if(getActualUrl().equals(URL_RESET_PASSWORD_COMPLETE)) {
            this.passwordPekama = newPassword;
            return newPassword;
        }
        else return null;
    }
    public void submitTeamDetailsForm(){
        TAB_PROFILE_BTN_SAVE.waitUntil(visible, 20000).shouldBe(disabled);
        $(byText("Title:")).shouldBe(visible);
        fillField(TAB_PROFILE_TITLE, this.teamName);
        $(byText("Code:")).shouldBe(visible);
        fillField(TAB_PROFILE_CODE, this.teamCode);
        $(byText("Business type:")).shouldBe(visible);
        $(byText("Your role:")).shouldBe(visible);
        $(byText("Email:")).shouldBe(visible);
        fillField(TAB_PROFILE_EMAIL, this.teamEmail);
        $(byText("@organizations.pekama.com")).shouldBe(visible);
        TAB_PROFILE_BTN_SAVE.shouldBe(enabled).click();
    }
    public Boolean validateTeamDetailsForm(){
        $(byText("Title:")).shouldBe(visible);
        TAB_PROFILE_TITLE.shouldHave(value(this.teamName));
        $(byText("Code:")).shouldBe(visible);
        TAB_PROFILE_CODE.shouldHave(value(this.teamCode));
        $(byText("Business type:")).shouldBe(visible);
        $(byText("Your role:")).shouldBe(visible);
        $(byText("Email:")).shouldBe(visible);
        TAB_PROFILE_EMAIL.shouldHave(value(this.teamEmail));
        $(byText("@organizations.pekama.com")).shouldBe(visible);
        return true;
    }
    private static Boolean checkActualUrl(ObjectUser user, String url){
        if(getActualUrl().equals(url)){
            user.isLoggedIn = true;
            return true;
        }
        else
            user.isLoggedIn = false;
            return false;
    };
    public enum Users {OWNER, TEAM_MEMBER, ADMIN, COLLABORATOR, VIEWER, REQUESTER, EXPERT, PRETENDER, USER_01, USER_02, USER_03, USER_04, USER_05, USER_06, USER_07, USER_08, USER_09, USER_10};
    public ObjectUser buildUser(Users id) {
        ObjectUser user = null;
        switch (id) {
            case OWNER:
                user = new ObjectUser(newBuilder())
                        .newBuilder()
                        .email(User8.GMAIL_EMAIL.getValue())
                        .passwordPekama(User8.PEKAMA_PASSWORD.getValue())
                        .passwordEmail(User8.GMAIL_PASSWORD.getValue())
                        .passwordBox(User8.BOX_PASSWORD.getValue())
                        .passwordLinkedIn(User8.LINKEDIN_PASSWORD.getValue())
                        .passwordXero(User8.XERO_PASSWORD.getValue())
                        .name(User8.NAME.getValue())
                        .surname(User8.SURNAME.getValue())
                        .nameSurname(User8.NAME_SURNAME.getValue())
                        .initials(User8.INITIALS.getValue())
                        .company(User8.TEAM_NAME.getValue())
                        .businessType(null)
                        .role(null)
                        .phone(null)
                        .country(null)
                        .teamName(User8.TEAM_NAME.getValue())
                        .teamFullName(User8.FULL_TEAM_NAME.getValue())
                        .teamCode(User8.TEAM_CODE.getValue())
                        .teamInitials(User8.TEAM_INITIALS.getValue())
                        .teamEmail("team01email")
                        .build();
                logUserFields(user);
                break;
            case TEAM_MEMBER:
                break;
            case ADMIN:
                break;
            case COLLABORATOR:
                break;
            case VIEWER:
                break;
            case REQUESTER:
                break;
            case EXPERT:
                break;
            case PRETENDER:
                break;
            case USER_01:
                user = new ObjectUser(newBuilder())
                        .newBuilder()
                        .email(User1.GMAIL_EMAIL.getValue())
                        .passwordPekama(User1.PEKAMA_PASSWORD.getValue())
                        .passwordEmail(User1.GMAIL_PASSWORD.getValue())
                        .passwordBox(User1.BOX_PASSWORD.getValue())
                        .passwordLinkedIn(User1.LINKEDIN_PASSWORD.getValue())
                        .passwordXero(User1.XERO_PASSWORD.getValue())
                        .name(User1.NAME.getValue())
                        .surname(User1.SURNAME.getValue())
                        .nameSurname(User1.NAME_SURNAME.getValue())
                        .initials(User1.INITIALS.getValue())
                        .company(User1.TEAM_NAME.getValue())
                        .businessType(null)
                        .role(null)
                        .phone(null)
                        .country(null)
                        .teamName(User1.TEAM_NAME.getValue())
                        .teamFullName(User1.FULL_TEAM_NAME.getValue())
                        .teamCode(User1.TEAM_CODE.getValue())
                        .teamInitials(User1.TEAM_INITIALS.getValue())
                        .teamEmail("team01email")
                        .build();
                logUserFields(user);
                break;
            case USER_02:
                user = new ObjectUser(newBuilder())
                        .newBuilder()
                        .email(User2.GMAIL_EMAIL.getValue())
                        .passwordPekama(User2.PEKAMA_PASSWORD.getValue())
                        .passwordEmail(User2.GMAIL_PASSWORD.getValue())
                        .passwordBox(User2.BOX_PASSWORD.getValue())
                        .passwordLinkedIn(User2.LINKEDIN_PASSWORD.getValue())
                        .passwordXero(User2.XERO_PASSWORD.getValue())
                        .name(User2.NAME.getValue())
                        .surname(User2.SURNAME.getValue())
                        .nameSurname(User2.NAME_SURNAME.getValue())
                        .company(User2.TEAM_NAME.getValue())
                        .businessType(null)
                        .role(null)
                        .phone(null)
                        .country(null)
                        .teamName(User2.TEAM_NAME.getValue())
                        .teamFullName(User2.FULL_TEAM_NAME.getValue())
                        .teamCode(User2.TEAM_CODE.getValue())
                        .teamInitials(User2.TEAM_INITIALS.getValue())
                        .teamEmail("team02email")
                        .build();
                logUserFields(user);
                break;
            case USER_03:
                user = new ObjectUser(newBuilder())
                        .newBuilder()
                        .email(User3.GMAIL_EMAIL.getValue())
                        .passwordPekama(User3.PEKAMA_PASSWORD.getValue())
                        .passwordEmail(User3.GMAIL_PASSWORD.getValue())
                        .passwordBox(User3.BOX_PASSWORD.getValue())
                        .passwordLinkedIn(User3.LINKEDIN_PASSWORD.getValue())
                        .passwordXero(User3.XERO_PASSWORD.getValue())
                        .name(User3.NAME.getValue())
                        .surname(User3.SURNAME.getValue())
                        .nameSurname(User3.NAME_SURNAME.getValue())
                        .company(User3.TEAM_NAME.getValue())
                        .businessType(null)
                        .role(null)
                        .phone(null)
                        .country(null)
                        .teamName(User3.TEAM_NAME.getValue())
                        .teamFullName(User3.FULL_TEAM_NAME.getValue())
                        .teamCode(User3.TEAM_CODE.getValue())
                        .teamInitials(User3.TEAM_INITIALS.getValue())
                        .teamEmail("team03email")
                        .build();
                logUserFields(user);
                break;
            case USER_04:
                user = new ObjectUser(newBuilder())
                        .newBuilder()
                        .email(User4.GMAIL_EMAIL.getValue())
                        .passwordPekama(User4.PEKAMA_PASSWORD.getValue())
                        .passwordEmail(User4.GMAIL_PASSWORD.getValue())
                        .passwordBox(User4.BOX_PASSWORD.getValue())
                        .passwordLinkedIn(User4.LINKEDIN_PASSWORD.getValue())
                        .passwordXero(User4.XERO_PASSWORD.getValue())
                        .name(User4.NAME.getValue())
                        .surname(User4.SURNAME.getValue())
                        .nameSurname(User4.NAME_SURNAME.getValue())
                        .company(User4.TEAM_NAME.getValue())
                        .businessType(null)
                        .role(null)
                        .phone(null)
                        .country(null)
                        .teamName(User4.TEAM_NAME.getValue())
                        .teamFullName(User4.FULL_TEAM_NAME.getValue())
                        .teamCode(User4.TEAM_CODE.getValue())
                        .teamInitials(User4.TEAM_INITIALS.getValue())
                        .teamEmail("team03email")
                        .build();
                logUserFields(user);
                break;
            case USER_05:
                user = new ObjectUser(newBuilder())
                        .newBuilder()
                        .email(User5.GMAIL_EMAIL.getValue())
                        .passwordPekama(User5.PEKAMA_PASSWORD.getValue())
                        .passwordEmail(User5.GMAIL_PASSWORD.getValue())
                        .passwordBox(User5.BOX_PASSWORD.getValue())
                        .passwordLinkedIn(User5.LINKEDIN_PASSWORD.getValue())
                        .passwordXero(User5.XERO_PASSWORD.getValue())
                        .name(User5.NAME.getValue())
                        .surname(User5.SURNAME.getValue())
                        .nameSurname(User5.NAME_SURNAME.getValue())
                        .company(User5.TEAM_NAME.getValue())
                        .businessType(User5.BUSINESS_TYPE.getValue())
                        .role(User5.BUSINESS_ROLE.getValue())
                        .phone(User5.PHONE.getValue())
                        .country(User5.COUNTRY.getValue())
                        .teamName(User5.TEAM_NAME.getValue())
                        .teamFullName(User5.FULL_TEAM_NAME.getValue())
                        .teamCode(User5.TEAM_CODE.getValue())
                        .teamInitials(User5.TEAM_INITIALS.getValue())
                        .teamEmail("team05email")
                        .build();
                logUserFields(user);
                break;
            case USER_06:
                user = new ObjectUser(newBuilder())
                        .newBuilder()
                        .email(User6.GMAIL_EMAIL.getValue())
                        .passwordPekama(User6.PEKAMA_PASSWORD.getValue())
                        .passwordEmail(User6.GMAIL_PASSWORD.getValue())
                        .passwordBox(User6.BOX_PASSWORD.getValue())
                        .passwordLinkedIn(User6.LINKEDIN_PASSWORD.getValue())
                        .passwordXero(User6.XERO_PASSWORD.getValue())
                        .name(User6.NAME.getValue())
                        .surname(User6.SURNAME.getValue())
                        .nameSurname(User6.NAME_SURNAME.getValue())
                        .company(User6.TEAM_NAME.getValue())
                        .businessType(null)
                        .role(null)
                        .phone(User6.PHONE.getValue())
                        .fax(User6.FAX.getValue())
                        .mobile(User6.MOBILE.getValue())
                        .country(User6.COUNTRY.getValue())
                        .street(User6.STREET.getValue())
                        .zip(User6.ZIP.getValue())
                        .city(User6.CITY.getValue())
                        .region(User6.REGION.getValue())
                        .teamName(User6.TEAM_NAME.getValue())
                        .teamFullName(User6.FULL_TEAM_NAME.getValue())
                        .teamCode(User6.TEAM_CODE.getValue())
                        .teamInitials(User6.TEAM_INITIALS.getValue())
                        .teamEmail("team06email")
                        .build();
                logUserFields(user);
                break;
            case USER_07:
                
                break;
            case USER_08:
                user = new ObjectUser(newBuilder())
                        .newBuilder()
                        .email(User8.GMAIL_EMAIL.getValue())
                        .passwordPekama(User8.PEKAMA_PASSWORD.getValue())
                        .passwordEmail(User8.GMAIL_PASSWORD.getValue())
                        .passwordBox(User8.BOX_PASSWORD.getValue())
                        .passwordLinkedIn(User8.LINKEDIN_PASSWORD.getValue())
                        .passwordXero(User8.XERO_PASSWORD.getValue())
                        .name(User8.NAME.getValue())
                        .surname(User8.SURNAME.getValue())
                        .nameSurname(User8.NAME_SURNAME.getValue())
                        .company(User8.TEAM_NAME.getValue())
                        .businessType(null)
                        .role(null)
                        .phone(null)
                        .country(null)
                        .teamName(User8.TEAM_NAME.getValue())
                        .teamFullName(User8.FULL_TEAM_NAME.getValue())
                        .teamCode(User8.TEAM_CODE.getValue())
                        .teamInitials(User8.TEAM_INITIALS.getValue())
                        .teamEmail("team08email")
                        .build();
                logUserFields(user);
                break;
            case USER_09:
                break;
            case USER_10:
                break;
        }
        return user;
    };
    public void logUserFields(ObjectUser user){
        rootLogger.info("USER fields:");
        rootLogger.info("email: "+user.email);
        rootLogger.info("passwordPekama: "+user.passwordPekama);
        rootLogger.info("passwordEmail: "+user.passwordEmail);
        rootLogger.info("passwordBox: "+user.passwordBox);
        rootLogger.info("passwordLinkedIn: "+user.passwordLinkedIn);
        rootLogger.info("passwordXero: "+user.passwordXero);

        rootLogger.info("name: "+user.name);
        rootLogger.info("surname: "+user.surname);
        rootLogger.info("company: "+user.company);
        rootLogger.info("businessType: "+user.businessType);
        rootLogger.info("role: "+user.role);
        rootLogger.info("phone: "+user.phone);
        rootLogger.info("country: "+user.country);

        rootLogger.info("teamName: "+user.teamName);
        rootLogger.info("teamFullName: "+user.teamFullName);
        rootLogger.info("teamCode: "+user.teamCode);
        rootLogger.info("teamInitials: "+user.teamInitials);
        rootLogger.info("======================");
    }
}