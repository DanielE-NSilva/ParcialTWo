package co.com.poli.bookingsservice.clientFeign;

import co.com.poli.bookingsservice.helper.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Users-service", fallback = UserClientImpHystrixFallback.class)
public interface UserClient {
    @GetMapping("/ParcialTwo/v1/user/{id}")
    Response findById(@PathVariable("id") Long id);
}