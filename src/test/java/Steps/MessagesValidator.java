package Steps;
import org.jsoup.select.Elements;
import org.junit.Assert;
import static Page.Emails.*;
import static Steps.Messages.*;
import static Steps.MessagesIMAP.*;
/**
 * Created by Viachaslau_Balashevi on 4/11/2017.
 */
public interface MessagesValidator extends StepsFactory {
    boolean validationEmail(String...strings);
    String validateLink(String html, Integer index);

    //}
    class ValidationSignUp implements MessagesValidator {
        private String html = null;
        private String invitedEmail = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            this.invitedEmail = login;
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Complete my registration"));
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 3);
            Elements links = parseHtmlHrefArray(html);
            String link1 = getLink(links, 0);
            Assert.assertTrue(link1.contains(EMAIL_CONFIRM_REGISTRATION_LINK));
            String link2 = getLink(links, 1);
            Assert.assertTrue(link2.contains(EMAIL_CONFIRM_REGISTRATION_LINK));
            String link3 = getLink(links, 2);
            Assert.assertTrue(link3.contains(login));
            Assert.assertTrue(html.contains("Almost there..."));
            Assert.assertTrue(html.contains(EMAIL_CONFIRM_REGISTRATION_TEXT));
            Assert.assertTrue(html.contains(EMAIL_CONFIRM_REGISTRATION_YOUR_EMAIL_IS));
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInvite implements MessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            String link = parseHtmlHref(html);
            Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Confirm Account"));
            parseHtmlHrefArray(html);
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteInPekama implements MessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            String link = parseHtmlHref(html);
            Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Confirm Account"));
            parseHtmlHrefArray(html);
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteCommunity implements MessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            String link = parseHtmlHref(html);
            Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Confirm Account"));
            parseHtmlHrefArray(html);
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteInProject implements MessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            String link = parseHtmlHref(html);
            Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Confirm Account"));
            parseHtmlHrefArray(html);
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteInTeam implements MessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            String link = parseHtmlHref(html);
            Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Confirm Account"));
            parseHtmlHrefArray(html);
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationCongratulationCaseCreated implements MessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String teamName = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.teamName = teamName;

            Assert.assertTrue(parseHtmlHrefArray(html).size() == 1);
            Elements links = parseHtmlHrefArray(html);
            String link1 = getLink(links, 0);
            Assert.assertTrue(link1.contains(userEmail));
            Assert.assertTrue(html.contains(EMAIL_CONGRATULATION_BODY_1(teamName)));
            Assert.assertTrue(html.contains(EMAIL_CONGRATULATION_BODY_2(teamName)));
            Assert.assertTrue(html.contains(EMAIL_CONFIRM_REGISTRATION_YOUR_EMAIL_IS));
            rootLogger.info("Email validation passed");
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationResetPassword implements MessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals(EMAIL_RESET_PASSWORD_BTN));
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 2);
            Elements links = parseHtmlHrefArray(html);
            String link1 = getLink(links, 0);
            Assert.assertTrue(link1.contains(EMAIL_RESET_PASSWORD_LINK));
            String link2 = getLink(links, 1);
            Assert.assertTrue(link2.contains(EMAIL_RESET_PASSWORD_LINK));
            Assert.assertTrue(html.contains(EMAIL_RESET_PASSWORD_TITLE));
            Assert.assertTrue(html.contains(EMAIL_RESET_PASSWORD_TEXT));
            Assert.assertTrue(html.contains("You may copy/paste this link into your browser:"));
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            Elements links = parseHtmlHrefArray(html);
            String link = getLink(links, index);
            return link;
        }
    }
    class ValidationReport implements MessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {

            this.html = html;
            parseHtmlHrefArray(html);
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
}