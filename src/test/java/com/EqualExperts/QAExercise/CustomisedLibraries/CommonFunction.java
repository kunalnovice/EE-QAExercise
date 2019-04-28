package com.EqualExperts.QAExercise.CustomisedLibraries;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;


@Component
public class CommonFunction {

    Calendar cal;
    SimpleDateFormat sdf;
    String strDate;

    public String getRandomString() {
        System.out.println("I am here");
        return "abc";
    }

    public String getCurrentDate() {
        cal= Calendar.getInstance();
        sdf= new SimpleDateFormat("ddMMYYYY_HHmmss");
        strDate=sdf.format(cal.getTime());
        //System.out.println("current date in String Format is"+strDate);
        return strDate;

    }






}
