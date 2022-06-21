package sn.ept.galsenshop.restservices;

import io.swagger.v3.oas.annotations.tags.Tag;
import sn.ept.galsenshop.entities.LigneArticle;
import sn.ept.galsenshop.repositories.LigneArticleFacade;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("ligneArticles")
@Tag(name = "LigneArticle", description = "Articles resources")
public class LigneArticleResource {

    @EJB
    LigneArticleFacade ligneArticleFacade;


    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response listLigneArticle() {
        return Response.ok().entity(ligneArticleFacade.findAll()).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addLigneArticle(LigneArticle la) {

        if(Objects.isNull(la)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            System.out.println("creating ligneArticle for "+la.getArticle().getCode_article());
            ligneArticleFacade.create(la);
            return Response.status(Response.Status.CREATED).entity(la).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findLigneArticle(@PathParam("id") Integer id) {

        if(ligneArticleFacade.find(id)==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(ligneArticleFacade.find(id)).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response deleteLigneArticle(@PathParam("id") Integer id) {

        if(ligneArticleFacade.find(id) == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            System.out.println("Deleting ligneArticle "+id);
            LigneArticle la = ligneArticleFacade.find(id);
            ligneArticleFacade.remove(la);
            return Response.ok(la).status(Response.Status.NO_CONTENT).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateArticle(@PathParam("id") Integer id, LigneArticle la) {

        if (ligneArticleFacade.find(id) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(la)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        else{
            ligneArticleFacade.edit(la);
            return Response.ok().entity(la).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response patchArticle(@PathParam("id") Integer id, LigneArticle la) {

        if (ligneArticleFacade.find(id) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(la)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        else{
            ligneArticleFacade.edit(la);
            return Response.ok().entity(la).build();
        }
    }

    @GET
    @Path("/paginate")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findRange(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size") @DefaultValue("3") int size) {
        return Response.ok().entity(ligneArticleFacade.findRange(page,size)).build();
    }

    @GET
    @Path("/quantityPerArticle")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response salesQuantityPerArticle(@QueryParam("startDate") String startDate,@QueryParam("endDate") String endDate) {
        return Response.ok().entity(ligneArticleFacade.salesQuantityPerArticle(startDate, endDate)).build();
    }

    @GET
    @Path("/stats")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getStockStats() {
        System.out.println(ligneArticleFacade.getStockStats().get(0).getJours_restants());
        return Response.ok(ligneArticleFacade.getStockStats()).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(ligneArticleFacade.count()).build();
    }
}
