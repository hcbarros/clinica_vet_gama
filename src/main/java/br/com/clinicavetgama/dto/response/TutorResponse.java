package br.com.clinicavetgama.dto.response;

import br.com.clinicavetgama.model.Tutor;

import java.io.Serializable;

public class TutorResponse implements Serializable {

    private Long id;

    private String nome;

    private String endereco;

    private String telefone;

    private String email;

    private String documento;

    private PetResponse pet;


    public TutorResponse() { }

    public TutorResponse(Tutor tutor) {
        this.id = tutor.getId();
        this.nome = tutor.getNome();
        this.endereco = tutor.getEndereco();
        this.telefone = tutor.getTelefone();
        this.email = tutor.getEmail();
        this.documento = tutor.getDocumento();
        if(tutor.getPet() != null) {
            this.pet = new PetResponse(tutor.getPet());
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public PetResponse getPet() {
        return pet;
    }

    public void setPet(PetResponse pet) {
        this.pet = pet;
    }

}
