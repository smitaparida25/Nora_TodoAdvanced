package com.nora.todo.controller;

import com.nora.todo.model.Habit;
import com.nora.todo.service.HabitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit")
@CrossOrigin
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public Habit addHabit(@RequestBody Habit habit) {
        return habitService.addHabit(habit);
    }

    @GetMapping
    public List<Habit> getHabits() {
        return habitService.getAllHabits();
    }

    @PutMapping
    public Habit updateHabit(@RequestBody Habit habit) {
        return habitService.updateHabit(habit);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
    }
}
