package com.ozgur.giys.api.messaging;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ozgur.giys.api.task.models.TaskType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseMessage {
    
    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private Object result;
    
    @JsonProperty("routing_key")
    private String routingKey;

    @JsonProperty("task_type")
    private TaskType taskType;

    @JsonProperty("task_id")
    private UUID taskId;


}
