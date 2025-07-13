package com.data.javarest04.repository;

import com.data.javarest04.entity.Booking;
import com.data.javarest04.entity.Flight;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Page<Booking> findByCustomerPhone(String customerPhone, Pageable pageable);
}
