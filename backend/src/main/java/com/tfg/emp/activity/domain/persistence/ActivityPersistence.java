package com.tfg.emp.activity.domain.persistence;

import com.tfg.emp.activity.domain.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivityPersistence {
  Page<Activity> findAll(Pageable pageable, String filter);
  List<Activity> findAll();
  List<Activity> getActivityByName(String partialName);
  Optional<Activity> getActivityById(Long activityId);
  Activity saveActivity(Activity activity);
  void deleteActivity(Long activityId);

}
