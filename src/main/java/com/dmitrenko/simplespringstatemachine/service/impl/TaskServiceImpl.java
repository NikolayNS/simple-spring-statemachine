package com.dmitrenko.simplespringstatemachine.service.impl;

import com.dmitrenko.simplespringstatemachine.model.entity.TaskEvent;
import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;
import com.dmitrenko.simplespringstatemachine.service.TaskService;
import com.dmitrenko.simplespringstatemachine.service.domain.TaskDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.transition.Transition;
import org.springframework.statemachine.trigger.Trigger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.dmitrenko.simplespringstatemachine.util.Constant.TASK_ID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDomainService taskDomainService;

    private final StateMachineFactory<TaskState, TaskEvent> factory;
    private final StateMachineInterceptor<TaskState, TaskEvent> interceptor;

    @Override
    public void startStateMachine() {
        taskDomainService.getAllNotFinished()
            .forEach(o -> {
                    var stateMachine = buildStateMachine(o.getId(), o.getState());

                    var events = stateMachine.getTransitions()
                        .stream()
                        .map(Transition::getTrigger)
                        .map(Trigger::getEvent)
                        .toList();

                    events
                        .forEach(e -> stateMachine
                            .sendEvent(Mono
                                .just(MessageBuilder
                                    .withPayload(e)
                                    .setHeaderIfAbsent(TASK_ID, o.getId())
                                    .build()))
                            .subscribe()
                        );
                }
            );
    }

    private StateMachine<TaskState, TaskEvent> buildStateMachine(UUID id, TaskState state) {
        var stateMachine = factory.getStateMachine(id);
        stateMachine.stopReactively().subscribe();

        stateMachine.getStateMachineAccessor()
            .doWithAllRegions(s -> {
                s.addStateMachineInterceptor(interceptor);
                s.resetStateMachineReactively(new DefaultStateMachineContext<>(state, null, null, null)).subscribe();
            });

        stateMachine.startReactively().subscribe();

        return stateMachine;
    }
}
