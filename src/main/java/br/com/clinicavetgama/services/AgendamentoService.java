package br.com.clinicavetgama.services;

import br.com.clinicavetgama.dto.response.AgendamentoResponse;
import br.com.clinicavetgama.dto.request.AgendamentoRequest;
import br.com.clinicavetgama.dto.response.TutorResponse;
import br.com.clinicavetgama.model.Agendamento;
import br.com.clinicavetgama.model.Tutor;
import br.com.clinicavetgama.repository.AgendamentoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private TutorService tutorService;

    private Logger LOG = LogManager.getLogger(AgendamentoService.class);

    public AgendamentoResponse criarAgendamento(AgendamentoRequest agendamentoRequest) {
        LOG.info("Obtendo o tutor de id = "+agendamentoRequest.getTutorId()+" para criar o agendamento");
        TutorResponse tutorResponse = tutorService.buscarTutor(agendamentoRequest.getTutorId());
        ModelMapper mapper = new ModelMapper();
        Agendamento agendamento = mapper.map(agendamentoRequest, Agendamento.class);
        Tutor tutor = mapper.map(tutorResponse, Tutor.class);
        LOG.info("Preparando-se para criar o agendamento");
        agendamento.setTutor(tutor);
        agendamento.setDataHora(agendarHorario());
        agendamento = repository.save(agendamento);
        return new AgendamentoResponse(agendamento);
    }

    public AgendamentoResponse editarAgendamento(Long id, AgendamentoRequest agendamentoDto) {
        listarAgendamentoEspecifico(id);
        LOG.info("Preparando-se editar agendamento de id = "+id);
        agendamentoDto.setId(id);
        return criarAgendamento(agendamentoDto);
    }

    public void excluirAgendamento(Long id) {
        listarAgendamentoEspecifico(id);
        LOG.info("Preparando-se para excluir o agendamento de id = "+id);
        repository.deleteById(id);
    }

    public List<AgendamentoResponse> listarTodosAgendamentos(){
        LOG.info("Preparando-se para listar todos os agendamentos");
        return repository.listarAgendamentos();
    }

    public AgendamentoResponse listarAgendamentoEspecifico(Long id){
        LOG.info("Preparando-se para obter o agendamento de id = "+id);
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum agendamento encontrado com o Id informado!"));
        return new AgendamentoResponse(agendamento);
    }

    private LocalDateTime agendarHorario() {

        LOG.info("Agendando horario...");
        LocalDateTime now = LocalDateTime.now().plusDays(1).withHour(0);
        LocalDateTime ultimoHorario = repository.ultimoHorario();
        if(ultimoHorario != null && (ultimoHorario.isEqual(now) || ultimoHorario.isAfter(now))) {
            now = ultimoHorario;
        }
        int dia = now.getDayOfWeek().getValue();
        int hora = (now.getHour() < 8) ? 8 : (now.getHour() + 1);
        now = hora > 17 ? now.plusDays(dia > 4 ? (8 - dia) : 1) : now;
        hora = hora > 17 ? 8 : hora;
        return now.withHour(hora).withMinute(0);
    }

}
