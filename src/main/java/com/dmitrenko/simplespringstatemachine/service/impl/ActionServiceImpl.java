package com.dmitrenko.simplespringstatemachine.service.impl;

import com.dmitrenko.simplespringstatemachine.exception.FourthActionException;
import com.dmitrenko.simplespringstatemachine.service.ActionService;
import com.dmitrenko.simplespringstatemachine.service.domain.TaskDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final TaskDomainService taskDomainService;

    @Override
    public void firstAction(UUID id) {
        log.info("First action for id: {}", id);
    }

    @Override
    public void secondAction(UUID id) {
        log.info("Second action for id: {}", id);
    }

    @Override
    public void thirdAction(UUID id) {
        log.info("Third action for id: {}", id);
    }

    @Override
    public void fourthAction(UUID id) {
        log.info("Fourth action for id: {}", id);
    }

    @Override
    public void fifthAction(UUID id) {
        log.info("Fifth action for id: {}", id);
    }

    @Override
    public void successAction(UUID id) {
        log.info("Success action for id: {}", id);
    }

    @Override
    public void errorAction(UUID id, Exception exception) {
        log.info("Error action for id: {}, error: {}", id, exception.getMessage());

        if (exception instanceof FourthActionException) {
            taskDomainService.setErrorState(id, exception.getMessage());
        }
    }
}
