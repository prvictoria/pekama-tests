package Page;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class PekamaTeamSettings {
    public static final String teamSettingsProjectTemplatesTab = "/a/settings/team/templates/projects";
    public static final String teamSettingsTaskTemplatesTab = "/a/settings/team/templates/tasks";
    public static final String teamSettingsTaskTemplatesTab_TM = "link=glob:*Trademark*";

    public static final String teamSettingsMessageTemplatesTab = "/a/settings/team/templates/messages";
    public static final String teamSettingsEventTemplatesTab = "/a/settings/team/templates/events";
    public static final String teamSettingsDocumentTemplatesTab = "/a/settings/team/templates/documents";
    public static final String teamSettingsStorageTab = "/a/settings/team/storage";
    public static final String teamSettingsStorageTabConnectBox = "//button[contains(.,'Connect Box')]";
    public static final String teamSettingsStorageTabPekama = "//input[@name='16']";
    public static final String teamSettingsStorageTabBOX = "//input[@name='17']";
    public static final String teamSettingsTabTitle = "//fieldset//*";
    public static final String teamSettingsStorageTabTitle = "//fieldset[contains(.,'Connect your Box.com account:')]";
    public static final String teamSettingsSaveButton = "//button[@submit][contains(.,'Save')]";

    public static final String modalProjectTemplateSelectProjectType = "xpath=//*[@class='ui-select-placeholder text-muted ng-binding']";
    public static final String projectTemplatesRow01 = "//div[@class='cells-row'][1]";
    public static final String templatesTable = "//ul[@class and 'like-table']/li";
    public static final String templateRow = "//div[@ng-click]";

    public static final String templatesIconCopy = "css=i.memobox-icon-docs";
    public static final String templatesIconEdit = "css=i.pkm-icon-edit";
    public static final String templatesIconCancel = "//div[@class='cells-row']//*[@pkm-confirm-click='remove(templateSet)']";
    public static final String templatesRow001 = "//li/div/div";
    public static final String templatesRow002 = "//li[2]/div/div";
    public static final String templatesRow003 = "//li[3]/div/div";
    public static final String projectTypeRow001 = "//div[2]/div/div/div/span/span[2]/span";
}
