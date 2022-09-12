package br.com.clinicavetgama.controllers;

import br.com.clinicavetgama.dto.response.AgendamentoResponse;
import br.com.clinicavetgama.dto.request.AgendamentoRequest;
import br.com.clinicavetgama.services.AgendamentoService;
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

@Api(value="API REST agendamentos")
@RestController
    @RequestMapping("/api/v1/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;


    @ApiOperation(value="Cria um agendamento")
    @PostMapping
    public ResponseEntity<AgendamentoResponse> criarAgendamento(@Valid @RequestBody
                                                           AgendamentoRequest agendamentoRequest) {

        AgendamentoResponse agendamento = service.criarAgendamento(agendamentoRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agendamento.getId())
                .toUri();
        return ResponseEntity.created(location).body(agendamento);
    }

    @ApiOperation(value="Edita um agendamento")
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> editarAgendamento(@PathVariable("id") Long id,
                                                                 @Valid @RequestBody AgendamentoRequest agendamentoRequest) {
        AgendamentoResponse agendamento = service.editarAgendamento(id, agendamentoRequest);
        return new ResponseEntity<>(agendamento, HttpStatus.OK);
    }

    @ApiOperation(value="Exclui um agendamento")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable("id") Long id) {
        service.excluirAgendamento(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="Lista todos os agendamentos")
    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> listarTodosAgendamentos() {
        List<AgendamentoResponse> agendamentos = service.listarTodosAgendamentos();
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }

    @ApiOperation(value="Lista um agendamento específico")
    @GetMapping("{id}")
    public ResponseEntity<AgendamentoResponse> listarAgendamentoEspecifico(@PathVariable("id") Long id) {
        AgendamentoResponse agendamento = service.listarAgendamentoEspecifico(id);
        return new ResponseEntity<>(agendamento, HttpStatus.OK);
    }

}
