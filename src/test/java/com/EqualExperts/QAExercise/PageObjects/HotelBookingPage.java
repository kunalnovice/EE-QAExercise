package com.EqualExperts.QAExercise.PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HotelBookingPage extends BasePage {

    String fNameLoc = "id:firstname";
    String sNameLoc = "id:lastname";
    String totalPriceLoc = "id:totalprice";
    String depositLoc = "id:depositpaid";
    String checkInLoc = "id:checkin";
    String checkOutLoc = "id:checkout";
    String savebtnLoc = "css:input[value=' Save ']";
    String headingLoc = "css:h1";
    String newCreatedRowLoc = "xpath://p[contains(text(),'PLACEHOLDER')]//ancestor::div[2]";
    String newCreatedRowLNameLoc = "css:div[id='PLACEHOLDER'] div:nth-child(2)";
    String newCreatedRowDepositLoc = "css:div[id='PLACEHOLDER'] div:nth-child(4)";
    String deleteCreatedBookingLoc= "css:input[onclick='deleteBooking(PLACEHOLDER)']";


    String firstNameValue = null;
    String surNameValue = null;
    String createdUniqueId = null;


    public boolean isValidPageDIsplayed() {
        return seleniumHelper.isTextPresentinElement(headingLoc, "Hotel booking form");
    }

    public void fillPageDetails(List<String> formParams, List<String> formValues) {
        Map<String, String> hotelBookingParam = new HashMap<>();
        hotelBookingParam = parameterTransformation.transformListtoMap(formParams, formValues);
        firstNameValue = hotelBookingParam.get("FNAME") + stringFunction.getRandomAphabets(4);
        surNameValue = hotelBookingParam.get("SNAME") + stringFunction.getRandomAphabets(4);
        seleniumHelper.enterText(fNameLoc, firstNameValue);
        seleniumHelper.enterText(sNameLoc, surNameValue);
        seleniumHelper.enterText(totalPriceLoc, hotelBookingParam.get("PRICE"));
        seleniumHelper.selectByVisibleText(depositLoc, hotelBookingParam.get("DEPOSIT"));
        seleniumHelper.enterText(checkInLoc, hotelBookingParam.get("CHECKIN"));
        seleniumHelper.enterText(checkOutLoc, hotelBookingParam.get("CHECKOUT"));
    }


    public void savesBooking() throws InterruptedException {
        seleniumHelper.click(savebtnLoc);
        Assert.assertTrue(seleniumHelper.isGivenTextPresentInPageSource(firstNameValue));
        setCreatedID();
    }

    private void setCreatedID() {
        String actualCreatedRowLoc = newCreatedRowLoc.replace("PLACEHOLDER", firstNameValue);
        WebElement element = seleniumHelper.getElement(actualCreatedRowLoc);
        createdUniqueId=element.getAttribute("id");
    }

    public boolean isNewRowCreated(List<String> formParams, List<String> formValues) {
        boolean lNameCheck1 =seleniumHelper.isTextPresentinElement((newCreatedRowLNameLoc.replace("PLACEHOLDER",createdUniqueId)),surNameValue);
        Map<String, String> hotelBookingParam = new HashMap<>();
        hotelBookingParam = parameterTransformation.transformListtoMap(formParams, formValues);
        boolean lDepositCheck2= seleniumHelper.isTextPresentinElement((newCreatedRowDepositLoc.replace("PLACEHOLDER",createdUniqueId)),hotelBookingParam.get("DEPOSIT"));
        screenshotCapability.takeScreenShot(testDataPropertyReader.getPropertyValue("browser")+"_"+createdUniqueId+"_"+firstNameValue+"_NewBooking");
        return lNameCheck1 && lDepositCheck2 ;
        //I have performed 2 random checks, it can be done based on better understanding of system
    }

    public void deletesBooking() {
        String actualToBeDeletedBookingLoc= deleteCreatedBookingLoc.replace("PLACEHOLDER",createdUniqueId);
        seleniumHelper.click(actualToBeDeletedBookingLoc);
        screenshotCapability.takeScreenShot(testDataPropertyReader.getPropertyValue("browser")+"_AfterDeletion");
    }

    public boolean isLastCreatedRowAbsent() {
        return seleniumHelper.isGivenTextAbsentInPageSource(firstNameValue);
    }
}