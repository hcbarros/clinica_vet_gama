package br.com.clinicavetgama.repository;

import br.com.clinicavetgama.dto.response.AgendamentoResponse;
import br.com.clinicavetgama.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query(value = "select a.dataHora from Agendamento a where a.id = (select max(a2.id) from Agendamento a2)")
    LocalDateTime ultimoHorario();

    @Query(value = "select new br.com.clinicavetgama.dto.response.AgendamentoResponse(a) from Agendamento a")
    List<AgendamentoResponse> listarAgendamentos();

}
