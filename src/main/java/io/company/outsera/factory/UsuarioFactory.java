package io.company.outsera.factory;

import io.company.outsera.dto.UsuarioDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioFactory {

    public static UsuarioDTO criarNovoUsuarioFactory(){

        Faker faker = new Faker();

        return UsuarioDTO.builder()
                .id(faker.hashCode())
                .username(faker.internet().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .phone(faker.phoneNumber().cellPhone())
                .userStatus(10).build();


    }
}
