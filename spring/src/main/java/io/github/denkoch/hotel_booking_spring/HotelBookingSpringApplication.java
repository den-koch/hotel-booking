package io.github.denkoch.hotel_booking_spring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@OpenAPIDefinition(
        info = @Info(
                title = "Hotel Booking",
                contact = @Contact(
                        name = "Denys Kochetov",
                        email = "@gmail.com"
                ),
                description = "Hotel booking management Spring app",
                version = "1.13",
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "local test server")
        }
)
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class HotelBookingSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingSpringApplication.class, args);
    }

}
