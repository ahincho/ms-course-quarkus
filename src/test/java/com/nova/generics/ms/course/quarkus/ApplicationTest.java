package com.nova.generics.ms.course.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * Integration test that boots the Quarkus runtime on the test port
 * and exercises the welcome endpoint end-to-end.
 */
@QuarkusTest
class ApplicationTest {

    @Test
    void welcomeEndpointReturnsExpectedJson() {
        given()
                .when().get("/api/notifications/email/welcome")
                .then()
                .statusCode(200)
                .body("sent", is(true))
                .body("channel", is("email"));
    }
}