package br.com.posjava.resource;

import br.com.posjava.model.Paciente;
import br.com.posjava.repository.PacienteRepository;
import io.quarkus.panache.common.Page;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/api/paciente")
public class PacienteResource {

    @Inject
    PacienteRepository repository;

    @POST
    public Response create(Paciente paciente) {
        repository.persist(paciente);
        return Response.ok(paciente).status(Response.Status.CREATED).build();
    }

    @PUT
    public Response update(Paciente paciente) {
        repository.update("nome = ?1", paciente.getNome());
        return Response.ok(paciente).status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAll(@QueryParam("page") @DefaultValue("0") int pageIndex,
                           @QueryParam("size") @DefaultValue("10") int pageSize,
                           @QueryParam("filter") @DefaultValue("") String filter) {

        List<Paciente> lista = repository
                .find("lower(nome) like ?1", "%" + filter.toLowerCase().trim() + "%")
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
