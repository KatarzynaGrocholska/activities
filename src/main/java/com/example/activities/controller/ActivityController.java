package com.example.activities.controller;

import com.example.activities.model.dto_input.ActivityInputDTO;
import com.example.activities.model.dto_output.ActivityOutputDTO;
import com.example.activities.service.ActivityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PrePersist;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    @PrePersist
    @GetMapping("/random")
    public ActivityInputDTO random() {
        activityService.saveRandomActivity();
        return activityService.getRandomActivity();
    }

    @GetMapping("/all")
    public List<ActivityOutputDTO> getAllSavedActivities() {
        return activityService.showAllSavedActivities();
    }


    @GetMapping(path = "")
    public List<ActivityOutputDTO> getAllRecruitmentsByStatus(@RequestParam List<String> typeList,
                                                         @RequestParam(required = false) Integer page,
                                                         @RequestParam(required = false) Integer size) {
        return activityService.getAllActivitiesByTypeList(typeList, page, size);
    }

}
