package co.com.poli.bookingsservice.persistence.repository;

import co.com.poli.bookingsservice.persistence.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long id);
    List<Booking> findByShowTimeId(Long id);

    @Transactional(readOnly = true)
    @Query(value = "SELECT TOP 1  * FROM Bookings ORDER BY Id DESC", nativeQuery = true)
    Booking lastRecord();
}
