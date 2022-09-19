package com.dmitrenko.simplespringstatemachine.mapper.impl;

import com.dmitrenko.simplespringstatemachine.mapper.TaskMapper;
import com.dmitrenko.simplespringstatemachine.model.dto.view.TaskView;
import com.dmitrenko.simplespringstatemachine.model.entity.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskView from(Task source) {
        return new TaskView()
            .setId(source.getId())
            .setState(source.getState());
    }
}
