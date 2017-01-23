package Utils;
import com.codeborne.selenide.Selenide;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.isFirefox;
import static org.junit.Assume.assumeTrue;

/**
 * Created by Viachaslau_Balashevic on 1/9/2017.
 */
public class HttpAuth {
    public void httpAuthStagingPekama() {
        assumeTrue(isFirefox());
        Selenide.open("https://staging.pekama.com/",
                "",
                "qweeco",
                "qw33coStudi0");
        sleep(250);

   }
   public static void httpAuthStagingCommunity() {
        assumeTrue(isFirefox());
        Selenide.open("https://communitystaging.pekama.com/",
                "",
                "qweeco",
                "qw33coStudi0");
        sleep(250);

    }
    public static void httpAuthUrl(String AUTH_URL) {
//        assumeTrue(isFirefox());
        Selenide.open(AUTH_URL,
                "",
                "qweeco",
                "qw33coStudi0");
        sleep(250);

    }
}
