package com.assessment.bajaj.controller;

import com.assessment.bajaj.Dto.RequestDto;
import com.assessment.bajaj.Dto.ResponseDto;
import com.assessment.bajaj.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bhfl")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> task(@RequestBody RequestDto request) {
        ResponseDto response = taskService.task(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Integer>> get() {
        Map<String, Integer> response = Map.of("operation_code", 1);
        return ResponseEntity.ok(response);
    }
}
