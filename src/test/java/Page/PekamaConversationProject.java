package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static Page.PekamaPersonalSettings.SIGNATURE_TAB_TEXT_EDITOR;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PekamaConversationProject extends Page {
    public static final String CONVERSATION_BLOCK = "//pkm-conversations";
    public static final String CONVERSATION_TEAM_TAB_NAME = "Talk to your team";
    public static final String CONVERSATION_PLACEHOLDER_IN_TEAM_TAB = "Team chat is great for conversations between groups of people, where all the group members should see the conversation all the time.";
    public static final String CONVERSATION_CLIENT_TAB_NAME = "Talk to your client/3rd party";
    public static final String CONVERSATION_PLACEHOLDER_IN_CLIENT_TAB = "Client emails are great to send completely standard-looking emails to clients/3rd party, but allow your team to see the conversation.";
    public static final SelenideElement CONVERSATION_ = $(byXpath(CONVERSATION_BLOCK +""));
    public static final SelenideElement CONVERSATION_BTN_New = $(byXpath(CONVERSATION_BLOCK +"//button[contains(.,'New')]"));

    public static final SelenideElement CONVERSATION_BTN_Team = $(byXpath( CONVERSATION_BLOCK+"//button[@class='team-button']"));
    public static final SelenideElement CONVERSATION_BTN_Client = $(byXpath( CONVERSATION_BLOCK+"//button[@class='external-email-button']"));

    //THREAD LIST
    public static final ElementsCollection THREAD_LIST = $$(byXpath(""));


   //INSIDE THREAD
   //TOP CONTROLS
    public static final SelenideElement CONVERSATION_LABEL_ACTIVE_TAB = $(byXpath( CONVERSATION_BLOCK+"//pkm-conversation/div/button"));
    public static final SelenideElement CONVERSATION_BTN_BACK = $(byLinkText("Back"));
    public static final SelenideElement CONVERSATION_BTN_EMAIL_PARAMETERS = $(byXpath( CONVERSATION_BLOCK+"//i[@class='pkm-icon-envelope-big']"));
    public static final SelenideElement CONVERSATION_Pin = $(byXpath( CONVERSATION_BLOCK+"//button[@class='btn btn-secondary btn-small ng-binding'][contains(.,'Pin')]"));
    public static final SelenideElement CONVERSATION_Unpin = $(byXpath( CONVERSATION_BLOCK+"//button[@class='btn btn-secondary btn-small ng-binding'][contains(.,'Unpin')]"));
   // public static final SelenideElement CONVERSATION_BTN_Back = $(byXpath( CONVERSATION_BLOCK+"//a[@class='btn btn-secondary btn-small'][contains(.,'Back')]"));

    public static final SelenideElement CONVERSATION_EDIT_TITLE = $(byXpath( CONVERSATION_BLOCK+"//*[@class='pkm-icon-edit']"));
    public static final SelenideElement CONVERSATION_TITLE = $( "h3.ng-binding");
    public static final SelenideElement CONVERSATION_FIELD_TITLE = $(byXpath( CONVERSATION_BLOCK+"//input[@name='subject']"));
    public static final SelenideElement CONVERSATION_SAVE_TITLE = $(byXpath( CONVERSATION_BLOCK+"//a[@ng-click='saveEditedTitle()']"));

    //FOLLOWERS
    public static final SelenideElement CONVERSATION_FOLLOWERS_UI = $(byXpath( CONVERSATION_BLOCK+"//ng-switch/span"));
    public static final SelenideElement CONVERSATION_FOLLOWERS_INPUT = $(byXpath( CONVERSATION_BLOCK+"//pkm-followers-picker//ul[@class='search-choices']/li//input"));
    public static final SelenideElement CONVERSATION_FOLLOWERS_ONE_NAME = $(byXpath( CONVERSATION_BLOCK+"//pkm-followers-picker//ul[@class='search-choices']/li[1]/span"));
    public static final SelenideElement CONVERSATION_FOLLOWERS_ONE_DELETE = $(byXpath( CONVERSATION_BLOCK+"//pkm-followers-picker//ul[@class='search-choices']/li[1]//a"));
    public static final ElementsCollection CONVERSATION_FOLLOWERS_LIST = $$(byXpath( CONVERSATION_BLOCK+"//pkm-followers-picker//ul[@class='search-choices']/li[@class='search-choice ng-scope']"));

    public static final SelenideElement CONVERSATION_INVITE_ALERT_TITLE = $(byXpath( CONVERSATION_BLOCK+"//*[@role='alert']/h4"));
    public static final SelenideElement CONVERSATION_INVITE_ALERT_FOLLOWER_EMAIL = $(byXpath( CONVERSATION_BLOCK+"//*[@role='alert']//div/span"));
    public static final SelenideElement CONVERSATION_INVITE_ALERT_DISMISS = $(byXpath( CONVERSATION_BLOCK+"//*[@role='alert']//div/button[text()='DISMISS']"));
    public static final SelenideElement CONVERSATION_INVITE_ALERT_INVITE = $(byXpath( CONVERSATION_BLOCK+"//*[@role='alert']//div/button[text()='+ INVITE']"));



    //TEXT EDITOR AREA
    public static final SelenideElement CONVERSATION_INPUT_TEXT_COLLAPSED = $(byXpath( CONVERSATION_BLOCK+"//input[@placeholder='Write Something']"));
    public static final SelenideElement CONVERSATION_BTN_EMAIL_SETTINGS = $(byXpath( CONVERSATION_BLOCK+""));
    public static final SelenideElement CONVERSATION_TEXT_EDITOR = SIGNATURE_TAB_TEXT_EDITOR;
    public static final SelenideElement CONVERSATION_BTN_POST = $(byXpath( CONVERSATION_BLOCK+"//*[@class='btn btn-primary'][contains(.,'Post')]"));
    public static final SelenideElement CONVERSATION_BTN_TEMPLATE = $(byXpath( CONVERSATION_BLOCK+"//div[@class='editor-holder']/following-sibling::div//i[@class='pkm-icon-template']"));
    public static final SelenideElement CONVERSATION_BTN_ATTACH = $(byXpath( CONVERSATION_BLOCK+"//i[@class='pkm-icon-attach']"));
    public static final SelenideElement CONVERSATION_BTN_CLOUD = $(byXpath( CONVERSATION_BLOCK+"//i[@class='pkm-icon-cloud']"));


    //MSG Controls
    public static final SelenideElement LAST_MESSAGE = $(byXpath("//*[@class='message-list']/li[1]//div[@class='message-holder']"));
    public static final SelenideElement CONVERSATION_MsgTask = $(byXpath( CONVERSATION_BLOCK+"//div[@class='media-body width-float']//span[@class='bubble ng-scope']/i"));
    public static final SelenideElement CONVERSATION_MsgDelete = $(byXpath( CONVERSATION_BLOCK+"//div[@class='media-body width-float']//i[@class='pkm-icon-cancel']"));
    public static final SelenideElement CONVERSATION_MsgTaskIcon = $(byXpath( CONVERSATION_BLOCK+""));
    public static final SelenideElement CONVERSATION_MsgBody = $(byXpath( CONVERSATION_BLOCK+"//div[@class='media-body width-float']"));
    public static final SelenideElement CONVERSATION_MsgText = $(byXpath( CONVERSATION_BLOCK+"//div[@class='media-body width-float']//p"));
    public static final SelenideElement CONVERSATION_MsgTo = $(byXpath( CONVERSATION_BLOCK+"//div[@class='media-body width-float']//dd[1]/span"));
    public static final SelenideElement CONVERSATION_MsgCC = $(byXpath( CONVERSATION_BLOCK+"//div[@class='media-body width-float']//dd[2]/span"));

    public static final SelenideElement CONVERSATION_MsgBCC = $(byXpath( CONVERSATION_BLOCK+"//div[@class='media-body width-float']//dd[3]/span"));
    public static final SelenideElement CONVERSATION_Msg_SUBJECT = $(byXpath( CONVERSATION_BLOCK+"//div[@class='media-body width-float']//dd[4]/span"));

    //MSG area TAB-TEAM

    //MSG area TAB-CLIENT TO fileds
    public static final SelenideElement CONVERSATION_EXTERNAL_INPUT_TO = $(byXpath( CONVERSATION_BLOCK+"//div[@class='external-recipients form-bordered ng-scope']/div[1]//input"));
    public static final SelenideElement CONVERSATION_EXTERNAL_INPUT_CC = $(byXpath( CONVERSATION_BLOCK+"//div[@class='external-recipients form-bordered ng-scope']/div[2]//input"));
    public static final SelenideElement CONVERSATION_EXTERNAL_INPUT_BCC = $(byXpath( CONVERSATION_BLOCK+"//div[@class='external-recipients form-bordered ng-scope']/div[3]//input"));
    public static final SelenideElement CONVERSATION_EXTERNAL_INPUT_SUBJECT = $(byXpath( CONVERSATION_BLOCK+"//div[@class='external-recipients form-bordered ng-scope']/div[4]//input"));


    //threads list
    public static final String CONVERSATION_THREADS_LIST = CONVERSATION_BLOCK+"";

    public static final SelenideElement CONVERSATION_THREAD_Name = $(byXpath( CONVERSATION_BLOCK+"//pkm-conversation-subject//h3"));

    public static final SelenideElement CONVERSATION_EmptyRow = $(byXpath( CONVERSATION_BLOCK+"//pkm-conversations/div/div[2]/div[2]/div[2]"));
    public static final SelenideElement CONVERSATION_01THREAD_Row = $(byXpath( CONVERSATION_BLOCK+"//div[2]/ul/li/span"));
    public static final SelenideElement CONVERSATION_02THREAD_Row = $(byXpath( CONVERSATION_BLOCK+"//li[2]/span"));
    public static final SelenideElement CONVERSATION_03THREAD_Row = $(byXpath( CONVERSATION_BLOCK+"//li[3]/span"));
    public static final SelenideElement CONVERSATION_04THREAD_Row = $(byXpath( CONVERSATION_BLOCK+"//li[4]/span"));
    public static final SelenideElement CONVERSATION_05THREAD_Row = $(byXpath( CONVERSATION_BLOCK+"//li[5]/span"));


    public static final SelenideElement CONVERSATION_NoMessagesAlert = $(byXpath( CONVERSATION_BLOCK+"//*[@class='alert alert-epmty text-center ng-scope']"));
    public static final SelenideElement CONVERSATION_AddFollowerField = $(byXpath( CONVERSATION_BLOCK+"//*[@class='search-field']//*[@type=\"text\"]"));
    public static final SelenideElement CONVERSATION_AddFollowerSearchResult = $(byXpath( CONVERSATION_BLOCK+"//*[@class=\"result-name ng-binding\"]"));
    public static final SelenideElement CONVERSATION_AddInviteToPekama = $(byXpath( CONVERSATION_BLOCK+"//*[@class='btn btn-primary btn-small ng-scope'][contains .,'invite to Pekama' ]"));
    public static final SelenideElement CONVERSATION_AddAsGuest = $(byXpath( CONVERSATION_BLOCK+"//*[@class='btn btn-primary btn-small'][contains .,'add as guest' ]"));

    public static final ElementsCollection MESSAGES_LIST =  $$(byXpath("//*[@class='message-list']/li[1]//div[@class='message-holder']"));
    public static final SelenideElement MESSAGE_FIRST_TEXT =  $(byXpath("//*[@class='message-list']/li[1]//div[@class='message-holder']//*[@class='message-body ng-binding ng-scope']/p"));



}
