package co.com.poli.showtimesservice.clientFeign;

import co.com.poli.showtimesservice.helper.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Bookings-service", fallback = BookingClientImpHystrixFallback.class)
public interface BookingClient {
    @GetMapping("ParcialTwo/v1/booking/ShowTimes/{ShowTimesId}")
    Response findByShowTimesId(@PathVariable("ShowTimesId") Long id);
}