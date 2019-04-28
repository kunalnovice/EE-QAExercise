package com.EqualExperts.QAExercise.StepDefs.API;

import com.EqualExperts.QAExercise.APIs.Booking;
import com.EqualExperts.QAExercise.StepDefs.API.BaseDefs;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HotelBookingStepDefs extends BaseDefs {

    RequestSpecification spec;
    Response response;

    @Given("^Request is created for API booking$")
    public void requestIsCreatedForAPIBooking() throws Throwable {
        spec= booking.createRequestSpecification();
    }

    @When("^POST Method is invoked with Parameters Body \"(.*?)\"$")
    public void POSTMethodisinvokedwithParametersBody(String jsonInstance,List<String> pathAndFileName) throws Throwable {
        response=booking.executePOST(spec,jsonInstance,pathAndFileName);
    }

    @Then("^Status is \"(.*?)\"$")
    public void statusIs(String statusCode) throws Throwable {
        Assert.assertTrue(booking.isResponse(response,statusCode));

    }

    @When("^DELETE Method is invoked for API Booking$")
    public void deleteMethodIsInvokedForAPIBooking() throws Throwable {
        spec=booking.createAuthRequestSpecification();
        response=booking.executeDELETE(spec);
    }

    @Test
    public void delete(){
        given()
                .accept("application/json")
                .header("authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .log()
                .all()
                .when()
                .delete("http://hotel-test.equalexperts.io/booking/64094")
                .then()
                .log()
                .all()
                .statusCode(201);
    }
}
