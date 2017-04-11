package Steps;

import org.junit.Assert;

import static Steps.MessagesIMAP.parseHtmlHref;
import static Steps.MessagesIMAP.parseHtmlHrefArray;
import static Steps.MessagesIMAP.parseHtmlLinkText;

/**
 * Created by Viachaslau_Balashevi on 4/11/2017.
 */
public interface MessagesValidator {
    public boolean validationEmail(String html);
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
class ValidationCongratulation implements MessagesValidator{
    private String html = null;

    @Override
    public boolean validationEmail(String html) {

        this.html = html;
        parseHtmlHrefArray(html);
        return true;
    }
}