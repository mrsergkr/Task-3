package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObject (WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // Проверяет, виден ли элемент на веб-странице
    public boolean webElementIsVisible(WebElement webElement) {
        /*System.out.println("Веб-элемент виден? " + webElement.isDisplayed());
        System.out.println("Текст веб-элемента: " + webElement.getText() + "\n");*/
        return webElement.isDisplayed();
    }

    // Проверяет, видны ли элементы из списки на веб-странице
    public boolean webElementsAreVisible(List<WebElement> webElements) {
        //System.out.println(webElements.size());
        for (WebElement webElement: webElements) {
            if (!webElementIsVisible(webElement))
                return false;
        }
        return true;
    }

    // Проверяет, если ли текст в текстовом элементе
    public boolean webElementIsEmpty(WebElement webElement) {
        /*System.out.println("Веб-элемент виден? " + webElement.isDisplayed());
        System.out.println("Текст веб-элемента: " + webElement.getText() + "\n");*/
        return webElement.getText().isEmpty();
    }

    // Проверяет, если ли текст в текстовых элементах из списка
    public boolean webElementsAreNotEmpty(List<WebElement> webElements) {
        //System.out.println(webElements.size());
        for (WebElement webElement: webElements) {
            if (webElementIsEmpty(webElement))
                return false;
        }
        return true;
    }
}
