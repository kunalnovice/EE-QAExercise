package com.EqualExperts.QAExercise.Utilities;


import Config.SpringConfig;
import com.EqualExperts.QAExercise.CustomisedLibraries.CommonFunction;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;

import static com.EqualExperts.QAExercise.Utilities.DriverProvider.driver;

@SpringBootTest(classes= SpringConfig.class)
@ContextConfiguration
@Component
public class ScreenshotCapability {



    @Autowired
    CommonFunction commonFunction;

    @Autowired
    TestDataPropertyReader testDataPropertyReader;

    final String USER_DIR=System.getProperty("user.dir");
    String screenShotDir =USER_DIR+"/target/Screenshots/ScenarioScreenshots/";



    public void takeScreenShot() {
        if (testDataPropertyReader.getPropertyValue("screenshot").equalsIgnoreCase("yes")) {
            String currentTime = commonFunction.getCurrentDate();
            File dir = new File(screenShotDir );
            dir.mkdir();

            try {
                File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrnsht, new File(screenShotDir+ "/" + currentTime + ".png"));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void takeScreenShot(String screenshotName) {
        if (testDataPropertyReader.getPropertyValue("screenshot").equalsIgnoreCase("yes")) {
            File dir = new File(screenShotDir );
            dir.mkdir();

            try {
                File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrnsht, new File(screenShotDir+ "/" + screenshotName + ".png"));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }



}
