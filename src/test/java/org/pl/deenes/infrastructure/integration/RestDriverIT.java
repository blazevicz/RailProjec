package org.pl.deenes.infrastructure.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.TrainDTO;

import static io.restassured.RestAssured.given;

class RestDriverIT {


    @Test
    public void shouldAddNewTrain() {
        TrainDTO trainDto = new TrainDTO();

        given()
                .contentType(ContentType.JSON)
                .body(trainDto)
                .when()
                .post("/analyse")
                .then()
                .statusCode(200) // Oczekiwany kod odpowiedzi HTTP 200 (OK)
                .contentType(ContentType.JSON); // Oczekiwany typ zawartości odpowiedzi JSON
        // .body("someProperty", equalTo("example_value")); // Oczekiwana wartość pola someProperty w odpowiedzi
    }

}