package com.EqualExperts.QAExercise.Utilities;

import com.EqualExperts.QAExercise.BeforeAfterHooks.UI.Hooks;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ArrayEvaluationUtility {

    @Autowired
    ScreenshotCapability screenshotCapability;


    int[] ints ;

    public int[] parseAsArray(String strings) {

        if(!strings.equals("")) {
            String arrString[] = strings.split(";");
            ints = new int[arrString.length];
            for (int i = 0; i < arrString.length; i++) {
                ints[i] = Integer.parseInt(arrString[i].trim());
            }

        } else {
            ints=new int[0];
            Hooks.scenario.write("possible incorrect Array passed, investigate please.");
        }
        return ints;
    }

    public int getMaxOrderedNumbers(int[] ar) {
        int count = 1; int maxCount=1; int tempMaxCount=0;
        for(int i=0;i<ar.length-1;i++) {

            if(ar[i]<ar[i+1]) {
                count = count+1;
                tempMaxCount= count;
            } else {
                count =1;
            }
            if(tempMaxCount>maxCount) {
                maxCount=tempMaxCount;
            }

        }
        if(ar.length>0)
        return maxCount;
        else
        return 0;
    }

    public void assertExpectedCount(int countOfOrderedNos, Integer expectedCount) {
        if (expectedCount ==0) {
            System.out.println("No Values was given as expected output");
            System.out.println("countOfOrderedNos = "+countOfOrderedNos);
        } else
            Assert.assertTrue("countOfOrderedNos= "+countOfOrderedNos +" ,"+"expectedCount= "+expectedCount,countOfOrderedNos==expectedCount);
    }
}
