package com.fleet.taxi.domain.repository;

import com.fleet.taxi.domain.entity.BookingInfo;
import com.fleet.taxi.domain.entity.TaxiStateEnum;
import com.fleet.taxi.domain.entity.TaxiStatus;

public interface TaxiStatusRepository {

    TaxiStatus retrieveTaxiStatus();

    void updateTaxStatus(TaxiStateEnum taxiStateEnum);

    void updateTaxiAssignment(BookingInfo bookingInfo);

    void updateTaxiPosition(Double latitude, Double longitude);
}
