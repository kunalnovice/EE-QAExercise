package TestRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        monochrome = true,
        format= {"pretty" ,"html:target/cucumber-original-html-api",
                "json:target/cucumber-json-reports/cucumber-report-api.json",
                "junit:target/cucumber-api.xml",
                "rerun:target/rerun-api.txt"},
        glue ={"classpath:com.EqualExperts.QAExercise.StepDefs.API", "classpath:com.EqualExperts.QAExercise.BeforeAfterHooks.API"},
        features = "classpath:AllFeatures", tags = "@testapi"  )
public class TestRunnerAPI {
}
