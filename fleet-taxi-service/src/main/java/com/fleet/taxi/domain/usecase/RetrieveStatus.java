package com.fleet.taxi.domain.usecase;

import com.fleet.taxi.domain.entity.TaxiStatus;
import com.fleet.taxi.domain.repository.TaxiStatusRepository;
import org.springframework.stereotype.Component;

@Component
public class RetrieveStatus {

    private final TaxiStatusRepository taxiStatusRepository;

    public RetrieveStatus(TaxiStatusRepository taxiStatusRepository) {
        this.taxiStatusRepository = taxiStatusRepository;
    }

    public TaxiStatus retrieve() {
        return taxiStatusRepository.retrieveTaxiStatus();
    }
}
