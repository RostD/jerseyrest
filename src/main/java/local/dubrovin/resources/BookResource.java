package local.dubrovin.resources;

import local.dubrovin.dao.NotFoundException;
import local.dubrovin.dao.ValidateException;
import local.dubrovin.models.Book;
import local.dubrovin.services.BookService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/books")
public class BookResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        BookService service = new BookService();
        try {
            List<Book> list = service.findAll();
            return Response.ok(list).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("bookId") Integer id) {
        BookService service = new BookService();

        try {
            Book book = service.find(id);
            return Response.ok(book).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book, @Context UriInfo uriInfo) {

        if (book == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Data is can't be empty\"}")
                    .build();
        }

        BookService service = new BookService();
        try {
            service.save(book);
            String newId = String.valueOf(book.getId());
            URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
            return Response.created(uri)
                    .entity(book)
                    .build();
        } catch (ValidateException e) {
            return Response.status(422)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("bookId") Integer id, Book book) {

        if (book == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        BookService service = new BookService();
        try {
            service.update(id, book);
        } catch (ValidateException e) {
            return Response.status(422)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }

        return Response.ok(book).build();
    }

    @DELETE
    @Path("/{bookId}")
    public Response deleteBook(@PathParam("bookId") Integer id) {
        BookService service = new BookService();
        try {
            service.delete(id);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @Path("/{bookId}/contacts")
    public ContactResource getContacts() {
        return new ContactResource();
    }
}
