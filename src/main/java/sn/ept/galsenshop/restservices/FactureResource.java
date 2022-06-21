package sn.ept.galsenshop.restservices;

import io.swagger.v3.oas.annotations.tags.Tag;
import sn.ept.galsenshop.entities.Facture;
import sn.ept.galsenshop.repositories.FactureFacade;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.Objects;

@Path("factures")
@Tag(name = "Facture", description = "Articles resources")
public class FactureResource {

    @EJB
    private FactureFacade factureFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response listFacture() {

        return Response.ok().entity(factureFacade.findAll()).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addFacture(Facture facture) {

        if(Objects.isNull(facture)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
           System.out.println("creating facture "+facture.getNumero());
           factureFacade.create(facture);
           return Response.status(Response.Status.CREATED).entity(facture).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findFacture(@PathParam("id") Integer id) {

        if(factureFacade.find(id)==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(factureFacade.find(id)).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response deleteFacture(@PathParam("id") Integer id) {

        if(factureFacade.find(id) == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            System.out.println("Deleting facture "+id);
            Facture facture = factureFacade.find(id);
            factureFacade.remove(facture);
        return Response.ok(facture).status(Response.Status.NO_CONTENT).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateArticle(@PathParam("id") Integer id, Facture facture) {

        if (factureFacade.find(id) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(facture)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        else{
            factureFacade.edit(facture);
            return Response.ok().entity(facture).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response patchArticle(@PathParam("id") Integer id, Facture facture) {

        if (factureFacade.find(id) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(facture)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        else{
            factureFacade.edit(facture);
            return Response.ok().entity(facture).build();
        }
    }

    @GET
    @Path("/paginate")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findRange(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size") @DefaultValue("3") int size) {
        return Response.ok().entity(factureFacade.findRange(page,size)).build();
    }

    @GET
    @Path("/searchClientFacture")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response searchClientFacture(@QueryParam("searchText") String searchText) {
        return Response.ok().entity(factureFacade.searchClientFacture(searchText)).build();
    }

    @GET
    @Path("/searchBetween")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response searchFactureBetweenDate(@QueryParam("startDate") String startDate,@QueryParam("endDate") String endDate,@QueryParam("page") @DefaultValue("0") int page,@QueryParam("size") @DefaultValue("3") int size) throws ParseException {
        return Response.ok().entity(factureFacade.searchFactureBetweenDate(startDate, endDate, page, size)).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(factureFacade.count()).build();
    }
}
