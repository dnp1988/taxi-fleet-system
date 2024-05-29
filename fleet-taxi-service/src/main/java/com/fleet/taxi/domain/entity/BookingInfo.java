package com.fleet.taxi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class BookingInfo {

    private String id;

    private Long passengers;

    private Double destinationLatitude;

    private Double destinationLongitude;
}
