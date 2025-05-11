package com.assessment.bajaj.controller;
import com.assessment.bajaj.Dto.RequestDto;
import com.assessment.bajaj.Dto.ResponseDto;
import com.assessment.bajaj.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bhfl")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ResponseDto> task(@RequestBody RequestDto request) {
        return ResponseEntity.ok(taskService.task(request));
    }

    @GetMapping
    public ResponseEntity<Map<String, Integer>> get() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }
}

