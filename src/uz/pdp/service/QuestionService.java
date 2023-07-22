package uz.pdp.service;

import uz.pdp.dto.QuestionDTO;
import uz.pdp.entity.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    void create(QuestionDTO questionDTO);
    void edit(Question editedQuestion);
    boolean delete(UUID uuid);
    List<Question> getAll();
}
