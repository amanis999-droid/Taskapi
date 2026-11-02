package com.TaskAPI.Taskapi.service;

import com.TaskAPI.Taskapi.exception.TaskNotFoundException;
import com.TaskAPI.Taskapi.model.Task;
import com.TaskAPI.Taskapi.model.User;
import com.TaskAPI.Taskapi.repository.TaskRepository;
import com.TaskAPI.Taskapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks(String username) {
        return taskRepository.findByUserUsername(username);
    }

    public Task getTaskById(Long id, String username) {
        return taskRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));
    }

    public Task createTask(Task task, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setId(null);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask, String username) {
        Task task = getTaskById(id, username);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id, String username) {
        Task task = getTaskById(id, username);
        taskRepository.delete(task);
    }
}