package com.fleet.taxi.domain.usecase;

import com.fleet.taxi.domain.entity.BookingInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReviseBooking {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviseBooking.class);

    public void revise(BookingInput bookingInput) {
        LOGGER.info("Revising Booking {}", bookingInput);
    }
}
