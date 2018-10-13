package local.dubrovin.resources;

import local.dubrovin.models.Contact;
import local.dubrovin.services.ContactService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ContactResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getContacts(@PathParam("bookId") Integer bookId) {
        ContactService service = new ContactService();
        return service.findAll(bookId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{contactId}")
    public Response getContact(@PathParam("bookId") Integer bookId, @PathParam("contactId") Integer contactId) {
        ContactService service = new ContactService();
        Contact contact = service.findById(bookId, contactId);
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("").build();
        }

        return Response.ok(contact).build();
    }
}
