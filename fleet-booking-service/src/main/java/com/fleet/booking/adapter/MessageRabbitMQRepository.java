package com.fleet.booking.adapter;

import com.fleet.booking.configuration.properties.RabbitMQProperties;
import com.fleet.booking.domain.repository.MessageRepository;
import com.fleet.message.dto.BookingMessage;
import com.fleet.message.util.MessageMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MessageRabbitMQRepository implements MessageRepository {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;
    private final MessageMapper messageMapper;

    public MessageRabbitMQRepository(RabbitTemplate rabbitTemplate,
                                     MessageMapper messageMapper,
                                     RabbitMQProperties rabbitMQProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageMapper = messageMapper;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    @Override
    public void sendBookingMessage(BookingMessage bookingMessage) {
        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getBooking().getExchangeName(),
                rabbitMQProperties.getBooking().getRequest().getRoutingKey(),
                messageMapper.getMessageAsString(bookingMessage));
    }
}
