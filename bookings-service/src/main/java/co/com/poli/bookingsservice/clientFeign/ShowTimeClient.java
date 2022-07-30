package co.com.poli.bookingsservice.clientFeign;

import co.com.poli.bookingsservice.helper.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ShowTimes-service", fallback = ShowTimeClientImpHystrixFallback.class)
public interface ShowTimeClient {
    @GetMapping("/ParcialTwo/v1/showtime/{id}")
    Response findById(@PathVariable("id") Long id);
}
