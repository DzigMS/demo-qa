package org.example.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public abstract <T extends BasePage> T open();

    public void scrollTo(Point point) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo" + point.toString());
    }
}