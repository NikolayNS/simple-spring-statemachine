package com.dmitrenko.simplespringstatemachine.util;

import com.dmitrenko.simplespringstatemachine.model.entity.Task;
import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;
import com.dmitrenko.simplespringstatemachine.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataManager {

    private final TaskRepository taskRepository;

    @Transactional
    public void deleteAll() {
        taskRepository.deleteAll();
        taskRepository.flush();
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public void addNewTasks() {
        taskRepository.saveAllAndFlush(List.of(
            new Task().setState(TaskState.IDLE),
            new Task().setState(TaskState.IDLE),
            new Task().setState(TaskState.IDLE)));
    }

    @Transactional
    public void addNotFinishedTasks() {
        taskRepository.saveAllAndFlush(List.of(
            new Task().setState(TaskState.THIRD_ACTION_COMPLETED),
            new Task().setState(TaskState.FIFTH_ACTION_COMPLETED),
            new Task().setState(TaskState.SECOND_ACTION_COMPLETED)));
    }
}
