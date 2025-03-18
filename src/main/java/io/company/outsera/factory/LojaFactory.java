package io.company.outsera.factory;

import io.company.outsera.dto.Animais;
import io.company.outsera.dto.LojaDTO;
import io.company.outsera.dto.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LojaFactory {

    public static LojaDTO criarNovoPedidoFactory(Animais animal, Status status){

        Faker faker = new Faker();

        return LojaDTO.builder()
                .id(faker.hashCode())
                .petId(animal.getEspecie())
                .quantity(faker.number().numberBetween(1,20))
                .status(status.getAtual()).build();


    }
}
