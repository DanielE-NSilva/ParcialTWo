package co.com.poli.moviesservice.service;

import co.com.poli.moviesservice.persistence.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie save(Movie movie);
    Movie delete(Long id);
    List<Movie> findAll();
    Movie findById(Long Id);
}
