package Page;
import static Page.UrlConfig.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class UrlStrings {
    public static final String URL_SingUp = SELECT_HOST+"/signup/";
    public static final String URL_LogIn = SELECT_HOST+"/accounts/login/";
    public static final String URL_Logout = SELECT_HOST+"/accounts/logout";
    public static final String URL_ResetPassword = SELECT_HOST+"/accounts/password/reset/";
    public static final String URL_ResetPasswordSuccess = SELECT_HOST+"/accounts/password/reset/done/";
    public static final String URL_ResetPasswordComplete = SELECT_HOST+"/accounts/password/reset/complete/";
    public static final String URL_Landing = SELECT_HOST+"/";
    public static final String URL_Dashboard = SELECT_HOST+"/a/dashboard";
    public static final String URL_NotFound = SELECT_HOST+"/a/projects/not_found";

    public static final String URL_ReportsProjects = ENVIRONMENT_PEKAMA +"/a/reports/projects";
    public static final String URL_ReportsTasks = ENVIRONMENT_PEKAMA +"/a/reports/tasks";
    public static final String URL_ReportsEvents = ENVIRONMENT_PEKAMA +"/a/reports/events";
    public static final String URL_ReportsCharges = ENVIRONMENT_PEKAMA +"/a/reports/charges";
    public static final String URL_ReportsContacts = ENVIRONMENT_PEKAMA +"/a/reports/contacts";

    public static final String URL_PersonalSettings = ENVIRONMENT_PEKAMA +"/a/settings/profile";
    public static final String URL_TeamSettings = ENVIRONMENT_PEKAMA +"/a/settings/team";
    public static final String URL_Members = ENVIRONMENT_PEKAMA +"/a/settings/team/members";
    public static final String URL_VALUES = ENVIRONMENT_PEKAMA +"/a/settings/team/values/";
    public static final String URL_TEMPLATES_PROJECT = ENVIRONMENT_PEKAMA +"/a/settings/team/templates/projects";
    public static final String URL_TEMPLATES_TASKS = ENVIRONMENT_PEKAMA +"/a/settings/team/templates/tasks";
    public static final String URL_TEMPLATES_TASKS_TRADEMARK = ENVIRONMENT_PEKAMA +"/a/settings/team/templates/tasks/trademarks";
    public static final String URL_TEMPLATES_MSG = ENVIRONMENT_PEKAMA +"/a/settings/team/templates/messages";
    public static final String URL_TEMPLATES_EVENT = ENVIRONMENT_PEKAMA +"/a/settings/team/templates/events";
    public static final String URL_TEMPLATES_DOC = ENVIRONMENT_PEKAMA +"/a/settings/team/templates/documents";
    public static final String URL_TEMPLATES_STORAGE = ENVIRONMENT_PEKAMA +"/a/settings/team/templates/storage";

    public static final String URL_COMMUNITY_PATH = ENVIRONMENT_COMMUNITY;
    public static final String URL_COMMUNITY_DASHBOARD = ENVIRONMENT_COMMUNITY +"/a";
    public static final String URL_COMMUNITY_SIGN_UP = ENVIRONMENT_COMMUNITY +"/signup/";
    public static final String URL_COMMUNITY_LOGIN = ENVIRONMENT_COMMUNITY +"/accounts/login/";
    public static final String URL_COMMUNITY_LOGOUT = ENVIRONMENT_COMMUNITY +"/accounts/logout";
    public static final String URL_COMMUNITY_WIZARD = ENVIRONMENT_COMMUNITY +"/a/community/wizard";
    public static final String URL_COMMUNITY_OUTGOING = ENVIRONMENT_COMMUNITY +"/a/community/outgoing";
    public static final String URL_COMMUNITY_INCOMING = ENVIRONMENT_COMMUNITY +"/a/community/incoming";
    public static final String URL_COMMUNITY_EXPERTS = ENVIRONMENT_COMMUNITY +"/a/community/experts";
    public static final String URL_COMMUNITY_PROFILE = ENVIRONMENT_COMMUNITY +"/a/community/profile";
    public static final String URL_COMMUNITY_PROFILE_TEAM = ENVIRONMENT_COMMUNITY +"/a/community/profile/team";
    public static final String URL_COMMUNITY_PROFILE_USER = ENVIRONMENT_COMMUNITY +"/a/community/profile/personal";

    public static final String URL_COMMUNITY_QUERY_COMMUNITY = "/?next=%2Fa%2Fcommunity%2F";
    public static final String URL_COMMUNITY_TO_WIZARD = ENVIRONMENT_COMMUNITY +URL_COMMUNITY_QUERY_COMMUNITY+"wizard";
    public static final String URL_COMMUNITY_TO_OUTGOING = ENVIRONMENT_COMMUNITY +URL_COMMUNITY_QUERY_COMMUNITY+"outgoing";
    public static final String URL_COMMUNITY_TO_INCOMING = ENVIRONMENT_COMMUNITY +URL_COMMUNITY_QUERY_COMMUNITY+"incoming";
    public static final String URL_COMMUNITY_TO_PROFILE = ENVIRONMENT_COMMUNITY +URL_COMMUNITY_QUERY_COMMUNITY+"profile";

    public static final String URL_BOX_LOGIN = "https://account.box.com/login";
    public static final String URL_BOX_FILES = "https://app.box.com/files";
    public static final String URL_BOX_LOGOUT = "https://app.box.com/logout";

    public static final String URL_COMMUNITY_LOGIN_WIZARD = "accounts/login/?next=%2Fa%2FURL_COMMUNITY_%2Fwizard";






}
