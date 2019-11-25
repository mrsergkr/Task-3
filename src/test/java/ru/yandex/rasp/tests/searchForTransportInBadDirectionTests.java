package ru.yandex.rasp.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.rasp.pages.MainPage;
import ru.yandex.rasp.pages.SearchResultsPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class searchForTransportInBadDirectionTests {
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
        // 2. Вводит пункт отправления “Кемерово проспект Ленина”; 3. Вводит пункт назначения “Кемерово Бакинский переулок”;
        // 4. Вводит дату на ближайшую среду.
        mainPage.enterAllInputFields("Кемерово проспект Ленина", "Кемерово Бакинский переулок", getDateNextWednesday());
        // 5. Нажимает на «Автобус».

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        // 6. Нажимает Найти
        mainPage.clickSearchButton();
        searchResultsPage = new SearchResultsPage(driver, wait);
        searchResultsPage.waitForFormErrors();
    }

    public static String getDateNextWednesday() {
        String[] months = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        while (!"среда".equals(dateFormat.format(calendar.getTime()))) {
            calendar.add(5, 1);
        }
        return "" + calendar.get(5) + " " + months[calendar.get(2) - 1];
    }

    // 7. Проверяет, что отображается ошибка с текстом «Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.»
    @Test
    public void checkVoyagesAreDisplayed() {
        Assert.assertEquals(searchResultsPage.searchFormErrors.get(1).getText(), "Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}