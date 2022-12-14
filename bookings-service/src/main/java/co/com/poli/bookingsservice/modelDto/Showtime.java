package co.com.poli.bookingsservice.modelDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Showtime {
    private Long id;
    private String date;
    private ArrayList<Movie> moviesInfo;
}