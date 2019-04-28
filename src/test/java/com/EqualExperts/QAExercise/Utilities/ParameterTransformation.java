package com.EqualExperts.QAExercise.Utilities;

import org.junit.Assert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ParameterTransformation {

    public Map<String,String> transformListtoMap(List<String> paramName, List<String> paramValue) {
        Assert.assertEquals(paramName.size(),paramValue.size());
        Map<String,String> map = new HashMap<>();
        int count=paramName.size();
        for(int i=0;i<count;i++) {
            map.put(paramName.get(i),paramValue.get(i));

        }
        return map;
    }
}
