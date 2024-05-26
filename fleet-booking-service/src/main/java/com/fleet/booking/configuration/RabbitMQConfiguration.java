package com.fleet.booking.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Configuration;

import static com.fleet.booking.configuration.RabbitMQConstants.EXCHANGE_NAME;
import static com.fleet.booking.configuration.RabbitMQConstants.QUEUE;
import static com.fleet.booking.configuration.RabbitMQConstants.ROUTING_KEY;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {

    private final AmqpAdmin rabbitAdmin;

    public RabbitMQConfiguration(AmqpAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    public void declareQueue() {
        String queueName = declareQueue(QUEUE);
        declareDirectExchange(EXCHANGE_NAME);
        declareBinding(queueName, EXCHANGE_NAME, ROUTING_KEY);
    }

    private void declareBinding(String queueName, String exchangeName, String routingKey) {
        Binding.DestinationType destinationType = Binding.DestinationType.QUEUE;
        Binding binding = new Binding(queueName, destinationType, exchangeName, routingKey, null);
        rabbitAdmin.declareBinding(binding);
    }

    private void declareDirectExchange(String exchangeName) {
        DirectExchange exchange = new DirectExchange(exchangeName, true, false);
        rabbitAdmin.declareExchange(exchange);
    }

    private String declareQueue(String name) {
        return rabbitAdmin.declareQueue(new Queue(name, true, false, false));
    }
}
