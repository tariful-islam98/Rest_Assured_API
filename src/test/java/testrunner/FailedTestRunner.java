package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "@target/failed-scenarios.txt",
        glue = "stepdefinitions",
        monochrome = true,
        plugin = {"html:allure-results/ey-failed.html",
                "json:allure-results/ey-failed.json"
        }
)

public class FailedTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
