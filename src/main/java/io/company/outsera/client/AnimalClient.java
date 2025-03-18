package io.company.outsera.client;

import io.company.outsera.dto.AnimalDTO;
import io.company.outsera.utils.constantes.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.company.outsera.utils.environment.ConfigurationManager.config;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimalClient {
    @Step("Adicionar novos animais na loja")
    public static ValidatableResponse cadastrarNovoPetNaLoja(AnimalDTO pedido) {

        return given()
                .basePath(config().basepath())
                .body(pedido)
                .when()
                .post(Endpoints.RESOURCE_PET)
                .then().log().all();

    }
}
