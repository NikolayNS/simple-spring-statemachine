package com.dmitrenko.simplespringstatemachine.service;

import java.util.UUID;

public interface ActionService {

    void firstAction(UUID id);

    void secondAction(UUID id);

    void thirdAction(UUID id);

    void fourthAction(UUID id);

    void fifthAction(UUID id);

    void successAction(UUID id);

    void errorAction(UUID id, Exception exception);
}
