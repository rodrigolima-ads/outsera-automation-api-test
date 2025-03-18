package io.company.outsera.dto;

import lombok.Getter;

@Getter
public enum Status {
   APROVADO("approved"), REPROVADO("disapprove"),DISPONIVEL("available"),INDISPONIVEL("unavailable");

    private final String atual;

    Status(String atual) {
        this.atual = atual;
    }
}
