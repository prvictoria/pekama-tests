package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import static Page.ModalWindows.*;
import static Page.PekamaReports.REPORTS_BTN_AddContact;
import static Page.TestsStrings.*;
import static Page.UrlStrings.*;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;

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
    public String[] contactProjects = null;
    public String contactProject = null;
    public String contactRelation = null;

    public void fillForm(
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
    ){
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

        waitForModalWindow(TITLE_MW_CONTACT);
        if(contactType!=null){
            if(contactType.equals("Person")){

            }
            if(contactType.equals("Company")){

            }
            else {
                Assert.fail("Invalid contact type");
            }
        }
        if(contactLegalEntity!=null){

        }
        if(contactFirstName!=null){
            fillField(MW_Contact_NAME, contactFirstName);
        }
        if(contactLastName!=null){
            fillField(MW_Contact_SURNAME, contactLastName);
        }
        if(contactCompany!=null){

        }
        if(contactEmail!=null){
            fillField(MW_Contact_EMAIL, contactEmail);
        }
        if(contactPhone!=null){

        }
        if(contactFax!=null){

        }
        if(contactMobile!=null){

        }
        if(contactStreetAddress!=null){

        }
        if(contactPostalCode!=null){

        }
        if(contactCity!=null){

        }
        if(contactRegion!=null){

        }
        if(contactCountry!=null){
            selectItemInDropdown(
                    MW_Contact_SelectCountry,
                    MW_Contact_InputCountry,
                    contactCountry);
        }

    }
    public enum enterPoint {PROJECT, REPORT}
    public void create(enterPoint enterPoint,
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
                     ){
        if(enterPoint==PROJECT){

        }
        if(enterPoint==REPORT){
            String url = getActualUrl();
            if(url.equals(URL_ReportsContacts)==false){
                openPageWithSpinner(URL_ReportsContacts);
            }
            submitEnabledButton(REPORTS_BTN_AddContact);
        }
        fillForm( contactType,
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
    public Boolean validateError(){
        return true;
    }
    public Boolean validateContact(){
        return true;
    }
}
