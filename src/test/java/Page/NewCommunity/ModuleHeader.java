package Page.NewCommunity;

import com.codeborne.selenide.SelenideElement;

import static Steps.Steps.clickSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class ModuleHeader extends PageCommunity{
    public static final SelenideElement HEADER_TAB_PEKAMA = $(byXpath("//div[@class='header__nav']//*[@href='/']"));
    public static final SelenideElement HEADER_TAB_EXPERTS = $(byXpath("//div[@class='header__nav']//*[@href='/experts']"));
    public static final SelenideElement HEADER_TAB_CASES = $(byXpath("//div[@class='header__nav']//*[@href='/projects']"));
    public static final SelenideElement HEADER_TAB_PROFILE = $(byXpath("//div[@class='header__nav']//*[@href='/profile']"));

    public static final SelenideElement HEADER_TAB_ABOUT = $(byXpath("//div[@class='header__nav']//*[@href='/about']"));
    public static final SelenideElement HEADER_TAB_BLOG = $(byXpath("//div[@class='header__nav']//*[@href='https://blog.pekama.com' and @target='_blank']"));
    public static final SelenideElement HEADER_TAB_JOIN = $(byXpath("//div[@class='header__nav']//*[@href='/login']"));
    public static final SelenideElement HEADER_TAB_ME = $(byXpath("//div[@class='header__nav']//*[@href='/personal']"));


    public void clickPekamaTab(){
        clickSelector(HEADER_TAB_PEKAMA);
    }
    public void clickExpertsTab(){
        clickSelector(HEADER_TAB_EXPERTS);
    }
    public void clickCasesTab(){
        clickSelector(HEADER_TAB_CASES);
    }
    public void clickProfileTab(){
        clickSelector(HEADER_TAB_PROFILE);
    }
    public void clickAboutTab(){
        clickSelector(HEADER_TAB_ABOUT);
    }
    public PageBlog clickBlogTab(){
        clickSelector(HEADER_TAB_BLOG);
        PageBlog pageBlog = new PageBlog();
        return pageBlog;
    }
    public void clickSingInTab(){
        clickSelector(HEADER_TAB_JOIN);
    }
    public void clickMeTab(){
        clickSelector(HEADER_TAB_ME);
    }

}
