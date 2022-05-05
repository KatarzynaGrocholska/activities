package com.example.activities.service;

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
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper = ActivityMapper.INSTANCE;
    private final ActivityValidation activityValidation;
    private final WebClient webClient;


    public ActivityInputDTO getRandomActivity() {
        //FIXME the goal was to remember which activities have already been proposed - does this call guarantee a new and unique activity?
        return webClient.callApi();
    }

    //FIXME this method does more than one thing - gets an activity and saves it. Divide into 2 separate ones.
    public void saveRandomActivity() {
        List<Activity> activitiesList = activityRepository.findAll();
        ActivityInputDTO randomActivityInputDTO = getRandomActivity();
        Activity randomActivity = activityMapper.activityInputDtoToActivity(randomActivityInputDTO);
        if (activitiesList.isEmpty()) {
            activityRepository.save(randomActivity);
        }
        // TODO a Stream would've solved this easier
        //FIXME this loop does nothing
        for (Activity activity : activitiesList)
            if (activity.getKey().equals(
                    getRandomActivity().getKey())) {
            }
        activityRepository.save(randomActivity);
    }

    public List<ActivityOutputDTO> showAllSavedActivities() {

        List<Activity> activityList = activityRepository.findAll();
        if (activityList.isEmpty()) {
            throw new ActivityException(ActivityError.ACTIVITY_LIST_NOT_FOUND);
        }
        return activityMapper.activityListToActivityOutputDtoList(activityList);
    }

    //TODO why are pageNumber and sizeOfQuery objects and not simple types?
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
// TODO - unnecessary variable - the method name is very self explanatory
//        List<ActivityOutputDTO> activityOutputDTOList =
        return activityMapper.activityListToActivityOutputDtoList(activityListByType);

//        return activityOutputDTOList;
    }
}
