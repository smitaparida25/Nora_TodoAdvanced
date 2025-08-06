package com.nora.todo.service;

import com.nora.todo.model.Habit;
import com.nora.todo.repository.HabitRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {
    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Habit addHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Habit updateHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }
    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void resetAllHabits() {
        List<Habit> habits = habitRepository.findAll();
        for (Habit habit : habits) {
            habit.setCompleted(true);
        }
        habitRepository.saveAll(habits);
    }

}
