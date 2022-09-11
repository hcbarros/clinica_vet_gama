package br.com.clinicavetgama.services;

import br.com.clinicavetgama.dto.request.PetRequest;
import br.com.clinicavetgama.dto.response.PetResponse;
import br.com.clinicavetgama.model.Pet;
import br.com.clinicavetgama.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;


    public PetResponse adicionarPet(PetRequest petRequest) {
        ModelMapper mapper = new ModelMapper();
        Pet pet = mapper.map(petRequest, Pet.class);
        pet = repository.save(pet);
        return new PetResponse(pet);
    }

    public PetResponse editarPet(Long id, PetRequest petRequest) {
        buscarPet(id);
        petRequest.setId(id);
        return adicionarPet(petRequest);
    }

    public void removerPet(Long id) {
        buscarPet(id);
        repository.deleteById(id);
    }

    public List<PetResponse> listarPets(){
        return repository.listarPets();
    }

    public PetResponse buscarPet(Long id){
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum pet encontrado com o Id informado!"));
        return new PetResponse(pet);
    }

}
