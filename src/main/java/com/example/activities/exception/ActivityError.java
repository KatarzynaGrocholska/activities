package com.example.activities.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActivityError {

    ACTIVITY_NOT_FOUND("Nie znaleziono aktywności"),
    ACTIVITY_LIST_NOT_FOUND("Lista aktywności jest pusta");

    private final String message;
}
