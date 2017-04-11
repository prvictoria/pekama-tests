package Steps;

import org.jsoup.select.Elements;
import org.junit.Assert;

import javax.lang.model.element.Element;
import java.lang.reflect.Array;

import static Steps.MessagesIMAP.*;

/**
 * Created by Viachaslau_Balashevi on 4/11/2017.
 */
public interface MessagesValidator {
    boolean validationEmail(String html);
}
class ValidationSignUp implements MessagesValidator{
    private String html = null;
    private String invitedEmail = null;

    @Override
    public boolean validationEmail(String html) {
        this.html = html;
        this.invitedEmail = login;
        String linkText = parseHtmlLinkText(html);
        Assert.assertTrue(linkText.equals("Confirm Account"));
        Assert.assertTrue(parseHtmlHrefArray(html).size()==3);
        Elements links = parseHtmlHrefArray(html);
        String link1 = getLink(links, 0);
        Assert.assertTrue(link1.contains("https://staging.pekama.com/accounts/confirm/"));
        String link2 = getLink(links, 1);
        Assert.assertTrue(link2.contains("https://staging.pekama.com/accounts/confirm/"));
        String link3 = getLink(links, 2);
        Assert.assertTrue(link3.contains(login));
        Assert.assertTrue(html.contains("Registration Complete"));
        Assert.assertTrue(html.contains("To finish registration, please confirm your account."));
        Assert.assertTrue(html.contains("Your sign in email is:"));
        return true;
    }
}
class ValidationInvite implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {
        this.html = html;
        String link = parseHtmlHref(html);
        Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
        String linkText = parseHtmlLinkText(html);
        Assert.assertTrue(linkText.equals("Confirm Account"));
        parseHtmlHrefArray(html);
        return true;
    }
}
class ValidationInviteInPekama implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {
        this.html = html;
        String link = parseHtmlHref(html);
        Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
        String linkText = parseHtmlLinkText(html);
        Assert.assertTrue(linkText.equals("Confirm Account"));
        parseHtmlHrefArray(html);
        return true;
    }
}
class ValidationInviteCommunity implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {
        this.html = html;
        String link = parseHtmlHref(html);
        Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
        String linkText = parseHtmlLinkText(html);
        Assert.assertTrue(linkText.equals("Confirm Account"));
        parseHtmlHrefArray(html);
        return true;
    }
}
class ValidationInviteInProject implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {
        this.html = html;
        String link = parseHtmlHref(html);
        Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
        String linkText = parseHtmlLinkText(html);
        Assert.assertTrue(linkText.equals("Confirm Account"));
        parseHtmlHrefArray(html);
        return true;
    }
}
class ValidationInviteInTeam implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {
        this.html = html;
        String link = parseHtmlHref(html);
        Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
        String linkText = parseHtmlLinkText(html);
        Assert.assertTrue(linkText.equals("Confirm Account"));
        parseHtmlHrefArray(html);
        return true;
    }
}
class ValidationCongratulation implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {

        this.html = html;
        parseHtmlHrefArray(html);
        return true;
    }
}
class ValidationResetPassword implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {

        this.html = html;
        parseHtmlHrefArray(html);
        return true;
    }
}
class ValidationReport implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {

        this.html = html;
        parseHtmlHrefArray(html);
        return true;
    }
}