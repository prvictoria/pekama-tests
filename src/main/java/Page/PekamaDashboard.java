package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaDashboard {
    public static final String dashboardProjectsTitle = "//h4[contains(.,'Projects')]";
    public static final String dashboardYourProfileTitle = "//h4[contains(.,'Your Profile And Team')]";
    public static final String dashboardUpcomingTitle = "//h4[contains(.,'UPCOMING')]";
    public static final String dashboardTasksTitle = "//h4[contains(.,'Tasks')]";

    public static final String dashboardSelectProjectTemplatesButton = "xpath=//button[@type='button'][2]";
    public static final String dashboardSelectProjectTemplatesTemplate01 = "//a[@ng-click='applyTemplate(template)'][1]";
    public static final String dashboardNewProject = "//button[@type='button'][contains(.,'+ NEW')]";
    public static final String dashboardProjectList01 = "//li[@class='item matter ng-scope']/a";
    public static final String dashboardProjectList02 = "//li[2][@class='item matter ng-scope']/a";
    public static final String dashboardProjectList03 = "//li[3][@class='item matter ng-scope']/a";
    public static final String dashboardProjectList04 = "//li[4][@class='item matter ng-scope']/a";
    public static final String dashboardProjectList05 = "//li[5][@class='item matter ng-scope']/a";

    public static final String dashboardInvite = "link=+ INVITE";
    public static final String dashboardTeamMembersQTY = "//*[@ui-sref='settings.organization.members']";
    public static final String dashboardAvailableProjectCount = "//a/span";
    public static final String dashboardBuyProjects = "link=*BUY MORE";
    public static final String dashboardUserAvatar = "???";
    public static final String dashboardProjectsLeft = "//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li[2]/div";
    public static final String dashboardYourProfileAndTeam = "//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div/div/div/h4";
    public static final String dashboardYourTeams = "//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li/div";
    public static final String dashboardTeamName = "//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li/div[2]/div/div/span/span[2]/span";

    public static final String dashboardNoProjects = "//pkm-dashboard-projects//div[starts-with(@class, 'alert alert-empty')]";

    public static final String dashboardUpcoming = "";
    public static final String dashboardUpcomingToday = "//button[@type='button'][contains(.,'today')]";
    public static final String dashboardUpcomingMonth = "//button[@type='button'][contains(.,'month')]";
    public static final String dashboardUpcomingWeek = "//button[@type='button'][contains(.,'week')]";
    public static final String dashboardUpcomingDay = "xpath=(//button[@type='button'])[8]";
    public static final String dashboardUpcomingPastButton = "xpath=(//button[@type='button'])[3]";
    public static final String dashboardUpcomingNextButton = "xpath=(//button[@type='button'])[4]";

    public static final String dashboardUpcomingMyDeadlines = "//li/button[contains(.,'My Deadlines')]";
    public static final String dashboardUpcomingMyDeadlinesSelected = "//li[@class='active-calendar-filter' and contains(.,'My Deadlines')]";
    public static final String dashboardUpcomingAllDeadlines = "//li/button[contains(.,'All Deadlines')]";
    public static final String dashboardUpcomingAllDeadlinesSelected = "//li[@class='active-calendar-filter' and contains(.,'All Deadlines')]";
    public static final String dashboardUpcomingMyTasks = "//li/button[contains(.,'My Tasks')]";
    public static final String dashboardUpcomingMyTasksSelected = "//li[@class='active-calendar-filter' and contains(.,'My Tasks')]";
    public static final String dashboardUpcomingAllTasks = "//li/button[contains(.,'All Tasks')]";
    public static final String dashboardUpcomingAllTasksSelected = "//li[@class='active-calendar-filter' and contains(.,'All Tasks')]";

    public static final String dashboardTasks = "";
    public static final String dashboardTasksToDo = "link=To Do";
    public static final String dashboardTasksDoing = "link=Doing";
}
