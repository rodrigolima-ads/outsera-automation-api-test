package io.company.outsera.contrato;

import io.company.outsera.utils.SetupTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

import static io.company.outsera.client.UsuarioClient.*;
import static io.company.outsera.factory.UsuarioFactory.criarNovoUsuarioFactory;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Tags(value = {@Tag("Regressivo"), @Tag("Contrato")})
class UsuarioContratoTest extends SetupTest {


    private static final String PATH_CONSULTAR_USUARIO_CADASTRADO = "schemas/usuario-cadastrado-200.json";
    private static final String PATH_CONSULTAR_USUARIO_ENCONTRADO = "schemas/usuario-encontrado-200.json";
    private static final String PATH_CONSULTAR_USUARIO_EXCLUIDO = "schemas/usuario-excluido-200.json";
    private static final String PATH_CONSULTAR_USUARIO_NAO_CADASTRADO = "schemas/usuario-nao-encontrado-404.json";

    @Test
    @DisplayName(" Validar contrato da API do petstore ao pesquisar um usuario cadastrado")
    @DisabledIfSystemProperty(named = "env", matches = "hom" ,disabledReason = "Bug in env=hom")
    void deveValidarContratoDaAPIDePetstoreAoPesquisaUmUsuarioCadastrado() {
        /// Criar usuario para ser cadastrado
        var novoUsuario = criarNovoUsuarioFactory();

        /// Validar contrato
        cadastrarUsuarioClient(novoUsuario);

        ///  Validar contratado
        consultarUsuarioClient(novoUsuario.getUsername())
                .body(matchesJsonSchemaInClasspath(PATH_CONSULTAR_USUARIO_ENCONTRADO));
    }
    @Test
    @DisplayName(" Validar contrato da API do petstore ao cadastrar um novo usuario")
    @DisabledIfSystemProperty(named = "env", matches = "hom", disabledReason = "Bug in env=hom")
    void deveValidarContratoDaAPIDePetstoreAoCadastrarUmNovoUsuario() {
        /// Criar usuario para ser cadastrado
        var novoUsuario = criarNovoUsuarioFactory();

        /// Validar contrato
        cadastrarUsuarioClient(novoUsuario)
                .body(matchesJsonSchemaInClasspath(PATH_CONSULTAR_USUARIO_CADASTRADO));
    }
    @Test
    @Disabled("Bug in env=local e hom")
    @DisplayName(" Validar contrato da API do petstore ao excluir um usuario")
    void deveValidarContratoDaAPIDePetstoreAoExcluirUmUsuario() {
        /// Criar usuario para ser cadastrado
        var usuario = criarNovoUsuarioFactory();

        /// Realizar o cadastro do usuario atraves da API.
        cadastrarUsuarioClient(usuario);

        /// Validar contrato
        excluirUsuarioClient(usuario.getUsername())
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath(PATH_CONSULTAR_USUARIO_EXCLUIDO));
    }
    @Test
    @DisabledIfSystemProperty(named = "env",matches = "local",disabledReason = "Bug in env=local")
    @DisplayName(" Validar contrato da API do petstore ao NÃO encontrar um usuario")
    void deveValidarContratoDaAPIDePetstoreAoNaoEncontrarUmUsuario() {

        ///  Validar contrato
        consultarUsuarioClient("outseraup").statusCode(HttpStatus.SC_NOT_FOUND)
                .body(matchesJsonSchemaInClasspath(PATH_CONSULTAR_USUARIO_NAO_CADASTRADO));
    }
}
