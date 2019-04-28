package com.EqualExperts.QAExercise.BeforeAfterHooks.UI;

import com.EqualExperts.QAExercise.CustomisedLibraries.CommonFunction;
import com.EqualExperts.QAExercise.Utilities.DriverProvider;
import com.EqualExperts.QAExercise.Utilities.TestDataPropertyReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.springframework.beans.factory.annotation.Autowired;

import static com.EqualExperts.QAExercise.Utilities.DriverProvider.driver;


public class Hooks {

    public static Scenario scenario;
    @Autowired
    DriverProvider driverProvider;
    @Autowired
    CommonFunction commonFunction;
    @Autowired
    TestDataPropertyReader testDataPropertyReader;

    @Before
    public void beforeHooks(Scenario scenario) throws Exception {
        Hooks.scenario= scenario;
        System.out.println("i am in before");
        driverProvider.loadDriver();

    }

    @After(order = 0)
    public void quitDriver() throws Exception {
        if(testDataPropertyReader.getPropertyValue("screenshotAtEnd").equals("yes")) {
        scenario.write(driver.getCurrentUrl()+" "+commonFunction.getCurrentDate());
        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
        }
        TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
        driver.manage().deleteAllCookies();
        System.out.println("\nQuitting driver");
        driverProvider.getDriver().quit();
    }
}
