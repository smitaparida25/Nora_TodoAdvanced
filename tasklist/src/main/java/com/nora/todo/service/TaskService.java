package com.nora.todo.service;

import com.nora.todo.model.Task;
import com.nora.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public List<Task> getTasksByDate(String dayType){
        LocalDate date;
        switch(dayType.toLowerCase()){
            case "today" -> date = LocalDate.now();
            case "yesterday" -> date = LocalDate.now().minusDays(1);
            default -> throw new IllegalArgumentException("Invalid");
        }
        return taskRepository.findByDate(date);
    }
}
