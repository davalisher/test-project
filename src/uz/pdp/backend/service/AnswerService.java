package uz.pdp.backend.service;

import uz.pdp.backend.dto.AnswerDTO;
import uz.pdp.backend.entity.Answer;
import uz.pdp.backend.repository.AnswerRepository;

import java.util.List;
import java.util.UUID;

public class   AnswerService extends BaseService<Answer, UUID, AnswerDTO> {
    AnswerRepository answerRepository=AnswerRepository.getInstance();

    @Override
    public boolean create(AnswerDTO answerDTO) {
        Answer option = new Answer(answerDTO.question(), answerDTO.selectedOption());
        answerRepository.add(option);
        return true;
    }

    @Override
    public List<Answer> getAll() {
        return answerRepository.getAll();
    }
}
