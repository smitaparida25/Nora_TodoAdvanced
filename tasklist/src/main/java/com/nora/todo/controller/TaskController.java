package com.nora.todo.controller;

import com.nora.todo.model.Task;
import com.nora.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }
    @GetMapping("/by-date")
    public List<Task> getTasksByDate(@RequestParam String when){
        return taskService.getTasksByDate(when);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }
    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

}
