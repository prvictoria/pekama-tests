package Utils;
import org.junit.Test;

import java.util.Random;
/**
 * Created by Viachaslau_Balashevi on 1/9/2017.
 */
public class Utils {
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random rnd = new Random();

    public static String getRandomString(int len) {
        StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                    sb.append(AB.charAt(rnd.nextInt(AB.length())));
               }
               return sb.toString();
    }
}
