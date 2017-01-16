package Page;
import org.junit.Test;

public class TestsCredentials {
    public enum User1 {
        GMAIL_EMAIL("testqweeco001@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        NAME("test001"),
        SURNAME("qweeco001"),
        TEAM_NAME("QweecoTeam01"),
        TEAM_CODE("QT01"),
        FULL_TEAM_NAME("Qweeco01 (QT01)");
        private String value;
        User1(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    public enum User2 {
        GMAIL_EMAIL("testqweeco002@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        NAME("test002"),
        SURNAME("qweeco002"),
        TEAM_NAME("QweecoTeam02"),
        TEAM_CODE("QT02"),
        FULL_TEAM_NAME("Qweeco02 (QT02)");
        private String value;
        User2(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    public enum User3 {
        GMAIL_EMAIL("testqweeco003@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        NAME("Test003"),
        SURNAME("Quality03"),
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
        FULL_TEAM_NAME("Qweeco03 (QT03)");
        private String value;
        User3(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    public enum User4 {
        GMAIL_EMAIL("testqweeco004@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD("asui67we34"),
        LINKEDIN_PASSWORD("123456789qasw"),
        NAME("Test004"),
        SURNAME("Quality04"),
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
        FULL_TEAM_NAME("Qweeco04 (QT04)");

        private String value;
        User4(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }//for password restoration tests
    public enum User5 {
        GMAIL_EMAIL("testqweeco005@gmail.com"),
        GMAIL_PASSWORD("123456789qasw1"),
        PEKAMA_PASSWORD(""),
        LINKEDIN_PASSWORD(""),
        NAME(""),
        SURNAME(""),
        TEAM_NAME(""),
        TEAM_CODE(""),
        FULL_TEAM_NAME("");
        private String value;
        User5(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }//Unregistered for SignUp links tests
    @Test
    public  void enumTest() {
        System.out.println(User1.GMAIL_EMAIL.value);
        System.out.println(User1.FULL_TEAM_NAME.value);
        System.out.println(User1.GMAIL_PASSWORD.value);
        System.out.println(User1.TEAM_CODE.value);
        System.out.println(User1.FULL_TEAM_NAME.value);

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
