package com.ozgur.giys.api.task.models;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskDto implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 6011516317163487768L;

    @NonNull
    private TaskType taskType;
    
    @NonNull
    private Map<String,Object> taskParameters;

    @NonNull
    private String routingKey;

}
