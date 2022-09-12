package br.com.clinicavetgama.services;

import br.com.clinicavetgama.dto.request.PetRequest;
import br.com.clinicavetgama.dto.response.PetResponse;
import br.com.clinicavetgama.model.Pet;
import br.com.clinicavetgama.repository.PetRepository;

import org.modelmapper.ModelMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.List;


@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    private Logger LOG = LogManager.getLogger(PetService.class);


    public PetResponse adicionarPet(PetRequest petRequest) {
        ModelMapper mapper = new ModelMapper();
        Pet pet = mapper.map(petRequest, Pet.class);
        LOG.info("Adicionando um pet");
        pet = repository.save(pet);
        return new PetResponse(pet);
    }

    public PetResponse editarPet(Long id, PetRequest petRequest) {
        buscarPet(id);
        LOG.info("Editando o pet de id = "+id);
        petRequest.setId(id);
        return adicionarPet(petRequest);
    }

    public void removerPet(Long id) {
        buscarPet(id);
        LOG.info("Removendo o pet de id = "+id);
        repository.deleteById(id);
    }

    public List<PetResponse> listarPets(){
        LOG.info("Listando todos os pets");
        return repository.listarPets();
    }

    public PetResponse buscarPet(Long id){
        LOG.info("Bunscando pet pelo id = "+id);
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum pet encontrado com o Id informado!"));
        return new PetResponse(pet);
    }

}
