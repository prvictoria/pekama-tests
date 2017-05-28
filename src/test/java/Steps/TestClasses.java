package Steps;

import org.junit.Test;

//import static Steps.BuildUser.newBuilder;
import static Page.UrlStrings.URL_PEKAMA_LOGIN;
import static Steps.ObjectUser.*;
import static Steps.ObjectUser.Users.OWNER;


/**
 * Created by Viachaslau_Balashevi on 5/26/2017.
 */
public class TestClasses {
    private static final String OWNER_LOGIN_EMAIL = null;
    private static final String OWNER_PASSWORD_PEKAMA = null;
    private static ObjectUser owner = newBuilder().email(OWNER_LOGIN_EMAIL).passwordPekama(OWNER_PASSWORD_PEKAMA).build();
    private static ObjectUser user = newBuilder().build();
    @Test
    public void test(){
//        BuildUser owner = newBuilder().businessType("11212121").build();
//        System.out.println(owner.businessType);

//        BuildUser owner = BuildUser.newBuilder().businessType("122122").build();
//        System.out.println(owner.businessType);

//        new Builder().businessType("1234567890").build();
//        System.out.println(this.businessType);

    }
    @Test
    public void test2(){
        ObjectFile build = ObjectFile.newBuilder().isFile(false).build();
        System.out.println(build);

        ObjectFile file = ObjectFile.newBuilder().isFile(true).fileName("qqwqwqwq").build();
        System.out.println(file);
        System.out.println(file.isFile);
        System.out.println(file.fileName);
        System.out.println("================================");

        ObjectUser user1 = newBuilder().phone("333-44-5555").isLoginSucceed(true).build();
        System.out.println(user1);
        System.out.println(user1.phone);
        System.out.println(user1.isLoginSucceed);
        System.out.println("================================");

        owner.login(owner.email, owner.passwordPekama, URL_PEKAMA_LOGIN);
        System.out.println(owner);
        System.out.println(owner.phone);
        System.out.println(owner.isLoginSucceed);
        System.out.println("================================");

    }
    @Test
    public void test_build_user(){
        ObjectUser owner = new ObjectUser(newBuilder()).buildUser(OWNER);
    }
}
