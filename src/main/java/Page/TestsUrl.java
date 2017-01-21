package Page;

import static Page.TestsUrlConfiguration.*;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class TestsUrl {
    public static final String urlSingUp = SELECT_HOST+"/signup/";
    public static final String urlLogIn = SELECT_HOST+"/accounts/login/";
    public static final String urlLogout = SELECT_HOST+"/accounts/logout";
    public static final String urlResetPassword = SELECT_HOST+"/accounts/password/reset/";
    public static final String urlResetPasswordSuccess = SELECT_HOST+"/accounts/password/reset/done/";
    public static final String urlResetPasswordComplete = SELECT_HOST+"/accounts/password/reset/complete/";
    public static final String urlLanding = SELECT_HOST+"/";
    public static final String urlDashboard = SELECT_HOST+"/a/dashboard";

    public static final String urlReportsProjects = PEKAMA+"/a/reports/projects";
    public static final String urlReportsTasks = PEKAMA+"/a/reports/tasks";
    public static final String urlReportsEvents = PEKAMA+"/a/reports/events";
    public static final String urlReportsCharges = PEKAMA+"/a/reports/financials";
    public static final String urlReportsContacts = PEKAMA+"/a/reports/contacts";

    public static final String urlPersonalSettings = PEKAMA+"/a/settings/profile";
    public static final String urlTeamSettings = PEKAMA+"/a/settings/team";
    public static final String urlTSMembers = PEKAMA+"/a/settings/team/members";

    public static final String COMMUNITY_PATH = COMMUNITY;
    public static final String COMMUNITY_DASHBOARD = COMMUNITY+"/a";
    public static final String COMMUNITY_SIGN_UP = COMMUNITY+"/signup/";
    public static final String COMMUNITY_LOGIN = COMMUNITY+"/accounts/login/";
    public static final String COMMUNITY_LOGOUT = COMMUNITY+"/accounts/logout";
    public static final String COMMUNITY_WIZARD = COMMUNITY+"/a/community/wizard";
    public static final String COMMUNITY_OUTGOING = COMMUNITY+"/a/community/outgoing";
    public static final String COMMUNITY_INCOMING = COMMUNITY+"/a/community/incoming";
    public static final String COMMUNITY_PROFILE = COMMUNITY+"/a/community/profile";
    public static final String COMMUNITY_PROFILE_TEAM = COMMUNITY+"/a/community/profile/team";
    public static final String COMMUNITY_PROFILE_USER = COMMUNITY+"/a/community/profile/personal";

    public static final String BACK_QUERY_COMMUNITY = "/?next=%2Fa%2Fcommunity%2F";
    public static final String BACK_TO_WIZARD = COMMUNITY+BACK_QUERY_COMMUNITY+"wizard";
    public static final String BACK_TO_OUTGOING = COMMUNITY+BACK_QUERY_COMMUNITY+"outgoing";
    public static final String BACK_TO_INCOMING = COMMUNITY+BACK_QUERY_COMMUNITY+"incoming";
    public static final String BACK_TO_PROFILE = COMMUNITY+BACK_QUERY_COMMUNITY+"profile";

    public static final String boxLoginURL = "https://account.box.com/login";
    public static final String boxFilesURL = "https://app.box.com/files";
    public static final String boxLogoutURL = "https://app.box.com/logout";

    public static final String COMMUNITY_UrlLoginWizard = "accounts/login/?next=%2Fa%2FCOMMUNITY_%2Fwizard";






}
