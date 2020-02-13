package com.miracle.mp.miracle.faultTolerance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/faultTolerance")
public class FaultToleranceController {

    @GET
    @Path("{id}")
    public Response getPostById(@PathParam("id") Long id) throws InterruptedException {
        return Response.ok(getDataFromLongRunningTask(id)).build();
    }

    public String getDefaultPost(Long id) {
        return "fault" + id.toString();
    }

    @Timeout(4000)
    @Fallback(fallbackMethod = "getDefaultPost")
    public String getDataFromLongRunningTask(Long id) throws InterruptedException {
        Thread.sleep(4500);
        return "duke" + id.toString();
    }
}