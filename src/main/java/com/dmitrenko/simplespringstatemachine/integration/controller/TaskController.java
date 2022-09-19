package com.dmitrenko.simplespringstatemachine.integration.controller;

import com.dmitrenko.simplespringstatemachine.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dmitrenko.simplespringstatemachine.util.Constant.START_STATE_MACHINE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping(START_STATE_MACHINE)
    public String startStateMachine() {
        log.info("Statemachine started");

        taskService.startStateMachine();
        return "ok";
    }
}
