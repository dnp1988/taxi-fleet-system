package com.fleet.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingMessage {

    private String id;

    private Long passengers;

    private Double destinationLatitude;

    private Double destinationLongitude;
}
