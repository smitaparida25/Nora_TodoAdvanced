package com.nora.todo.service;

import com.nora.todo.dto.request.TaskUpdateRequest;
import com.nora.todo.dto.response.TaskResponse;
import com.nora.todo.dto.request.TaskRequest;
import com.nora.todo.model.Task;
import com.nora.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.nora.todo.constant.TaskType.HABIT;

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
                .id(save.getId())
                .title(save.getTitle())
                .listType(save.getListType())
                .isCompleted(save.isCompleted())
                .build();
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public TaskResponse updateTask(Long id, TaskUpdateRequest updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setCompleted(updatedTask.getIsCompleted());
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
        List<Task> tasks = taskRepository.findByListType(listType);
        if(HABIT.getType().equalsIgnoreCase(listType)){
                LocalDate today = LocalDate.now();
            for(Task t: tasks){
                if(t.getUpdatedAt().toLocalDate().isBefore(today)) {
                    t.setCompleted(false);
                }
            }
            taskRepository.saveAll(tasks);
        }
        return tasks;
    }

}
