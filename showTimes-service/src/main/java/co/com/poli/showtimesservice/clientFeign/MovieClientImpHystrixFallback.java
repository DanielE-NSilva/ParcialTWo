package co.com.poli.showtimesservice.clientFeign;

import co.com.poli.showtimesservice.helper.Response;
import co.com.poli.showtimesservice.modelDto.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieClientImpHystrixFallback implements MovieClient{
    @Override
    public Response findById(Long id) {
        return Response.builder()
                .data(new Movie())
                .code(200)
                .build();
    }
}
