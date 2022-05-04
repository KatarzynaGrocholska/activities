package com.example.activities.service;

import ch.qos.logback.core.joran.spi.ActionException;
import com.example.activities.exception.ActivityError;
import com.example.activities.exception.ActivityException;
import com.example.activities.model.dto_input.ActivityInputDTO;
import com.example.activities.model.dto_output.ActivityOutputDTO;
import com.example.activities.model.entity.Activity;
import com.example.activities.model.mapper.ActivityMapper;
import com.example.activities.repository.ActivityRepository;
import com.example.activities.webclient.WebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper = ActivityMapper.INSTANCE;
    private final ActivityValidation activityValidation;
    private final WebClient webClient;


    public ActivityInputDTO getRandomActivity() {
        return webClient.callApi();
    }

    public void saveRandomActivity() {
        List<Activity> activitiesList = activityRepository.findAll();
        ActivityInputDTO randomActivityInputDTO = getRandomActivity();
        Activity randomActivity = activityMapper.activityInputDtoToActivity(randomActivityInputDTO);
        if (activitiesList.isEmpty()) {
            activityRepository.save(randomActivity);
        }
        for (Activity activity : activitiesList)
            if (activity.getKey().equals(
                    getRandomActivity().getKey())) {
            }
        activityRepository.save(randomActivity);
    }

    public List<ActivityOutputDTO> showAllSavedActivities() {

            List<Activity> activityList = activityRepository.findAll();
        if(activityList.isEmpty()){
throw new ActivityException(ActivityError.ACTIVITY_LIST_NOT_FOUND);
        }
        return activityMapper.activityListToActivityOutputDtoList(activityList);
    }

    public List<ActivityOutputDTO> getAllActivitiesByTypeList(List<String> typeList,
                                                              Integer pageNumber,
                                                              Integer sizeOfQuery) {

        int repositoryListSize = activityRepository.countActivitiesByTypeIn(typeList);

        sizeOfQuery = activityValidation.setProperSizeOfQueryIfIsNullOrNegative(sizeOfQuery, repositoryListSize);

        pageNumber = activityValidation.setProperPageNumberIfIsNullOrNegative(pageNumber);

        int pagesCount = activityValidation.calculateProperPagesCount(repositoryListSize, sizeOfQuery);

        pageNumber = activityValidation.setProperPageNumberIfIsOverTotalAvailablePages(pagesCount, pageNumber);


        List<Activity> activityListByType = activityRepository
                .findActivitiesByTypeIn(typeList, PageRequest.of(pageNumber - 1, sizeOfQuery));

        List<ActivityOutputDTO> activityOutputDTOList = activityMapper.activityListToActivityOutputDtoList(activityListByType);

        return activityOutputDTOList;
    }
}
