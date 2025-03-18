package io.company.outsera.dto;

import lombok.Getter;

@Getter
public enum Animais {
   GATO(1), CAO(2), PASSARO(3), COELHO(4), TARTARUGA(5);

   private final int especie;

   Animais(int especie) {
      this.especie = especie;
   }

}
