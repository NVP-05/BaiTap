package com.data.javarest04.service;

import com.data.javarest04.entity.Flight;
import com.data.javarest04.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightService implements IService<Flight, Long> {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight add(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight update(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Flight> getAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    public Page<Flight> searchFlights(String departure, String destination, Pageable pageable) {
        return flightRepository.findByDepartureContainingAndDestinationContaining(
                departure == null ? "" : departure,
                destination == null ? "" : destination,
                pageable
        );
    }
}
