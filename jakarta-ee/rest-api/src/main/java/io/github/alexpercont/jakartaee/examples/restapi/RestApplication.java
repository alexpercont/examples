package io.github.alexpercont.jakartaee.examples.restapi;

import io.github.alexpercont.jakartaee.examples.restapi.resources.ExpensesResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api/rest")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(ExpensesResource.class);
    }
}
