package br.com.posjava.resource;

import br.com.posjava.repository.NotificacaoRepository;
import br.com.posjava.resource.dto.BoletimDTO;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

//Anotações do resteasy
//@Path("/api/boletim")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)

//Anotaçoes com spring-web
@RestController
@RequestMapping("/api/boletim")
public class BoletimResource {

    @Inject
    NotificacaoRepository repository;

//    @GET
    @GetMapping
    public Response getTotal() {
        List<BoletimDTO> todosOsDados = Lists.newArrayList();

        for (Long i = 15L; i <= 100; i += 10) {
            long positivos = repository.count("positivo = true and paciente.idade between ?1 and ?2 ", i, i + 10);
            long negativos = repository.count("positivo = false and paciente.idade between ?1 and ?2 ", i, i + 10);

            BoletimDTO boletimLocal = new BoletimDTO(positivos, negativos);
            boletimLocal.setAgrupador("Fixa Etaria de " + i + " até " + (i + 10));
            todosOsDados.add(boletimLocal);

        }

        return Response.ok(todosOsDados).build();
    }

}
