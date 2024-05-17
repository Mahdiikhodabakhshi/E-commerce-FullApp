package com.tfg.emp.activity.application.mapper;

import com.tfg.emp.activity.application.Dto.ActivityDTO;
import com.tfg.emp.activity.application.Dto.ActivitySimpleDTO;
import com.tfg.emp.activity.domain.entity.Activity;
import com.tfg.emp.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper extends EntityMapper<ActivityDTO,Activity> {

    List<ActivitySimpleDTO> toSimpleDTO(List<Activity> activities);
}
