package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.dto.QuestionDTO;
import uz.pdp.dto.UserDTO;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    private UUID uuid;
    private String title;
    private List <Answer> answers;

    public Question(QuestionDTO questionDTO) {
        this.uuid=UUID.randomUUID();
        this.title=questionDTO.title();
        this.answers=questionDTO.answers();
    }
}
