package com.tfg.emp.question.application.service.Impl;

import com.tfg.emp.question.application.Dto.QuestionDto;
import com.tfg.emp.question.application.mapper.QuestionMapper;
import com.tfg.emp.question.application.service.QuestionService;
import com.tfg.emp.question.domain.entity.Question;
import com.tfg.emp.question.domain.persistence.QuestionPersistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionPersistence questionPersistence;
    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionPersistence questionPersistence, QuestionMapper questionMapper) {
        this.questionPersistence = questionPersistence;
        this.questionMapper = questionMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<QuestionDto> getQuestionDtoByCriteriaPaged(Pageable pageable, String search) {
        Page<Question> questions = questionPersistence.findAllQuestions(pageable, search);
        return questions.map(questionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionDto> getQuestionDtoById(Long questionId) {
        return questionPersistence
                .getQuestionById(questionId)
                .map(questionMapper::toDto);
    }

    @Override
    @Transactional
    public QuestionDto saveQuestionDto(QuestionDto questionDto) {
        Question question = questionMapper.toEntity(questionDto);
        question = questionPersistence.saveQuestion(question);
        return questionMapper.toDto(question);
    }

    @Override
    public void deleteQuestionDto(Long questionId) {
        questionPersistence.deleteQuestion(questionId);

    }
}
