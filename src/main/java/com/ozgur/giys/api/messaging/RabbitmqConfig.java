package com.ozgur.giys.api.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class RabbitmqConfig {

    @Autowired
    private RabbitmqProperties rabbitmqProperites;

    @Bean
    public Queue queue() {
        return new Queue(rabbitmqProperites.getQueueName(), true, true, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(rabbitmqProperites.getExchangeName());
    }

    @Bean
    public Binding binding(final Queue queue, final DirectExchange directExchange) {
        return BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(rabbitmqProperites.getRoutingKey());
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("EEEEEEEEEEEEEEE");
        });
        rabbitTemplate.setReturnsCallback((message) -> {
            // System.out.println("Exception");
            // System.out.println(message.toString());
        });

        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
