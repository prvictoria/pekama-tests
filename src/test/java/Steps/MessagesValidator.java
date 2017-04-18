package Steps;
import org.jsoup.select.Elements;
import org.junit.Assert;
import static Page.Emails.*;
import static Page.UrlStrings.*;
import static Steps.Messages.*;
import static Steps.MessagesIMAP.*;
/**
 * Created by Viachaslau_Balashevi on 4/11/2017.
 */
public interface MessagesValidator extends StepsFactory {
    boolean validationEmail(String...strings);
    String validateLink(String html, Integer index);
    //}
    class ValidationNotificationCaseConfirmed implements MessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String expertTeam = null;
        public static String caseName = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.expertTeam = expertTeam;
            this.caseName = caseName;
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals(EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_BTN_TEXT));
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 2);
            Elements links = parseHtmlHrefArray(html);
            String link1 = getLink(links, 0);
            Assert.assertTrue(link1.contains(COMMUNITY_CONVERSATION_LINK));
            String link2 = getLink(links, 1);
            Assert.assertTrue(link2.contains(userEmail));
            Assert.assertTrue(html.contains(EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_TITLE));
            Assert.assertTrue(html.contains(EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_1(expertTeam, caseName)));
            Assert.assertTrue(html.contains(EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_2));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOUR_EMAIL_IS));
            rootLogger.info("Email validation passed");
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationSignUp implements MessagesValidator {
        private String html = null;
        public static String userEmail = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Complete my registration"));
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 3);
            Elements links = parseHtmlHrefArray(html);
            String link1 = getLink(links, 0);
            Assert.assertTrue(link1.contains(EMAIL_CONFIRM_REGISTRATION_LINK_PEKAMA));
            String link2 = getLink(links, 1);
            Assert.assertTrue(link2.contains(EMAIL_CONFIRM_REGISTRATION_LINK_PEKAMA));
            String link3 = getLink(links, 2);
            Assert.assertTrue(link3.contains(userEmail));
            Assert.assertTrue(html.contains("Almost there..."));
            Assert.assertTrue(html.contains(EMAIL_CONFIRM_REGISTRATION_TEXT));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOUR_EMAIL_IS));
            rootLogger.info("Email validation passed");
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
        public static String userEmail = null;
        public static String inviterNameSurname = null;
        public static String customText = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.customText = customText;
            this.inviterNameSurname = inviterNameSurname;
            String linkText = parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals(EMAIL_INVITED_IN_COMMUNITY_ACTION_BTN_TEXT));
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 3);
            Elements links = parseHtmlHrefArray(html);
            String link1 = getLink(links, 0);
            Assert.assertTrue(link1.contains(EMAIL_CONFIRM_INVITATION_LINK_COMMUNITY));
            String link2 = getLink(links, 1);
            Assert.assertTrue(link2.contains(EMAIL_CONFIRM_INVITATION_LINK_COMMUNITY));
            String link3 = getLink(links, 2);
            Assert.assertTrue(link3.contains(userEmail));
            Assert.assertTrue(html.contains(EMAIL_INVITED_IN_COMMUNITY_TITLE));
            System.out.println(inviterNameSurname);
            System.out.println(EMAIL_INVITED_IN_COMMUNITY_BODY_1(inviterNameSurname));
            Assert.assertTrue(html.contains(EMAIL_INVITED_IN_COMMUNITY_BODY_1(inviterNameSurname)));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOUR_EMAIL_IS));
            if(customText==null){
                Assert.assertTrue(html.contains(EMAIL_INVITED_IN_COMMUNITY_DEFAULT_TEXT));
            }
            if(customText!=null){
                Assert.assertTrue(html.contains(customText));
            }
            rootLogger.info("Email validation passed");
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteInProject implements MessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String projectName = null;
        public static String inviterNameSurname = null;
        public static String projectBackLink = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.projectName = projectName;
            this.inviterNameSurname = inviterNameSurname;
            String linkText = parseHtmlLinkText(html);
            Assert.assertEquals(EMAIL_BTN_YOU_INVITED_IN_PROJECT(projectName), linkText);
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 3);
            Elements links = parseHtmlHrefArray(html);
            projectBackLink = getLink(links, 0);
            Assert.assertTrue(projectBackLink.contains(EMAIL_INVITE_IN_PEKAMA_LINK1));
            Assert.assertTrue(projectBackLink.contains(EMAIL_INVITE_IN_PROJECT_LINK2));
            projectBackLink = getLink(links, 1);
            Assert.assertTrue(projectBackLink.contains(EMAIL_INVITE_IN_PEKAMA_LINK1));
            Assert.assertTrue(projectBackLink.contains(EMAIL_INVITE_IN_PROJECT_LINK2));
            String link3 = getLink(links, 2);
            Assert.assertTrue(link3.contains(userEmail));

            System.out.println(EMAIL_INVITED_IN_COMMUNITY_BODY_1(inviterNameSurname));
            Assert.assertTrue(html.contains(EMAIL_TITLE_YOU_INVITED_IN_PROJECT(inviterNameSurname)));
            System.out.println(EMAIL_TEXT_YOU_INVITED_IN_PROJECT(inviterNameSurname, projectName));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOU_INVITED_IN_PROJECT(inviterNameSurname, projectName)));
            Assert.assertTrue(html.contains(EMAIL_BTN_YOU_INVITED_IN_PROJECT(projectName)));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOUR_EMAIL_IS));
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteInTeamUnregistered implements MessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String inviterNameSurname = null;
        public static String inviterName = null;
        public static String inviterFullTeamName = null;
        //return
        public static String teamBackLink = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.inviterNameSurname = inviterNameSurname;
            this.inviterName = inviterName;
            this.inviterFullTeamName = inviterFullTeamName;
            String linkText = parseHtmlLinkText(html);
            Assert.assertEquals(EMAIL_BTN_YOU_INVITED_IN_TEAM(inviterFullTeamName), linkText);
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 3);
            Elements links = parseHtmlHrefArray(html);
            teamBackLink = getLink(links, 0);
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_PEKAMA_LINK1));
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_TEAM_LINK2));
            teamBackLink = getLink(links, 1);
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_PEKAMA_LINK1));
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_TEAM_LINK2));
            String link3 = getLink(links, 2);
            Assert.assertTrue(link3.contains(userEmail));

            System.out.println(EMAIL_TITLE_YOU_INVITED_IN_TEAM(inviterFullTeamName));
            Assert.assertTrue(html.contains(EMAIL_TITLE_YOU_INVITED_IN_TEAM(inviterFullTeamName)));
            System.out.println(EMAIL_TEXT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName, inviterName));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName, inviterName)));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOUR_EMAIL_IS));
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteInTeamRegistered implements MessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String inviterNameSurname = null;
        public static String inviterName = null;
        public static String inviterFullTeamName = null;
        //return
        public static String teamBackLink = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.inviterNameSurname = inviterNameSurname;
            this.inviterName = inviterName;
            this.inviterFullTeamName = inviterFullTeamName;
            String linkText = parseHtmlLinkText(html);
            Assert.assertEquals(EMAIL_BTN_YOU_INVITED_IN_TEAM(inviterFullTeamName), linkText);
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 3);
            Elements links = parseHtmlHrefArray(html);
            teamBackLink = getLink(links, 0);
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_TEAM_REGISTERED_USER));
            teamBackLink = getLink(links, 1);
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_TEAM_REGISTERED_USER));
            String link3 = getLink(links, 2);
            Assert.assertTrue(link3.contains(userEmail));

            System.out.println(EMAIL_TITLE_YOU_INVITED_IN_TEAM(inviterFullTeamName));
            Assert.assertTrue(html.contains(EMAIL_TITLE_YOU_INVITED_IN_TEAM(inviterFullTeamName)));
            System.out.println(EMAIL_TEXT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName, inviterName));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOU_INVITED_IN_TEAM(inviterNameSurname, inviterFullTeamName, inviterName)));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOUR_EMAIL_IS));
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
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOUR_EMAIL_IS));
            rootLogger.info("Email validation passed");
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationCongratulationForInvite implements MessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String invitedEmail = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.invitedEmail = invitedEmail;

            Assert.assertTrue(parseHtmlHrefArray(html).size() == 1);
            Elements links = parseHtmlHrefArray(html);
            String link1 = getLink(links, 0);
            Assert.assertTrue(link1.contains(userEmail));
            Assert.assertTrue(html.contains(EMAIL_CONGRATULATION_INVITE_BODY_1(invitedEmail)));
            Assert.assertTrue(html.contains(EMAIL_CONGRATULATION_INVITE_BODY_2(invitedEmail)));
            Assert.assertTrue(html.contains(EMAIL_TEXT_YOUR_EMAIL_IS));
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
            this.html = strings[0];
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
        public static String reportSchedule = null;
        public static String reportBackLink = null;
        public static String unsubscribeLink = null;
        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.reportSchedule = reportSchedule;
            Assert.assertTrue(parseHtmlHrefArray(html).size() == 2);
            Elements links = parseHtmlHrefArray(html);
            reportBackLink = getLink(links, 0);
            Assert.assertTrue(reportBackLink.contains(REPORT_BACK_LINK));
            unsubscribeLink = getLink(links, 1);
            Assert.assertTrue(unsubscribeLink.contains(REPORT_UNSUBSCRIBE_LINK));
            Assert.assertTrue(html.contains(EMAIL_SUBJECT_SCHEDULE(reportSchedule)));
            rootLogger.info("Email validation passed");
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
}