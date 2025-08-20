package com.nora.todo.constant;

import lombok.Getter;

@Getter
public enum TaskType {

    TODO("todo"),
    TODAY("today"),
    HABIT("habit");

    private final String type;

    TaskType(String type) {
        this.type = type;
    }
}
