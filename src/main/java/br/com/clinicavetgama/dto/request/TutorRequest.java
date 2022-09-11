package br.com.clinicavetgama.dto.request;

import br.com.clinicavetgama.model.Tutor;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class TutorRequest implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotBlank(message = "{nome.tutor}")
    @Size(min = 2, max = 100, message = "{tamanho.nome.tutor}")
    private String nome;

    @NotBlank(message = "{endereco}")
    @Size(min = 5, max = 100, message = "{tamanho.endereco}")
    private String endereco;

    @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?[\\s9]?\\d{4}-?\\d{4}$", message = "{telefone.tutor.formato}")
    @NotNull(message = "{telefone.tutor.vazio}")
    private String telefone;
    
    @Pattern(regexp = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$", message = "{email.tutor.valido}")
    @NotNull(message = "{email.tutor.vazio}")
    private String email;

    @NotBlank(message = "{documento.tutor.vazio}")
    @Size(min = 5, max = 100, message = "{tamanho.documento}")
    private String documento;

    @NotNull(message = "{pet.id.vazio}")
    private Long petId;


    public TutorRequest() { }

    public TutorRequest(String nome, String endereco, String telefone, String email, String documento, Long petId) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.documento = documento;
        this.petId = petId;
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

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

}
