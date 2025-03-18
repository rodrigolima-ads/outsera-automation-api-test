package io.company.outsera.utils;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.parsing.Parser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.util.logging.Logger;

import static io.company.outsera.utils.constantes.Parameters.MAX_TIMEOUT;
import static io.company.outsera.utils.environment.ConfigurationManager.config;
import static io.restassured.http.ContentType.JSON;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetupTest {
    public static final Logger log = Logger.getLogger("EQUIPE_QA");

    @BeforeAll
    public static void setupAPI() {
        log.info("starting test setup ...");

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(config().baseuripetstore())
                .setContentType(JSON)
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new AllureRestAssured())
                .build();

        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        responseBuilder.setDefaultParser(Parser.JSON).build();
        RestAssured.responseSpecification = responseBuilder.build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

}