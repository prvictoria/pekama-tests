//package Steps;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Test;
//
//import java.util.Enumeration;
//
///**
// * Created by VatslauX on 25-May-17.
// */
//public class BuildUser {
//    static final Logger rootLogger = LogManager.getRootLogger();
//    public String email;
//    public String passwordPekama;
//    public String name;
//    public String surname;
//    public String company;
//    public String businessType;
//    public String role;
//    public String phone;
//    public String country;
//    public Boolean isSignUpSucceed;
//    public Boolean isLoginSucceed;
//
//    private BuildUser(  String email,
//             String passwordPekama,
//             String name,
//             String surname,
//             String company,
//             String businessType,
//             String role,
//             String phone,
//             String country,
//             Boolean isSignUpSucceed,
//             Boolean isLoginSucceed) {
//        this.email = email;
//        this.passwordPekama = passwordPekama;
//        this.name = name;
//        this.surname = surname;
//        this.company = company;
//        this.businessType = businessType;
//        this.role = role;
//        this.phone = phone;
//        this.country = country;
//        this.isSignUpSucceed = isSignUpSucceed;
//        this.isLoginSucceed = isLoginSucceed;
//    }
//
//    public static Builder newBuilder() {
//        return new Builder();
//    }
//
//    public static final class Builder {
//        private String email;
//        private String passwordPekama;
//        private String name;
//        private String surname;
//        private String company;
//        private String businessType;
//        private String role;
//        private String phone;
//        private String country;
//        private Boolean isSignUpSucceed;
//        private Boolean isLoginSucceed;
//
//        public Builder email(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public Builder passwordPekama(String passwordPekama) {
//            this.passwordPekama = passwordPekama;
//            return this;
//        }
//
//        public Builder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public Builder surname(String surname) {
//            this.surname = surname;
//            return this;
//        }
//
//        public Builder company(String company) {
//            this.company = company;
//            return this;
//        }
//
//        public Builder businessType(String businessType) {
//            this.businessType = businessType;
//            return this;
//        }
//
//        public Builder role(String role) {
//            this.role = role;
//            return this;
//        }
//
//        public Builder phone(String phone) {
//            this.phone = phone;
//            return this;
//        }
//
//        public Builder country(String country) {
//            this.country = country;
//            return this;
//        }
//
//        public Builder isSignUpSucceed(Boolean isSignUpSucceed) {
//            this.isSignUpSucceed = isSignUpSucceed;
//            return this;
//        }
//
//        public Builder isLoginSucceed(Boolean isLoginSucceed) {
//            this.isLoginSucceed = isLoginSucceed;
//            return this;
//        }
//
//        public BuildUser build() {
//            return new BuildUser(
//                    email,
//                    passwordPekama,
//                    name,
//                    surname,
//                    company,
//                    businessType,
//                    role,
//                    phone,
//                    country,
//                    isSignUpSucceed,
//                    isLoginSucceed);
//        }
//    }
//
//}
