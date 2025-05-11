package factories;

import Config.ConfigReader;
import lombok.Getter;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    @Getter
    private static WebDriver driver;

    public static WebDriver initDriver() {
        String browser = System.getProperty("browser", ConfigReader.get("browser"));

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--no-first-run");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }
    public static void launchApplication() {
        initDriver();
        String baseUrl = ConfigReader.get("base.url");
        driver.get(baseUrl);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
