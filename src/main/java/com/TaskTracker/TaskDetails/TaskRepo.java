package com.TaskTracker.TaskDetails;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task,Integer> {
    List<Task> findByCategoryId(Integer categoryId);

    Optional<Task> findByIdAndCategoryId(Integer taskId, Integer categoryId);

    List<Task> findByCategoryIdAndStatusId(Integer categoryId, Integer statusId);
}
