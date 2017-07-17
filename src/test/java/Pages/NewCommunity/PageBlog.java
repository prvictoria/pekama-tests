package Pages.NewCommunity;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PageBlog extends ModuleHeader {
    public static final String BLOG_URL = "";



    public Boolean switchToBlog(){
        PageBlog pageBlog = new PageBlog();
        return pageBlog.switchToWindow("Pekama Blog – News, Information, Help, & Insight Into The Pekama Community");
    }
}
