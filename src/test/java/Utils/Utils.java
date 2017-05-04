package Utils;
import org.junit.Ignore;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
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

    public static String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date date = new java.util.Date();
        String datetime = dateFormat.format(date);
        System.out.println("Current Date Time : " + datetime);
        return datetime;
    }
    public static String formatDateToString(Date currentDate){
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String datetime = dateFormat.format(currentDate);
        System.out.println("Current Date Time : " + datetime);
        return datetime;
    }

    public static void main (String[] args) {
        // get a calendar instance, which defaults to "now"
        Calendar calendar = Calendar.getInstance();
        // get a date to represent "today"
        Date today = calendar.getTime();
        System.out.println("today:    " + today);
        // add one day to the date/calendar
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        // now get "tomorrow"
        Date tomorrow = calendar.getTime();
        //Format
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String datetime = dateFormat.format(tomorrow);
        System.out.println("format: " + datetime);
        // print out tomorrow's date
        System.out.println("tomorrow: " + tomorrow);
    }
    public static String getDate (int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String datetime = dateFormat.format(date);
        return datetime;
    }
//    public static void enumSelection (enum a){
//        a.getValue;
//    }
    public static Boolean matchedString(){
        String[] alphabet = new String[]{"A", "B", "C"};

        // Convert String Array to List
        List<String> list = Arrays.asList(alphabet);

        if(list.contains("A")) {
            System.out.println("Hello A");
        }
        // Convert to stream and test it Java 1.8 is neded
//        boolean result = Arrays.stream(alphabet).anyMatch("A"::equals);
//        if (result) {
//            System.out.println("Hello A");
//        }
        return true;
    }
    @Ignore
    @Test
    public void test() throws ParseException {
        matchedString();
//        System.out.println(randomString(10));
//        System.out.println(getDate(-100));
    }
}
