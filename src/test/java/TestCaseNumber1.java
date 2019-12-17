import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;
import pageobjects.SearchPage;

// Поиск транспорта по направлению
public class TestCaseNumber1 {

    static WebDriver driver;
    static WebDriverWait wait;
    static SearchPage searchPage;

    @BeforeClass
    public static void setUp() {
        //driver = DriverFabric.getDriver("chrome");
        driver = DriverFabric.getDriver("safari");
        wait = new WebDriverWait(driver, 20);

        // 1. Пользователь открывает сайт https://rasp.yandex.ru
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.load();
        // 2. Вводит пункт отправления “Кемерово”; 3. Вводит пункт назначения “Москва”; 4. Вводит дату 7 сентября
        mainPage.enterInAllTextField("Кемерово", "Москва", "7 сентября");
        // 5. Нажимает Найти
        searchPage = mainPage.clickOnSearchButton();
        System.out.println("dfdfdf");
        //searchPage.load();
        searchPage.showTransferRoutes();
    }


    @Test
    // 6. Проверяет, что отображаются названия рейсов.
    // Название рейса = номер транспортного средства (если есть) + название маршрута
    public void voyageNamesAreDisplayed() {
        Assert.assertTrue(searchPage.vehicleNumbersAreDisplayed() & searchPage.routeNamesAreDisplayed());
    }

    @Test
    // 7. Проверяет, что у направлений есть время в пути.
    public void travelTimesAreDisplayed() {
        Assert.assertTrue(searchPage.travelTimesAreDisplayed());
    }

    @Test
    // 8. Проверяет, что у всех рейсов есть иконка транспорта.
    public void transportIconsAreDisplayed() {
        Assert.assertTrue(searchPage.transportIconsAreDisplayed());
    }

    @Test
    // 9. Проверяет, что рейсов отобразилось 5.
    public void summaryVoyagesCountIs5() {
        Assert.assertEquals(5, searchPage.getSummaryVoyagesCount());
    }

    @AfterClass
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
