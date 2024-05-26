package com.fleet.booking.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.booking.domain.entity.BookingInput;
import com.fleet.booking.domain.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Repository;

import static com.fleet.booking.configuration.RabbitMQConstants.EXCHANGE_NAME;
import static com.fleet.booking.configuration.RabbitMQConstants.ROUTING_KEY;

@Repository
public class RabbitMQRepository implements MessageRepository {

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    public RabbitMQRepository(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendBookingMessage(BookingInput bookingInput) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, getMessageAsString(bookingInput));
    }

    private String getMessageAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
