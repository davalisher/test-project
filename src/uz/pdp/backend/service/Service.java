package uz.pdp.backend.service;

import java.util.List;

public interface Service<Entity, ID,DTO > {
    boolean create(DTO answerDTO);
    Entity get(ID id);
    boolean update(Entity option);
    boolean delete(ID id);
    List<Entity> getAll();
    int numberOfElements();
}
