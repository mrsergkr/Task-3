package ru.yandex.rasp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {

    @FindBy(id = "from")
    WebElement startingPointInputField;

    @FindBy(id = "to")
    WebElement destinationPointInputField;

    @FindBy(id = "when")
    WebElement dateInputField;

    @FindBy(css = ".Button.SearchForm__submit")
    WebElement searchButton;


    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void enterStartingPointInputField(String startingPoint) {
        startingPointInputField.clear();
        pause();
        startingPointInputField.sendKeys(startingPoint);
    }

    public void enterDestinationPointInputField(String destinationPoint) {
        destinationPointInputField.clear();
        pause();
        destinationPointInputField.sendKeys(destinationPoint);
    }

    public void enterDateInputField(String date) {
        dateInputField.clear();
        pause();
        dateInputField.sendKeys(date);
    }

    public void enterAllInputFields(String startingPoint, String destinationPoint, String date) {
        enterStartingPointInputField(startingPoint);
        enterDestinationPointInputField(destinationPoint);
        enterDateInputField(date);
    }

    public void clickSearchButton() {
        searchButton.click();
    }
}
