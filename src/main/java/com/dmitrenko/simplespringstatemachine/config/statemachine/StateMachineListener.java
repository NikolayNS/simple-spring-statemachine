package com.dmitrenko.simplespringstatemachine.config.statemachine;

import com.dmitrenko.simplespringstatemachine.model.entity.TaskEvent;
import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StateMachineListener extends StateMachineListenerAdapter<TaskState, TaskEvent> {

    @Override
    public void stateMachineStarted(StateMachine<TaskState, TaskEvent> stateMachine) {
        log.info("State machine started");
    }

    @Override
    public void stateMachineStopped(StateMachine<TaskState, TaskEvent> stateMachine) {
        log.info("State machine stopped");
    }

    @Override
    public void stateChanged(State<TaskState, TaskEvent> from, State<TaskState, TaskEvent> to) {
        if (from != null && to != null)
            log.info(String.format("State changed from %s to %s", from.getId().getValue(), to.getId().getValue()));
    }

    @Override
    public void eventNotAccepted(Message<TaskEvent> event) {
        log.info("Event Not Accepted " + event.getPayload().getValue());
    }
}
