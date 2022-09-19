package com.dmitrenko.simplespringstatemachine.config.statemachine;

import com.dmitrenko.simplespringstatemachine.model.entity.TaskEvent;
import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;
import com.dmitrenko.simplespringstatemachine.service.domain.TaskDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.dmitrenko.simplespringstatemachine.util.Constant.TASK_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StateMachineInterceptor extends StateMachineInterceptorAdapter<TaskState, TaskEvent> {

    private final TaskDomainService taskDomainService;

    @Override
    public void preStateChange(State<TaskState, TaskEvent> state,
                               Message<TaskEvent> message,
                               Transition<TaskState, TaskEvent> transition,
                               StateMachine<TaskState, TaskEvent> stateMachine,
                               StateMachine<TaskState, TaskEvent> rootStateMachine) {

        Optional.ofNullable(message)
            .ifPresent(msg ->
                taskDomainService.setState((UUID) msg.getHeaders().get(TASK_ID), state.getId()));
    }
}
