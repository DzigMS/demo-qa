package org.example.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverProvider {
    public static WebDriver getDriver() {
        String driverName = ConfigProvider.getDriverName();

        switch (driverName) {
            case "chrome":
                return WebDriverManager.chromedriver().create();
            case "firefox":
                return WebDriverManager.firefoxdriver().create();
            case "remote":
                return WebDriverManager.getInstance().getWebDriver();
            default:
                throw new IllegalArgumentException("Unknown type of webdriver");
        }
    }
}
