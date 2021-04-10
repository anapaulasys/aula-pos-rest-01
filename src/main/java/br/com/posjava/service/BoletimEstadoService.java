package br.com.posjava.service;

import br.com.posjava.resource.dto.BoletimDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@RegisterRestClient
public interface BoletimEstadoService {

    @GET
    @Path("/estado/boletim")
    BoletimDTO getBoletins();

    @POST
    @Path("/estado/boletim")
    void comunicarBoletins(@QueryParam("positivos") Long positivos);
}
