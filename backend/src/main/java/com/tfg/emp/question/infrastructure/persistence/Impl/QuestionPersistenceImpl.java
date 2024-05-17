package com.tfg.emp.question.infrastructure.persistence.Impl;

import com.tfg.emp.question.domain.entity.Question;
import com.tfg.emp.question.domain.persistence.QuestionPersistence;
import com.tfg.emp.question.infrastructure.persistence.QuestionJpaRepository;
import com.tfg.emp.question.specs.QuestionSpecification;
import com.tfg.emp.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class QuestionPersistenceImpl implements QuestionPersistence {

    private final QuestionJpaRepository questionJpaRepository;

    @Autowired
    public QuestionPersistenceImpl(QuestionJpaRepository questionJpaRepository) {
        this.questionJpaRepository = questionJpaRepository;
    }

    @Override
    public Page<Question> findAllQuestions(Pageable pageable, String filter) {
        QuestionSpecification specification = new QuestionSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return questionJpaRepository.findAll(specification,pageable);
    }

    @Override
    public Optional<Question> getQuestionById(Long questionId) {
        return questionJpaRepository.findById(questionId);
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionJpaRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionJpaRepository.deleteById(questionId);
    }
}
