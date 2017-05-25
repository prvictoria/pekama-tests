package Steps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.Emails.EMAIL_INVITE_IN_PROJECT_TEXT;
import static Page.Emails.EMAIL_INVITE_IN_PROJECT_TITLE;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Messages extends Steps {
    static final Logger rootLogger = LogManager.getRootLogger();
    //Subject strings
    public static final String EMAIL_SUBJECT_ = "";
    public static final String EMAIL_SUBJECT_CONFIRM_REGISTRATION = "Welcome to Pekama! Just one more click";
    public static final String EMAIL_SUBJECT_PASSWORD_REGISTRATION = "Password Restoration [Pekama]";
    //Common messages
    //Pekama Messages
    public static String emailInviteInProjectTitle(String... args){
        String emailTitle = String.format(EMAIL_INVITE_IN_PROJECT_TITLE, args);
        return emailTitle;
    }
    public static String emailInviteInProjectText(String... args){
        String emailText = String.format(EMAIL_INVITE_IN_PROJECT_TEXT, args);
        return emailText;
    }
    //Community Messages
    public final static String msgIntroduce(String REQUESTER_NAME, String REQUESTER_SURNAME, String EXPERT_NAME, String EXPERT_SURNAME) {
        String introduce = "Dear %s %s and %s %s,";
        String msg = String.format(introduce, REQUESTER_NAME, REQUESTER_SURNAME, EXPERT_NAME, EXPERT_SURNAME);
        return msg;
    }
    public final static String msgIntroduceNewCommunityCollaborators(String REQUESTER_NAME_SURNAME, String EXPERT_NAME_SURNAME, String TEST_CASE_COUNTRY) {
        String msg = "Dear "+REQUESTER_NAME_SURNAME+" and "+EXPERT_NAME_SURNAME+",\n\nI am very pleased to introduce you to each other. "+REQUESTER_NAME_SURNAME+" needs an IP service in "+TEST_CASE_COUNTRY+" and we believe that "+EXPERT_NAME_SURNAME+" may be able to help with that. Please do follow up directly on this introduction.";
//        String neverWorkedTogether = "I am very pleased to introduce you to each other. %s %s needs an IP service in %s and we believe that %s %s may be able to help with that. Please do follow up directly on this introduction.";
//        String msg = String.format(neverWorkedTogether, REQUESTER_NAME, REQUESTER_SURNAME, TEST_CASE_COUNTRY, EXPERT_NAME, EXPERT_SURNAME);
        return msg;
    }
    public final static String msgIntroduceWorkedBeforeCommunityCollaborators(String REQUESTER_NAME_SURNAME, String EXPERT_NAME_SURNAME, String TEST_CASE_COUNTRY) {
        String msg = "Dear "+REQUESTER_NAME_SURNAME+" and "+EXPERT_NAME_SURNAME+",\n\nI believe that you already know each other or your firms are already working together. "+REQUESTER_NAME_SURNAME+" now needs an IP service in "+TEST_CASE_COUNTRY+" and we believe, as usual, that "+EXPERT_NAME_SURNAME+" may be able to help with that. I trust that "+REQUESTER_NAME_SURNAME+" will follow up with details.";
//        String neverWorkedTogether = "I believe that you already know each other or your firms are already working together. %s %s now needs an IP service in %s and we believe, as usual, that %s %s may be able to help with that. I trust that %s %s will follow up with details.";
//        String msg = String.format(neverWorkedTogether, REQUESTER_NAME, REQUESTER_SURNAME, TEST_CASE_COUNTRY, EXPERT_NAME, EXPERT_SURNAME);
        return msg;
    }
    public static final String MSG_DEFAULT_SENT_INSTRUCTION = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String MSG_DEFAULT_CONFIRM_INSTRUCTIONS = "I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed.";
    public static final String MSG_DEFAULT_CONFIRM_COMPLETION = "I'm pleased to confirm that your instructions were executed and the work was completed on time.";
    public static final String MSG_DEFAULT_WITHDRAW = "Please DO NOT proceed with this filing. Kindly confirm immediately.";

    public final static String msgCaseCancelled(String userName) {
        String MSG_DEFAULT_CANCEL = "Dear %s. I'm afraid that we will not be proceeding with this case. Thank you for your information. Please close your file.";
        String msg = String.format(MSG_DEFAULT_CANCEL, userName);
        rootLogger.info(msg);
        return msg;
    }

    //Email messages and other formated strings
    //Email Invite in Team
    public static final String EMAIL_SUBJECT_YOU_INVITED_IN_TEAM(String name_surname, String fullTeamName) {
        String subject = String.format("%s invited you to join %s on Pekama", name_surname, fullTeamName);
        return subject;
    }
    public static final String EMAIL_TITLE_YOU_INVITED_IN_TEAM(String fullTeamName) {
        String subject = String.format("You're invited to %s", fullTeamName);
        return subject;
    }
    public static final String EMAIL_TEXT_YOU_INVITED_IN_TEAM(String name_surname, String fullTeamName, String name) {
        String subject = String.format("%s has sent you an invitation to join %s on Pekama. %s uses Pekama to work collaboratively on legal matters.", name_surname, fullTeamName, name);
        return subject;
    }
    public static final String EMAIL_BTN_YOU_INVITED_IN_TEAM(String fullTeamName) {
        String subject = String.format("Join %s", fullTeamName);
        return subject;
    }

    //Email Invite in Project
    //new test project - 83B25l   new test project - 83B25l (TM.PN.030993)
    public static final String EMAIL_SUBJECT_YOU_INVITED_IN_PROJECT(String name_surname) {

        String subject = String.format("%s invited you to", name_surname);
        return subject;
    }
    public static final String EMAIL_TITLE_YOU_INVITED_IN_PROJECT(String name_surname) {
        String subject = String.format("%s invited you to collaborate", name_surname);
        return subject;
    }
    public static final String EMAIL_TEXT_YOU_INVITED_IN_PROJECT(String name_surname, String projectName) {
        String subject = String.format("%s from  created a project for %s and invites you to join in.", name_surname, projectName.toUpperCase());
        return subject;
    }
    public static final String EMAIL_BTN_YOU_INVITED_IN_PROJECT(String projectName) {
        String subject = String.format("Join Project %s", projectName.toUpperCase());
        return subject;
    }

    //Email NAME SURNAME invited you to join Pekama Community
    public static final String EMAIL_SUBJECT_YOU_INVITED_IN_COMMUNITY(String name_surname) {
        String subject = String.format("%s invited you to join Pekama Community", name_surname);
        return subject;
    }
    public static final String EMAIL_INVITED_IN_COMMUNITY_TITLE = "You're invited to Pekama Community";
    public static final String EMAIL_INVITED_IN_COMMUNITY_BODY_1(String name_surname) {
        String text = String.format("%s has sent you an invitation to join Pekama Community", name_surname);
        return text;
    };
    public static final String EMAIL_INVITED_IN_COMMUNITY_DEFAULT_TEXT = "I am a member of the Pekama community, a community of over hundreds of IP firms that work with each other in preferential terms. Pekama is a great tool to control outgoing work and is a great source of incoming work. I firmly recommend that you sign up and become a supplier. This way, we can also work together on the platform.";
    public static final String EMAIL_INVITED_IN_COMMUNITY_ACTION_BTN_TEXT = "Join Pekama Community";

    //Email Congratulations - Case successfully instructed via Pekama!
    public static final String EMAIL_SUBJECT_CONGRATULATION_CASE_CREATED = "Case successfully instructed via Pekama!";
    public static final String EMAIL_CONGRATULATION_BODY_1(String teamName) {
        String text = String.format("Congratulations, you instructed %s through the Pekama platform!", teamName);
        return text;
    };
    public static final String EMAIL_CONGRATULATION_BODY_2(String teamName) {
        String text = String.format("Your community score will be raised once the work is marked as confirmed by %s and this will lead to improved placement in your jurisdiction.", teamName);
        return text;
    };
    //Email Congratulations - Thank you for inviting EMAIL_INVITED to Pekama!
    public static final String EMAIL_SUBJECT_CONGRATULATION_FOR_INVITE(String invitedEmail) {
        String subject = String.format("Thank you for inviting %s to Pekama!", invitedEmail);
        return subject;
    }
    public static final String EMAIL_CONGRATULATION_INVITE_BODY_1(String invitedEmail) {
        String text = String.format("Congratulations, you invited testqweeco005@gmail.com to join Pekama!", invitedEmail);
        return text;
    };
    public static final String EMAIL_CONGRATULATION_INVITE_BODY_2(String invitedEmail) {
        String text = String.format("If testqweeco005@gmail.com indeed joins Pekama, your community score will be increased and this will lead to improved placement in your jurisdiction.", invitedEmail);
        return text;
    };
    //EMAIL Pekama notification: TEAM_NAME confirmed instructions!
    public static final String DEFAULT_CASE_NAME(String caseType, String defining) {
        String subject = String.format("%s in %s", caseType, defining);
        return subject;
    }
    public static final String EMAIL_SUBJECT_NOTIFICATION_INSTRUCTION_CONFIRMED(String expertTeam) {
        String subject = String.format("Pekama notification: %s confirmed instructions!", expertTeam);
        return subject;
    }
    public static final String EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_TITLE = "Congratulations!";
    public static final String EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_1(String expertTeam, String caseName) {
        String text = String.format("%s has confirmed your instructions for %s!", expertTeam, caseName);
        return text;
    };
    public static final String EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_2 = "Your community score has been increased";
    public static final String EMAIL_NOTIFICATION_INSTRUCTION_CONFIRMED_BTN_TEXT = "GO TO CASE";
    //EMAIL reports
    public static final String EMAIL_SUBJECT_REPORT(String reportName) {
        String subject = String.format("Pekama Report \"%s\"", reportName);
        return subject;
    }
    public static final String EMAIL_SUBJECT_SCHEDULE(String reportSchedule) {
        String subject = String.format("This is the report that you configured in Pekama. You will get it every %s days.", reportSchedule);
        return subject;
    }
    //Messages email
    public static final String EMAIL_MESSAGE_SUBJECT(String messageSubject, String projectName) {
        String subject = String.format("This is the report that you configured in Pekama. You will get it every %s days.", messageSubject, projectName);
        return subject;
    }
    public static final String EMAIL_TEXT_SECURE_GROUP(String userNameSurname, String followerEmail) {
        String secureGroupText = String.format("A <strong>Secure Group Conversation</strong> between <strong>%s</strong>, <strong>%s</strong>.", userNameSurname, followerEmail);
        return secureGroupText;
    }
}
