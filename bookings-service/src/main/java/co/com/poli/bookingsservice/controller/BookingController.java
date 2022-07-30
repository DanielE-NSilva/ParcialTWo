package co.com.poli.bookingsservice.controller;


import co.com.poli.bookingsservice.helper.Common;
import co.com.poli.bookingsservice.helper.Response;
import co.com.poli.bookingsservice.helper.ResponseBuild;
import co.com.poli.bookingsservice.modelDto.BookingDto;
import co.com.poli.bookingsservice.persistence.entity.Booking;
import co.com.poli.bookingsservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final ResponseBuild builder;
    private final Common common;

    @GetMapping
    public Response findAll(){
        return builder.success(bookingService.findAll());
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        return builder.success(bookingService.findById(id));
    }

    @GetMapping("/users/{userId}")
    public Response findByUserId(@PathVariable("userId") Long id){
        List<Booking> bookings = bookingService.findByUserId(id);
        if(bookings.isEmpty())
            return builder.success("Not found",NO_CONTENT.value());
        return builder.success(bookings);
    }

    @GetMapping("/showTimes/{ShowTimesId}")
    public Response findByShowTimesId(@PathVariable("ShowTimesId") Long id){
        List<Booking> bookings = bookingService.findByShowTimeId(id);
        if(bookings.isEmpty())
            return builder.success(new ArrayList<>(), NO_CONTENT.value());
        return builder.success();
    }

    @GetMapping("/movies/{moviesId}")
    public Response findByMoviesIdId(@PathVariable("moviesId") Long id){
        Long bookings = bookingService.findByMoviesIdId(id);
        System.out.println("Controller "+bookings);
        if(bookings == 0)
            return builder.success(new ArrayList<>(), NO_CONTENT.value());
        return builder.success();
    }

    @PostMapping
    public Response save(@Valid @RequestBody Booking booking, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(common.formatMessage(result));
        }
        BookingDto bookingDto = bookingService.save(booking);
        if(bookingDto.getUserInfo() == null)
            return builder.failed("User not found ",NOT_FOUND.value());
        if(bookingDto.getShowtimeInfo() == null)
            return builder.failed("ShowTime not found ",NOT_FOUND.value());
        if(bookingDto.getMoviesBookings() == null)
            return builder.failed("Movie not found ",NOT_FOUND.value());
        if(bookingDto.getId() == -1 )
            return builder.failed("Not found ",NOT_FOUND.value());
        return builder.success(bookingDto);
    }

    @DeleteMapping("/{id}")
    public Response deleteById(@PathVariable("id") Long id){
        BookingDto bookingDto = bookingService.findById(id);
        if(bookingDto == null)
            return builder.failed("Booking not found", NOT_FOUND.value());
        bookingService.delete(bookingDto.getId());
        return builder.success(bookingDto);
    }
}
