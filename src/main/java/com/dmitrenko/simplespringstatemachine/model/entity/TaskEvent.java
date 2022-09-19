package com.dmitrenko.simplespringstatemachine.model.entity;

import com.dmitrenko.simplespringstatemachine.exception.EnumValueNotFoundException;

import java.util.Arrays;

public enum TaskEvent {
    FIRST_ACTION("FIRST_ACTION"),
    SECOND_ACTION("SECOND_ACTION"),
    THIRD_ACTION("THIRD_ACTION"),
    FOURTH_ACTION("FOURTH_ACTION"),
    FIFTH_ACTION("FIFTH_ACTION"),
    SUCCESS_ACTION("SET_STATUS_SUCCESS");

    private final String value;

    TaskEvent(String value) {
        this.value = value;
    }

    public static TaskEvent fromValue(String value) {
        return Arrays.stream(TaskEvent.values())
            .filter(o -> o.value.equals(value))
            .findFirst()
            .orElseThrow(() -> new EnumValueNotFoundException(String.format("Unexpected TaskEvent value %s", value)));
    }

    public String getValue() {
        return value;
    }
}
