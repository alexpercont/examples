package io.github.alexpercont.jakartaee.examples.dao;

import io.github.alexpercont.jakartaee.examples.persistence.entities.EntityClass;
import jakarta.persistence.EntityManager;

public abstract class JpaDataAccessObject <Entity extends EntityClass, Id> implements DataAccessObject<Entity, Id> {

    private final EntityManager entityManager;
    private final Class<Entity> entityClass;

    protected JpaDataAccessObject(EntityManager entityManager, Class<Entity> w) {
        this.entityManager = entityManager;
        this.entityClass = w;
    }

    @Override
    public Entity findById(Id id) {
        return entityManager.find(entityClass, id);
    }
}
