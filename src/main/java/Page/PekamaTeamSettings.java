package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byPartialLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class PekamaTeamSettings extends Page {
    public static final SelenideElement teamSettingsProjectTemplatesTab = $(byXpath("/a/settings/team/templates/projects"));
    public static final SelenideElement teamSettingsTaskTemplatesTab = $(byXpath("/a/settings/team/templates/tasks"));
    public static final SelenideElement teamSettingsTaskTemplatesTab_TM = $(byPartialLinkText("Trademark"));

    public static final SelenideElement teamSettingsMessageTemplatesTab = $(byXpath("/a/settings/team/templates/messages"));
    public static final SelenideElement teamSettingsEventTemplatesTab = $(byXpath("/a/settings/team/templates/events"));
    public static final SelenideElement teamSettingsDocumentTemplatesTab = $(byXpath("/a/settings/team/templates/documents"));
    public static final SelenideElement teamSettingsStorageTab = $(byXpath("/a/settings/team/storage"));
    public static final SelenideElement teamSettingsStorageTabConnectBox = $(byXpath("//button[contains(.,'Connect Box')]"));
    public static final SelenideElement teamSettingsStorageTabPekama = $(byXpath("//input[@name='16']"));
    public static final SelenideElement teamSettingsStorageTabBOX = $(byXpath("//input[@name='17']"));
    public static final SelenideElement teamSettingsTabTitle = $(byXpath("//fieldset//*"));
    public static final SelenideElement teamSettingsStorageTabTitle = $(byXpath("//fieldset[contains(.,'Connect your Box.com account:')]"));
    public static final SelenideElement teamSettingsSaveButton = $(byXpath("//button[@submit][contains(.,'Save')]"));

    public static final SelenideElement modalProjectTemplateSelectProjectType = $(byXpath("xpath=//*[@class='ui-select-placeholder text-muted ng-binding']"));
    public static final SelenideElement projectTemplatesRow01 = $(byXpath("//div[@class='cells-row'][1]"));
    public static final SelenideElement templatesTable = $(byXpath("//ul[@class and 'like-table']/li"));
    public static final SelenideElement templateRow = $(byXpath("//div[@ng-click]"));

    public static final SelenideElement templatesIconCopy = $("css=i.memobox-icon-docs");
    public static final SelenideElement templatesIconEdit = $("css=i.pkm-icon-edit");
    public static final SelenideElement templatesIconCancel = $(byXpath("//div[@class='cells-row']//*[@pkm-confirm-click='remove(templateSet)']"));
    public static final SelenideElement templatesRow001 = $(byXpath("//li/div/div"));
    public static final SelenideElement templatesRow002 = $(byXpath("//li[2]/div/div"));
    public static final SelenideElement templatesRow003 = $(byXpath("//li[3]/div/div"));
    public static final SelenideElement projectTypeRow001 = $(byXpath("//div[2]/div/div/div/span/span[2]/span"));
}
