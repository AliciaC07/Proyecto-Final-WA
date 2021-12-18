package practica.mocky.practica2pwa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DtoConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
