package com.fleet.booking.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private Long passengers;

    private Double destinationLatitude;

    private Double destinationLongitude;
}
