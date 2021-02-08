package com.ozgur.giys.api.messaging;

import com.ozgur.giys.api.task.models.TaskDto;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    
    @RabbitListener(queues = "${rabbitmq.queueName}")
    public void handleMessage(TaskDto taskMessage){
        System.out.println("Message Received " + taskMessage);
    }


}
