package com.assessment.bajaj.controller;

import com.assessment.bajaj.Dto.RequestDto;
import com.assessment.bajaj.Dto.ResponseDto;
import com.assessment.bajaj.service.TaskService;
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

@GetMapping
public ResponseEntity<Map<String, Integer>> get(HttpServletResponse response) {
    response.setHeader("Cache-Control", "no-store");
    return ResponseEntity.ok(Map.of("operation_code", 1));
}

@PostMapping
public ResponseEntity<ResponseDto> post(@RequestBody RequestDto request, HttpServletResponse response) {
    response.setHeader("Cache-Control", "no-store");
    return ResponseEntity.ok(taskService.task(request));
}

}
