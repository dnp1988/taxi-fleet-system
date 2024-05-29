package com.fleet.taxi.domain.usecase;

import com.fleet.taxi.domain.entity.BookingInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReviseBooking {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviseBooking.class);

    public void revise(BookingInfo bookingInfo) {
        LOGGER.info("Revising Booking Request {}", bookingInfo);
    }
}
