package local.dubrovin.resources;

import local.dubrovin.dao.NotFoundException;
import local.dubrovin.dao.ValidateException;
import local.dubrovin.models.EmailType;
import local.dubrovin.services.EmailTypeService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/email-types")
public class EmailTypeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmailType> getTypes() {
        EmailTypeService service = new EmailTypeService();
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType(@PathParam("id") Integer id) {
        EmailTypeService service = new EmailTypeService();
        try {
            EmailType type = service.find(id);
            return Response.ok(type).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createType(EmailType type, @Context UriInfo uriInfo) {

        if (type == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Data is can't be empty\"}")
                    .build();
        }

        EmailTypeService service = new EmailTypeService();
        try {
            service.save(type);
            String newId = String.valueOf(type.getId());
            URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
            return Response.created(uri)
                    .entity(type)
                    .build();
        } catch (ValidateException e) {
            return Response.status(422)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }


    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateType(@PathParam("id") Integer id, EmailType type) {

        if (type == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        EmailTypeService service = new EmailTypeService();
        try {
            service.update(id, type);
            return Response.ok(type).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (ValidateException e) {
            return Response.status(422)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }

    }

    @DELETE
    @Path("/{id}")
    public Response deleteType(@PathParam("id") Integer id) {
        EmailTypeService service = new EmailTypeService();
        try {
            service.delete(id);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
