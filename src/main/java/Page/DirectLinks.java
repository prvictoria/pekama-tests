package Page;

import static Page.TestsUrlConfiguration.*;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class DirectLinks {
    public static final String urlSingUp = SELECT_HOST+"/signup/";
    public static final String urlLogIn = SELECT_HOST+"/accounts/login/";
    public static final String urlLogout = SELECT_HOST+"/accounts/logout";
    public static final String urlResetPassword = SELECT_HOST+"/accounts/password/reset/";
    public static final String urlResetPasswordSuccess = SELECT_HOST+"/accounts/password/reset/done/";
    public static final String urlLanding = SELECT_HOST+"/";
    public static final String urlDashboard = SELECT_HOST+"/a/dashboard";

    public static final String urlReportsProjects = PEKAMA+DOMAIN+"a/reports/projects";
    public static final String urlReportsTasks = PEKAMA+DOMAIN+"a/reports/tasks";
    public static final String urlReportsEvents = PEKAMA+DOMAIN+"a/reports/events";
    public static final String urlReportsCharges = PEKAMA+DOMAIN+"a/reports/financials";
    public static final String urlReportsContacts = PEKAMA+DOMAIN+"a/reports/contacts";

    public static final String urlPersonalSettings = PEKAMA+DOMAIN+"a/settings/profile";
    public static final String urlTeamSettings = PEKAMA+DOMAIN+"a/settings/team";
    public static final String urlTS_Members = PEKAMA+DOMAIN+"a/settings/team/members";

    public static final String COMMUNITY_PATH = COMMUNITY;
    public static final String COMMUNITY_DASHBOARD = COMMUNITY+"/a";
    public static final String COMMUNITY_LOGOUT = COMMUNITY+"/accounts/logout";
    public static final String COMMUNITY_WIZARD = COMMUNITY+"/a/community/wizard";
    public static final String COMMUNITY_OUTGOING = COMMUNITY+"/a/community/outgoing";
    public static final String COMMUNITY_INCOMING = COMMUNITY+"/a/community/incoming";
    public static final String COMMUNITY_PROFILE = COMMUNITY+"/a/community/profile";

    public static final String BACK_QUERY_COMMUNITY = "/?next=%2Fa%2Fcommunity%2F";
    public static final String BACK_TO_WIZARD = COMMUNITY+BACK_QUERY_COMMUNITY+"wizard";
    public static final String BACK_TO_OUTGOING = COMMUNITY+BACK_QUERY_COMMUNITY+"outgoing";
    public static final String BACK_TO_INCOMING = COMMUNITY+BACK_QUERY_COMMUNITY+"incoming";
    public static final String BACK_TO_PROFILE = COMMUNITY+BACK_QUERY_COMMUNITY+"profile";





}
