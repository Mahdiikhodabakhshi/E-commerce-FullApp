package com.tfg.emp.question.infrastructure.rest;


import com.tfg.emp.question.application.Dto.QuestionDto;
import com.tfg.emp.question.application.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuestionRestController {
    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/questions" , produces = "application/json")
    public ResponseEntity<Page<QuestionDto>> getQuestions(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.ASC,"title");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<QuestionDto> questionDtos = questionService.getQuestionDtoByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(questionDtos);
    }

    @GetMapping(value = "/questions/{questionId}" , produces = "application/json")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable("questionId") Long questionId) {
        return questionService.getQuestionDtoById(questionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/questions" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<QuestionDto> createQuestions(@RequestBody QuestionDto questionDto) {
        QuestionDto questionDto1 = questionService.saveQuestionDto(questionDto);
        return new ResponseEntity<>(questionDto1 , HttpStatus.CREATED);
    }

    @PatchMapping(value = "/questions" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<QuestionDto> updateQuestions(@RequestBody QuestionDto questionDto) {
        QuestionDto questionDto1 = questionService.saveQuestionDto(questionDto);
        return new ResponseEntity<>(questionDto1 , HttpStatus.OK);
    }

    @DeleteMapping(value = "/questions/{questionId}" , produces = "application/json")
    public ResponseEntity<QuestionDto> deletePromotion(@PathVariable("questionId") Long questionId) {
        questionService.deleteQuestionDto(questionId);
        return ResponseEntity.noContent().build();
    }



}
