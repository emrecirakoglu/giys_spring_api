package com.ozgur.giys.api.task.models;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TaskDto {

    @NonNull
    private TaskType taskType;
    
    @NonNull
    private String routingKey;

    @NonNull
    private Map<String,String> taskParameters;

}
