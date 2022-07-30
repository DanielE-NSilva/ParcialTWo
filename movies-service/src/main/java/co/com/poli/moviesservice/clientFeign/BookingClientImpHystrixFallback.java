package co.com.poli.moviesservice.clientFeign;

import co.com.poli.moviesservice.helper.Response;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.OK;

@Component
public class BookingClientImpHystrixFallback implements BookingClient {
    @Override
    public Response findByMoviesIdId(Long id) {
        return Response.builder()
                .code(OK.value()).build();
    }
}
