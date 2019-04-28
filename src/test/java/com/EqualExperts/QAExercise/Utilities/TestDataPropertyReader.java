package com.EqualExperts.QAExercise.Utilities;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TestDataPropertyReader {
    private static String User_dir = System.getProperty("user.dir")+ File.separator;
    private static String envDataPath = User_dir +"src/test/resources/TestData/envData.properties";

    PropertyReaderUtility propertyReader = new PropertyReaderUtility(envDataPath);

    public String getPropertyValue(String key) {
        return propertyReader.getPropertyValue(key);
    }
}
