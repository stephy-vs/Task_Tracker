package com.TaskTracker.TaskDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Integer id;
    private String description;
    private String categoryName;
    private String statusName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
