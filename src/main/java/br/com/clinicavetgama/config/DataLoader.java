package br.com.clinicavetgama.config;

import br.com.clinicavetgama.dto.request.AgendamentoRequest;
import br.com.clinicavetgama.dto.request.PetRequest;
import br.com.clinicavetgama.dto.request.TutorRequest;
import br.com.clinicavetgama.dto.response.PetResponse;
import br.com.clinicavetgama.dto.response.TutorResponse;
import br.com.clinicavetgama.enums.Porte;
import br.com.clinicavetgama.enums.Procedimento;
import br.com.clinicavetgama.enums.SexoAnimal;
import br.com.clinicavetgama.enums.StatusAgendamento;
import br.com.clinicavetgama.repository.PetRepository;
import br.com.clinicavetgama.repository.TutorRepository;
import br.com.clinicavetgama.services.AgendamentoService;
import br.com.clinicavetgama.services.PetService;
import br.com.clinicavetgama.services.TutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader {

    @Autowired
    PetRepository petRepository;
    @Autowired
    PetService petService;
    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    TutorService tutorService;
    @Autowired
    AgendamentoService agendamentoService;


    @Bean
    CommandLineRunner baseLoad() {

        return args -> {

            if (petRepository.count() == 0) {

                PetRequest p1 = new PetRequest("Fifi", 3, "Siamês",
                        Porte.MEDIO, "Felino", SexoAnimal.FEMININO);
                PetRequest p2 = new PetRequest("Bidu", 2, "Doberman",
                        Porte.GRANDE, "Canino", SexoAnimal.MASCULINO);
                PetRequest p3 = new PetRequest("Louro José", 10, "Papagaio-verdadeiro",
                        Porte.PEQUENO, "Amazona aestiva", SexoAnimal.MASCULINO);

                PetResponse pr1 = petService.adicionarPet(p1);
                PetResponse pr2 = petService.adicionarPet(p2);
                PetResponse pr3 = petService.adicionarPet(p3);


                if (tutorRepository.count() == 0) {

                    TutorRequest t1 = new TutorRequest("Henrique", "Rua do Java, 123",
                            "(81)98542-3654", "henrique@gmail.com", "123456", pr1.getId());
                    TutorRequest t2 = new TutorRequest("Renato", "Rua do Python, 234",
                            "(21)95784-2365", "renato@gmail.com", "234567", pr2.getId());
                    TutorRequest t3 = new TutorRequest("Wesley", "Rua do PHP, 345",
                            "(21)94875-6754", "wesley@gmail.com", "345678", pr3.getId());

                    TutorResponse tr1 = tutorService.adicionarTutor(t1);
                    TutorResponse tr2 = tutorService.adicionarTutor(t2);
                    TutorResponse tr3 = tutorService.adicionarTutor(t3);

                    AgendamentoRequest a1 = new AgendamentoRequest(tr1.getId(),
                            Arrays.asList(Procedimento.values()), StatusAgendamento.CONFIRMADO);
                    AgendamentoRequest a2 = new AgendamentoRequest(tr2.getId(),
                            Arrays.asList(Procedimento.values()), StatusAgendamento.ATENDIDO);
                    AgendamentoRequest a3 = new AgendamentoRequest(tr3.getId(),
                            Arrays.asList(Procedimento.values()), StatusAgendamento.FALTOU);

                    Arrays.asList(a1, a2, a3).forEach(agendamentoService::criarAgendamento);
                }

            }

        };

    }

}
