package co.com.poli.bookingsservice.modelDto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class BookingDto {
    private Long id;
    private User userInfo;
    private Showtime showtimeInfo;
    private List<Long> moviesBookings;
}