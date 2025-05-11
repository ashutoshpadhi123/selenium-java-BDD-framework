package preprocessor;

import Config.ConfigReader;
import factories.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Hooks {
//    @Before
//    public void setUp() {
//        DriverFactory.initDriver();
//        String baseUrl = ConfigReader.get("base.url");
//        DriverFactory.getDriver().get(baseUrl);
//    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            if (scenario.isFailed()) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File src = ts.getScreenshotAs(OutputType.FILE);
                File dest = new File("screenshots/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png");
                Files.copy(src.toPath(), dest.toPath());
            }

            DriverFactory.quitDriver();
        }
    }
}
