package com.TaskTracker.categoryDetatils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseEntity<?> addCategory(Categories categories) {
        return new ResponseEntity<>(categoryRepo.save(categories), HttpStatus.OK);
    }

    public ResponseEntity<List<Categories>> getAllCategory() {
        return new ResponseEntity<>(categoryRepo.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<?> updateCategoryName(Integer id, String name) {
        Optional<Categories> categoriesOptional = categoryRepo.findById(id);
        if (categoriesOptional.isPresent()){
            Categories categories = categoriesOptional.get();
            categories.setName(name);
            categoryRepo.save(categories);
            return new ResponseEntity<>(categories,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Id "+id+" isn't valid",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteCategory(Integer id) {
        Optional<Categories> categoriesOptional = categoryRepo.findById(id);
        if (categoriesOptional.isPresent()){
            Categories categories = categoriesOptional.get();
            String name = categories.getName();
            categoryRepo.delete(categories);
            return new ResponseEntity<>(name+" is deleted",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Id "+id+" isn't valid",HttpStatus.NOT_FOUND);
        }
    }
}
