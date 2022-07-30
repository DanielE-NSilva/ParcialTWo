package co.com.poli.bookingsservice.service;

import co.com.poli.bookingsservice.clientFeign.ShowTimeClient;
import co.com.poli.bookingsservice.clientFeign.UserClient;
import co.com.poli.bookingsservice.modelDto.BookingDto;
import co.com.poli.bookingsservice.modelDto.Movie;
import co.com.poli.bookingsservice.modelDto.Showtime;
import co.com.poli.bookingsservice.modelDto.User;
import co.com.poli.bookingsservice.persistence.entity.Booking;
import co.com.poli.bookingsservice.persistence.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingServiceImp implements  BookingService {
    private final BookingRepository bookingRepository;
    private final ShowTimeClient showTimeClient;
    private final UserClient userClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookingDto save(Booking booking){
        ModelMapper modelMapper = new ModelMapper();
        User user;
        Showtime showTime;
        List<Long> moviesBookings = new ArrayList<>();
        Object objectUser = userClient.findById(booking.getUserId()).getData();
        if (objectUser != null) user = modelMapper.map(objectUser, User.class);
        else user = null;
        Object objectShowTime = showTimeClient.findById(booking.getShowTimeId()).getData();
        if (objectShowTime != null) {
            showTime = modelMapper.map(objectShowTime, Showtime.class);
            if(booking.getMovies() != null)
                for (Movie movieId: showTime.getMoviesInfo()) {
                    for(long movieIdClient: booking.getMovies() ){
                        if (movieId.getId() != null )
                            if(movieId.getId() == movieIdClient)
                                moviesBookings.add(movieIdClient);
                    }
                }
            else
               moviesBookings = null;
        } else {
            showTime = null;
            moviesBookings = null;
        }

        BookingDto bookingDto = BookingDto.builder()

                .userInfo(user)
                .showtimeInfo(showTime)
                .moviesBookings(moviesBookings)
                .build();
        if( bookingDto.getUserInfo() == null ||
            bookingDto.getShowtimeInfo() == null ||
            bookingDto.getMoviesBookings() == null){
            bookingDto.setId(-1L);
            return bookingDto;
        }

        bookingRepository.save(booking);
        bookingDto.setId(bookingRepository.lastRecord().getId());
        return bookingDto;
    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public void delete(Long bookingId){
        bookingRepository.deleteById(bookingId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    //Find for Booking
    @Override
    @Transactional(readOnly = true)
    public BookingDto findById(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking != null) {
            ModelMapper modelMapper = new ModelMapper();
            User user;
            Showtime showTime;
            Object objectUser = userClient.findById(booking.getUserId()).getData();
            user = modelMapper.map(objectUser, User.class);
            Object objectShowTime = showTimeClient.findById(booking.getShowTimeId()).getData();
            showTime = modelMapper.map(objectShowTime, Showtime.class);
            BookingDto bookingDto = BookingDto.builder()
                    .id(id)
                    .userInfo(user)
                    .showtimeInfo(showTime)
                    .moviesBookings(booking.getMovies())
                    .build();
            return bookingDto;
        }
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Booking> findByUserId(Long userId){
        return bookingRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> findByShowTimeId(Long showTimeId){
        return bookingRepository.findByShowTimeId(showTimeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findByMoviesIdId(Long movieId){
        List<Booking> bookingList = bookingRepository.findAll();
        if(bookingList.isEmpty())
            return 0L;
        for (Booking booking:bookingList) {
            for (Long movies:booking.getMovies()) {
                if(movies.longValue() == movieId){
                    return 1L;
                }
            }
        }
        return 0L;
    }
}

