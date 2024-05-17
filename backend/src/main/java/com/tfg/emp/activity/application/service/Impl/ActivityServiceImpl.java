package com.tfg.emp.activity.application.service.Impl;

import com.tfg.emp.activity.application.Dto.ActivityDTO;
import com.tfg.emp.activity.application.Dto.ActivitySimpleDTO;
import com.tfg.emp.activity.application.mapper.ActivityMapper;
import com.tfg.emp.activity.application.service.ActivityService;
import com.tfg.emp.activity.domain.entity.Activity;
import com.tfg.emp.activity.domain.persistence.ActivityPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityPersistence activityPersistence;
    private final ActivityMapper activityMapper;

    @Autowired
    public ActivityServiceImpl(ActivityPersistence activityPersistence, ActivityMapper activityMapper) {
        this.activityPersistence = activityPersistence;
        this.activityMapper = activityMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ActivitySimpleDTO> getActivities() {
        List<Activity> activities = activityPersistence.findAll();
        return activityMapper.toSimpleDTO(activities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivitySimpleDTO> getActivityByName(String partialName) {
        List<Activity> activities = activityPersistence.getActivityByName(partialName);
        return activityMapper.toSimpleDTO(activities);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ActivityDTO> getActivityByCriteriaPaged(Pageable pageable, String search) {

        Page<Activity> activityPage = activityPersistence.findAll(pageable, search);
        return activityPage.map(activityMapper::toDto);
    }

    @Override
    public Optional<ActivityDTO> getActivityById(Long activityId) {
        return activityPersistence.getActivityById(activityId)
                .map(activityMapper::toDto);
    }

    @Override
    @Transactional
    public ActivityDTO saveActivity(ActivityDTO activityDTO) {
        activityDTO.setUploadDate(new Date());
        Activity activity = activityMapper.toEntity(activityDTO);
        activity = activityPersistence.saveActivity(activity);
        return activityMapper.toDto(activity);
    }

    @Override
    public void deleteActivity(Long activityId) {
        activityPersistence.deleteActivity(activityId);
    }
}
