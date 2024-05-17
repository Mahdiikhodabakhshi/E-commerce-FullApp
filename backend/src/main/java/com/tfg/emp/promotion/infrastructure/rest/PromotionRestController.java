package com.tfg.emp.promotion.infrastructure.rest;


import com.tfg.emp.promotion.application.Dto.PromotionDTO;
import com.tfg.emp.promotion.application.service.PromotionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PromotionRestController {
    private final PromotionService promotionService;

    public PromotionRestController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping(value = "/promotions/new-promotions" , produces = "application/json")
    public ResponseEntity<Page<PromotionDTO>> getNewPromotions(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.DESC,"uploadDate");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<PromotionDTO> promotionDTOS = promotionService.getPromotionByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(promotionDTOS);
    }


    @GetMapping(value = "/promotions" , produces = "application/json")
    public ResponseEntity<Page<PromotionDTO>> getPromotions(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.DESC,"uploadDate");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<PromotionDTO> promotionDTOS = promotionService.getPromotionByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(promotionDTOS);
    }

    @GetMapping(value = "/promotions/{promotionId}" , produces = "application/json")
    public ResponseEntity<PromotionDTO> getPromotion(@PathVariable("promotionId") Long activityId) {
        return promotionService.getPromotionById(activityId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/promotions" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO) {
        PromotionDTO promotion = promotionService.savePromotion(promotionDTO);
        return new ResponseEntity<>(promotion , HttpStatus.CREATED);
    }

    @PatchMapping(value = "/promotions" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<PromotionDTO> updatePromotions(@RequestBody PromotionDTO promotionDTO) {
        PromotionDTO promotion = promotionService.savePromotion(promotionDTO);
        return new ResponseEntity<>(promotion , HttpStatus.OK);
    }

    @DeleteMapping(value = "/promotions/{promotionId}" , produces = "application/json")
    public ResponseEntity<PromotionDTO> deletePromotion(@PathVariable("promotionId") Long promotionId) {
        promotionService.deletePromotion(promotionId);
        return ResponseEntity.noContent().build();
    }




}
