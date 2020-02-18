package com.miracle.mp.miracle.client;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("books")
public class BookResources {

    @Inject
    private BookService bookService;

    @GET
    public List<Book> all() {
        return bookService.all();
    }
}
