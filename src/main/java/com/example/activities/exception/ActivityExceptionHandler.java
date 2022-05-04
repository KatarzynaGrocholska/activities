package com.example.activities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ActivityExceptionHandler {

    @RestControllerAdvice
    public class ActivityExceptionHandler {

        @ExceptionHandler(value = ActivityException.class)
        public ResponseEntity<ActivityErrorInfo> handlerActivityException(ActivityException e) {
            HttpStatus httpStatus = HttpStatus.MULTI_STATUS;
            if (e.getActivityError().equals(ActivityError.ACTIVITY_LIST_NOT_FOUND)) {
                httpStatus = HttpStatus.NOT_FOUND;
            }
            return ResponseEntity.status(httpStatus).body(new ActivityErrorInfo(e.getActivityError().getMessage()));
        }

    }
}
