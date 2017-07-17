package Steps.Intrefaces;
import Steps.Objects.Emails.EmailUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;

import java.io.IOException;

import static Pages.Emails.*;
import static Pages.UrlStrings.*;
import static Steps.Messages.*;

/**
 * Created by Viachaslau_Balashevi on 4/11/2017.
 */
public interface IMessagesValidator {
    static final Logger rootLogger = LogManager.getRootLogger();
    boolean validationEmail(String...strings);
    String validateLink(String html, Integer index);

    @Deprecated
    class ValidationNotificationCaseConfirmed implements IMessagesValidator {
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
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals(EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_BTN_TEXT));
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 2);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            String link1 = EmailUtils.getLink(links, 0);
            Assert.assertTrue(link1.contains(COMMUNITY_CONVERSATION_LINK));
            String link2 = EmailUtils.getLink(links, 1);
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
    @Deprecated
    class ValidationSignUp implements IMessagesValidator {
        private String html = null;
        public static String userEmail = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Complete my registration"));
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 3);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            String link1 = EmailUtils.getLink(links, 0);
            Assert.assertTrue(link1.contains(EMAIL_CONFIRM_REGISTRATION_LINK));
            String link2 = EmailUtils.getLink(links, 1);
            Assert.assertTrue(link2.contains(EMAIL_CONFIRM_REGISTRATION_LINK));
            String link3 = EmailUtils.getLink(links, 2);
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
    @Deprecated
    class ValidationInvite implements IMessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            String link = EmailUtils.parseHtmlHref(html);
            Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Confirm Account"));
            EmailUtils.parseHtmlHrefArray(html);
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteInPekama implements IMessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = html;
            String link = EmailUtils.parseHtmlHref(html);
            Assert.assertTrue(link.contains("https://staging.pekama.com/accounts/confirm/"));
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals("Confirm Account"));
            EmailUtils.parseHtmlHrefArray(html);
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            return null;
        }
    }
    class ValidationInviteCommunity implements IMessagesValidator {
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
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals(EMAIL_INVITED_IN_COMMUNITY_ACTION_BTN_TEXT));
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 3);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            String link1 = EmailUtils.getLink(links, 0);
            Assert.assertTrue(link1.contains(EMAIL_CONFIRM_INVITATION_LINK_COMMUNITY));
            String link2 = EmailUtils.getLink(links, 1);
            Assert.assertTrue(link2.contains(EMAIL_CONFIRM_INVITATION_LINK_COMMUNITY));
            String link3 = EmailUtils.getLink(links, 2);
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

    @Deprecated
    class ValidationInviteInProject implements IMessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String projectName = null;
        public static String inviterNameSurname = null;
        public static String projectBackLink = null;
        @Deprecated
        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.projectName = projectName;
            this.inviterNameSurname = inviterNameSurname;
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertEquals(EMAIL_BTN_YOU_INVITED_IN_PROJECT(projectName), linkText);
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 3);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            projectBackLink = EmailUtils.getLink(links, 0);
            Assert.assertTrue(projectBackLink.contains(EMAIL_INVITE_IN_PEKAMA_LINK1));
            Assert.assertTrue(projectBackLink.contains(EMAIL_INVITE_IN_PROJECT_LINK2));
            projectBackLink = EmailUtils.getLink(links, 1);
            Assert.assertTrue(projectBackLink.contains(EMAIL_INVITE_IN_PEKAMA_LINK1));
            Assert.assertTrue(projectBackLink.contains(EMAIL_INVITE_IN_PROJECT_LINK2));
            String link3 = EmailUtils.getLink(links, 2);
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
    class ValidationInviteInTeamUnregistered implements IMessagesValidator {
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
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertEquals(EMAIL_BTN_YOU_INVITED_IN_TEAM(inviterFullTeamName), linkText);
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 3);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            teamBackLink = EmailUtils.getLink(links, 0);
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_PEKAMA_LINK1));
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_TEAM_LINK2));
            teamBackLink = EmailUtils.getLink(links, 1);
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_PEKAMA_LINK1));
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_TEAM_LINK2));
            String link3 = EmailUtils.getLink(links, 2);
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
    class ValidationInviteInTeamRegistered implements IMessagesValidator {
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
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertEquals(EMAIL_BTN_YOU_INVITED_IN_TEAM(inviterFullTeamName), linkText);
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 3);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            teamBackLink = EmailUtils.getLink(links, 0);
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_TEAM_REGISTERED_USER));
            teamBackLink = EmailUtils.getLink(links, 1);
            Assert.assertTrue(teamBackLink.contains(EMAIL_INVITE_IN_TEAM_REGISTERED_USER));
            String link3 = EmailUtils.getLink(links, 2);
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
    class ValidationCongratulationCaseCreated implements IMessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String teamName = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.teamName = teamName;

            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 1);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            String link1 = EmailUtils.getLink(links, 0);
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
    class ValidationCongratulationForInvite implements IMessagesValidator {
        private String html = null;
        public static String userEmail = null;
        public static String invitedEmail = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.userEmail = userEmail;
            this.invitedEmail = invitedEmail;

            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 1);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            String link1 = EmailUtils.getLink(links, 0);
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
    @Deprecated
    class ValidationResetPassword implements IMessagesValidator {
        private String html = null;

        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            String linkText = EmailUtils.parseHtmlLinkText(html);
            Assert.assertTrue(linkText.equals(EMAIL_RESET_PASSWORD_BTN));
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 2);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            String link1 = EmailUtils.getLink(links, 0);
            Assert.assertTrue(link1.contains(EMAIL_RESET_PASSWORD_LINK));
            String link2 = EmailUtils.getLink(links, 1);
            Assert.assertTrue(link2.contains(EMAIL_RESET_PASSWORD_LINK));
            Assert.assertTrue(html.contains(EMAIL_RESET_PASSWORD_TITLE));
            Assert.assertTrue(html.contains(EMAIL_RESET_PASSWORD_TEXT));
            Assert.assertTrue(html.contains("You may copy/paste this link into your browser:"));
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            String link = EmailUtils.getLink(links, index);
            return link;
        }
    }
    @Deprecated
    class ValidationReport implements IMessagesValidator {
        private String html = null;
        public static String reportSchedule = null;
        public static String reportBackLink = null;
        public static String unsubscribeLink = null;
        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.reportSchedule = reportSchedule;
            Assert.assertTrue(EmailUtils.parseHtmlHrefArray(html).size() == 2);
            Elements links = EmailUtils.parseHtmlHrefArray(html);
            reportBackLink = EmailUtils.getLink(links, 0);
            Assert.assertTrue(reportBackLink.contains(REPORT_BACK_LINK));
            unsubscribeLink = EmailUtils.getLink(links, 1);
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
    class ValidationEmailMessage implements IMessagesValidator {
        private String html = null;
        private String text = null;
        public static String userNameSurname = null;
        public static String followerEmailOrTeamNameSurname = null;
        public static String replyLink = null;
        @Override
        public boolean validationEmail(String...strings) {
            this.html = strings[0];
            this.text = strings[1];
            this.userNameSurname = userNameSurname;
            this.followerEmailOrTeamNameSurname = followerEmailOrTeamNameSurname;
            Assert.assertTrue(html.contains(text));

            Document document = EmailUtils.document(html);
            try {
                EmailUtils.parseCleanHtml(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String secureGroupText = EmailUtils.getHtmlElementByTag(document, "p", 0);
            Assert.assertEquals(secureGroupText, EMAIL_TEXT_SECURE_GROUP(userNameSurname, followerEmailOrTeamNameSurname));
            rootLogger.info("Email validation passed");
            return true;
        }

        @Override
        public String validateLink(String html, Integer index) {
            try {
                if (html != null) {
                    this.html = html;
                    Elements links = EmailUtils.parseHtmlHrefArray(html);
                    this.replyLink = EmailUtils.getLink(links, index);
                    //rootLogger.info(replyLink);
                }
                return replyLink;
            }
            catch (IndexOutOfBoundsException e){

            }
            return null;
        }
    }
}