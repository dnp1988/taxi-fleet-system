package com.fleet.booking.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.booking.configuration.properties.RabbitMQProperties;
import com.fleet.message.util.MessageMapper;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitMQProperties.class)
@EnableRabbit
public class RabbitMQConfiguration {

    private final AmqpAdmin rabbitAdmin;
    private final RabbitMQProperties rabbitMQProperties;

    public RabbitMQConfiguration(AmqpAdmin rabbitAdmin,
                                 RabbitMQProperties rabbitMQProperties) {
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitMQProperties = rabbitMQProperties;
        declare();
    }

    public void declare() {
        declareDirectExchange(rabbitMQProperties.getBooking().getExchangeName());
        declareDirectExchange(rabbitMQProperties.getTaxi().getExchangeName());

        declareQueueAndBinding(rabbitMQProperties.getTaxi().getExchangeName(),
                rabbitMQProperties.getTaxi().getStatus().getRoutingKey(),
                rabbitMQProperties.getTaxi().getStatus().getQueueName());

        declareQueueAndBinding(rabbitMQProperties.getTaxi().getExchangeName(),
                rabbitMQProperties.getTaxi().getClaim().getRoutingKey(),
                rabbitMQProperties.getTaxi().getClaim().getQueueName());
    }

    private void declareDirectExchange(String exchangeName) {
        DirectExchange exchange = new DirectExchange(exchangeName, true, false);
        rabbitAdmin.declareExchange(exchange);
    }

    private void declareQueueAndBinding(String exchangeName, String routingKey, String queueName) {
        String name = rabbitAdmin.declareQueue(new Queue(queueName, true, false, false));
        rabbitAdmin.declareBinding(new Binding(name, Binding.DestinationType.QUEUE, exchangeName, routingKey, null));
    }

    @Bean
    public MessageMapper createMessageMapper(ObjectMapper objectMapper) {
        return new MessageMapper(objectMapper);
    }
}
