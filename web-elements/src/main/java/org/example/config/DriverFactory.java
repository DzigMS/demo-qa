package org.example.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver getDriver(){
        String browserName = ConfigProvider.getDriverName();

        WebDriver browser = switch (browserName.toLowerCase()) {
            case "chrome" -> WebDriverManager.chromedriver().create();
            case "firefox" -> WebDriverManager.firefoxdriver().create();
            default -> throw new IllegalArgumentException("Unsupported browser");
        };

        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigProvider.timeout()));

        return browser;
    }
}
