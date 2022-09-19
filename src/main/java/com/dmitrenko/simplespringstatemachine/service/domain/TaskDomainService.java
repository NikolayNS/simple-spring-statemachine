package com.dmitrenko.simplespringstatemachine.service.domain;

import com.dmitrenko.simplespringstatemachine.model.dto.view.TaskView;
import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;

import java.util.List;
import java.util.UUID;

public interface TaskDomainService {

    List<TaskView> getAllNotFinished();

    void setState(UUID id, TaskState state);

    void setErrorState(UUID id, String errorMessage);
}
