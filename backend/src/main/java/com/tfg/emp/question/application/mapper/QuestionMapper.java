package com.tfg.emp.question.application.mapper;

import com.tfg.emp.mapper.EntityMapper;
import com.tfg.emp.question.application.Dto.QuestionDto;
import com.tfg.emp.question.domain.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends EntityMapper<QuestionDto, Question> {
}
