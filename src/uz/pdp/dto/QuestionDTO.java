package uz.pdp.dto;

import uz.pdp.entity.Answer;

import java.util.List;

public record QuestionDTO(String title, List<Answer> answers){}
