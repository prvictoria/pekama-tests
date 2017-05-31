package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.PekamaReportsContacts.*;
import static Page.TestsStrings.*;
import static Page.UrlStrings.*;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
import static Steps.StepsPekamaReports.saveContactForm;

/**
 * Created by VatslauX on 14-May-17.
 */
public class ObjectContact {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String contact = null;
    public String contactOwner = null;
    public String contactType = null;
    public String contactLegalEntity = null;
    public String contactFirstName = null;
    public String contactLastName = null;
    public String contactCompany = null;
    public String contactEmail = null;
    public String contactPhone = null;
    public String contactFax = null;
    public String contactMobile = null;
    public String contactStreetAddress = null;
    public String contactPostalCode = null;
    public String contactCity = null;
    public String contactRegion = null;
    public String contactCountry = null;
    public String contactNameSurname = contactFirstName+" "+contactLastName;
    public String[] contactProjects = null;
    public String contactProject = null;
    public String[] contactRelations = null;
    public String contactRelation = null;

    public ObjectContact(Builder builder) {
        contact = builder.contact;
        contactOwner = builder.contactOwner;
        contactType = builder.contactType;
        contactLegalEntity = builder.contactLegalEntity;
        contactFirstName = builder.contactFirstName;
        contactLastName = builder.contactLastName;
        contactCompany = builder.contactCompany;
        contactEmail = builder.contactEmail;
        contactPhone = builder.contactPhone;
        contactFax = builder.contactFax;
        contactMobile = builder.contactMobile;
        contactStreetAddress = builder.contactStreetAddress;
        contactPostalCode = builder.contactPostalCode;
        contactCity = builder.contactCity;
        contactRegion = builder.contactRegion;
        contactCountry = builder.contactCountry;
        contactNameSurname = builder.contactNameSurname;
        contactProjects = builder.contactProjects;
        contactProject = builder.contactProject;
        contactRelations = builder.contactRelations;
        contactRelation = builder.contactRelation;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public enum contactType {PERSON, COMPANY}
    public enum enterPoint {PROJECT, REPORT}

    public void setValues(String contactType, String contactLegalEntity, String contactFirstName, String contactLastName, String contactEmail){
        this.contactType = contactType;
        this.contactLegalEntity = contactLegalEntity;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactEmail = contactEmail;
    }
    @Overloaded
    public void fillModalForm(
            String contactType,
            String contactLegalEntity,
            String contactFirstName,
            String contactLastName,
            String contactCompany,
            String contactEmail,
            String contactPhone,
            String contactFax,
            String contactMobile,
            String contactStreetAddress,
            String contactPostalCode,
            String contactCity,
            String contactRegion,
            String contactCountry
    )
    {
        this.contactType = contactType;
        this.contactLegalEntity = contactLegalEntity;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactCompany = contactCompany;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.contactFax = contactFax;
        this.contactMobile = contactMobile;
        this.contactStreetAddress = contactStreetAddress;
        this.contactPostalCode = contactPostalCode;
        this.contactCity = contactCity;
        this.contactRegion = contactRegion;
        this.contactCountry = contactCountry;
        this.contactNameSurname = contactFirstName+" "+contactLastName;

        waitForModalWindow(TITLE_MW_CONTACT);
        if(contactType!=null){
            if(contactType.equals("Person")){
                MW_Contact_Select_CONTACT_TYPE.selectOptionByValue("Person");
            }
            if(contactType.equals("Company")){
                MW_Contact_Select_CONTACT_TYPE.selectOptionByValue("Company");
            }
            if (contactType.equals("Person")==false && contactType.equals("Company")==false) {
                Assert.fail("Invalid contact type");
            }
        }
        if(contactLegalEntity!=null){
            fillField(MW_Contact_Entity,  contactLegalEntity);
        }
        if(contactFirstName!=null){
            fillField(MW_Contact_NAME, contactFirstName);
        }
        if(contactLastName!=null){
            fillField(MW_Contact_SURNAME, contactLastName);
        }
        if(contactCompany!=null){
            selectItemInDropdown(
                    MW_Contact_Select_COMPANY,
                    MW_Contact_Input_COMPANY,
                    contactCompany
            );
        }
        if(contactEmail!=null){
            fillField(MW_Contact_EMAIL, contactEmail);
        }
        if(contactPhone!=null){
            fillField(MW_Contact_PHONE, contactPhone);
        }
        if(contactFax!=null){
            fillField(MW_Contact_FAX, contactFax);
        }
        if(contactMobile!=null){
            fillField(MW_Contact_MOBILE, contactMobile);
        }
        if(contactStreetAddress!=null){
            fillField(MW_Contact_STREET, contactStreetAddress);
        }
        if(contactPostalCode!=null){
            fillField(MW_Contact_ZIP, contactPostalCode);
        }
        if(contactCity!=null){
            fillField(MW_Contact_CITY, contactCity);
        }
        if(contactRegion!=null){
            fillField(MW_Contact_REGION, contactRegion);
        }
        if(contactCountry!=null){
            selectItemInDropdown(
                    MW_Contact_SelectCountry,
                    MW_Contact_InputCountry,
                    contactCountry);
        }
    }
    @Overloaded
    public void fillModalForm() {
        this.contactNameSurname = contactFirstName+" "+contactLastName;

        waitForModalWindow(TITLE_MW_CONTACT);
        if(this.contactType!=null){
            if(this.contactType.equals("Person")){
                MW_Contact_Select_CONTACT_TYPE.selectOptionByValue("Person");
            }
            if(this.contactType.equals("Company")){
                MW_Contact_Select_CONTACT_TYPE.selectOptionByValue("Company");
            }
            if (this.contactType.equals("Person")==false && this.contactType.equals("Company")==false) {
                Assert.fail("Invalid contact type");
            }
        }
        if(this.contactLegalEntity!=null){
            fillField(MW_Contact_Entity,  this.contactLegalEntity);
        }
        if(this.contactFirstName!=null){
            fillField(MW_Contact_NAME, this.contactFirstName);
        }
        if(this.contactLastName!=null){
            fillField(MW_Contact_SURNAME, this.contactLastName);
        }
        if(this.contactCompany!=null){
            selectItemInDropdown(
                    MW_Contact_Select_COMPANY,
                    MW_Contact_Input_COMPANY,
                    this.contactCompany
            );
        }
        if(this.contactEmail!=null){
            fillField(MW_Contact_EMAIL, this.contactEmail);
        }
        if(this.contactPhone!=null){
            fillField(MW_Contact_PHONE, this.contactPhone);
        }
        if(this.contactFax!=null){
            fillField(MW_Contact_FAX, this.contactFax);
        }
        if(this.contactMobile!=null){
            fillField(MW_Contact_MOBILE, this.contactMobile);
        }
        if(this.contactStreetAddress!=null){
            fillField(MW_Contact_STREET, this.contactStreetAddress);
        }
        if(this.contactPostalCode!=null){
            fillField(MW_Contact_ZIP, this.contactPostalCode);
        }
        if(this.contactCity!=null){
            fillField(MW_Contact_CITY, this.contactCity);
        }
        if(this.contactRegion!=null){
            fillField(MW_Contact_REGION, this.contactRegion);
        }
        if(this.contactCountry!=null){
            selectItemInDropdown(
                    MW_Contact_SelectCountry,
                    MW_Contact_InputCountry,
                    this.contactCountry);
        }
    }
    public void createContact(enterPoint enterPoint){
        rootLogger.info("Create new contact");
        if(enterPoint==PROJECT){
            if(contactFirstName!=null) {
                callNewContactModal(contactFirstName);
            }
            else {
                Assert.fail("First name is mandatory");
            }
        }
        if(enterPoint==REPORT){
            String url = getActualUrl();
            if(url.equals(URL_ReportsContacts)==false){
                openPageWithSpinner(URL_ReportsContacts);
            }
            submitEnabledButton(REPORTS_BTN_ADD_CONTACT);
        }
        fillModalForm();
        submitEnabledButton(MW_BTN_OK);
    }
    @Overloaded
    public void createPerson(enterPoint enterPoint,
                             String contactLegalEntity,
                             String contactFirstName,
                             String contactLastName,
                             String contactCompany,
                             String contactEmail,
                             String contactPhone,
                             String contactFax,
                             String contactMobile,
                             String contactStreetAddress,
                             String contactPostalCode,
                             String contactCity,
                             String contactRegion,
                             String contactCountry
                     ){
        rootLogger.info("Create new contact");
        if(enterPoint==PROJECT){
            if(contactFirstName!=null) {
                callNewContactModal(contactFirstName);
            }
            else {
                Assert.fail("First name is mandatory");
            }
        }
        if(enterPoint==REPORT){
            String url = getActualUrl();
            if(url.equals(URL_ReportsContacts)==false){
                openPageWithSpinner(URL_ReportsContacts);
            }
            submitEnabledButton(REPORTS_BTN_ADD_CONTACT);
        }
        fillModalForm( "Person",
                contactLegalEntity,
                contactFirstName,
                contactLastName,
                contactCompany,
                contactEmail,
                contactPhone,
                contactFax,
                contactMobile,
                contactStreetAddress,
                contactPostalCode,
                contactCity,
                contactRegion,
                contactCountry);
        submitEnabledButton(MW_BTN_OK);
    }

    @Overloaded
    public void createCompany(enterPoint enterPoint,
                       String contactLegalEntity,
                       String contactEmail,
                       String contactPhone,
                       String contactFax,
                       String contactMobile,
                       String contactStreetAddress,
                       String contactPostalCode,
                       String contactCity,
                       String contactRegion,
                       String contactCountry
    )
    {
        rootLogger.info("Create new contact");
        if(enterPoint==PROJECT){
            if(contactFirstName!=null) {
                callNewContactModal(contactFirstName);
            }
            else {
                Assert.fail("First name is mandatory");
            }
        }
        if(enterPoint==REPORT){
            String url = getActualUrl();
            if(url.equals(URL_ReportsContacts)==false){
                openPageWithSpinner(URL_ReportsContacts);
            }
            submitEnabledButton(REPORTS_BTN_ADD_CONTACT);
        }
        fillModalForm( "Company",
                contactLegalEntity,
                null,
                null,
                null,
                contactEmail,
                contactPhone,
                contactFax,
                contactMobile,
                contactStreetAddress,
                contactPostalCode,
                contactCity,
                contactRegion,
                contactCountry);
        submitEnabledButton(MW_BTN_OK);
        logContactFields();
    }
    public void editForm(Integer rowCount,
                         String contactType,
                         String contactLegalEntity,
                         String contactFirstName,
                         String contactLastName,
                         String contactCompany,
                         String contactEmail,
                         String contactPhone,
                         String contactFax,
                         String contactMobile,
                         String contactStreetAddress,
                         String contactPostalCode,
                         String contactCity,
                         String contactRegion,
                         String contactCountry){
        this.contactType = contactType;
        this.contactLegalEntity = contactLegalEntity;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactCompany = contactCompany;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.contactFax = contactFax;
        this.contactMobile = contactMobile;
        this.contactStreetAddress = contactStreetAddress;
        this.contactPostalCode = contactPostalCode;
        this.contactCity = contactCity;
        this.contactRegion = contactRegion;
        this.contactCountry = contactCountry;
        this.contactNameSurname = contactFirstName+" "+contactLastName;

        if(contactType!=null){
            if(contactType.equals("Person")){
                REPORTS_CONTACT_CONTACT_TYPE(rowCount).selectOptionByValue("Person");
            }
            if(contactType.equals("Company")){
                REPORTS_CONTACT_CONTACT_TYPE(rowCount).selectOptionByValue("Company");
            }
            if (contactType.equals("Person")==false && contactType.equals("Company")==false) {
                Assert.fail("Invalid contact type");
            }
        }
        if(contactLegalEntity!=null){
            fillField(REPORTS_CONTACT_FORM_LEGAL_ENTITY(rowCount),  contactLegalEntity);
        }
        if(contactFirstName!=null){
            fillField(REPORTS_CONTACT_FORM_FIRST_NAME(rowCount), contactFirstName);
        }
        if(contactLastName!=null){
            fillField(REPORTS_CONTACT_FORM_LAST_NAME(rowCount), contactLastName);
        }
        if(contactCompany!=null){
            selectItemInDropdown(
                    REPORTS_CONTACT_FORM_SELECT_COMPANY(rowCount),
                    REPORTS_CONTACT_FORM_INPUT_COMPANY(rowCount),
                    contactCompany
            );
        }
        if(contactEmail!=null){
            fillField(REPORTS_CONTACT_FORM_EMAIL(rowCount) , contactEmail);
        }
        if(contactPhone!=null){
            fillField(REPORTS_CONTACT_FORM_PHONE(rowCount), contactPhone);
        }
        if(contactFax!=null){
            fillField(REPORTS_CONTACT_FORM_FAX(rowCount) , contactFax);
        }
        if(contactMobile!=null){
            fillField(REPORTS_CONTACT_FORM_MOBILE(rowCount), contactMobile);
        }
        if(contactStreetAddress!=null){
            fillField(REPORTS_CONTACT_FORM_ADDRESS(rowCount), contactStreetAddress);
        }
        if(contactPostalCode!=null){
            fillField(REPORTS_CONTACT_FORM_ZIP(rowCount) , contactPostalCode);
        }
        if(contactCity!=null){
            fillField(REPORTS_CONTACT_FORM_CITY(rowCount) , contactCity);
        }
        if(contactRegion!=null){
            fillField(REPORTS_CONTACT_FORM_REGION(rowCount), contactRegion);
        }
        if(contactCountry!=null){
            selectItemInDropdown(
                    REPORTS_CONTACT_FORM_SELECT_COUNTRY(rowCount),
                    REPORTS_CONTACT_FORM_INPUT_COUNTRY(rowCount),
                    contactCountry);
        }
        saveContactForm(rowCount);
    }
    public void getData(){

    }
    public static final Boolean validateError(){
        return true;
    }
    public static final Boolean validateContactRowInReports(ObjectContact contact){

        return true;
    }
    public ObjectContact logContactFields(){
        rootLogger.info("contactType: "+ this.contactType);
        rootLogger.info("contactLegalEntity: "+ this.contactLegalEntity);
        rootLogger.info("contactFirstName: "+ this.contactFirstName);
        rootLogger.info("contactLastName: "+ this.contactLastName);
        rootLogger.info("contactCompany: "+ this.contactCompany);
        rootLogger.info("contactEmail: "+ this.contactEmail);
        rootLogger.info("contactPhone: "+ this.contactPhone);
        rootLogger.info("contactFax: "+ this.contactFax);
        rootLogger.info("contactMobile: "+ this.contactMobile);
        rootLogger.info("contactStreetAddress: "+ this.contactStreetAddress);
        rootLogger.info("contactPostalCode: "+ this.contactPostalCode);
        rootLogger.info("contactCity: "+ this.contactCity);
        rootLogger.info("contactRegion: "+ this.contactRegion);
        rootLogger.info("contactCountry: "+ this.contactCountry);
        rootLogger.info("contactNameSurname: "+ this.contactFirstName+" "+contactLastName);
        return this;
    }


    public static final class Builder {
        private String contact;
        private String contactOwner;
        private String contactType;
        private String contactLegalEntity;
        private String contactFirstName;
        private String contactLastName;
        private String contactCompany;
        private String contactEmail;
        private String contactPhone;
        private String contactFax;
        private String contactMobile;
        private String contactStreetAddress;
        private String contactPostalCode;
        private String contactCity;
        private String contactRegion;
        private String contactCountry;
        private String contactNameSurname;
        private String[] contactProjects;
        private String contactProject;
        private String[] contactRelations;
        private String contactRelation;

        private Builder() {
        }

        public Builder contact(String contact) {
            this.contact = contact;
            return this;
        }

        public Builder contactOwner(String contactOwner) {
            this.contactOwner = contactOwner;
            return this;
        }

        public Builder contactType(String contactType) {
            this.contactType = contactType;
            return this;
        }

        public Builder contactLegalEntity(String contactLegalEntity) {
            this.contactLegalEntity = contactLegalEntity;
            return this;
        }

        public Builder contactFirstName(String contactFirstName) {
            this.contactFirstName = contactFirstName;
            return this;
        }

        public Builder contactLastName(String contactLastName) {
            this.contactLastName = contactLastName;
            return this;
        }

        public Builder contactCompany(String contactCompany) {
            this.contactCompany = contactCompany;
            return this;
        }

        public Builder contactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }

        public Builder contactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
            return this;
        }

