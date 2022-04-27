package org.gs;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/dogs")
public class DogsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<DogsEntity> dogs = DogsEntity.listAll();
        return Response.ok(dogs).build();
}
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
       return DogsEntity.findByIdOptional(id)
                .map(dogs -> Response.ok(dogs).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

}
    @GET
    @Path("breed/{breed}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByBreed(@PathParam("breed") String breed){
        return DogsEntity.find("breed", breed)
                .singleResultOptional()
                .map(dogs -> Response.ok(dogs).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

    }
    @GET
    @Path("year/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByYear(@PathParam("year") int year){
       return DogsEntity.find("year", year)
                .singleResultOptional()
                .map(dogs -> Response.ok(dogs).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

    }
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(DogsEntity dogs){

        DogsEntity.persist(dogs);
        if(dogs.isPersistent()){
            return Response.created(URI.create("/dogs"+ dogs.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Path("delete/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id){
        boolean deleted = DogsEntity.deleteById(id);
        if(deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}