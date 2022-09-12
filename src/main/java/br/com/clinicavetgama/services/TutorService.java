package br.com.clinicavetgama.services;

import br.com.clinicavetgama.dto.request.TutorRequest;
import br.com.clinicavetgama.dto.response.PetResponse;
import br.com.clinicavetgama.dto.response.TutorResponse;
import br.com.clinicavetgama.model.Pet;
import br.com.clinicavetgama.model.Tutor;
import br.com.clinicavetgama.repository.TutorRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.List;


@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private PetService petService;

    private Logger LOG = LogManager.getLogger(TutorService.class);


    @Transactional(rollbackOn = EntityExistsException.class)
    public TutorResponse adicionarTutor(TutorRequest tutorRequest) {
        ModelMapper mapper = new ModelMapper();
        LOG.info("Adicionando um tutor");
        Tutor tutor = mapper.map(tutorRequest, Tutor.class);
        if(tutorRequest.getPetId() != null) {
            PetResponse petResponse = petService.buscarPet(tutorRequest.getPetId());
            Pet pet = mapper.map(petResponse, Pet.class);
            tutor.setPet(pet);
        }
        tutor = repository.save(tutor);
        validarDados(tutor.getId(), tutorRequest);
        return new TutorResponse(tutor);
    }

    public TutorResponse editarTutor(Long id, TutorRequest tutor) {
        buscarTutor(id);
        LOG.info("Editando o tutor de id = "+id);
        tutor.setId(id);
        return adicionarTutor(tutor);
    }

    private void validarDados(Long id, TutorRequest tutor) {
        LOG.info("Validando dados de tutor");
        if(tutor.getPetId() != null && repository.existeTutorPorIdPet(id, tutor.getPetId())) {
            throw new EntityExistsException("Já existe um outro tutor cadastrado com esse pet!");
        }
        if(repository.existeTutorPorDocumento(id, tutor.getDocumento().toLowerCase())) {
            throw new EntityExistsException("Já existe um outro tutor cadastrado com esse documento!");
        }
        if(repository.existeTutorPorEmail(id, tutor.getEmail().toLowerCase())) {
            throw new EntityExistsException("Já existe um outro tutor cadastrado com esse email!");
        }
    }

    public void removerTutor(Long id) {
        buscarTutor(id);
        LOG.info("Editando o tutor de id = "+id);
        repository.deleteById(id);
    }

    public List<TutorResponse> listarTutores(){
        LOG.info("Listando todos os tutores");
        return repository.listarTutores();
    }

    public TutorResponse buscarTutor(Long id){
        LOG.info("Obtendo o tutor de id = "+id);
        Tutor tutor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum tutor encontrado com o Id informado!"));
        return new TutorResponse(tutor);
    }

}


