package com.fleet.taxi.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.taxi.domain.entity.BookingInput;
import com.fleet.taxi.domain.usecase.ReviseBooking;
import com.fleet.taxi.listener.entity.BookingMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingMessageListener {

    private final ReviseBooking reviseBooking;
    private final ObjectMapper objectMapper;

    public BookingMessageListener(ReviseBooking reviseBooking, ObjectMapper objectMapper) {
        this.reviseBooking = reviseBooking;
        this.objectMapper = objectMapper;
    }

//    @RabbitListener(bindings =
//        @QueueBinding(
//                value = @Queue(value = "${rabbitmq.booking.queue-name}", durable = "true"),
//                exchange = @Exchange(value = "${rabbitmq.booking.exchange-name}", type = ExchangeTypes.FANOUT)
//        )
//    )
    @RabbitListener(queues = "${rabbitmq.booking.queue-name}")
    public void processMessage(String message) {
        BookingMessage bookingMessage = parseMessage(message, BookingMessage.class);
        reviseBooking.revise(new BookingInput(bookingMessage.getPassengers()));
    }

    private <T> T parseMessage(String message, Class<T> type) {
        try {
            return objectMapper.readValue(message, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
