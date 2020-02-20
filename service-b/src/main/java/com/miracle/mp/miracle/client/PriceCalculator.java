package com.miracle.mp.miracle.client;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@RequestScoped
public class PriceCalculator {

    private WebTarget bookStorePriceTarget;
    private Double discount = 1.5;

    @PostConstruct
    public void setUp() {
        Client client = ClientBuilder
                .newBuilder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .build();

        this.bookStorePriceTarget = client.target("http://service-a:9082/mp-miracle/data/prices");
    }

    public Double getPriceForBook(int id) {
        Double bookPrice = this.bookStorePriceTarget.path(String.valueOf(id)).request().get().readEntity(Double.class);
        return Math.round((bookPrice - discount) * 100.0) / 100.0;
    }

}