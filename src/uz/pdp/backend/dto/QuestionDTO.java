package uz.pdp.backend.dto;

import uz.pdp.backend.entity.Option;

import java.util.List;

public record QuestionDTO<ID>(String title, List<Option> options,ID createdBy ){}
