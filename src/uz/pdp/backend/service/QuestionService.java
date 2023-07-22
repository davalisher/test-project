package uz.pdp.backend.service;

import uz.pdp.backend.dto.QuestionDTO;
import uz.pdp.backend.entity.Question;
import uz.pdp.backend.repository.QuestionRepository;

import java.util.List;
import java.util.UUID;

public class QuestionService extends BaseService<Question, UUID, QuestionDTO> {
    QuestionRepository questionRepository = QuestionRepository.getInstance();

    @Override
    public boolean create(QuestionDTO questionDTO) {
        Question question = new Question(questionDTO);
        question.setCreatedBy((UUID) questionDTO.createdBy());
        questionRepository.add(question);
        return true;
    }

    @Override
    public List<Question> getAll() {
       return questionRepository.getAll();
    }
}
