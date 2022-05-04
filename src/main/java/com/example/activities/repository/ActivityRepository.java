package com.example.activities.repository;

import com.example.activities.model.entity.Activity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    List<Activity> findActivitiesByTypeIn(List<String> typeList, Pageable pageable);

    int countActivitiesByTypeIn(List<String> typeList);

}
