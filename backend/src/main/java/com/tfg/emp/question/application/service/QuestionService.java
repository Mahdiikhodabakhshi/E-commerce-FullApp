package com.tfg.emp.question.application.service;


import com.tfg.emp.question.application.Dto.QuestionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuestionService {
    Page<QuestionDto> getQuestionDtoByCriteriaPaged(Pageable pageable , String search);
    Optional<QuestionDto> getQuestionDtoById(Long questionId);
    QuestionDto saveQuestionDto(QuestionDto questionDto);
    void deleteQuestionDto(Long questionId);
}
