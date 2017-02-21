package Steps;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Messages {
    //Common messages

    
    //Pekama Messages


    //Community Messages
    public static final String MSG_DEFAULT_SENT_INSTRUCTION = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String MSG_DEFAULT_CONFIRM_INSTRUCTIONS = "I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed.";
    public static final String MSG_DEFAULT_CONFIRM_COMPLETION = "I'm pleased to confirm that your instructions were executed and the work was completed on time.";
    public static final String MSG_DEFAULT_WITHDRAW = "Please DO NOT proceed with this filing. Kindly confirm immediately.";

    private static String MSG_DEFAULT_CANCEL = "Dear %s. I'm afraid that we will not be proceeding with this case. Thank you for your information. Please close your file.";
    public final static String msgCaseCancelled(String userName) {
        String msg = String.format(MSG_DEFAULT_CANCEL, userName);
        return msg;
    }
    //Email messages and other formated strings
}
