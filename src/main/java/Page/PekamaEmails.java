package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaEmails {
    public static final String EMAIL_RESET_PASSWORD = "";
    public static final String EMAIL_CONFIRM_REGISTRATION = "";
    public static final String EMAIL_IVITE_IN_PEKAMA = "";
    public static final String EMAIL_INVITE_IN_PROJECT = "";
    public static final String EMAIL_TO_TEAM_ADMIN = "";
    public static final String EMAIL_REPORT = "";
    public static final String EMAIL_COMMUNITY = "";

    public static final String GMAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
    public static final String GMAIL_LOGIN = "testqweeco001@gmail.com";
    public static final String GMAIL_PASSWORD = "123456789qasw1";
    public static final String GMAIL_LOGIN_FIELD = "Email";
    public static final String GMAIL_PASSWORD_FIELD = "#Passwd";
    public static final String GMAIL_SIGNIN_BTN = "#signIn";
    public static final String GMAIL_NEXT_BTN = "#next";

    public static final String GMAIL_EMAIL_LIST = "//div[@role='main']//table/tbody/tr";
    public static final String GMAIL_EMAIL_LIST_SUBJECT = "//div[@role='link']//span[@id]";

    public static final String GMAIL_ROW_NUMBER = "[1]";
    public static final String GMAIL_EMAIL_SUBJECT_PATH = GMAIL_EMAIL_LIST+GMAIL_ROW_NUMBER+GMAIL_EMAIL_LIST;

    public static final String OUTLOOK_URL = "https://outlook.live.com/owa/?path=/mail/inbox";
    public static final String OUTLOOK_INBOX_LIST = "//div[@role='listbox']//div[@data-convid][2]/*[@role='option']";
    public static final String OUTLOOK_INBOX_EMAIL_SUNJECT = "//tbody/tr[1]//b[1]";
    public static final String EMAIL_SUBJECT_LastWeekReport = "Pekama Report \"Last week's Events\"";
    public static final String EMAIL_SUBJECT_ResetPassword = "";
    public static final String EMAIL_SUBJECT_InviteInTeam = "";
    public static final String EMAIL_SUBJECT_ConfirmRegistration = "";
    public static final String EMAIL_SUBJECT_ForAdmin = "";
    public static final String EMAIL_SUBJECT_6 = "";
    public static final String EMAIL_SUBJECT_7 = "";
    public static final String EMAIL_SUBJECT_8 = "";
}
