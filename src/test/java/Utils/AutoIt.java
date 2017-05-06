package Utils;
/**
 * Created by Viachaslau Balashevich.
 * https:www.linkedin.com/in/viachaslau
 */
import java.io.IOException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class AutoIt {

    static WebDriver driver;
    String URL = "C:\\Users\\Harry\\Desktop\\samplehtml.html";

    @Test
    public void testUpload() throws InterruptedException, IOException
    {
        driver = new FirefoxDriver();
        driver.get(URL);
        WebElement element = driver.findElement(By.name("file"));
        element.click();
        //Which calls the autoit exe file
        Runtime.getRuntime().exec("D:\\PRJ\\gradle-migration\\src\\test\\resources\\upload_script.exe");
    }
}