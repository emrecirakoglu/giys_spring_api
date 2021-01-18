package com.ozgur.giys.api.task;

import com.ozgur.giys.api.task.models.TaskDto;
import com.ozgur.giys.api.task.models.TaskResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/task")
public class TaskController {

    @PostMapping(value = "/")
    public ResponseEntity<TaskResponseDto> sendTask(@RequestBody TaskDto task) {
        System.out.println(task.toString());
        TaskResponseDto response = TaskResponseDto.builder().status("success").build();
        return ResponseEntity.ok().body(response);
    }
    
}
