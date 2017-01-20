package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class CommunityWizard {
    public static final SelenideElement WIZARD_ = $(byXpath(""));
    public static final SelenideElement WIZARD_STEP1 = $(byXpath("//section/ui-view/div[1]"));
    public static final SelenideElement WIZARD_STEP2 = $(byXpath("//section/ui-view/div[2]"));
    public static final SelenideElement WIZARD_STEP3 = $(byXpath("//section/ui-view/div[3]"));
    public static final SelenideElement WIZARD_STEP4 = $(byXpath("//section/ui-view/div[4]"));
    public static final SelenideElement WIZARD_STEP5 = $(byXpath("//section/ui-view/div[5]"));

    public static final SelenideElement WIZARD_BTN_GetStarted = $(byXpath("//button[contains(text(),'Get Started')]"));
    public static final SelenideElement WIZARD_BTN_NEXT = $(byXpath("//button[contains(text(),'Next')]"));
    public static final SelenideElement WIZARD_BTN_REQUEST_INSTRUCTIONS = $(byXpath("//button[contains(text(),'request introductions')]"));

    public static final SelenideElement WIZARD_BTN_YES = $(byXpath(WIZARD_STEP2+"//div[@ class='toggle-group mb-3']/label[1]"));
    public static final SelenideElement WIZARD_BTN_NO = $(byXpath(WIZARD_STEP2+"//div[@ class='toggle-group mb-3']/label[2]"));

    public static final SelenideElement WIZARD_SELECT_CaseType = $(byXpath("//div[@class='panel-body']//div[@name='matterType']//span"));
    public static final SelenideElement WIZARD_INPUT_CaseType = $(byXpath("//div[@class='panel-body']//div[@name='matterType']//input"));
    public static final SelenideElement WIZARD_SELECT_Defining = $(byXpath("//div[@class='panel-body']//div[@name='defining']//span"));
    public static final SelenideElement WIZARD_INPUT_Defining = $(byXpath("//div[@class='panel-body']//div[@name='defining']//input"));
    public static final SelenideElement WIZARD_SELECT_ExpertType = $(byXpath("//div[@class='panel-body']//div[@name='expertiseType']//span"));
    public static final SelenideElement WIZARD_INPUT_ExpertType = $(byXpath("//div[@class='panel-body']//div[@name='expertiseType']//input"));
}
