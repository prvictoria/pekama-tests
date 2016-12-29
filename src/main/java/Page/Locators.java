package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class Locators {


 public static final String urlBASE = "https://pekama.com/";
 public static final String urlStaging = "https://staging.pekama.com/";
 public static final String urlProduction = "https://pekama.com/";
 public static final String urlLogIn = "accounts/login/";
 public static final String urlSingUp = "signup/";
 public static final String urlLogput = "accounts/logout/";
 public static final String urlLanding = "/";
 public static final String urlDashboard = "a/";
 public static final String urlReportsProjects = "a/reports/projects";
 public static final String urlReportsTasks = "a/reports/tasks";
 public static final String urlReportsEvents = "a/reports/events";
 public static final String urlReportsFin = "a/reports/financials";
 public static final String urlReportsContacts = "a/reports/contacts";
 public static final String urlPersonalSettings = "a/settings/profile";
 public static final String urlTeamSettings = "a/settings/team";
 public static final String urlTS_Members = "a/settings/team/members";

 public static final String USER_DATA = "test data login passwords etc.";
 public static final String gmailTest01 = "testqweeco001@gmail.com";
 public static final String gmailTest02 = "testqweeco002@gmail.com";
 public static final String gmailTest03 = "testqweeco003@gmail.com";
 public static final String gmailTestGenericPass = "asui67we34";
 public static final String teamTest01 = "Qweeco01 (QT01)";
 public static final String teamTest02 = "Qweeco02 (QT02)";
 public static final String teamTest03 = "Qweeco03 (QT03)";
 public static final String linkedinLogin_user01 = "v375291200656@gmail.com";
 public static final String linkedinPassword_user01 = "37823akk10ajqlpq128dnwuao9";
 public static final String linkedinLogin_user04 = "vatslav.realt@gmail.com";
 public static final String linkedinPassword_user04 = "123456789qasw";

 public static final String COMMENT_002 = "LOCATORS start pages";
 public static final String landing = "";
 public static final String landingSingUp = "link=sign up";
 public static final String landingLogin = "link=Login";
 public static final String landingLogOut = "link=log out";
 public static final String landingDashboard = "link=Dashboard";

 public static final String login = "sic!";
 public static final String loginForgotPass = "accounts/password/reset/";
 public static final String loginLinkedin = "";
 public static final String loginGoogle = "";
 public static final String loginField_Email = "name=username";
 public static final String loginField_Password = "name=password";
 public static final String loginButton_Login = "css=button.btn.btn-default";

 public static final String signin = "";

 public static final String signup = "//form[@id='signup-form']";
 public static final String signupTitle = "";
 public static final String signupNewButtonDisabled = "//form[@id='signup-form']//*[@class='btn btn-primary disabled']//*[contains(text(),'Next Step')]";
 public static final String signupNewButtonEnabled = "//*[@class='btn btn-primary']//*[contains(text(),'Next Step')]";
 public static final String signupPasswordEmptyAlert = "";
 public static final String signupCompanyEmptyAlert = "";
 public static final String signupFirstnameEmptyAlert = "";
 public static final String signupLastnameEmptyAlert = "";
 public static final String signupEmailEmptyAlert = "";
 public static final String signupgGenericEmptyAlert = "//form[@id='signup-form']//li[contains(.,'Please fill out this field.')]";
 public static final String signupResetPassw = "";
 public static final String header = "sic!";
 public static final String headerUserDropdown = "css=i.memobox-icon-down-open";
 public static final String headerUsername = "";
 public static final String headerTeamname = "";
 public static final String headerUserAvavtar = "";
 public static final String headerTeamAvatar = "";
 public static final String headerPersonalSettingsLink = "link=Personal settings";
 public static final String headerTeamSettingsLink = "link=Team settings";
 public static final String headerLogOutLink = "link=Log out";
 public static final String dashboard = "sic!";
 public static final String dashboardProjectsTitle = "//h4[contains(.,'Projects')]";
 public static final String dashboardYourProfileTitle = "//h4[contains(.,'Your Profile And Team')]";
 public static final String dashboardUpcomingTitle = "//h4[contains(.,'UPCOMING')]";
 public static final String dashboardTasksTitle = "//h4[contains(.,'Tasks')]";

 public static final String dashboardSelectProjectTemplatesButton = "xpath=//button[@type='button'][2]";
 public static final String dashboardSelectProjectTemplatesTemplate01 = "//a[@ng-click='applyTemplate(template)'][1]";
 public static final String dashboardNewProject = "//button[@type='button'][contains(.,'+ NEW')]";
 public static final String dashboardProjectList01 = "//li[@class='item matter ng-scope']/a";
 public static final String dashboardProjectList02 = "//li[2][@class='item matter ng-scope']/a";
 public static final String dashboardProjectList03 = "//li[3][@class='item matter ng-scope']/a";
 public static final String dashboardProjectList04 = "//li[4][@class='item matter ng-scope']/a";
 public static final String dashboardProjectList05 = "//li[5][@class='item matter ng-scope']/a";

 public static final String dashboardInvite = "link=+ INVITE";
 public static final String dashboardTeamMembersQTY = "//*[@ui-sref='settings.organization.members']";
 public static final String dashboardAvailableProjectCount = "//a/span";
 public static final String dashboardBuyProjects = "link=*BUY MORE";
 public static final String dashboardUserAvatar = "???";
 public static final String dashboardProjectsLeft = "//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li[2]/div";
 public static final String dashboardYourProfileAndTeam = "//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div/div/div/h4";
 public static final String dashboardYourTeams = "//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li/div";
 public static final String dashboardTeamName = "//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li/div[2]/div/div/span/span[2]/span";

 public static final String dashboardNoProjects = "//pkm-dashboard-projects//div[starts-with(@class, 'alert alert-empty')]";

 public static final String dashboardUpcoming = "";
 public static final String dashboardUpcomingToday = "//button[@type='button'][contains(.,'today')]";
 public static final String dashboardUpcomingMonth = "//button[@type='button'][contains(.,'month')]";
 public static final String dashboardUpcomingWeek = "//button[@type='button'][contains(.,'week')]";
 public static final String dashboardUpcomingDay = "xpath=(//button[@type='button'])[8]";
 public static final String dashboardUpcomingPastButton = "xpath=(//button[@type='button'])[3]";
 public static final String dashboardUpcomingNextButton = "xpath=(//button[@type='button'])[4]";

 public static final String dashboardUpcomingMyDeadlines = "//li/button[contains(.,'My Deadlines')]";
 public static final String dashboardUpcomingMyDeadlinesSelected = "//li[@class='active-calendar-filter' and contains(.,'My Deadlines')]";
 public static final String dashboardUpcomingAllDeadlines = "//li/button[contains(.,'All Deadlines')]";
 public static final String dashboardUpcomingAllDeadlinesSelected = "//li[@class='active-calendar-filter' and contains(.,'All Deadlines')]";
 public static final String dashboardUpcomingMyTasks = "//li/button[contains(.,'My Tasks')]";
 public static final String dashboardUpcomingMyTasksSelected = "//li[@class='active-calendar-filter' and contains(.,'My Tasks')]";
 public static final String dashboardUpcomingAllTasks = "//li/button[contains(.,'All Tasks')]";
 public static final String dashboardUpcomingAllTasksSelected = "//li[@class='active-calendar-filter' and contains(.,'All Tasks')]";

 public static final String dashboardTasks = "";
 public static final String dashboardTasksToDo = "link=To Do";
 public static final String dashboardTasksDoing = "link=Doing";

 public static final String checkoutBuy = "";
 public static final String checkoutCardNumberField = "";
 public static final String checkoutCardDate = "";
 public static final String checkoutCardCVV = "";
 public static final String checkoutSubmit = "";

 public static final String COMMENT_003 = "Personal / Team settings Project Templates";
 public static final String personalSettings = "/a/settings/profile";
 public static final String personalSettingsTabPersonal = "link=Personal details";
 public static final String personalSettingsTabSecurity = "link=Login & Security";
 public static final String personalSettingsTabEmails = "link=Emails";
 public static final String personalSettingsTabSignature = "link=E-mail signature";
 public static final String personalSettingsEmails = "";
 public static final String personalSettings1 = "";
 public static final String personalSettings2 = "";
 public static final String personalSettings3 = "";
 public static final String personalSettings4 = "";
 public static final String personalSettings5 = "";

 public static final String personalSettingsSaveButton = "//div[@id='login-security']/div/form/fieldset/div[5]/button";
 public static final String personalSettingsEnableButton = "//div[@id='login-security']/div/fieldset/div/div/div/button";
 public static final String personalSettingsCurrentPassword = "name=original_password";
 public static final String personalSettingsNewPassword = "name=password1";
 public static final String personalSettingsConfirmPassword = "name=password2";
 public static final String mwEnableVerificationTitle = "//h3";
 public static final String mwEnableVerificationClose = "//button[@type='button'][contains(.,'Close')]";
 public static final String mwEnableVerificationNext = "//button[@type='submit'][contains(.,'Next')]";
 public static final String mwEnableVerificationTelField = "name=phone";
 public static final String mwEnableVerificationCountrySelect = "//div/div/div/div/div/span";
 public static final String mwEnableVerificationCoutryField = "//div/div/div/div/input";
 public static final String mwEnableVerificationConfirmCodeField = "name=two_factor_code";
 public static final String mwEnableVerificationErrorArea = "//div[2]/form/div";

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

 public static final String mwTaskTemplateSet = "";
 public static final String mwTaskTemplateSet_FieldTitle = "//div[@class='modal-body']//input[@name]";
 public static final String mwTaskTemplateSet_FieldDefining = "//div[@class='modal-body']/div[3]//input";
 public static final String mwTaskTemplateSet_FieldType = "//div[@class='modal-body']/div[4]//input";
 public static final String mwTaskTemplateSet_FieldEvent = "//div[@class='modal-body']/div[5]//input";
 public static final String mwTaskTemplate_FieldTitle = "//div[@class='modal-body']//input[@name='title']";
 public static final String mwTaskTemplate_Assignor = "//div[@placeholder='Choose assignor...']/span";
 public static final String mwTaskTemplate_Assignee = "//div[@placeholder='Choose assignee...']/span";
 public static final String mwTaskTemplate_Importance = "//div[@placeholder='Choose importance...']/span";
 public static final String mwTaskTemplate_Status = "//div[@placeholder='Choose status...']/span";
 public static final String mwTaskTemplate_DateOffset = "";
 public static final String mwTaskTemplate_DateOffsetUnit = "";

 public static final String mwEventTemplate_EventType = "//pkm-values-dropdown/div/div/span";
 public static final String mwEventTemplate_AdditionalInfo = "//textarea";
 public static final String mwEventTemplate_Input = "//div[@name='event_type']/input[1]";

 public static final String COMMENT_005 = "CURRENT PROJECT";
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


 public static final String conversationProjectBtnTeam = "css=button.team-button";
 public static final String conversationProjectBtnClient = "css=button.external-email-button";
 public static final String conversationProjectBtnNew = "//pkm-conversations//button[contains(.,'New')]";
 public static final String conversationProjectNoThreads = "//pkm-conversations//div[starts-with(@class, 'alert alert-empty')]";
 public static final String conversationProjectBtnPost = "//a[@class='btn btn-primary'][contains(.,'Post')]";
 public static final String conversationProjectBtnTemplate = "//i[@class='pkm-icon-template']";
 public static final String conversationProjectBtnAttach = "//i[@class='pkm-icon-attach']";
 public static final String conversationProjectBtnCloud = "//i[@class='pkm-icon-cloud']";
 public static final String conversationProjectList = "//ul[@class='conversation-list ng-scope']/li";
 public static final String conversationProjectBtnBack = "//a[@class='btn btn-secondary btn-small'][contains(.,'Back')]";
 public static final String conversationProjectPin = "//button[@class='btn btn-secondary btn-small ng-binding'][contains(.,'Pin')]";
 public static final String conversationProjectUnpin = "//button[@class='btn btn-secondary btn-small ng-binding'][contains(.,'Unpin')]";
 public static final String conversationProjectBtnParameters = "//i[@class='pkm-icon-envelope-big']";
 public static final String conversationProjectValidation = "//div[@ng-if='validationMessage']";
 public static final String conversationProjectInputTo = "//div[@class='external-recipients form-bordered ng-scope']/div[1]//input";
 public static final String conversationProjectInputCC = "//div[@class='external-recipients form-bordered ng-scope']/div[2]//input";
 public static final String conversationProjectInputBCC = "//div[@class='external-recipients form-bordered ng-scope']/div[3]//input";
 public static final String conversationProjectInputSubject = "//div[@class='external-recipients form-bordered ng-scope']/div[4]//input";
 public static final String conversationProjectMsgBody = "//div[@class='media-body width-float']";
 public static final String conversationProjectMsgTo = "//div[@class='media-body width-float']//dd[1]/span";
 public static final String conversationProjectMsgCC = "//div[@class='media-body width-float']//dd[2]/span";
 public static final String conversationProjectMsgTask = "//div[@class='media-body width-float']//span[@class='bubble ng-scope']/i";
 public static final String conversationProjectMsgBCC = "//div[@class='media-body width-float']//dd[3]/span";
 public static final String conversationProjectMsgDelete = "//div[@class='media-body width-float']//i[@class='pkm-icon-cancel']";
 public static final String conversationErrorNoRecipients = "External conversation message should have recipients";
 public static final String conversationProjectInputText = "//input[@placeholder='Write Something']";
 public static final String conversationProjectThreadName = "//pkm-conversation-subject//h3";
 public static final String conversationProject = "";

 public static final String conversationSidebar = "//aside[@class='column-sidebar']";
 public static final String conversationSidebarToggle = "css=a.sidebar-toggle";
 public static final String conversationSidebarSearch = "//input[@placeholder='Search in conversations']";
 public static final String conversationSidebarPlaceholder = "//aside[@class='column-sidebar']//div[starts-with(@class, 'alert alert-empty')]";
 public static final String conversationSidebarFilterAll = "//aside[@class='column-sidebar']//a[text()='All']";
 public static final String conversationSidebarFilterUnread = "//aside[@class='column-sidebar']//a[text()='Unread']";
 public static final String conversationSidebarFilterPinned = "//aside[@class='column-sidebar']//a[text()='Pinned']";
 public static final String conversationSidebarSortByDate = "//aside[@class='column-sidebar']//a[text()='By Date']";
 public static final String conversationSidebarTreadList = "//aside[@class='column-sidebar']//div[@class='menu-list ng-isolate-scope']/div";
 public static final String conversationSidebarIconTeam = "//i[@class='pkm-icon-chat']";
 public static final String conversationSidebarIconEmail = "//i[@class='pkm-icon-envelope']";
 public static final String conversationSidebarIconPin = "//i[@class='pkm-icon-pin']";






 public static final String conversationEmptyRow = "//pkm-conversations/div/div[2]/div[2]/div[2]";
 public static final String conversation01ThreadRow = "//div[2]/ul/li/span";
 public static final String conversation02ThreadRow = "//li[2]/span";
 public static final String conversation03ThreadRow = "//li[3]/span";
 public static final String conversation04ThreadRow = "//li[4]/span";
 public static final String conversation05ThreadRow = "//li[5]/span";
 public static final String conversationNewButton = "//pkm-conversations/div/div/button";
 public static final String projectThreadEditTitleIcon = "//*[@ng-click='startTitleEditing  ']//*[@class='memobox-icon-edit']";
 public static final String projectThreadTitle = "css=h3.ng-binding";
 public static final String projectThread01Follower = "//div/div/ul/li/span/span[2]";
 public static final String projectThread02Follower = "//li[2]/span/span";
 public static final String projectThread03Follower = "//li[3]/span/span";
 public static final String projectThread04Follower = "//li[4]/span/span";
 public static final String projectThread05Follower = "//li[5]/span/span";
 public static final String projectThreadNoMessagesAlert = "//*[@class='alert alert-epmty text-center ng-scope']";
 public static final String projectThreadAddFollowerField = "//*[@class='search-field']//*[@type=\"text\"]";
 public static final String projectThreadAddFollowerSearchResult = "//*[@class=\"result-name ng-binding\"]";
 public static final String projectThreadAddInviteToPekama = "//*[@class='btn btn-primary btn-small ng-scope'][contains .,'invite to Pekama' ]";
 public static final String projectThreadAddAsGuest = "//*[@class='btn btn-primary btn-small'][contains .,'add as guest' ]";
 public static final String projectThreadBack = "link=Back";
 public static final String projectThreadShrinkMsgInput = "//section[@id='page']/div[2]/ui-view/div/section[2]/ui-view/div/pkm-conversations/div/div[3]/pkm-conversation/div[2]/div/div/input";
 public static final String projectThreadUploadFile = "";
 public static final String projectThreadCloud = "";
 public static final String projectThreadMsgTemplate = "css=i.fa.fa-file-text-o";
 public static final String projectThreadMsgPostButton = "";
 public static final String projectThreadEmailParameters = "css=div.action-buttons.clearfix-row > div.pull-right > button.btn.btn-default";
 public static final String projectThreadMsgTaskIcon = "css=i.memobox-icon-ok";
 public static final String projectThreadMsgDelete = "css=i.memobox-icon-delete-small";

 public static final String conversationTeamChatTab = "css=button.team-button";
 public static final String conversationExternalTab = "css=button.external-email-button";
 public static final String conversationActiveCenterTab = "//pkm-conversation/div/button";
 public static final String conversation = "";


 public static final String COMMENT_006 = "MODAL WINDOWS";

 public static final String mwProjectTitle = "NEW_PROJECT";
 public static final String buttonNewProject = "//div[2]/div/button";
 public static final String field_ProjectType = "//fieldset/div/div/div/span";
 public static final String projectTypeGENERAL = "link=General";
 public static final String field_ProjectDefining = "//fieldset/div[2]/div/div/span";
 public static final String projectDefiningUSA = "//div[4]/a/span";
 public static final String field_ProjectTitle = "name=title";
 public static final String button_Finish = "//button[@type='submit']";

 public static final String BTN_WITH_TEXT = "//button[contains(.,'')]";
 public static final String genericButtonSave = "//button[contains(.,'Save')]";
 public static final String genericButtonOk = "//button[contains(.,'Ok')]";
 public static final String genericButtonYes = "//button[contains(.,'Yes')]";
 public static final String genericButtonAdd = "//button[contains(.,'add')]";
 public static final String genericButtonDelete = "//button[contains(.,'Delete')]";
 public static final String genericButtonCancel = "//button[contains(.,'Cancel')]";
 public static final String genericSelectHighlighted = "css=span.ui-select-highlight";
 public static final String mwGenericBody = "//div[@class='modal-content']";
 public static final String mwGenericButtonOk = "//button[text()='OK']";
 public static final String mwGenericTitleSimple = "//h3[@class='modal-title']";
// public static final String 1 = "[1]";
// public static final String 2 = "[2]";
// public static final String 3 = "[3]";
// public static final String 4 = "[4]";
// public static final String 5 = "[5]";
// public static final String 6 = "[6]";
// public static final String 7 = "[7]";
// public static final String 8 = "[8]";
// public static final String 9 = "[9]";
// public static final String 10 = "[10]";


 public static final String modal = "sic!";
 public static final String modalBuyProject = "sic!";
 public static final String modalBuyProjectInputQTY = "//input[@type='number']";
 public static final String modalBuyProjectTotalPrice = "//span[@class='your-price ng-binding']";
 public static final String modalBuyProjectDiscount = "//span[@class='your-discount ng-binding']";
 public static final String modalBuyProjectBuyProjects = "//div/button[contains(.,'Buy Projects')]";

 public static final String modalEmailparametersFieldEmailAddress = "//div/div[2]/div/div/input";
 public static final String modalEmailparametersSubjectLine = "name=template";
 public static final String modalEmailparametersSubjectLineTemplate = "//div[2]/div/form/input";
 public static final String modalEmailparametersShow = "//ng-switch/span";

 public static final String modalTemplatesPicktemplate = "//div/div[2]/div/div/input";
 public static final String modalTemplatesSubmatter = "//div/div/div/span/span[2]/span";
 public static final String modalTemplatesSubMatterType = "//div[3]/div/div/div/span/span";
 public static final String modalTemplatesEventType = "//div[4]/div/div/div/span/span";
 public static final String modalTemplatesMsgTemplate = "//a/div/div/p";

 public static final String modalGenericBody = "css=.modal-content";
 public static final String modalGenericTitle = "//*[@class='modal-title']";
 public static final String modalGenericDatepickerField = "css=input.form-control.date-box";
 public static final String modalGenericDatepickerIcon = "css=.memobox-icon-calendar";
 public static final String modalGenericOkButton = "//*[@class='btn btn-primary ng-isolate-scope'][contains(.,'OK')]";
 public static final String modalGenericYesButton = "//*[@class='btn btn-primary'][contains .,'Yes' ]";
 public static final String modalGenericCrossButoon = "//*[@aria-label='Close']";
 public static final String modalGenericSaveButton = "//button[@submit][contains(.,'Save')]";
 public static final String modalGenericCancelButton = "//*[@class='btn btn-secondary'][contains .,'Cancel']";
 public static final String modalGenericCancelButton2 = "//button[@type='button']//*[@class='memobox-icon-cancel']";
 public static final String modalGenericCloseButton = "//*[@class='btn btn-primary'][contains .,'Close' ]";
 public static final String modalGenericErrorMsg = "css=.help-block.error";
 public static final String modalGenericSpecificTeam = "//*[@pkm-team-colored-box='chosenOrganization']";
 public static final String modalGenericTeamsCheckbox = "//*[@pkm-team-colored-box='chosenOrganization']";
 public static final String modalGenericSelectHighlighted = "css=span.ui-select-highlight";

 public static final String modalNewConversation = "//div[@class='modal-content']";
 public static final String modalNewConversationInputSubject = "//div[@class='modal-content']//input[@name='subject']";
 public static final String modalNewConversationInputFollower = "//div[@class='modal-content']//pkm-followers-picker//input";
 public static final String modalNewConversationInputZoneTeams = "//div[@class='modal-content']//pkm-organizations-picker//li/input";
// public static final String modalNewConversationCancellButton = "";
// public static final String modalNewConversationCancellButton = "";
 public static final String modalNewConversationCreatelButton = "//button[text()='Create']";
 public static final String modalNewConversationCancellButton = "//button[text()='Cancel']";
 public static final String modalNewConversationFollowerField = "//li/input";
 public static final String modalNewConversationFollowerSelect = "css=span.result-name.ng-binding";

 public static final String modalCheckboxPermissionAllTeams = "//*[@ng-disabled='uiState.disableAllTeams']";

 public static final String modalProjectTemplateCancelButton = "//div[2]/button[2]";
 public static final String modalProjectTemplateOklButton = "//div[2]/button";
 public static final String mwProjectTemplateProjectTypeInput = "//div[@name='matter_type']/input[1]";
 public static final String mwProjectTemplateProjectDefiningInput = "//div[@name='defining']/input[1]";



 public static final String modalNewProjectFinishButton = "//button[@type='submit'][contains(.,'FINISH')]";
 public static final String modalNewProject_SelectType = "//div[@name='matter_type']/div/span/span[1]";
 public static final String modalNewProject_InputType = "//div[@name='matter_type']/input[1]";
 public static final String modalNewProject_SelectDefining = "//div[@name='defining']/div/span/span[1]";
 public static final String modalNewProject_InputDefining = "//div[@name='defining']/input[1]";
 public static final String modalNewProject_TMNumber = "//input[@name='official_lookup']";
 public static final String modalNewProject_Title = "//input[@name='title']";
 public static final String modalNewProject_Reference = "//input[@name='number']";

 public static final String modalMembersOkButton = "//div[2]/button";

 public static final String mwDeployDoc_Title = "css=h3.modal-title";
 public static final String mwDeployDoc_FileName = "//input[@name='name']";
 public static final String mwDeployDoc_01TemplateWord = "//li/label[text()='New Word document']";
 public static final String mwDeployDoc_02TemplateExcel = "//li/label[text()='New Excel sheet']";
 public static final String mwDeployDoc_03TemplateCustom = "//li[3]/label";
 public static final String mwDeployDoc_FilterName = "";
 public static final String mwDeployDoc_SelectDefining = "";
 public static final String mwDeployDoc_SelectType = "";
 public static final String mwDeployDoc_SelectEvent = "";
 public static final String mwDeployDoc_ButtonCreate = "//button[contains(.,'Create')]";
 public static final String mwDeployDoc_ButtonCancel = "//button[contains(.,'Cancel')]";

 public static final String mwDeployTask_Title = "";
 public static final String mwDeployTask_SelectEvent = "//div[@id='task-template-picker']/div/div/div/span/span";
 public static final String mwDeployTask_InputEvent = "//input[@placeholder='Select an event...']";
 public static final String mwDeployTask_01Template = "//div[@id='task-template-picker']/div[2]/div/div/label/span";
 public static final String mwDeployTask_Apply = "//button[contains(.,'Apply')]";
 public static final String mwDeployTask_Cancel = "";

 public static final String modalNewTaskAssignorField = "";
 public static final String modalNewTaskAssineeField = "";
 public static final String modalNewTaskAssignorSelect = "css=span.ng-binding.ng-scope";
 public static final String modalNewTaskAssineeSelect = "//div[2]/div/div/span/span[2]/span";
 public static final String modalNewTaskImportanceField = "";
 public static final String modalNewTaskImportanceSelect = "//pkm-values-dropdown/div/div/span/span[2]";
 public static final String modalNewTaskImportanceReminder = "";
 public static final String modalNewTaskImportanceDeadline = "";
 public static final String modalNewTaskImportanceFinalDeadline = "";
 public static final String modalNewTaskImportanceTask = "";
 public static final String modalNewTaskImportanceFatal = "";
 public static final String modalNewTaskStatusField = "";
 public static final String modalNewTaskStatusSelect = "";
 public static final String modalNewTaskStatusNew = "";
 public static final String modalNewTaskStatusInProgress = "";
 public static final String modalNewTaskStatusCompleted = "";
 public static final String modalNewTaskStatusApproved = "";
 public static final String modalNewTaskStatusRejected = "";
 public static final String modalNewTaskStatusCanceled = "";
 public static final String modalNewTask = "";

 public static final String modalNewFinancial = "";
 public static final String modalNewFinancialClickFrom = "//span[2]/span";
 public static final String modalNewFinancialInputFrom = "";
 public static final String modalNewFinancialClickTo = "//div[2]/div/div/span/span";
 public static final String modalNewFinancialInputTo = "";
 public static final String modalNewFinancialClickBy = "//div[3]/div/div/span/span[2]/span";
 public static final String modalNewFinancialInputBy = "";
 public static final String modalNewFinancialClickStatus = "//div[2]/div/div/div/span/span[2]/span";
 public static final String modalNewFinancialInputStatus = "";
 public static final String modalNewFinancialClickType = "//pkm-values-dropdown/div/div/span/span";
 public static final String modalNewFinancialInputType = "";
 public static final String modalNewFinancialInputDueDate = "generic datepicker!";
 public static final String modalNewFinancialInputItem = "//textarea";
 public static final String modalNewFinancialClickCurrency = "//div[3]/div[2]/pkm-values-dropdown/div/div/span/span";
 public static final String modalNewFinancialInputCurrency = "";
 public static final String modalNewFinancialInputQty = "//input[@name='quantity']";
 public static final String modalNewFinancialInputTimeHour = "//input[@name='hours']";
 public static final String modalNewFinancialInputTimeMin = "//input[@name='minutes']";
 public static final String modalNewFinancialInputTimeRate = "//input[@name='hourly_rate']";
 public static final String modalNewFinancialInputPrice = "//input[@name='price']";
 public static final String modalNewFinancialInputVat = "//input[@name='vat']";
 public static final String modalNewFinancialInputDisc = "//input[@name='discount']";
 public static final String modalNewFinancialInputTotal = "";

 public static final String mwTimeTrackerSave = "";
 public static final String mwTimeTrackerPause = "";
 public static final String mwTimeTrackerDiscard = "//label[@class='danger']/input";

 public static final String modalNewContact = "";
 public static final String modalNewNumber = "";
 public static final String modalNewDocument = "";

 public static final String COMMENT_007 = "DATEPICKER";
 public static final String dueDateInputField = "css=input.form-control.date-box";
 public static final String datepickerToday = "css=.day.active.today";
 public static final String calendarBody = "css=table.table-condensed";
 public static final String calendarDay = "//*[@data-action='selectDay'][contains .,'' ]";
 public static final String calendarDay01 = "//*[@data-action='selectDay'][contains .,'1' ]";
 public static final String calendarDay02 = "//*[@data-action='selectDay'][contains .,'2' ]";
 public static final String calendarDay03 = "//*[@data-action='selectDay'][contains .,'3' ]";
 public static final String calendarDay04 = "//*[@data-action='selectDay'][contains .,'4' ]";
 public static final String calendarDay05 = "//*[@data-action='selectDay'][contains .,'5' ]";
 public static final String calendarDay06 = "//*[@data-action='selectDay'][contains .,'6' ]";
 public static final String calendarDay07 = "//*[@data-action='selectDay'][contains .,'7' ]";
 public static final String calendarDay08 = "//*[@data-action='selectDay'][contains .,'8' ]";
 public static final String calendarDay09 = "//*[@data-action='selectDay'][contains .,'9' ]";
 public static final String calendarDay10 = "//*[@data-action='selectDay'][contains .,'10' ]";
 public static final String eventTypeApplicationFiled = "//*[@class='ng-binding ng-scope'][contains .,'Application filed']";
 public static final String eventTypeApplicationPublished = "//*[@class='ng-binding ng-scope'][contains .,'Application published' ]";
 public static final String eventTypeSubMatterCreated = "//*[@class='ng-binding ng-scope'][contains .,'Sub Matter Created' ]";

 public static final String COMMENT_008 = "REPORTS";

 public static final String reportsAllCheckbox = "//li[@class='items-header clearfix clearfix']//input[@type='checkbox']";
 public static final String reportsBulkDelete = "//button[@class='btn-link'][contains(.,'Delete')]";
 public static final String reportsBulkMerge = "//button[@class='btn-link'][contains(.,'Merge')]";
 public static final String reportsNoData = "//div/div[2][@class='alert alert-empty ng-binding ng-scope']";
 public static final String reportsSpinner = "//i[@class='pkm-icon-spinner icon-spin']";
 public static final String reportsBtnList = "//button[contains(.,'List')]";
 public static final String reportsBtnReport = "//button[contains(.,'Report')]";
 public static final String reportsBtnNew = "//button[@type='button'][contains(.,'New')]";
 public static final String reportsBtnImport = "//button[@type='button'][contains(.,'Import')]";
 public static final String reportsBtnAddContact = "//button[@type='button'][contains(.,'Add contact')]";
 public static final String reportsListRow01 = "//ng-include[@src='reportParams.listTemplate']/li[1]";
 public static final String reportsListRow02 = "//ng-include[@src='reportParams.listTemplate']/li[2]";
 public static final String reportsListRow03 = "//ng-include[@src='reportParams.listTemplate']/li[3]";
 public static final String reportsListRow04 = "//ng-include[@src='reportParams.listTemplate']/li[4]";
 public static final String reportsListRow05 = "//ng-include[@src='reportParams.listTemplate']/li[5]";

 public static final String reports1RowCheckbox = "";
 public static final String reports2RowCheckbox = "";
 public static final String reports3RowCheckbox = "";
 public static final String reports4RowCheckbox = "";
 public static final String reports5RowCheckbox = "";
 public static final String reports1Row = "//section[@id='page']/div[2]/ui-view/div/div[2]/div/div/ul/li[2]/ul/ng-include/li/a";
 public static final String reports2Row = "//section[@id='page']/div[2]/ui-view/div/div[2]/div/div/ul/li[2]/ul/ng-include/li[2]/a";
 public static final String reports3Row = "";
 public static final String reportsContacts = "";
 public static final String reports1RowNameSurname = "css=div.conact-page-name.ng-binding";
 public static final String reports1RowCountry = ".//*[@class='contact-page-country ng-binding'][contains(.,'Ireland')]";
 public static final String reportsFiltersFreeText = "//pkm-filtering//input";

 public static final String reportsContactTailsPath = "";
 public static final String reportsContactRowName = "same";
 public static final String reportsContactRowSurname = "same";
 public static final String reportsContactName = "//*[@class='name ng-binding']";
 public static final String reportsContactEmail = "//span[@ng-if='contact.email']";
 public static final String reportsContactCountry = "//span[@ng-if='contact.country']";
 public static final String reportsContactLinkedCompany = "//span[@ng-if='contact.company']";
 public static final String reportsContactProjects = "//span[@ng-switch='contact.number_of_projects']";
 public static final String reportsContactCharges = "//span[@ng-switch='!contact.total_charges']";

 public static final String reportsContactCheckbox = "//input[@type='checkbox']";
 public static final String reportsContactNewProject = "//button[contains(.,'+ New Project')]";
 public static final String reportsContactEdit = "//div[@class='contact-page-invite-edit']/i[1]";
 public static final String reportsContactDelete = "//div[@class='contact-page-invite-edit']/i[2]";


 public static final String reportsFilters = "";
 public static final String reportsFiltersButtonClear = "//button[@ng-click='clearFilters()']";
 public static final String reportsFiltersButtonSearch = "//button[@ng-click='applyFilters()']";
 public static final String reportsFiltersButtonAdvanced = "";
 public static final String reportsFiltersButtonSaveSearch = "";
 public static final String reportsFiltersButtonSaveAs = "";
 public static final String reportsFiltersButtonUpdate = "";
 public static final String reportsFiltersButtonMailingList = "";
 public static final String reportsFiltersButtonDelete = "";
 public static final String reportsFiltersButton = "";
// public static final String reportsFiltersButton = "";
// public static final String reportsFiltersButton = "";

 public static final String mwMailingList_ = "";

 public static final String reports = "";

 public static final String mwContact_ = "";
 public static final String mwContact_SelectType = "//select[@name='type']";
 public static final String mwContact_FieldEntity = "//input[@name='legal_entity_name']";
 public static final String mwContact_Field = "";
 public static final String mwContact_SelectCountry = "//div[@name='country']/div/span";
 public static final String mwContact_SelectCountryName = "//div[@name='country']/div/span/span/span";
 public static final String mwContact_InputCountry = "//div[@name='country']/input";
 public static final String mwMergeContact_ = "";
 public static final String mwMergeContact_Select = "//div[@class='modal-body']//span[text()='Pick one...']";
 public static final String mwMergeContact_Input = "//div[@class='modal-body']//input[@type='search']";
// public static final String mwMergeContact_ = "";



 public static final String COMMENT_009 = "External Resourses locators";
 public static final String ext = "???";
 public static final String extGmail = "";
 public static final String extBox = "";

 public static final String extXeroEmail = "//input[@id='email']";
 public static final String extXeroPassword = "//input[@id='password']";
 public static final String extXeroLogin = "//button[@id='submitButton']";
 public static final String extXeroAccept = "id=submit-button";
 public static final String extXeroBillTotal = "//input[@id='invoiceTotal']";
 public static final String mwXero = "";
 public static final String mwXeroTitle = "";


 public static final String box = "";
 public static final String boxLoginURL = "https://account.box.com/login";
 public static final String boxFilesURL = "https://app.box.com/files";
 public static final String boxLogoutURL = "https://app.box.com/logout";
 public static final String boxPasswUser = "32qew8127a12a";
 public static final String boxPasswUser01 = "";
 public static final String boxPasswUser02 = "";
 public static final String boxPasswUser03 = "";

 public static final String boxConnectDashboardButton = "link=boost my document management";
 public static final String boxConnectStorageButton = "//button[text()='Connect Box']";
 public static final String boxConnectProjectButton = "link=Connect Box";

 public static final String boxWindow = "";
 public static final String boxWindowEmail = "name=login";
 public static final String boxWindowPassword = "name=password";
 public static final String boxWindowSubmit = "name=login_submit";
 public static final String boxWindowAccept = "//button[@id='consent_accept_button']";
 public static final String boxWindowReject = "//input[@id='consent_reject_button']";
 public static final String boxSubmit = "//button[@type='submit']";

 public static final String boxList = "//ul[@id='item-list']/li";
 public static final String boxItemName = "//div[@class='item-name-container']";

 public static final String boxNameFolderTeam1 = "Pekama - Qweeco01 (QT01)";
 public static final String boxNameFolderTeam2 = "Pekama - Qweeco02 (QT01)";
 public static final String boxNameFolderTeam3 = "Pekama - Qweeco03 (QT01)";
 public static final String boxNameFolderProject = "";
 public static final String boxNameFolderInner = "";
 public static final String boxNameFileInner = "";
 public static final String boxPlaceholderPath = "//div[@id='empty-folder-content']/span[2]";
 public static final String boxNoFilesPlaceholder = "Drag and drop files from your desktop or use the file browser.";

 public static final String NAMES = "USER DATA";
// public static final String name = "";
 public static final String nameProjectInTM = "AUTOMATED TEST TRADEMARK";
 public static final String nameProjectTypeTM = "Trademark";
 public static final String nameDefiningTM = "United Kingdom";
 public static final String nameTypeTM = "Paris Convention";
 public static final String nameSubTypeTM = "";
 public static final String nameSubSubTypeTM = "";
 public static final String nameEventTypeTM = "Trademark Registered";

 public static final String nameProjectTemplateTM = "Trademark project template";
 public static final String nameProjectTemplateGeneral = "General project template";
 public static final String nameTaskTemplateSet_inTM = "Task template set in Patent - United Kingdom";
 public static final String nameTaskTemplate_inTM = "Automated task deployed in Trademark";
 public static final String nameEventTemplateSet_inTM = "Event template set in Patent - United Kingdom";
 public static final String nameEventTemplate_inTM = "Priority Application FIled";
 public static final String nameDocumentTemplateSet_inTM = "";
 public static final String nameMsgTemplateSet = "AUTO-TEST MESSAGE";

 public static final String nameProjectTemporary = "New project (temporary name)";
 public static final String nameProject = "Test Project";
 public static final String nameProjectBOX = "BOX Test Project";
 public static final String nameEventInfo = "Test Event";
 public static final String nameTask = "Test Task";
 public static final String nameDocument = "Test Document";
 public static final String nameCharges = "Test Charges";
 public static final String nameMessage = "Test Message";
 public static final String nameThreadTitle = "Test Thread Title";
 public static final String nameThreadSubject = "Test Thread Subject";
 public static final String nameFolder = "Test Folder";
 public static final String nameFile = "Test File";
 public static final String nameBefore = "Before";
 public static final String nameEdited = "Edited";

 public static final String nameCountryUK = "United Kingdom";
 public static final String nameCountryIreland = "Ireland";
 public static final String nameCountryUSA = "United States of America";
 public static final String nameContactName = "Name";
 public static final String nameContactSurname = "Surname";
 public static final String nameContactLegal = "Legal Entity";

 public static final String nameContactCompanyEmail = "LegalEntity@dot.com";
 public static final String nameContactCompanyTel = "";
 public static final String nameContactCompanyFax = "";
 public static final String nameContactCompanyMobile = "";
 public static final String nameContactCompanyAddress = "Guido Fawkes av. 113b";
 public static final String nameContactCompanyPostal = "12345";
 public static final String nameContactCompanyCity = "";
 public static final String nameContactCompanyRegion = "";

 public static final String nameContactPersonEmail = "Person@email.com";
 public static final String nameContactPersonTel = "1234567890";
 public static final String nameContactPersonFax = "0987654321";
 public static final String nameContactPersonMobile = "+44 75 18 12 00";
 public static final String nameContactPersonAddress = "";
 public static final String nameContactPersonPostal = "67890";
 public static final String nameContactPersonCity = "London";
 public static final String nameContactPersonRegion = "13 District";
 public static final String nameContactCompany = "";

 public static final String nameTeamConversation = "Thread in Talk to your team";
 public static final String nameExterlalConversation = "Thread in Talk to your client/3rd party";
 public static final String name = "";

 public static final String mwTitleTaskTemplateSet = "Task Template Set";
 public static final String mwTitleNewFolder = "Add new folder";
 public static final String mwTitleNewTask = "Add new task";
 public static final String mwTitleEvent = "Event";
 public static final String mwTitle = "";
 public static final String placeholderNoFiles = "No files found. Upload your first file.";
 public static final String placeholderEmptyList = "Empty list.";
 public static final String placeholderNoNumbers = "No numbers yet. Use the form below to create one.";
 public static final String placeholderNoProjects = "No projects yet. Create your first project";
 public static final String placeholderNoData = "No data available.";
 public static final String placeholderPicOne = "Pick one...";
 public static final String placeholderTeamChat = "Team chat is great for conversations between groups of people, where all the group members should see the conversation all the time.";
 public static final String placeholderExternalEmails = "Client emails are great to send completely standard-looking emails to clients/3rd party, but allow your team to see the conversation.";
 public static final String placeholderNoCommunityCases = "No community cases.";
 public static final String placeholder = "";

 public static final String errorMsg = "";
 public static final String errorDuplicatedFolder = "Folder with this Parent and Name already exists.";
 public static final String error = "";
// public static final String error = "";
// public static final String error = "";
// public static final String error = "";

 public static final String mwTaskImportanceDeadline = "//span[text()='Deadline']";
 public static final String mwTaskImportanceFatal = "//span[text()='Fatal']";
 public static final String mwTaskImportanceFinalDeadline = "//span[text()='Final Deadline']";
 public static final String mwTaskImportanceReminder = "//span[text()='Reminder']";
 public static final String mwTaskImportanceTask = "//span[text()='Task']";
 public static final String mwTaskStatusNew = "//span[text()='New']";
 public static final String mwTaskStatusInProgress = "//span[text()='In Progress']";
 public static final String mwTaskStatusCompleted = "//span[text()='Completed']";
 public static final String mwTaskStatusApproved = "//span[text()='Approved']";
 public static final String mwTaskStatusRejected = "//span[text()='Rejected']";
 public static final String mwTaskStatusCancelled = "//span[text()='Cancelled']";

 public static final String COMMENT_COMMUNITY = "COMMUNITY";
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


 public static final String COMMENT_END = "END";




}
