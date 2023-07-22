package uz.pdp.backend.dto;

import uz.pdp.backend.entity.Option;
import uz.pdp.backend.entity.Question;

public record AnswerDTO (Question question, Option selectedOption){}
