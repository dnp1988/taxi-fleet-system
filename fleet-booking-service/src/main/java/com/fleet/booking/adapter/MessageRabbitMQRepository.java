package com.fleet.booking.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.booking.domain.entity.BookingInput;
import com.fleet.booking.domain.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class MessageRabbitMQRepository implements MessageRepository {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final String bookingExchangeName;
    private final String bookingRoutingKey;

    public MessageRabbitMQRepository(RabbitTemplate rabbitTemplate,
                                     ObjectMapper objectMapper,
                                     @Value("${rabbitmq.booking.exchange-name}") String bookingExchangeName,
                                     @Value("${rabbitmq.booking.routing-key}") String bookingRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.bookingExchangeName = bookingExchangeName;
        this.bookingRoutingKey = bookingRoutingKey;
    }

    @Override
    public void sendBookingMessage(BookingInput bookingInput) {
        rabbitTemplate.convertAndSend(bookingExchangeName, bookingRoutingKey, getMessageAsString(bookingInput));
    }

    private String getMessageAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
