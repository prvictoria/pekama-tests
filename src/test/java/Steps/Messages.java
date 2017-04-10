package Steps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Messages implements StepsFactory{
    static final Logger rootLogger = LogManager.getRootLogger();
    //Subject strings
    public static final String EMAIL_SUBJECT_ = "";
    public static final String EMAIL_SUBJECT_CONFIRM_REGISTRATION = "Confirm Registration [Pekama]";
    public static final String EMAIL_BODY_CONFIRM_REGISTRATION_1 = "Registration Complete";
    public static final String EMAIL_BODY_CONFIRM_REGISTRATION_2 = "To finish registration, please confirm your account.";
    public static final String EMAIL_BODY_CONFIRM_REGISTRATION_3 = "Confirm Account";
    public static final String EMAIL_SUBJECT_4 = "";


    //Common messages


    //Pekama Messages


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
}
