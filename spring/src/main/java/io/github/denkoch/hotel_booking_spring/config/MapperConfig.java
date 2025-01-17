package io.github.denkoch.hotel_booking_spring.config;

import io.github.denkoch.hotel_booking_spring.dto.RoomBuildingsDTO;
import io.github.denkoch.hotel_booking_spring.model.RoomId;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        return modelMapper;
    }

}
