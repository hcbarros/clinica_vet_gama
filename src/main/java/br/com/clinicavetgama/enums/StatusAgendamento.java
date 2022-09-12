package br.com.clinicavetgama.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusAgendamento {
    CONFIRMADO, ATENDIDO, CANCELADO, FALTOU;

    @JsonCreator
    public static StatusAgendamento fromString(String name) {
        for(StatusAgendamento status : values()) {
            if(name != null && name.toUpperCase().contains(status.name().substring(0, 4))) {
                return status;
            }
        }
        return null;
    }

}
