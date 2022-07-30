package co.com.poli.showtimesservice.clientFeign;

import co.com.poli.showtimesservice.helper.Response;
import org.springframework.stereotype.Component;

@Component
public class BookingClientImpHystrixFallback implements BookingClient  {
    @Override
    public Response findByShowTimesId(Long id) {
        return Response.builder()
                .code(200).build();
    }
}
