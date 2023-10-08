package org.pl.deenes.infrastructure.integration;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.pl.deenes.api.controller.dto.TrainDTO;

public interface DriverControllerTestSupport {

    RequestSpecification requestSpecification();

    default ExtractableResponse<Response> saveTrain(final TrainDTO trainDTO) {
        return requestSpecification()
                .body(trainDTO)
                .post("/api/train/analyse")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .extract();
    }
}
