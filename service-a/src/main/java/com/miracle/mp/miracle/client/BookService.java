package com.miracle.mp.miracle.client;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Stateless
public class BookService {

    @RestClient
    @Inject
    private BookClient bookClient;

    public List<Book> all() {
        return bookClient.all();
    }
}