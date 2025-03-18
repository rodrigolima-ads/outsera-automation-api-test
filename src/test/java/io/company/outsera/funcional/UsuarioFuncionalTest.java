package io.company.outsera.funcional;

import io.company.outsera.utils.SetupTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

import static io.company.outsera.client.UsuarioClient.*;
import static io.company.outsera.factory.UsuarioFactory.criarNovoUsuarioFactory;
import static org.hamcrest.CoreMatchers.is;
@Tags(value = {@Tag("Regressivo"), @Tag("Funcional")})
class UsuarioFuncionalTest extends SetupTest {


    @Test
    @DisabledIfSystemProperty(named = "env",matches = "hom",disabledReason = "Bug in env=hom")
    @DisplayName("Validar a funcionalidade de pesquisar um usuario cadastrado")
    void deveRealizarABuscaPorUmUsuarioCadastradoComSucesso(){
        /// Criar usuario para ser cadastrado
        var novoUsuario = criarNovoUsuarioFactory();

        /// Validar contrato
        cadastrarUsuarioClient(novoUsuario);

        /// Validar busca por usuario cadastrado;
        consultarUsuarioClient(novoUsuario.getUsername())
                .statusCode(HttpStatus.SC_OK)
                .body("username",is(novoUsuario.getUsername()));

    }

    @Test
    @DisplayName("Validar a funcionalidade de realizar um cadastro de usuario")
    @DisabledIfSystemProperty(named = "env",matches = "hom",disabledReason = "Bug in env=hom")
    void deveRealizarOCadastramentoDoNovoUsuarioComSucesso(){

        /// Criar usuario para ser cadastrado
        var novoUsuario = criarNovoUsuarioFactory();

        /// Realizar o cadastro do usuario atraves da API.
        cadastrarUsuarioClient(novoUsuario);

        /// Validar o cadastro do novo usuario;
        consultarUsuarioClient(novoUsuario.getUsername())
                .statusCode(HttpStatus.SC_OK)
                .body("username",is(novoUsuario.getUsername()),"lastName",is(novoUsuario.getLastName()));

    }

    @Test
    @DisabledIfSystemProperty(named = "env",matches = "hom",disabledReason = "Bug in env=hom")
    @DisplayName("Validar a funcionalidade de atualização um usuario")
    void deveRealizarAtualizacaoDeUmUsuarioComSucesso(){

        /// Criar usuario para ser cadastrado
        var usuario = criarNovoUsuarioFactory();

        /// Realizar o cadastro do usuario atraves da API.
        cadastrarUsuarioClient(usuario);

        /// Atualizar dados do usuario;
        usuario.setEmail("email@atualizado.com");
        usuario.setPhone("119555555");

        atualizarDadosUsuarioClient(usuario)
                .statusCode(HttpStatus.SC_OK)
                .body("email",is("email@atualizado.com"),"phone",is("119555555"));


    }
    @Test
    @DisabledIfSystemProperty(named = "env",matches = "hom",disabledReason = "Bug in env=hom")
    @DisplayName("Validar a funcionalidade de excluir um usuario cadastrado")
    void deveRealizarExclusaoDeUmUsuarioComSucesso(){

        /// Criar usuario para ser cadastrado
        var usuario = criarNovoUsuarioFactory();

        /// Realizar o cadastro do usuario atraves da API.
        cadastrarUsuarioClient(usuario);

        /// Excluir usuario cadastrado;
        excluirUsuarioClient(usuario.getUsername())
                .statusCode(HttpStatus.SC_OK);

        /// Validar busca por usuario não cadastrado;

        consultarUsuarioClient(usuario.getUsername())
                .statusCode(HttpStatus.SC_NOT_FOUND);


    }

    @Test
    @DisplayName("Validar a funcionalidade de pesquisar para usuario não cadastrado")
    void deveRetorna404AoTentaRealizarABuscaPorUmUsuarioNAOCadastrado(){

        /// Validar busca por usuario não cadastrado;
        consultarUsuarioClient("outseraup").statusCode(HttpStatus.SC_NOT_FOUND);


    }
    @Test
    @DisplayName("[Bug env_local] - Validar a funcionalidade de excluir um usuario não cadastrado")
    @DisabledIfEnvironmentVariable(named = "env",matches = "local",disabledReason = "Bug in env=local")
    void deveRetorna404AoTentaRealizarAExclusaoPorUmUsuarioNAOCadastrado(){

        /// Validar busca por usuario não cadastrado;
        excluirUsuarioClient("outseraup").statusCode(HttpStatus.SC_NOT_FOUND);


    }

}
