package com.dmitrenko.simplespringstatemachine.repository;

import com.dmitrenko.simplespringstatemachine.model.entity.Task;
import com.dmitrenko.simplespringstatemachine.model.entity.TaskState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByStateNotIn(List<TaskState> states);
}
