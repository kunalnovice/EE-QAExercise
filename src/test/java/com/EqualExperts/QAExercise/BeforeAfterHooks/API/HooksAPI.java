package com.EqualExperts.QAExercise.BeforeAfterHooks.API;

import com.EqualExperts.QAExercise.Utilities.DriverProvider;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;


public class HooksAPI {

    public static Scenario scenario;
    @Autowired
    DriverProvider driverProvider;

    @Before
    public void beforeHooks(Scenario scenario) throws Exception {
        HooksAPI.scenario= scenario;


    }
}
