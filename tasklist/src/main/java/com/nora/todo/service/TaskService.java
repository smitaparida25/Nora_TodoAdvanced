package com.nora.todo.service;

import com.nora.todo.dto.request.TaskUpdateRequest;
import com.nora.todo.dto.response.TaskResponse;
import com.nora.todo.dto.request.TaskRequest;
import com.nora.todo.model.Task;
import com.nora.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskResponse createTask(TaskRequest request){
        Task task = Task.builder()
                .title(request.getTitle())
                .listType(request.getListType())
                .build();
        Task save = taskRepository.save(task);
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .listType(task.getListType())
                .isCompleted(task.isCompleted())
                .build();
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public TaskResponse updateTask(Long id, TaskUpdateRequest updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setCompleted(updatedTask.isCompleted());
            taskRepository.save(task);
            return TaskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .listType(task.getListType())
                    .isCompleted(task.isCompleted())
                    .build();
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public List<Task> getTasksByListType(String listType) {
        return taskRepository.findByListType(listType);
    }

}
