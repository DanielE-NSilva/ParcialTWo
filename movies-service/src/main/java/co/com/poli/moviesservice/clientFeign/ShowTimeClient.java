package co.com.poli.moviesservice.clientFeign;

import co.com.poli.moviesservice.helper.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ShowTimes-service", fallback = ShowTimeClientImpHystrixFallback.class)
public interface ShowTimeClient {
    @GetMapping("/ParcialTwo/v1/showtime/Movies/{id}")
    Response findMoviesById(@PathVariable("id") Long id);
}