        public Builder contactFax(String contactFax) {
            this.contactFax = contactFax;
            return this;
        }

        public Builder contactMobile(String contactMobile) {
            this.contactMobile = contactMobile;
            return this;
        }

        public Builder contactStreetAddress(String contactStreetAddress) {
            this.contactStreetAddress = contactStreetAddress;
            return this;
        }

        public Builder contactPostalCode(String contactPostalCode) {
            this.contactPostalCode = contactPostalCode;
            return this;
        }

        public Builder contactCity(String contactCity) {
            this.contactCity = contactCity;
            return this;
        }

        public Builder contactRegion(String contactRegion) {
            this.contactRegion = contactRegion;
            return this;
        }

        public Builder contactCountry(String contactCountry) {
            this.contactCountry = contactCountry;
            return this;
        }

        public Builder contactNameSurname(String contactNameSurname) {
            this.contactNameSurname = contactNameSurname;
            return this;
        }

        public Builder contactProjects(String[] contactProjects) {
            this.contactProjects = contactProjects;
            return this;
        }

        public Builder contactProject(String contactProject) {
            this.contactProject = contactProject;
            return this;
        }

        public Builder contactRelations(String[] contactRelations) {
            this.contactRelations = contactRelations;
            return this;
        }

        public Builder contactRelation(String contactRelation) {
            this.contactRelation = contactRelation;
            return this;
        }

        public ObjectContact build() {
            return new ObjectContact(this);
        }
    }
}
