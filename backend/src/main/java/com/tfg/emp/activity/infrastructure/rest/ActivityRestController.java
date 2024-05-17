package com.tfg.emp.activity.infrastructure.rest;

import com.tfg.emp.activity.application.Dto.ActivityDTO;
import com.tfg.emp.activity.application.service.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ActivityRestController {
    private final ActivityService activityService;

    public ActivityRestController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping(value = "/activities/new-activities" , produces = "application/json")
    public ResponseEntity<Page<ActivityDTO>> getNewActivities(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.DESC,"uploadDate");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<ActivityDTO> activityDTOS = activityService.getActivityByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(activityDTOS);
    }

    @GetMapping(value = "/activities" , produces = "application/json")
    public ResponseEntity<Page<ActivityDTO>> getActivities(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Sort sort =Sort.by(Sort.Direction.DESC,"uploadDate");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<ActivityDTO> activityDTOS = activityService.getActivityByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(activityDTOS);
    }

    @GetMapping(value = "/activities/{activityId}" , produces = "application/json")
    public ResponseEntity<ActivityDTO> getActivity(@PathVariable("activityId") Long activityId) {
        return activityService.getActivityById(activityId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/activities" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {

        ActivityDTO aDTO = activityService.saveActivity(activityDTO);
        return new ResponseEntity<>(aDTO , HttpStatus.CREATED);
    }

    @PatchMapping(value = "/activities" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<ActivityDTO> updateActivity(@RequestBody ActivityDTO activityDTO) {
        ActivityDTO aDTO = activityService.saveActivity(activityDTO);
        return ResponseEntity.ok(aDTO);
    }

    @DeleteMapping(value = "/activities/{activityId}" , produces = "application/json")
    public ResponseEntity<ActivityDTO> deleteActivity(@PathVariable("activityId") Long activityId) {
        activityService.deleteActivity(activityId);

        return ResponseEntity.noContent().build();
    }








}
