package co.com.poli.bookingsservice.helper;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.*;

@Component
public class ResponseBuild {

    public Response success() {
        return Response.builder()
                .data(OK)
                .code(OK.value()).build();
    }

    public Response success(Object data) {
        return Response.builder()
                .data(data)
                .code(OK.value()).build();
    }

    public Response failed(Object data) {
        return Response.builder()
                .data(data)
                .code(INTERNAL_SERVER_ERROR.value()).build();
    }


    public Response failed(Object data, int code ) {
        return Response.builder()
                .data(data)
                .code(code).build();
    }

    public Response success(Object data, int code ) {
        return Response.builder()
                .data(data)
                .code(code).build();
    }

}