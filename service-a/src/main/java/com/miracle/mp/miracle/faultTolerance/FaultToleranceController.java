package com.miracle.mp.miracle.faultTolerance;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/faultTolerance")
public class FaultToleranceController {

    @GET
    @Path("/fallback/{id}")
    public Response getNameById(@PathParam("id") Long id) throws InterruptedException {
        return Response.ok(getDataFromLongRunningTask(id)).build();
    }

    @GET
    @Path("/retry")
    public Response callRetry() {
        return Response.ok(accessFlakyService()).build();
    }

    public String getDefaultName(Long id) {
        return "John" + id.toString();
    }

    @Timeout(3)
    @Fallback(fallbackMethod = "getDefaultName")
    public String getDataFromLongRunningTask(Long id) throws InterruptedException {
        Thread.sleep(4500);
        return "duke" + id.toString();
    }

    @Retry(maxDuration = 5000, maxRetries = 3, delay = 500, jitter = 200)
    public String accessFlakyService() {
    
        System.out.println("Trying to access flaky service at " + LocalTime.now());
    
        if (ThreadLocalRandom.current().nextLong(1000) < 50) {
            return "flaky duke";
        } else {
            throw new RuntimeException("Flaky service not accessible");
        }
    }
}