package com.dmitrenko.simplespringstatemachine.service.impl;

import com.dmitrenko.simplespringstatemachine.config.TestConfig;
import com.dmitrenko.simplespringstatemachine.service.TaskService;
import com.dmitrenko.simplespringstatemachine.util.DataManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.dmitrenko.simplespringstatemachine.model.entity.TaskState.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private DataManager dataManager;

    @BeforeEach
    void prepare() {

        dataManager.deleteAll();
    }

    @Test
    void startStateMachine() {
        dataManager.addNewTasks();
        dataManager.addNotFinishedTasks();

        taskService.startStateMachine();

        var tasks = dataManager.getAllTasks();
        tasks.forEach(o -> assertEquals(SUCCESS, o.getState()));
        assertEquals(6, tasks.size());
    }
}