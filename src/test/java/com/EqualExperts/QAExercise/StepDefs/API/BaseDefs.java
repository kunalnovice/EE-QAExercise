package com.EqualExperts.QAExercise.StepDefs.API;

import Config.SpringConfig;
import com.EqualExperts.QAExercise.APIs.Booking;
import com.EqualExperts.QAExercise.PageObjects.BasePage;
import com.EqualExperts.QAExercise.PageObjects.HotelBookingPage;
import com.EqualExperts.QAExercise.PageObjects.WikiHomePage;
import com.EqualExperts.QAExercise.PageObjects.WikiSearchResultPage;
import com.EqualExperts.QAExercise.Utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes= SpringConfig.class)
@ContextConfiguration
public class BaseDefs {
    @Autowired
    ArrayEvaluationUtility arrayEvaluationUtility;
    @Autowired
    ScenarioLogging scenarioLogging;
    @Autowired
    TestDataPropertyReader testDataPropertyReader;
    @Autowired
    HeaderUtility headerUtility;
    @Autowired
    RequestHelper requestHelper;
    @Autowired
    Booking booking;


    public String getScenarioDesc() {
        return scenarioLogging.getScenarioDesc();
        //int rowNumber = getRowNumber
    }

    public void addMoreInfoAboutScenario(String str) {
         scenarioLogging.addMoreInfoAboutScenario(str);
    }

}
