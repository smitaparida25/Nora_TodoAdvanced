package com.nora.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonProperty("isCompleted")
    private boolean isCompleted;

    private String listType;
}
