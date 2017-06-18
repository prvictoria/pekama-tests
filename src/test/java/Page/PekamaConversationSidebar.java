package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.Page;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaConversationSidebar extends Page {
    public static final SelenideElement conversationSidebar = $(byXpath("//aside[@class='column-sidebar']"));
    public static final SelenideElement conversationSidebarToggle = $(byXpath("css=a.sidebar-toggle"));
    public static final SelenideElement conversationSidebarSearch = $(byXpath("//input[@placeholder='Search in conversations']"));
    public static final SelenideElement conversationSidebarPlaceholder = $(byXpath("//aside[@class='column-sidebar']//div[starts-with(@class, 'alert alert-empty')]"));
    public static final SelenideElement conversationSidebarFilterAll = $(byXpath("//aside[@class='column-sidebar']//a[text()='All']"));
    public static final SelenideElement conversationSidebarFilterUnread = $(byXpath("//aside[@class='column-sidebar']//a[text()='Unread']"));
    public static final SelenideElement conversationSidebarFilterPinned = $(byXpath("//aside[@class='column-sidebar']//a[text()='Pinned']"));
    public static final SelenideElement conversationSidebarSortByDate = $(byXpath("//aside[@class='column-sidebar']//a[text()='By Date']"));
    public static final SelenideElement conversationSidebarTreadList = $(byXpath("//aside[@class='column-sidebar']//div[@class='menu-list ng-isolate-scope']/div"));
    public static final SelenideElement conversationSidebarIconTeam = $(byXpath("//i[@class='pkm-icon-chat']"));
    public static final SelenideElement conversationSidebarIconEmail = $(byXpath("//i[@class='pkm-icon-envelope']"));
    public static final SelenideElement conversationSidebarIconPin = $(byXpath("//i[@class='pkm-icon-pin']"));


}
