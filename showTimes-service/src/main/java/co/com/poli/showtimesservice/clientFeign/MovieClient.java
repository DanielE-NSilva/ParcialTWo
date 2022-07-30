package co.com.poli.showtimesservice.clientFeign;

import co.com.poli.showtimesservice.helper.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Movies-service", fallback = MovieClientImpHystrixFallback.class)
public interface MovieClient {
    @GetMapping("/ParcialTwo/v1/movie/{id}")
    Response findById(@PathVariable("id") Long id);
}