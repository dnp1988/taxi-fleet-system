package com.fleet.taxi.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {

    private final AmqpAdmin rabbitAdmin;
    private final String bookingExchangeName;
    private final String bookingQueueName;
    private final String bookingRoutingKey;

    public RabbitMQConfiguration(AmqpAdmin rabbitAdmin,
                                 @Value("${rabbitmq.booking.exchange-name}") String bookingExchangeName,
                                 @Value("${rabbitmq.booking.queue-name}") String bookingQueueName,
                                 @Value("${rabbitmq.booking.routing-key}") String bookingRoutingKey) {
        this.rabbitAdmin = rabbitAdmin;
        this.bookingExchangeName = bookingExchangeName;
        this.bookingQueueName = bookingQueueName;
        this.bookingRoutingKey = bookingRoutingKey;
        declareBookingQueue();
    }

    public void declareBookingQueue() {
        String queueName = declareBookingQueue(bookingQueueName);
        declareBinding(queueName, bookingExchangeName, bookingRoutingKey);
    }

    private void declareBinding(String queueName, String exchangeName, String routingKey) {
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
        rabbitAdmin.declareBinding(binding);
    }

    private String declareBookingQueue(String name) {
        return rabbitAdmin.declareQueue(new Queue(name, true, false, false));
    }
}
