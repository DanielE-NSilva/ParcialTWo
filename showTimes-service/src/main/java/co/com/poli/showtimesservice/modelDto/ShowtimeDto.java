package co.com.poli.showtimesservice.modelDto;

import com.netflix.discovery.provider.Serializer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Getter
@Serializer
@Builder
public class ShowtimeDto {
    private Long id;
    private LocalDateTime date;
    private ArrayList<Movie> moviesInfo;
}
