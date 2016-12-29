package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaProject {
    //public static final String COMMENT_005 = "CURRENT PROJECT";
    public static final String projectButtonPlus = "//div[@class='project-heading']/button";
    public static final String projectPlusNewEvent = "link=New Event";
    public static final String projectPlusNewConversation = "link=New Conversation";
    public static final String projectPlusNewTask = "link=New Task";
    public static final String projectPlusNewDocument = "link=New Document";
    public static final String projectPlusNewFinancial = "link=New financial";
    public static final String projectPlusNewNumber = "link=New Number";
    public static final String projectPlusNewContact = "link=New Contact";
    public static final String projectAllCheckbox = "css=i.pkm-icon-checkbox";
    public static final String projectAllCheckboxFiles = "//div[@class='checkbox-holder fancy main-checkbox pull-left']//input";

    public static final String mODAL_ShareProject = "css=.modal-content";
    public static final String mODAL_ShareProjectEmptyTeam = "//*[@class='ng-binding'][contains .,'' ]";
    public static final String mODAL_ShareProjectOkButton = "//*[@class='btn btn-primary ng-isolate-scope'][contains .,'OK' ]";
    public static final String mODAL_ShareProjectCancelButton = "//*[@class='btn btn-secondary'][contains .,'Cancel' ]";
    public static final String collaborator_VatslauAfrica = "//*[@class='ng-binding'][contains .,'Vatslau Africa' ]";
    public static final String collaborator_V375291200656 = "//*[@class='ng-binding'][contains .,'Test01 Musk' ]";
    public static final String collaborator_QweecoTest02 = "//*[@class='ng-binding'][contains .,'Test02 Qweeco Zubr' ]";

    public static final String timelineLine = "css=.slider-selection";
    public static final String timelineCheckboxLessImportant = "//*[@ng-model='showLessImportant']";
    public static final String timelineCheckboxAutoPopulated = "//*[@ng-model='showPopulated']";
    public static final String timelineCheckboxManuallyAdded = "//*[@ng-model='showManual']";
    public static final String timelineCheckboxShrinkedEventsView = "//*[@ng-model='shrinkedEventsView']";
    public static final String timelineDeleteEvent = "//*[@pkm-confirm-click='remove event ']";
    public static final String timelineEditEvent = "//*[@ng-click='update event ']";
    public static final String timelineArrowLeft = "";
    public static final String timelineArrowRight = "";

    public static final String projectTimeline_ShowHide = "//button[@type='button'][contains(.,'timeline')]";
    public static final String projectTimeline_Line = "css=.slider-selection";
    public static final String projectTimeline_CheckboxLessImportant = "//div[@class='check-filters-holder form-inline']/div[1]//input";
    public static final String projectTimeline_CheckboxAutoPopulated = "//div[@class='check-filters-holder form-inline']/div[2]//input";
    public static final String projectTimeline_CheckboxManuallyAdded = "//div[@class='check-filters-holder form-inline']/div[3]//input";
    public static final String projectTimeline_CheckboxShrinkedEventsView = "//div[@class='check-filters-holder form-inline']/div[4]//input";
    public static final String projectTimeline_DeleteEvent = "//*[@pkm-confirm-click='remove(event)']";
    public static final String projectTimeline_EditEvent = "//*[@ng-click='update(event)']";
    public static final String projectTimeline_ArrowLeft = "";
    public static final String projectTimeline_ArrowRight = "";
    public static final String projectTimeline_EventToday = "//ul[@id='timeline']/li/a/span";

    public static final String wmEvent_ = "";
    public static final String wmEvent_SelectType = "//div/div[2]/pkm-values-dropdown/div/div/span";
    public static final String wmEvent_SelectInput = "//pkm-values-dropdown/div/input";
    public static final String wmEvent_FieldInfo = "//textarea[@name='number']";
    public static final String wmEvent_SAVE = "";

    public static final String projectTabMore = "//i[@class='icon pkm-icon-info-square']";
    public static final String projectTabContacts = "//i[@class='icon pkm-icon-users-square']";
    public static final String projectTabDocs = "//i[@class='icon pkm-icon-documents-square']";
    public static final String projectTabTasks = "//i[@class='icon pkm-icon-tasks-square']";
    public static final String projectTabFin = "//i[@class='icon pkm-icon-finances-square']";
    public static final String projectTabFamily = "//i[@class='icon pkm-icon-family-square']";
    public static final String projectTabSearch = "//i[@class='icon pkm-icon-search-square']";
    public static final String projectTabNoContentPlaceholder = "//div[@class='project-content-body ng-scope']//div[starts-with(@class, 'alert alert-empty')]";

    public static final String projectTabMore_ = "";
    public static final String projectTabMore_ProjectTitle = "//pkm-editable-title/div/div/h4";
    public static final String projectTabMore_TitleEditButton = "link=Edit";
    public static final String projectTabMore_TitleInput = "//input[@name='title']";
    public static final String projectTabMore_TitleSave = "//button[contains(.,'Save')]";
    public static final String projectTabMore_TitleCancel = "//button[contains(.,'Cancel')]";
    public static final String projectTabMore_Notes = "";
    public static final String projectTabMore_Statuses = "";
    public static final String projectTabMore_StatusesSearchButton = "css=button.info-status-seach-btn";
    public static final String projectTabMore_StatusesSearchInupt = "id=searchStatusInput";
    public static final String projectTabMore_Statuses1Status = "//ul//*[@id]/span";
    public static final String projectTabMore_Statuses2Status = "//ul//*[@id][2]/span";

    public static final String projectTabMore_NumberNoNumbers = "//pkm-reference-numbers/div[2]/div/div[2]";
    public static final String projectTabMore_NumberNoClasses = "//pkm-classes/div[2]/div/div[2]";

    public static final String projectTabMore_NumberAdd = "//button[contains(.,'Add')]";
    public static final String projectTabMore_NumberRow01Type = "//li[@class='like-tr ng-scope']/div/div[1]";
    public static final String projectTabMore_NumberRow01Number = "//li[@class='like-tr ng-scope']/div/div/span";
    public static final String projectTabMore_NumberRow01Edit = "//li[@class='like-tr ng-scope']/div/div[3]/a[1]";
    public static final String projectTabMore_NumberRow01Delete = "//li[@class='like-tr ng-scope']/div/div[3]/a[2]";
    public static final String projectTabMore_NumberRow02Number = "";
    public static final String projectTabMore_NumberRow02Edit = "";
    public static final String projectTabMore_NumberRow02Type = "";

    public static final String projectTabMore_NumberNewSelect = "//div[@name='reference_type']/div/span/span[1]";
    public static final String projectTabMore_NumberNewField = "//div[@name='reference_type']/input[1]";
    public static final String projectTabMore_NumberReferenceField = "//input[@name='reference_number']";

    public static final String projectTabMore_ClassesAdd = "link=add";
    public static final String projectTabMore_ClassRow01Number = "//tbody/tr/td[1]";
    public static final String projectTabMore_ClassRow01Type = "//tbody/tr/td[2]";
    public static final String projectTabMore_ClassRow01Description = "//tbody/tr/td[3]";
    public static final String projectTabMore_ClassRow01Edit = "//tbody/tr/td[4]/a[@class='edit ng-scope']";

    public static final String projectTabMore_ProejctType = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/div/div[2]/div[2]/form/div/pkm-values-dropdown/div/div/span/span[2]/span";
    public static final String projectTabMore_SelectDefining = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/div/div[2]/div[2]/form/div/pkm-values-dropdown/div/div/span/span[2]/span";
    public static final String projectTabMore_SelectType = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/div/div[2]/div[2]/form/div[2]/pkm-values-dropdown/div/div/span/span";
    public static final String projectTabMore_SelectSubType = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/div/div[2]/div[2]/form/div[3]/pkm-values-dropdown/div/div/span/span";

    public static final String projectTabMore_Community = "//pkm-community-projects";
    public static final String projectTabMore_CommunityModule = "//pkm-community-projects//h4";
    public static final String projectTabMore_CommunityBtnNewCase = "link=+ START NEW";
    public static final String projectTabMore_CommunityCasesList = "//pkm-community-projects//div[@class='request-quote-list']/div";
    public static final String projectTabMore_CommunityCaseDefining = "//div[@class='patent']/span";
    public static final String projectTabMore_CommunityCaseStatus = "//div[@class='status']//span";

    public static final String mwClasses_Title = "//div/div/h3";
    public static final String mwClasses_SelectClassType = "//div[2]/div/div/div/span/span[2]/span";
    public static final String mwClasses_FieldClassType = "//form/div[2]/div/div/input";
    public static final String mwClasses_FieldClass = "//input[@name='class_no']";
    public static final String mwClasses_FieldDescription = "//textarea[@name='description']";

    public static final String projectTabContacts_TeamsTitle = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/pkm-collaborators/h4";
    public static final String projectTabContacts_TeamRow = "//tr[@ng-repeat='collaborator in collaborators']";
    public static final String projectTabContacts_TeamName = "/td[1]";
    public static final String projectTabContacts_TeamStatus = "/td[2]";
    public static final String projectTabContacts_TeamEdit = "/td[3]/a[1]";
    public static final String projectTabContacts_TeamDelete = "/td[3]/a[2]";
    public static final String mwChangeCollaborator_Title = "//div/div/h3";
    public static final String mwChangeCollaborator_Select = "//select";
    public static final String mwChangeCollaborator_Viewer = "label=Viewer";
    public static final String mwChangeCollaborator_Collaborator = "label=Collaborator";
    public static final String mwChangeCollaborator_Admin = "label=Admin";

    public static final String projectTabContacts_RelationNoData = "//pkm-contact-relations/div[2]/div/div[2]";
    public static final String projectTabContacts_AddSelectContact = "//div[@name='contact']/div/span";
    public static final String projectTabContacts_AddContactInput = "//input[@type='search']";
    public static final String projectTabContacts_AddSelectRelation = "//div[@name='relation']/div/span";
    public static final String projectTabContacts_AddRelationInput = "//div[@name='relation']/input[1]";
    public static final String projectTabContacts_AddContactButton = "";

    public static final String projectTabContacts_ContactRow = "//li[starts-with(@ng-repeat, 'contactRelation')]";
    public static final String projectTabContacts_ContactIconPerson = "css=i.pkm-icon-user";
    public static final String projectTabContacts_ContactIconCompany = "css=i.pkm-icon-building";
    public static final String projectTabContacts_ContactName = "/div/div[1]";
    public static final String projectTabContacts_ContactRelation = "/div/div[2]";
    public static final String projectTabContacts_ContactCollaborate = "//button[contains(.,'+COLLABORATE')]";
    public static final String projectTabContacts_ContactDrop = "//a[@class='first']";
    public static final String projectTabContacts_ContactEdit = "//a[@class='edit']";
    public static final String projectTabContacts_ContactDelete = "//a[@class='cancel']";

    public static final String projectTabContacts_Form = "";
    public static final String projectTabContacts_FormRelationSelect = "//select[@name='relation']";
    public static final String projectTabContacts_FormOwnership = "//input[@name='ownership_percentage']";
    public static final String projectTabContacts_FormTypeSelect = "//select[@name='contact.type']";
    public static final String projectTabContacts_FormEntity = "//input[@name='contact.legal_entity_name']";
    public static final String projectTabContacts_FormFirstName = "//input[@name='contact.first_name']";
    public static final String projectTabContacts_FormLastName = "//input[@name='contact.last_name']";
    public static final String projectTabContacts_FormCompanySelect = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/div/pkm-contact-relations/ul/li/div[2]/div/form/div/div[2]/pkm-contact-fields/div/div[3]/div/div/div/span";
    public static final String projectTabContacts_FormCompanyInput = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/div/pkm-contact-relations/ul/li/div[2]/div/form/div/div[2]/pkm-contact-fields/div/div[3]/div/div/input";
    public static final String projectTabContacts_FormEmail = "//input[@name='contact.email']";
    public static final String projectTabContacts_FormPhone = "//input[@name='contact.phone_number']";
    public static final String projectTabContacts_FormFax = "//input[@name='contact.fax_number']";
    public static final String projectTabContacts_FormMobile = "//input[@name='contact.cellphone_number']";
    public static final String projectTabContacts_FormStreet = "//input[@name='contact.street_address']";
    public static final String projectTabContacts_FormPostal = "//input[@name='contact.postal_code']";
    public static final String projectTabContacts_FormCity = "//input[@name='contact.city']";
    public static final String projectTabContacts_FormRegion = "//input[@name='contact.region']";
    public static final String projectTabContacts_FormCountrySelect = "//div[@name='contact.country']//div/span";
    public static final String projectTabContacts_FormCountryInput = "//div[@name='contact.country']//input";


    public static final String projectTabDocs_ = "";
    public static final String projectTabDocs_Row = "//div[@class='items-list files ng-scope angular-ui-tree']/ol/li";
    public static final String projectTabDocs_RowName = "//div[@class='file-info-holder']/a[1]";

    public static final String projectTabDocs_01Row = "//li[@ng-repeat='node in data'][1]//div[@class='file-info-holder']/a";
    public static final String projectTabDocs_01RowMenu = "//li[4]/div[2]/button";
    public static final String projectTabDocs_01RowPermission = "//li[4]/div/ul/li/span";
    public static final String projectTabDocs_01RowNameExpanded = "//pkm-files-editable-title/div/h5";
    public static final String projectTabDocs_02Row = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/pkm-files/div/div[3]/div/ol/li[2]/div/div/ul/li[3]/div/a";
    public static final String projectTabDocs_03Row = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/pkm-files/div/div[3]/div/ol/li[3]/div/div/ul/li[3]/div/a/span";
    public static final String projectTabDocs_RenameExpanded = "//a[contains(text(),'Rename')]";
    public static final String projectTabDocs_NameExpanded = "//input[@name='name']";
    public static final String projectTabDocs_SaveExpanded = "//pkm-files-editable-title/div/form/div/a[contains(text(),'Save')]";

    public static final String projectTabTask_New = "link=Create task";
    public static final String projectTabTask_BulkSelectAll = "xpath=(//input[@type='checkbox'])[5]";
    public static final String projectTabTask_BulkSelect01 = "";
    public static final String projectTabTask_BulkSelect02 = "";
    public static final String projectTabTask_BulkSelect03 = "";
    public static final String projectTabTask_BulkSelect04 = "";
    public static final String projectTabTask_BulkSelect05 = "";
    public static final String projectTabTask_BulkDelete = "//button[@pkm-confirm-click='bulkDelete()']";
    public static final String projectTabTask_BulkStatus = "//button[@ng-click='bulkUpdate('status')']";
    public static final String projectTabTask_BulkImportance = "//button[@ng-click='bulkUpdate('importance')']";
    public static final String projectTabTask_BulkAssignor = "//button[@ng-click='bulkUpdate('assignor')']";
    public static final String projectTabTask_BulkAssignee = "//button[@ng-click='bulkUpdate('assignee')']";
    public static final String projectTabTask_DatepickerCall = "";
    public static final String projectTabTask_ButtonStatus = "";
    public static final String projectTabTask_MenuStatus = "//pkm-task-change-status/div/div";
    public static final String projectTabTask_NoTaskText = "";
    public static final String projectTabTask_01TaskButtonStatus = "//pkm-task-change-status/div/div[2]/button";
    public static final String projectTabTask_ListActive = "//div/div/div/div/div/button";
    public static final String projectTabTask_ListAll = "//div/div/div/div/div[2]/button";
    public static final String projectTabTask_ = "";

    public static final String buttonAddNewTask = "id=file-controls";
    public static final String linkCreateNewTask = "link=Create task";

    public static final String mwUpdateSatus_Select = "";
    public static final String mwUpdateSatus_Input = "";
    public static final String mwUpdateImportance_Select = "";
    public static final String mwUpdateImportance_Input = "";
    public static final String mwUpdateAssignor_Select = "";
    public static final String mwUpdateAssignor_Input = "";
    public static final String mwUpdateAssignee_Select = "";
    public static final String mwUpdateAssignee_Input = "";

    public static final String projectTabFin_ = "";
    public static final String projectTabFin_CollapseForm = "//div/ng-include/div/a[@class='link-task']";
    public static final String projectTabFin_BackToListLink = "link=Back to all finances";
    public static final String projectTabFin_ToXero = "//button[@ng-click='bulkToXero()']";

    public static final String projectTabFin_01From = "//div[@class='items-list with-caret ng-scope finances']/div[1]//span[@class='name ng-binding']";
    public static final String projectTabFin_01Type = "//div[@class='items-list with-caret ng-scope finances']/div[1]//span[@class='task-title ng-binding']";
    public static final String projectTabFin_01Total = "//div[@class='items-list with-caret ng-scope finances']/div[1]//span[@class='price ng-binding']";
    public static final String projectTabFin_01Date = "//div[@class='items-list with-caret ng-scope finances']/div[1]//span[@class='date ng-binding']";
    public static final String projectTabFin_01To = "";
    public static final String projectTabFin_01AllZone = "//div[@class='items-list with-caret ng-scope finances']/div[1]//div[@class='organizations-list ng-isolate-scope']//span";
    public static final String projectTabFin_01OneTeamZone = ".//*[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div[2]/div/div/ui-view/div[3]/div/div/ng-include/div[2]/ul/li/a";


}
