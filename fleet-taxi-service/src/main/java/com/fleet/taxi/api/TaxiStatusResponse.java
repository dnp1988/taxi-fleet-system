package com.fleet.taxi.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaxiStatusResponse {
    String name;
    Integer seats;
    String state;
    Double latitude;
    Double longitude;
}
