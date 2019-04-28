package com.EqualExperts.QAExercise.StepDefs.UI;

import Config.SpringConfig;
import com.EqualExperts.QAExercise.PageObjects.HotelBookingPage;
import com.EqualExperts.QAExercise.Utilities.TestDataPropertyReader;
import com.EqualExperts.QAExercise.PageObjects.BasePage;
import com.EqualExperts.QAExercise.PageObjects.WikiHomePage;
import com.EqualExperts.QAExercise.PageObjects.WikiSearchResultPage;
import com.EqualExperts.QAExercise.Utilities.ArrayEvaluationUtility;
import com.EqualExperts.QAExercise.Utilities.ScenarioLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes= SpringConfig.class)
@ContextConfiguration
public class BaseDefs {

    @Autowired
    ScenarioLogging scenarioLogging;
    @Autowired
    TestDataPropertyReader testDataPropertyReader;
    @Autowired
    BasePage basePage;
    @Autowired
    HotelBookingPage hotelBookingPage;


    public String getScenarioDesc() {
        return scenarioLogging.getScenarioDesc();
        //int rowNumber = getRowNumber
    }

    public void addMoreInfoAboutScenario(String str) {
         scenarioLogging.addMoreInfoAboutScenario(str);
    }

}
