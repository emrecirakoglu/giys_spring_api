package com.ozgur.giys.api.messaging;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ozgur.giys.api.task.models.TaskType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskMessage {

    @JsonProperty("type")
    private String type;

    @JsonProperty("task_type")
    private TaskType taskType;

    @JsonProperty("task_id")
    private UUID taskId;

    @JsonProperty("parameters")
    private Map<String, String> parameters;

}
