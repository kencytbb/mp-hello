package com.miracle.mp.miracle.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@Liveness
public class MultipleHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("MultipleHealthCheck")
                .withData("foo", "bar")
                .withData("uptime", 42)
                .withData("isReady", true)
                .up()
                .build();
    }
}
