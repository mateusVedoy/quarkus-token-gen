package token.gen.application.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import token.gen.application.dto.TokenDTO;
import token.gen.application.response.APIResponse;
import token.gen.application.useCase.BuildTokenJWT;

@Path("/token")
public class TokenController {

    @Inject
    BuildTokenJWT buildTokenJWT;

    @POST
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(TokenDTO dto, @Context UriInfo context) {
        String requester = context.getRequestUri().getHost();
        dto.addHost(requester);
        APIResponse response = buildTokenJWT.build(dto);
        return Response.status(response.getStatus()).entity(response).build();
    }

}
