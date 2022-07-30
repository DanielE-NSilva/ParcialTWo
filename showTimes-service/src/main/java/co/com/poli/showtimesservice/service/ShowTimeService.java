package co.com.poli.showtimesservice.service;

import co.com.poli.showtimesservice.persistence.entity.Showtime;
import co.com.poli.showtimesservice.modelDto.ShowtimeDto;
import java.util.List;

public interface ShowTimeService {
    ShowtimeDto save(Showtime showtime);
    List<Showtime> findAll();
    ShowtimeDto findById(Long id);
    ShowtimeDto updateId(Long id, Showtime showtime);
    List<Long> findAllMovies();
    Long findMovieById(Long id);
}
