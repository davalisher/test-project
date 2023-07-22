package uz.pdp.backend.repository;

import uz.pdp.backend.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<ENTITY extends BaseEntity, ID> implements Repository<ENTITY, ID> {
    List<ENTITY> ENTITIES=new ArrayList<>();

    @Override
    public Optional<ENTITY> getByID(ID id) {
        for (ENTITY entity : ENTITIES) {
            if (entity.getId().equals(id)){
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ENTITY> getAll() {
        return ENTITIES;
    }

    @Override
    public void add(ENTITY entity) {
        ENTITIES.add(entity);

    }

    @Override
    public ENTITY remove(ID id) {
        for (ENTITY entity : ENTITIES) {
            if (entity.getId().equals(id)){
                ENTITIES.remove(entity);
                return entity;
            }
        }
        return null;
    }
}
