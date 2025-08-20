package com.nora.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    @NotEmpty(message = "Title should be present")
    private String title;

    @NotEmpty(message = "Task type should be present")
    private String listType;

}
