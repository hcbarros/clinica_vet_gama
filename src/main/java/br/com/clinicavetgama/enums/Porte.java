package br.com.clinicavetgama.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.text.Normalizer;
import java.util.regex.Pattern;

public enum Porte {

    PEQUENO, MEDIO, GRANDE;

    @JsonCreator
    public static Porte fromString(String name) {
        for(Porte porte : values()) {
            if(name != null && semAcento(name).toUpperCase().contains(porte.name().substring(0, 4))) {
                return porte;
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
