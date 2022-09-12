package br.com.clinicavetgama;

import br.com.clinicavetgama.dto.request.AgendamentoRequest;
import br.com.clinicavetgama.dto.request.PetRequest;
import br.com.clinicavetgama.dto.request.TutorRequest;
import br.com.clinicavetgama.dto.response.AgendamentoResponse;
import br.com.clinicavetgama.dto.response.PetResponse;
import br.com.clinicavetgama.dto.response.TutorResponse;
import br.com.clinicavetgama.enums.Porte;
import br.com.clinicavetgama.enums.Procedimento;
import br.com.clinicavetgama.enums.SexoAnimal;
import br.com.clinicavetgama.enums.StatusAgendamento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClinicaVetGamaApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private static ResponseEntity<PetResponse> petResponse;
	private static PetRequest petRequest;
	private static ResponseEntity<TutorResponse> tutorResponse;
	private static TutorRequest tutorRequest;
	private static ResponseEntity<AgendamentoResponse> agendamentoResponse;
	private static AgendamentoRequest agendamentoRequest;


	@Order(1)
	@Test
	public void deveAdicionarUmPet() {
		petRequest = new PetRequest("Pet_teste", 10, "Teste",
				Porte.MEDIO, "Testador", SexoAnimal.MASCULINO);

		petResponse = restTemplate.postForEntity(
				"http://localhost:" + port + "/api/v1/pet", petRequest, PetResponse.class);

		assertEquals(petResponse.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(petResponse.getBody().getId());
		assertEquals(petResponse.getBody().getEspecie(), "Testador");
		assertEquals(petResponse.getBody().getRaca(), "Teste");
	}

	@Order(2)
	@Test
	public void deveEditarUmPet() {

		petRequest.setNome("Pet_teste_editado");

		petResponse = editarEntidade(
				PetResponse.class, "pet", petRequest, petResponse.getBody().getId());

		assertEquals(petResponse.getStatusCode(), HttpStatus.OK);
		assertEquals(petResponse.getBody().getNome(), "Pet_teste_editado");
	}

	@Order(3)
	@Test
	public void deveObterTodosOsPets() {
		List<PetResponse> response = obterEntidade(List.class, "pet", "").getBody();
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Order(4)
	@Test
	public void deveObterUmPetEspecifico() {
		ResponseEntity<PetResponse> response = obterEntidade(
				PetResponse.class, "pet", "/"+petResponse.getBody().getId());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertNotNull(response.getBody().getId());
		assertEquals(response.getBody().getId(), petResponse.getBody().getId());
		assertEquals(response.getBody().getRaca(), "Teste");
	}

	@Order(5)
	@Test
	public void deveAdicionarUmTutor() {

		tutorRequest = new TutorRequest(
				"TutorTest", "Rua do teste, 999",
				"(81)95642-5813", "tutorTest@gmail.com", "456466", petResponse.getBody().getId()
		);

		tutorResponse = restTemplate.postForEntity(
				"http://localhost:" + port + "/api/v1/tutor", tutorRequest, TutorResponse.class);

		assertEquals(tutorResponse.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(tutorResponse.getBody().getId());
		assertEquals(tutorResponse.getBody().getNome(), "TutorTest");
		assertEquals(tutorResponse.getBody().getEmail(), "tutorTest@gmail.com");
	}

	@Order(6)
	@Test
	public void deveEditarUmTutor() {

		tutorRequest.setNome("TutorTest_editado");

		tutorResponse = editarEntidade(TutorResponse.class, "tutor", tutorRequest, tutorResponse.getBody().getId());

		assertEquals(tutorResponse.getStatusCode(), HttpStatus.OK);
		assertEquals(tutorResponse.getBody().getNome(), "TutorTest_editado");
	}

	@Order(7)
	@Test
	public void deveObterTodosOsTutores() {
		List<TutorResponse> response = obterEntidade(List.class, "tutor", "").getBody();
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Order(8)
	@Test
	public void deveObterUmTutorEspecifico() {
		ResponseEntity<TutorResponse> response = obterEntidade(
				TutorResponse.class, "tutor", "/"+tutorResponse.getBody().getId());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getId(), tutorResponse.getBody().getId());
		assertEquals(response.getBody().getEmail(), "tutorTest@gmail.com");
	}

	@Order(9)
	@Test
	public void deveCriarUmAgendamento() {

		agendamentoRequest = new AgendamentoRequest(
				tutorResponse.getBody().getId(), Arrays.asList(Procedimento.CASTRACAO, Procedimento.VACINACAO),
				StatusAgendamento.CONFIRMADO);

		agendamentoResponse = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/agendamento",
										agendamentoRequest, AgendamentoResponse.class);

		assertEquals(agendamentoResponse.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(agendamentoResponse.getBody().getId());
		assertEquals(agendamentoResponse.getBody().getTutor().getId(), tutorResponse.getBody().getId());
		assertTrue(agendamentoResponse.getBody().getProcedimentos().contains(Procedimento.CASTRACAO));
		assertEquals(agendamentoResponse.getBody().getStatus(), StatusAgendamento.CONFIRMADO);
	}

	@Order(10)
	@Test
	public void deveEditarUmAgendamento() {

		agendamentoRequest.setStatus(StatusAgendamento.ATENDIDO);

		agendamentoResponse = editarEntidade(AgendamentoResponse.class,
				"agendamento", agendamentoRequest, agendamentoResponse.getBody().getId());

		assertEquals(agendamentoResponse.getStatusCode(), HttpStatus.OK);
		assertNotNull(agendamentoResponse.getBody().getId());
		assertEquals(agendamentoResponse.getBody().getStatus(), StatusAgendamento.ATENDIDO);
	}

	@Order(11)
	@Test
	public void deveObterTodosOsAgendamentos() {
		List<AgendamentoResponse> response = obterEntidade(List.class, "agendamento", "").getBody();
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Order(12)
	@Test
	public void deveObterUmAgendamentoEspecifico() {
		ResponseEntity<AgendamentoResponse> response = obterEntidade(
				AgendamentoResponse.class, "agendamento", "/"+agendamentoResponse.getBody().getId());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getId(), agendamentoResponse.getBody().getId());
		assertEquals(response.getBody().getStatus(), agendamentoResponse.getBody().getStatus());
	}


	@Order(13)
	@Test
	public void deveExcluirUmAgendamento() {
		restTemplate.delete("http://localhost:" + port + "/api/v1/agendamento/{id}", agendamentoResponse.getBody().getId());

		agendamentoResponse = obterEntidade(
				AgendamentoResponse.class, "agendamento", "/"+agendamentoResponse.getBody().getId());

		assertEquals(agendamentoResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertNull(agendamentoResponse.getBody().getId());
	}

	@Order(14)
	@Test
	public void deveExcluirUmTutor() {
		restTemplate.delete("http://localhost:" + port + "/api/v1/tutor/{id}", tutorResponse.getBody().getId());

		tutorResponse = obterEntidade(
				TutorResponse.class, "tutor", "/"+tutorResponse.getBody().getId());

		assertEquals(tutorResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertNull(tutorResponse.getBody().getId());
	}

	@Order(15)
	@Test
	public void deveExcluirUmPet() {
		restTemplate.delete("http://localhost:" + port + "/api/v1/pet/{id}", petResponse.getBody().getId());

		petResponse = obterEntidade(
				PetResponse.class, "pet", "/"+petResponse.getBody().getId());

		assertEquals(petResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertNull(petResponse.getBody().getId());
	}


	private <T> ResponseEntity<T> obterEntidade(Class<T> classe, String endPoint, String parametro) {
		return restTemplate.getForEntity("http://localhost:" + port + "/api/v1/"+endPoint + parametro, classe);
	}

	private <T,U> ResponseEntity<T> editarEntidade(Class<T> classeResponse, String endPoint, U request, Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return restTemplate.exchange("http://localhost:" + port + "/api/v1/"+ endPoint +"/{id}",
						HttpMethod.PUT, new HttpEntity<U>(request, headers), classeResponse, id);
	}

}
