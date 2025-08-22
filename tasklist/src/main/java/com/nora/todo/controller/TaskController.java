package com.nora.todo.controller;

import com.nora.todo.dto.request.TaskRequest;
import com.nora.todo.dto.request.TaskUpdateRequest;
import com.nora.todo.dto.response.TaskResponse;
import com.nora.todo.model.Task;
import com.nora.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


import java.util.List;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> getTask(@RequestBody TaskRequest request) {
        TaskResponse task = taskService.createTask(request);
        return ResponseEntity.status(CREATED.value()).body(task);  // 201 CREATED + JSON body
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskUpdateRequest task){
        TaskResponse taskResponse = taskService.updateTask(id, task);
        return ResponseEntity.status(OK.value()).body(taskResponse);  // 200 OK + JSON body
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(NO_CONTENT.value()).build();  // 204 OK + no JSON body
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks(); // return everything
    }


}
