package br.com.clinicavetgama.repository;

import br.com.clinicavetgama.dto.response.TutorResponse;
import br.com.clinicavetgama.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query(value = "select count(t) > 0 from Tutor t where t.id <> ?1 and t.pet.id = ?2")
    boolean existeTutorPorIdPet(Long idTutor, Long idPet);

    @Query(value = "select count(t) > 0 from Tutor t where t.id <> ?1 and lower(t.documento) = ?2")
    boolean existeTutorPorDocumento(Long idTutor, String documento);

    @Query(value = "select count(t) > 0 from Tutor t where t.id <> ?1 and lower(t.email) = ?2")
    boolean existeTutorPorEmail(Long idTutor, String email);

    @Query(value = "select new br.com.clinicavetgama.dto.response.TutorResponse(t) from Tutor t")
    List<TutorResponse> listarTutores();

}
