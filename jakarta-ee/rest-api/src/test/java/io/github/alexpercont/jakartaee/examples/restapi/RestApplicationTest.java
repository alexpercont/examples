package io.github.alexpercont.jakartaee.examples.restapi;

import io.github.alexpercont.jakartaee.examples.restapi.resources.ExpensesResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RestApplicationTest {

    @Test
    public void getClassesTest(){
        RestApplication restApplication = new RestApplication();
        Assertions.assertTrue(restApplication.getClasses().contains(ExpensesResource.class));
    }
}
