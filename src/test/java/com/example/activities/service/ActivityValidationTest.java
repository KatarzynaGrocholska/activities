package com.example.activities.service;

import com.example.activities.model.entity.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ActivityValidationTest {

    private ActivityValidation activityValidation;


    @BeforeEach
    void initializeValidation() {
        activityValidation = new ActivityValidation();
    }

    @Test
    void shouldReturnFirstPageOfActivitiesWhenPageNumberIsZeroOrNegative() {
        //given
        int pageNumberZero = 0;
        int pageNumberNegative = -2;
        //when

        int properPageWhenPageIsZero = activityValidation.setProperPageNumberIfIsNullOrNegative(pageNumberZero);
        int properPageWhenPageIsNegative = activityValidation.setProperPageNumberIfIsNullOrNegative(pageNumberNegative);
        //then
        assertAll(
                () -> assertEquals(1, properPageWhenPageIsZero),
                () -> assertEquals(1, properPageWhenPageIsNegative));

    }

    @Test
    void shouldCalculateProperPageNumber() {
        //given
        int repositoryListSizeZero = 0;
        int sizeOfQueryNumberSix = 6;
        int repositoryListSizeFive = 5;
        int repositoryListSizeSix = 6;
        int sizeOfQueryNumberTwo = 2;
        int sizeOfQueryNumberThree = 3;
        //when
        int repositorySizeListZero = activityValidation.calculateProperPagesCount(repositoryListSizeZero, sizeOfQueryNumberSix);
        int moduloSizeOfQueryAndActivityListSizeNotZero = activityValidation.calculateProperPagesCount(repositoryListSizeFive, sizeOfQueryNumberTwo);
        int moduloSizeOfQueryAndActivityListSizeZero = activityValidation.calculateProperPagesCount(repositoryListSizeSix, sizeOfQueryNumberThree);
        // TODO not a serious issue, but if you use the variables only once a comment would be enough
        //then
        assertAll(
                () -> assertEquals(1, repositorySizeListZero),
                () -> assertEquals(3, moduloSizeOfQueryAndActivityListSizeNotZero),
                () -> assertEquals(2, moduloSizeOfQueryAndActivityListSizeZero));

    }

    @Test
    void shouldReturnWholeListOfActivitiesWhenSizeOfQueryIsZeroOrNegative() {
        //given
        List<Activity> listOfActivity = new ArrayList<>();
        //FIXME - listOfActivity is always empty
        //TODO again, the variables below are used just once, a comment would be enough
        int sizeOfQueryNumberZero = 0;
        int sizeOfQueryNumberNegative = -2;
        //when
        int sizeOfQueryIsZero = activityValidation.setProperSizeOfQueryIfIsNullOrNegative(sizeOfQueryNumberZero, listOfActivity.size());
        int sizeOfQueryIsNegative = activityValidation.setProperSizeOfQueryIfIsNullOrNegative(sizeOfQueryNumberNegative, listOfActivity.size());
        //then
        assertAll(
                () -> assertEquals(listOfActivity.size(), sizeOfQueryIsZero),
                () -> assertEquals(listOfActivity.size(), sizeOfQueryIsNegative));

    }

    @Test
    void shouldSetProperPageNumberIfIsOverTotalAvailablePages() {
        //given
        List<Activity> listOfActivity = new ArrayList<>();
        int pageCountTwo = 2;
        int pageNumberOne = 1;
        int pageNumberTree = 3;

        //when
        int pageCountBiggerThenPageNumber = activityValidation.setProperPageNumberIfIsOverTotalAvailablePages(pageCountTwo, pageNumberOne);
        int pageCountSmallerThenPageNumber = activityValidation.setProperPageNumberIfIsOverTotalAvailablePages(pageCountTwo, pageNumberTree);

        //then
        assertAll(
                () -> assertEquals(1, pageCountBiggerThenPageNumber),
                () -> assertEquals(2, pageCountSmallerThenPageNumber));

    }
}
