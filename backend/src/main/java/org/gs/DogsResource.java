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
    @Path("age/{age}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByYear(@PathParam("age") int age){
      return DogsEntity.find("age", age)
                .singleResultOptional()
                .map(dogs -> Response.ok(dogs).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());



    }

    @POST
    @Path("post")
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
    @PUT
    @Path("update/{id}")
    @Transactional
    public DogsEntity update( Long id, DogsEntity dogs) {
        DogsEntity entity = DogsEntity.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.name = dogs.name;
        entity.age = dogs.age;
        entity.color = dogs.color;

        return entity;
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