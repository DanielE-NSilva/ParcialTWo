package co.com.poli.bookingsservice;

import co.com.poli.bookingsservice.persistence.entity.Booking;
import co.com.poli.bookingsservice.persistence.repository.BookingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

@DataJpaTest
public class BookingRepositoryMockTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void whenFindByUserId(){
        ArrayList<Integer> movies = new ArrayList<>();
        movies.add(1);
        movies.add(2);
        Booking booking = Booking.builder()
                .id(1L)
                .userId(1L)
                .showTimeId(1L)
                .movies(movies)
                .build();
        bookingRepository.save(booking);
        booking.setId(2L);
        bookingRepository.save(booking);
        int bookings = bookingRepository.findByUserId(1L).size();
        Assertions.assertThat(bookings).isEqualTo(2);
    }

}