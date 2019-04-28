package com.EqualExperts.QAExercise.StepDefs.UI;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

public class HotelBookingStepDefs extends BaseDefs {


    @Given("^User submit Parameters \"(.*?)\" with values \"(.*?)\" on Hotel Booking Page$")
    public void userSubmitValuesInTheParametersOfHotelBookingPage(List<String> formParams, List<String> formValues) throws Throwable {
        basePage.loadURL(testDataPropertyReader.getPropertyValue("baseURL"));
        Assert.assertTrue(hotelBookingPage.isValidPageDIsplayed());
        hotelBookingPage.fillPageDetails(formParams,formValues);



    }

    @When("^User saves the Booking$")
    public void userSavesTheBooking() throws Throwable {
        hotelBookingPage.savesBooking();
    }

    @Then("^Hotel Booking Page parameters \"(.*?)\" contains values \"(.*?)\"$")
    public void hotelBookingPageContainsValuesInTheParameters(List<String> formParams, List<String> formValues) throws Throwable {

        Assert.assertTrue(hotelBookingPage.isNewRowCreated(formParams,formValues));
    }

    @Given("^Booking exist for Parameters \"(.*?)\" with values \"(.*?)\"$")
    public void bookingExistForParametersWithValues(List<String> formParams, List<String> formValues) throws Throwable {
        basePage.loadURL(testDataPropertyReader.getPropertyValue("baseURL"));
        Assert.assertTrue(hotelBookingPage.isValidPageDIsplayed());
        hotelBookingPage.fillPageDetails(formParams,formValues);
        hotelBookingPage.savesBooking();
        Assert.assertTrue(hotelBookingPage.isNewRowCreated(formParams,formValues));
    }

    @When("^User deletes the Booking$")
    public void userDeletesTheBooking() throws Throwable {
        hotelBookingPage.deletesBooking();
    }

    @Then("^Booking is deleted$")
    public void bookingIsDeleted() throws Throwable {
        Assert.assertTrue(hotelBookingPage.isLastCreatedRowAbsent());

    }

}
