package Steps;

import org.junit.Test;

import static Steps.BuildUser.newBuilder;

/**
 * Created by Viachaslau_Balashevi on 5/26/2017.
 */
public class TestUser {
    @Test
    public void test(){
//        BuildUser user = newBuilder().businessType("11212121").build();
//        System.out.println(user.businessType);

        BuildUser user = newBuilder().businessType("122122").build();
        System.out.println(user.businessType);

//        new Builder().businessType("1234567890").build();
//        System.out.println(this.businessType);

    }
}
