package com.fleet.booking.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class BookingInput {

    private Long passengers;

    private Double destinationLatitude;

    private Double destinationLongitude;
}
