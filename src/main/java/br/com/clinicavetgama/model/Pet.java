package br.com.clinicavetgama.model;

import br.com.clinicavetgama.enums.Porte;
import br.com.clinicavetgama.enums.SexoAnimal;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_pet")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{nome.pet}")
    @Size(min = 2, max = 100, message = "{tamanho.nome.pet}")
    private String nome;

    @Max(value = 200, message = "{pet.idade.maxima}")
    @Min(value = 0, message = "{pet.idade.minima}")
    private Integer idade;

    @NotBlank(message = "{raca.vazia}")
    @Size(min = 2, max = 100, message = "{tamanho.raca}")
    private String raca;

    @NotNull(message = "{porte.vazio}")
    @Enumerated(EnumType.STRING)
    private Porte porte;

    @NotBlank(message = "{especie.vazia}")
    @Size(min = 2, max = 100, message = "{tamanho.especie}")
    private String especie;

    @NotNull(message = "{sexo.animal.vazio}")
    @Enumerated(EnumType.STRING)
    private SexoAnimal sexo;

    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Tutor tutor;


    public Pet() { }

    public Pet(String nome, Integer idade, String raca, Porte porte, String especie, SexoAnimal sexo) {
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.especie = especie;
        this.porte = porte;
        this.sexo = sexo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public SexoAnimal getSexo() {
        return sexo;
    }

    public void setSexo(SexoAnimal sexo) {
        this.sexo = sexo;
    }

}
