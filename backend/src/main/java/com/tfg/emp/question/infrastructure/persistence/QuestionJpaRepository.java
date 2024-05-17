package com.tfg.emp.question.infrastructure.persistence;

import com.tfg.emp.question.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionJpaRepository extends JpaRepository<Question, Long> , JpaSpecificationExecutor<Question> {
}
