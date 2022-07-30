package co.com.poli.bookingsservice.clientFeign;

import co.com.poli.bookingsservice.helper.Response;
import co.com.poli.bookingsservice.modelDto.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpHystrixFallback implements UserClient {
    @Override
    public Response findById(Long id) {
        return Response.builder()
                .data(new User())
                .code(200)
                .build();
    }
}
