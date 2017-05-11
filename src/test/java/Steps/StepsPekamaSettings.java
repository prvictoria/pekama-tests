package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.PekamaTeamSettings.*;
import static Steps.StepsPekama.selectItemInDropdown;
import static com.codeborne.selenide.Condition.*;
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
}
