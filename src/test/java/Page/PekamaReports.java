package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static Steps.StepsPekamaReports.elementInRowListReport;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaReports extends Page {

    public static final SelenideElement REPORTS_PAGE_TITLE_PANEL = $(byXpath("//div[@class='content-col']//div[@class='panel-heading']//h4"));
    public static final SelenideElement REPORTS_ALL_CHECKBOX = $(byXpath("//li[@class='items-header clearfix clearfix']//input[@type='checkbox']/following-sibling::i"));
    public static final SelenideElement REPORTS_DELETE = $(byXpath("//button[@class='btn-link'][contains(.,'Delete')]"));
    public static final SelenideElement REPORTS_EVENTS_DELETE = $(byXpath("//*[@href][contains(.,'Delete')]"));
    public static final SelenideElement REPORTS_MERGE = $(byXpath("//button[@class='btn-link'][contains(.,'Merge')]"));
    public static final SelenideElement REPORTS_PLACEHOLDER_NO_DATA = $(byXpath("//ul//*[@class='alert alert-empty ng-binding ng-scope']"));
    public static final SelenideElement REPORTS_Spinner = $(byXpath("//i[@class='pkm-icon-spinner icon-spin']"));
    public static final SelenideElement REPORTS_BTN_List = $(byXpath("//button[contains(.,'List')]"));
    public static final SelenideElement REPORTS_BTN_Report = $(byXpath("//button[contains(.,'Report')]"));
    public static final SelenideElement REPORTS_BTN_NEW_PROJECT = $(byXpath("//button[@type='button'][contains(.,'New')]"));
    public static final SelenideElement REPORTS_BTN_NEW_PROJECT_TEMPLATE = $(byXpath("//button[@type='button'][contains(.,'New')]/following-sibling::button"));
    public static final ElementsCollection REPORTS_PROJECT_TEMPLATES_LIST = $$(byXpath("//button[@type='button'][contains(.,'New')]/following-sibling::ul//a"));
    public static final SelenideElement REPORTS_BTN_IMPORT = $(byXpath("//button[@type='button'][contains(.,'Import')]"));
    public static final SelenideElement REPORTS_BTN_ADD_CONTACT = $(byXpath("//button[@type='button'][contains(.,'Add contact')]"));
    //list view
    public static final SelenideElement REPORTS_SORT_ORDER_TYPE = $(byXpath("//a[@class='sortable ng-binding dropdown-toggle']"));
    public static final SelenideElement REPORTS_SELECT_SORT_ORDER(String order){
        SelenideElement select = $(byXpath("//a[@class='sortable ng-binding dropdown-toggle']/following-sibling::ul//a[text()='"+order+"']"));
        return select;
    }
    public static final SelenideElement REPORTS_SORT_ORDER_DESCENDING = $(byXpath("//div[@class='dropdown sort desc']"));
    public static final SelenideElement REPORTS_SORT_ORDER_ASCENDING = $(byXpath("//div[@class='dropdown sort']"));

    public static final SelenideElement REPORTS_SORT_BY_NONE = $(byLinkText("None"));
    public static final SelenideElement REPORTS_SORT_BY_NAME = $(byLinkText("Name"));
    public static final SelenideElement REPORTS_SORT_BY_PROJECT_TYPE = $(byLinkText("Project Type"));
    public static final SelenideElement REPORTS_SORT_BY_COUNTRY = $(byLinkText("Country"));
    public static final SelenideElement REPORTS_SORT_BY_STATUS = $(byLinkText("Status"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_CREATED = $(byLinkText("Last created"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_MODIFIED = $(byLinkText("Last modified"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_TYPE = $(byLinkText("Type"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_SUB_TYPE = $(byLinkText("Sub Type"));
    public static String REPORTS_ROW_BY_INDEX_LIST(Integer rowCount) {
        String count = Integer.toString(rowCount);
        String row = String.format("//ng-include[@src='reportParams.listTemplate']/li[%s]", count);
        return row;
    }

    //report view
    public static final String REPORTS_LIST_ROWS_PATH = "//ng-include[@src='reportParams.listTemplate']/li";
    public static final ElementsCollection REPORTS_LIST_ROWS = $$(byXpath("//ng-include[@src='reportParams.listTemplate']/li"));

    public static final String REPORTS_ListRow01 = "//ng-include[@src='reportParams.listTemplate']/li[1]";
    public static SelenideElement REPORTS_LIST_PROJECT_TILE_ROW1 = $(byXpath(REPORTS_ListRow01+"//h4"));
    public static SelenideElement REPORTS_LIST_PROJECT_SELECT_ROW1 = $(byXpath(REPORTS_ListRow01+"//input[@type='checkbox']/following-sibling::i"));


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
    public static SelenideElement REPORTS_MAILING_LISTS_DELETE_MW = $(byXpath("//li[./a[text()='Mailing List']]//following-sibling::li/a[text()='Delete']"));

    public static final String REPORTS_MAILING_SAVE_SEARCH_DROPDOWN = "//*[@class='save-search-dropdown']";
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//input"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[text()='Save']"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE_NEW = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[text()='Save as New']"));

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

    //CHARGES
    public static String CHARGES_STATUS_NOT_BILLED = "not billed";
    public static String CHARGES_STATUS_BILLED = "billed";
    public static String CHARGES_STATUS_BILLED_AND_PAID = "billed and paid";
    public static final SelenideElement REPORTS_CHARGES_ROW(Integer rowCount) {
        final String FROM = "/a";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_DESCRIPTION(Integer rowCount) {
        final String FROM = "//h4";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_FROM(Integer rowCount) {
        final String FROM = "//ul/li[1]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_BY(Integer rowCount) {
        final String FROM = "//ul/li[1]/span";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_TO(Integer rowCount) {
        final String FROM = "//ul/li[2]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_DATE(Integer rowCount) {
        final String FROM = "//*[@class='date ng-binding']";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_TOTAL(Integer rowCount) {
        final String FROM = "//*[@class='footer']/div[1]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_TYPE(Integer rowCount) {
        final String FROM = "//*[@class='footer']/div[2]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_STATUS(Integer rowCount) {
        final String FROM = "//*[@class='footer']/div[3]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }


    //CONTACTS

    public static String REPORTS_CONTACT_ROW_BY_NAME(String contactName) {
        String row = String.format("//ng-include[@src='reportParams.listTemplate']/li[.//span[contains(.,'%s')]]", contactName);
        return row;
    }

    public static final String REPORTS_CONTACT_ROW_SELECT = "//label[./input[@type='checkbox']]";
    public static final String REPORTS_CONTACT_ROW_NAME = "//*[@class='name ng-binding']";
    public static final String REPORTS_CONTACT_ROW_EMAIL = "//span[@ng-if='contact.email']";
    public static final String REPORTS_CONTACT_ROW_COUNTRY = "//span[@ng-if='contact.country']";
    public static final String REPORTS_CONTACT_ROW_COMPANY = "//span[@ng-if='contact.company']";
    public static final String REPORTS_CONTACT_ROW_PROJECTS = "//span[@ng-switch='contact.number_of_projects']/*";
    public static final String REPORTS_CONTACT_ROW_CHARGES_TOTAL = "//span[@ng-switch='!contact.total_charges']/*";
    public static final String REPORTS_CONTACT_ROW_RELATIONS = "//*[@class='name ng-binding']/following-sibling::*";


    public static final String REPORTS_CONTACTS_EDIT_BTN = "//button[./i[@class='pkm-icon-edit']]";
    public static final String REPORTS_CONTACTS_DELETE_BTN = "//button[./i[@class='pkm-icon-delete']]";
    public static final String REPORTS_CONTACTS_NEW_PROJECT_BTN = "//button[text()='+ New Project']";
    public static final String REPORTS_CONTACT_FORM_SAVE = "//button[text()='Save']";

    public static final SelenideElement REPORTS_CONTACT_CONTACT_TYPE(Integer rowCount) {
        final String REPORTS_CONTACT_CONTACT_TYPE = "//select";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_CONTACT_TYPE);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_LEGAL_ENTITY(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_LEGAL_ENTITY = "//input[@name='legal_entity_name']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_LEGAL_ENTITY);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_FIRST_NAME(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_FIRST_NAME = "//input[@name='first_name']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_FIRST_NAME);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_LAST_NAME(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_LAST_NAME = "//input[@name='last_name']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_LAST_NAME);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_SELECT_COMPANY(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_SELECT_COMPANY = "//label[text()='Company']/following-sibling::div//span";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_SELECT_COMPANY);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_INPUT_COMPANY(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_INPUT_COMPANY = "//label[text()='Company']/following-sibling::div//input[@type='search']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_INPUT_COMPANY);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_EMAIL(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_EMAIL = "//input[@name='email']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_EMAIL);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_PHONE(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_PHONE = "//input[@name='phone_number']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_PHONE);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_FAX(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_FAX = "//input[@name='fax_number']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_FAX);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_MOBILE(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_MOBILE = "//input[@name='cellphone_number']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_MOBILE);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_ADDRESS(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_ADDRESS = "//input[@name='street_address']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_ADDRESS);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_ZIP(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_ZIP = "//input[@name='postal_code']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_ZIP);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_CITY(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_CITY = "//input[@name='city']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_CITY);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_REGION(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_REGION = "//input[@name='region']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_REGION);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_SELECT_COUNTRY(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_SELECT_COUNTRY = "//div[@name='country']/div/span";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_SELECT_COUNTRY);
        return field;
    }
    public static final SelenideElement REPORTS_CONTACT_FORM_INPUT_COUNTRY(Integer rowCount) {
        final String REPORTS_CONTACT_FORM_INPUT_COUNTRY = "//div[@name='country']/input[@type='search']";
        SelenideElement field = elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_INPUT_COUNTRY);
        return field;
    }


}
