package com.miracle.mp.miracle.faultTolerance;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/faultTolerance")
@ApplicationScoped
public class FaultToleranceController {

    @GET
    @Path("/fallback")
    @Timeout(3)
    @Fallback(fallbackMethod = "fallout")
    public Response getName() throws InterruptedException {
        Thread.sleep(4500);
        return Response.ok(getDataFromLongRunningTask()).build();
    }

    @GET
    @Path("/retry")
    @Retry(retryOn = RuntimeException.class,
           maxRetries = 4,
           maxDuration = 10,
           durationUnit = ChronoUnit.SECONDS)
    public Response callRetry() throws IOException {
        accessFlakyService();
        return Response.ok("Retried!").build();
    }

    public Response fallout() {
        return Response.ok("Fallback answer due to timeout").build();
    }
    
    public String getDataFromLongRunningTask() throws InterruptedException {
        return "duke";
    }
    
    public String accessFlakyService()  {
        System.out.println("Thread wake up at " + LocalTime.now());
        if (ThreadLocalRandom.current().nextLong(1000) < 50) {
            return "flaky duke";
        } else {
            throw new RuntimeException("Flaky service not accessible");
        }
    }
}