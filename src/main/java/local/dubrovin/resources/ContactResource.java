package local.dubrovin.resources;

import local.dubrovin.dao.NotFoundException;
import local.dubrovin.dao.ValidateException;
import local.dubrovin.models.Contact;
import local.dubrovin.services.ContactService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

public class ContactResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContacts(@PathParam("bookId") Integer bookId) {
        ContactService service = new ContactService(bookId);
        try {
            List<Contact> list = service.findAll();
            return Response.ok(list).build();
        } catch (NotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{contactId}")
    public Response getContact(@PathParam("bookId") Integer bookId, @PathParam("contactId") Integer contactId) {
        ContactService service = new ContactService(bookId);
        try {
            Contact contact = service.findById(contactId);
            return Response.ok(contact).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createContact(@PathParam("bookId") Integer bookId, Contact contact, @Context UriInfo uriInfo) {
        if (contact == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Data is can't be empty\"}")
                    .build();
        }

        ContactService service = new ContactService(bookId);
        try {
            service.save(contact);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"" + e.getMessage() + "\"}").build();
        } catch (ValidateException e) {
            return Response.status(422)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }

        String newId = String.valueOf(contact.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();

        return Response.created(uri)
                .entity(contact)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{contactId}")
    public Response updateContact(@PathParam("bookId") Integer bookId, @PathParam("contactId") Integer contactId, Contact contact) {
        if (contact == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Data is can't be empty\"}")
                    .build();
        }

        ContactService service = new ContactService(bookId);
        try {
            service.update(contactId, contact);
            return Response.ok(contact).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"" + e.getMessage() + "\"}").build();
        } catch (ValidateException e) {
            return Response.status(422)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{contactId}")
    public Response deleteContact(@PathParam("bookId") Integer bookId, @PathParam("contactId") Integer contactId) {
        ContactService service = new ContactService(bookId);
        try {
            service.delete(contactId);
            return Response.ok("").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @Path("/{contactId}/emails")
    public EmailResource getEmails() {
        return new EmailResource();
    }
}
