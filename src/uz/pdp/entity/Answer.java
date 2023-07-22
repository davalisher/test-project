package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Answer {
    private String text;
    private boolean correct;

    public Answer(String text) {
        this.text = text;
    }
}
