package sn.ept.galsenshop.restservices;


import io.swagger.v3.oas.annotations.tags.Tag;
import sn.ept.galsenshop.entities.Categorie;
import sn.ept.galsenshop.repositories.CategorieFacade;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("categories")
@Tag(name = "Categorie", description = "Articles resources")
public class CategorieResource {

    @EJB
    private CategorieFacade categorieFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response listCategories() {
        return Response.ok().entity(categorieFacade.findAll()).build();
    }


    @GET
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response retrieveCategorie(@PathParam("code") String code) {

        if(categorieFacade.find(code)==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{

            return Response.ok(categorieFacade.find(code)).build();

        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addCategorie(Categorie categorie) {

        if(Objects.isNull(categorie)){
           return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            System.out.println("creating Categorie "+categorie.getCode_categorie());
            categorieFacade.create(categorie);
            return Response.status(Response.Status.CREATED).entity(categorie).build();
        }
    }


    @DELETE
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response deleteCategorie(@PathParam("code") String code) {
        if(categorieFacade.find(code) == null){
           return Response.status(Response.Status.NOT_FOUND).build();
        }else{
           System.out.println("Deleting Categorie "+code);
           Categorie categorie = categorieFacade.find(code);
           categorieFacade.remove(categorie);
           return Response.ok(categorie).status(Response.Status.NO_CONTENT).build();
        }
    }

    @PUT
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateCategorie(@PathParam("code") String code, Categorie categorie) {

        if (categorieFacade.find(code) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(categorie)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        else{
            categorieFacade.edit(categorie);
            return Response.ok().entity(categorie).build();
        }

    }

    @PATCH
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response patchCategorie(@PathParam("code") String code, Categorie categorie) {

        if (categorieFacade.find(code) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(categorie)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }else{
            categorieFacade.edit(categorie);
            return Response.ok().entity(categorie).build();
        }
    }

    @GET
    @Path("/paginate")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findRange(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size") @DefaultValue("3") int size) {
        return Response.ok().entity(categorieFacade.findRange(page,size)).build();
    }

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response searchCategory(@QueryParam("searchText") String searchText) {
        return Response.ok().entity(categorieFacade.searchCategory(searchText)).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(categorieFacade.count()).build();
    }

}
