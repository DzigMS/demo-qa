package org.example.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class WebDriverProvider {
    public static WebDriver getDriver(){
        String driverName = ConfigProvider.getDriverName();

        switch (driverName){
            case "chrome":
                return WebDriverManager.chromedriver().create();
            case "firefox":
                return WebDriverManager.firefoxdriver().create();
            default:
                throw new IllegalArgumentException("Unknown type of webdriver");
        }
    }
}
