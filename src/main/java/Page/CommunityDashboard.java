package Page;
/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class CommunityDashboard {
    public static final String COMMENT_COMMUNITY = "COMMUNITY";
    public static final String COMMUNITY_DASHBOARD_ = "";
    public static final String COMMUNITY_DASHBOARD_LOGO = "//header//*[@class='logo']";
    public static final String COMMUNITY_DASHBOARD_MANAGEMENT = "//header//*[@href='https://pekama.com/a/']";
    public static final String COMMUNITY_DASHBOARD_SIGNUP = "//header//*[@href='/signup/?next=%2Fa%2Fcommunity%2Fwizard']";
    public static final String COMMUNITY_DASHBOARD_LOGIN = "//header//*[@href='/accounts/login/?next=%2Fa%2Fcommunity%2Fwizard']";
//    public static final String COMMUNITY_DASHBOARD_ = "";
//    public static final String COMMUNITY_DASHBOARD_ = "";
//    public static final String COMMUNITY_DASHBOARD_ = "";
//    public static final String COMMUNITY_DASHBOARD_ = "";

    public static final String communityBtnExplore = "link=Explore the community";
    public static final String communityBtnSignUp = "//a[@type='button' and contains(.,'Sign up')]";
    public static final String communityBtnLogin = "//a[@type='button' and contains(.,'Login')]";
    public static final String communityBtnGetStarted = "//button[contains(text(),'Get Started')]";
    public static final String communityBtnStartConversation = "//button[contains(text(),'start new conversation')]";
    public static final String communityBtnStartRequestInstruction = "//button[contains(text(),'request introductions')]";
    public static final String communityBtnBoostProfile = "//button[contains(text(),'Boost Your Profile')]";
    public static final String communityBtnSendInstructions = "//button[contains(text(),'Send Instructions')]";
    public static final String communityBtnInstruct = "//button[contains(text(),'Instruct Now!')]";
    public static final String communityBtnWithdraw = "//button[contains(text(),'withdraw instructions')]";
    public static final String communityBtnConfirmInstructions = "//button[contains(text(),'Confirm Instructions')]";
    public static final String communityBtnConfirmCompletion = "//button[contains(text(),'Confirm Completion')]";


    public static final String communityTabSupplier = "//a[@class and @href='/a/community/wizard']";
    public static final String communityTabOutgoing = "//a[@class and @href='/a/community/outgoing']";
    public static final String communityTabIncoming = "//a[@class and @href='/a/community/incoming']";
    public static final String communityTabProfile = "//a[@class and @href='/a/community/profile']";
    public static final String communityBtnHeaderLogin = "//header//a[@type='button' and contains(.,'Login')]";
    public static final String communityBtnHeaderSignUp = "//header//a[@type='button' and contains(.,'Sign up')]";
    public static final String communityBtn = "//button[contains(text(),'Get Started')]";
// public static final String communityBtn = "//button[contains(text(),'Get Started')]";

    public static final String communitySelectCaseType = "//div[@class='panel-body']//div[@name='matterType']//span";
    public static final String communityInputCaseType = "//div[@class='panel-body']//div[@name='matterType']//input";
    public static final String communitySelectDefining = "//div[@class='panel-body']//div[@name='defining']//span";
    public static final String communityInputDefining = "//div[@class='panel-body']//div[@name='defining']//input";
    public static final String communitySelectExpertType = "//div[@class='panel-body']//div[@name='expertiseType']//span";
    public static final String communityInputExpertType = "//div[@class='panel-body']//div[@name='expertiseType']//input";

    public static final String communityLabelYou = "//span[contains(text(),'This Is You!')]";
    public static final String communityLabelExpertTeam = "//span[contains(text(),'Member of Qweeco03')]";
    public static final String communityLabelCollaborator = "//span[contains(text(),'Existing Relationship')]";
    public static final String communityLabel = "//span[contains(text(),'')]";

    public static final String communityStatus = "//div[@class='status']//span";
    public static final String communityStatusDraft = "draft";
    public static final String communityStatusSent = "sent";
    public static final String communityStatusReceived = "received";
    public static final String communityStatusConfirmed = "confirmed";
    public static final String communityStatusCompleted = "completed";
    public static final String communityStatusCancelled = "withdrawn";

    public static final String communityMwbody = "//div[@class='modal-content']";
    public static final String communityMwTitle = "//div[@class='modal-content']//h2";
    public static final String communityMwText = "//div[@class='modal-content']//p";
    public static final String communityMwLink = "//div[@class='modal-community-footer ng-scope']/div[@class='link']";
    public static final String communityMwNo = "//div[@class='modal-content']//button[contains(text(),'No')]";
    public static final String communityMwYes = "//div[@class='modal-content']//button[contains(text(),'Yes')]";
    public static final String communityMwLinkTextNoSendEmail = "I already asked the expert not to proceed";
    public static final String communityMwText1 = "Please DO NOT proceed with this filing. Kindly confirm immediately.";
    public static final String communityMwText2 = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String communityMwText3 = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String communityMwText4 = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String communityMwText5 = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";

    public static final String communityHeaderSignUp = "//a[@class and @href='/signup/?next=%2Fa%2Fcommunity%2Fwizard']";
    public static final String communityHeaderLogin = "//a[@class and @href='/accounts/login/?next=%2Fa%2Fcommunity%2Fwizard']";

    public static final String communityCaseList = "//div[@class='request-quote-list ng-scope']/div";
    public static final String communityCaseIconDraft = "//div/i";
    public static final String communityCaseIcon = "//img[@alt]";
    public static final String communityCaseName = "//div[@class='name']/h3";
    public static final String communityCaseLink = "//div[@class='name']//a";
    public static final String communityCasePatent = "//div[@class='patent']/button";
    public static final String communityCaseStatusOut = "//div[@class='status']//span";
    public static final String communityCaseStatusIn = "//div[@class='status ng-scope']//span";
    public static final String communityCaseDefiningFirst = "class=btn btn-blue text-uppercase ng-binding";

    public static final String communityStep1 = "//div[contains(text(),'Enter case details')]";
    public static final String communityStep2 = "//div[contains(text(),'Choose an expert/associate')]";
    public static final String communityStep3 = "//div[contains(text(),'Start talking')]";
    public static final String communityStep4 = "//div[contains(text(),'Send instructions')]";

    public static final String communityExpertList = "";
    public static final String communityTeamName3 = "Test03 QA";
    public static final String communityTeamName3path = "[contains(.,'Test03 QA')]";
    public static final String communityExpertTeamName = "//div[@class='name ng-binding']";

    public static final String communityMsg = "//ul[@class='message-list']/li";
    public static final String communityMsgBody = "//p";
    public static final String communityPresenterMsg1 = "Dear Test01 QA and Test03 QA,\n\nI believe that you already know each other or your firms are already working together. Test01 QA now needs an IP service in United Kingdom and we believe, as usual, that Test03 QA may be able to help with that. I trust that Test01 QA will follow up with details.";
    public static final String communityRequesterMsgHi = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String communityRequesterMsgWithdwraw = "";
    public static final String communityExpertMsgConfirmation = "I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed.";
    public static final String communityExpertMsgInstructionReceived = "I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed.";
    public static final String communityExpertMsgCaseCompleted = "I'm pleased to confirm that your instructions were executed and the work was completed on time.";
    public static final String communityFirstMsgBody = "css=div.message-body.ng-binding > p";
    public static final String communityUrlLoginWizard = "accounts/login/?next=%2Fa%2Fcommunity%2Fwizard";
    public static final String communityExpertInfo = "//div[@class='expert-info']/div";

}
