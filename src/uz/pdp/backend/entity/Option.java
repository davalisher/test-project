package uz.pdp.backend.entity;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class Option extends BaseEntity<UUID> { // javob varianti

    private String text;
    private boolean correct;

    public Option(String text) {
        this.text = text;
    }
}
