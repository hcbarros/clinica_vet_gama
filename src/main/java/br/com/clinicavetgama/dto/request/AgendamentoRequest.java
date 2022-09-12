package br.com.clinicavetgama.dto.request;

import br.com.clinicavetgama.enums.Procedimento;
import br.com.clinicavetgama.enums.StatusAgendamento;
import br.com.clinicavetgama.model.Agendamento;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoRequest implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull(message = "{tutor.id.vazio}")
    private Long tutorId;

    @NotEmpty(message = "{procedimentos.vazio}")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<@NotNull(message = "{procedimento.invalido}") Procedimento> procedimentos = new ArrayList<>();

    @NotNull(message = "{status.vazio}")
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;


    public AgendamentoRequest() { }

    public AgendamentoRequest(Long tutorId, List<Procedimento> procedimentos, StatusAgendamento status) {
        this.tutorId = tutorId;
        this.procedimentos = procedimentos;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }

}
