package com.dmitrenko.simplespringstatemachine.model.dto.view;

import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class TaskView {
    private UUID id;
    private TaskState state;
}
