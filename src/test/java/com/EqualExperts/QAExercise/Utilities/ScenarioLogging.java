package com.EqualExperts.QAExercise.Utilities;

import com.EqualExperts.QAExercise.BeforeAfterHooks.UI.Hooks;
import org.springframework.stereotype.Component;

@Component
public class ScenarioLogging {

    public String getScenarioDesc() {
        String scenarioDesc = Hooks.scenario.getName();
        return scenarioDesc;
        //int rowNumber = getRowNumber
    }

    public void addMoreInfoAboutScenario(String str) {
        Hooks.scenario.write(str);

        //int rowNumber = getRowNumber
    }
}
