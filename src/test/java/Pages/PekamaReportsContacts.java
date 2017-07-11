package Pages;

import com.codeborne.selenide.SelenideElement;

import static Steps.StepsPekamaReports.elementInRowListReport;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PekamaReportsContacts extends PekamaReports {
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
