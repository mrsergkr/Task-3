package ru.yandex.rasp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage extends Page {

    @FindBy(className = "SegmentTitle__title")
    public List<WebElement> voyageTitles;

    @FindBy(className = "SegmentTitle__number")
    public List<WebElement> voyageNumbers;

    @FindBy(className = "SearchSegment__duration")
    public List<WebElement> voyageTravelTimes;

    @FindBy(className = "TransportIcon__icon")
    public List<WebElement> voyageTransportIcons;

    @FindBy(className = "ErrorPageSearchForm__title")
    public List<WebElement> searchFormErrors;

    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitForSearchVoyageTitles() {
        wait.until(ExpectedConditions.visibilityOfAllElements(voyageTitles));
    }

    public void waitForFormErrors() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchFormErrors));
    }
}
