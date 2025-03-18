package io.company.outsera.healtcheck;


import io.company.outsera.utils.SetupTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Tags(value = {@Tag("Regressivo"), @Tag("Health")})
class HealthCheckTest extends SetupTest {

    @Test
    @DisplayName("[HealthCheck]")
    void healthCheck() {
        given()
                .get()
                .then().statusCode(HttpStatus.SC_OK);

    }
}
