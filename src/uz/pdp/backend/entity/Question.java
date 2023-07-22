package uz.pdp.backend.entity;

import lombok.*;
import uz.pdp.backend.dto.QuestionDTO;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","text","options"})
@EqualsAndHashCode(callSuper=true)
public class Question extends BaseEntity<UUID> {

    private String text;
    private List <Option> options;

    public Question(QuestionDTO questionDTO) {
        this.id =UUID.randomUUID();
        this.text =questionDTO.title();
        this.options =questionDTO.options();
    }
}
