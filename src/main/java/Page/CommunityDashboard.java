package Page;
/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class CommunityDashboard {
    public static final String COMMENT_COMMUNITY = "COMMUNITY";
    public static final String COMMUNITY_DASHBOARD_ = "";
    public static final String COMMUNITY_HEADER_LOGO = "//header//*[@class='logo']";
    public static final String COMMUNITY_HEADER_MANAGEMENT = "//header//*[@href='https://pekama.com/a/']";
    public static final String COMMUNITY_HEADER_SIGNUP = "//header//*[@href='/signup/?next=%2Fa%2Fcommunity%2Fwizard']";
    public static final String COMMUNITY_HEADER_LOGIN = "//header//*[@href='/accounts/login/?next=%2Fa%2Fcommunity%2Fwizard']";

    public static final String COMMUNITY_HEADER_UserDropdown = "//*[@class='pkm-icon-down-open']";
    public static final String COMMUNITY_HEADER_PersonalSettings = "//ul[@role='menu']//*[text()='Personal settings']";
    public static final String COMMUNITY_HEADER_TeamSettings = "//ul[@role='menu']//*[text()='Team settings']";
    public static final String COMMUNITY_HEADER_LogOut = "//ul[@role='menu']//*[@ng-click='logout()']";

//    public static final String COMMUNITY_DASHBOARD_ = "";
    public static final String COMMUNITY_TAB_Supplier = "//a[contains(@href, '/a/community/wizard')]";
    public static final String COMMUNITY_TAB_Incoming = "//a[contains(@href, '/a/community/incoming')]";
    public static final String COMMUNITY_TAB_Outgoing = "//a[contains(@href, '/a/community/outgoing')]";
    public static final String COMMUNITY_TAB_Profile = "//a[contains(@href, '/a/community/profile')]";
//    public static final String COMMUNITY_BTN_HeaderLogin = "//header//a[@type='button' and contains(.,'Login')]";
//    public static final String COMMUNITY_BTN_HeaderSignUp = "//header//a[@type='button' and contains(.,'Sign up')]";
//    public static final String COMMUNITY_BTN_ = "//button[contains(text(),'Get Started')]";

//    public static final String COMMUNITY_BTN_Explore = "link=Explore the community";
    public static final String COMMUNITY_BTN_SignUp = "//a[@type='button' and contains(.,'Sign up')]";
    public static final String COMMUNITY_BTN_Login = "//a[@type='button' and contains(.,'Login')]";
    public static final String COMMUNITY_BTN_GetStarted = "//button[contains(text(),'Get Started')]";
    public static final String COMMUNITY_BTN_StartConversation = "//button[contains(text(),'start new conversation')]";
    public static final String COMMUNITY_BTN_StartRequestInstruction = "//button[contains(text(),'request introductions')]";
    public static final String COMMUNITY_BTN_BoostProfile = "//button[contains(text(),'Boost Your Profile')]";
    public static final String COMMUNITY_BTN_SendInstructions = "//button[contains(text(),'Send Instructions')]";
    public static final String COMMUNITY_BTN_Instruct = "//button[contains(text(),'Instruct Now!')]";
    public static final String COMMUNITY_BTN_Withdraw = "//button[contains(text(),'withdraw instructions')]";
    public static final String COMMUNITY_BTN_ConfirmInstructions = "//button[contains(text(),'Confirm Instructions')]";
    public static final String COMMUNITY_BTN_ConfirmCompletion = "//button[contains(text(),'Confirm Completion')]";

    public static final String COMMUNITY_SELECT_CaseType = "//div[@class='panel-body']//div[@name='matterType']//span";
    public static final String COMMUNITY_INPUT_CaseType = "//div[@class='panel-body']//div[@name='matterType']//input";
    public static final String COMMUNITY_SELECT_Defining = "//div[@class='panel-body']//div[@name='defining']//span";
    public static final String COMMUNITY_INPUT_Defining = "//div[@class='panel-body']//div[@name='defining']//input";
    public static final String COMMUNITY_SELECT_ExpertType = "//div[@class='panel-body']//div[@name='expertiseType']//span";
    public static final String COMMUNITY_INPUT_ExpertType = "//div[@class='panel-body']//div[@name='expertiseType']//input";

    public static final String COMMUNITY_LABEL_You = "//span[contains(text(),'This Is You!')]";
    public static final String COMMUNITY_LABEL_ExpertTeam = "//span[contains(text(),'Member of Qweeco03')]";
    public static final String COMMUNITY_LABEL_Collaborator = "//span[contains(text(),'Existing Relationship')]";
    public static final String COMMUNITY_LABEL_ = "//span[contains(text(),'')]";

    public static final String COMMUNITY_STATUS_ = "//div[@class='status']//span";
    public static final String COMMUNITY_STATUS_Draft = "draft";
    public static final String COMMUNITY_STATUS_Sent = "sent";
    public static final String COMMUNITY_STATUS_Received = "received";
    public static final String COMMUNITY_STATUS_Confirmed = "confirmed";
    public static final String COMMUNITY_STATUS_Completed = "completed";
    public static final String COMMUNITY_STATUS_Cancelled = "withdrawn";





    public static final String COMMUNITY_CaseList = "//div[@class='request-quote-list ng-scope']/div";
    public static final String COMMUNITY_CaseIconDraft = "//div/i";
    public static final String COMMUNITY_CaseIcon = "//img[@alt]";
    public static final String COMMUNITY_CaseName = "//div[@class='name']/h3";
    public static final String COMMUNITY_CaseLink = "//div[@class='name']//a";
    public static final String COMMUNITY_CasePatent = "//div[@class='patent']/button";
    public static final String COMMUNITY_CaseStatusOut = "//div[@class='status']//span";
    public static final String COMMUNITY_CaseStatusIn = "//div[@class='status ng-scope']//span";
    public static final String COMMUNITY_CaseDefiningFirst = "class=btn btn-blue text-uppercase ng-binding";

    public static final String COMMUNITY_Step1 = "//div[contains(text(),'Enter case details')]";
    public static final String COMMUNITY_Step2 = "//div[contains(text(),'Choose an expert/associate')]";
    public static final String COMMUNITY_Step3 = "//div[contains(text(),'Start talking')]";
    public static final String COMMUNITY_Step4 = "//div[contains(text(),'Send instructions')]";

    public static final String COMMUNITY_ExpertList = "";
    public static final String COMMUNITY_TeamName3 = "Test03 QA";
    public static final String COMMUNITY_TeamName3path = "[contains(.,'Test03 QA')]";
    public static final String COMMUNITY_ExpertTeamName = "//div[@class='name ng-binding']";

    public static final String COMMUNITY_Msg = "//ul[@class='message-list']/li";
    public static final String COMMUNITY_MsgBody = "//p";
    public static final String COMMUNITY_PresenterMsg1 = "Dear Test01 QA and Test03 QA,\n\nI believe that you already know each other or your firms are already working together. Test01 QA now needs an IP service in United Kingdom and we believe, as usual, that Test03 QA may be able to help with that. I trust that Test01 QA will follow up with details.";
    public static final String COMMUNITY_RequesterMsgHi = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String COMMUNITY_RequesterMsgWithdwraw = "";
    public static final String COMMUNITY_ExpertMsgConfirmation = "I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed.";
    public static final String COMMUNITY_ExpertMsgInstructionReceived = "I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed.";
    public static final String COMMUNITY_ExpertMsgCaseCompleted = "I'm pleased to confirm that your instructions were executed and the work was completed on time.";
    public static final String COMMUNITY_FirstMsgBody = "css=div.message-body.ng-binding > p";
    public static final String COMMUNITY_UrlLoginWizard = "accounts/login/?next=%2Fa%2FCOMMUNITY_%2Fwizard";
    public static final String COMMUNITY_ExpertInfo = "//div[@class='expert-info']/div";

}
