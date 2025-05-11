package com.assessment.bajaj.controller;

import com.assessment.bajaj.Dto.RequestDto;
import com.assessment.bajaj.Dto.ResponseDto;
import com.assessment.bajaj.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bhfl")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

 @PostMapping
    public ResponseEntity<?> task(@RequestBody RequestDto request, HttpServletResponse response, HttpServletRequest req) {
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        System.out.println("POST HIT ✅ method: " + req.getMethod());
        return ResponseEntity.ok(taskService.task(request));
    }

    @GetMapping
    public ResponseEntity<?> get(HttpServletResponse response, HttpServletRequest req) {
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        System.out.println("GET HIT ✅ method: " + req.getMethod());
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }
}

