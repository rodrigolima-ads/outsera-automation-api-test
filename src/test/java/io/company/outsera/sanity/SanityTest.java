package io.company.outsera.sanity;

import io.company.outsera.utils.SetupTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

import static io.company.outsera.client.AnimalClient.cadastrarNovoPetNaLoja;
import static io.company.outsera.client.LojaClient.criarNovoPedidoNaLojaClient;
import static io.company.outsera.client.UsuarioClient.cadastrarUsuarioClient;
import static io.company.outsera.client.UsuarioClient.consultarUsuarioClient;
import static io.company.outsera.dto.Animais.COELHO;
import static io.company.outsera.dto.Animais.TARTARUGA;
import static io.company.outsera.dto.Status.APROVADO;
import static io.company.outsera.dto.Status.DISPONIVEL;
import static io.company.outsera.factory.AnimalFactory.novoPetNaLojaFactory;
import static io.company.outsera.factory.LojaFactory.criarNovoPedidoFactory;
import static io.company.outsera.factory.UsuarioFactory.criarNovoUsuarioFactory;
import static org.hamcrest.CoreMatchers.is;

@Tags(value = {@Tag("Regressivo"), @Tag("Sanity")})
class SanityTest extends SetupTest {


    @Test
    @DisplayName("Validar funcionalidade de realizar um cadastro de usuario")
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
    @DisplayName("Validar funcionalidade de realizar um pedido")
    void deveRealizarUmNovoPedidoComSucesso(){

        var novaOrder = criarNovoPedidoFactory(COELHO, APROVADO);

        /// Criar pedido na loja;
        criarNovoPedidoNaLojaClient(novaOrder)
                .statusCode(HttpStatus.SC_OK)
                .body("petId",is(4),"status",is("approved"));

    }
    @Test
    @DisplayName("Validar funcionalidade de cadastrar novo animal de estimação na loja")
    void deveRealizarCadastroDeAnimalNaLoja(){

        var novoPet = novoPetNaLojaFactory(TARTARUGA, DISPONIVEL);

        /// Adiciona novos pet na loja;
        cadastrarNovoPetNaLoja(novoPet)
                .statusCode(HttpStatus.SC_OK)
                .body("name",is(TARTARUGA.name()),"status",is("available"));

    }
}
