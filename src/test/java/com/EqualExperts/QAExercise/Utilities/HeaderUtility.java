package com.EqualExperts.QAExercise.Utilities;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class HeaderUtility {
    public RequestSpecification getRequestSpec()  {

        RequestSpecification reqSpec = given().contentType(ContentType.JSON)
                .accept("application/json")
                .header("User-Agent", "MyAutomationProject")
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Accept-Encoding","gzip, deflate")
                .header("Host","hotel-test.equalexperts.io")
                .header("Referer","http://hotel-test.equalexperts.io/")
                .header("X-Requested-With","XMLHttpRequest")
                .header("Connection","keep-alive");



        return reqSpec;
    }

    public RequestSpecification getBasicAuthenticReqSpec()  {

        RequestSpecification basicAuthSpec = getRequestSpec()
                .header("authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");


        return basicAuthSpec;
    }
}
