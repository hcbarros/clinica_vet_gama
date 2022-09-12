package br.com.clinicavetgama.model;

import br.com.clinicavetgama.enums.Procedimento;
import br.com.clinicavetgama.enums.StatusAgendamento;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{tutor.vazio}")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @NotEmpty(message = "{procedimentos.vazio}")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<@NotNull(message = "{procedimento.invalido}") Procedimento> procedimentos = new ArrayList<>();

    private LocalDateTime dataHora;

    @NotNull(message = "{status.vazio}")
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;


    public Agendamento() { }

    public Agendamento(Tutor tutor, List<Procedimento> procedimentos, LocalDateTime dataHora, StatusAgendamento status) {
        this.tutor = tutor;
        this.procedimentos = procedimentos;
        this.dataHora = dataHora;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }
}
