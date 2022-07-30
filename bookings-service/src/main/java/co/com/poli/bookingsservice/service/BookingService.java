package co.com.poli.bookingsservice.service;

import co.com.poli.bookingsservice.modelDto.BookingDto;
import co.com.poli.bookingsservice.persistence.entity.Booking;

import java.util.List;

public interface BookingService {
    BookingDto save(Booking booking);
    void delete(Long bookingId);
    List<Booking> findAll();
    BookingDto findById(Long id);
    List<Booking> findByUserId(Long userid);
    List<Booking> findByShowTimeId(Long showTimeId);
    Long findByMoviesIdId(Long movieId);
}
