package com.TaskTracker.statusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/status")
@CrossOrigin
public class StatusCodeController {
    @Autowired
    private StatusCodeService statusCodeService;

    @PostMapping(path = "/addStatus")
    public ResponseEntity<?> addStatusCode(@RequestBody StatusCode statusCode){
        try {
            return statusCodeService.addStatus(statusCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getStatus")
    public ResponseEntity<List<StatusCode>>getAllStatusCode(){
        try {
            return statusCodeService.getAllStatus();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
