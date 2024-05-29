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
public class TaxiStatus {

    private String name;

    private Integer seats;

    private TaxiStateEnum state;

    private Double latitude;

    private Double longitude;

    private BookingInfo assignment;
}
