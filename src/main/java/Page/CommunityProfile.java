package Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CommunityProfile {
    //Team setting
    public static final SelenideElement PROFILE_TEAM_NAME = $(byXpath("//*[@class='editable-title clearfix']//h3"));
    public static final SelenideElement PROFILE_TEAM_NAME_INPUT = $(byXpath("//*[@class='editable-title clearfix']//input"));
    public static final SelenideElement PROFILE_TEAM_NAME_SAVE = $(byXpath("//*[@class='editable-title clearfix']//button[contains(.,'Save')]"));
    public static final SelenideElement PROFILE_TEAM_NAME_CANCEL = $(byXpath("//*[@class='editable-title clearfix']//button[contains(.,'Cancel')]"));

    public static final SelenideElement PROFILE_SERVICES_FORM = $(byXpath(""));

    public static void findServiceRow(String profileServiceCaseType, String profileServiceCountry, boolean rowPresentOnPage) {
        //String profileServiceRow = "//div[contains(.,'"+profileServiceCaseType+"')]/following-sibling::div[contains(.,'"+profileServiceCountry+"')]";
        String profileServiceRow = "//div[@class='row' and contains(.,'"+ profileServiceCaseType +"') and contains(.,'"+ profileServiceCountry +"')]";
        if ($(byXpath(profileServiceRow)).exists()!=rowPresentOnPage)
        {
            Assert.fail("Service present element state is - "+$(byXpath(profileServiceRow)).exists());
        }
    }

    public static void clickServiceRowEdit(String profileServiceCaseType, String profileServiceCountry) {
 //       String profileServiceRow = "//div[contains(.,'"+profileServiceCaseType+"')]/following-sibling::div[contains(.,'"+profileServiceCountry+"')]/following-sibling::div//button[1]";
        String profileServiceRow = "//div[@class='row' and contains(.,'"+ profileServiceCaseType +"') and contains(.,'"+ profileServiceCountry +"')]//button[1]";
        SelenideElement PROFILE_SERVICE_EDIT = $(byXpath(profileServiceRow));
        PROFILE_SERVICE_EDIT.click();
    }

    public static void clickServiceRowDelete(String profileServiceCaseType, String profileServiceCountry) {
       // String profileServiceRow = "//div[contains(.,'"+profileServiceCaseType+"')]/following-sibling::div[contains(.,'"+profileServiceCountry+"')]/following-sibling::div//button[2]";
        String profileServiceRow = "//div[@class='row' and contains(.,'"+ profileServiceCaseType +"') and contains(.,'"+ profileServiceCountry +"')]//button[2]";
        SelenideElement PROFILE_SERVICE_EDIT = $(byXpath(profileServiceRow));
        PROFILE_SERVICE_EDIT.click();
    }
    public static void changeServiceRate(String profileServiceCaseType, String profileServiceCountry, String newPrice) {
        SelenideElement serviceRateField = $(byXpath("//div[@class='row ng-scope' and contains(.,'"+profileServiceCaseType+"') and contains(.,'"+profileServiceCountry+"')]//input[@name='rate']"));
        serviceRateField.clear();
        serviceRateField.val(newPrice);
        serviceRateField.shouldHave(Condition.value(newPrice));
    }

    public static final SelenideElement PROFILE_SERVICE_SAVE = $(byXpath("//*[@class='border-container border-container-white']//button[contains(.,'Save')]"));
    private static final String PROFILE_SERVICE_TABLE = "//*[@class='services-table']";
    private static final String PROFILE_SERVICE_ROW = "//div[@class='row']";



    public static final SelenideElement PROFILE_SERVICE = $(byXpath(""));

    public static final SelenideElement PROFILE_SELECT_CaseType = $(byXpath("//span[text()='Practice Area']"));
    public static final SelenideElement PROFILE_INPUT_CaseType = $(byXpath("//input[@placeholder='Practice Area']"));
    public static final SelenideElement PROFILE_SELECT_Defining = $(byXpath("//span[text()='Jurisdiction']"));
    public static final SelenideElement PROFILE_INPUT_Defining = $(byXpath("//input[@placeholder='Jurisdiction']"));
    public static final SelenideElement PROFILE_SELECT_ExpertType = $(byXpath("//span[text()='Service Type']"));
    public static final SelenideElement PROFILE_INPUT_ExpertType = $(byXpath("//input[@placeholder='Service Type']"));
    public static final SelenideElement PROFILE_INPUT_PRICE = $(byXpath("//input[@name='rate']"));
    public static final SelenideElement PROFILE_BTN_ADD = $(byXpath("//button[contains(.,'+ Add')]")); //disabled="disabled"


    public static final SelenideElement PROFILE_INPUT_DESCRIPTION = $(byXpath("//textarea[@name='competence']"));
    public static final SelenideElement PROFILE_BTN_SAVE_DESCRIPTION = $(byXpath("//*[@class='clearfix ng-scope']//button[contains(.,'Save')]")); //disabled="disabled"

    public static final SelenideElement PROFILE_MEMBERS = $(byXpath(""));
    public static final SelenideElement PROFILE_MEMBERS_COUNT = $(byXpath("//*[@class='members-link ng-binding'][@href]")); //reditect to /a/settings/team/members
    public static final SelenideElement PROFILE_MEMBERS_ROW = $(byXpath(""));

    public static final SelenideElement PROFILE_PLEDGE_1 = $(byXpath("//*[@name='pledge-1']"));
    public static final SelenideElement PROFILE_PLEDGE_2 = $(byXpath("//*[@name='pledge-2']"));
    public static final SelenideElement PROFILE_PLEDGE_3 = $(byXpath("//*[@name='pledge-3']"));
    public static final SelenideElement PROFILE_PLEDGE_4 = $(byXpath("//*[@name='pledge-3']"));
    public static final SelenideElement PROFILE_BTN_YES = $(byXpath("[@value='true']"));
    public static final SelenideElement PROFILE_BTN_NO = $(byXpath("[@value='false']"));

    public static final SelenideElement PROFILE_BTN_INVITE = $(byXpath("//button[contains(.,'+ Invite')]"));
    public static final SelenideElement PROFILE_BTN_AVATAR_DELETE = $(byXpath(""));
    public static final SelenideElement PROFILE_BTN_AVATAR_UPLOAD = $(byXpath(""));
    //Personal setting



}
