package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class CommunityDashboard extends Page {

    public static final SelenideElement COMMUNITY_DASHBOARD_ = $(byXpath(""));
    public static final SelenideElement COMMUNITY_HEADER_LOGO = $(byXpath("//header//*[@class='logo']"));
    public static final SelenideElement COMMUNITY_HEADER_MANAGEMENT = $(byXpath("//header//*[@href and contains(.,'management')]"));
    public static final SelenideElement COMMUNITY_HEADER_SIGNUP = $(byXpath("//header//*[@href='/signup/?next=%2Fa%2Fcommunity%2Fwizard']"));
    public static final SelenideElement COMMUNITY_HEADER_LOGIN = $(byXpath("//header//*[@href='/accounts/login/?next=%2Fa%2Fcommunity%2Fwizard']"));

    public static final SelenideElement COMMUNITY_HEADER_UserDropdown = $(byXpath("//*[@class='pkm-icon-down-open']"));
    public static final SelenideElement COMMUNITY_HEADER_PersonalSettings = $(byXpath("//ul[@role='menu']//*[text()='Personal settings']"));
    public static final SelenideElement COMMUNITY_HEADER_TeamSettings = $(byXpath("//ul[@role='menu']//*[text()='Team settings']"));
    public static final SelenideElement COMMUNITY_HEADER_LogOut = $(byXpath("//ul[@role='menu']//*[@ng-click='logout()']"));

    public static final SelenideElement COMMUNITY_TAB_Supplier = $(byXpath("//a[contains(@href, '/a/community/wizard')]"));
    public static final SelenideElement COMMUNITY_TAB_Outgoing = $(byXpath("//a[contains(@href, '/a/community/outgoing')]"));
    public static final SelenideElement COMMUNITY_TAB_Incoming = $(byXpath("//a[contains(@href, '/a/community/incoming')]"));
    public static final SelenideElement COMMUNITY_TAB_Experts = $(byXpath("//a[contains(@href, '/a/community/experts')]"));
    public static final SelenideElement COMMUNITY_TAB_Profile = $(byXpath("//a[contains(@href, '/a/community/profile')]"));

    public static final SelenideElement COMMUNITY_INNRER_BTN_SIGNUP = $(byXpath("//a[@type='button' and contains(.,'Sign up')]"));
    public static final SelenideElement COMMUNITY_INNRER_BTN_LOGIN = $(byXpath("//a[@type='button' and contains(.,'Login')]"));

    public static final SelenideElement COMMUNITY_BTN_StartConversation = $(byXpath("//button[contains(text(),'start new conversation')]"));
    public static final SelenideElement COMMUNITY_BTN_StartRequestInstruction = $(byXpath("//button[contains(text(),'request introductions')]"));
    public static final SelenideElement COMMUNITY_BTN_BoostProfile = $(byXpath("//button[contains(text(),'Boost Your Profile')]"));
    public static final SelenideElement COMMUNITY_BTN_SendInstructions = $(byXpath("//button[contains(text(),'Send Instructions')]"));
    public static final SelenideElement COMMUNITY_BTN_Instruct = $(byXpath("//button[contains(text(),'Instruct Now!')]"));
    public static final SelenideElement COMMUNITY_BTN_Withdraw = $(byXpath("//button[contains(text(),'withdraw instructions')]"));
    public static final SelenideElement COMMUNITY_BTN_ConfirmInstructions = $(byXpath("//button[contains(text(),'Confirm Instructions')]"));
    public static final SelenideElement COMMUNITY_BTN_ConfirmCompletion = $(byXpath("//button[contains(text(),'Confirm Completion')]"));

    public static final SelenideElement COMMUNITY_LABEL_You = $(byXpath("//span[contains(text(),'This Is You!')]"));
    public static final SelenideElement COMMUNITY_LABEL_ExpertTeam = $(byXpath("//span[contains(text(),'Member of Qweeco03')]"));
    public static final SelenideElement COMMUNITY_LABEL_Collaborator = $(byXpath("//span[contains(text(),'Existing Relationship')]"));
    public static final SelenideElement COMMUNITY_LABEL_ = $(byXpath("//span[contains(text(),'')]"));

    public static final SelenideElement COMMUNITY_STATUS_ = $(byXpath("//div[@class='status']//span"));
    public static final SelenideElement COMMUNITY_STATUS_Draft = $(byXpath("draft"));
    public static final SelenideElement COMMUNITY_STATUS_Sent = $(byXpath("sent"));
    public static final SelenideElement COMMUNITY_STATUS_Received = $(byXpath("received"));
    public static final SelenideElement COMMUNITY_STATUS_Confirmed = $(byXpath("confirmed"));
    public static final SelenideElement COMMUNITY_STATUS_Completed = $(byXpath("completed"));
    public static final SelenideElement COMMUNITY_STATUS_Cancelled = $(byXpath("withdrawn"));

    public static final SelenideElement COMMUNITY_CaseList = $(byXpath("//div[@class='request-quote-list ng-scope']/div"));
    public static final SelenideElement COMMUNITY_CaseIconDraft = $(byXpath("//div/i"));
    public static final SelenideElement COMMUNITY_CaseIcon = $(byXpath("//img[@alt]"));
    public static final SelenideElement COMMUNITY_CaseName = $(byXpath("//div[@class='name']/h3"));
    public static final SelenideElement COMMUNITY_CaseLink = $(byXpath("//div[@class='name']//a"));
    public static final SelenideElement COMMUNITY_CasePatent = $(byXpath("//div[@class='patent']/button"));
    public static final SelenideElement COMMUNITY_CaseStatusOut = $(byXpath("//div[@class='status']//span"));
    public static final SelenideElement COMMUNITY_CaseStatusIn = $(byXpath("//div[@class='status ng-scope']//span"));
    public static final SelenideElement COMMUNITY_CaseDefiningFirst = $(byXpath("class=btn btn-blue text-uppercase ng-binding"));

    public static final SelenideElement COMMUNITY_Step1 = $(byXpath("//div[contains(text(),'Enter case details')]"));
    public static final SelenideElement COMMUNITY_Step2 = $(byXpath("//div[contains(text(),'Choose an expert/associate')]"));
    public static final SelenideElement COMMUNITY_Step3 = $(byXpath("//div[contains(text(),'Start talking')]"));
    public static final SelenideElement COMMUNITY_Step4 = $(byXpath("//div[contains(text(),'Send instructions')]"));

    public static final SelenideElement COMMUNITY_ExpertList = $(byText(""));
    public static final SelenideElement COMMUNITY_TeamName3 = $(byText("Test03 QA"));
    
    public static String teamName;
    public static final SelenideElement COMMUNITY_TeamName3path = $(byXpath("[contains(.,'"+ teamName +"')]"));
    public static final SelenideElement COMMUNITY_ExpertTeamName = $(byXpath("//div[@class='name ng-binding']"));

    public static final SelenideElement COMMUNITY_Msg = $(byXpath("//ul[@class='message-list']/li"));
    public static final SelenideElement COMMUNITY_MsgBody = $(byXpath("//p"));
    //texts
    public static final SelenideElement COMMUNITY_PresenterMsg1 = $(byText("Dear Test01 QA and Test03 QA,\n\nI believe that you already know each other or your firms are already working together. Test01 QA now needs an IP service in United Kingdom and we believe, as usual, that Test03 QA may be able to help with that. I trust that Test01 QA will follow up with details."));
    public static final SelenideElement COMMUNITY_RequesterMsgHi = $(byText("Thank you for all the information. Please consider this as instructions to proceed with this case as discussed."));
    public static final SelenideElement COMMUNITY_RequesterMsgWithdwraw = $(byText(""));
    public static final SelenideElement COMMUNITY_ExpertMsgConfirmation = $(byText("I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed."));
    public static final SelenideElement COMMUNITY_ExpertMsgInstructionReceived = $(byText("I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed."));
    public static final SelenideElement COMMUNITY_ExpertMsgCaseCompleted = $(byText("I'm pleased to confirm that your instructions were executed and the work was completed on time."));
    public static final SelenideElement COMMUNITY_FirstMsgBody = $(byXpath("css=div.message-body.ng-binding > p"));

    public static final SelenideElement COMMUNITY_ExpertInfo = $(byXpath("//div[@class='expert-info']/div"));


}
