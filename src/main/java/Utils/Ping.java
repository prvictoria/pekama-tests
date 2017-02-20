package Utils;
import java.net.InetAddress;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Ping {
    public static boolean reachable;
    public static void main(String[] args){
        reachable = false;
        try{
            InetAddress address = InetAddress.getByName("8.8.8.8");
            reachable = address.isReachable(10000);

            System.out.println("Is host reachable? " + reachable);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}