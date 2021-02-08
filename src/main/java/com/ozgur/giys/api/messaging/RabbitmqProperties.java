package com.ozgur.giys.api.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqProperties {
    
    private String queueName;
    private String exchangeName;
    private String routingKey;
}
