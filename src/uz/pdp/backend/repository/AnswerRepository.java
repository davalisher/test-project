package uz.pdp.backend.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.pdp.backend.entity.Answer;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerRepository extends BaseRepository<Answer, UUID> {
    private static final AnswerRepository ANSWER_REPOSITORY = new AnswerRepository();

    public static AnswerRepository getInstance() {
        return ANSWER_REPOSITORY;
    }
}
