package Steps;

import org.junit.Test;

import static Steps.BuildUser.newBuilder;
import static Steps.ObjectFile.newBuilder;
/**
 * Created by Viachaslau_Balashevi on 5/26/2017.
 */
public class TestUser {
    @Test
    public void test(){
//        BuildUser user = newBuilder().businessType("11212121").build();
//        System.out.println(user.businessType);

        BuildUser user = BuildUser.newBuilder().businessType("122122").build();
        System.out.println(user.businessType);

//        new Builder().businessType("1234567890").build();
//        System.out.println(this.businessType);

    }
    @Test
    public void test2(){
        ObjectFile build = ObjectFile.newBuilder().isFile(false).build();
        System.out.println(build);

        ObjectFile file = ObjectFile.newBuilder().isFile(true).fileName("qqwqwqwq").build();
        System.out.println(file.isFile);
        System.out.println(file.fileName);
        System.out.println(file);

    }
}
