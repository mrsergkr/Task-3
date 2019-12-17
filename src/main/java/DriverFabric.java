import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFabric {
    public static WebDriver getDriver(String type) {
        switch(type) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/user/Downloads/chromedriver");
                return new ChromeDriver();
            case "safari":
                System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
                return new SafariDriver();
        }
        System.setProperty("webdriver.chrome.driver", "/Users/user/Downloads/chromedriver");
        return new ChromeDriver();
    }
}
