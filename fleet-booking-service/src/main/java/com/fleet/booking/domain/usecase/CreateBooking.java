package com.fleet.booking.domain.usecase;

import com.fleet.booking.domain.entity.BookingInput;
import com.fleet.booking.domain.repository.MessageRepository;
import com.fleet.message.dto.BookingMessage;
import com.google.common.base.Verify;
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
        String bookingId = UUID.randomUUID().toString();
        LOGGER.info("Creating Booking {} {}", bookingId, bookingInput);
        validateBookingInput(bookingInput);

        messageRepository.sendBookingMessage(BookingMessage.builder()
                        .id(bookingId)
                        .passengers(bookingInput.getPassengers())
                        .destinationLatitude(bookingInput.getDestinationLatitude())
                        .destinationLongitude(bookingInput.getDestinationLongitude())
                .build());

        return bookingId;
    }

    private void validateBookingInput(BookingInput bookingInput) {
        Verify.verifyNotNull(bookingInput);
        Verify.verifyNotNull(bookingInput.getPassengers());
        Verify.verifyNotNull(bookingInput.getDestinationLatitude());
        Verify.verifyNotNull(bookingInput.getDestinationLongitude());
    }
}
