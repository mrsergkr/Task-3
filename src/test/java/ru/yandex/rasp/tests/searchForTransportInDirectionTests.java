package ru.yandex.rasp.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.rasp.pages.MainPage;
import ru.yandex.rasp.pages.SearchResultsPage;

public class searchForTransportInDirectionTests {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static SearchResultsPage searchResultsPage;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15, 500);

        mainPage = new MainPage(driver, wait);
        // 1. Пользователь открывает сайт https://rasp.yandex.ru
        driver.get("https://rasp.yandex.ru/");
        // 2. Вводит пункт отправления “Кемерово”; 3. Вводит пункт назначения “Москва”; 4. Вводит дату 7 сентября
        mainPage.enterAllInputFields("Кемерово", "Москва", "7 сентября");
        // 5. Нажимает Найти
        mainPage.clickSearchButton();
        searchResultsPage = new SearchResultsPage(driver, wait);
        searchResultsPage.waitForSearchVoyageTitles();
    }

    // 6. Проверяет, что отображается название рейса.
    @Test
    public void checkVoyagesAreDisplayed() {
        for (WebElement webElement: searchResultsPage.voyageTitles) {
            Assert.assertEquals("Кемерово — Москва", webElement.getText());
        }

        for (WebElement webElement: searchResultsPage.voyageNumbers) {
            Assert.assertNotEquals("", webElement.getText());
        }
    }

    // 7. Проверяет, что у направления есть время в пути.
    @Test
    public void checkVoyagesHaveTravelTime() {
        for (WebElement webElement: searchResultsPage.voyageTravelTimes) {
            Assert.assertNotEquals("", webElement.getText());
        }
    }

    // 8. Проверяет, что у всех рейсов есть иконка транспорта.
    @Test
    public void checkVoyageHaveTransportIcon() {
        for (WebElement webElement: searchResultsPage.voyageTransportIcons) {
            Assert.assertNotNull(webElement);
        }
    }

    // 9. Проверяет, что рейсов отобразилось 5.
    @Test
    public void Checks5VoyagesAreDisplayed() {
        Assert.assertEquals(5, searchResultsPage.voyageTitles.size());
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}