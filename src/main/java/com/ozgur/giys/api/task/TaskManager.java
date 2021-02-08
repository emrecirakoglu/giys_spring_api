package com.ozgur.giys.api.task;

import java.util.Date;
import java.util.UUID;

import com.ozgur.giys.api.messaging.MessageSender;
import com.ozgur.giys.api.messaging.TaskMessage;
import com.ozgur.giys.api.messaging.TaskResponseMessage;
import com.ozgur.giys.api.task.models.TaskDto;
import com.ozgur.giys.api.task.models.TaskLog;
import com.ozgur.giys.api.task.models.TaskLogRepository;
import com.ozgur.giys.api.task.models.TaskStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskManager {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private TaskLogRepository taskLogRepository; 

    public TaskResponseMessage handleTask(TaskDto task) {

        // Generate task message
        TaskMessage taskMessage = TaskMessage.builder()
                .type("task")
                .taskType(task.getTaskType())
                .taskId(UUID.randomUUID())
                .parameters(task.getTaskParameters())
                .build();

        TaskResponseMessage response = this.messageSender.sendTaskMessage(taskMessage, task.getRoutingKey());

        TaskLog log = TaskLog.builder()
                .client("pardus")
                .createdAt(new Date())
                .createdFrom("Admin")
                .taskStatus((response.getStatus() == 0) ? TaskStatus.SUCCESS : TaskStatus.ERROR)
                .build();
        
        this.taskLogRepository.save(log);

        return response;
    }

}
