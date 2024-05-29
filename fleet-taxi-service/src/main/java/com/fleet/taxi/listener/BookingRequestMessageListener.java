package com.fleet.taxi.listener;

import com.fleet.message.dto.BookingMessage;
import com.fleet.message.util.MessageMapper;
import com.fleet.taxi.domain.entity.BookingInfo;
import com.fleet.taxi.domain.usecase.ReviseBooking;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingRequestMessageListener {

    private final ReviseBooking reviseBooking;
    private final MessageMapper messageMapper;

    public BookingRequestMessageListener(ReviseBooking reviseBooking,
                                         MessageMapper messageMapper) {
        this.reviseBooking = reviseBooking;
        this.messageMapper = messageMapper;
    }

    @RabbitListener(queues = "${rabbitmq.booking.request.queue-name}")
    public void processMessage(String message) {
        BookingMessage bookingMessage = messageMapper.parseMessage(message, BookingMessage.class);
        reviseBooking.revise(BookingInfo.builder()
                        .id(bookingMessage.getId())
                        .passengers(bookingMessage.getPassengers())
                        .destinationLatitude(bookingMessage.getDestinationLatitude())
                        .destinationLongitude(bookingMessage.getDestinationLongitude())
                .build());
    }
}
