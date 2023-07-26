package uz.pdp.backend.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Result {
    private int rightAnswers;
    private int wrongAnswers;

    public Result(int rightAnswers, int wrongAnswers) {
        this.rightAnswers = rightAnswers;
        this.wrongAnswers = wrongAnswers;
    }
}
