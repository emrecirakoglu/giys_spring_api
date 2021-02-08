package com.ozgur.giys.api.task.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskLogRepository extends JpaRepository<TaskLog, Long>  {
    
}
