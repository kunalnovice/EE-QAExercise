package com.EqualExperts.QAExercise.Utilities;




import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class RequestHelper  {

    @Autowired
    HeaderUtility headerUtility;

    public RequestSpecification getRequestSpec()  {
        //FileOperations.preArchiveSteps();
        RequestSpecification reqSpec =null;//= heade
                //.cookies(SessionData.getCookies())
                //.header("headernname","headerValue");
        return reqSpec;
    }

    public Response assertGETReponse200(RequestSpecification reqSpec, String endPoint) {
        Response resp = reqSpec.when()
                .log().all()
                .get(endPoint)
                .then()
                .log().all()
                .extract().response();
        //FileOperations.appendFiles(source,interim);
        //BasicValidation.isResponse200(resp);

        return resp;
    }

    public Response assertPOSTReponse200(RequestSpecification reqSpec, String endPoint, String StringJSON) {
        Response resp = reqSpec.body(StringJSON).when()
                .log().all()
                .post(endPoint)
                .then()
                .log().all()
                .extract().response();
        //FileOperations.appendFiles(source,interim);
        //BasicValidation.isResponse200(resp);

        return resp;
    }

    public Response assertPOSTReponse400(RequestSpecification reqSpec, String endPoint, String StringJSON) {
        Response resp = reqSpec.body(StringJSON).when()
                .log().all()
                .post(endPoint)
                .then()
                .log().all()
                .extract().response();
        //FileOperations.appendFiles(source,interim);
        //BasicValidation.isResponse400(resp);

        return resp;
    }
}
