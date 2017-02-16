package Page;
import static Page.TestsUrlConfiguration.*;
/**
 * Created by VatslauX on 29-Dec-16.
 */
public class TestsUrl {
    public static final String URL_SingUp = SELECT_HOST+"/signup/";
    public static final String URL_LogIn = SELECT_HOST+"/accounts/login/";
    public static final String URL_Logout = SELECT_HOST+"/accounts/logout";
    public static final String URL_ResetPassword = SELECT_HOST+"/accounts/password/reset/";
    public static final String URL_ResetPasswordSuccess = SELECT_HOST+"/accounts/password/reset/done/";
    public static final String URL_ResetPasswordComplete = SELECT_HOST+"/accounts/password/reset/complete/";
    public static final String URL_Landing = SELECT_HOST+"/";
    public static final String URL_Dashboard = SELECT_HOST+"/a/dashboard";

    public static final String URL_ReportsProjects = PEKAMA+"/a/reports/projects";
    public static final String URL_ReportsTasks = PEKAMA+"/a/reports/tasks";
    public static final String URL_ReportsEvents = PEKAMA+"/a/reports/events";
    public static final String URL_ReportsCharges = PEKAMA+"/a/reports/charges";
    public static final String URL_ReportsContacts = PEKAMA+"/a/reports/contacts";

    public static final String URL_PersonalSettings = PEKAMA+"/a/settings/profile";
    public static final String URL_TeamSettings = PEKAMA+"/a/settings/team";
    public static final String URL_Members = PEKAMA+"/a/settings/team/members";
    public static final String URL_VALUES = PEKAMA+"/a/settings/team/values/";
    public static final String URL_TEMPLATES_PROJECT = PEKAMA+"/a/settings/team/templates/projects";
    public static final String URL_TEMPLATES_TASKS = PEKAMA+"/a/settings/team/templates/tasks";
    public static final String URL_TEMPLATES_MSG = PEKAMA+"/a/settings/team/templates/messages";
    public static final String URL_TEMPLATES_EVANT = PEKAMA+"/a/settings/team/templates/events";
    public static final String URL_TEMPLATES_DOC = PEKAMA+"/a/settings/team/templates/documents";
    public static final String URL_TEMPLATES_STORAGE = PEKAMA+"/a/settings/team/templates/storage";

    public static final String URL_COMMUNITY_PATH = COMMUNITY;
    public static final String URL_COMMUNITY_DASHBOARD = COMMUNITY+"/a";
    public static final String URL_COMMUNITY_SIGN_UP = COMMUNITY+"/signup/";
    public static final String URL_COMMUNITY_LOGIN = COMMUNITY+"/accounts/login/";
    public static final String URL_COMMUNITY_LOGOUT = COMMUNITY+"/accounts/logout";
    public static final String URL_COMMUNITY_WIZARD = COMMUNITY+"/a/community/wizard";
    public static final String URL_COMMUNITY_OUTGOING = COMMUNITY+"/a/community/outgoing";
    public static final String URL_COMMUNITY_INCOMING = COMMUNITY+"/a/community/incoming";
    public static final String URL_COMMUNITY_EXPERTS = COMMUNITY+"/a/community/experts";
    public static final String URL_COMMUNITY_PROFILE = COMMUNITY+"/a/community/profile";
    public static final String URL_COMMUNITY_PROFILE_TEAM = COMMUNITY+"/a/community/profile/team";
    public static final String URL_COMMUNITY_PROFILE_USER = COMMUNITY+"/a/community/profile/personal";

    public static final String URL_COMMUNITY_QUERY_COMMUNITY = "/?next=%2Fa%2Fcommunity%2F";
    public static final String URL_COMMUNITY_TO_WIZARD = COMMUNITY+URL_COMMUNITY_QUERY_COMMUNITY+"wizard";
    public static final String URL_COMMUNITY_TO_OUTGOING = COMMUNITY+URL_COMMUNITY_QUERY_COMMUNITY+"outgoing";
    public static final String URL_COMMUNITY_TO_INCOMING = COMMUNITY+URL_COMMUNITY_QUERY_COMMUNITY+"incoming";
    public static final String URL_COMMUNITY_TO_PROFILE = COMMUNITY+URL_COMMUNITY_QUERY_COMMUNITY+"profile";

    public static final String URL_BOX_LOGIN = "https://account.box.com/login";
    public static final String URL_BOX_FILES = "https://app.box.com/files";
    public static final String URL_BOX_LOGOUT = "https://app.box.com/logout";

    public static final String URL_COMMUNITY_LOGIN_WIZARD = "accounts/login/?next=%2Fa%2FURL_COMMUNITY_%2Fwizard";






}
