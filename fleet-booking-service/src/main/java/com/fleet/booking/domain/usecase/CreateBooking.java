package com.fleet.booking.domain.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.booking.domain.entity.BookingInput;
import com.fleet.booking.domain.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateBooking {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBooking.class);

    private MessageRepository messageRepository;

    public CreateBooking(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String create(BookingInput bookingInput) {
        LOGGER.info("Incoming Input {}", bookingInput);
        messageRepository.sendBookingMessage(bookingInput);
        return UUID.randomUUID().toString();
    }
}
