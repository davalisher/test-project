package uz.pdp.backend.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<ENTITY, ID> {
    Optional<ENTITY> getByID(ID id);
    List<ENTITY> getAll();
    void add(ENTITY entity);
    ENTITY remove(ID id);
}
