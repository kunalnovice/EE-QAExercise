package com.EqualExperts.QAExercise.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaderUtility {
    public  Properties prop = new Properties();
    //private static String User_dir = System.getProperty("user.dir")+ File.separator;
    //private static String testEnvironmentPath = User_dir + "src/test/resources/config/TestEnvironment.properties";
    //private static String JSONLocPath = User_dir +"src/test/resources/config/JSONLocator.properties";
    //private static String testDataPath = User_dir +"src/test/resources/TestData/testData.properties";
    InputStream inputStream=null;



    PropertyReaderUtility(String path) {loadProperties(path);}


    //static PropertyReader testDataObject = new PropertyReader(testDataPath);


    /*public static PropertyReader getTestDataObject(){
        return testDataObject;
    }*/
    public void loadProperties(String propFilePath) {
        try {
            if(propFilePath!= null) {
                inputStream = new FileInputStream(propFilePath);
                prop.load(inputStream);
            } else {
                System.out.println(propFilePath + "File not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyValue(String key) {
        return prop.getProperty(key);
    }
}


