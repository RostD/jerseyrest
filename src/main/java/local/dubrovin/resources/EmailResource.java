package local.dubrovin.resources;

import local.dubrovin.dao.NotFoundException;
import local.dubrovin.dao.ValidateException;
import local.dubrovin.models.Email;
import local.dubrovin.services.EmailService;

import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class EmailResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{emailId}")
    public Response getEmail(@PathParam("contactId") Integer contactId, @PathParam("bookId") Integer bookId, @PathParam("emailId") Integer emailId) {
        EmailService service = new EmailService(bookId, contactId);

        try {
            Email email = service.findById(emailId);
            return Response.ok(email).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmails(@PathParam("contactId") Integer contactId, @PathParam("bookId") Integer bookId) {
        EmailService service = new EmailService(bookId, contactId);
        try {
            List<Email> list = service.findAll();
            return Response.ok(list).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmail(@PathParam("contactId") Integer contactId, @PathParam("bookId") Integer bookId, Email email) {
        if (email == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Data is can't be empty\"}")
                    .build();
        }

        EmailService service = new EmailService(bookId, contactId);

        try {
            service.save(email);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }

        return Response.ok().entity(email).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{emailId}")
    public Response updateEmail(@PathParam("contactId") Integer contactId, @PathParam("bookId") Integer bookId, @PathParam("emailId") Integer emailId, Email email) {
        if (email == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Data is can't be empty\"}")
                    .build();
        }

        EmailService service = new EmailService(bookId, contactId);

        try {
            service.update(emailId, email);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (ValidateException e) {
            return Response.status(422)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }

        return Response.ok().entity(email).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{emailId}")
    public Response createEmail(@PathParam("contactId") Integer contactId, @PathParam("bookId") Integer bookId, @PathParam("emailId") Integer emailId) {

        EmailService service = new EmailService(bookId, contactId);

        try {
            service.delete(emailId);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }

        return Response.ok("").build();
    }
}
