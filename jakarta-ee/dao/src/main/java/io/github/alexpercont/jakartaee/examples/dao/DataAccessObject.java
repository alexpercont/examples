package io.github.alexpercont.jakartaee.examples.dao;

import io.github.alexpercont.jakartaee.examples.persistence.entities.EntityClass;

public interface DataAccessObject<Entity extends EntityClass, Id> {

    Entity findById(Id id);

}
