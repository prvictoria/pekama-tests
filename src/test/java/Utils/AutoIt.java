package Utils;
/**
 * Created by Viachaslau Balashevich.
 * https:www.linkedin.com/in/viachaslau
 */
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutoIt {

    private static WebDriver driver = null;

    public static void main(String[] args) throws IOException, InterruptedException {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("http:toolsqa.wpengine.com/automation-practice-form");

        driver.findElement(By.id("photo")).click();

        Runtime.getRuntime().exec("src/test/lib/AutoItTest.exe"); //no executable file

        Thread.sleep(5000);

        driver.close();

    }

}