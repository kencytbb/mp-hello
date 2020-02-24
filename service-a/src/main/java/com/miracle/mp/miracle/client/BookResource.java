package com.miracle.mp.miracle.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.RestClientDefinitionException;

@Stateless
@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookService bookService;

    @GET
    @Path("all-books-by-mp-rest-client")
    public List<Book> allByCDI() {
        return bookService.all();
    }

    @GET
    @Path("all-books-by-mp-rest-client-builder")
    public List<Book> allByRestClientBuilder()
            throws IllegalStateException, RestClientDefinitionException, URISyntaxException {
        BookClient bookClient = RestClientBuilder.newBuilder()
                                    .baseUri(new URI("http://localhost:9081/data/books"))
                                    .build(BookClient.class);
        return bookClient.all();
    }
    
}
