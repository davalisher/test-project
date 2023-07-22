package uz.pdp.backend.service;

public abstract class BaseService<ENTITY,ID, DTO> implements Service<ENTITY,ID, DTO> {


    @Override
    public ENTITY get(ID id) {
        return null;
    }

    @Override
    public boolean update(ENTITY entity) {
        return false;
    }

    @Override
    public boolean delete(ID id) {
        return false;
    }



}
