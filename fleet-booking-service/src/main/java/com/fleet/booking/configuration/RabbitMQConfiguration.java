package com.fleet.booking.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {

    private final AmqpAdmin rabbitAdmin;
    private final String bookingExchangeName;

    public RabbitMQConfiguration(AmqpAdmin rabbitAdmin,
                                 @Value("${rabbitmq.booking.exchange-name}") String bookingExchangeName) {
        this.rabbitAdmin = rabbitAdmin;
        this.bookingExchangeName = bookingExchangeName;
        declareBookingExchange();
    }

    public void declareBookingExchange() {
        declareDirectExchange(bookingExchangeName);
    }

    private void declareDirectExchange(String exchangeName) {
        DirectExchange exchange = new DirectExchange(exchangeName, true, false);
        rabbitAdmin.declareExchange(exchange);
    }
}
