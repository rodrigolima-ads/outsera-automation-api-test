package io.company.outsera.client;

import io.company.outsera.dto.LojaDTO;
import io.company.outsera.utils.constantes.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.company.outsera.utils.environment.ConfigurationManager.config;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LojaClient {
    @Step("Criar um novo pedido")
    public static ValidatableResponse criarNovoPedidoNaLojaClient(LojaDTO pedido) {

        return given()
                .basePath(config().basepath())
                .body(pedido)
                .when()
                .post(Endpoints.RESOURCE_STORE_ORDER)
                .then().log().all();

    }
}
