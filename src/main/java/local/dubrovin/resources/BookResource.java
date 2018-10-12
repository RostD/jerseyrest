package local.dubrovin.resources;

import local.dubrovin.models.Book;
import local.dubrovin.services.BookService;
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

@Path("/books")
public class BookResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        BookService service = new BookService();
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType(@PathParam("id") Integer id) {
        BookService service = new BookService();
        Book book = service.find(id);

        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Not found\"}")
                    .build();
        }

        return Response.ok(book).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createType(Book book, @Context UriInfo uriInfo) {

        if (book == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Data is can't be empty\"}")
                    .build();
        }

        Validator validator = ValidatorFactoryUtil.getValidator();
        Set<ConstraintViolation<Book>> constraintViolationSet = validator.validate(book);

        if (constraintViolationSet.size() > 0) {
            return Response.status(422)
                    .entity("{\"message\":\"" + constraintViolationSet.iterator().next().getMessage() + "\"}")
                    .build();
        }

        BookService service = new BookService();
        service.save(book);

        String newId = String.valueOf(book.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(book)
                .build();

    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateType(@PathParam("id") Integer id, Book book) {

        BookService service = new BookService();
        Book oldBook = service.find(id);

        if (oldBook == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Not found\"}")
                    .build();
        }

        if (book == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        book.setId(id);
        if (book.equals(oldBook)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        Validator validator = ValidatorFactoryUtil.getValidator();
        Set<ConstraintViolation<Book>> constraintViolationSet = validator.validate(book);

        if (constraintViolationSet.size() > 0) {
            return Response.status(422)
                    .entity("{\"message\":\"" + constraintViolationSet.iterator().next().getMessage() + "\"}")
                    .build();
        }

        service.update(book);

        return Response.ok(book).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteType(@PathParam("id") Integer id) {
        BookService service = new BookService();
        Book book = service.find(id);

        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Not found\"}")
                    .build();
        }

        service.delete(book);
        return Response.ok().build();
    }
}
