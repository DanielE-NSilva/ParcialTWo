package co.com.poli.moviesservice.service;

import co.com.poli.moviesservice.clientFeign.BookingClient;
import co.com.poli.moviesservice.clientFeign.ShowTimeClient;
import co.com.poli.moviesservice.persistence.entity.Movie;
import co.com.poli.moviesservice.persistence.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImp implements MovieService {
    private final MovieRepository movieRepository;
    private final ShowTimeClient showTimeClient;
    private final BookingClient bookingClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Movie save(Movie movie){
        return movieRepository.save(movie);
    };

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Movie delete(Long id) {
        Movie movieFind = movieRepository.findById(id).orElse(null);
        if(movieFind == null)
            return null;
        int movieShowTime = showTimeClient.findMoviesById(id).getCode();
        int movieBooking = bookingClient.findByMoviesIdId(id).getCode();
        if(movieShowTime == 204  && movieBooking == 204){
            movieRepository.deleteById(id);
            return movieFind;
        }else {
            if (movieShowTime == 200 && movieBooking == 204)
                return Movie.builder()
                        .id(0L)
                        .build();
            else if(movieBooking == 200 && movieShowTime == 204 )
                return Movie.builder()
                        .id(-1L)
                        .build();
            else
                return Movie.builder()
                        .id(-2L)
                        .build();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findById(Long Id){
        return movieRepository.findById(Id).orElse(null);
    }

}