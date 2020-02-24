package com.miracle.mp.miracle.health;

import java.io.File;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class ServiceLiveHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {

    	File file = new File("/");
        long freeSpace = file.getFreeSpace() / 1024 / 1024;
        
        return HealthCheckResponse.named("ServiceLiveHealthCheck")
                .withData("remainingSpace", freeSpace).state(freeSpace > 100)
        		.build();
    }
}


