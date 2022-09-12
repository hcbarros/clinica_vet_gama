package br.com.clinicavetgama.controllers.response;

import java.util.Arrays;
import java.util.List;

public class ResponseError {

    private List<String> errors;

    public ResponseError(List<String> errors) {
        this.errors = errors;
    }

    public ResponseError(String error) {
        this.errors = Arrays.asList(error);
    }

    public List<String> getErrors() {
        return errors;
    }

}