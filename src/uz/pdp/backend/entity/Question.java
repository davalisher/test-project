package uz.pdp.backend.entity;

import lombok.*;
import uz.pdp.backend.dto.QuestionDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "text", "options"})
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseEntity<UUID> {
    {
        id = UUID.randomUUID();
    }
@Builder
    public Question(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, UUID createdBy, UUID updatedBy, String text, List<Option> options) {
        super(id, createdAt, updatedAt, createdBy, updatedBy);
        this.text = text;
        this.options = options;
    }

    private String text;
    private List<Option> options;

    public Question(QuestionDTO questionDTO) {
        this.id = UUID.randomUUID();
        this.text = questionDTO.title();
        this.options = questionDTO.options();
    }

    public Question(String text, List<Option> options) {
        this.text = text;
        this.options = options;
    }
}
