package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    @Inject
    EventService service;

    @POST
    public EventEntity create(EventEntity e) {
        return service.create(e.type, e.payload);
    }

    @GET
    public List<EventEntity> list(@QueryParam("limit") @DefaultValue("10") int limit) {
        return service.list(limit);
    }
}
