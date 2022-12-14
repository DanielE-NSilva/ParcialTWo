package co.com.poli.bookingsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class BookingsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingsServiceApplication.class, args);
    }
}
