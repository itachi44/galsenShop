package sn.ept.galsenshop.restservices;

import io.swagger.v3.oas.annotations.tags.Tag;
import sn.ept.galsenshop.entities.Client;
import sn.ept.galsenshop.repositories.ClientFacade;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("clients")
@Tag(name = "Client", description = "Articles resources")
public class ClientResource {

    @EJB
    private ClientFacade clientFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response listClient() {

        return Response.ok().entity(clientFacade.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response retrieveArticle(@PathParam("id") Integer id) {

        if(clientFacade.find(id)==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(clientFacade.find(id)).build();
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addArticle(Client client) {
        if(Objects.isNull(client)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            System.out.println("creating article "+client.getId());
            clientFacade.create(client);
            return Response.status(Response.Status.CREATED).entity(client).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response deleteArticle(@PathParam("id") Integer id) {
        if(clientFacade.find(id) == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            System.out.println("Deleting Client "+id);
            Client client = clientFacade.find(id);
            clientFacade.remove(client);
            return Response.ok(client).status(Response.Status.NO_CONTENT).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateArticle(@PathParam("id") Integer id, Client client) {

        if (clientFacade.find(id) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(client)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        else{
            clientFacade.edit(client);
            return Response.ok().entity(client).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response patchArticle(@PathParam("id") Integer id, Client client) {

        if (clientFacade.find(id) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(client)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        else{
            clientFacade.edit(client);
            return Response.ok().entity(client).build();
        }
    }

    @GET
    @Path("/paginate")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findRange(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size") @DefaultValue("3") int size) {
        return Response.ok().entity(clientFacade.findRange(page,size)).build();
    }

    @GET
    @Path("/searchByPhone")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findClientByPhoneNumber(@QueryParam("searchText") String searchText) {
        return Response.ok().entity(clientFacade.findClientByPhoneNumber(searchText)).build();
    }

    @GET
    @Path("/searchByName")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findClientByName(@QueryParam("searchText") String searchText,@QueryParam("page") @DefaultValue("0") int page,@QueryParam("size") @DefaultValue("3") int size) {
        return Response.ok().entity(clientFacade.findClientByName(searchText, page, size)).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(clientFacade.count()).build();
    }
}
