package com.fleet.booking.domain.usecase;

import com.fleet.booking.domain.entity.BookingInput;
import com.fleet.booking.domain.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateBooking {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBooking.class);

    private final MessageRepository messageRepository;

    public CreateBooking(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String create(BookingInput bookingInput) {
        LOGGER.info("Creating Booking {}", bookingInput);
        messageRepository.sendBookingMessage(bookingInput);
        return UUID.randomUUID().toString();
    }
}
