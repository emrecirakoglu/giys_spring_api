package com.ozgur.giys.api.task.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task_log")
public class TaskLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private TaskType task_type;

    @Enumerated
    private TaskStatus taskStatus;
    
    @Column(name = "task_id")
    private UUID taskId;

    @Column
    private String client;

    @Column
    private String createdFrom;

    @Column
    private Date createdAt;

    @Column
    private String description;

}
