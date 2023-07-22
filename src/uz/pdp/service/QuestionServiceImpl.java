package uz.pdp.service;

import uz.pdp.dto.QuestionDTO;
import uz.pdp.entity.Question;
import uz.pdp.repository.QuestionRepository;
import uz.pdp.repository.QuestionRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class QuestionServiceImpl implements QuestionService {
    QuestionRepository questionRepository = new QuestionRepositoryImpl();

    @Override
    public void create(QuestionDTO questionDTO) {
        Question question = new Question(questionDTO);
        questionRepository.QUESTIONS.add(question);
        System.out.println(questionRepository.QUESTIONS);
    }

    @Override
    public void edit(Question editedQuestion) {
        for (int i = 0; i < questionRepository.QUESTIONS.size(); i++) {
            if (questionRepository.QUESTIONS.get(i).getUuid().equals(editedQuestion.getUuid())) {
                questionRepository.QUESTIONS.set(i, editedQuestion);
                return;
            }
        }
        System.out.println(questionRepository.QUESTIONS);
        System.out.println(questionRepository.QUESTIONS);
    }

    @Override
    public boolean delete(UUID uuid) {
        for (int i = 0; i < questionRepository.QUESTIONS.size(); i++) {
            if (questionRepository.QUESTIONS.get(i).getUuid().equals(uuid)) {
                questionRepository.QUESTIONS.remove(i);
                System.out.println(questionRepository.QUESTIONS);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.QUESTIONS;
    }
}
