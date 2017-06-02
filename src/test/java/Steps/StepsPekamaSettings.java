package Steps;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.PekamaPersonalSettings.*;
import static Page.PekamaPersonalSettings.PERSONAL_DETAILS_SAVE_BTN;
import static Page.PekamaTeamSettings.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by VatslauX on 11-May-17.
 */
public class StepsPekamaSettings {
    static final Logger rootLogger = LogManager.getRootLogger();
    public static boolean setAutoDeploy(boolean selectAutoCheckbox){
        if(selectAutoCheckbox==true){
            templateRow.waitUntil(visible, 15000).click();
            TEMPLATES_AUTO_DEPLOY.shouldNotBe(selected).setSelected(true);
            TEMPLATES_AUTO_DEPLOY.shouldBe(selected);
            return selectAutoCheckbox;
        }
        if(selectAutoCheckbox==false){
            templateRow.waitUntil(visible, 15000).click();
            TEMPLATES_AUTO_DEPLOY.shouldBe(selected).setSelected(false);
            TEMPLATES_AUTO_DEPLOY.shouldNotBe(selected);
            return selectAutoCheckbox;
        }
        return false;
    }
    public static Integer checkTemplatesFilters(
            String defining,
            String type,
            String event,
            Integer listSize){
        rootLogger.info("Check template filters");
        refresh();
        SETTINGS_DELETE_X.waitUntil(visible, 15000);
        if(defining!=null) {
            rootLogger.info("Select DEFINING filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_DEFINING,
                    TEMPLATES_FILTER_INPUT_DEFINING,
                    defining);
        }
        if(type!=null) {
            rootLogger.info("Select TYPE filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_TYPE,
                    TEMPLATES_FILTER_INPUT_TYPE,
                    type);
        }
        if(event!=null) {
            rootLogger.info("Select EVENT filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_EVENT,
                    TEMPLATES_FILTER_INPUT_EVENT,
                    event);
        }
        if(listSize!=null) {
            rootLogger.info("Check templates list size");
            TEMPLATES_LIST.shouldHaveSize(listSize);}
        return listSize;
    }

    public static void submitPersonalForm(ObjectUser user) {
        openSettingsTabPersonalDetails();

        if(user.name!=null) {
            fillField(PERSONAL_DETAILS_INPUT_NAME, user.name, "First name: "+user.name);
        }

        if(user.surname!=null) {
            fillField(PERSONAL_DETAILS_INPUT_SURNAME, user.surname, "Last name: "+user.surname);
        }

        if(user.phone!=null) {
            fillField(PERSONAL_DETAILS_INPUT_PHONE, user.phone, "Phone # "+user.phone);
        }

        if(user.fax!=null) {
            fillField(PERSONAL_DETAILS_INPUT_FAX, user.fax, "Fax # "+user.fax);
        }

        if(user.mobile!=null) {
            fillField(PERSONAL_DETAILS_INPUT_MOBILE, user.mobile, "Mobile # "+user.mobile);
        }

        if(user.legalEntity!=null) {
            fillField(PERSONAL_DETAILS_INPUT_LEGAL_ENTITY, user.legalEntity, "Legal entity: "+user.legalEntity);
        }

        if(user.street!=null) {
            fillField(PERSONAL_DETAILS_INPUT_STREET, user.street, "Street address: "+user.street);
        }

        if(user.zip!=null) {
            fillField(PERSONAL_DETAILS_INPUT_ZIP, user.zip, "Post code: "+user.zip);
        }

        if(user.city!=null) {
            fillField(PERSONAL_DETAILS_INPUT_CITY, user.city, "City: "+user.city);
        }

        if(user.region!=null) {
            fillField(PERSONAL_DETAILS_INPUT_REGION, user.region, "State/Region "+user.region);
        }

        submitEnabledButton(PERSONAL_DETAILS_SAVE_BTN);
        sleep(2000);
        PERSONAL_DETAILS_SAVE_BTN_WITH_SPINNER.waitUntil(not(visible), 10000);
        SPINNER_IN_BUTTON.shouldNotBe(visible);
    }
    public static Boolean checkPersonalForm(ObjectUser user){
        openSettingsTabPersonalDetails();
        rootLogger.info("Check Saved Data in personal form");
        sleep(2000);

        if(user.name!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_NAME, user.name, "First name has value: "+user.name);
        }

        if(user.surname!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_SURNAME, user.surname, "Last name has value: "+user.surname);
        }

        if(user.phone!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_PHONE, user.phone, "Phone has value: "+user.phone);
        }

        if(user.fax!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_FAX, user.fax, "Fax has value: "+user.fax);
        }

        if(user.mobile!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_MOBILE, user.mobile, "Mobile has value:  "+user.mobile);
        }

        if(user.legalEntity!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_LEGAL_ENTITY, user.legalEntity, "Legal entity has value: "+user.legalEntity);
        }

        if(user.street!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_STREET, user.street, "Street address has value: "+user.street);
        }

        if(user.zip!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_ZIP, user.zip, "Post code has value: "+user.zip);
        }

        if(user.city!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_CITY, user.city, "City has value: "+user.city);
        }

        if(user.region!=null) {
            checkInputValue(PERSONAL_DETAILS_INPUT_REGION, user.region, "State/Region has value: "+user.region);
        }
        PERSONAL_DETAILS_SAVE_BTN.shouldBe(Condition.disabled);
        rootLogger.info("ObjectUser default data present");
        return true;
    }

}
