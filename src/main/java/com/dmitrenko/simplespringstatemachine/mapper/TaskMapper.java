package com.dmitrenko.simplespringstatemachine.mapper;

import com.dmitrenko.simplespringstatemachine.model.dto.view.TaskView;
import com.dmitrenko.simplespringstatemachine.model.entity.Task;

import java.util.List;

public interface TaskMapper {

    TaskView from(Task source);

    default List<TaskView> from(List<Task> source) {
        return source.stream()
            .map(this::from)
            .toList();
    }
}
