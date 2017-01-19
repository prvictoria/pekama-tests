package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaConversationProject {
    public static final SelenideElement conversationProjectBtnTeam = $("button.team-button");
    public static final SelenideElement conversationProjectBtnClient = $("button.external-email-button");
    public static final SelenideElement conversationProjectBtnNew = $(byXpath("//pkm-conversations//button[contains(.,'New')]"));
    public static final SelenideElement conversationProjectNoThreads = $(byXpath("//pkm-conversations//div[starts-with(@class, 'alert alert-empty')]"));
    public static final SelenideElement conversationProjectBtnPost = $(byXpath("//a[@class='btn btn-primary'][contains(.,'Post')]"));
    public static final SelenideElement conversationProjectBtnTemplate = $(byXpath("//i[@class='pkm-icon-template']"));
    public static final SelenideElement conversationProjectBtnAttach = $(byXpath("//i[@class='pkm-icon-attach']"));
    public static final SelenideElement conversationProjectBtnCloud = $(byXpath("//i[@class='pkm-icon-cloud']"));
    public static final SelenideElement conversationProjectList = $(byXpath("//ul[@class='conversation-list ng-scope']/li"));
    public static final SelenideElement conversationProjectBtnBack = $(byXpath("//a[@class='btn btn-secondary btn-small'][contains(.,'Back')]"));
    public static final SelenideElement conversationProjectPin = $(byXpath("//button[@class='btn btn-secondary btn-small ng-binding'][contains(.,'Pin')]"));
    public static final SelenideElement conversationProjectUnpin = $(byXpath("//button[@class='btn btn-secondary btn-small ng-binding'][contains(.,'Unpin')]"));
    public static final SelenideElement conversationProjectBtnParameters = $(byXpath("//i[@class='pkm-icon-envelope-big']"));
    public static final SelenideElement conversationProjectValidation = $(byXpath("//div[@ng-if='validationMessage']"));
    public static final SelenideElement conversationProjectInputTo = $(byXpath("//div[@class='external-recipients form-bordered ng-scope']/div[1]//input"));
    public static final SelenideElement conversationProjectInputCC = $(byXpath("//div[@class='external-recipients form-bordered ng-scope']/div[2]//input"));
    public static final SelenideElement conversationProjectInputBCC = $(byXpath("//div[@class='external-recipients form-bordered ng-scope']/div[3]//input"));
    public static final SelenideElement conversationProjectInputSubject = $(byXpath("//div[@class='external-recipients form-bordered ng-scope']/div[4]//input"));
    public static final SelenideElement conversationProjectMsgBody = $(byXpath("//div[@class='media-body width-float']"));
    public static final SelenideElement conversationProjectMsgTo = $(byXpath("//div[@class='media-body width-float']//dd[1]/span"));
    public static final SelenideElement conversationProjectMsgCC = $(byXpath("//div[@class='media-body width-float']//dd[2]/span"));
    public static final SelenideElement conversationProjectMsgTask = $(byXpath("//div[@class='media-body width-float']//span[@class='bubble ng-scope']/i"));
    public static final SelenideElement conversationProjectMsgBCC = $(byXpath("//div[@class='media-body width-float']//dd[3]/span"));
    public static final SelenideElement conversationProjectMsgDelete = $(byXpath("//div[@class='media-body width-float']//i[@class='pkm-icon-cancel']"));
    public static final SelenideElement conversationErrorNoRecipients = $(byXpath("External conversation message should have recipients"));
    public static final SelenideElement conversationProjectInputText = $(byXpath("//input[@placeholder='Write Something']"));
    public static final SelenideElement conversationProjectThreadName = $(byXpath("//pkm-conversation-subject//h3"));
    public static final SelenideElement conversationProject = $(byXpath(""));

    public static final SelenideElement conversationEmptyRow = $(byXpath("//pkm-conversations/div/div[2]/div[2]/div[2]"));
    public static final SelenideElement conversation01ThreadRow = $(byXpath("//div[2]/ul/li/span"));
    public static final SelenideElement conversation02ThreadRow = $(byXpath("//li[2]/span"));
    public static final SelenideElement conversation03ThreadRow = $(byXpath("//li[3]/span"));
    public static final SelenideElement conversation04ThreadRow = $(byXpath("//li[4]/span"));
    public static final SelenideElement conversation05ThreadRow = $(byXpath("//li[5]/span"));

    public static final SelenideElement conversationNewButton = $(byXpath("//pkm-conversations/div/div/button"));
    public static final SelenideElement projectThreadEditTitleIcon = $(byXpath("//*[@ng-click='startTitleEditing  ']//*[@class='memobox-icon-edit']"));
    public static final SelenideElement projectThreadTitle = $(byXpath("css=h3.ng-binding"));
    public static final SelenideElement projectThread01Follower = $(byXpath("//div/div/ul/li/span/span[2]"));
    public static final SelenideElement projectThread02Follower = $(byXpath("//li[2]/span/span"));
    public static final SelenideElement projectThread03Follower = $(byXpath("//li[3]/span/span"));
    public static final SelenideElement projectThread04Follower = $(byXpath("//li[4]/span/span"));
    public static final SelenideElement projectThread05Follower = $(byXpath("//li[5]/span/span"));
    public static final SelenideElement projectThreadNoMessagesAlert = $(byXpath("//*[@class='alert alert-epmty text-center ng-scope']"));
    public static final SelenideElement projectThreadAddFollowerField = $(byXpath("//*[@class='search-field']//*[@type=\"text\"]"));
    public static final SelenideElement projectThreadAddFollowerSearchResult = $(byXpath("//*[@class=\"result-name ng-binding\"]"));
    public static final SelenideElement projectThreadAddInviteToPekama = $(byXpath("//*[@class='btn btn-primary btn-small ng-scope'][contains .,'invite to Pekama' ]"));
    public static final SelenideElement projectThreadAddAsGuest = $(byXpath("//*[@class='btn btn-primary btn-small'][contains .,'add as guest' ]"));
    public static final SelenideElement projectThreadBack = $(byLinkText("Back"));
    public static final SelenideElement projectThreadShrinkMsgInput = $(byXpath("//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div/pkm-conversations/div/div[3]/pkm-conversation/div[2]/div/div/input"));
    public static final SelenideElement projectThreadUploadFile = $(byXpath(""));
    public static final SelenideElement projectThreadCloud = $(byXpath(""));
    public static final SelenideElement projectThreadMsgTemplate = $("css=i.fa.fa-file-text-o");
    public static final SelenideElement projectThreadMsgPostButton = $(byXpath(""));
    public static final SelenideElement projectThreadEmailParameters = $(byXpath("css=div.action-buttons.clearfix-row > div.pull-right > button.btn.btn-default"));
    public static final SelenideElement projectThreadMsgTaskIcon = $("css=i.memobox-icon-ok");
    public static final SelenideElement projectThreadMsgDelete = $("css=i.memobox-icon-delete-small");

    public static final SelenideElement conversationTeamChatTab = $("css=button.team-button");
    public static final SelenideElement conversationExternalTab = $("css=button.external-email-button");
    public static final SelenideElement conversationActiveCenterTab = $(byXpath("//pkm-conversation/div/button"));
    public static final SelenideElement conversation = $(byXpath(""));

}
