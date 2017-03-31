package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaProject extends Page {

    public static final SelenideElement PROJECT_FULL_NAME = $(byXpath("//*[@class='project-heading']//h2"));
    public static final SelenideElement PROJECT_BTN_CLONE = $(byXpath("//button[@pkm-confirm-click='copy()']"));
    public static final SelenideElement PROJECT_BTN_DELETE = $(byXpath("//*[@class='project-heading']//button[contains(.,'Delete')]"));

    public static final SelenideElement projectButtonPlus = $(byXpath("//div[@class='project-heading']/button"));
    public static final SelenideElement projectPlusNewEvent = $(byLinkText("New Event"));
    public static final SelenideElement projectPlusNewConversation = $(byLinkText("New Conversation"));
    public static final SelenideElement projectPlusNewTask = $(byLinkText("New Task"));
    public static final SelenideElement projectPlusNewDocument = $(byLinkText("New Document"));
    public static final SelenideElement projectPlusNewFinancial = $(byLinkText("New Charge"));
    public static final SelenideElement projectPlusNewNumber = $(byLinkText("New Number"));
    public static final SelenideElement projectPlusNewContact = $(byLinkText("New Contact"));
    public static final SelenideElement projectAllCheckbox = $("i.pkm-icon-checkbox");
    public static final SelenideElement projectAllCheckboxFiles = $(byXpath("//div[@class='checkbox-holder fancy main-checkbox pull-left']//input"));

    public static final SelenideElement TIMELINE__Line = $(".slider-selection");
    public static final SelenideElement TIMELINE_DeleteEvent = $(byXpath("//*[@id='timeline']//a[@class='delete ng-scope']"));
    public static final SelenideElement TIMELINE_EditEvent = $(byXpath("//*[@id='timeline']//a[@class='edit ng-scope']"));
    public static final SelenideElement TIMELINE_ArrowLeft = $(byXpath(""));
    public static final SelenideElement TIMELINE_ArrowRight = $(byXpath(""));
    public static final SelenideElement BTN_HIDE_TIMELINE = $(byXpath("//button[text()='hide timeline']"));
    public static final SelenideElement BTN_SHOW_TIMELINE = $(byXpath("//button[text()='show timeline']"));
    public static final SelenideElement TIMELINE_Line = $("css=.slider-selection");
    public static final SelenideElement TIMELINE_CheckboxLessImportant = $(byXpath("//div[@class='check-filters-holder form-inline']/div[1]//span"));
    public static final SelenideElement TIMELINE_CheckboxAutoPopulated = $(byXpath("//div[@class='check-filters-holder form-inline']/div[2]//span"));
    public static final SelenideElement TIMELINE_CheckboxManuallyAdded = $(byXpath("//div[@class='check-filters-holder form-inline']/div[3]//span"));
    public static final SelenideElement TIMELINE_CheckboxShrinkedEventsView = $(byXpath("//div[@class='check-filters-holder form-inline']/div[4]//span"));
    public static final SelenideElement TIMELINE_EventToday = $(byXpath("//ul[@id='timeline']/li/a/span"));

    public static final String CONTROLS_ROW = "//div[@class='clearfix-row zone-controls-holder']";
    public static final SelenideElement TAB_CONTROL_DELETE = $(byXpath("//button[@class='btn-link' and contains(.,'Delete')]"));
    public static final SelenideElement TAB_CONTROL_XERO = $(byXpath("//button[@class='btn-link' and contains(.,'To Xero')]"));
    public static final SelenideElement TAB_CONTROL_DOWNLOAD = $(byXpath(""));
    public static final SelenideElement TAB_CONTROL_ADD = $(byXpath(CONTROLS_ROW+"//button[contains(.,'add')]"));
    public static final SelenideElement TAB_CONTROL_SELECT_ALL = $("i.pkm-icon-checkbox");
    public static final SelenideElement TAB_CONTROL_1 = $(byXpath(CONTROLS_ROW+""));
    public static final SelenideElement TAB_CONTROL_2 = $(byXpath(CONTROLS_ROW+""));
    public static final SelenideElement TAB_CONTROL_3 = $(byXpath(CONTROLS_ROW+""));

    public static final SelenideElement TAB_CHARGES_ADD = TAB_CONTROL_ADD;
    public static final SelenideElement TAB_CHARGES_CHECKBOX_ALL = $(byXpath(CONTROLS_ROW+""));
    public static final SelenideElement PROJECT_TAB_INFO = $(byXpath("//i[@class='icon pkm-icon-info-square']"));
    public static final SelenideElement PROJECT_TAB_CONTACTS = $(byXpath("//i[@class='icon pkm-icon-users-square']"));
    public static final SelenideElement PROJECT_TAB_DOCS = $(byXpath("//i[@class='icon pkm-icon-documents-square']"));
    public static final SelenideElement PROJECT_TAB_TASKS = $(byXpath("//i[@class='icon pkm-icon-tasks-square']"));
    public static final SelenideElement PROJECT_TAB_CHARGES = $(byXpath("//i[@class='icon pkm-icon-finances-square']"));
    public static final SelenideElement PROJECT_TAB_FAMILY = $(byXpath("//i[@class='icon pkm-icon-family-square']"));
    public static final SelenideElement PROJECT_TAB_SEARCH = $(byXpath("//i[@class='icon pkm-icon-search-square']"));

    public static final SelenideElement TAB_INFO_ProjectTitle = $(byXpath("//pkm-editable-title//h4"));
    public static final SelenideElement TAB_INFO_TitleEditButton = $(byXpath("//*[@class='info-section project-details ng-scope']//button[contains(.,'Edit')]"));
    public static final SelenideElement TAB_INFO_TitleInput = $(byXpath("//input[@name='title']"));
    public static final SelenideElement TAB_INFO_TitleSave = $(byXpath("//button[contains(.,'Save')]"));
    public static final SelenideElement TAB_INFO_TitleCancel = $(byXpath("//button[contains(.,'Cancel')]"));

    public static final String TAB_INFO_VALUES = "//*[@class='details-list clearfix']";
    public static final SelenideElement TAB_INFO_PROJECT_TYPE = $(byXpath(TAB_INFO_VALUES+"//strong[contains(.,'Project type')]/following-sibling::span"));
    public static final SelenideElement TAB_INFO_SELECT_Defining = $(byXpath(TAB_INFO_VALUES+"//strong[text()='Country']/following-sibling::pkm-values-dropdown"));
    public static final SelenideElement TAB_INFO_INPUT_Defining = $(byXpath(TAB_INFO_VALUES+"//strong[text()='Country']/following-sibling::pkm-values-dropdown//input[@type='search']"));
    public static final SelenideElement TAB_INFO_SELECT_Type = $(byXpath(TAB_INFO_VALUES+"//strong[text()='Type']/following-sibling::pkm-values-dropdown"));
    public static final SelenideElement TAB_INFO_INPUT_Type = $(byXpath(TAB_INFO_VALUES+"//strong[text()='Type']/following-sibling::pkm-values-dropdown//input[@type='search']"));
    public static final SelenideElement TAB_INFO_SELECT_SubType = $(byXpath(TAB_INFO_VALUES+"//strong[text()='Sub Type ']/following-sibling::pkm-values-dropdown"));
    public static final SelenideElement TAB_INFO_INPUT_SubType = $(byXpath(TAB_INFO_VALUES+"//strong[text()='Sub Type ']/following-sibling::pkm-values-dropdown//input[@type='search']"));


    public static final SelenideElement TAB_INFO_Notes = $(byXpath(""));
    public static final SelenideElement TAB_INFO_Statuses = $(byXpath(""));
    public static final SelenideElement TAB_INFO_StatusesSearchButton = $("css=button.info-status-seach-btn");
    public static final SelenideElement TAB_INFO_StatusesSearchInupt = $(byId("searchStatusInput"));
    public static final SelenideElement TAB_INFO_Statuses1Status = $(byXpath("//ul//*[@id]/span"));
    public static final SelenideElement TAB_INFO_Statuses2Status = $(byXpath("//ul//*[@id][2]/span"));


    public static final SelenideElement TAB_INFO_NumberAdd = $(byXpath("//button[contains(.,'Add')]"));
    public static final SelenideElement TAB_INFO_NumberRow01Type = $(byXpath("//li[@class='like-tr ng-scope']/div/div[1]"));
    public static final SelenideElement TAB_INFO_NumberRow01Number = $(byXpath("//li[@class='like-tr ng-scope']/div/div/span"));
    public static final SelenideElement TAB_INFO_NumberRow01Edit = $(byXpath("//pkm-reference-numbers/ul/li[1]//i[@class='icon pkm-icon-edit']"));
    public static final SelenideElement TAB_INFO_NumberRow01Delete = $(byXpath("//pkm-reference-numbers/ul/li[1]//i[@class='pkm-icon-cancel icon']"));
    public static final SelenideElement TAB_INFO_NumberRow01Collapse = $(byXpath("//pkm-reference-numbers/ul/li[1]//i[@class='icon pkm-icon-up-open']"));
    public static final SelenideElement TAB_INFO_NumberRow02Number = $(byXpath(""));
    public static final SelenideElement TAB_INFO_NumberRow02Edit = $(byXpath(""));
    public static final SelenideElement TAB_INFO_NumberRow02Type = $(byXpath(""));

    public static final SelenideElement TAB_INFO_NumberEdit = $(byXpath("//*[@class='more-item numbers ng-scope']//ul/li[contains(.,'122')]//a[@class='edit ng-scope']"));
    public static final SelenideElement TAB_INFO_NumberDelete = $(byXpath("//*[@class='more-item numbers ng-scope']//ul/li[contains(.,'122')]//i[@class='pkm-icon-cancel icon']"));

    public static final SelenideElement TAB_INFO_NumberNewSelect = $(byXpath("//div[@name='reference_type']/div/span/span[1]"));
    public static final SelenideElement TAB_INFO_NumberNewField = $(byXpath("//div[@name='reference_type']/input[1]"));
    public static final SelenideElement TAB_INFO_NumberReferenceField = $(byXpath("//input[@name='reference_number']"));
    public static final SelenideElement TAB_INFO_Number_EDIT_REFERENCE_TYPE_SELECT = $(byXpath("//*[@class='subrow collapsable ng-scope']//pkm-values-dropdown//span[starts-with(@class, 'btn btn-default form-control ui-select-toggle')]"));
    public static final SelenideElement TAB_INFO_Number_EDIT_REFERENCE_TYPE_INPUT = $(byXpath("//*[@class='subrow collapsable ng-scope']//pkm-values-dropdown//input[@type='search']"));
    public static final SelenideElement TAB_INFO_Number_EDIT_REFERENCE_VALUE_INPUT = $(byXpath("//*[@class='subrow collapsable ng-scope']//div[@class='form-group ng-scope']/input"));
    public static final SelenideElement TAB_INFO_Number_EDIT_REFERENCE_BTN_SAVE = $(byXpath("//*[@class='subrow collapsable ng-scope']//button"));


    public static final String TAB_INFO_Classes = "//*[@class='more-item classes ng-scope']";
    public static final SelenideElement TAB_INFO_ClassesAdd = $(byXpath(TAB_INFO_Classes +"//button[contains(.,'Add')]"));
    public static final SelenideElement TAB_INFO_ClassRow01Number = $(byXpath("//tbody/tr/td[1]"));
    public static final SelenideElement TAB_INFO_ClassRow01Type = $(byXpath("//tbody/tr/td[2]"));
    public static final SelenideElement TAB_INFO_ClassRow01Description = $(byXpath("//tbody/tr/td[3]"));
    public static final SelenideElement TAB_INFO_ClassRow01Edit = $(byXpath("//tbody/tr/td[4]/a[@class='edit ng-scope']"));
    public static final SelenideElement TAB_INFO_ClassRow01delete = $(byXpath(TAB_INFO_Classes +"//table//tr[1]//*[@class='pkm-icon-cancel icon']"));

    //ENVIRONMENT_COMMUNITY
    public static final SelenideElement TAB_INFO_COMMUNITY = $(byXpath("//pkm-community-projects"));
    public static final SelenideElement TAB_INFO_COMMUNITY_TITLE = $(byXpath("//pkm-community-projects//h4"));
    public static final SelenideElement TAB_INFO_COMMUNITY_BTN_START_NEW = $(byXpath("//pkm-community-projects//button[contains(.,'+ START NEW')]"));
    public static final ElementsCollection TAB_INFO_COMMUNITY_CASES_LIST = $$(byXpath("//pkm-community-projects//div[@class='request-quote-list']/div"));
    public static final SelenideElement TAB_INFO_COMMUNITY_CASE_NAME = $(byXpath("//div[@class='name']/*"));
    public static final SelenideElement TAB_INFO_COMMUNITY_CASE_TYPE = $(byXpath("//div[@class='patent']/*"));
    public static final SelenideElement TAB_INFO_COMMUNITY_CASE_ACTION = $(byXpath("//pkm-community-projects//*[starts-with(@class, 'status')]/button"));
    public static final SelenideElement TAB_INFO_COMMUNITY_CASE_STATUS = $(byXpath("//pkm-community-projects//*[starts-with(@class, 'status')]//span"));
    public static final SelenideElement TAB_INFO_COMMUNITY_CASE_ROW = $(byXpath("//pkm-community-projects//div[@class='request-quote-list ng-scope']"));

    public static final SelenideElement projectTabContacts_Teams = $(byXpath("//pkm-collaborators"));
    public static final SelenideElement projectTabContacts_AddCollaborator = $(byXpath("//pkm-collaborators//button[contains(.,'Add')]"));
    public static final SelenideElement projectTabContacts_TeamsTitle = $(byXpath("//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/pkm-collaborators/h4"));
    public static final SelenideElement projectTabContacts_TeamRow = $(byXpath("//tr[@ng-repeat='collaborator in collaborators']"));
    public static final SelenideElement projectTabContacts_TeamName = $(byXpath("/td[1]"));
    public static final SelenideElement projectTabContacts_TeamStatus = $(byXpath("/td[2]"));
    public static final SelenideElement projectTabContacts_TeamEdit = $(byXpath("//pkm-collaborators//tbody//tr[2]//a[1]"));
    public static final SelenideElement projectTabContacts_TeamDelete = $(byXpath("//pkm-collaborators//tbody//tr[2]//a[2]"));
    public static final String OWNER = "OWNER";
    public static final String ADMIN = "ADMIN";
    public static final String COLLABORATOR = "COLLABORATOR";
    public static final String VIEWER = "VIEWER";

    public static final String TAB_CONTACTS = "//pkm-contact-relations/ul"; //table
    public static final String TAB_CONTACTS_SELECTION_ROW = TAB_CONTACTS+"/li[last()]";
    public static final String TAB_CONTACTS_FIRST_ROW = TAB_CONTACTS+"/li[1]";

    public static final SelenideElement projectTabContacts_RelationNoData = $(byXpath("//pkm-contact-relations/div[2]/div/div[2]"));
    public static final SelenideElement projectTabContacts_AddSelectContact = $(byXpath("//div[@name='contact']/div/span"));
    public static final SelenideElement projectTabContacts_AddContactInput = $(byXpath("//input[@type='search']"));
    public static final SelenideElement projectTabContacts_AddSelectRelation = $(byXpath("//div[@name='relation']/div/span"));
    public static final SelenideElement projectTabContacts_AddRelationInput = $(byXpath("//div[@name='relation']/input[1]"));
    public static final SelenideElement projectTabContacts_AddContactButton = $(byXpath("//pkm-contact-relations//button[contains(.,'Add')]"));
    public static final SelenideElement projectTabContacts_CREATE_NEW_CONTACT = $(byXpath("//pkm-values-dropdown//span[text()='Create new']"));

    public static final SelenideElement projectTabContacts_ContactRow = $(byXpath("//li[starts-with(@ng-repeat, 'contactRelation')]"));
    public static final SelenideElement projectTabContacts_ContactIconPerson = $(byXpath("css=i.pkm-icon-user"));
    public static final SelenideElement projectTabContacts_ContactIconCompany = $(byXpath("css=i.pkm-icon-building"));
    public static final SelenideElement projectTabContacts_ContactName = $(byXpath(TAB_CONTACTS_FIRST_ROW+"/div/div[1]"));
    public static final SelenideElement projectTabContacts_ContactRelation = $(byXpath(TAB_CONTACTS_FIRST_ROW+"/div/div[2]"));
    public static final SelenideElement projectTabContacts_Contact_BTN_COLLABORATE = $(byXpath(TAB_CONTACTS_FIRST_ROW+"//button[text()='+COLLABORATE']"));
    public static final SelenideElement projectTabContacts_ContactDrop = $(byXpath(TAB_CONTACTS_FIRST_ROW+"//a[@class='first']"));
    public static final SelenideElement projectTabContacts_ContactEdit = $(byXpath(TAB_CONTACTS_FIRST_ROW+"//a[@class='edit']"));
    public static final SelenideElement projectTabContacts_ContactDelete = $(byXpath(TAB_CONTACTS_FIRST_ROW+"//a[@class='cancel']"));

    public static final SelenideElement projectTabContacts_Form = $(byXpath(""));
    public static final SelenideElement projectTabContacts_FormRelationSelect = $(byXpath("//select[@name='relation']"));
    public static final SelenideElement projectTabContacts_FormOwnership = $(byXpath("//input[@name='ownership_percentage']"));
    public static final SelenideElement projectTabContacts_FormTypeSelect = $(byXpath("//select[@name='contact.type']"));
    public static final SelenideElement projectTabContacts_FormEntity = $(byXpath("//input[@name='contact.legal_entity_name']"));
    public static final SelenideElement projectTabContacts_FormFirstName = $(byXpath("//input[@name='contact.first_name']"));
    public static final SelenideElement projectTabContacts_FormLastName = $(byXpath("//input[@name='contact.last_name']"));
    public static final SelenideElement projectTabContacts_FormCompanySelect = $(byXpath("//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/div/pkm-contact-relations/ul/li/div[2]/div/form/div/div[2]/pkm-contact-fields/div/div[3]/div/div/div/span"));
    public static final SelenideElement projectTabContacts_FormCompanyInput = $(byXpath("//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/div/pkm-contact-relations/ul/li/div[2]/div/form/div/div[2]/pkm-contact-fields/div/div[3]/div/div/input"));
    public static final SelenideElement projectTabContacts_FormEmail = $(byXpath("//input[@name='contact.email']"));
    public static final SelenideElement projectTabContacts_FormPhone = $(byXpath("//input[@name='contact.phone_number']"));
    public static final SelenideElement projectTabContacts_FormFax = $(byXpath("//input[@name='contact.fax_number']"));
    public static final SelenideElement projectTabContacts_FormMobile = $(byXpath("//input[@name='contact.cellphone_number']"));
    public static final SelenideElement projectTabContacts_FormStreet = $(byXpath("//input[@name='contact.street_address']"));
    public static final SelenideElement projectTabContacts_FormPostal = $(byXpath("//input[@name='contact.postal_code']"));
    public static final SelenideElement projectTabContacts_FormCity = $(byXpath("//input[@name='contact.city']"));
    public static final SelenideElement projectTabContacts_FormRegion = $(byXpath("//input[@name='contact.region']"));
    public static final SelenideElement projectTabContacts_FormCountrySelect = $(byXpath("//div[@name='contact.country']//div/span"));
    public static final SelenideElement projectTabContacts_FormCountryInput = $(byXpath("//div[@name='contact.country']//input"));
    //TAB DOCS
    public static final SelenideElement TAB_DOCS_BTN_ADD = $(byId("file-controls"));
    public static final SelenideElement TAB_DOC_ADD_FOLDER = $(byLinkText("Add Folder"));
    public static final SelenideElement TAB_DOC_NEW_DOCUMENT = $(byLinkText("New document"));
    public static final SelenideElement LINK_DELETE = $(byLinkText("Delete"));
    public static final SelenideElement TAB_DOCS = $(byXpath(""));

    public static final SelenideElement projectTabDocs_Row = $(byXpath("//div[@class='items-list files ng-scope angular-ui-tree']/ol/li"));
    public static final SelenideElement projectTabDocs_RowName = $(byXpath("//div[@class='file-info-holder']/a[1]"));

    public static String TAB_DOCS_FILES_SELECT_ROW = "//ul[@class='doc-list-table' and contains(.,'%s')]//input[@type='checkbox']";
    public static String TAB_DOCS_FILES_EXPAND_FOLDER = "//ul[@class='doc-list-table' and contains(.,'%s')]//ng-pluralize";
    public static String TAB_DOCS_FILES_EXPAND_FILE = "//ul[@class='doc-list-table' and contains(.,'%s')]//*[@class='link-file ng-scope']";

    public static String TAB_DOCS_FILES_MENU = "//ul[@class='doc-list-table' and contains(.,'%s')]//div[@id]";
    public static String TAB_DOCS_FILES_MENU_OPEN = TAB_DOCS_FILES_MENU+"/button";
    public static String TAB_DOCS_FILES_MENU_DOWNLOAD = TAB_DOCS_FILES_MENU+"//a[contains(.,'Download')]";
    public static String TAB_DOCS_FILES_MENU_RENAME = TAB_DOCS_FILES_MENU+"//a[contains(.,'Rename')]";
    public static String TAB_DOCS_FILES_MENU_DELETE = TAB_DOCS_FILES_MENU+"//a[contains(.,'Delete')]";
    public static String TAB_DOCS_FILES_MENU_UPLOAD = TAB_DOCS_FILES_MENU+"//a[contains(.,'Upload New Version')]";
    public static String TAB_DOCS_FILES_MENU_DOWNLOAD_ZIP = TAB_DOCS_FILES_MENU+"//a[contains(.,'Download ZIP')]";
    public static String TAB_DOCS_FILES_MENU_ADD_SUBFOLDER = TAB_DOCS_FILES_MENU+"//a[contains(.,'Add Sub Folder')]";

    //SIMPLE IN RWO CONTRLOS
        public static final SelenideElement TAB_DOCS_FILE_INPUT_NAME_IN_ROW = $(byXpath("//pkm-simple-files-editable-title//input[@name='name']"));
    public static final SelenideElement TAB_DOCS_FILE_SAVE_IN_ROW = $(byXpath("//pkm-simple-files-editable-title//a[contains(.,'Save')]"));

    //EXPANDED CONTROLS
    public static final SelenideElement TAB_DOCS_FILE_RENAME = $(byXpath("//pkm-files-editable-title//a[contains(.,'Rename')]"));
    public static final SelenideElement TAB_DOCS_FILE_INPUT_NAME = $(byXpath("//pkm-files-editable-title//input[@name='name']"));
    public static final SelenideElement TAB_DOCS_FILE_SAVE = $(byXpath("//pkm-files-editable-title//a[contains(.,'Save')]"));
    public static final SelenideElement TAB_DOCS_FILE_DELETE = $(byXpath("//pkm-files-editable-title//a[contains(.,'Delete')]"));

    //TAB TASKS
    public static final String TASKS_ORDER = "";
    public static final String TASKS_ORDER_DUE_DATE = "Due date";
    public static final String TASKS_ORDER_LAST_CREATED = "Last created";
    public static final String TASKS_ORDER_LAST_MODIFIED = "Last modified";
    public static final String TASKS_ORDER_TIRLE = "Title";
    public static final String TASKS_ORDER_ASSIGNEE = "Assignee";
    public static final SelenideElement TAB_TASKS_ACTIVE = $(byXpath(CONTROLS_ROW+"//button[contains(.,'Active')]"));
    public static final SelenideElement TAB_TASKS_ALL = $(byXpath(CONTROLS_ROW+"//button[contains(.,'All')]"));
    public static SelenideElement TAB_TASKS_ACTUAL_ORDER = $(byXpath(CONTROLS_ROW+"//a[contains(.,'"+TASKS_ORDER+"')]"));        //order value
    public static final SelenideElement TAB_TASKS_ADD = $(byXpath(CONTROLS_ROW+"//button[contains(.,'add')]"));
    public static final SelenideElement TAB_TASKS_NEW_TASK = $(byLinkText("Create task"));
    public static final SelenideElement TAB_TASKS_DEPLOY_TASK = $(byLinkText("Deploy template"));

    public static final SelenideElement TAB_TASKS_BTN_DELETE = $(byXpath("//button[@class='btn-link' and contains(.,'Delete')]"));
    public static final SelenideElement TAB_TASKS_BTN_UPDATE_STATUS = $(byXpath("//button[@class='btn-link' and contains(.,'Update Status')]"));
    public static final SelenideElement TAB_TASKS_BTN_UPDATE_IMPORTANCE = $(byXpath("//button[@class='btn-link' and contains(.,'Update Importance')]"));
    public static final SelenideElement TAB_TASKS_BTN_UPDATE_ASSIGNOR = $(byXpath("//button[@class='btn-link' and contains(.,'Update Assignor')]"));
    public static final SelenideElement TAB_TASKS_BTN_UPDATE_ASSIGNEE = $(byXpath("//button[@class='btn-link' and contains(.,'Update Assignee')]"));
    //task row
    public static final String TASKS_LIST = "//*[@class='items-list with-caret ng-scope tasks']";
    public static final String TASKS_ROW = "/div";
    public static final ElementsCollection TASKS_ROWS = $$(byXpath(TASKS_LIST+TASKS_ROW));
    public static final SelenideElement TASKS_NAME_IN_FIRST_ROW = $(byXpath(TASKS_LIST+TASKS_ROW+"[1]"+"//*[@class='text']//a[@href]"));
    public static final SelenideElement TASKS_PRIORITY_IN_FIRST_ROW = $(byXpath(TASKS_LIST+TASKS_ROW+"[1]"+"//*[@class='text']//span"));


    public static final String TASKS_BTN_STATUS_ACTION = "//pkm-task-change-status//button";
    public static final String TASKS_BTN_STATUS_ACTION_ACCEPT = "//pkm-task-change-status//button[1]";
    public static final String TASKS_BTN_STATUS_ACTION_REJECT = "//pkm-task-change-status//button[2]";
    public static final String TASKS_ACTION_START = "Start";
    public static final String TASKS_ACTION_FINISH = "finish";
    public static final String TASKS_ACTION_ACCEPT = "accept";
    public static final String TASKS_ACTION_REJECT = "reject";
    public static final String TASKS_ACTION_RESTART = "Restart";

    public static final String TASK_STATUS_NOT_STARTED = "Not Started";
    public static final String TASK_STATUS_IN_PROGRESS = "In Progress";
    public static final String TASK_STATUS_COMPLETED = "Completed";
    public static final String TASK_STATUS_ACCEPTED = " Accepted";
    public static final String TASK_STATUS_REJECTED = "Rejected";
    public static final String TASK_STATUS_CANCELLED = "Cancelled";

    public static final String TASKS_FIRST_ROW = TASKS_LIST+TASKS_ROW+"[1]";
    public static final SelenideElement TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW = $(byXpath(TASKS_FIRST_ROW+TASKS_BTN_STATUS_ACTION));
    public static final SelenideElement TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT = $(byXpath(TASKS_FIRST_ROW+TASKS_BTN_STATUS_ACTION_ACCEPT));
    public static final SelenideElement TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT = $(byXpath(TASKS_FIRST_ROW+TASKS_BTN_STATUS_ACTION_REJECT));

    public static final String TASKS_BTN_STATUS_ACTUAL = "//pkm-task-change-status//div[./i]";
    public static final SelenideElement TASKS_BTN_STATUS_IN_FIRST_ROW = $(byXpath(TASKS_FIRST_ROW+TASKS_BTN_STATUS_ACTUAL));

    public final static SelenideElement TASKS_STATUS_SELECTED_IN_DROPDOWN_MENU(String statusName) {
        String selectStatus = "//*[@role='menu']//li/a[1][@href and text()='%s']";
        String selectStatusSelected = String.format(selectStatus, statusName);
        SelenideElement selectStatusSelectedElement = $(byXpath(selectStatusSelected));
        return selectStatusSelectedElement;
    }
    public static final String PROJECT_TASK_DROPDOWN_STATUS_NEW = "New";
    public static final String PROJECT_TASK_DROPDOWN_STATUS_IN_PROGRESS = "In progress";
    public static final String PROJECT_TASK_DROPDOWN_STATUS_COMPLETED = "Completed";
    public static final String PROJECT_TASK_DROPDOWN_STATUS_APPROVED = "Approved";
    public static final String PROJECT_TASK_DROPDOWN_STATUS_REJECTED = "Rejected";
    public static final String PROJECT_TASK_DROPDOWN_STATUS_CANCELLED = "Cancelled";

    //TAB CHARGES
    public static SelenideElement TAB_CHARGES_ACTUAL_ORDER = $(byXpath(CONTROLS_ROW+"//a[contains(.,'"+TASKS_ORDER+"')]"));        //order value

    public static final SelenideElement TAB_CHARGES = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_CollapseForm = $(byXpath("//div/ng-include/div/a[@class='link-task']"));
    public static final SelenideElement TAB_CHARGES_BackToListLink = $(byLinkText("link=Back to all finances"));
    public static final SelenideElement TAB_CHARGES_BTN_DELETE = TAB_CONTROL_DELETE;
    public static final SelenideElement TAB_CHARGES_XERO = $(byXpath("//button[@ng-click='bulkToXero()']"));

    public static final SelenideElement TAB_CHARGES01From = $(byXpath("//div[@class='items-list with-caret ng-scope finances']/div[1]//span[@class='name ng-binding']"));
    public static final SelenideElement TAB_CHARGES01Type = $(byXpath("//div[@class='items-list with-caret ng-scope finances']/div[1]//span[@class='task-title ng-binding']"));
    public static final SelenideElement TAB_CHARGES01Total = $(byXpath("//div[@class='items-list with-caret ng-scope finances']/div[1]//span[@class='price ng-binding']"));
    public static final SelenideElement TAB_CHARGES01Date = $(byXpath("//div[@class='items-list with-caret ng-scope finances']/div[1]//span[@class='date ng-binding']"));
    public static final SelenideElement TAB_CHARGES01To = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES01AllZone = $(byXpath("//div[@class='items-list with-caret ng-scope finances']/div[1]//div[@class='organizations-list ng-isolate-scope']//span"));
    public static final SelenideElement TAB_CHARGES01OneTeamZone = $(byXpath(".//*[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/ui-view/div[3]/div/div/ng-include/div[2]/ul/li/a"));

        //TAB FAMILY
    public static final SelenideElement TAB_FAMILY = $(byXpath(""));
    public static final SelenideElement TAB_FAMILY_NEW = $(byXpath(CONTROLS_ROW+"//button[contains(.,'New')]"));
    public static final SelenideElement TAB_FAMILY_1ST_ROW_TITLE = $(byXpath("//ul[@class='items']/li[1]//h4"));


    //TAB SEARCH
    public static final SelenideElement TAB_SEARCH = $(byXpath(""));
    public static final SelenideElement TAB_SEARCH_INPUT = $(byXpath("//*[@id='typeahead-container']/input"));
    public static final SelenideElement TAB_SEARCH_BTN = $(byXpath("//*[@class='media-right submit-box']/button"));
}
