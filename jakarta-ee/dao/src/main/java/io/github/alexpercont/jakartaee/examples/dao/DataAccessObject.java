package io.github.alexpercont.jakartaee.examples.dao;

import io.github.alexpercont.jakartaee.examples.persistence.entities.EntityClass;

public interface DataAccessObject<Entity extends EntityClass, Id> {

    Entity read(Id id);

    void create(Entity entity);
}
