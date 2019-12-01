package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends MainPage {

    /*@FindBy(css = ".TransportSelectorLinks.SearchLinks__transportSelector")
    WebElement vehicleTypeChoiceDiv; // блок div, в котором находятся кнопки выбора траспортного средства*/

    @FindBy(xpath = "//div[contains(@class,'SearchTransfer__toggler')]//button")
    List<WebElement> transferRoutesHidingButtons; // кнопки, по нажатию на которые, скрываются/раскрываются маршруты с пересадками

    // название рейса = номер транспортного средства (если есть) + название маршрута
    @FindBy(className = "SegmentTitle__number")
    List<WebElement> vehicleNumbers; // номера транспортных средств
    @FindBy(className = "SegmentTitle__title")
    List<WebElement> routeNames; // названия маршрутов (в виде "исходная точка - точка назначения")

    @FindBy(className = "SearchSegment__duration")
    List<WebElement> travelTimes; // время в пути
    @FindBy(css = ".TransportIcon.TransportIcon_showHint")
    List<WebElement> transportIcons; // иконки транспорта

    @FindAll({@FindBy(xpath = "//section[@class='SearchSegments']/article"),
            @FindBy(xpath = "//section[@class='SearchSegments']/div[contains(@class,'SearchTransfer')]")})
    List<WebElement> summaryVoyages; // итоговые рейсы = прямые рейсы + рейсы с пересадками

    @FindBy(className = "ErrorPageSearchForm__title")
    List<WebElement> errorMessages; // сообщениях об ощибках

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Разворачивает маршруты с пересадками
    public void showTransferRoutes() {
        // Явное ожидание при помощи wait.until(..) не работает. Любые используемые проверки говорят о том, что с кнопками
        // "Развернуть" можно взаимодействовать. Однако какой-то div их ненадолго перекрывает. Приходится использовать sleep(..).
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        //wait.until(ExpectedConditions.elementToBeClickable(textHidingButtons));
        for (WebElement button: transferRoutesHidingButtons) {
            if (!button.isSelected()) {
                button.click();
            }
        }
        wait.until(ExpectedConditions.visibilityOfAllElements(routeNames));
    }

    // Подсчитывает итоговое количество рейсов (прямые рейсы + рейсы с пересадками)
    public int getSummaryVoyagesCount() {
        return summaryVoyages.size();
    }

    // Номера всех ли транспортных средств отображаются?
    public boolean vehicleNumbersAreDisplayed() {
        return webElementsAreVisible(vehicleNumbers) && webElementsAreNotEmpty(vehicleNumbers);
    }

    // Названия всех ли маршрутов (в виде "исходная точка - точка назначения") транспортных средств отображаются?
    public boolean routeNamesAreDisplayed() {
        return webElementsAreVisible(routeNames) && webElementsAreNotEmpty(routeNames);
    }

    // Время в пути отображается для всех рейсов?
    public boolean travelTimesAreDisplayed() {
        return webElementsAreVisible(travelTimes) && webElementsAreNotEmpty(travelTimes);
    }

    // Иконки транспорта отображаются для всех рейсов?
    public boolean transportIconsAreDisplayed() {
        return webElementsAreVisible(transportIcons);
    }

    // Ищет ошибку с текстом «Пункт прибытия не найден. Проверьте правильность написания или выберите другой город."
    public String getDestinationPointErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfAllElements(errorMessages));
        return errorMessages.get(1).getText();
    }

    // Получает сообщения обо всех ошибках.
    public List<String> getErrorMessages() {
        wait.until(ExpectedConditions.visibilityOfAllElements(errorMessages));
        List<String> errorMessages = new ArrayList<>();
        for (WebElement errorMessage: this.errorMessages) {
            errorMessages.add(errorMessage.getText());
        }
        return errorMessages;
    }
}
