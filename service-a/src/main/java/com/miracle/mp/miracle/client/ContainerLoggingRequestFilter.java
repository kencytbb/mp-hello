package com.miracle.mp.miracle.client;

import java.time.LocalDateTime;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ContainerLoggingRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        System.out.println("==============");
        System.out.println("Incoming request at: " + LocalDateTime.now());
        requestContext.getHeaders().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("==============");
    }
}