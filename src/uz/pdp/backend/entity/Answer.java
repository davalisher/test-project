package uz.pdp.backend.entity;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class Answer extends BaseEntity<UUID> {

    private Question question;
    private Option selectedOption;
}
