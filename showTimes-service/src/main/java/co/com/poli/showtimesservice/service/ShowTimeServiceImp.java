package co.com.poli.showtimesservice.service;

import co.com.poli.showtimesservice.clientFeign.BookingClient;
import co.com.poli.showtimesservice.clientFeign.MovieClient;
import co.com.poli.showtimesservice.modelDto.Movie;
import co.com.poli.showtimesservice.modelDto.ShowtimeDto;
import co.com.poli.showtimesservice.persistence.entity.Showtime;
import co.com.poli.showtimesservice.persistence.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImp implements ShowTimeService {
    private final ShowtimeRepository showtimeRepository;
    private final MovieClient movieClient;
    private final BookingClient bookingClient;

    private ArrayList<Movie> findsMovies(Showtime showtime){
        ArrayList<Movie> moviesFind = new ArrayList<>();
        ArrayList<Long> moviesId = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < showtime.getMoviesId().size(); i++) {
            Object object = movieClient.findById(showtime.getMoviesId().get(i)).getData();
            if(object != null){
                Movie movie = modelMapper.map(object, Movie.class);
                if(movie.getId() != null) {
                    moviesFind.add(movie);
                    moviesId.add(showtime.getMoviesId().get(i));
                }
            }
        }
        showtime.setMoviesId(moviesId);
        return moviesFind;
    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public ShowtimeDto save(Showtime showtime){
        ArrayList<Movie>  moviesFind = findsMovies(showtime);
        if(moviesFind.size() > 0) {
            List<Long> moviesFindF = new ArrayList<>();
            for (Movie movie : moviesFind){
                moviesFindF.add(movie.getId());
            }
            showtime.setMoviesId(moviesFindF);
            showtimeRepository.save(showtime);
            Long idSave = showtimeRepository.lastRecord().getId();
            ShowtimeDto showtimeDto = ShowtimeDto.builder()
                    .id(idSave)
                    .date(showtime.getDate())
                    .moviesInfo(moviesFind)
                    .build();
            return showtimeDto;
        }else
            return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ShowtimeDto findById(Long id){
        Showtime showtime = showtimeRepository.findById(id).orElse(null);
        if (showtime == null)
            return null;
        else{
            ArrayList<Movie> movies = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            for (int i = 0; i < showtime.getMoviesId().size(); i++) {
                Movie movie = modelMapper.map(movieClient.findById(showtime.getMoviesId().get(i)).getData(), Movie.class);
                movies.add(movie);
            }
            ShowtimeDto showtimeDto = ShowtimeDto.builder()
                    .id(showtime.getId())
                    .date(showtime.getDate())
                    .moviesInfo(movies)
                    .build();
            return showtimeDto;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Showtime> findAll(){
        return showtimeRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShowtimeDto updateId(Long id,Showtime showtime){
        int Code = bookingClient.findByShowTimesId(id).getCode();

        if(Code == 204) {
            ArrayList<Movie> moviesFind = findsMovies(showtime);
            if (moviesFind.size() > 0) {
                showtimeRepository.updateById(id, showtime.getDate());
                showtimeRepository.updateByIdDelete(id);
                for (int i = 0; i < moviesFind.size(); i++) {
                    showtimeRepository.updateByIdMovies(id, moviesFind.get(i).getId());
                }
                ShowtimeDto showtimeDto = ShowtimeDto.builder()
                        .id(id)
                        .date(showtime.getDate())
                        .moviesInfo(moviesFind)
                        .build();
                return showtimeDto;
            } else
                return null;
        }else
            return ShowtimeDto.builder()
                    .id(-1L)
                    .build();

    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findAllMovies(){
        return showtimeRepository.findAllMovies();
    }

    @Override
    @Transactional(readOnly = true)
    public Long findMovieById(Long id){
        return showtimeRepository.findMovieById(id);
    }


}
