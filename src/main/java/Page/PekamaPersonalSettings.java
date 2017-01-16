package Page;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class PekamaPersonalSettings {
 public static final String PERSONAL_SETTINGS_BTN = "//*[@class='header-panel-nav']//*[@href='/a/settings/profile']";
 public static final String TEAM_SETTINGS_BTN = "//*[@class='header-panel-nav']//*[@href='/a/settings/team']";

 //tabs data attribbute "data-target"

    public static final String personalSettingsTabPersonal = "#basic-info";
    public static final String personalSettingsTabSecurity = "#login-security";
    public static final String personalSettingsTabEmails = "#emails";
    public static final String personalSettingsTabSignature = "#signature";
    public static final String personalSettingsTabIMAP = "#imap";
    public static final String personalSettingsTabTimeTracker = "#timeTracker";


   //tab fields find by NAME
    public static final String PERSONAL_DETAILS_TAB = "#basic-info"; //id
    public static final String PERSONAL_DETAILS_INPUT_NAME = "first_name";
    public static final String PERSONAL_DETAILS_INPUT_SURNAME = "last_name";
    public static final String PERSONAL_DETAILS_INPUT_PHONE = "phone_number";
    public static final String PERSONAL_DETAILS_INPUT_FAX = "fax_number";
    public static final String PERSONAL_DETAILS_INPUT_MOBILE = "cellphone_number";
    public static final String PERSONAL_DETAILS_INPUT_LEGAL_ENTITY = "legal_entity_name";
    public static final String PERSONAL_DETAILS_INPUT_STREET = "street_address";
    public static final String PERSONAL_DETAILS_INPUT_ZIP = "postal_code";
    public static final String PERSONAL_DETAILS_INPUT_CITY = "city";
    public static final String PERSONAL_DETAILS_INPUT_REGION = "region";
    public static final String PERSONAL_DETAILS_COUNTRY_SELECT = "//*[@name='country']//span";
    public static final String PERSONAL_DETAILS_COUNTRY_INPUT = "//*[@name='country']//input";
    public static final String PERSONAL_DETAILS_SAVE_BTN = "//button[contains(.,'Save')]";
    public static final String PERSONAL_DETAILS_COUNTRY_PLACEHOLDER = "Choose country...";

    //tab fields find by NAME
    public static final String SECURITY_TAB = "#login-security"; //id
    public static final String personalSettingsCurrentPassword = "original_password";
    public static final String personalSettingsNewPassword = "password1";
    public static final String personalSettingsConfirmPassword = "password2";
    public static final String personalSettingsSaveButton = "//button[contains(.,'Save')]";
    public static final String personalSettingsEnableButton = "//button[contains(.,'Enable')]";



    //tab fields find by NAME
    public static final String EMAILS_TAB = "#emails"; //id

    //tab fields find by NAME
    public static final String SIGNATURE_TAB = "#signature"; //id

    //tab fields find by NAME
    public static final String IMAP_TAB = "#imap"; //id

    //tab fields find by NAME
    public static final String TIME_TRACKER_TAB = "#timeTracker"; //id



}
