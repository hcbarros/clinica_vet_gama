package br.com.clinicavetgama.repository;

import br.com.clinicavetgama.dto.response.PetResponse;
import br.com.clinicavetgama.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(value = "select new br.com.clinicavetgama.dto.response.PetResponse(p) from Pet p")
    List<PetResponse> listarPets();
}
