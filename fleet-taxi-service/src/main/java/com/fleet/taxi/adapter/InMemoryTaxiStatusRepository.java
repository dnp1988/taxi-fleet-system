package com.fleet.taxi.adapter;

import com.fleet.taxi.domain.entity.BookingInfo;
import com.fleet.taxi.domain.entity.TaxiStatus;
import com.fleet.taxi.domain.entity.TaxiStateEnum;
import com.fleet.taxi.domain.repository.TaxiStatusRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@PropertySource("classpath:application.yml")
public class InMemoryTaxiStatusRepository implements TaxiStatusRepository {

    private TaxiStatus taxiStatus;

    public InMemoryTaxiStatusRepository(@Value("${taxi.name}") String name,
                      @Value("${taxi.seats}") Integer seats,
                      @Value("${taxi.initial-state}") TaxiStateEnum initialState,
                      @Value("${taxi.initial-longitude}") Double initialLongitude,
                      @Value("${taxi.initial-latitude}") Double initialLatitude) {
        this.taxiStatus = TaxiStatus.builder()
                .name(name)
                .seats(seats)
                .state(initialState)
                .latitude(initialLatitude)
                .longitude(initialLongitude)
                .build();
    }

    @Override
    public TaxiStatus retrieveTaxiStatus() {
        return TaxiStatus.builder()
                .name(taxiStatus.getName())
                .seats(taxiStatus.getSeats())
                .state(taxiStatus.getState())
                .latitude(taxiStatus.getLatitude())
                .longitude(taxiStatus.getLongitude())
                .assignment(Optional.ofNullable(taxiStatus.getAssignment())
                        .map(assignment -> BookingInfo.builder()
                                .passengers(assignment.getPassengers())
                                .destinationLatitude(assignment.getDestinationLatitude())
                                .destinationLongitude(assignment.getDestinationLongitude())
                                .build())
                        .orElse(null))
                .build();
    }

    @Override
    public void updateTaxStatus(TaxiStateEnum taxiStateEnum) {
        this.taxiStatus.setState(taxiStateEnum);
    }

    @Override
    public void updateTaxiAssignment(BookingInfo bookingInfo) {
        this.taxiStatus.setAssignment(bookingInfo);
    }

    @Override
    public void updateTaxiPosition(Double latitude, Double longitude) {
        this.taxiStatus.setLatitude(latitude);
        this.taxiStatus.setLongitude(longitude);
    }
}
