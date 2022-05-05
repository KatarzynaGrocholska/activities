package com.example.activities.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
//TODO this class doesn't validate and Activity, but rather pages of activities
public class ActivityValidation {

    // FIXME why are these parameters Objects and not primitives?
    protected int setProperSizeOfQueryIfIsNullOrNegative(Integer sizeOfQuery, Integer repositoryListSize) {
        if (sizeOfQuery == null || sizeOfQuery <= 0) {
            sizeOfQuery = repositoryListSize;
        }
        return sizeOfQuery;
    }
    // FIXME why are these parameters Objects and not primitives?
    protected int setProperPageNumberIfIsNullOrNegative(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        return pageNumber;
    }
    // FIXME why are these parameters Objects and not primitives?
    protected int calculateProperPagesCount(int repositoryListSize, Integer sizeOfQuery) {
        int pagesCount;
        //TODO pagesCount variable is unnecessary - you can just return the number in the if statement
        if (repositoryListSize == 0) {
            pagesCount = 1;
        } else if (repositoryListSize % sizeOfQuery == 0) {
            pagesCount = repositoryListSize / sizeOfQuery;
        } else {
            pagesCount = repositoryListSize / sizeOfQuery + 1;
        }
        return pagesCount;
    }

    protected int setProperPageNumberIfIsOverTotalAvailablePages(int pagesCount, int pageNumber) {
        if (pageNumber > pagesCount) {
            pageNumber = pagesCount;
        }
        return pageNumber;
    }
}
