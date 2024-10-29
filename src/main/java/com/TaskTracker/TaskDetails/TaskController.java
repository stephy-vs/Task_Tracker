package com.TaskTracker.TaskDetails;

import com.TaskTracker.categoryDetatils.Categories;
import com.TaskTracker.categoryDetatils.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/task")
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping(path = "/addTask")
    public ResponseEntity<?>addTask(@RequestBody Task task){
        try {
            return taskService.addTask(task);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getTaskById")
    public ResponseEntity<List<TaskDto>>getTasks(@RequestParam Integer categoryId){
        Optional<Categories> categoriesOptional = categoryRepo.findById(categoryId);
        if (categoriesOptional.isPresent()){
            return taskService.getTasks(categoryId);
        }return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getByStatus")
    public ResponseEntity<List<TaskDtoStatus>>getTaskByStatus(@RequestParam Integer categoryId,
                                                     @RequestParam Integer statusId){
        try {
            return taskService.getTaskByStatusId(categoryId,statusId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getAllTask")
    public ResponseEntity<List<TaskDto>>getAllTask(){
        try {
            return taskService.getAllTask();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(path = "updateTaskStatus")
    public ResponseEntity<?>updateTaskStatus(@RequestParam Integer taskId,@RequestParam Integer categoryId,
                                             @RequestParam Integer statusId){
        try {
            return taskService.updateTaskStatus(taskId,categoryId,statusId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/deleteTask/{id}")
    public ResponseEntity<?>deleteTaskById(@PathVariable Integer id){
        try {
            return taskService.deleteTaskById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
