package co.com.poli.moviesservice.controller;


import co.com.poli.moviesservice.helper.Common;
import co.com.poli.moviesservice.helper.Response;
import co.com.poli.moviesservice.helper.ResponseBuild;
import co.com.poli.moviesservice.persistence.entity.Movie;
import co.com.poli.moviesservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final ResponseBuild builder;
    private final Common common;

    @GetMapping
    public Response findAll(){
        return builder.success(movieService.findAll());
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        Movie movie = movieService.findById(id);
        if (movie == null)
            return builder.success(new ArrayList<>(), NO_CONTENT.value());
        return builder.success(movie);
    }

    @PostMapping
    public Response save(@Valid @RequestBody Movie movie, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(common.formatMessage(result));
        }
        return builder.success(movieService.save(movie),CREATED.value());
    }

    @DeleteMapping("/{id}")
    public Response deleteById(@PathVariable("id") Long id){
        Movie movie = movieService.delete(id);
        if(movie == null)
            return builder.failed("Movie not found", NOT_FOUND.value());
        if(movie.getId() == 0)
            return builder.failed("Used on showtime", NOT_ACCEPTABLE.value());
        if(movie.getId()== -1)
            return builder.failed("Used on booking", NOT_ACCEPTABLE.value());
        if(movie.getId()== -2)
            return builder.failed("Used on booking and showtime", NOT_ACCEPTABLE.value());

        return builder.success(movie);
    }

}
