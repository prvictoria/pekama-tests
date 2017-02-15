package Page;

import com.codeborne.selenide.SelenideElement;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaReports extends Page {

    public static final SelenideElement REPORTS_AllCheckbox = $(byXpath("//li[@class='items-header clearfix clearfix']//input[@type='checkbox']/following-sibling::i"));
    public static final SelenideElement REPORTS_DELETE = $(byXpath("//button[@class='btn-link'][contains(.,'Delete')]"));
    public static final SelenideElement REPORTS_MERGE = $(byXpath("//button[@class='btn-link'][contains(.,'Merge')]"));
    public static final SelenideElement REPORTS_NoData = $(byXpath("//div/div[2][@class='alert alert-empty ng-binding ng-scope']"));
    public static final SelenideElement REPORTS_Spinner = $(byXpath("//i[@class='pkm-icon-spinner icon-spin']"));
    public static final SelenideElement REPORTS_BTN_List = $(byXpath("//button[contains(.,'List')]"));
    public static final SelenideElement REPORTS_BTN_Report = $(byXpath("//button[contains(.,'Report')]"));
    public static final SelenideElement REPORTS_BTN_New = $(byXpath("//button[@type='button'][contains(.,'New')]"));
    public static final SelenideElement REPORTS_BTN_Import = $(byXpath("//button[@type='button'][contains(.,'Import')]"));
    public static final SelenideElement REPORTS_BTN_AddContact = $(byXpath("//button[@type='button'][contains(.,'Add contact')]"));
//list view
    public static final SelenideElement REPORTS_SORT_BY_NONE = $(byLinkText("None"));
    public static final SelenideElement REPORTS_SORT_BY_NAME = $(byLinkText("Name"));
    public static final SelenideElement REPORTS_SORT_BY_PROJECT_TYPE = $(byLinkText("Project Type"));
    public static final SelenideElement REPORTS_SORT_BY_COUNTRY = $(byLinkText("Country"));
    public static final SelenideElement REPORTS_SORT_BY_STATUS = $(byLinkText("Status"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_CREATED = $(byLinkText("Last created"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_MODIFIED = $(byLinkText("Last modified"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_TYPE = $(byLinkText("Type"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_SUB_TYPE = $(byLinkText("Sub Type"));

    //detailes view
    public static final String REPORTS_ListRow = "//ng-include[@src='reportParams.listTemplate']/li";
    public static final String REPORTS_ListRow01 = "//ng-include[@src='reportParams.listTemplate']/li[1]";
    public static SelenideElement REPORTS_LIST_PROJECT_TILE_ROW1 = $(byXpath(REPORTS_ListRow01+"//h4"));
    public static SelenideElement REPORTS_LIST_PROJECT_SELECT_ROW1 = $(byXpath(REPORTS_ListRow01+"//input[@type='checkbox']/following-sibling::i"));

    public static final SelenideElement REPORTS_Contacts = $(byXpath(""));
    public static final SelenideElement REPORTS_1RowNameSurname = $("div.conact-page-name.ng-binding");
    public static final SelenideElement REPORTS_1RowCountry = $(byXpath(".//*[@class='contact-page-country ng-binding'][contains(.,'Ireland')]"));
    public static final SelenideElement REPORTS_FiltersFreeText = $(byXpath("//pkm-filtering//input"));

//search sidebar - all controls
    public static final SelenideElement REPORTS_SEARCH_SIDEBAR = $(byXpath("//*[@class='search-sidebar']"));
    public static final SelenideElement REPORTS_MAILING_AREA = $(byXpath("//*[@class='saved']"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH = $(byXpath("//*[@type='button' and contains(.,'Save search...')]"));
    public static final String REPORTS_MAILING_LISTS = "//*[@class='search-list']";
    public static SelenideElement reportName;
    public static SelenideElement REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = $(byXpath("//li[//a[contains(.,'"+ reportName +"')]]")); //ML+
    public static SelenideElement REPORTS_MAILING_LISTS_BTN_UPDATE = $(byXpath("//button[contains(.,'Update')]"));
    public static String REPORTS_MAILING_LISTS_BTN_CALL_ML = "//button[@uib-dropdown-toggle]";
    public static SelenideElement REPORTS_MAILING_LISTS_CALL_MW = $(byLinkText("Mailing List"));
    public static SelenideElement REPORTS_MAILING_LISTS_DELETE_MW = $(byLinkText("Delete"));

    public static final String REPORTS_MAILING_SAVE_SEARCH_DROPDOWN = "//*[@class='save-search-dropdown']";
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//input"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[text()='Save']"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE_NEW = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[text()='Save as New']"));
// //button[text()='Save as New']
//    public static final SelenideElement REPORTS_MAILING_ = $(byXpath(""));
//    public static final SelenideElement REPORTS_MAILING_ = $(byXpath(""));

    public static final SelenideElement REPORTS_FiltersButtonClear = $(byXpath("//button[@ng-click='clearFilters()']"));
    public static final SelenideElement REPORTS_FiltersButtonSearch = $(byXpath("//button[@ng-click='applyFilters()']"));
    public static final SelenideElement REPORTS_FiltersButtonAdvanced = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonSaveSearch = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonSaveAs = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonUpdate = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonMailingList = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonDelete = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButton = $(byXpath(""));

    public static final SelenideElement REPORTS_ = $(byXpath(""));
//Specific pages
    
    public static String REPORTS_ContactRowByCount = "//ng-include[@src='reportParams.listTemplate']/li[%s]";
    public static final String REPORTS_ContactRowName = "";
    public static final String REPORTS_ContactRowSurname = "";
    public static final String REPORTS_ContactNameSurname = "//*[@class='name ng-binding']";
    public static final String REPORTS_ContactEmail = "//span[@ng-if='contact.email']";
    public static final String REPORTS_ContactCountry = "//span[@ng-if='contact.country']";
    public static final String REPORTS_ContactLinkedCompany = "//span[@ng-if='contact.company']";
    public static final String REPORTS_ContactProjects = "//span[@ng-switch='contact.number_of_projects']";
    public static final String REPORTS_ContactCharges = "//span[@ng-switch='!contact.total_charges']";
    public static final String REPORTS_ContactCheckbox = "//input[@type='checkbox']";
    public static final String REPORTS_ContactNewProject = "//button[contains(.,'+ New ProjectValues')]";
    public static final String REPORTS_ContactEdit = "//div[@class='contact-page-invite-edit']/i[1]";
    public static final String REPORTS_ContactDelete = "//div[@class='contact-page-invite-edit']/i[2]";
}
