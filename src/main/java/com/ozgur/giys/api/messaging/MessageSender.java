package com.ozgur.giys.api.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitmqProperties rabbitmqProperties;

    public TaskResponseMessage sendTaskMessage(TaskMessage taskMessage, String routingKey) {

        rabbitTemplate.setReplyTimeout(180000);

        TaskResponseMessage response = this.rabbitTemplate.convertSendAndReceiveAsType(
                rabbitmqProperties.getExchangeName(), routingKey, taskMessage,
                new ParameterizedTypeReference<TaskResponseMessage>() {
                });

        return response;
    }
}
