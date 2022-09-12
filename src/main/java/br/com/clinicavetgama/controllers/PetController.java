package br.com.clinicavetgama.controllers;

import br.com.clinicavetgama.dto.request.PetRequest;
import br.com.clinicavetgama.dto.response.AgendamentoResponse;
import br.com.clinicavetgama.dto.response.PetResponse;
import br.com.clinicavetgama.services.PetService;

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

@Api(value="API REST pets")
@RestController
@RequestMapping("/api/v1/pet")
public class PetController {

    @Autowired
    private PetService petService;


    @ApiOperation(value="Salva um pet")
    @PostMapping
    public ResponseEntity<PetResponse> adicionarPet(@Valid @RequestBody PetRequest petRequest) {
        PetResponse response = petService.adicionarPet(petRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @ApiOperation(value="Edita um pet")
    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> editarPet(@PathVariable("id") Long id,
                                        @Valid @RequestBody PetRequest petRequest) {
        PetResponse response = petService.editarPet(id, petRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value="Remove um pet")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removerPet(@PathVariable("id") Long id) {
        petService.removerPet(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="Lista todos os pets")
    @GetMapping
    public ResponseEntity<List<PetResponse>> listarTodosPets() {
        List<PetResponse> pets = petService.listarPets();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @ApiOperation(value="Lista um pet específico")
    @GetMapping("{id}")
    public ResponseEntity<PetResponse> listarPetEspecifico(@PathVariable("id") Long id) {
        PetResponse pet = petService.buscarPet(id);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

}
