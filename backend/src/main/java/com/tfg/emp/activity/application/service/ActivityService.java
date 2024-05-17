package com.tfg.emp.activity.application.service;

import com.tfg.emp.activity.application.Dto.ActivityDTO;
import com.tfg.emp.activity.application.Dto.ActivitySimpleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<ActivitySimpleDTO> getActivities();
    List<ActivitySimpleDTO> getActivityByName(String partialName);
    Page<ActivityDTO> getActivityByCriteriaPaged(Pageable pageable , String search);
    Optional<ActivityDTO> getActivityById(Long activityId);
    ActivityDTO saveActivity(ActivityDTO activityDTO);

    void deleteActivity(Long activityId);
}
