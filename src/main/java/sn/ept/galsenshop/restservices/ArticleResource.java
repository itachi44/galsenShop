package sn.ept.galsenshop.restservices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import sn.ept.galsenshop.entities.Article;
import sn.ept.galsenshop.repositories.ArticleFacade;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;


@Path("articles")
@Tag(name = "Article", description = "Articles resources")
public class ArticleResource {


    @EJB
    private ArticleFacade articleFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(description = "récupérer tous les articles")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Success")
    })
    public Response listArticle() {

        return Response.ok().entity(articleFacade.findAll()).build();
    }


    @GET
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response retrieveCategorie(@PathParam("code") String code) {

        if(articleFacade.find(code)==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{

            return Response.ok(articleFacade.find(code)).build();

        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addArticle(Article article) {
        if(Objects.isNull(article)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            System.out.println("creating article "+article.getCode_article());
            articleFacade.create(article);
            return Response.status(Response.Status.CREATED).entity(article).build();
        }
    }


    @DELETE
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response deleteArticle(@PathParam("code") String code) {
        if(articleFacade.find(code) == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            System.out.println("Deleting Categorie "+code);
            Article article = articleFacade.find(code);
            articleFacade.remove(article);
            return Response.ok(article).status(Response.Status.NO_CONTENT).build();
        }
    }

    @PUT
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateCategorie(@PathParam("code") String code, Article article) {

        if (articleFacade.find(code) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(article)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        else{
            articleFacade.edit(article);
            return Response.ok().entity(article).build();
        }

    }

    @PATCH
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response patchCategorie(@PathParam("code") String code, Article article) {
        if (articleFacade.find(code) == null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }else if(Objects.isNull(article)){
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }else{
            articleFacade.edit(article);
            return Response.ok().entity(article).build();
        }
    }

    @GET
    @Path("/paginate")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response findRange(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size") @DefaultValue("3") int size) {
        return Response.ok().entity(articleFacade.findRange(page,size)).build();
    }

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response searchArticle(@QueryParam("searchText") String searchText,@QueryParam("page") @DefaultValue("0") int page,@QueryParam("size") @DefaultValue("3") int size) {
        return Response.ok().entity(articleFacade.findArticle(searchText, page, size)).build();

    }

    @GET
    @Path("/count")
    public Response count() {

        return Response.ok(articleFacade.count()).build();
    }
}
