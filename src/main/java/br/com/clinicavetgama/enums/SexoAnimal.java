package br.com.clinicavetgama.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SexoAnimal {
    MASCULINO, FEMININO;

    @JsonCreator
    public static SexoAnimal fromString(String name) {
        for(SexoAnimal sexo : values()) {
            if(name != null && name.toUpperCase().contains(sexo.name().substring(0, 4))) {
                return sexo;
            }
        }
        return null;
    }

}
