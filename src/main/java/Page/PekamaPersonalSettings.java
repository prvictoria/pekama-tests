package Page;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class PekamaPersonalSettings {
 public static final String PERSONAL_SETTINGS_BTN = "//*[@class='header-panel-nav']//*[@href='/a/settings/profile']";
 public static final String TEAM_SETTINGS_BTN = "//*[@class='header-panel-nav']//*[@href='/a/settings/team']";

 //tabs data attribbute "data-target"

    public static final String personalSettingsTabPersonal = "//*[@id='basic-info']";
    public static final String personalSettingsTabSecurity = "//*[@id='login-security']";
    public static final String personalSettingsTabEmails = "//*[@id='emails']";
    public static final String personalSettingsTabSignature = "//*[@id='signature']";
    public static final String personalSettingsTabIMAP = "//*[@id='imap']";
    public static final String personalSettingsTabTimeTracker = "//*[@id='timeTracker']";


   //tab fields find by NAME
    public static final String PERSONAL_DETAILS_TAB_TITLE = "//*[@data-target='#basic-info']"; //id
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
    public static final String PERSONAL_DETAILS_SAVE_BTN = personalSettingsTabPersonal+"//button[contains(.,'Save')]";
    public static final String PERSONAL_DETAILS_COUNTRY_PLACEHOLDER = "Choose country...";

    //tab fields find by NAME
    public static final String SECURITY_TAB_TITLE = "//*[@data-target='#login-security']";
    public static final String SECURITY_TAB_CURRENT_PASSWORD = "original_password";
    public static final String SECURITY_TAB_NEW_PASSWORD = "password1";
    public static final String SECURITY_TAB_CONFIRM_PASSWORD = "password2";
    public static final String SECURITY_SAVE_BTN = personalSettingsTabSecurity+"//button[contains(.,'Save')]";
    public static final String SECURITY_ENABLE_BTN = "//button[contains(.,'Enable')]";



    //tab fields find by NAME
    public static final String EMAILS_TAB_TITLE = "//*[@data-target='#emails']"; //id

    //tab fields find by NAME
    public static final String SIGNATURE_TAB_TITLE = "//*[@data-target='#signature']"; //id

    //tab fields find by NAME
    public static final String IMAP_TAB_TITLE = "//*[@data-target='#imap']"; //id

    //tab fields find by NAME
    public static final String TIME_TRACKER_TAB_TITLE = "//*[@data-target='#timeTracker']"; //id



}
