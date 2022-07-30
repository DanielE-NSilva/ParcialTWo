package co.com.poli.usersservice.clientFeign;

import co.com.poli.usersservice.helper.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Bookings-service", fallback = BookingClientImpHystrixFallback.class)
public interface BookingClient {
    @GetMapping("/ParcialTwo/v1/booking/users/{userId}")
    Response findByUserId(@PathVariable("userId") Long id);
}