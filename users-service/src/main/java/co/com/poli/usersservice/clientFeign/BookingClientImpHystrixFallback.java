package co.com.poli.usersservice.clientFeign;

import co.com.poli.usersservice.helper.Response;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.OK;

@Component
public class BookingClientImpHystrixFallback implements BookingClient{

    @Override
    public Response findByUserId(Long id) {
        return Response.builder()
                .code(OK.value()).build();
    }

}
