package TestRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        monochrome = true,
        format= {"pretty" ,"html:target/cucumber-original-html-ui",
                "json:target/cucumber-json-reports/cucumber-report-ui.json",
                "junit:target/cucumber-ui.xml",
                "rerun:target/rerun-ui.txt"},
        glue ={"classpath:com.EqualExperts.QAExercise.StepDefs.UI", "classpath:com.EqualExperts.QAExercise.BeforeAfterHooks.UI"},
        features = "classpath:AllFeatures", tags = "@testui"  )
public class TestRunnerUI {
}
