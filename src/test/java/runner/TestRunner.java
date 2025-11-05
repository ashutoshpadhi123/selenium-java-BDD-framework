package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"steps","preprocessor"},
        plugin = {"pretty","html:target/cucumber-html-report","json:cucumber.json"}
)
public class TestRunner extends AbstractTestNGCucumberTests {


}
