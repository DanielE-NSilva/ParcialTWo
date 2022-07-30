package co.com.poli.moviesservice.clientFeign;


import co.com.poli.moviesservice.helper.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Bookings-service", fallback = BookingClientImpHystrixFallback.class)
public interface BookingClient {
    @GetMapping("/ParcialTwo/v1/booking/movies/{moviesId}")
    Response findByMoviesIdId(@PathVariable("moviesId") Long id);
}
