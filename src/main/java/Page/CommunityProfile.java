package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CommunityProfile {
    //Team setting
    public static final SelenideElement PROFILE_TEAM_NAME = $(byXpath("//*[@class='editable-title clearfix']//h3"));
    public static final SelenideElement PROFILE_TEAM_NAME_INPUT = $(byXpath("//*[@class='editable-title clearfix']//input"));
    public static final SelenideElement PROFILE_TEAM_NAME_SAVE = $(byXpath("//*[@class='editable-title clearfix']//button[contains(.,'Save')]"));
    public static final SelenideElement PROFILE_TEAM_NAME_CANCEL = $(byXpath("//*[@class='editable-title clearfix']//button[contains(.,'Cancel')]"));

    public static final SelenideElement PROFILE_SERVICES_FORM = $(byXpath(""));
    public static final SelenideElement PROFILE_SERVICE_ROW = $(byXpath(""));
    public static final SelenideElement PROFILE_SERVICE_DELETE = $(byXpath(""));
    public static final SelenideElement PROFILE_SERVICE_EDIT = $(byXpath(""));
    public static final SelenideElement PROFILE_SERVICE_SAVE = $(byXpath("//*[@class='border-container border-container-white']//button[contains(.,'Save')]"));

    public static final SelenideElement PROFILE_SELECT_DEFINING = $(byXpath(""));
    public static final SelenideElement PROFILE_INPUT_DEFINING = $(byXpath(""));
    public static final SelenideElement PROFILE_SELECT_JURSDICTION = $(byXpath(""));
    public static final SelenideElement PROFILE_INPUT_JURSDICTION = $(byXpath(""));
    public static final SelenideElement PROFILE_SELECT_SERVICE = $(byXpath(""));
    public static final SelenideElement PROFILE_INPUT_SERVICE = $(byXpath(""));
    public static final SelenideElement PROFILE_INPUT_PRICE = $(byXpath("//input[@type='number']"));
    public static final SelenideElement PROFILE_BTN_ADD = $(byXpath("//button[contains(.,'+ Add')]")); //disabled="disabled"

    public static final SelenideElement PROFILE_INPUT_DESCRIPTION = $(byXpath("//textarea[@name='competence']"));
    public static final SelenideElement PROFILE_BTN_SAVE_DESCRIPTION = $(byXpath("//*[@class='clearfix ng-scope']//button[contains(.,'Save')]")); //disabled="disabled"

    public static final SelenideElement PROFILE_MEMBERS = $(byXpath(""));
    public static final SelenideElement PROFILE_MEMBERS_COUNT = $(byXpath("//*[@class='members-link ng-binding'][@href]")); //reditect to /a/settings/team/members
    public static final SelenideElement PROFILE_MEMBERS_ROW = $(byXpath(""));

    public static final SelenideElement PROFILE_PLEDGE_1 = $(byXpath("//*[@name='pledge-1']"));
    public static final SelenideElement PROFILE_PLEDGE_2 = $(byXpath("//*[@name='pledge-2']"));
    public static final SelenideElement PROFILE_PLEDGE_3 = $(byXpath("//*[@name='pledge-3']"));
    public static final SelenideElement PROFILE_PLEDGE_4 = $(byXpath("//*[@name='pledge-3']"));
    public static final SelenideElement PROFILE_BTN_YES = $(byXpath("[@value='true']"));
    public static final SelenideElement PROFILE_BTN_NO = $(byXpath("[@value='false']"));

    public static final SelenideElement PROFILE_BTN_INVITE = $(byXpath("//button[contains(.,'+ Invite')]"));
    public static final SelenideElement PROFILE_BTN_AVATAR_DELETE = $(byXpath(""));
    public static final SelenideElement PROFILE_BTN_AVATAR_UPLOAD = $(byXpath(""));
    //Personal setting



}
