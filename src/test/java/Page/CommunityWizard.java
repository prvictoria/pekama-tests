package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
public class CommunityWizard extends Page {
    public static final SelenideElement WIZARD_ = $(byXpath(""));
    public static final SelenideElement WIZARD_STEP1 = $(byXpath("//section/ui-view/div[1]"));
    public static final SelenideElement WIZARD_STEP2 = $(byXpath("//section/ui-view/div[2]"));
    public static final SelenideElement WIZARD_STEP3 = $(byXpath("//section/ui-view/div[3]"));
    public static final SelenideElement WIZARD_STEP4 = $(byXpath("//section/ui-view/div[4]"));
    public static final SelenideElement WIZARD_STEP5 = $(byXpath("//section/ui-view/div[5]"));

    public static final SelenideElement WIZARD_BTN_GetStarted = $(byXpath("//button[contains(text(),'Get Started')]"));
    public static final SelenideElement WIZARD_BTN_NEXT = $(byXpath("//button[contains(text(),'Next')]"));
    public static final SelenideElement WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS = $(byXpath("//button[contains(text(),'start new conversation') or contains(text(),'request introductions')]"));
    public static final SelenideElement WIZARD_BTN_REQUEST_INSTRUCTIONS = $(byXpath("//button[contains(text(),'request introductions')]"));
    public static final SelenideElement WIZARD_BTN_REQUEST_INSTRUCTION_OR_START_CONVERSATION = $(byXpath("//button[contains(text(),'request introductions / start a conversation')]"));
    public static final SelenideElement WIZARD_BTN_START_CONVERSATION = $(byXpath("//button[contains(text(),'start new conversation')]"));
    public static final SelenideElement PROFILE_BTN_BOOST_YOUR_PROFILE = $(byXpath("//button[contains(.,'Boost Your Profile')]"));



    public static final SelenideElement WIZARD_SELECT_CaseType = $(byXpath("//div[@class='profile-filters']//div[@class='filter-block'][1]//span"));
    public static final SelenideElement WIZARD_INPUT_CaseType = $(byXpath("//div[@class='profile-filters']//div[@class='filter-block'][1]//input[@type='search']"));
    public static final SelenideElement WIZARD_SELECT_Defining = $(byXpath("//div[@class='profile-filters']//div[@class='filter-block'][2]//span"));
    public static final SelenideElement WIZARD_INPUT_Defining = $(byXpath("//div[@class='profile-filters']//div[@class='filter-block'][2]//input[@type='search']"));
    public static final SelenideElement WIZARD_SELECT_Service = $(byXpath("//div[@class='profile-filters']//div[@class='filter-block'][3]//span"));
    public static final SelenideElement WIZARD_INPUT_Service = $(byXpath("//div[@class='profile-filters']//div[@class='filter-block'][3]//input[@type='search']"));

    //2nd step
    public static String expertRowLabel = "//div[@profile='profile']//span[contains(.,'Member of %s')]";
    public static final SelenideElement zzz = $(byXpath(""));
    //3rd step
    public static final SelenideElement WIZARD_BTN_YES = $(byXpath("//button[@class='btn btn-lg btn-purple'][contains(.,'Next')]"));
    public static final SelenideElement WIZARD_BTN_SKIP = $(byXpath("//button[@class='btn btn-lg btn-warning'][contains(.,'skip')]"));
    public static final SelenideElement WIZARD_FIELD_CASE_NAME = $(byXpath("//input[@name='title']"));

    //4 step
    public static final SelenideElement BTN_SEND_INSTRUCTION = $(byXpath("//button[contains(text(),'Send Instructions')]"));
    //5 step
    public static final SelenideElement WIZARD_BTN_INSTRUCT_NOW = $(byXpath("//button[contains(text(),'Instruct Now!')]"));
    public static final SelenideElement WIZARD_BTN_CANCEL = $(byXpath("//button[contains(text(),'Cancel')]"));
}
