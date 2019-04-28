package com.EqualExperts.QAExercise.Utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class JSONReader
{
    public String getJSON(String JSONdirectory, String fileName,String key)
    {
        JSONParser parser = new JSONParser();
        String name = null;
        try
        {
            String filePath = JSONdirectory+ File.separator+fileName;
            Object object = parser
                    .parse(new FileReader(filePath));

            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;

            //Reading the String
             name = jsonObject.get(key).toString();
            //System.out.println(name);

        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return name;
    }
}