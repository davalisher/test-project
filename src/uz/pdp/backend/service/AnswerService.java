package uz.pdp.backend.service;

import uz.pdp.backend.dto.AnswerDTO;
import uz.pdp.backend.entity.Answer;
import uz.pdp.backend.repository.AnswerRepository;
import uz.pdp.backend.util.Result;

import java.util.List;
import java.util.UUID;

public class AnswerService extends BaseService<Answer, UUID, AnswerDTO, AnswerRepository> {
    private AnswerService() {
        repository = AnswerRepository.getInstance();
    }

    private static final AnswerService service = new AnswerService();

    @Override
    public boolean create(AnswerDTO answerDTO) {
        Answer option = new Answer(answerDTO.question(), answerDTO.selectedOption());
        repository.add(option);
        return true;
    }

    @Override
    public List<Answer> getAll() {
        return repository.getAll();
    }

    public Result getResult(List<Answer> answers) {
        Result result = new Result();
        int rightAnswers = 0;
        int wrongAnswers = 0;
        for (Answer answer : answers) {
            if (answer.getSelectedOption().isCorrect()) {
                rightAnswers++;
            } else {
                wrongAnswers++;
            }
        }
        result.setRightAnswers(rightAnswers);
        result.setWrongAnswers(wrongAnswers);
        return result;
    }


    public static AnswerService getInstance() {
        return service;
    }

}
