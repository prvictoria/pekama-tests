package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class PekamaTeamSettings extends Page {
    public static final SelenideElement SETTINGS_TEAM_ProjectTemplatesTab = $(byXpath("/a/settings/team/templates/projects"));
    public static final SelenideElement SETTINGS_TEAM_TaskTemplatesTab = $(byXpath("/a/settings/team/templates/tasks"));
    public static final SelenideElement SETTINGS_TEAM_TaskTemplatesTab_TM = $(byPartialLinkText("Trademark"));

    public static final SelenideElement SETTINGS_TEAM_MessageTemplatesTab = $(byXpath("/a/settings/team/templates/messages"));
    public static final SelenideElement SETTINGS_TEAM_EventTemplatesTab = $(byXpath("/a/settings/team/templates/events"));
    public static final SelenideElement SETTINGS_TEAM_DocumentTemplatesTab = $(byXpath("/a/settings/team/templates/documents"));
    public static final SelenideElement SETTINGS_TEAM_StorageTab = $(byXpath("/a/settings/team/storage"));
    public static final SelenideElement SETTINGS_TEAM_StorageTabConnectBox = $(byXpath("//button[contains(.,'Connect Box')]"));
    public static final SelenideElement SETTINGS_TEAM_StorageTabPekama = $(byXpath("//input[@name='16']"));
    public static final SelenideElement SETTINGS_TEAM_StorageTabBOX = $(byXpath("//input[@name='17']"));
    public static final SelenideElement SETTINGS_TEAM_TabTitle = $(byXpath("//fieldset//*"));
    public static final SelenideElement SETTINGS_TEAM_StorageTabTitle = $(byXpath("//fieldset[contains(.,'Connect your Box.com account:')]"));
    public static final SelenideElement SETTINGS_TEAM_SaveButton = $(byXpath("//button[@submit][contains(.,'Save')]"));

    public static final SelenideElement SETTINGS_TEAM_TAB_PROFILE = $(byLinkText("Profile"));
    public static final SelenideElement SETTINGS_TEAM_TAB_MEMBERS = $(byLinkText("Members"));
    public static final SelenideElement SETTINGS_TEAM_TAB_VALUES = $(byLinkText("Values"));
    public static final SelenideElement SETTINGS_TEAM_TAB_TASK_TEMPLATES = $(byLinkText("Task Templates"));
    public static final SelenideElement SETTINGS_TEAM_TAB_MESSAGE_TEMPLATES = $(byLinkText("Message Templates"));
    public static final SelenideElement SETTINGS_TEAM_TAB_EVENT_TEMPLATES = $(byLinkText("Event Templates"));
    public static final SelenideElement SETTINGS_TEAM_TAB_DOCUMENT_TEMPLATES = $(byLinkText("Document Templates"));
    public static final SelenideElement SETTINGS_TEAM_TAB_STORAGE = $(byLinkText("Storage"));

    public static final SelenideElement TAB_PROFILE_TITLE = $(byName("title"));
    public static final SelenideElement TAB_PROFILE_CODE = $(byName("code"));
    public static final SelenideElement TAB_PROFILE_SELECT_ORG_TYPE = $(byXpath(""));
    public static final SelenideElement TAB_PROFILE_INPUT_ORG_TYPE = $(byXpath(""));
    public static final SelenideElement TAB_PROFILE_SELECT_ORG_RELATION = $(byXpath(""));
    public static final SelenideElement TAB_PROFILE_INPUT_ORG_RELATION = $(byXpath(""));
    public static final SelenideElement TAB_PROFILE_EMAIL = $(byName("email_local_part"));
    public static final SelenideElement TAB_PROFILE_STREET = $(byName("street_address"));
    public static final SelenideElement TAB_PROFILE_ZIP = $(byName("postal_code"));
    public static final SelenideElement TAB_PROFILE_CITY = $(byName("city"));
    public static final SelenideElement TAB_PROFILE_REGION = $(byName("region"));
    public static final SelenideElement TAB_PROFILE_SELECT_COUNTRY = $(byXpath(""));
    public static final SelenideElement TAB_PROFILE_INPUT_COUNTRY = $(byXpath(""));
    public static final SelenideElement TAB_PROFILE_SELECT_DEFAULT_CHARGES = $(byXpath(""));
    public static final SelenideElement TAB_PROFILE_INPUT_DEFAULT_CHARGES = $(byXpath(""));
    public static final SelenideElement TAB_PROFILE_BTN_SAVE = $(byXpath("//button[text()='Save']"));

    public static final SelenideElement TAB_MEMBERS_BTN_ADD = $(byXpath("//button[text()='Add']"));
    public static String BTN_DELETE_MEMBER = "//div[@class='cells-row']//div[contains(.,'%s')]/following-sibling::div//i";


    public static final SelenideElement TAB_MEMEBERS_ROW = $(byXpath(""));
    public static final SelenideElement TAB_MEMEBERS_DELETE = $(byXpath(""));

    //Values
    public static final SelenideElement SETTINGS_VALUES_DROPDOWN = $(byXpath("//button[./span[@class='caret']]"));

    public static String settingsValueRow = "//div[@class='cells-row' and contains(.,'%s')]";
    public static String settingsValueState = "//div[@class='cells-row' and contains(.,'%s')]/div[2]";
    public static String settingsValueEdit = "//div[@class='cells-row' and contains(.,'%s')]//i[@class='pkm-icon-edit']";
    public static String settingsValueDelete = "//div[@class='cells-row' and contains(.,'%s')]//i[@class='pkm-icon-cancel']";

    public static final SelenideElement SETTINGS_VALUES_ROW = $(byXpath(""));
    public static final SelenideElement SETTINGS_VALUES_ADD = $(byXpath("//button[contains(.,'Add') and ./i]"));
    public static final SelenideElement SETTINGS_VALUES_SORT_BY_ = $(byLinkText(""));
    public static final SelenideElement SETTINGS_VALUES_SORT_BY_VALUE = $(byLinkText("value"));
    public static final SelenideElement SETTINGS_VALUES_SORT_BY_STATE = $(byLinkText("state"));

    //public static SelenideElement SETTINGS_VALUES_ = $(byXpath(String.format(valueRow, "")));


    public static final SelenideElement SETTINGS_DELETE_X = $(byXpath("//i[@class='pkm-icon-cancel']"));


    //Templates
    public static final String projectTemplatesRow01 = "//ul/li[1]//div[@class='cells-row']";
    public static final SelenideElement templateRow = $(byXpath(projectTemplatesRow01+"/div[1]"));

    public static final SelenideElement templatesIconCopy = $("css=i.memobox-icon-docs");
    public static final SelenideElement templatesIconEdit = $("css=i.pkm-icon-edit");
    public static final SelenideElement templatesIconCancel = $(byXpath("//div[@class='cells-row']//*[@pkm-confirm-click='remove(templateSet)']"));
    public static final SelenideElement templatesRow001 = $(byXpath("//li/div/div"));
    public static final SelenideElement templatesRow002 = $(byXpath("//li[2]/div/div"));
    public static final SelenideElement templatesRow003 = $(byXpath("//li[3]/div/div"));
    public static final SelenideElement projectTypeRow001 = $(byXpath("//div[2]/div/div/div/span/span[2]/span"));
    public static final SelenideElement BTN_TEMPLATE_ADD_IN_1st_ROW = $(byXpath("//*[@class='inner']//button[contains(.,'Add') and ./i]"));
    public static final SelenideElement TEMPLATES_TEXT_FIELD = $(byXpath("//textarea"));
}
