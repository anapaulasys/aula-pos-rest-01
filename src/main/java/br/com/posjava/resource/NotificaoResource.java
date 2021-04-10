package br.com.posjava.resource;

import br.com.posjava.model.Notificacao;
import br.com.posjava.model.Paciente;
import br.com.posjava.repository.NotificacaoRepository;
import br.com.posjava.repository.PacienteRepository;
import io.quarkus.panache.common.Page;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("api/notificacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificaoResource {

    @Inject
    NotificacaoRepository repository;
    @Inject
    PacienteRepository pacienteRepository;

    @POST
    @Transactional
    public Response create(Notificacao notificacao) {

        Optional<Paciente> first = pacienteRepository
                .findAll()
                .stream()
                .filter(paciente -> paciente.getNome().equals(notificacao.getPaciente().getNome()))
                .findFirst();

        if (first.isPresent()) {
            notificacao.setPaciente(first.get());
        } else {
            notificacao.setPaciente(pacienteRepository.getEntityManager().merge(notificacao.getPaciente()));
        }

        repository.persist(notificacao);

        return Response.ok(notificacao).status(Response.Status.CREATED).build();
    }

    @PUT
    public Response update(Notificacao notificacao) {
        repository.update("positivo = ?1, unidadeSaude = ?2", notificacao.getPositivo(), notificacao.getUnidadeSaude());
        return Response.ok(notificacao).status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAll(@QueryParam("page") @DefaultValue("0") int pageIndex,
                           @QueryParam("size") @DefaultValue("10") int pageSize,
                           @QueryParam("filter") @DefaultValue("") String filter) {
        List<Notificacao> lista = repository.find("lower(paciente.nome) like ?1", "%" + filter.toLowerCase().trim() + "%")
                .page(Page.of(pageIndex, pageSize))
                .list();

        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") UUID id) {
        return Response.ok(repository.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") UUID id) {
        return Response.ok(repository.deleteById(id)).status(Response.Status.NO_CONTENT).build();
    }

}
