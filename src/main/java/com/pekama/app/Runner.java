package com.pekama.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Viachaslau_Balashevi on 12/28/2016.
 */
public class Runner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        Thread.sleep(3000);
        driver.get("http://stackoverflow.com/");
        driver.findElement(By.id("nav-questions")).click();

      //  driver.close();
    }
}
