package com.data.javarest04.service;

import com.data.javarest04.entity.Booking;
import com.data.javarest04.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IService<Booking,Long> {
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Booking add(Booking booking) {
        return bookingRepository.save(booking);
    }
    @Override
    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }
    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
    @Override
    public Page<Booking> getAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }
    public Page<Booking> findByCustomerPhone(String phone, Pageable pageable) {
        return bookingRepository.findByCustomerPhone(phone, pageable);
    }
}
