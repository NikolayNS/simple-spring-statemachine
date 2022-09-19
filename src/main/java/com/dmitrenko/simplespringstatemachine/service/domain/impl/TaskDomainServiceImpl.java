package com.dmitrenko.simplespringstatemachine.service.domain.impl;

import com.dmitrenko.simplespringstatemachine.mapper.TaskMapper;
import com.dmitrenko.simplespringstatemachine.model.dto.view.TaskView;
import com.dmitrenko.simplespringstatemachine.model.entity.Task;
import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;
import com.dmitrenko.simplespringstatemachine.repository.TaskRepository;
import com.dmitrenko.simplespringstatemachine.service.domain.TaskDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskDomainServiceImpl implements TaskDomainService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TaskView> getAllNotFinished() {
        return taskMapper
            .from(taskRepository
                .findAllByStateNotIn(List.of(TaskState.SUCCESS, TaskState.ERROR)));
    }

    @Override
    @Transactional
    public void setState(UUID id, TaskState state) {
        var task = findById(id);
        task.setState(state);
        taskRepository.saveAndFlush(task);
    }

    @Override
    @Transactional
    public void setErrorState(UUID id, String errorMessage) {
        var task = findById(id);
        task.setState(TaskState.ERROR);
        task.setErrorMessage(errorMessage);
        taskRepository.saveAndFlush(task);
    }

    private Task findById(UUID id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Task with id %s not found", id)));
    }
}
