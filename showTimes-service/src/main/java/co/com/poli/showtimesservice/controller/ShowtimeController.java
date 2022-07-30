package co.com.poli.showtimesservice.controller;


import co.com.poli.showtimesservice.helper.Common;
import co.com.poli.showtimesservice.helper.Response;
import co.com.poli.showtimesservice.helper.ResponseBuild;
import co.com.poli.showtimesservice.modelDto.ShowtimeDto;
import co.com.poli.showtimesservice.persistence.entity.Showtime;
import co.com.poli.showtimesservice.service.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/showtime")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowTimeService showTimeService;
    private final ResponseBuild builder;
    private final Common common;

    @GetMapping
    public Response findAll(){
        return builder.success(showTimeService.findAll());
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        return builder.success(showTimeService.findById(id));
    }

    @GetMapping("/Movies")
    public Response findAllMovies(){
        return builder.success(showTimeService.findAllMovies());
    }

    @GetMapping("/Movies/{id}")
    public Response findMoviesById(@PathVariable("id") Long id){
        Long Movie = showTimeService.findMovieById(id);
        if(Movie == null)
            return builder.success("Movies Not found",NO_CONTENT.value());
        return builder.success(Movie);
    }

    @PostMapping
    public Response save(@Valid @RequestBody Showtime showtime, BindingResult result){
        if(result.hasErrors())
            return builder.failed(common.formatMessage(result));
        if(showtime.getMoviesId() == null)
            return builder.failed("Not found movies",NOT_FOUND.value());

        ShowtimeDto showtimeDto = showTimeService.save(showtime);
        if(showtimeDto == null)
            return builder.failed("Not found movies",NOT_FOUND.value());
        return builder.success(showtimeDto,CREATED.value());
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody Showtime showtime){
        if(showTimeService.findById(id) == null)
            return builder.failed("Showtime Not found",NOT_FOUND.value());
        ShowtimeDto showtimeFind = showTimeService.updateId(id,showtime);

        if(showtimeFind == null)
            return builder.failed("All Movies Not found ",NOT_FOUND.value());
        if(showtimeFind.getId() == -1)
            return builder.failed("Used in bookings",NOT_FOUND.value());
        return builder.success(showtimeFind);
    }

}
