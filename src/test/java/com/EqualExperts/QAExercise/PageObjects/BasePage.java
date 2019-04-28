package com.EqualExperts.QAExercise.PageObjects;

import com.EqualExperts.QAExercise.CustomisedLibraries.StringFunction;
import com.EqualExperts.QAExercise.Utilities.ParameterTransformation;
import com.EqualExperts.QAExercise.Utilities.ScreenshotCapability;
import com.EqualExperts.QAExercise.Utilities.SeleniumHelper;
import com.EqualExperts.QAExercise.Utilities.TestDataPropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.EqualExperts.QAExercise.Utilities.DriverProvider.driver;

@Component
public class BasePage {


    @Autowired
    SeleniumHelper seleniumHelper;

    @Autowired
    ScreenshotCapability screenshotCapability;

    @Autowired
    ParameterTransformation parameterTransformation;
    @Autowired
    StringFunction stringFunction;
    @Autowired
    TestDataPropertyReader testDataPropertyReader;

 public void loadURL(String url) {

     driver.get(url);
 }
    public String getCurrentUrl() {
        seleniumHelper.waitForPageToLoad();
        return driver.getCurrentUrl();
    }


}
