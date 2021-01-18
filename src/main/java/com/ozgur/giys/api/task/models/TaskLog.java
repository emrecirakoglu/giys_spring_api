package com.ozgur.giys.api.task.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_log")
public class TaskLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_status")
    private TaskStatus taskStatus;
    
    @Column(name = "task_id")
    private UUID taskId;

    @Column
    private String client;

    @Column
    private String createdFrom;
}
