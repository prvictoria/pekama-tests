package Utils;
import org.junit.Assert;
import org.junit.Test;

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
    public static void pingPekamaServer(String host){
        reachable = false;
        try{
            InetAddress address = InetAddress.getByName(host);
            reachable = address.isReachable(5000);

            System.out.println("Is host reachable? " + reachable);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void pingTest(){
        pingPekamaServer("tut.by");
        Assert.assertTrue(reachable);
    }


}
