package com.dmitrenko.simplespringstatemachine.model.entity;

import com.dmitrenko.simplespringstatemachine.exception.EnumValueNotFoundException;

import java.util.Arrays;

public enum TaskState {
    IDLE("IDLE"),
    FIRST_ACTION_COMPLETED("FIRST_ACTION_COMPLETED"),
    SECOND_ACTION_COMPLETED("SECOND_ACTION_COMPLETED"),
    THIRD_ACTION_COMPLETED("THIRD_ACTION_COMPLETED"),
    FOURTH_ACTION_COMPLETED("FOURTH_ACTION_COMPLETED"),
    FIFTH_ACTION_COMPLETED("FIFTH_ACTION_COMPLETED"),
    SUCCESS("SUCCESS"),
    ERROR("ERROR");

    private final String value;

    TaskState(String value) {
        this.value = value;
    }

    public static TaskState fromValue(String value) {
        return Arrays.stream(TaskState.values())
            .filter(o -> o.value.equals(value))
            .findFirst()
            .orElseThrow(() -> new EnumValueNotFoundException(String.format("Unexpected TaskState value %s", value)));
    }

    public String getValue() {
        return value;
    }
}
