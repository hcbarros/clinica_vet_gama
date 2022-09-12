package br.com.clinicavetgama.dto.response;

import br.com.clinicavetgama.enums.Procedimento;
import br.com.clinicavetgama.enums.StatusAgendamento;
import br.com.clinicavetgama.model.Agendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoResponse implements Serializable {

    private Long id;

    private TutorResponse tutor;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Procedimento> procedimentos = new ArrayList<>();

    @JsonFormat(pattern="dd/MM/uuuu HH:mm")
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;


    public AgendamentoResponse() { }

    public AgendamentoResponse(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.tutor = new TutorResponse(agendamento.getTutor());
        this.procedimentos = agendamento.getProcedimentos();
        this.dataHora = agendamento.getDataHora();
        this.status = agendamento.getStatus();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TutorResponse getTutor() {
        return tutor;
    }

    public void setTutor(TutorResponse tutor) {
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
