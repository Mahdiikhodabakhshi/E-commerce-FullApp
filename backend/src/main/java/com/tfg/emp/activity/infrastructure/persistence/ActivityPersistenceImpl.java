package com.tfg.emp.activity.infrastructure.persistence;


import com.tfg.emp.activity.domain.entity.Activity;
import com.tfg.emp.activity.domain.persistence.ActivityPersistence;
import com.tfg.emp.activity.specs.ActivitySpecification;
import com.tfg.emp.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActivityPersistenceImpl implements ActivityPersistence {



    private final ActivityJpaRepository activityJpaRepository;

    @Autowired
    public ActivityPersistenceImpl(ActivityJpaRepository activityJpaRepository) {
        this.activityJpaRepository = activityJpaRepository;
    }


    @Override
    public Page<Activity> findAll(Pageable pageable, String filter) {
        ActivitySpecification specification = new ActivitySpecification(SearchCriteriaHelper.fromFilterString(filter));
        return activityJpaRepository.findAll(specification, pageable);
    }

    @Override
    public List<Activity> findAll() {

        return activityJpaRepository.findAll();
    }

    @Override
    public List<Activity> getActivityByName(String partialName) {
        return activityJpaRepository.findByTitleContainsIgnoreCase(partialName);
    }

    @Override
    public Optional<Activity> getActivityById(Long activityId) {
        return activityJpaRepository.findById(activityId);
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return activityJpaRepository.save(activity);
    }

    @Override
    public void deleteActivity(Long activityId) {
        activityJpaRepository.deleteById(activityId);
    }
}
