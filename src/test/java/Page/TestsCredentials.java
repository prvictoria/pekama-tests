package Page;
import org.junit.Test;

import static Page.TestsCredentials.User1.GMAIL_EMAIL;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class TestsCredentials {
    //need valid accounts on server
    public enum User1 {
        GMAIL_EMAIL("testqweeco001@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("asui67we34@Q"),
        BOX_PASSWORD("32qew8127a12a"),
        NAME("Test001"),
        SURNAME("Quality01"),
        NAME_SURNAME("Test001 Quality01"),
        TEAM_NAME("QweecoTeam01"),
        TEAM_CODE("QT01"),
        FULL_TEAM_NAME("QweecoTeam01 (QT01)"),
        COMMUNITY_CASE_TYPE("Patent"),
        COMMUNITY_CASE_COUNTRY("Pitcairn Islands");
        private String value;
        User1(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //Requester
    public enum User2 {
        GMAIL_EMAIL("testqweeco002@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        BOX_PASSWORD("32qew8127a12a"),
        NAME("Test002"),
        SURNAME("Quality02"),
        NAME_SURNAME("Test002 Quality02"),
        TEAM_NAME("QweecoTeam02"),
        TEAM_CODE("QT02"),
        FULL_TEAM_NAME("QweecoTeam02 (QT02)"),
        COMMUNITY_CASE_TYPE("Patent"),
        COMMUNITY_CASE_COUNTRY("Pitcairn Islands");
        private String value;
        User2(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //Expert
    public enum User3 {
        GMAIL_EMAIL("testqweeco003@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        BOX_PASSWORD("32qew8127a12a"),
        XERO_PASSWORD("asui67we34"),
        NAME("Test003"),
        SURNAME("Quality03"),
        NAME_SURNAME("Test003 Quality03"),
        PHONE("205-780-5656"),
        FAX("(205) 254-2294"),
        MOBILE("205- 324-8712"),
        LEGAL_ENTITY("Arlington House"),
        STREET("331 Cotton Avenue, Southwest"),
        ZIP("35211"),
        CITY("Birmingham"),
        REGION("Alabama"),
        COUNTRY("United States"),
        TEAM_NAME("QweecoTeam03"),
        TEAM_CODE("QT03"),
        FULL_TEAM_NAME("QweecoTeam03 (QT03)"),
        COMMUNITY_CASE_TYPE("Patent"),
        COMMUNITY_CASE_COUNTRY("Pitcairn Islands");
        private String value;
        User3(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //Requester
    public enum User4 {
        GMAIL_EMAIL("testqweeco004@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        BOX_PASSWORD("32qew8127a12a"),
        NAME("Test004"),
        SURNAME("Quality04"),
        NAME_SURNAME("Test004 Quality04"),
        PHONE("205-780-5656"),
        FAX("(205) 254-2294"),
        MOBILE("205- 324-8712"),
        LEGAL_ENTITY("Arlington House"),
        STREET("331 Cotton Avenue, Southwest"),
        ZIP("35211"),
        CITY("Birmingham"),
        REGION("Alabama"),
        COUNTRY("United States"),
        TEAM_NAME("QweecoTeam04"),
        TEAM_CODE("QT04"),
        FULL_TEAM_NAME("QweecoTeam04 (QT04)"),
        COMMUNITY_CASE_TYPE("Patent"),
        COMMUNITY_CASE_COUNTRY("Pitcairn Islands");

        private String value;
        User4(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //Expert
    public enum User5 {
        GMAIL_EMAIL("testqweeco005@gmail.com"),
        GMAIL_PASSWORD("123456789qasw11"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        BOX_PASSWORD("32qew8127a12a"),
        NAME("Test005"),
        SURNAME("Quality05"),
        NAME_SURNAME("Test005 Quality05"),
        PHONE("205-780-5656"),
        FAX("(205) 254-2294"),
        MOBILE("205- 324-8712"),
        LEGAL_ENTITY("Arlington House"),
        STREET("331 Cotton Avenue, Southwest"),
        ZIP("35211"),
        CITY("Birmingham"),
        REGION("Alabama"),
        COUNTRY("United States"),
        TEAM_NAME("QweecoTeam05"),
        TEAM_CODE("QT05"),
        FULL_TEAM_NAME("QweecoTeam05 (QT05)"),
        COMMUNITY_CASE_TYPE("Patent"),
        COMMUNITY_CASE_COUNTRY("Pitcairn Islands");

        private String value;
        User5(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //Edit profile flow
    public enum User6 {
        GMAIL_EMAIL("testqweeco006@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        BOX_PASSWORD("32qew8127a12a"),
        NAME("Test006"),
        SURNAME("Quality06"),
        NAME_SURNAME("Test006 Quality06"),
        PHONE("205-780-5656"),
        FAX("(205) 254-2294"),
        MOBILE("205- 324-8712"),
        LEGAL_ENTITY("Arlington House"),
        STREET("331 Cotton Avenue, Southwest"),
        ZIP("35211"),
        CITY("Birmingham"),
        REGION("Alabama"),
        COUNTRY("United States"),
        TEAM_NAME("QweecoTeam06"),
        TEAM_CODE("QT06"),
        FULL_TEAM_NAME("QweecoTeam06 (QT06)"),
        COMMUNITY_CASE_TYPE("Patent"),
        COMMUNITY_CASE_COUNTRY("Pitcairn Islands");

        private String value;
        User6(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //Reset password flow, login with Auth2 flow
    //No need accounts on server
    public enum User7 {
        GMAIL_EMAIL("");

        private String value;
        User7(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //User flow
    public enum User8 {
    GMAIL_EMAIL("");

    private String value;
        User8(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //Invite flow
    public enum User9 {
        GMAIL_EMAIL("");

        private String value;
        User9(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    } //Guest flow

    public enum Countries {
        AFGHANISTAN("Afghanistan"),
        ALL_COUNTRIES("All countries"),
        AMERICAN_SAMOA("American Samoa"),
        GREENLAND("Greenland"),
        PITCAIRN_ISLANDS("Pitcairn Islands"),
        NETHERLANDS_ANTILES("Netherlands Antilles"),
        PATENT_COOPERATION_TREATY("Patent Cooperation Treaty"),
        CRM_CONFERENCE("Conference Lead"),
        CRM_PROSPECT("Prospect"),
        TRADEMARK_MADRID_PROTOCOL("International Application (Madrid Protocol)"),
        TRADEMARK_CMT("European Union (CTM)"),
        TRADEMARK_CONSUMER_TRADEMARK("Consumer Trademark Defining"),
        USA("United States"),
        ALL("All countries"),
        GENERIC("");

        private String value;
        Countries(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    public enum CaseType {
        PATENT("Patent"),
        CRM("Client Relation (CRM)"),
        TRADEMARK("Trademark");
        private String value;
        CaseType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    public enum ProductionCaseType {
        PATENT("IP: Patent"),
        CRM("IP: Design"),
        TRADEMARK("IP: Trademark");
        private String value;
        ProductionCaseType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    public enum ProjectType {
        PATENT_COOPERATION_TREATY("Patent Cooperation Treaty"),
        CRM_CONFERENCE("Conference Lead"),
        TRADEMARK_CONSUMER_TRADEMARK("Consumer Trademark Defining");
        private String value;
        ProjectType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    public enum ContactRelation {
        ATTORNEY("Attorney of Record"),
        INVESTOR("Investor"),
        CLIENT_COMPANY("Client (Company)"),
        OWNER_PERSON("Owner (Person)"),
        REGIXTERED_OWNER("Registered Owner"),
        CONTACT_PERSON("Contact Person"),
        OPPONENT_REPRESENTATIVE("opponent representative"),
        OWNER_COMPANY("Owner (Company)"),
        DOMESTIC_REPRESENTATIVE("Domestic Representative");
        private String value;
        ContactRelation(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    public enum TrademarkTypes {
        CONVENTION("Paris Convention"),
        NATIONAL_RIGHT("National right of Madrid"),
        LEGAL("Legal - Infringement"),
        BASIC("Basic Filing");

        private String eventName;
        TrademarkTypes(String value) {
            this.eventName = value;
        }
        public String getValue() {
            return eventName;
        }
    }
    public enum PatentTypes {
        CONVENTION("Paris Convention"),
        VALIDATION("Validation of EP Divisional"),
        CONTINUATION("Continuation in Part"),
        BASIC("Basic Filing");

        private String eventName;
        PatentTypes(String value) {
            this.eventName = value;
        }
        public String getValue() {
            return eventName;
        }
    }
    public enum TrademarkEvents {
        CASE_SUSPENDED("Case still suspended"),
        PRIORITY_APPLICATION_FILED("Priority Application FIled"),
        OPPOSITION_END_DATE("Opposition end date"),
        APPLICATION_REGISTERED("Application Registered"),
        MARK_CREATED("Mark Created");

        private String eventName;
        TrademarkEvents(String value) {
            this.eventName = value;
        }
        public String getValue() {
            return eventName;
        }
    }
    public enum PatentEvents {
        APPLICATION("Application Entered National Stage"),
        CHANGE("Change - lapse in a contracting state"),
        DELETION("Deletion: Date of oral proceedings"),
        NEW_ENTRY("New entry: Date of receipt of notice of appeal"),
        WITHDRAW("Withdrawal of application");

        private String eventName;
        PatentEvents(String value) {
            this.eventName = value;
        }
        public String getValue() {
            return eventName;
        }
    }
    /* new enum - USER1 (
    "EMAIL",
    "EMAIL_PASSWORD",
    "PEKAMA_PASSWORD",
    "LINKEDIN_PASSWORD",
    "BOX_PASSWORD"
    ETC) */

    @Test
    public  void enumTest() {
        System.out.println(TrademarkEvents.APPLICATION_REGISTERED.eventName);
        System.out.println(TrademarkEvents.APPLICATION_REGISTERED.toString());
        System.out.println(TrademarkEvents.APPLICATION_REGISTERED.getValue());
//        System.out.println(User1.GMAIL_PASSWORD.value);
//        System.out.println(User1.TEAM_CODE.value);
//        System.out.println(User1.FULL_TEAM_NAME.value);

    }

    public static final String GENERIC_PEKAMA_PASSWORD = "asui67we34";
    public static final String GENERIC_GMAIL_PASSWORD = "123456789qasw1";
    public static final String GENERIC_GMAIL_LOGIN = "123456789@gmail.com";
    public static final String GMAIL_PASSWORD = "123456789qasw1";

    public static final String teamTest01 = "Qweeco01 (QT01)";
    public static final String teamTest02 = "Qweeco02 (QT02)";
    public static final String teamTest03 = "Qweeco03 (QT03)";
    public static final String linkedinLogin_user04 = "vatslav.realt@gmail.com";
    public static final String linkedinPassword_user04 = "123456789qasw";

    public static final String HTTP_AUTH_EMAIL = "qweeco";
    public static final String HTTP_AUTH_PASSWORD = "qw33coStudi0";


    public static final String VALID_PASSWORD = "123456789qaswQ!";
    public static final String VALID_EMAIL = "12345@mail.com";
    public static final String VALID_NAME = "NAME";
    public static final String VALID_SURNAME = "SURNAME";
    public static final String VALID_COMPANY = "COMPANY";

     public static final String ROW1 = "[1]";
     public static final String ROW2 = "[2]";
     public static final String ROW3 = "[3]";
     public static final String ROW4 = "[4]";
     public static final String ROW5 = "[5]";
     public static final String ROW6 = "[6]";
     public static final String ROW7 = "[7]";
     public static final String ROW8 = "[8]";
     public static final String ROW9 = "[9]";
     public static final String ROW10 = "[10]";

}
