package Page;

import com.codeborne.selenide.SelenideElement;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaReports extends Page {

    public static final SelenideElement REPORTS_AllCheckbox = $(byXpath("//li[@class='items-header clearfix clearfix']//input[@type='checkbox']"));
    public static final SelenideElement REPORTS_BulkDelete = $(byXpath("//button[@class='btn-link'][contains(.,'Delete')]"));
    public static final SelenideElement REPORTS_BulkMerge = $(byXpath("//button[@class='btn-link'][contains(.,'Merge')]"));
    public static final SelenideElement REPORTS_NoData = $(byXpath("//div/div[2][@class='alert alert-empty ng-binding ng-scope']"));
    public static final SelenideElement REPORTS_Spinner = $(byXpath("//i[@class='pkm-icon-spinner icon-spin']"));
    public static final SelenideElement REPORTS_BtnList = $(byXpath("//button[contains(.,'List')]"));
    public static final SelenideElement REPORTS_BtnReport = $(byXpath("//button[contains(.,'Report')]"));
    public static final SelenideElement REPORTS_BtnNew = $(byXpath("//button[@type='button'][contains(.,'New')]"));
    public static final SelenideElement REPORTS_BtnImport = $(byXpath("//button[@type='button'][contains(.,'Import')]"));
    public static final SelenideElement REPORTS_BtnAddContact = $(byXpath("//button[@type='button'][contains(.,'Add contact')]"));
//list view
//detailes view
    public static final SelenideElement REPORTS_ListRow01 = $(byXpath("//ng-include[@src='reportParams.listTemplate']/li[1]"));
    public static final SelenideElement REPORTS_ListRow02 = $(byXpath("//ng-include[@src='reportParams.listTemplate']/li[2]"));
    public static final SelenideElement REPORTS_ListRow03 = $(byXpath("//ng-include[@src='reportParams.listTemplate']/li[3]"));
    public static final SelenideElement REPORTS_ListRow04 = $(byXpath("//ng-include[@src='reportParams.listTemplate']/li[4]"));
    public static final SelenideElement REPORTS_ListRow05 = $(byXpath("//ng-include[@src='reportParams.listTemplate']/li[5]"));

    public static final SelenideElement REPORTS_1RowCheckbox = $(byXpath(""));
    public static final SelenideElement REPORTS_2RowCheckbox = $(byXpath(""));
    public static final SelenideElement REPORTS_3RowCheckbox = $(byXpath(""));
    public static final SelenideElement REPORTS_4RowCheckbox = $(byXpath(""));
    public static final SelenideElement REPORTS_5RowCheckbox = $(byXpath(""));
    public static final SelenideElement REPORTS_1Row = $(byXpath("//section[@id='page']/div[2]/ui-view/div/div[2]/div/div/ul/li[2]/ul/ng-include/li/a"));
    public static final SelenideElement REPORTS_2Row = $(byXpath("//section[@id='page']/div[2]/ui-view/div/div[2]/div/div/ul/li[2]/ul/ng-include/li[2]/a"));
    public static final SelenideElement REPORTS_3Row = $(byXpath(""));
    public static final SelenideElement REPORTS_Contacts = $(byXpath(""));
    public static final SelenideElement REPORTS_1RowNameSurname = $(byXpath("css=div.conact-page-name.ng-binding"));
    public static final SelenideElement REPORTS_1RowCountry = $(byXpath(".//*[@class='contact-page-country ng-binding'][contains(.,'Ireland')]"));
    public static final SelenideElement REPORTS_FiltersFreeText = $(byXpath("//pkm-filtering//input"));

//search sidebar - all controls
    public static final SelenideElement REPORTS_SEARCH_SIDEBAR = $(byXpath("//*[@class='search-sidebar']"));
    public static final SelenideElement REPORTS_MAILING_AREA = $(byXpath("//*[@class='saved']"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH = $(byXpath("//*[@type='button' and contains(.,'Save search...')]"));
    public static final SelenideElement REPORTS_MAILING_LISTS = $(byXpath("//*[@class='search-list']"));
    public static SelenideElement reportName;
    public static SelenideElement REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = $(byXpath("//li[//a[contains(.,'"+ reportName +"')]]")); //ML+
    public static SelenideElement REPORTS_MAILING_LISTS_BTN_UPDATE = $(byXpath("//button[contains(.,'Update')]")); //REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+
    public static SelenideElement REPORTS_MAILING_LISTS_BTN_CALL_ML = $(byXpath("//button[@uib-dropdown-toggle]")); //REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+
    public static SelenideElement REPORTS_MAILING_LISTS_CALL_MW = $(byLinkText("Mailing List"));
    public static SelenideElement REPORTS_MAILING_LISTS_DELETE_MW = $(byLinkText("Delete"));

    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN = $(byXpath("//*[@class='save-search-dropdown']"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//input"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[contains(.,'Save')]"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE_NEW = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[contains(.,'Save as New')]"));

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
    public static final SelenideElement REPORTS_ContactTailsPath = $(byXpath(""));
    public static final SelenideElement REPORTS_ContactRowName = $(byXpath("same"));
    public static final SelenideElement REPORTS_ContactRowSurname = $(byXpath("same"));
    public static final SelenideElement REPORTS_ContactName = $(byXpath("//*[@class='name ng-binding']"));
    public static final SelenideElement REPORTS_ContactEmail = $(byXpath("//span[@ng-if='contact.email']"));
    public static final SelenideElement REPORTS_ContactCountry = $(byXpath("//span[@ng-if='contact.country']"));
    public static final SelenideElement REPORTS_ContactLinkedCompany = $(byXpath("//span[@ng-if='contact.company']"));
    public static final SelenideElement REPORTS_ContactProjects = $(byXpath("//span[@ng-switch='contact.number_of_projects']"));
    public static final SelenideElement REPORTS_ContactCharges = $(byXpath("//span[@ng-switch='!contact.total_charges']"));
    public static final SelenideElement REPORTS_ContactCheckbox = $(byXpath("//input[@type='checkbox']"));
    public static final SelenideElement REPORTS_ContactNewProject = $(byXpath("//button[contains(.,'+ New ProjectValues')]"));
    public static final SelenideElement REPORTS_ContactEdit = $(byXpath("//div[@class='contact-page-invite-edit']/i[1]"));
    public static final SelenideElement REPORTS_ContactDelete = $(byXpath("//div[@class='contact-page-invite-edit']/i[2]"));
}
