package local.dubrovin.resources;

import local.dubrovin.models.EmailType;
import local.dubrovin.services.EmailTypeService;
import local.dubrovin.utils.ValidatorFactoryUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Set;

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
        EmailType type = service.find(id);

        if (type == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Not found\"}")
                    .build();
        }

        return Response.ok(type).build();
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

        Validator validator = ValidatorFactoryUtil.getValidator();
        Set<ConstraintViolation<EmailType>> constraintViolationSet = validator.validate(type);

        if (constraintViolationSet.size() > 0) {
            return Response.status(422)
                    .entity("{\"message\":\"" + constraintViolationSet.iterator().next().getMessage() + "\"}")
                    .build();
        }

        EmailTypeService service = new EmailTypeService();
        service.save(type);

        String newId = String.valueOf(type.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(type)
                .build();

    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateType(@PathParam("id") Integer id, EmailType type) {

        EmailTypeService service = new EmailTypeService();
        EmailType oldType = service.find(id);

        if (oldType == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Not found\"}")
                    .build();
        }

        if (type == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        type.setId(id);
        if (type.equals(oldType)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        Validator validator = ValidatorFactoryUtil.getValidator();
        Set<ConstraintViolation<EmailType>> constraintViolationSet = validator.validate(type);

        if (constraintViolationSet.size() > 0) {
            return Response.status(422)
                    .entity("{\"message\":\"" + constraintViolationSet.iterator().next().getMessage() + "\"}")
                    .build();
        }

        service.update(type);

        return Response.ok(type).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteType(@PathParam("id") Integer id) {
        EmailTypeService service = new EmailTypeService();
        EmailType type = service.find(id);

        if (type == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Not found\"}")
                    .build();
        }

        service.delete(type);
        return Response.ok().build();
    }
}
