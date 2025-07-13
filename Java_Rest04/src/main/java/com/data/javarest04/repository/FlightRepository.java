package com.data.javarest04.repository;

import com.data.javarest04.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Page<Flight> findByDepartureContainingAndDestinationContaining(String departure, String destination, Pageable pageable);
}
