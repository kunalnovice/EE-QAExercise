package com.EqualExperts.QAExercise.APIs;

import com.EqualExperts.QAExercise.BeforeAfterHooks.API.HooksAPI;
import com.EqualExperts.QAExercise.Utilities.HeaderUtility;
import com.EqualExperts.QAExercise.Utilities.JSONReader;
import com.EqualExperts.QAExercise.Utilities.TestDataPropertyReader;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;

import static io.restassured.filter.log.ResponseLoggingFilter.logResponseTo;

@Component
public class Booking {

    @Autowired
    HeaderUtility headerUtility;

    @Autowired
    TestDataPropertyReader testDataPropertyReader;

    @Autowired
    JSONReader jsonReader;

    int bookingId;


    public RequestSpecification createRequestSpecification() {
        RequestSpecification requestSpecification=headerUtility.getRequestSpec();
                return requestSpecification;
    }
    public RequestSpecification createAuthRequestSpecification() {

        RequestSpecification requestSpecification=headerUtility.getBasicAuthenticReqSpec();
        return requestSpecification;
    }

    public Response executePOST(RequestSpecification spec,String jsonInstance ,List<String> pathAndFileName) {
        final StringWriter reqwriter = new StringWriter();
        final PrintStream reqcaptor = new PrintStream(new WriterOutputStream(reqwriter),true);
        final StringWriter reswriter = new StringWriter();
        final PrintStream rescaptor = new PrintStream(new WriterOutputStream(reswriter),true);
        String bookingURL=testDataPropertyReader.getPropertyValue("bookingAPI");
        String jsonString = jsonReader.getJSON(pathAndFileName.get(1),pathAndFileName.get(0),jsonInstance);
        Response response= spec.filter(new RequestLoggingFilter(reqcaptor))
                .filter(new ResponseLoggingFilter(rescaptor))
                .when().body(jsonString)
                .post(bookingURL).then().log().all().extract().response();
        HooksAPI.scenario.write(reqwriter.toString());
        HooksAPI.scenario.write(reswriter.toString());

        if(!(response.getBody().asString().equals("Internal Server Error")))
        bookingId= JsonPath.read(response.getBody().asString(),"$.bookingid");
        return response;
    }

    public boolean isResponse200(Response response) {

        if(response.getStatusCode()==200)
            return true;
        else
            return false;
    }

    public boolean isResponse400(Response response) {
        if(response.getStatusCode()==400)
            return true;
        else
            return false;
    }

    public boolean isResponse(Response response,String statusCode) {
        if(response.getStatusCode()==Integer.parseInt(statusCode))
            return true;
        else
            return false;
    }

    public Response executeDELETE(RequestSpecification spec) {
        final StringWriter reqwriter = new StringWriter();
        final PrintStream reqcaptor = new PrintStream(new WriterOutputStream(reqwriter),true);
        final StringWriter reswriter = new StringWriter();
        final PrintStream rescaptor = new PrintStream(new WriterOutputStream(reswriter),true);
        String deleteBookingURL=testDataPropertyReader.getPropertyValue("bookingAPI")+bookingId;
        Response response= spec.filter(new RequestLoggingFilter(reqcaptor))
                .filter(new ResponseLoggingFilter(rescaptor))
                .when().delete(deleteBookingURL).then().log().all().extract().response();
        HooksAPI.scenario.write(reqwriter.toString());
        HooksAPI.scenario.write(reswriter.toString());
        return response;
    }
}
