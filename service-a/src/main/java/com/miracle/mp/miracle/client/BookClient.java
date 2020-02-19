package com.miracle.mp.miracle.client;

import java.util.List;

import javax.ws.rs.GET;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface BookClient {

    @GET
    public List<Book> all();
    
}