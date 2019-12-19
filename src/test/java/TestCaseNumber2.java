import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;
import pageobjects.SearchPage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

// Поиск транспорта по нехорошему направлению
public class TestCaseNumber2 {

    static WebDriver driver;
    static WebDriverWait wait;
    static SearchPage searchPage;

    // Находит ближайший день недели (ближайшую среду, в частности)
    public static String comingDayOfWeek(DayOfWeek dayOfWeek) {
        /*LocalDate date = LocalDate.now();
        if (date.getDayOfWeek() != dayOfWeek) {
            date = date.with(TemporalAdjusters.next(dayOfWeek));
        }
        Locale loc = Locale.forLanguageTag("ru");
        StringBuilder str = new StringBuilder(date.getDayOfMonth() + " " + date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, loc));
        str = str.deleteCharAt(str.length() - 1).append('я');
        return str.toString().toLowerCase();*/

        String[] months = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        LocalDate date = LocalDate.now();
        if (date.getDayOfWeek() != dayOfWeek) {
            date = date.with(TemporalAdjusters.next(dayOfWeek));
        }
        return date.getDayOfMonth() + " " + months[date.getMonthValue() - 1];
    }

    @BeforeAll
    public static void setUp() {
        /*System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
        driver = new FirefoxDriver();*/
        driver = DriverFabric.getDriver("chrome");
        wait = new WebDriverWait(driver, 20);

        // 1. Пользователь открывает сайт https://rasp.yandex.ru
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.load();

        // 2. Вводит пункт отправления “Кемерово”; 3. Вводит пункт назначения “Москва”; 4. Вводит дату на ближайшую среду
        mainPage.enterInAllTextField("Кемерово проспект Ленина","Кемерово Бакинский переулок",
                comingDayOfWeek(DayOfWeek.WEDNESDAY));

        // 5. Нажимает на «Автобус»
        mainPage.chooseVehicleType("Автобус");
        // 6. Нажимает Найти
        searchPage = mainPage.clickOnSearchButton();
    }

    @Test
    // 7. Проверяет, что отображается ошибка с текстом «Пункт прибытия не найден. Проверьте правильность написания или выберите другой город."
    public void checkErrorMessages() {
        Assertions.assertEquals("Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.",
                searchPage.getDestinationPointErrorMessage());
    }

    @AfterAll
    public static void finish() {
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }*/
        if (driver != null) {
            driver.quit();
        }
    }
}
