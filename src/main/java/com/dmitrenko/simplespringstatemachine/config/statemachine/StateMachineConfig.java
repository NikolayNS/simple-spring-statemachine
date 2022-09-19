package com.dmitrenko.simplespringstatemachine.config.statemachine;

import com.dmitrenko.simplespringstatemachine.model.entity.TaskEvent;
import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;
import com.dmitrenko.simplespringstatemachine.service.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;

import java.util.EnumSet;
import java.util.UUID;

import static com.dmitrenko.simplespringstatemachine.model.entity.TaskEvent.*;
import static com.dmitrenko.simplespringstatemachine.model.entity.TaskState.*;
import static com.dmitrenko.simplespringstatemachine.util.Constant.TASK_ID;

@Slf4j
@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor
public class StateMachineConfig extends StateMachineConfigurerAdapter<TaskState, TaskEvent> {

    private final ActionService actionService;

    private final StateMachineListener<TaskState, TaskEvent> listener;

    @Override
    public void configure(StateMachineTransitionConfigurer<TaskState, TaskEvent> transitions) throws Exception {
        transitions
            .withExternal().source(IDLE).target(FIRST_ACTION_COMPLETED).event(FIRST_ACTION).action(firstAction(), errorAction())
            .and()
            .withExternal().source(FIRST_ACTION_COMPLETED).target(SECOND_ACTION_COMPLETED).event(SECOND_ACTION).action(secondAction(), errorAction())
            .and()
            .withExternal().source(SECOND_ACTION_COMPLETED).target(THIRD_ACTION_COMPLETED).event(THIRD_ACTION).action(thirdAction(), errorAction())
            .and()
            .withExternal().source(THIRD_ACTION_COMPLETED).target(FOURTH_ACTION_COMPLETED).event(FOURTH_ACTION).action(fourthAction(), errorAction())
            .and()
            .withExternal().source(FOURTH_ACTION_COMPLETED).target(FIFTH_ACTION_COMPLETED).event(FIFTH_ACTION).action(fifthAction(), errorAction())
            .and()
            .withExternal().source(FIFTH_ACTION_COMPLETED).target(SUCCESS).event(SUCCESS_ACTION).action(successAction(), errorAction());
    }

    @Override
    public void configure(StateMachineStateConfigurer<TaskState, TaskEvent> states) throws Exception {
        states.withStates()
            .initial(IDLE)
            .states(EnumSet.allOf(TaskState.class))
            .end(SUCCESS)
            .end(ERROR);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<TaskState, TaskEvent> config) throws Exception {
        config.withConfiguration()
            .autoStartup(false)
            .listener(listener);
    }

    public Action<TaskState, TaskEvent> firstAction() {
        return context -> actionService.firstAction((UUID) context.getMessageHeader(TASK_ID));
    }

    public Action<TaskState, TaskEvent> secondAction() {
        return context -> actionService.secondAction((UUID) context.getMessageHeader(TASK_ID));
    }

    public Action<TaskState, TaskEvent> thirdAction() {
        return context -> actionService.thirdAction((UUID) context.getMessageHeader(TASK_ID));
    }

    public Action<TaskState, TaskEvent> fourthAction() {
        return context -> actionService.fourthAction((UUID) context.getMessageHeader(TASK_ID));
    }

    public Action<TaskState, TaskEvent> fifthAction() {
        return context -> actionService.fifthAction((UUID) context.getMessageHeader(TASK_ID));
    }

    public Action<TaskState, TaskEvent> successAction() {
        return context -> actionService.successAction((UUID) context.getMessageHeader(TASK_ID));
    }

    public Action<TaskState, TaskEvent> errorAction() {
        return context -> actionService.errorAction((UUID) context.getMessageHeader(TASK_ID), context.getException());
    }
}
