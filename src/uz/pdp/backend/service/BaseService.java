package uz.pdp.backend.service;

import uz.pdp.backend.entity.BaseEntity;
import uz.pdp.backend.repository.BaseRepository;
import uz.pdp.backend.repository.Repository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<ENTITY extends BaseEntity,ID, DTO,REPOSITORY extends BaseRepository> implements Service<ENTITY,ID, DTO> {
    public Repository<ENTITY, ID> repository;

    @Override
    public ENTITY get(ID id) {
        Optional<ENTITY> optional = repository.getByID(id);
        return optional.orElseThrow();

    }

    @Override
    public boolean update(ENTITY option) {
        List<ENTITY> entities = repository.getAll();
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId().equals(option.getId())){
                entities.set(i, option);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(ID id) {
        ENTITY entity=repository.remove(id);
        return entity!=null;
    }

    @Override
    public List<ENTITY> getAll() {
        return repository.getAll();
    }

    @Override
    public int numberOfElements() {
        return repository.getAll().size();
    }
}
