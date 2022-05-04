package com.example.activities.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActivityValidation {


    protected int setProperSizeOfQueryIfIsNullOrNegative(Integer sizeOfQuery, Integer repositoryListSize) {
        if (sizeOfQuery == null || sizeOfQuery <= 0) {
            sizeOfQuery = repositoryListSize;
        }
        return sizeOfQuery;
    }

    protected int setProperPageNumberIfIsNullOrNegative(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        return pageNumber;
    }

    protected int calculateProperPagesCount(int repositoryListSize, Integer sizeOfQuery) {
        int pagesCount;
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
