package com.miracle.mp.miracle.client;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("books")
public class BookResource {

    @Inject
    private BookService bookService;

    @GET
    @Path("all-books-by-mp-rest-client")
    public List<Book> all() {
        return bookService.all();
    }
    
}