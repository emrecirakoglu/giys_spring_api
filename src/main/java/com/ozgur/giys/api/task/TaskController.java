package com.ozgur.giys.api.task;

import com.ozgur.giys.api.messaging.MessageSender;
import com.ozgur.giys.api.messaging.TaskResponseMessage;
import com.ozgur.giys.api.task.models.TaskDto;
import com.ozgur.giys.api.task.models.TaskResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/task")
public class TaskController {

    @Autowired
    private TaskManager taskManager;

    @Autowired
    MessageSender messageSender;

    @PostMapping(value = "/")
    public ResponseEntity<Object> sendTask(@RequestBody TaskDto task) {
        System.out.println(task.toString());

        TaskResponseMessage responseMessage = this.taskManager.handleTask(task);

        return ResponseEntity.ok().body(
            TaskResponseDto.builder()
                .status(responseMessage.getStatus())
                .message(responseMessage.getMessage())
                .result(responseMessage.getResult())
                .build()
        );
    }
}
