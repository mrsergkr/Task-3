package ru.yandex.rasp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void pause() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
