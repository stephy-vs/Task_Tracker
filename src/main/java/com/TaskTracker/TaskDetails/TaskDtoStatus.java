package com.TaskTracker.TaskDetails;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDtoStatus {
    private Integer id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
