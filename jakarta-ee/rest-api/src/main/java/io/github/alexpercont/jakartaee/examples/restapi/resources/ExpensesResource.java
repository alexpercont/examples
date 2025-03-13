package io.github.alexpercont.jakartaee.examples.restapi.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/expenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class ExpensesResource {

    @GET
    public List<String> getExpenses() {
        return List.of("Rent", "Food", "Transportation");
    }

}
