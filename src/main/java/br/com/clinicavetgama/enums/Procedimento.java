package br.com.clinicavetgama.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EntityNotFoundException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public enum Procedimento {
    CONSULTA, VACINACAO, CASTRACAO, INTERNACAO;

    @JsonCreator
    public static Procedimento fromString(String name) {
        for(Procedimento proced : values()) {
            if(name != null && semAcento(name).toUpperCase().contains(proced.name().substring(0, 4))) {
                return proced;
            }
        }
        return null;
    }

    public static String semAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

}
