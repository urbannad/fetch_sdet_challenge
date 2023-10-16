package fetch.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt" ,
                "json:target/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = "fetch/steps",
        dryRun = false,
        tags = ""
)

public class TestRunner {

}
