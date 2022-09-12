package br.com.clinicavetgama.controllers;

import br.com.clinicavetgama.dto.request.TutorRequest;
import br.com.clinicavetgama.dto.response.PetResponse;
import br.com.clinicavetgama.dto.response.TutorResponse;
import br.com.clinicavetgama.services.TutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(value="API REST tutores")
@RestController
@RequestMapping("/api/v1/tutor")
public class TutorController {

    @Autowired
    private TutorService service;


    @ApiOperation(value="Salva um tutor")
    @PostMapping
    public ResponseEntity<TutorResponse> adicionarTutor(@Valid @RequestBody TutorRequest tutorRequest) {
        TutorResponse tutorResponse = service.adicionarTutor(tutorRequest);
           URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tutorResponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(tutorResponse);
    }

    @ApiOperation(value="Edita um tutor")
    @PutMapping("/{id}")
    public ResponseEntity<TutorResponse> editarTutor(@PathVariable("id") Long id,
                                                @Valid @RequestBody TutorRequest tutorRequest) {
        TutorResponse tutorResponse = service.editarTutor(id, tutorRequest);
        return new ResponseEntity<>(tutorResponse, HttpStatus.OK);
    }

    @ApiOperation(value="Remove um tutor")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removerTutor(@PathVariable("id") Long id) {
        service.removerTutor(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="Lista todos os tutores")
    @GetMapping
    public ResponseEntity<List<TutorResponse>> listarTodosTutores() {
        List<TutorResponse> tutores = service.listarTutores();
        return new ResponseEntity<>(tutores, HttpStatus.OK);
    }

    @ApiOperation(value="Lista um tutor específico")
    @GetMapping("{id}")
    public ResponseEntity<TutorResponse> listarPetEspecifico(@PathVariable("id") Long id) {
        TutorResponse tutor = service.buscarTutor(id);
        return new ResponseEntity<>(tutor, HttpStatus.OK);
    }

}
