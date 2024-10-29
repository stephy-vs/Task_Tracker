package com.TaskTracker.TaskDetails;

import com.TaskTracker.categoryDetatils.Categories;
import com.TaskTracker.categoryDetatils.CategoryRepo;
import com.TaskTracker.statusCode.StatusCode;
import com.TaskTracker.statusCode.StatusCodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private StatusCodeRepo statusCodeRepo;
    public ResponseEntity<?> addTask(Task task) {
        Task task1 = new Task();
        task1.setDescription(task.getDescription());
        task1.setCategoryId(task.getCategoryId());
        task1.setStatusId(1);
        task1.setCreatedAt(LocalDateTime.now());
        taskRepo.save(task1);
        return new ResponseEntity<>(task1, HttpStatus.OK);
    }

    public ResponseEntity<List<TaskDto>> getTasks(Integer categoryId) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<Task> taskList = taskRepo.findByCategoryId(categoryId);
        if (!taskList.isEmpty()){
            for (Task task : taskList){
                TaskDto taskDto = new TaskDto();
                taskDto.setId(task.getId());
                taskDto.setDescription(task.getDescription());
                Optional<Categories> categoriesOptional = categoryRepo.findById(task.getCategoryId());
                if (categoriesOptional.isPresent()){
                    Categories categories = categoriesOptional.get();
                    taskDto.setCategoryName(categories.getName());
                }
                Optional<StatusCode> statusCodeOptional = statusCodeRepo.findById(task.getStatusId());
                if (statusCodeOptional.isPresent()){
                    StatusCode statusCode = statusCodeOptional.get();
                    taskDto.setStatusName(statusCode.getStatus());
                }
                taskDto.setCreatedAt(task.getCreatedAt());
                taskDto.setUpdatedAt(task.getUpdatedAt());
                taskDtoList.add(taskDto);
            }
            return new ResponseEntity<>(taskDtoList,HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateTaskStatus(Integer taskId, Integer categoryId,Integer statusId) {
        Optional<Task> taskOptional = taskRepo.findByIdAndCategoryId(taskId,categoryId);
        if (taskOptional.isPresent()){
            Task task = taskOptional.get();
            task.setStatusId(statusId);
            task.setUpdatedAt(LocalDateTime.now());
            taskRepo.save(task);
            return new ResponseEntity<>(task,HttpStatus.OK);
        }return new ResponseEntity<>("Task Id "+taskId+" and categoryId "+categoryId+" are not matching",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<TaskDtoStatus>> getTaskByStatusId(Integer categoryId, Integer statusId) {
        List<Task> taskList = taskRepo.findByCategoryIdAndStatusId(categoryId,statusId);
        List<TaskDtoStatus> taskDtoStatusList = new ArrayList<>();
        if (!taskList.isEmpty()){
            for (Task task : taskList){
                TaskDtoStatus taskDtoStatus = new TaskDtoStatus();
                taskDtoStatus.setId(task.getId());
                taskDtoStatus.setDescription(task.getDescription());
                taskDtoStatus.setCreatedAt(task.getCreatedAt());
                taskDtoStatus.setUpdatedAt(task.getUpdatedAt());
                taskDtoStatusList.add(taskDtoStatus);
            }
            return new ResponseEntity<>(taskDtoStatusList,HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<?> deleteTaskById(Integer id) {
        Optional<Task> taskOptional = taskRepo.findById(id);
        if (taskOptional.isPresent()){
            Task task = taskOptional.get();
            String name = task.getDescription();
            taskRepo.delete(task);
            return new ResponseEntity<>(name+" is deleted",HttpStatus.OK);
        }return new ResponseEntity<>("Id "+id+" isn't matching",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<TaskDto>> getAllTask() {
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<Task> taskList = taskRepo.findAll();
        if (!taskList.isEmpty()){
            for (Task task : taskList){
                TaskDto taskDto = new TaskDto();
                taskDto.setId(task.getId());
                Optional<Categories> categoriesOptional = categoryRepo.findById(task.getCategoryId());
                if (categoriesOptional.isPresent()){
                    Categories categories = categoriesOptional.get();
                    taskDto.setCategoryName(categories.getName());
                }
                taskDto.setDescription(task.getDescription());
                taskDto.setCreatedAt(task.getCreatedAt());
                Optional<StatusCode> statusCodeOptional = statusCodeRepo.findById(task.getStatusId());
                if (statusCodeOptional.isPresent()){
                    StatusCode statusCode = statusCodeOptional.get();
                    taskDto.setStatusName(statusCode.getStatus());
                }
                taskDto.setUpdatedAt(task.getUpdatedAt());
                taskDtoList.add(taskDto);
            }
            return new ResponseEntity<>(taskDtoList,HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }
}
