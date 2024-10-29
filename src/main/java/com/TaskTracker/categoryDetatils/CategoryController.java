package com.TaskTracker.categoryDetatils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/addCategory")
    public ResponseEntity<?>addCategory(@RequestBody Categories categories){
        try {
            return categoryService.addCategory(categories);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(path = "getAll")
    public ResponseEntity<List<Categories>> getAllCategories(){
        try {
            return categoryService.getAllCategory();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(path = "/updateCategory/{id}")
    public ResponseEntity<?>updateCategoryName(@PathVariable Integer id,@RequestParam String name){
        try {
            return categoryService.updateCategoryName(id,name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/deleteCategory/{id}")
    public ResponseEntity<?>deleteCategory(@PathVariable Integer id){
        try {
            return categoryService.deleteCategory(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
