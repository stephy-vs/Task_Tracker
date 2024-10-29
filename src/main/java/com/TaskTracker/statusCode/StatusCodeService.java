package com.TaskTracker.statusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusCodeService {
    @Autowired
    private StatusCodeRepo statusCodeRepo;

    public ResponseEntity<?> addStatus(StatusCode statusCode) {
        return new ResponseEntity<>(statusCodeRepo.save(statusCode), HttpStatus.OK);
    }

    public ResponseEntity<List<StatusCode>> getAllStatus() {
        return new ResponseEntity<>(statusCodeRepo.findAll(),HttpStatus.OK);
    }
}
