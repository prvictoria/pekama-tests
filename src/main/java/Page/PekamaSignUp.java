package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaSignUp {
    public static final String signup = "//form[@id='signup-form']";
    public static final String signupTitle = "";
    public static final String signupNewButtonDisabled = "//form[@id='signup-form']//*[@class='btn btn-primary disabled']//*[contains(text(),'Next Step')]";
    public static final String signupNewButtonEnabled = "//*[@class='btn btn-primary']//*[contains(text(),'Next Step')]";
    public static final String signupPasswordEmptyAlert = "";
    public static final String signupCompanyEmptyAlert = "";
    public static final String signupFirstnameEmptyAlert = "";
    public static final String signupLastnameEmptyAlert = "";
    public static final String signupEmailEmptyAlert = "";
    public static final String signupgGenericEmptyAlert = "//form[@id='signup-form']//li[contains(.,'Please fill out this field.')]";
    public static final String signupResetPassw = "";
}
