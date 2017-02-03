package Utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Utils {
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random rnd = new Random();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                    sb.append(AB.charAt(rnd.nextInt(AB.length())));
               }
               return sb.toString();
    }

    public void  getCurrentDate (){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //2017-01-10 need- 2017/01/10
        java.util.Date date = new java.util.Date();
        String datetime = dateFormat.format(date);
        System.out.println("Current Date Time : " + datetime);
    }
}
