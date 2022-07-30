package co.com.poli.bookingsservice.clientFeign;

import co.com.poli.bookingsservice.helper.Response;
import co.com.poli.bookingsservice.modelDto.Showtime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ShowTimeClientImpHystrixFallback implements ShowTimeClient {
    @Override
    public Response findById(Long id) {
        return Response.builder()
                .data(Showtime.builder().moviesInfo(new ArrayList<>()).build() )
                .code(200)
                .build();
    }
}
