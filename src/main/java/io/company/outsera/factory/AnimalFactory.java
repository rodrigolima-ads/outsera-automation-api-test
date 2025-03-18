package io.company.outsera.factory;

import io.company.outsera.dto.Animais;
import io.company.outsera.dto.AnimalDTO;
import io.company.outsera.dto.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimalFactory {

    public static AnimalDTO novoPetNaLojaFactory(Animais animal, Status status){

        Faker faker = new Faker();

        return AnimalDTO.builder()
                .id(faker.hashCode())
                .status(status.getAtual())
                .name(animal.name())
                .photoUrls(List.of(faker.image().base64JPG()))
                .category(AnimalDTO.Category.builder()
                        .id(1)
                        .name("Bicho de estimação").build())
                .tags(List.of(AnimalDTO.Tags.builder()
                        .id(1)
                        .name(faker.animal().name()).build())).build();



    }
}
