package io.company.outsera.client;

import io.company.outsera.dto.UsuarioDTO;
import io.company.outsera.utils.constantes.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.company.outsera.utils.environment.ConfigurationManager.config;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioClient {
    private static final String USUARIO_NAME = "username";
    @Step("Cadastrar um novo usuario na loja")
    public static ValidatableResponse cadastrarUsuarioClient(UsuarioDTO novoUsuario) {

        return given()
                .basePath(config().basepath())
                .body(novoUsuario)
                .when()
                .post(Endpoints.RESOURCE_USER)
                .then().log().all();

    }
    @Step("Consultar usuario cadastrado")
    public static ValidatableResponse consultarUsuarioClient(String nomeUsuario) {

        return given()
                .basePath(config().basepath())
                .pathParam(USUARIO_NAME, nomeUsuario)
                .when()
                .get(Endpoints.RESOURCE_USER_NAME)
                .then().log().all();
    }

    @Step("Atualizar usuario cadastrado")
    public static ValidatableResponse atualizarDadosUsuarioClient(UsuarioDTO usuario) {

        return given()
                .basePath(config().basepath())
                .pathParam(USUARIO_NAME,usuario.getUsername())
                .body(usuario)
                .when()
                .put(Endpoints.RESOURCE_USER_NAME)
                .then().log().all();

    }
    @Step("Excluir usuario cadastrado")
    public static ValidatableResponse excluirUsuarioClient(String usuario) {

        return given()
                .basePath(config().basepath())
                .pathParam(USUARIO_NAME,usuario)
                .body(usuario)
                .when()
                .delete(Endpoints.RESOURCE_USER_NAME)
                .then().log().all();

    }
}
