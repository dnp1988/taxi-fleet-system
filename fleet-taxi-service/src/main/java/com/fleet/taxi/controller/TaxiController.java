package com.fleet.taxi.controller;

import com.fleet.taxi.api.ApiConstants;
import com.fleet.taxi.api.TaxiStatusResponse;
import com.fleet.taxi.domain.entity.TaxiStatus;
import com.fleet.taxi.domain.usecase.RetrieveStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.TAXI_BOOKINGS)
public class TaxiController {

    private RetrieveStatus retrieveStatus;

    public TaxiController(RetrieveStatus retrieveStatus) {
        this.retrieveStatus = retrieveStatus;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaxiStatusResponse retrieveTaxiStatus() {
        TaxiStatus taxiStatus = retrieveStatus.retrieve();
        return TaxiStatusResponse.builder()
                .name(taxiStatus.getName())
                .seats(taxiStatus.getSeats())
                .state(taxiStatus.getState().toString())
                .latitude(taxiStatus.getLatitude())
                .longitude(taxiStatus.getLongitude())
                .build();
    }
}
