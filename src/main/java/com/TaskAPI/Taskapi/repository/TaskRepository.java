package com.TaskAPI.Taskapi.repository;

import com.TaskAPI.Taskapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
