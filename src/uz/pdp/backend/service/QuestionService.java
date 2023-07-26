package uz.pdp.backend.service;

import uz.pdp.backend.dto.QuestionDTO;
import uz.pdp.backend.entity.Question;
import uz.pdp.backend.repository.QuestionRepository;

import java.util.List;
import java.util.UUID;

public class QuestionService extends BaseService<Question, UUID, QuestionDTO, QuestionRepository> {

    private QuestionService() {
        repository=QuestionRepository.getInstance();
    }
    private static final QuestionService questionService=new QuestionService();

    @Override
    public boolean create(QuestionDTO questionDTO) {
        Question question = new Question(questionDTO);
        question.setCreatedBy((UUID) questionDTO.createdBy());
        repository.add(question);
        return true;
    }

    public static QuestionService getInstance() {
        return questionService;
    }


}
