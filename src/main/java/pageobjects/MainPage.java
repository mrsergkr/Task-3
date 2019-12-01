package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends PageObject {

    /*private By fromTextField = By.xpath("//input[@id='from']"); // Поле: Откуда?
    private By toTextField = By.xpath("//input[@id='to']");   // Поле: Куда?
    private By whenTextField = By.xpath("//input[@id='when']");   // Поле: Когда?
    private By searchButton = By.xpath("//button[@class='Button SearchForm__submit']");   // Кнопка: Найти!*/

    @FindBy(id = "from")
    WebElement fromTextField; // Поле Откуда?
    @FindBy(id = "to")
    WebElement toTextField; // Поле: Куда?
    @FindBy(id = "when")
    WebElement whenTextField; // Поле: Когда?
    @FindBy(css = ".Button.SearchForm__submit")
    WebElement searchButton; // Кнопка: Найти!

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void load () {
        driver.get("https://rasp.yandex.ru/");
        wait.until(ExpectedConditions.visibilityOfAllElements(fromTextField, toTextField, whenTextField, searchButton));
    }

    // Вводит в поле "Откуда?" стартовую точку
    public void enterInFromTextField (String startPoint) {
        fromTextField.clear();
        wait.until(ExpectedConditions.attributeToBe(fromTextField, "value", "")); // ждём пока поле не очистится
        fromTextField.sendKeys(startPoint);
        wait.until(ExpectedConditions.attributeToBe(fromTextField, "value", startPoint)); // ждём пока не закончится ввод
    }

    // Вводит в поле "Куда?" конечную точку
    public void enterInToTextField (String destinationPoint) {
        toTextField.clear();
        wait.until(ExpectedConditions.attributeToBe(toTextField, "value", "")); // ждём пока поле не очистится
        toTextField.sendKeys(destinationPoint);
        wait.until(ExpectedConditions.attributeToBe(toTextField, "value", destinationPoint)); // ждём пока не закончится ввод
    }

    // Вводит в поле "Когда?" дату прописью
    public void enterInWhenTextField (String date) {
        whenTextField.clear();
        wait.until(ExpectedConditions.attributeToBe(whenTextField, "value", "")); // ждём пока поле не очистится
        whenTextField.sendKeys(date);
        wait.until(ExpectedConditions.attributeToBe(whenTextField, "value", date)); // ждём пока не закончится ввод
    }

    // Заполняет все поля
    public void enterInAllTextField (String startPoint, String destinationPoint, String date) {
        enterInFromTextField(startPoint);
        enterInToTextField(destinationPoint);
        enterInWhenTextField(date);
    }

    public void chooseVehicleType(String vehicle) {
        String xpath = String.format("//span[text()='%s']/..", vehicle);
        WebElement webElement = driver.findElement(By.xpath(xpath));
        if (!webElement.isSelected())
            webElement.click();
    }

    // Кликает по кнопке "Найти"
    public SearchPage clickOnSearchButton () {
        searchButton.click();
        return new SearchPage(driver,wait);
    }

}
