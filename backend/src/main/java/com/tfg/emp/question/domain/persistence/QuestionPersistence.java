package com.tfg.emp.question.domain.persistence;


import com.tfg.emp.question.domain.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuestionPersistence {
    Page<Question> findAllQuestions(Pageable pageable, String filter);
    Optional<Question> getQuestionById(Long questionId);
    Question saveQuestion(Question question);
    void deleteQuestion(Long questionId);
}
