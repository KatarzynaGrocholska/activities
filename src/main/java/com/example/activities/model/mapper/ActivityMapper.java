package com.example.activities.model.mapper;

import com.example.activities.model.dto_input.ActivityInputDTO;
import com.example.activities.model.dto_output.ActivityOutputDTO;
import com.example.activities.model.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    Activity activityInputDtoToActivity(ActivityInputDTO dto);

    ActivityOutputDTO activityToActivityOutputDTO(Activity activity);

    List<ActivityOutputDTO> activityListToActivityOutputDtoList(List<Activity> activityList);

    List<Activity> activityInputDTOListActivityList(List<ActivityInputDTO> activityInputDTOList);
}
